package com.ansa.data.platform.fix;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import quickfix.*;
import quickfix.fix42.MessageCracker;


import java.util.concurrent.BlockingQueue;

public class ClientApplication extends MessageCracker implements Application {


    private SourceFunction.SourceContext ctx;
    private BlockingQueue<Message> queue;

    public ClientApplication(){

    }
    public ClientApplication(BlockingQueue<Message> queue){
        this.queue = queue;

    }


    @Override
    public void onCreate(SessionID sessionId) {

    }

    @Override
    public void onLogon(SessionID sessionId) {

    }

    @Override
    public void onLogout(SessionID sessionId) {

    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {

    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound,
            IncorrectDataFormat, IncorrectTagValue, RejectLogon {

    }

    @Override
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {

    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound,
            IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println(message);
        queue.offer(message);
    }

//
//    @Override
//    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
//        System.out.println("from app" + message);
//        //crack(message, sessionId);
//        if (ctx != null)
//            ctx.collect(message);
//    }


}
