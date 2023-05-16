package catamusico.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HomeController {

	@GetMapping("/")
	public ModelAndView greetings() {
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
