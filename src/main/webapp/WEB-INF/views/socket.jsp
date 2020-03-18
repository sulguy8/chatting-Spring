<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<body>
<h3>용수 톡톡</h3>
<button id="open">연결</button>
<div id="rDiv" style="display:none">
	<textarea id="tarea" rows= "20" cols= "50" style="display:none"></textarea><br>
	<input type="text" class="msg"><button id="send">전송</button>
</div>
<script>
function qs(id){
	return document.querySelector(id);
}
var ws;
window.onload = function(){
	document.querySelector('button').onclick = function(){
		var url ='ws://localhost/ws/chat'; 
		ws = new WebSocket(url);
		ws.onopen = function(evt){
			if(evt && evt.isTrusted){
				qs('#open').style.display = 'none';
				qs('#rDiv').style.display = '';
				qs('#tarea').style.display = '';
				qs('#send').onclick = function(){
					var msg = document.querySelector('.msg').value;
					document.querySelector('#tarea').innerHTML += '                                      ' + msg + '\r\n';
					ws.send(msg);
				}
			}
		}
		ws.onmessage = function(evt){
			console.log(evt);
			
			var str = evt.data + '\r\n';
			document.querySelector('#tarea').innerHTML += str;
		}
	} 
}
</script>
</body>
</html>