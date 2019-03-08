package northwind.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;
import northwind.entity.Shipper;
import northwind.service.NorthwindService;

@Named
@ViewScoped
public class ShipperCRUDController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private NorthwindService northwindService;
	
	private List<Shipper> shippers;					// +getter
	
	/** The current Shipper to create, edit, update, or delete */
	@Getter @Setter private Shipper currentShipper = new Shipper();	// +getter +setter	
	
	@PostConstruct
	void init() {
		try {
			shippers = northwindService.findAllShipper();
		} catch(Exception e) {
			Messages.addGlobalError("Error retreiving shippers");
		}
	}
	
	public String create() {
		String outcome = null;
		try {
			northwindService.createShipper(currentShipper);
			Messages.addFlashGlobalInfo("Create was succesful");
			currentShipper = new Shipper();
			outcome = "shippers?faces-redirect=true";
		} catch(Exception e) {
			Messages.addGlobalError("Error creating shipper");
		}
		
		return outcome;
	}
	
	public void delete(Shipper existingShipper) {
		try {
			northwindService.deleteShipper(existingShipper);
			shippers.remove(existingShipper);
			Messages.addGlobalInfo("Delete was successful");
		} catch (Exception e) {
			Messages.addGlobalError("Delete was not successful");
		}
	}

	public List<Shipper> getShippers() {
		return shippers;
	}

	
}
