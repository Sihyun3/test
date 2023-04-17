package board.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import board.entity.MessageEntity;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class JpaMessageController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    /*
        /sub/channel/12345      - 구독(channelId:12345)
        /pub/hello              - 메시지 발행
    */

    @MessageMapping("/hello")
    public void message(MessageEntity message) {
    	simpMessageSendingOperations.convertAndSend("/sub/channel/" + message.getChannelId(), message);
    }
}