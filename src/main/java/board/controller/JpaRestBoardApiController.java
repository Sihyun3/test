package board.controller;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import board.entity.BoardEntity;
import board.service.JpaBoardService;

@RestController
@RequestMapping("/jpa")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JpaRestBoardApiController {
	
	@Autowired
	private JpaBoardService jpaService;
	
	@GetMapping("/api/board")
	public ResponseEntity<List<BoardEntity>> openBoardList() throws Exception {
		List<BoardEntity> list = jpaService.selectBoardList();
		if (list != null && list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping("/api/board/write")
	public ResponseEntity<String> insertBoard(
//			@Parameter(description="게시물 정보를 담고 있는 객체", required=true)
			@RequestBody BoardEntity BoardEntity) throws Exception {
		try {
			jpaService.saveBoard(BoardEntity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록오류");
		}
		return ResponseEntity.status(HttpStatus.OK).body("정상처리");
	}
	
	@GetMapping("/api/board/{boardIdx}")
	public ResponseEntity<BoardEntity> openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
		BoardEntity BoardEntity = jpaService.selectBoardDetail(boardIdx);
		if (BoardEntity == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(BoardEntity);
		}
	}
	
	@PutMapping("/api/board/{boardIdx}")
	public ResponseEntity<Object> updateBoard(@PathVariable("boardIdx") int boardIdx, @RequestBody BoardEntity boardEntity) throws Exception {
		try {
			boardEntity.setBoardIdx(boardIdx);
			jpaService.saveBoard(boardEntity);
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
		}
	}
	
	@DeleteMapping("/api/board/{boardIdx}")
	public ResponseEntity<Object> deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
		try {
			jpaService.deleteBoard(boardIdx);
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
		}
	}
	@GetMapping("/api/select/{type}/{title}")
	public ResponseEntity<List<BoardEntity>> selectTitle(@PathVariable("type") int type,@PathVariable("title") String title) throws Exception {
		List<BoardEntity> list;
		switch(type) {
		case 1:
			 list = jpaService.select1(title);
		case 2:
			list = jpaService.select2(title);
			break;
		case 3:
			list = jpaService.select3(title);
			break;
		case 4:
			list = jpaService.select4(title);
			break;
		default:
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		
		if (list != null && list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	

}
