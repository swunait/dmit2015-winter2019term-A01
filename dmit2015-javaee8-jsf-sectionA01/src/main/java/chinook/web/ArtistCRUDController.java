package chinook.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import chinook.entity.Artist;
import chinook.service.ChinookService;

@Named
@ViewScoped
public class ArtistCRUDController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ChinookService chinookService;
	
	private List<Artist> artists;	// +getters
	
	@Produces
	@Named
	private Artist artistDetail;	// The current Artist to create, edit, update or delete
	
	@PostConstruct
	void init() {
		artistDetail = new Artist();
		try {
			artists = chinookService.findAllArtist();
		} catch(Exception e) {
			Messages.addGlobalError("Error retreiving artits");
			Messages.addGlobalError("Exception: " + e.getMessage());			
		}
	}
	
	public String create() {
		String outcome = null;
		
		try {
			chinookService.createArtist(artistDetail);
			init();
			Messages.addFlashGlobalInfo("Add successful");
			outcome = "listArtist?faces-redirect=true";
		} catch(Exception e) {
			Messages.addGlobalError("Add unsuccessful");
			Messages.addGlobalError("Exception: " + e.getMessage());			
		}
		
		return outcome;
	}
	
	public void delete(Artist existingArtist) {
		
		try {			
			chinookService.deleteArtist(existingArtist);
			artists.remove(existingArtist);
			Messages.addGlobalInfo("Delete successful");
		} catch(Exception e) {
			Messages.addGlobalError("Delete unsuccessful");
			Messages.addGlobalError("Exception: " + e.getMessage());			
		}
		
	}

	@Produces
	@Named
	public List<Artist> getArtists() {
		return artists;
	}	
	
}
