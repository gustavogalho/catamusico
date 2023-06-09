package catamusico.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import catamusico.webapp.domain.Band;
import catamusico.webapp.domain.Musician;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> 
{
    Band findByLoginId(Long LoginId);
}
