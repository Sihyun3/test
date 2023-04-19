package board.repository;

import org.springframework.data.repository.CrudRepository;

import board.entity.ChattingEntity;

public interface JpaChattingRepository extends CrudRepository<ChattingEntity, Integer> {

}
