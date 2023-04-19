package board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.controller.JpaMusicController;
import board.entity.MusicEntity;
import board.repository.JpaMusicRepository;

@Service
public class JpaMusicServiceImpl implements JpaMusicService {
	
	@Autowired
	private JpaMusicRepository jpaMusicRepository;
	
	@Override
	public void insertMusic(MusicEntity musicEntity) {
		jpaMusicRepository.save(musicEntity);

	}

}
