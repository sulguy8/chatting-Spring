package com.test.chat.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/ws/chat", configurator = ServletConfig.class)
public class SocketTest { 
	
	private static List<Session> ssList = new ArrayList<Session>();
	@OnOpen
	public void open(Session ss, EndpointConfig ec) {
		System.out.println("나는 소켓테스트의 오픈 메서드");
		HttpSession hs = (HttpSession)ec.getUserProperties().get("hs");
		System.out.println(ssList.indexOf(ss));
		if(ssList.indexOf(ss)==-1) {
			ssList.add(ss);
		}
		try {
			ss.getBasicRemote().sendText("용수 톡톡에 접속함");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnMessage
	public void message(String msg, Session ss) {	
		System.out.println("나는 소켓테스트의 메세지 메서드");
		String rst = ss.getId() + "번 세션 : " + msg;
		
		for(Session ss1:ssList) {
			if(ss1!=ss) {
				try {					
					ss1.getBasicRemote().sendText(rst);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@OnClose
	public void close(Session ss)	{
		System.out.println("나는 소켓테스트의 클로즈 메서드");
		ssList.remove(ss);
	}
	
	@OnError
	public void error(Throwable t) {
		System.out.println("나는 소켓테스트의 에러 메서드");
	}
	
}
