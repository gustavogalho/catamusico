package catamusico.webapp.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import catamusico.webapp.bean.BandBean;
import catamusico.webapp.bean.MusicianBean;
import catamusico.webapp.bean.RegisterBean;
import catamusico.webapp.bean.SearchBean;
import catamusico.webapp.domain.Band;
import catamusico.webapp.domain.CustomUserDetails;
import catamusico.webapp.domain.File;
import catamusico.webapp.domain.Login;
import catamusico.webapp.domain.Musician;
import catamusico.webapp.domain.Notification;
import catamusico.webapp.service.BandService;
import catamusico.webapp.service.FileService;
import catamusico.webapp.service.LoginService;
import catamusico.webapp.service.MusicianService;
import catamusico.webapp.service.NotificationService;

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

	@Autowired
	private NotificationService notificationService;

	@GetMapping("/search")
	public ModelAndView search(Model model, @AuthenticationPrincipal CustomUserDetails activeUser) {
		ModelAndView mav = new ModelAndView("/search");
		model = userIdentification(model, activeUser);
		mav.addObject("search", new SearchBean());
		return mav;
	}

	@PostMapping("/search")
	public ModelAndView postSearch(Model model, @AuthenticationPrincipal CustomUserDetails activeUser,
			SearchBean searchBean) {
		model = userIdentification(model, activeUser);
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
			bandService.createBand(new Band(registerBean, login, fileSavedList));
		}
		return "redirect:/home";
	}

	@PostMapping("/updateBand")
	public String updateBand(BandBean band) {
		List<File> fileSavedList = new ArrayList<>();
		if (band.getFiles() != null && band.getFiles().length > 0) {

			try {
				for (MultipartFile file : band.getFiles()) {
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
		bandService.update(band, fileSavedList);
		return "redirect:/profile";
	}

	@PostMapping("/updateMusician")
	public String updateMusician(MusicianBean musician) {
		List<File> fileSavedList = new ArrayList<>();
		if (musician.getFiles() != null && musician.getFiles().length > 0) {

			try {
				for (MultipartFile file : musician.getFiles()) {
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
		musicianService.update(musician, fileSavedList);
		return "redirect:/profile";
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
		model = userIdentification(model, activeUser);
		model.addAttribute("musicians", musicianService.getTop6Latest());
		model.addAttribute("bands", bandService.findAll());

		return new ModelAndView("/index");
	}

	@GetMapping("/favorites")
	public ModelAndView favorites(Model model, @AuthenticationPrincipal CustomUserDetails activeUser) {
		model = userIdentification(model, activeUser);
		Login login = loginService.findByEmail(activeUser.getUsername());

		Band band = bandService.findByLoginId(login.getId());
		model.addAttribute("band", band);

		model.addAttribute("favorites", band.getFavorites());
		return new ModelAndView("/favorites");
	}

	@GetMapping("/notifications")
	public ModelAndView notifications(Model model, @AuthenticationPrincipal CustomUserDetails activeUser) {
		model = userIdentification(model, activeUser);
		Login login = loginService.findByEmail(activeUser.getUsername());
		Musician musician = musicianService.findByLogin(login.getId());
		List<Notification> notifications = notificationService.findByMusicianId(musician.getId());

		System.out.println(notifications);

		model.addAttribute("notifications", notifications);
		model.addAttribute("musician", musician);
		return new ModelAndView("/notifications");
	}

	@GetMapping("/notifications/read/{notificationId}")
	public String readNotification(@PathVariable("notificationId") Long notificationId) {
		Notification notification = notificationService.setRead(true, notificationId);
		Long bandId = notification.getBand().getId();

		return "redirect:/viewProfile/" + bandId;
	}

	@PostMapping("/addFavorites/{musicianId}")
	public String addFavorites(@PathVariable("musicianId") Long musicianId,
			@AuthenticationPrincipal CustomUserDetails activeUser) {
		Login login = loginService.findByEmail(activeUser.getUsername());
		Musician musician = musicianService.getOne(musicianId);
		Band band = bandService.findByLoginId(login.getId());
		notificationService.save(band, musician);
		bandService.addMusician(band.getId(), musicianId);
		return "redirect:/favorites";
	}

	@GetMapping("/profile")
	public ModelAndView editUser(Model model, @AuthenticationPrincipal CustomUserDetails activeUser) {
		boolean isMusician = loginService.isMusician(activeUser.getUsername());
		model.addAttribute("isMusician", isMusician);
		model.addAttribute("view", false);
		

		Login login = loginService.findByEmail(activeUser.getUsername());

		if (isMusician) {
			Musician musician = musicianService.findByLogin(login.getId());
			model.addAttribute("musician", musician);
			model.addAttribute("musicianBean", new MusicianBean(musician));

		} else {
			Band band = bandService.findByLoginId(login.getId());
			model.addAttribute("band", band);
			model.addAttribute("bandBean", new BandBean(band));
		}

		return new ModelAndView("/profile");
	}

	@GetMapping("/viewProfile/{id}")
	public ModelAndView viewProfile(@PathVariable("id") Long id, Model model,
			@AuthenticationPrincipal CustomUserDetails activeUser) {
		boolean isMusician = loginService.isMusician(activeUser.getUsername());
		model = userIdentification(model, activeUser);
		if (!isMusician) {
			Musician musician = musicianService.getOne(id);
			model.addAttribute("viewMusician", musician);
		} else {
			Band band = bandService.getOne(id);
			model.addAttribute("viewBand", band);
		}

		model.addAttribute("isMusician", isMusician);
		model.addAttribute("view", true);

		return new ModelAndView("/profile");
	}

	public Model userIdentification(Model model, @AuthenticationPrincipal CustomUserDetails activeUser) {
		boolean isMusician = loginService.isMusician(activeUser.getUsername());
		model.addAttribute("isMusician", isMusician);
		model.addAttribute("view", false);
		model.addAttribute("profile", activeUser);

		Login login = loginService.findByEmail(activeUser.getUsername());

		if (isMusician) {
			Musician musician = musicianService.findByLogin(login.getId());
			boolean newNotification = notificationService.newNotification(musician.getId());
			model.addAttribute("musician", musician);
			model.addAttribute("newNotification", newNotification);

		} else {
			Band band = bandService.findByLoginId(login.getId());
			model.addAttribute("band", band);
		}
		return model;
	}
}
