package board.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import board.entity.ChattingEntity;

public interface JpaChattingRepository extends CrudRepository<ChattingEntity, Integer> {
	
	List<ChattingEntity> findByUserId1(String userId);
	List<ChattingEntity> findByUserId2(String userId);
}
