package catamusico.webapp.service;

import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import catamusico.webapp.domain.Musician;
import catamusico.webapp.repository.MusicianRepository;


@Service
public class MusicianService {
    @Autowired
    private MusicianRepository musicianRepository;

    public List<Musician> findAllMusicians() {
        return musicianRepository.findAll();
    }

    public Musician createMusician(Musician musician)
    {
        return musicianRepository.save(musician);
    }

}
