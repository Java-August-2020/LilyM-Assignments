package com.Lily.lookify.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Lily.lookify.models.Song;
import com.Lily.lookify.services.SongService;

@Controller
public class SongController {
	private  SongService songService;
	public SongController(SongService service) {
		this.songService = service;
	}
	//only Welcome
	@RequestMapping("/")
	public String index() {
		return "/songs/index.jsp";
	}
	//from back-end to front-end thru model (from database -ALL)
	@RequestMapping("/songs")
	public String Songs(Model model) {
		model.addAttribute("songs", songService.allSongs());
		return "/songs/songs.jsp";
	}
	//show findSong by id.   
	@RequestMapping("/songs/{id}")
	public String Show(@PathVariable("id") Long id, Model model) {
		model.addAttribute("song", songService.findSong(id));
		return "/songs/show.jsp";
	}
	//Add new song
		@RequestMapping("/songs/new")
	public String New(@ModelAttribute("song") Song song) {
		return "/songs/new.jsp";
	}
	@RequestMapping(value="/songs", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("song") Song song, BindingResult result) {
		if(result.hasErrors())
			return "/songs/new.jsp";
		songService.createSong(song);
		return "redirect:/songs";
	}
	//delete
	@RequestMapping(value="/songs/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		songService.deleteSong(id);
		return "redirect:/songs";
	}
	//topTen	
	@RequestMapping("/songs/topTen")
	public String TopTen(Model model) {
		model.addAttribute("songs", songService.topTenByRating());
		return "/songs/topTen.jsp";
	}
	//Search
	@RequestMapping("/songs/search")
	public String search(@RequestParam("artist") String artist, Model model) {
		model.addAttribute("songs",	songService.songsByArtist(artist));
		model.addAttribute("artist", artist);
		return "/songs/search.jsp";
	}
}