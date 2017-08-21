package kr.co.intobe;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	private Map<String,Object> userMap;
	
	private RoomManager roomManager = new RoomManager();
	
	private Room room;
	
	private User user;
	
	/**
	 * 클라이언트 연결 시 실행되는 메소드
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//sessionList.add(session);
		logger.info("{} 연결됨", session.getId());
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", "roomList");
		param.put("value", roomManager.getRoomName());
		
		String json = mapper.writeValueAsString(param);
		session.sendMessage(new TextMessage(json));
	}
	
	
	/**
	 * 클라이언트가 웹소켓서버로 메세지를 전송했을 때 실행되는 메소드
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
		
		Map<String,Object> param =  mapper.readValue(message.getPayload(), new TypeReference<Map<String,Object>>(){});
		sendType(session, message);
/*		for (WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage(message.getPayload()));
		}*/
	}
	
	
	
	
	/**
	 * 클라이언트가 연결을 끊었을 경우 실행되는 메소드
	 */
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		logger.info("{} 연결 끊킴", session.getId());
	}
	
	
	
	public void sendType(WebSocketSession session, TextMessage message) throws Exception {
		Map<String,Object> param =  mapper.readValue(message.getPayload(), new TypeReference<Map<String,Object>>(){});
		String type = (String) param.get("type");
		switch(type) {
		case "login" : 
			userMap = new HashMap<String,Object>();
			String id = (String) param.get("id");
			user = new User(id, session);
			userMap.put(id, user);
			break;
		case "createRoom" :
			createRoom(session, message);
			break;
		case "enterRoom" : 
			room.getUserList(session, message);
			break;
		case "joinRoom" :
			roomManager.joinRoom(user, (String) param.get("roomName"));
			break;
		case "sendMsg" : 
			room.sendMessage(session, message);
			break;
		}
		
	}
	
	private void createRoom(WebSocketSession session, TextMessage message) throws Exception {
		Map<String,Object> param =  mapper.readValue(message.getPayload(), new TypeReference<Map<String,Object>>(){});
		User user = (User) userMap.get((String) param.get("id"));
		room = new Room();
		room = roomManager.createRoom(user, (String) param.get("roomName"));
	}
	
	private void sendMessage(Map<String, Object> param, WebSocketSession session, TextMessage message) throws Exception {
/*		for (Map<String, Object> user : userList) {
			WebSocketSession sess = (WebSocketSession) user.get("session");
			sess.sendMessage(new TextMessage(message.getPayload()));
		}*/
	}
}
