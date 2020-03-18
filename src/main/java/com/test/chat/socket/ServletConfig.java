package com.test.chat.socket;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class ServletConfig extends ServerEndpointConfig.Configurator{
	
	@Override
	public void modifyHandshake (ServerEndpointConfig sec,
			HandshakeRequest request,
			HandshakeResponse response){
		System.out.println("나는 서블릿컨피그");
		HttpSession hs = (HttpSession) request.getHttpSession();
		ServletContext sc = hs.getServletContext();
		sec.getUserProperties().put("hs", hs);
		sec.getUserProperties().put("sc", sc);
		System.out.println(sc);
	}
}
