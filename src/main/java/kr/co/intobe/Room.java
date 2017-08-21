package kr.co.intobe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.experimental.theories.Theories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Room {
	private static Logger logger = LoggerFactory.getLogger(Room.class);
	private List<User> userList;
	private String name;
	private ObjectMapper mapper = new ObjectMapper();
	
	
	public Room() {
		userList = new ArrayList<User>();
	}
	
	public Room(User user) {
		userList = new ArrayList<User>();
		userList.add(user);
	}
	
	public Room(User user, String roomName) {
		userList = new ArrayList<User>();
		userList.add(user);
		this.name = roomName;
	}
	
	public void enterRoom(User user) {
		userList.add(user);
	}
	
	public void sendMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map<String,Object> param =  mapper.readValue(message.getPayload(), new TypeReference<Map<String,Object>>(){});
		for (User user : userList) {
			WebSocketSession sess = user.getSession();
			sess.sendMessage(new TextMessage(message.getPayload()));
		}
	}
	
	public String exitRoom(User user) {
		userList.remove(user);
		logger.info("{} 님이 퇴장 하였습니다.", user.getId());
		return user.getId() + "님이 퇴장 하였습니다.";
	}
	
	public int getRoomSize() {
		return userList.size();
	}
	
	public boolean isEnterUser(User user) {
		for (User inUser : userList) {
			if (inUser.getSession().getId().equals(user.getSession().getId())) {
				return true;
			}
		}
		return false;
	}
	
	public void getUserList(WebSocketSession session, TextMessage message) throws Exception {
		List<String> userNameList = new ArrayList<String>();
		Map<String,Object> param = new HashMap<String, Object>();
		
		logger.info("방에 접속한 유저 수 : {}", getRoomSize());
		
		param.put("type", "userList");
		
		for (User user : userList) {
			logger.info("방에 접속한 유저 id : {}", user.getId());
			userNameList.add(user.getId());
		}
		
		param.put("value", userNameList);
		
		String value = mapper.writeValueAsString(param);
		
		for (User user : userList) {
			WebSocketSession sess = user.getSession();
			sess.sendMessage(new TextMessage(value));
		}
		
	}
	
	public String getRoomName() {
		return name;
	}
}