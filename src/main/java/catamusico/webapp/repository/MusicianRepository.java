package catamusico.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import catamusico.webapp.domain.Musician;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long>
{

	List<Musician> findTop6ByOrderByIdDesc();

	List<Musician> findAllByState(String state);

	@Query("SELECT e FROM Musician e WHERE (:instrument = '' OR e.instrument = :instrument) " +
            "AND (:experienceLevel = '' OR e.experienceLevel = :experienceLevel) " +
            "AND (:state = '' OR e.state = :state) " +
            "AND (:city = '' OR e.city = :city)")
	List<Musician> findByQuery(@Param("instrument")String instrument, @Param("experienceLevel")String experienceLevel, @Param("state")String state, @Param("city")String city);

	Musician findByLoginId(Long loginId);
}
