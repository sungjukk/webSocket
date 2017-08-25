function chatController() {
	var obj = {};
	var jsonStr;
	obj.id = userId;
	obj.message = "정상작동함";
	obj.type = "enterRoom";
	
	sock.send(JSON.stringify(obj));
}

function initUserList(data) {
	console.log('chat.js',data.value.length);
	$(".userList").html("");
	for(var i = 0; i < data.value.length; i++) {
		$(".userList").append(data.value[i] + "</br>");
	}
}

function sendMsg() {
	var obj = {};
	obj.sendId = userId;
	obj.message = $("input[name=message]").val();
	obj.type = "sendMsg";
	
	sock.send(JSON.stringify(obj));
	
	$("input[name=message]").val('');
}

function receiveMsg(data) {
	$(".chatRoom").append(data.sendId + " : " + data.message + "</br>");
}

function outRoom() {
	var obj = {};
	obj.id = userId;
	obj.type = "exitRoom";
	
	sock.send(JSON.stringify(obj));
	
	location.href = "/#main";
}
/*
function exitRoom() {
	var obj = {};
	obj.id = userId;
	obj.type = "exitRoom";
	
	sock.send(JSON.stringify(obj));
	
	location.href = "/";
}*/