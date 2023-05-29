package catamusico.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import catamusico.webapp.domain.Musician;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> 
{
    
}
