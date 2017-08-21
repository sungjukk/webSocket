package kr.co.intobe;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoomManager {
	private static Logger logger = LoggerFactory.getLogger(RoomManager.class);
	static List<Room> roomList;
	
	public RoomManager() {
		roomList = new ArrayList<Room>();
	}
	
	public Room createRoom(User user, String roomName) {
		Room room = new Room(user, roomName);
		roomList.add(room);
		logger.info("방 생성 완료");
		return room;
	}
	
	public List<String> getRoomName() {
		List<String> roomName = new ArrayList<String>();
		for (Room room : roomList) {
			roomName.add(room.getRoomName());
		}
		return roomName;
	}
	
	public Room joinRoom(User user, String roomName) {
		Room room = null;
		for (Room rm : roomList) {
			if (rm.getRoomName().equals(roomName)) {
				logger.info("방에 접속하는 유저 id : {}", user.getId());
				rm.enterRoom(user);
				room = rm;
				break;
			}
		}
		return room;
	}
	
	public static void removeRoom(Room room) {
		roomList.remove(room);
	}
	
	public static int roomCount() {
		return roomList.size();
	}
}
