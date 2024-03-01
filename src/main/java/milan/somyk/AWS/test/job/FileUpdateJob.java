package milan.somyk.AWS.test.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milan.somyk.AWS.test.document.StatsByAsin;
import milan.somyk.AWS.test.document.StatsByDate;
import milan.somyk.AWS.test.dto.*;
import milan.somyk.AWS.test.dto.response.ResponseContainer;
import milan.somyk.AWS.test.mapper.SummaryMapper;
import milan.somyk.AWS.test.repository.ReportSpecificationRepository;
import milan.somyk.AWS.test.repository.StatsByAsinRepository;
import milan.somyk.AWS.test.repository.StatsByDateRepository;
import milan.somyk.AWS.test.service.CacheService;
import milan.somyk.AWS.test.service.JsonFileService;
import milan.somyk.AWS.test.service.StatsService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileUpdateJob {
    private final CacheService cacheService;
    private final JsonFileService jsonFileService;
    private final StatsByDateRepository statsByDateRepository;
    private final StatsByAsinRepository statsByAsinRepository;
    private final ReportSpecificationRepository reportSpecificationRepository;
    private final StatsService statsService;
    private final SummaryMapper summaryMapper;
    @Scheduled(cron = "0/7 * * * * *")
    public void process() throws InterruptedException {
        ResponseContainer responseContainer = jsonFileService.updateFile();
        if(responseContainer.isError()){
            log.error("database not updated");
            return;
        }
        ReportFile reportFile = (ReportFile) responseContainer.getResult();
        ReportSpecification reportSpecification = reportFile.getReportSpecification();
        List<StatsByDate> salesAndTrafficByDate = reportFile.getSalesAndTrafficByDate();
        List<StatsByAsin> salesAndTrafficByAsin = reportFile.getSalesAndTrafficByAsin();
        List<StatsByDate> foundStatsByDates;
        try {
            foundStatsByDates = statsByDateRepository.saveAll(salesAndTrafficByDate);
        } catch (Exception e){
            log.error(e.getMessage());
            return;
        }
        List<StatsByAsin> foundStatsByAsin;
        try{
            foundStatsByAsin = statsByAsinRepository.saveAll(salesAndTrafficByAsin);
        } catch (Exception e){
            log.error(e.getMessage());
            return;
        }
        ReportSpecification foundReport;
        try{
            foundReport = reportSpecificationRepository.findReportSpecificationByDataStartTimeAndDataEndTime(reportSpecification.getDataStartTime(), reportSpecification.getDataEndTime());
        } catch (Exception e){
            log.error(e.getMessage());
            return;
        }
        ReportSpecification savedReport = new ReportSpecification();
        if(Objects.isNull(foundReport)){
            try{
                savedReport = reportSpecificationRepository.save(reportSpecification);
            }catch (Exception e){
                log.error(e.getMessage());
                return;
            }
        }
        log.info("database updated");
        cacheService.clearCache();
        ResponseContainer asin = statsService.getSumStats("asin");
        if(asin.isError()){
            log.error(responseContainer.getErrorMessage());
        }else{
            SummaryByAsinDto asinSum = (SummaryByAsinDto) asin.getResult();
            cacheService.updateAsinSumCache(asinSum);
        }
        ResponseContainer date = statsService.getSumStats("date");
        if(date.isError()){
            log.error(responseContainer.getErrorMessage());
        }else{
            SummaryByDateDto dateSum = (SummaryByDateDto) date.getResult();
            cacheService.updateDateSumCache(dateSum);
        }
        cacheService.updateStatsByDateCache(foundStatsByDates);
        cacheService.updateStatsByAsinCache(foundStatsByAsin);
        cacheService.updateReportSpecification(savedReport);
        log.info("cache updated");
    }

}
