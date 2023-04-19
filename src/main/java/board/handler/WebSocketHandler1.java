//package board.handler;
//
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import board.entity.MessageEntity;
//import board.service.JpaService;
//
//public class WebSocketHandler1 extends TextWebSocketHandler {
//
//	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
//	@Autowired
//	private JpaService jpaService;
//	// 웹소켓 연결 시
//	@Override
//	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		   var sessionId = session.getId();
//	        sessions.put(sessionId, session);	//세션에 저장
//
//	        MessageEntity message = new MessageEntity();
//	        message.setSender(sessionId);
//	        message.setReceiver("all");
////	        		MessageEntity.builder().sender(sessionId).receiver("all").build();
//	        message.setType("new");
//	       CharSequence list = null;
//	       sessions.values().forEach(s -> {	//모든 세션에 알림
//	            try {
//	                if(!s.getId().equals(sessionId)) {
//	                    s.sendMessage(new TextMessage(list));
//	                }
//	            }
//	            catch (Exception e) {
//	                //TODO: throw
//	            }
//	        });
//	}

//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
//
//    	MessageEntity message = Utils.getObject(textMessage.getPayload());
//        message.setSender(session.getId());
//
//        WebSocketSession receiver = sessions.get(message.getReceiver());
//
//        if (receiver != null && receiver.isOpen()) {
//
//            receiver.sendMessage(new TextMessage(Utils.getString(message)));
//        }
//    }
//
//    //소켓 연결 종료
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//
//        var sessionId = session.getId();
//
//        sessions.remove(sessionId);
//
//        final MessageEntity message = new Message();
//        message.closeConnect();
//        message.setSender(sessionId);
//
//        sessions.values().forEach(s -> {
//            try {
//                s.sendMessage(new TextMessage(Utils.getString(message)));
//            } catch (Exception e) {
//                //TODO: throw
//            }
//        });
//    }
//}
