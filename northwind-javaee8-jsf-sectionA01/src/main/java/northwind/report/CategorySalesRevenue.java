package northwind.report;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategorySalesRevenue {
	
	private String categoryName;
	private Double categorySalesTotal;

}
