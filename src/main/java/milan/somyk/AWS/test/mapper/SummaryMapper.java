package milan.somyk.AWS.test.mapper;

import milan.somyk.AWS.test.dto.*;
import org.springframework.stereotype.Component;

@Component
public class SummaryMapper {
    public SummaryByAsinDto toAsinDto(SummaryStatsByAsin summary){
        return new SummaryByAsinDto(new SalesByAsin<>(summary.getUnitsOrdered(),summary.getUnitsOrderedB2B(),summary.getOrderedProductSales(),summary.getOrderedProductSalesB2B(),summary.getTotalOrderItems(),summary.getTotalOrderItemsB2B()),
                new TrafficByAsin(summary.getBrowserSessions(),summary.getBrowserSessionsB2B(),summary.getMobileAppSessions(),summary.getMobileAppSessionsB2B(),
                        summary.getSessions(),summary.getSessionsB2B(),summary.getBrowserSessionPercentage(),summary.getBrowserSessionPercentageB2B(),summary.getMobileAppSessionPercentage(),
                        summary.getMobileAppSessionPercentageB2B(),summary.getSessionPercentage(),summary.getSessionPercentageB2B(),summary.getBrowserPageViews(),summary.getBrowserPageViewsB2B(),summary.getMobileAppPageViews(),summary.getMobileAppPageViewsB2B(),summary.getPageViews(),summary.getPageViewsB2B(),
                        summary.getBrowserPageViewsPercentage(),summary.getBrowserPageViewsPercentageB2B(),summary.getMobileAppPageViewsPercentage(),summary.getMobileAppPageViewsPercentageB2B(),summary.getPageViewsPercentage(),summary.getPageViewsPercentageB2B(),summary.getBuyBoxPercentage(),summary.getBuyBoxPercentageB2B(),
                        summary.getUnitSessionPercentage(),summary.getUnitSessionPercentageB2B()));
    }
    public SummaryByDateDto toDateDto(SummaryStatsByDate summary){
        return new SummaryByDateDto(new SalesByDate<>(summary.getOrderedProductSales(),summary.getOrderedProductSalesB2B(),summary.getUnitsOrdered(),summary.getUnitsOrderedB2B(),summary.getTotalOrderItems(),summary.getTotalOrderItemsB2B(),
                summary.getAverageSalesPerOrderItem(),summary.getAverageSalesPerOrderItemB2B(),summary.getAverageSalesPerOrderItem(),summary.getAverageSalesPerOrderItemB2B(),summary.getAverageSellingPrice(),
                summary.getAverageSellingPriceB2B(),summary.getUnitsRefunded(),summary.getRefundRate(),summary.getClaimsGranted(),summary.getClaimsAmount(),summary.getShippedProductSales(),summary.getUnitsShipped(),summary.getOrdersShipped()),new TrafficByDate(summary.getBrowserPageViews(),
                summary.getBrowserPageViewsB2B(),summary.getMobileAppPageViews(),summary.getMobileAppPageViewsB2B(),summary.getPageViews(),summary.getPageViewsB2B(),summary.getBrowserSessions(),summary.getBrowserSessionsB2B(),summary.getMobileAppSessions(),summary.getMobileAppSessionsB2B(),summary.getSessions(),summary.getSessionsB2B(),summary.getBuyBoxPercentage(),summary.getBuyBoxPercentageB2B(),
                summary.getOrderItemSessionPercentage(),summary.getOrderItemSessionPercentageB2B(),summary.getUnitSessionPercentage(),summary.getUnitSessionPercentageB2B(),summary.getAverageOfferCount(),summary.getAverageParentItems(),summary.getFeedbackReceived(),summary.getNegativeFeedbackReceived(),summary.getReceivedNegativeFeedbackRate()));
    }
}
