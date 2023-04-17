package board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.entity.MessageEntity;
import board.repository.JpaMessageRepository;

@Service
public class JpaServiceImpl implements JpaService {
	@Autowired
	private JpaMessageRepository JpaMessageRepository;
	
	@Override
	public List<MessageEntity> getMessage(MessageEntity messageEntity) {
		return (List<MessageEntity>) JpaMessageRepository.findAll();
	}
//	public List<BoardEntity> select1(String title) throws Exception {
//
//		return jpaBoardRepository.findByTitle(title);
//	}
}
