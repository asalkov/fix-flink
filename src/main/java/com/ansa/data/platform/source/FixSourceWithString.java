package com.ansa.data.platform.source;

import com.ansa.data.platform.fix.FixUtils;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import quickfix.Message;
import quickfix.SessionID;
import quickfix.SessionNotFound;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class FixSourceWithString implements SourceFunction<String> {

    private SessionID sessionID;

    @Override
    public void run(SourceContext ctx) throws Exception {
        sessionID = FixUtils.configureString(ctx);
    }

    @Override
    public void cancel() {
        try {
            FixUtils.cancelString(sessionID);
        } catch (SessionNotFound sessionNotFound) {
            sessionNotFound.printStackTrace();
        }

    }
}
