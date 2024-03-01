package milan.somyk.AWS.test.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milan.somyk.AWS.test.document.StatsByAsin;
import milan.somyk.AWS.test.document.StatsByDate;
import milan.somyk.AWS.test.dto.*;
import milan.somyk.AWS.test.dto.response.ResponseContainer;
import milan.somyk.AWS.test.mapper.SummaryMapper;
import milan.somyk.AWS.test.repository.StatsByAsinRepository;
import milan.somyk.AWS.test.repository.StatsByDateRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsService {
    private final StatsByDateRepository statsByDateRepository;
    private final StatsByAsinRepository statsByAsinRepository;
    private final SummaryMapper summaryMapper;
    private final CacheManager cacheManager;
    public ResponseContainer getStatsByDate(String date) throws InterruptedException {
        ResponseContainer responseContainer = new ResponseContainer();
        Cache cache = cacheManager.getCache("statsByDate");
        if(!Objects.isNull(cache)){
            StatsByDate statsByDate1 = new StatsByDate();
            if(!Objects.isNull(cache.get(date))){
                statsByDate1 = (StatsByDate) cache.get(date).get();
                if(!Objects.isNull(statsByDate1)){
                    return responseContainer.setSuccessResult(statsByDate1);
                }
            }
        }
        StatsByDate statsByDate;
        Thread.sleep(2000);
        try {
            statsByDate = statsByDateRepository.findByDate(date).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseContainer.setErrorMessageAndStatusCode(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        if (Objects.isNull(statsByDate)) {
            log.error("date not found");
            return responseContainer.setErrorMessageAndStatusCode("date not found", HttpStatus.BAD_REQUEST.value());
        }
        return responseContainer.setSuccessResult(statsByDate);
    }

    public ResponseContainer getStatsByDates(String startDate, String endDate) throws InterruptedException {
        Thread.sleep(2000);
        ResponseContainer responseContainer = new ResponseContainer();
        List<StatsByDate> byDateBetween;
        if (!StringUtils.hasText(startDate)) {
            log.error("no startDate");
            return responseContainer.setErrorMessageAndStatusCode("no startDate", HttpStatus.BAD_REQUEST.value());
        }
        if (!StringUtils.hasText(endDate)) {
            log.error("no endDate");
            return responseContainer.setErrorMessageAndStatusCode("no endDate", HttpStatus.BAD_REQUEST.value());
        }
        char[] startChars = startDate.toCharArray();
        int c = startChars[startChars.length - 1];
        startChars[startChars.length - 1] = Integer.toString(Character.getNumericValue(c) - 1).toCharArray()[0];
        String searchStart = new String(startChars);
        char[] endChars = endDate.toCharArray();
        int b = endChars[endChars.length - 1];
        endChars[endChars.length - 1] = Integer.toString(Character.getNumericValue(b) + 1).toCharArray()[0];
        String searchEnd = new String(endChars);
        try {
            byDateBetween = statsByDateRepository.findByDateBetween(searchStart, searchEnd);
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseContainer.setErrorMessageAndStatusCode(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseContainer.setSuccessResult(byDateBetween);
    }

    public ResponseContainer getStatsByAsin(String asin) throws InterruptedException {
        ResponseContainer responseContainer = new ResponseContainer();
        Cache cache = cacheManager.getCache("statsByAsin");
        if(!Objects.isNull(cache)){
            StatsByAsin stats = new StatsByAsin();
            if(!Objects.isNull(cache.get(asin))){
                stats = (StatsByAsin) cache.get(asin).get();
                if(!Objects.isNull(stats)){
                    return responseContainer.setSuccessResult(stats);
                }
            }
        }
        Thread.sleep(2000);
        StatsByAsin statsByAsin;
        try {
            statsByAsin = statsByAsinRepository.findByParentAsin(asin).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseContainer.setErrorMessageAndStatusCode(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        if (Objects.isNull(statsByAsin)) {
            log.error("asin not found");
            return responseContainer.setErrorMessageAndStatusCode("asin not found", HttpStatus.BAD_REQUEST.value());
        }
        return responseContainer.setSuccessResult(statsByAsin);
    }

    public ResponseContainer getStatsByAsinList(List<String> asinList) throws InterruptedException {
        ResponseContainer responseContainer = new ResponseContainer();
        Cache cache = cacheManager.getCache("statsByAsin");
        List<StatsByAsin> statsList = new ArrayList<>();
        if(!Objects.isNull(cache)){
            StatsByAsin stats = new StatsByAsin();
            for(String item : asinList){
                if(!Objects.isNull(cache.get(item))){
                    stats = (StatsByAsin) cache.get(item).get();
                    if(!Objects.isNull(stats)){
                        statsList.add(stats);
                    }
                }
            }
            if(!statsList.isEmpty()){
                return responseContainer.setSuccessResult(statsList);
            }
        }
        Thread.sleep(2000);
        List<StatsByAsin> statsByAsinList;
        try {
            statsByAsinList = statsByAsinRepository.findByParentAsinIn(asinList);
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseContainer.setErrorMessageAndStatusCode(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseContainer.setSuccessResult(statsByAsinList);
    }

    public ResponseContainer getSumStats(String type) throws InterruptedException {
        Cache cache = cacheManager.getCache("summary");
        ResponseContainer responseContainer = new ResponseContainer();
        if(type.equals("asin")){
            if(!Objects.isNull(cache)){
                SummaryByAsinDto summaryByAsinDto = new SummaryByAsinDto();
                if(!Objects.isNull(cache.get(type))){
                    summaryByAsinDto = (SummaryByAsinDto) cache.get(type).get();
                    if(!Objects.isNull(summaryByAsinDto)){
                        return responseContainer.setSuccessResult(summaryByAsinDto);
                    }
                }
            }
        }
        if(type.equals("date")){
            if(!Objects.isNull(cache)){
                SummaryByDateDto summary = new SummaryByDateDto();
                if(!Objects.isNull(cache.get(type))){
                    summary = (SummaryByDateDto) cache.get(type).get();
                    if(!Objects.isNull(summary)){
                        return responseContainer.setSuccessResult(summary);
                    }
                }
            }
        }

        Thread.sleep(2000);
        if (type.equals("asin")) {
            SummaryStatsByAsin statsAndTrafficByAsin;
            try {
                statsAndTrafficByAsin = statsByAsinRepository.sumByAsin();
            } catch (Exception e) {
                log.error(e.getMessage());
                return responseContainer.setErrorMessageAndStatusCode(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
            SummaryByAsinDto dto = summaryMapper.toAsinDto(statsAndTrafficByAsin);
            return responseContainer.setSuccessResult(dto);
        }
        if (type.equals("date")) {
            SummaryStatsByDate statsByDate;
            try {
                statsByDate = statsByDateRepository.sumByDate();
            } catch (Exception e) {
                log.error(e.getMessage());
                return responseContainer.setErrorMessageAndStatusCode(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
            SummaryByDateDto dateDto = summaryMapper.toDateDto(statsByDate);
            return responseContainer.setSuccessResult(dateDto);
        }

        log.error("incorrect type");
        return responseContainer.setErrorMessageAndStatusCode("incorrect type", HttpStatus.BAD_REQUEST.value());
    }
}
