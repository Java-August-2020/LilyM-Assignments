package com.Lily.lookify.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.Lily.lookify.models.Song;
import com.Lily.lookify.repositories.SongRepository;

@Service
public class SongService {

	private  SongRepository songRepo;
	public SongService(SongRepository repo) {
		this.songRepo = repo;
	}
	//get all
	public List<Song> allSongs() {
		return this.songRepo.findAll();
	}
	//Get one by id
	public Song findSong(Long id) {
		return this.songRepo.findById(id).orElse(null);
	}
	//create
	public Song createSong(Song song) {
		return this.songRepo.save(song);
	}
	//update
	public Song updateSong(Song updatedsong, Long id) {
		if(updatedsong.getId() == null) {
			updatedsong.setId(id);
		}
		return this.songRepo.save(updatedsong);
	}
	//delete
	public void deleteSong(Long id) {
		this.songRepo.deleteById(id);
	}
	//topTen -see repository for ordering
	public List<Song> topTenByRating() {
		return songRepo.findTop10ByOrderByRatingDesc();
	}
	//songsByArtist -see repository for ordering
	public List<Song> songsByArtist(String artist) {
		return songRepo.findByArtistContaining(artist);
	}
}
