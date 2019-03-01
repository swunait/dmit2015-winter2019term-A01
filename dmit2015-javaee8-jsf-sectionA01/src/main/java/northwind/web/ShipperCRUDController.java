package northwind.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;
import northwind.entity.Shipper;
import northwind.service.NorthwindService;

@Named
@ViewScoped
public class ShipperCRUDController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_OUTCOME = "list?faces-redirect=true";

	@Inject
	private NorthwindService northwindService;
	
	private List<Shipper> shippers;		// +getter	

	@PostConstruct
	public void init() {
		shippers = northwindService.findAllShippers();
		shipperDetail = new Shipper();
	}
	
	@Produces
	@Named
	public List<Shipper> getShippers() {
		return shippers;
	}
	
	@Getter @Setter private boolean editMode = false;
	@Getter @Setter private String editId;
	
	@Produces
	@Named
	private Shipper shipperDetail;
	
	public String create() {
		String outcome = null;
		try {						
			northwindService.addShipper(shipperDetail);		
			init();
			Messages.addGlobalInfo("Add successful");
			outcome = DEFAULT_OUTCOME;
		} catch (Exception e) {
			Messages.addGlobalError("Add unsuccessful");
			Messages.addGlobalError("{0}", e.getMessage());	
		}
		return outcome;
	}
	
	public String update() {
		String outcome = null;
		try {
			northwindService.updateShipper(shipperDetail);
			Messages.addFlashGlobalInfo("Update successful");			
			outcome = DEFAULT_OUTCOME;
		} catch (Exception e) {
			Messages.addGlobalError("Update unsuccessful");	
			Messages.addGlobalError("{0}", e.getMessage());	
		}
		return outcome;
	}

	public String delete() {
		String nextUrl = null;
		try {
			northwindService.deleteShipper(shipperDetail);
			shipperDetail = null;
			editId = null;
			Messages.addFlashGlobalInfo("Delete successful");
			
			nextUrl = DEFAULT_OUTCOME;
		} catch (Exception e) {
			Messages.addGlobalError("Delete unsuccessful");			
			Messages.addGlobalError("{0}", e.getMessage());	
		}
		return nextUrl;
	}
	
	public String cancel() {
		shipperDetail = null;
		editMode = false;
		return DEFAULT_OUTCOME;
	}

	public void retreiveDetailById() {
		if (!Faces.isPostback() && !Faces.isValidationFailed() ) {
			if (editId != null) {
				try {
					shipperDetail = northwindService.findOneShipper(editId);
					if (shipperDetail != null) {
						editMode = true;
					} else {
						Messages.addFlashGlobalError("{0} is not a valid id value", editId);
						Faces.navigate("viewAll?faces-redirect=true");						
					}
				} catch (Exception e) {
					Messages.addGlobalError("Query unsucessful");
					Messages.addGlobalError("{0}", e.getMessage());	
				}	
			} else {
				Faces.navigate(DEFAULT_OUTCOME);
			}
		} 
	}
	
}