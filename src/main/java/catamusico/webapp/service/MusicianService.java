package catamusico.webapp.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import catamusico.webapp.bean.MusicianBean;
import catamusico.webapp.bean.SearchBean;
import catamusico.webapp.domain.File;
import catamusico.webapp.domain.Musician;
import catamusico.webapp.domain.Notification;
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

	public Musician getOne(Long id) {
		return musicianRepository.getOne(id);
	}

	public Musician createMusician(Musician musician) {
		return musicianRepository.save(musician);
	}

	public List<Musician> queryMusician(SearchBean searchBean) {
		if (!searchBean.getState().isEmpty()) {
			searchBean.setState(searchBean.getState().split("-")[1]);
		}
		System.out.println(searchBean.toString());
		System.out.println(musicianRepository.findByQuery(searchBean.getInstrument(), searchBean.getExperienceLevel(),
				searchBean.getState(), searchBean.getCity()));
		return musicianRepository.findByQuery(searchBean.getInstrument(), searchBean.getExperienceLevel(),
				searchBean.getState(), searchBean.getCity());
	}

	public Musician findByLogin(Long loginId) {
		return musicianRepository.findByLoginId(loginId);
	}

	public Musician update(MusicianBean musician, List<File> filesavedlist) {
		Musician musicianToUpdate = getOne(musician.getId());
		musicianToUpdate.setCity(musician.getCity());
		musicianToUpdate.setState(musician.getState().split("-")[1]);
		musicianToUpdate.setContact(musician.getContact());
		musicianToUpdate.setExperienceLevel(musician.getExperienceLevel());
		musicianToUpdate.setExperiences(musician.getExperiences());
		musicianToUpdate.setInstrument(musician.getInstrument());
		musicianToUpdate.setMusicGenre(musician.getMusicGenre());
		
		List<File> fileList = musicianToUpdate.getMedia();

        fileList.addAll(filesavedlist);
        
		musicianToUpdate.setMedia(fileList);

		return musicianRepository.save(musicianToUpdate);
	}
}
