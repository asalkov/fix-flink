package com.ansa.data.platform.fix;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import quickfix.*;
import quickfix.field.BeginString;
import quickfix.field.EncryptMethod;
import quickfix.field.HeartBtInt;
import quickfix.field.ResetSeqNumFlag;
import quickfix.fix42.Logon;
import quickfix.fix42.Logout;

import java.util.concurrent.BlockingQueue;

public class FixUtils {
    public static void configure() throws ConfigError, SessionNotFound {
        Application serverApp = new ClientApplication();

        SessionSettings sessionSettings = new SessionSettings(
                FixServer.class.getResourceAsStream("initiator.cfg")
        );

        MessageStoreFactory messageStoreFactory = new FileStoreFactory(sessionSettings);

        MessageFactory messageFactory = new DefaultMessageFactory();

        Initiator initiator = new SocketInitiator(
                serverApp, messageStoreFactory, sessionSettings, messageFactory);

        initiator.start();

        SessionID sessionId = initiator.getSessions().get(0);

        sendLogonRequest(sessionId);

    }


    public static void configure(SourceFunction.SourceContext context) throws ConfigError, SessionNotFound {
        Application serverApp = new ClientApplication(context);

        SessionSettings sessionSettings = new SessionSettings(
                FixServer.class.getResourceAsStream("initiator.cfg")
        );

        MessageStoreFactory messageStoreFactory = new FileStoreFactory(sessionSettings);

        MessageFactory messageFactory = new DefaultMessageFactory();

        Initiator initiator = new SocketInitiator(
                serverApp, messageStoreFactory, sessionSettings, messageFactory);

        initiator.start();

        SessionID sessionId = initiator.getSessions().get(0);

        sendLogonRequest(sessionId);
    }

    public static SessionID configureString(SourceFunction.SourceContext context) throws ConfigError, SessionNotFound {
        Application serverApp = new ClientApplicationWithString(context);

        SessionSettings sessionSettings = new SessionSettings(
                FixServer.class.getResourceAsStream("initiator.cfg")
        );

        MessageStoreFactory messageStoreFactory = new FileStoreFactory(sessionSettings);

        MessageFactory messageFactory = new DefaultMessageFactory();

        Initiator initiator = new SocketInitiator(
                serverApp, messageStoreFactory, sessionSettings, messageFactory);

        initiator.start();

        SessionID sessionId = initiator.getSessions().get(0);

        sendLogonRequest(sessionId);

        return sessionId;
    }


    public static void configure(BlockingQueue<Message> queue) throws ConfigError, SessionNotFound {
        Application serverApp = new ClientApplication(queue);

        SessionSettings sessionSettings = new SessionSettings(
                FixServer.class.getResourceAsStream("initiator.cfg")
        );

        MessageStoreFactory messageStoreFactory = new FileStoreFactory(sessionSettings);

        MessageFactory messageFactory = new DefaultMessageFactory();

        Initiator initiator = new SocketInitiator(
                serverApp, messageStoreFactory, sessionSettings, messageFactory);

        initiator.start();

        SessionID sessionId = initiator.getSessions().get(0);

        sendLogonRequest(sessionId);
    }


    private static void sendLogonRequest(SessionID sessionId) throws SessionNotFound {
        Logon logon = new Logon();
        Message.Header header = logon.getHeader();
        header.setField(new BeginString("FIX.4.2"));
        logon.set(new HeartBtInt(30));
        logon.set(new ResetSeqNumFlag(true));
        logon.set(new EncryptMethod());
        boolean sent = Session.sendToTarget(logon, sessionId);
        System.out.println("Logon Message Sent : " + sent);
    }

    public static void cancelString(SessionID sessionId) throws SessionNotFound {
        Logout logout = new Logout();
        Message.Header header = logout.getHeader();
        header.setField(new BeginString("FIX.4.2"));
        //logout.set(new HeartBtInt(30));
        //logout.set(new ResetSeqNumFlag(true));
        //logout.set(new EncryptMethod());
        boolean sent = Session.sendToTarget(logout, sessionId);
        System.out.println("Logon Message Sent : " + sent);

    }
}
