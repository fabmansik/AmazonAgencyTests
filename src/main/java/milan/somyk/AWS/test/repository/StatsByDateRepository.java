package milan.somyk.AWS.test.repository;

import milan.somyk.AWS.test.document.StatsByDate;
import milan.somyk.AWS.test.dto.SummaryStatsByDate;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StatsByDateRepository extends MongoRepository<StatsByDate, String> {
    List<StatsByDate> findByDateBetween(String startDate, String endDate);
    Optional<StatsByDate> findByDate(String date);
    @Aggregation(pipeline = {
            "{$group: {" +
                    "_id: '$id'," +
                    "orderedProductSales:{$sum:'$salesByDate.orderedProductSales.amount'}," +
                    " orderedProductSalesB2B: {$sum: '$salesByDate.orderedProductSalesB2B.amount'}," +
                    "unitsOrdered :{$sum: '$salesByDate.unitsOrdered'}," +
                    "unitsOrderedB2B :{$sum: '$salesByDate.unitsOrderedB2B'}," +
                    "totalOrderItems :{$sum: '$salesByDate.totalOrderItems'}," +
                    "totalOrderItemsB2B :{$sum: '$salesByDate.totalOrderItemsB2B'}," +
                    "averageSalesPerOrderItem :{$sum: '$salesByDate.averageSalesPerOrderItem.amount'}," +
                    "averageSalesPerOrderItemB2B :{$sum: '$salesByDate.averageSalesPerOrderItemB2B.amount'}," +
                    "averageUnitsPerOrderItem :{$sum: '$salesByDate.averageUnitsPerOrderItem'}," +
                    "averageUnitsPerOrderItemB2B :{$sum: '$salesByDate.averageUnitsPerOrderItemB2B'}," +
                    "averageSellingPrice :{$sum: '$salesByDate.averageSellingPrice.amount'}," +
                    "averageSellingPriceB2B :{$sum: '$salesByDate.averageSellingPriceB2B.amount'}," +
                    "unitsRefunded :{$sum: '$salesByDate.unitsRefunded'}," +
                    "refundRate :{$sum: '$salesByDate.refundRate'}," +
                    "claimsGranted :{$sum: '$salesByDate.claimsGranted'}," +
                    "claimsAmount :{$sum: '$salesByDate.claimsAmount.amount'}," +
                    "shippedProductSales :{$sum: '$salesByDate.shippedProductSales.amount'}," +
                    "unitsShipped :{$sum: '$salesByDate.unitsShipped'}," +
                    "ordersShipped :{$sum: '$salesByDate.ordersShipped'}," +
                    "browserPageVievs: {$sum: '$trafficByDate.browserPageVievs'}," +
                    "browserPageVievsB2B: {$sum: '$trafficByDate.browserPageVievsB2B'}," +
                    "mobileAppPageViews: {$sum: '$trafficByDate.MobileAppPageViews'}," +
                    "mobileAppPageViewsB2B: {$sum: '$trafficByDate.MobileAppPageViewsB2B'}," +
                    "pageViews: {$sum: '$trafficByDate.pageViews'}," +
                    "pageViewsB2B: {$sum: '$trafficByDate.pageViewsB2B'}," +
                    "browserSessions: {$sum: '$trafficByDate.browserSessions'}," +
                    "browserSessionsB2B: {$sum: '$trafficByDate.browserSessionsB2B'}," +
                    "mobileAppSessions: {$sum: '$trafficByDate.mobileAppSessions'}," +
                    "mobileAppSessionsB2B: {$sum: '$trafficByDate.mobileAppSessionsB2B'}," +
                    "sessions: {$sum: '$trafficByDate.sessions'}," +
                    "sessionsB2B: {$sum: '$trafficByDate.sessionsB2B'}," +
                    "buyBoxPercentage: {$sum: '$trafficByDate.buyBoxPercentage'}," +
                    "buyBoxPercentageB2B: {$sum: '$trafficByDate.buyBoxPercentageB2B'}," +
                    "orderItemSessionPercentage: {$sum: '$trafficByDate.orderItemSessionPercentage'}," +
                    "orderItemSessionPercentageB2B: {$sum: '$trafficByDate.orderItemSessionPercentageB2B'}," +
                    "unitSessionPercentage: {$sum: '$trafficByDate.unitSessionPercentage'}," +
                    "unitSessionPercentageB2B: {$sum: '$trafficByDate.unitSessionPercentageB2B'}," +
                    "averageOfferCount: {$sum: '$trafficByDate.averageOfferCount'}," +
                    "averageParentItems: {$sum: '$trafficByDate.averageParentItems'}," +
                    "feedbackReceived: {$sum: '$trafficByDate.feedbackReceived'}," +
                    "negativeFeedbackReceived: {$sum: '$trafficByDate.negativeFeedbackReceived'}," +
                    "receivedNegativeFeedbackRate: {$sum: '$trafficByDate.receivedNegativeFeedbackRate'}," +
                    "}}"
    })
    SummaryStatsByDate sumByDate();

}
