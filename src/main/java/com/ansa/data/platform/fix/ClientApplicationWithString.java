package com.ansa.data.platform.fix;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import quickfix.*;
import quickfix.fix42.MessageCracker;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;

public class ClientApplicationWithString extends MessageCracker implements Application {

    private SourceFunction.SourceContext ctx;

    public ClientApplicationWithString(){

    }

    public ClientApplicationWithString(SourceFunction.SourceContext ctx){
        this.ctx = ctx;
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
        if (ctx != null){
            ctx.collect(LocalDateTime.now().toString());
        }
    }
}
