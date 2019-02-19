package com.ansa.data.platform.fix;

import quickfix.*;

import java.io.InputStream;

public class FixServer {
    public static void main(String[] args) throws ConfigError {

        Application serverApp = new ServerApplication();

        InputStream st = FixServer.class.getResourceAsStream("acceptor.cfg");

        System.out.println(st);
        SessionSettings sessionSettings = new SessionSettings(
                FixServer.class.getResourceAsStream("acceptor.cfg")
        );


        MessageStoreFactory messageStoreFactory = new FileStoreFactory(sessionSettings);

        MessageFactory messageFactory = new DefaultMessageFactory();

        Acceptor acceptor = new SocketAcceptor(serverApp, messageStoreFactory, sessionSettings, messageFactory);

        acceptor.start();



    }
}
