package catamusico.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import catamusico.webapp.service.MusicianService;

@Controller
public class HomeController {

	@Autowired
	private MusicianService musicianService;

	@GetMapping("/register")
	public ModelAndView register() {
		return new ModelAndView("/register");
	}

	@GetMapping("/login")
	public ModelAndView getLogin() {
		return new ModelAndView("/auth/login");
	}

	@PostMapping("/login")
	public ModelAndView postLogin(Model model) {
		// loginServive.login();
		return greetings(model);
	}

	@GetMapping("/home")
	public ModelAndView greetings(Model model) {
		model.addAttribute("musicians", musicianService.getTop6Latest());
		return new ModelAndView("/index");
	}

	@GetMapping("/search")
	public ModelAndView search() {
		return new ModelAndView("/search");
	}

	@GetMapping("/favorites")
	public ModelAndView favorites() {
		return new ModelAndView("/favorites");
	}

	@GetMapping("/profile")
	public ModelAndView profile() {
		return new ModelAndView("/profile");
	}

	@GetMapping("/notifications")
	public ModelAndView notifications() {
		return new ModelAndView("/notifications");
	}

}
