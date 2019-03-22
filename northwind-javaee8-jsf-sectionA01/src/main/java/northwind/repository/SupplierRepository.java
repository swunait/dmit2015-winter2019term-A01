package northwind.repository;

import northwind.entity.Supplier;

public class SupplierRepository extends AbstractNorthwindJpaRepository<Supplier> {

	public SupplierRepository() {
		super(Supplier.class);
	}

	
}
