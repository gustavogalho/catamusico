package catamusico.webapp.controller;


import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import catamusico.webapp.domain.Musician;
import catamusico.webapp.repository.MusicianRepository;
import catamusico.webapp.service.MusicianService;

@RestController
@RequestMapping("/api/v1/musicians")
public class MusicianController {
    @Autowired
    private MusicianService musicianService;
    private MusicianRepository musicianRepository;

    @GetMapping("/")
    public List<Musician> getAll(Model model)
    {
        musicianService.findAllMusicians();
        return musicianService.findAllMusicians();
    }

    @PostMapping("/")
    public Musician createMusician(@RequestBody Musician newMusician)
    {
        System.out.println(newMusician);
        return musicianRepository.save(newMusician);
    }
}
