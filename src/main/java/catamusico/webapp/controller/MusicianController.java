package catamusico.webapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import catamusico.webapp.domain.Musician;
import catamusico.webapp.service.MusicianService;

@RestController
@RequestMapping("/api/v1/musicians")
public class MusicianController {
    @Autowired
    private MusicianService musicianService;

    @GetMapping("/")
    public List<Musician> getAll(Model model)
    {
        return musicianService.findAllMusicians();
    }

    @PostMapping("/")
    public Musician createMusician(@RequestBody Musician newMusician)
    {
        System.out.println(newMusician);
		return musicianService.createMusician(newMusician);
    }
}
