<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			.chatRoom, .userListDiv {float: left}
		</style>
	</head>
	<body>
		<button type="button" id="chat" onclick="chatController()"></button>
		<div style="width: 704px; height: 500px">
			<div class="chatRoom" style="border: 1px solid black; width: 500px; height: 100%"></div>
			<div class="userListDiv" style="border: 1px solid black; width: 200px; height: 100%;">
				<h5 style="text-align: center; margin-top: 5px">입장 목록</h5>
				<div class="userList"></div>
			</div>
		</div>
		<div class="inputDiv" style="margin-top: 5px; width: 700px">
			<input type="text" name="message" style="width: 90%">
			<button type="button" onclick="sendMsg()">보내기</button>
		</div>
	</body>
</html>