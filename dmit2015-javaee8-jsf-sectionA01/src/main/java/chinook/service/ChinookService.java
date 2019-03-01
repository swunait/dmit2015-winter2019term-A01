package chinook.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import chinook.entity.Artist;
import chinook.repository.ArtistRespository;

@Stateless
public class ChinookService {

	@Inject
	private ArtistRespository artistRespository;
	
	public void createArtist(Artist newArtist) {
		artistRespository.create(newArtist);
	}
	
	public void updateArtist(Artist existingArtist) {
		artistRespository.edit(existingArtist);
	}
	
	public void deleteArtist(Artist existingArtist) {
		artistRespository.remove(existingArtist);
	}
	
	public Artist findOneArtist(int artistId) {
		return artistRespository.find(artistId);
	}

	public List<Artist> findAllArtist() {
		return artistRespository.findAll();
	}
}
