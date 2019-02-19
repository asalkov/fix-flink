package com.ansa.data.platform.fix;

import quickfix.*;

public class ServerApplication implements Application {
    @Override
    public void onCreate(SessionID sessionId) {


    }

    @Override
    public void onLogon(SessionID sessionId) {
        System.out.println("Session id logon:" + sessionId);
        new Thread(new DataPublisher(sessionId)).start();
    }

    @Override
    public void onLogout(SessionID sessionId) {

    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {

    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {

    }

    @Override
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {

    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {

    }
}
