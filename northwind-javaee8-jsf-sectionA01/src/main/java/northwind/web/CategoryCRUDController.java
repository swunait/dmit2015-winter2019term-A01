package northwind.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import northwind.entity.Category;
import northwind.service.NorthwindService;

@Named
@ViewScoped
public class CategoryCRUDController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private NorthwindService northwindService;
	
	private List<Category> categorys;			// +getter
	
	@PostConstruct
	void init() {
		categorys = northwindService.findAllCategory();
	}

	@Produces
	@Named
	public List<Category> getCategorys() {
		return categorys;
	}
	
}