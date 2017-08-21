package kr.co.intobe;

import org.springframework.web.socket.WebSocketSession;

public class User {
	private String id;
	private WebSocketSession session;
	
	public User(String id, WebSocketSession session) {
		this.id = id;
		this.session = session;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WebSocketSession getSession() {
		return session;
	}

	public void setSession(WebSocketSession session) {
		this.session = session;
	}
}
