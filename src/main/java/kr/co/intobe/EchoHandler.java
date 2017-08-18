package kr.co.intobe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EchoHandler extends TextWebSocketHandler {
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	private ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 클라이언트 연결 시 실행되는 메소드
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		logger.info("{} 연결됨", session.getId());
	}
	
	
	/**
	 * 클라이언트가 웹소켓서버로 메세지를 전송했을 때 실행되는 메소드
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
		
		Map<String,Object> param =  mapper.readValue(message.getPayload(), new TypeReference<Map<String,Object>>(){});
		
		logger.info("id : {}", param.get("id"));
		logger.info("message : {}", param.get("message"));
		
		for (WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage(message.getPayload()));
		}
	}
	
	
	
	
	/**
	 * 클라이언트가 연결을 끊었을 경우 실행되는 메소드
	 */
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		logger.info("{} 연결 끊킴", session.getId());
	}
}
