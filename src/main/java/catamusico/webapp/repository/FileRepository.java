package catamusico.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import catamusico.webapp.domain.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
