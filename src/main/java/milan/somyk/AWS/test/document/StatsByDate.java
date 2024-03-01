package milan.somyk.AWS.test.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import milan.somyk.AWS.test.dto.Sale;
import milan.somyk.AWS.test.dto.SalesByDate;
import milan.somyk.AWS.test.dto.TrafficByDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "salesAndTrafficByDate")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class StatsByDate {
    @Id
    String date;
    SalesByDate<Sale> salesByDate;
    TrafficByDate trafficByDate;
}
