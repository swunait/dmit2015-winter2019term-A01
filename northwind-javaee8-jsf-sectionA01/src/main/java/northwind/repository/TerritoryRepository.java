package northwind.repository;

import northwind.entity.Territory;

public class TerritoryRepository extends AbstractNorthwindJpaRepository<Territory> {

	public TerritoryRepository() {
		super(Territory.class);
	}

	
}
