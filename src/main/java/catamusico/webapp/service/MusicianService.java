package catamusico.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import catamusico.webapp.domain.Musician;
import catamusico.webapp.repository.MusicianRepository;

@Service
public class MusicianService {

	@Autowired
	private MusicianRepository musicianRepository;

	public List<Musician> findAllMusicians() {
		return musicianRepository.findAll();
	}

	public List<Musician> getTop6Latest() {
		return musicianRepository.findTop6ByOrderByIdDesc();
	}

	public Musician createMusician(Musician musician) {
		return musicianRepository.save(musician);
	}

}
