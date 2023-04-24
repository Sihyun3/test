package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import board.entity.ChattingEntity;
import board.entity.MessageEntity;
import board.service.JpaService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class JpaMessageController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    /*
        /sub/channel/12345      - 구독(channelId:12345)
        /pub/hello              - 메시지 발행
    */
    

//    @Autowired
//    JpaService jpaservice;
//    @MessageMapping("/hi/${channelId}")
//    public String postMessage(@Requestparam String channelId) {
//    	return jpaservice.getData(channelId);
//    }
 // 방이 있고 방안에 두명이 들어간다. 클릭했을 때 방 인덱스를 넘기고 그 인덱스 안에는 두개의 값이 있고 두개를 리턴해서 넘기면 될듯
    
//	public ResponseEntity<Map<String, Object>> mainpage() throws Exception {
//		List<MovieDto> movieDto = movieService.listMovie();
//		List<AnnouncementDto> AnnouncementDto = movieService.listAnnouncementDto();
//		Map<String, Object> map = new HashMap<>();
//		map.put("announcementList", AnnouncementDto);
//		map.put("listMovie", movieDto);

    
    @Autowired
    private JpaService jpaService;
    
    @GetMapping("/chatroom/{userId}")
    public ResponseEntity<List<ChattingEntity>> chatroom(@PathVariable("userId") int userId){
    	String a = Integer.toString(userId);
    	System.out.println("aaaaaaaaaaaaaaaa"+a.getClass().getName());
    	List<ChattingEntity> chattingEntity = jpaService.getChattingRoom(a);
    	return ResponseEntity.status(HttpStatus.OK).body(chattingEntity);
    }
    
    
    @GetMapping("/chat/{roomIdx}")
	public ResponseEntity<Map<String, Object>> connect(@PathVariable("roomIdx") int roomIdx){
    	Map<String,Object> map = new HashMap<>();
    	List<MessageEntity> MessageEntity = jpaService.getMessage(roomIdx);
    	map.put("messagelist", MessageEntity);
    	ChattingEntity chattingEntity = jpaService.getchatting(roomIdx);

    	map.put("chatting",chattingEntity);

    	return ResponseEntity.status(HttpStatus.OK).body(map);
    }
    @MessageMapping("/hello")
    public void message(MessageEntity message) {
    	simpMessageSendingOperations.convertAndSend("/sub/channel/" + message.getChannelId(), message);
    	jpaService.insertData(message);
    }
    // 1. 채팅방을 클릭하면 get 룸아이디엑스를 파라미터로 받는다
    // 2. 1번방(1 유저) 2번 방(2번 유저) 뽑아서 리턴시켜준다 클라이언트 쪽으로
    // 3. 룸 인데스를 가지고 메세지 내역에서 같은 것들을 클라이언트 쪽으로 리턴시켜준다
    // 4. 클라이언트가 그걸 받으면 그 데이터를 토대로 채널에 연결을 한다
    // 5. 데이터 넘어가면 알아서 db에 저장이되고 이거는 실시간 채팅은 클라이언트에서 구현할 거같아요 이게 내가 쓴 채팅 내역은
    // 리턴이 아마 안될거 같아서 클라이언트쪽에서 처리할 예정? 이건 방법을 찾으면 바뀔수도 있고 아닐 수도 있고
}