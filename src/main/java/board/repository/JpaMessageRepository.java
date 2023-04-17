package board.repository;

import org.springframework.data.repository.CrudRepository;

import board.entity.MessageEntity;

public interface JpaMessageRepository extends CrudRepository<MessageEntity, Integer> {

}
