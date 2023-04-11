package ch.hevs.beanifyservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Artist;

@Local
public interface Beanify {

	Artist getArtist(String artistName);
	public List<Artist> getArtists();
	
}
