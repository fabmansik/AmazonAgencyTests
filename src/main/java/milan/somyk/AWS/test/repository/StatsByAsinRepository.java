package milan.somyk.AWS.test.repository;

import milan.somyk.AWS.test.document.StatsByAsin;
import milan.somyk.AWS.test.dto.SummaryStatsByAsin;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StatsByAsinRepository extends MongoRepository<StatsByAsin, String> {
    Optional<StatsByAsin> findByParentAsin(String asin);
    List<StatsByAsin> findByParentAsinIn(List<String> asinList);
    @Aggregation(pipeline = {
            "{$group: {" +
                    "_id: '$id',"+
                    "unitsOrdered: {$sum: '$salesByAsin.unitsOrdered'},"+
                    "unitsOrderedB2B: {$sum: '$salesByAsin.unitsOrderedB2B'},"+
                    "orderedProductSales: {$sum: '$salesByAsin.orderedProductSales.amount'},"+
                    "orderedProductSalesB2B: {$sum: '$salesByAsin.orderedProductSalesB2B.amount'},"+
                    "totalOrderItems: {$sum: '$salesByAsin.totalOrderItems'},"+
                    "totalOrderItemsB2B: {$sum: '$salesByAsin.totalOrderItemsB2B'},"+
                    "browserSessions: {$sum: '$trafficByAsin.browserSessions'},"+
                    "browserSessionsB2B: {$sum: '$trafficByAsin.browserSessionsB2B'},"+
                    "mobileAppSessions: {$sum: '$trafficByAsin.mobileAppSessions'},"+
                    "mobileAppSessionsB2B: {$sum: '$trafficByAsin.mobileAppSessionsB2B'},"+
                    "sessions: {$sum: '$trafficByAsin.sessions'},"+
                    "sessionsB2B: {$sum: '$trafficByAsin.sessionsB2B'},"+
                    "browserSessionPercentage: {$sum: '$trafficByAsin.browserSessionPercentage'},"+
                    "browserSessionPercentageB2B: {$sum: '$trafficByAsin.browserSessionPercentageB2B'},"+
                    "mobileAppSessionPercentage: {$sum: '$trafficByAsin.mobileAppSessionPercentage'},"+
                    "mobileAppSessionPercentageB2B: {$sum: '$trafficByAsin.mobileAppSessionPercentageB2B'},"+
                    "sessionPercentage: {$sum: '$trafficByAsin.sessionPercentage'},"+
                    "sessionPercentageB2B: {$sum: '$trafficByAsin.sessionPercentageB2B'},"+
                    "browserPageViews: {$sum: '$trafficByAsin.browserPageViews'},"+
                    "browserPageViewsB2B: {$sum: '$trafficByAsin.browserPageViewsB2B'},"+
                    "mobileAppPageViews: {$sum: '$trafficByAsin.mobileAppPageViews'},"+
                    "mobileAppPageViewsB2B: {$sum: '$trafficByAsin.mobileAppPageViewsB2B'},"+
                    "pageViews: {$sum: '$trafficByAsin.pageViews'},"+
                    "pageViewsB2B: {$sum: '$trafficByAsin.pageViewsB2B'},"+
                    "browserPageViewsPercentage: {$sum: '$trafficByAsin.browserPageViewsPercentage'},"+
                    "browserPageViewsPercentageB2B: {$sum: '$trafficByAsin.browserPageViewsPercentageB2B'},"+
                    "mobileAppPageViewsPercentage: {$sum: '$trafficByAsin.mobileAppPageViewsPercentage'},"+
                    "mobileAppPageViewsPercentageB2B: {$sum: '$trafficByAsin.mobileAppPageViewsPercentageB2B'},"+
                    "pageViewsPercentage: {$sum: '$trafficByAsin.pageViewsPercentage'},"+
                    "pageViewsPercentageB2B: {$sum: '$trafficByAsin.pageViewsPercentageB2B'},"+
                    "buyBoxPercentage: {$sum: '$trafficByAsin.buyBoxPercentage'},"+
                    "buyBoxPercentageB2B: {$sum: '$trafficByAsin.buyBoxPercentageB2B'},"+
                    "unitSessionPercentage: {$sum: '$trafficByAsin.unitSessionPercentage'},"+
                    "unitSessionPercentageB2B: {$sum: '$trafficByAsin.unitSessionPercentageB2B'},"+
                    "}}"
    })
    SummaryStatsByAsin sumByAsin();
}
