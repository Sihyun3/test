package board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.entity.BoardEntity;
import board.repository.JpaBoardRepository;

@Service
public class JpaBoardServiceImpl implements JpaBoardService {

	@Autowired
	JpaBoardRepository jpaBoardRepository;
	
	@Override
	public List<BoardEntity> selectBoardList() throws Exception {
		return (List<BoardEntity>)jpaBoardRepository.findAll();
		
	}

	@Override
	public void saveBoard(BoardEntity boardEntity) throws Exception {
		boardEntity.setCreatorId("admin");
		jpaBoardRepository.save(boardEntity);
	}

	@Override
	public BoardEntity selectBoardDetail(int boardIdx) throws Exception {
		Optional<BoardEntity> optional =  jpaBoardRepository.findById(boardIdx);
		if(optional.isPresent()) {
			BoardEntity board = optional.get();
			board.setHit_cnt(board.getHit_cnt() + 1);
			jpaBoardRepository.save(board);
			return board;
		}else {
			throw new NullPointerException();
		}
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		jpaBoardRepository.deleteById(boardIdx);
	}

	@Override
	public List<BoardEntity> select1(String title) throws Exception {

		return jpaBoardRepository.findByTitle(title);
	}

	@Override
	public List<BoardEntity> select2(String title) throws Exception {
		// TODO Auto-generated method stub
		return  jpaBoardRepository.findByTitleLike(title);
	}

	@Override
	public List<BoardEntity> select3(String title) throws Exception {
		// TODO Auto-generated method stub
		return  jpaBoardRepository.findByTitleContaining(title);
	}

	@Override
	public List<BoardEntity> select4(String title) throws Exception {
		// TODO Auto-generated method stub
		return  jpaBoardRepository.fomdBoardDetailByTitle(title);
	}

}
