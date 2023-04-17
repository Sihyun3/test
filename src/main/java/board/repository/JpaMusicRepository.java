package board.repository;

import org.springframework.data.repository.CrudRepository;

import board.entity.MusicEntity;

public interface JpaMusicRepository extends CrudRepository<MusicEntity, Integer>{

}
