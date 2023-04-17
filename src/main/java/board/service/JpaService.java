package board.service;

import java.util.List;

import board.entity.MessageEntity;

public interface JpaService {
	List<MessageEntity> getMessage(MessageEntity messageEntity);
}
