package milan.somyk.AWS.test.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milan.somyk.AWS.test.document.StatsByAsin;
import milan.somyk.AWS.test.document.StatsByDate;
import milan.somyk.AWS.test.dto.ReportSpecification;
import milan.somyk.AWS.test.dto.SummaryByAsinDto;
import milan.somyk.AWS.test.dto.SummaryByDateDto;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheService {
    private final CacheManager cacheManager;

    public void updateStatsByDateCache(List<StatsByDate> list) {
        Cache salesByData = cacheManager.getCache("statsByDate");
        if (Objects.isNull(salesByData)) {
            log.error("statsByDateCache is null");
        } else {
            for (StatsByDate item : list) {
                salesByData.put(item.getDate(), item);
            }
            log.info("statsByDateCache is set");
        }
    }


    public void updateStatsByAsinCache(List<StatsByAsin> list) {
        Cache salesByAsin = cacheManager.getCache("statsByAsin");
        if (Objects.isNull(salesByAsin)) {
            log.error("statsByAsinCache is null");
        } else {
            for (StatsByAsin item : list) {
                salesByAsin.put(item.getParentAsin(), item);
            }
            log.info("statsByAsinCache is set");
        }
    }


    public void updateReportSpecification(ReportSpecification reportSpecification) {
        Cache report = cacheManager.getCache("report");
        if (Objects.isNull(report)) {
            log.error("reportSpecificationCache is null");
        } else {
            SimpleKey key = new SimpleKey(reportSpecification.getDataStartTime(), reportSpecification.getDataEndTime());
            report.put(key, reportSpecification);
        }
    }
    @Caching(evict = {@CacheEvict("summary"),@CacheEvict("statsByDate"),@CacheEvict("statsByAsin"),@CacheEvict("report")})
    public void clearCache(){
    }

    public void updateAsinSumCache(SummaryByAsinDto summary) {
        Cache summaryCache = cacheManager.getCache("summary");
        if (Objects.isNull(summaryCache)) {
            log.error("summaryCache is null");
        } else {
            summaryCache.put("asin", summary);
        }
    }

    public void updateDateSumCache(SummaryByDateDto summary) {
        Cache summaryCache = cacheManager.getCache("summary");
        if (Objects.isNull(summaryCache)) {
            log.error("summaryCache is null");
        } else {
            summaryCache.put("date", summary);
        }
    }
}
