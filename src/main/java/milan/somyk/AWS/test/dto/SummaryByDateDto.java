package milan.somyk.AWS.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SummaryByDateDto {
    SalesByDate<Double> salesByDate;
    TrafficByDate trafficByDate;

    public SummaryByDateDto() {

    }
}
