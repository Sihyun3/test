package board.service;

import java.util.List;

import board.entity.ChattingEntity;
import board.entity.MessageEntity;

public interface JpaService {
	List<MessageEntity> getMessage(int roomIdx);
	void insertData(MessageEntity messageEtity);
	ChattingEntity getchatting(int roomIdx);

}
