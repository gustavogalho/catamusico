package catamusico.webapp.controller;

import java.util.List;

import javax.naming.directory.SearchControls;
import javax.websocket.server.PathParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import catamusico.webapp.bean.RegisterBean;
import catamusico.webapp.bean.SearchBean;
import catamusico.webapp.domain.Band;
import catamusico.webapp.domain.CustomUserDetails;
import catamusico.webapp.domain.File;
import catamusico.webapp.domain.Login;
import catamusico.webapp.domain.Musician;
import catamusico.webapp.service.BandService;
import catamusico.webapp.service.FileService;
import catamusico.webapp.service.LoginService;
import catamusico.webapp.service.MusicianService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private MusicianService musicianService;

	@Autowired
	private BandService bandService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private FileService fileService;

	@GetMapping("/search")
	public ModelAndView search(Model model, @AuthenticationPrincipal CustomUserDetails activeUser) {
		ModelAndView mav = new ModelAndView("/search");
		model.addAttribute("isMusician", loginService.isMusician(activeUser.getUsername()));
		mav.addObject("search", new SearchBean());
		return mav;
	}

	@PostMapping("/search")
	public ModelAndView postSearch(SearchBean searchBean) {
		System.out.println("Search Bean " + searchBean.toString());
		List<Musician> musicians = musicianService.queryMusician(searchBean);
		ModelAndView mav = new ModelAndView("/search");
		mav.addObject("search", searchBean);
		mav.addObject("musicians", musicians);
		return mav;
	}

	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("/register");
		mav.addObject("user", new RegisterBean());
		return mav;
	}

	@PostMapping("/save")
	public String saveUser(RegisterBean registerBean) {
		List<File> fileSavedList = new ArrayList<>();
		if (registerBean.getFiles() != null && registerBean.getFiles().length > 0) {

			try {
				for (MultipartFile file : registerBean.getFiles()) {
					if (!file.isEmpty()) {
						File fileToSave = new File();
						fileToSave.setContent(file.getBytes());
						fileToSave.setContentType(file.getContentType());
						fileToSave.setFilename(file.getOriginalFilename());
						fileSavedList.add(fileService.saveFile(fileToSave));
					}
				}
			} catch (IOException e) {
				System.err.println("Error " + e.getLocalizedMessage());
			}
		}
		Login login = loginService.save(new Login(registerBean));
		if (!registerBean.getInstrument().isEmpty()) {
			musicianService.createMusician(new Musician(registerBean, login, fileSavedList));
		} else {
			bandService.createBand(new Band(registerBean, login));
		}
		return "redirect:/home";
	}

	@GetMapping("/file/content/{id}")
	public ResponseEntity<ByteArrayResource> getFileContent(@PathVariable("id") Long fileId) {
		File file = fileService.getById(fileId);
		ByteArrayResource resource = new ByteArrayResource(file.getContent());

		return ResponseEntity.ok().contentLength(file.getContent().length)
				.contentType(MediaType.parseMediaType(file.getContentType())).body(resource);
	}

	@GetMapping("/login")
	public ModelAndView getLogin() {
		return new ModelAndView("/auth/login");
	}

	@GetMapping("/")
	public String redirect() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public ModelAndView greetings(Model model, @AuthenticationPrincipal CustomUserDetails activeUser) {
		model.addAttribute("musicians", musicianService.getTop6Latest());
		model.addAttribute("isMusician", loginService.isMusician(activeUser.getUsername()));
		return new ModelAndView("/index");
	}

	@GetMapping("/favorites")
	public ModelAndView favorites(Model model, @AuthenticationPrincipal CustomUserDetails activeUser) {
		model.addAttribute("favorites", bandService.getOne(1001L).getFavorites());
		model.addAttribute("isMusician", loginService.isMusician(activeUser.getUsername()));
		return new ModelAndView("/favorites");
	}

	@GetMapping("/notifications")
	public ModelAndView notifications(Model model, @AuthenticationPrincipal CustomUserDetails activeUser) {
		model.addAttribute("isMusician", loginService.isMusician(activeUser.getUsername()));
		return new ModelAndView("/notifications");
	}

	@PostMapping("/addFavorites/{musicianId}")
	public String addFavorites(@PathVariable("musicianId") Long musicianId) {
		System.out.println(musicianId);
		bandService.addMusician(1001L, musicianId);
		return "redirect:/favorites";
	}

	@GetMapping("/profile")
	public ModelAndView editUser(Model model, @AuthenticationPrincipal CustomUserDetails activeUser) {
		boolean isMusician = loginService.isMusician(activeUser.getUsername());
		model.addAttribute("isMusician", isMusician);
		Login login = loginService.findByEmail(activeUser.getUsername());

		if (isMusician) {
			Musician musician = musicianService.findByLogin(login.getId());
			model.addAttribute("musician", musician);

		} else {
			Band band = bandService.findByLoginId(login.getId());
			model.addAttribute("band", band);
		}

		return new ModelAndView("/profile");
	}

	@GetMapping("/viewProfile/{id}")
	public ModelAndView viewProfile(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal CustomUserDetails activeUser){
		boolean isMusician = loginService.isMusician(activeUser.getUsername());
		Musician musician = musicianService.getOne(id);

		model.addAttribute("isMusician", isMusician);
		model.addAttribute("musician", musician);
		model.addAttribute("view", true);
		return new ModelAndView("/profile");
	}
}
