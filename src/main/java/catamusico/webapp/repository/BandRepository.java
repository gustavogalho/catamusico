package catamusico.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import catamusico.webapp.domain.Band;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> 
{
    
}
