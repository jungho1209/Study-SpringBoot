package com.example.fcm.global.websocket.connect;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.example.fcm.global.security.jwt.JwtTokenProvider;
import com.example.fcm.global.websocket.property.ClientProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WebSocketJwtHandler {

    public static final ConcurrentMap<String, SocketIOClient> socketIOClientMap = new ConcurrentHashMap<>();
    private final JwtTokenProvider jwtTokenProvider;

    @OnConnect
    public void onConnect(SocketIOClient client) {
        String token = client.getHandshakeData().getHttpHeaders().get("Authorization");
        Authentication authentication = jwtTokenProvider.authentication(token);
        socketIOClientMap.put(authentication.getName(), client);
        client.set(ClientProperty.USER_KEY, authentication.getName());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        socketIOClientMap.remove(client.get(ClientProperty.USER_KEY).toString());
    }

}