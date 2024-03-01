package milan.somyk.AWS.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesByAsin<T> {
    int unitsOrdered;
    int unitsOrderedB2B;
    T orderedProductSales;
    T orderedProductSalesB2B;
    int totalOrderItems;
    int totalOrderItemsB2B;
}
