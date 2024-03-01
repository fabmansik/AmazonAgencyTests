package milan.somyk.AWS.test.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import milan.somyk.AWS.test.dto.Sale;
import milan.somyk.AWS.test.dto.SalesByAsin;
import milan.somyk.AWS.test.dto.TrafficByAsin;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "salesAndTrafficByAsin")
@NoArgsConstructor
@Data
public class StatsByAsin {
    @Id
    String parentAsin;
    SalesByAsin<Sale> salesByAsin;
    TrafficByAsin trafficByAsin;
}
