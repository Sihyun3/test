package board.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import board.entity.MessageEntity;

public interface JpaMessageRepository extends CrudRepository<MessageEntity, Integer> {

	List<MessageEntity> findByRoomIdx(int roomIdx);
	

}
