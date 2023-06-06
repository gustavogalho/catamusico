package catamusico.webapp.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import catamusico.webapp.bean.SearchBean;
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

	public List<Musician> queryMusician(SearchBean searchBean) {
		if (!searchBean.getState().isEmpty()) 
		{
			searchBean.setState(searchBean.getState().split("-")[1]);
			// System.out.println(musicianRepository.findAllByState(searchBean.getState()));
		}
		System.out.println(searchBean.toString());
		System.out.println(musicianRepository.findByQuery(searchBean.getInstrument(), searchBean.getExperienceLevel(), searchBean.getState(), searchBean.getCity()));
		return musicianRepository.findByQuery(searchBean.getInstrument(), searchBean.getExperienceLevel(), searchBean.getState(), searchBean.getCity());
	}

}
