package board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import board.entity.BoardEntity;

public interface JpaBoardRepository extends CrudRepository<BoardEntity, Integer>{
	// 기본 제공 메서드 외에 커스텀 쿼리를 추가하는 방법
	
	//방법1. 쿼리 메서드 사용
	List<BoardEntity> findByTitle(String title);
	List<BoardEntity> findByTitleLike(String title);
	List<BoardEntity> findByTitleContaining(String title);
	
	//방법2. @Query -JPGL을 이용해서 쿼리를 직접 작성
	@Query("SELECT board FROM BoardEntity board WHERE title like '%'||:title||'%' ")
	List<BoardEntity> fomdBoardDetailByTitle(@Param("title") String title);
}
