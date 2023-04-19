package board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.entity.BoardEntity;
import board.entity.ChattingEntity;
import board.entity.MessageEntity;
import board.repository.JpaChattingRepository;
import board.repository.JpaMessageRepository;

@Service
public class JpaServiceImpl implements JpaService {
	@Autowired
	private JpaMessageRepository jpaMessageRepository;
	@Autowired
	private JpaChattingRepository jpaChattingRepository;
	@Override
	public List<MessageEntity> getMessage(int roomIdx) {
		return (List<MessageEntity>) jpaMessageRepository.findByRoomIdx(roomIdx);
	}
	
	@Override
	public void insertData(MessageEntity messageEtity) {
		jpaMessageRepository.save(messageEtity);
	}

	@Override
	public ChattingEntity getchatting(int roomIdx) {
		Optional<ChattingEntity> optional = jpaChattingRepository.findById(roomIdx);
		ChattingEntity chatting = optional.get();
		return chatting;
	}




}
