<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WebSocket</title>
	<script src="/resources/js/jquery-3.2.0.min.js"></script>
	<script src="/resources/js/sockjs-0.3.4.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			$("#sendBtn").click(function () {
				sendMessage();
			});
		});
		
		var sock;
		
		//웸소켓을 지정한 url로 연결한다.
		sock = new SockJS("<c:url value='/echo' />");
		
		//자바스크립트 안에 function을 집어 넣을 수 있음
		//데이터가 나한테 전달 된 경우 자동으로 실행하는 function
		sock.onmessage = onMessage;
		
		//연결을 끊을 경우 실행하는 메소드
		sock.onclose = onClose;
		
		function sendMessage() {
			// 소켓으로 메세지 보냄
			sock.send($("#message").val());
		}
		
		// evt 파라미터는 웹소켓을 보내준 데이터
		function onMessage(evt) {
			var data = evt.data;
			$("#data").append(data + '<br/>');
		}
		
		function onClose(evt) {
			$("#data").append('연결 끊킴');
		}
	</script>
</head>
	<body>
		<input type="text" id="message" />
		<input type="button" id="sendBtn" value="전송" />
		<div id="data"></div> 
	</body>
</html>
