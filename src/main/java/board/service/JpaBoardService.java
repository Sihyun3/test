package board.service;

import java.util.List;

import board.entity.BoardEntity;

public interface JpaBoardService {

	
	public List<BoardEntity> selectBoardList() throws Exception;
	public void saveBoard(BoardEntity boardEntity) throws Exception;
	public BoardEntity selectBoardDetail(int boardIdx) throws Exception;
//	void updateBoard(BoardEntity boardEntity) throws Exception;
	void deleteBoard(int boardIdx) throws Exception;
	
	public List<BoardEntity> select1(String title) throws Exception;
	public List<BoardEntity> select2(String title) throws Exception;
	public List<BoardEntity> select3(String title) throws Exception;
	public List<BoardEntity> select4(String title) throws Exception;
}
