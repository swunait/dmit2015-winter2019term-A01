package northwind.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.List;


/**
 * The persistent class for the Region database table.
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RegionID")
	private int regionID;

	@NotBlank(message="Description value is required.")
	@Size(max=50, message="Description value must be 50 or less characters")
	@Column(name="RegionDescription")
	private String regionDescription;

	//bi-directional many-to-one association to Territory
	@XmlTransient
	@OneToMany(mappedBy="region")
	private List<Territory> territories;

	public Region() {
	}

	public int getRegionID() {
		return this.regionID;
	}

	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}

	public String getRegionDescription() {
		return this.regionDescription;
	}

	public void setRegionDescription(String regionDescription) {
		this.regionDescription = regionDescription;
	}

	public List<Territory> getTerritories() {
		return this.territories;
	}

	public void setTerritories(List<Territory> territories) {
		this.territories = territories;
	}

	public Territory addTerritory(Territory territory) {
		getTerritories().add(territory);
		territory.setRegion(this);

		return territory;
	}

	public Territory removeTerritory(Territory territory) {
		getTerritories().remove(territory);
		territory.setRegion(null);

		return territory;
	}

}