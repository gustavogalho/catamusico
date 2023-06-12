package catamusico.webapp.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import catamusico.webapp.bean.BandBean;
import catamusico.webapp.bean.MusicianBean;
import catamusico.webapp.domain.Band;
import catamusico.webapp.domain.File;
import catamusico.webapp.domain.Musician;
import catamusico.webapp.repository.BandRepository;

@Service
public class BandService {

    @Autowired
    private final BandRepository bandRepository;
    @Autowired
    private final MusicianService musicianService;

    public BandService(BandRepository bandRepository, MusicianService musicianService) {
        this.bandRepository = bandRepository;
        this.musicianService = musicianService;
    }

    public List<Band> findAll() {
        return bandRepository.findAll();
    }

    public Band createBand(Band band) {
        return bandRepository.save(band);
    }

    public Band getOne(Long id) {
        return bandRepository.getOne(id);
    }

    public void addMusician(Long bandId, Long musicianID) {
        Band band = bandRepository.getOne(bandId);
        Musician musician = musicianService.getOne(musicianID);

        List<Musician> listMusician = band.getFavorites();

        if (listMusician != null) {
            listMusician.add(musician);
        } else {
            listMusician = Arrays.asList(musician);
        }

        band.setFavorites(listMusician);
        bandRepository.save(band);
    }

    public Band findByLoginId(Long loginId) {
        return bandRepository.findByLoginId(loginId);
    }

    public Band update(BandBean band, List<File> filesavedlist) {
		Band bandToUpdate = getOne(band.getId());
		bandToUpdate.setCity(band.getCity());
		bandToUpdate.setState(band.getState().split("-")[1]);
		bandToUpdate.setContact(band.getContact());
		bandToUpdate.setExperiences(band.getExperiences());
		bandToUpdate.setMusicGenre(band.getMusicGenre());
		
		List<File> fileList = bandToUpdate.getMedia();

        fileList.addAll(filesavedlist);
        
		bandToUpdate.setMedia(fileList);

		return bandRepository.save(bandToUpdate);
	}
}
