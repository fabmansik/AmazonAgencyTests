package milan.somyk.AWS.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SummaryByDateDto {
    SalesByDate<Double> salesByDate;
    TrafficByDate trafficByDate;

}
