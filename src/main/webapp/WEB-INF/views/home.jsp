<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WebSocket</title>
<!-- 	<link rel="stylesheet" type="text/css" href="resources/css/semantic/semantic.min.css"> -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"
  			integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  			crossorigin="anonymous"></script>
<!-- <script src="resources/css/semantic/semantic.min.js"></script> -->
	<script src="/resources/js/sockjs-0.3.4.js"></script>
	<script src="/resources/js/location.js"></script>
	<script src="/resources/js/socket.js"></script>
	<script type="text/javascript">	
		$(document).ready(function () {
			$("#createRoom").click(function () {
				createRoom();
			});
		});
		window.onpageshow = function (event) {
			if (event.persisted) {
				console.log("뒤로가기로 호출");
			} else {
				console.log("새로 호출");
			}
		}
	</script>
	<script src="/resources/js/chat.js"></script>
</head>
	<body>
		<div class="content">
		<input type="text" id="userId" />
<button type="button" onclick="init()">등록</button>
<h4>방 목록</h4>
<div id="roomList"></div>
<input type="text" id="roomName" />
<input type="button" id="createRoom" value="방만들기" />
<button type="button" onclick="closeSocket()">연걸끊기</button>
<div id="data"></div> 
		</div>
	</body>
</html>
