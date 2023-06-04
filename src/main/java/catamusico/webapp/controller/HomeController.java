package catamusico.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import catamusico.webapp.bean.RegisterBean;
import catamusico.webapp.domain.Band;
import catamusico.webapp.domain.Login;
import catamusico.webapp.domain.Musician;
import catamusico.webapp.service.BandService;
import catamusico.webapp.service.LoginService;
import catamusico.webapp.service.MusicianService;

@Controller
public class HomeController {

	@Autowired
	private MusicianService musicianService;

	@Autowired
	private BandService bandService;

	@Autowired
	private LoginService loginService;

	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("/register");
		mav.addObject("user", new RegisterBean());
		return mav;
	}

	@PostMapping("/save")
	public String saveUser(RegisterBean registerBean) {
		System.out.println("register bean " + registerBean.toString());
		Login login = loginService.save(new Login(registerBean));
		if (!registerBean.getInstrument().isEmpty()) {
			musicianService.createMusician(new Musician(registerBean, login));
		} else {
			bandService.createBand(new Band(registerBean, login));
		}
		return "redirect:/home";
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
