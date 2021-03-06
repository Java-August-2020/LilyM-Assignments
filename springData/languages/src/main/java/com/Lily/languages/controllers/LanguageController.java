package com.Lily.languages.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Lily.languages.models.Language;
import com.Lily.languages.services.LanguageService;


	@Controller
	public class LanguageController {
		
		private LanguageService langService;
		
		public LanguageController(LanguageService service) {
			this.langService = service;
		}
		//from back-end to front-end thru model (from database -ALL)
		@RequestMapping("/")
		public String index(Model model) {
			model.addAttribute("languages", langService.allLanguages());
			return "index.jsp";
		}
		
		//show findLanguage by id.   
		@RequestMapping("/{id}")
		public String Show(@PathVariable("id") Long id, Model model) {
			model.addAttribute("language", langService.findLanguage(id));
			return "show.jsp";
		}

		@RequestMapping("/new")
		public String newLanguage(@ModelAttribute("language") Language language) {
 		return "new.jsp";
		}
 		//from front-end to back-end thru @RequestParam -parms entered by user
		@RequestMapping(value="/", method=RequestMethod.POST)
		public String create(@Valid @ModelAttribute("language") Language lang, BindingResult result, @RequestParam Map<String,String> body) {
			if(result.hasErrors())
				return "new.jsp";
			langService.createLanguage(lang);
			return "redirect:/";
 	}
		//updateLanguage -by id
		@RequestMapping("/{id}/edit")
		public String Edit(@PathVariable("id") Long id, Model model) {
			model.addAttribute("language", langService.findLanguage(id));
			return "edit.jsp";
		}	
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		public String update(@Valid @ModelAttribute("language") Language lang, BindingResult result, @PathVariable("id") Long id ) {
			System.out.println("Before id"+ id);
			if(result.hasErrors()) {
				return "edit.jsp";
			}else {
				System.out.println("id"+ id);
			langService.updateLanguage(lang, id);
			return "redirect:/";
			}
	}
		//deleteLanguage -by id
		@RequestMapping(value="/{id}/remove")
  		public String delete(@PathVariable("id") Long id) {
			langService.deleteLanguage(id);
			return "redirect:/";
		}
		
	}

