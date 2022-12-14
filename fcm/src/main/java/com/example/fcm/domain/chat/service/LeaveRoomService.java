package com.example.fcm.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.example.fcm.domain.chat.domain.room.Room;
import com.example.fcm.domain.chat.domain.roomuser.RoomUser;
import com.example.fcm.domain.chat.facade.RoomFacade;
import com.example.fcm.domain.chat.facade.RoomUserFacade;
import com.example.fcm.domain.chat.presentation.dto.request.LeaveRoomRequest;
import com.example.fcm.domain.chat.presentation.dto.response.RoomNotificationResponse;
import com.example.fcm.domain.user.domain.User;
import com.example.fcm.domain.user.facade.UserFacade;
import com.example.fcm.global.websocket.property.ClientProperty;
import com.example.fcm.global.websocket.property.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LeaveRoomService {

    private final RoomFacade roomFacade;
    private final UserFacade userFacade;
    private final RoomUserFacade roomUserFacade;
    private final SocketIOServer socketIOServer;

    @Transactional
    public void execute(SocketIOClient socketIOClient, LeaveRoomRequest request) {
        User user = userFacade.findUserByEmail(socketIOClient.get(ClientProperty.USER_KEY));
        Room room = roomFacade.getRoom(request.getRoomId());
        RoomUser roomUser = roomUserFacade.getRoomUser(user, room);

        room.removeRoomUser(roomUser);

        String socketRoomId = room.getId().toString();
        socketIOClient.leaveRoom(socketRoomId);

        RoomNotificationResponse response = new RoomNotificationResponse(socketRoomId, user.getName() + "퇴장!");

        socketIOServer.getRoomOperations(socketRoomId)
                .sendEvent(SocketProperty.LEAVE, response);
    }

}

