package milan.somyk.AWS.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SummaryByAsinDto {
    SalesByAsin<Double> salesByAsin;
    TrafficByAsin trafficByAsin;

    public SummaryByAsinDto() {

    }
}
