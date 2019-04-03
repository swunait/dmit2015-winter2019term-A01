package chinook.service;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@Stateless
public class ChinookService {

	@RolesAllowed({"VIEW_CUSTOMER_PAGES","VIEW_EMPLOYEE_PAGES"})
	public String getEjbMessage() {
		return "Hello World from EJB";
	}

}
