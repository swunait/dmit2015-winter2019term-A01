package northwind.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.List;


/**
 * The persistent class for the Territories database table.
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="Territories")
@NamedQuery(name="Territory.findAll", query="SELECT t FROM Territory t")
public class Territory implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message="TerritoryID value is required")
	@Size(max=20, message="TerrritorID value must be 20 characters or less")
	@Id
	@Column(name="TerritoryID")
	private String territoryID;

	@NotBlank(message="Description value is required")
	@Size(max=50, message="Description value must be 20 characters or less")
	@Column(name="TerritoryDescription")
	private String territoryDescription;

	//bi-directional many-to-many association to Employee
	@XmlTransient
	@ManyToMany
	@JoinTable(
		name="EmployeeTerritories"
		, joinColumns={
			@JoinColumn(name="TerritoryID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="EmployeeID")
			}
		)
	private List<Employee> employees;

	//bi-directional many-to-one association to Region
	@XmlTransient
	@Valid
	@NotNull(message="A region is required.")
	@ManyToOne
	@JoinColumn(name="RegionID")
	private Region region;

	public Territory() {
	}

	public String getTerritoryID() {
		return this.territoryID;
	}

	public void setTerritoryID(String territoryID) {
		this.territoryID = territoryID;
	}

	public String getTerritoryDescription() {
		return this.territoryDescription;
	}

	public void setTerritoryDescription(String territoryDescription) {
		this.territoryDescription = territoryDescription;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}