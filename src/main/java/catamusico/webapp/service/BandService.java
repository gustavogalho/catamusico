package catamusico.webapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import catamusico.webapp.domain.Band;
import catamusico.webapp.repository.BandRepository;

@Service
public class BandService {
    private final BandRepository bandRepository;

    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    public List<Band> findAll() {
        return bandRepository.findAll();
    }

}
