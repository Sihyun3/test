package board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import board.entity.MusicEntity;
import board.service.JpaMusicService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JpaMusicController {
	@Autowired
	private JpaMusicService jpaMusicService;

//	@GetMapping("/api/board")
//	public ResponseEntity<List<BoardEntity>> openBoardList() throws Exception {
//		List<BoardEntity> list = jpaService.selectBoardList();
//		if (list != null && list.size() > 0) {
//			return ResponseEntity.status(HttpStatus.OK).body(list);
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	}
	@PostMapping("/api/insertmusic")
//	   @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> insertmovie(
			@RequestPart(value = "files", required = false) MultipartFile[] files
//			@RequestPart(value = "data", required = false) MusicEntity data
			) throws Exception {
		String UPLOAD_PATH = "C:\\Temp\\";
		int insertedCount = 0;
		String fileNames = "";
		String uuid = UUID.randomUUID().toString();
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa" + files);
		MusicEntity musicEntity = new MusicEntity();
		try {
			for (MultipartFile mf : files) {
				String originFileName = mf.getOriginalFilename();
				try {
					File f = new File(UPLOAD_PATH + File.separator + originFileName);
//					+ originFileName );
					System.out.println("---------------------------" + f);
					mf.transferTo(f);
			
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
				musicEntity.setMusicTitle(originFileName);
				musicEntity.setContents("asd");

//				+ originFileName);
				jpaMusicService.insertMusic(musicEntity);
			}
		

			if (insertedCount > 0) {
				Map<String, Object> result = new HashMap<>();
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} else {
				Map<String, Object> result = new HashMap<>();
				return ResponseEntity.status(HttpStatus.OK).body(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> result = new HashMap<>();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}

	@GetMapping("/api/insertmusic/{poster}")
	public void getImage(@PathVariable("poster") String poster, HttpServletResponse response) throws Exception {
		// reviewImage를 읽어서 전달
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String UPLOAD_PATH = "C:\\Temp\\";
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaa" + poster);
		try {
			response.setHeader("Content-Disposition", "inline;");
			byte[] buf = new byte[1024];
			fis = new FileInputStream(UPLOAD_PATH + poster);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(response.getOutputStream());
			int read;
			while ((read = bis.read(buf, 0, 1024)) != -1) {
				bos.write(buf, 0, read);
			}
		} finally {
			bos.close();
			bis.close();
			fis.close();
		}
	}

	// 모든 상태의 컨테이너를 조회
	@GetMapping("/api/docker")
	public List<String> dockerList() throws Exception {

		final String command = "docker container run -d -w /my-app -v  c:\\test:/my-app sihyun2/spleeter  /bin/bash -c \"spleeter separate -p spleeter:5stems -o output example.mp3\""; 
		List<String> result = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
			process.waitFor();
			process = Runtime.getRuntime().exec(command);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}
}
