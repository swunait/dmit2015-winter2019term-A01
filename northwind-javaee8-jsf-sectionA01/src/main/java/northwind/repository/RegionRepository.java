package northwind.repository;

import northwind.entity.Region;

public class RegionRepository extends AbstractNorthwindJpaRepository<Region> {

	public RegionRepository() {
		super(Region.class);
	}

	
}
