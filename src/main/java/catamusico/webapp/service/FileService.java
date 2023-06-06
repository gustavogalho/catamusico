package catamusico.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import catamusico.webapp.domain.File;
import catamusico.webapp.repository.FileRepository;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	public File saveFile(File file) {
		return fileRepository.save(file);
	}

	public File getById(Long id) {
		return fileRepository.getOne(id);
	}

	public List<File> findAll() {
		return fileRepository.findAll();
	}
}
