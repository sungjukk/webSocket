/**
 * 
 */
var sock;
var userId;
//웸소켓을 지정한 url로 연결한다.
sock = new SockJS("/echo");
		
function init() {
	//userId = prompt("아이디 입력", "");
	userId = $("#userId").val();
	loginSocket(userId);
}
		
function loginSocket(userId) {
	var obj = {};
	obj.type = "login";
	obj.id = userId;
	sock.send(JSON.stringify(obj));
}
		
sock.onopen = function (message) {
}
		
		//자바스크립트 안에 function을 집어 넣을 수 있음
		//데이터가 나한테 전달 된 경우 자동으로 실행하는 function
sock.onmessage = onMessage;
		
		//연결을 끊을 경우 실행하는 메소드
sock.onclose = onClose;
		
function sendMessage() {
	var obj = {};
	var jsonStr;
	obj.id = userId;
	obj.message = $("#message").val();
	obj.type = "message";
			
	jsonStr = JSON.stringify(obj);
	// 소켓으로 메세지 보냄
	sock.send(jsonStr);
}
		
// evt 파라미터는 웹소켓을 보내준 데이터
function onMessage(evt) {
	var data = JSON.parse(evt.data);
	console.log(data);
	switch (data.type) {
	case 'userList' : initUserList(data); break;
	case 'roomList' : initRoomList(data); break;
	case 'sendMsg' : receiveMsg(data); break;
	case 'error' : socketError(data); break;
	}
}
		
function onClose(evt) {
	console.log(evt);
	//$("#data").append('연결 끊킴');
}
		
function createRoom() {
	var obj = {};
	var roomName = $("#roomName").val();
	obj.type = "createRoom";
	obj.id = userId;
	obj.roomName = roomName;
			
	sock.send(JSON.stringify(obj));
			
	location.href = "/#chat"
}
		
function initRoomList(data) {
	for (var i=0; i < data.value.length; i++) {
		$("#roomList").append("<a href='javascript:goChatRoom(\"" + data.value[i] + "\")'>" + data.value[i] + "</a></br>");
	}
}
		
function goChatRoom(roomName) {
	console.log(roomName);
	var obj = {};
	obj.roomName = roomName;
	obj.id = userId;
	obj.type = "joinRoom";
			
	sock.send(JSON.stringify(obj));
			
	location.href = "/#chat";
}
		
function socketError(data) {
	alert(data.message);
	sock.close();
}
		
function closeSocket() {
	sock.close();
}