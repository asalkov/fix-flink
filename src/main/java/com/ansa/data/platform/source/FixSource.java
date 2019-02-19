package com.ansa.data.platform.source;

import com.ansa.data.platform.fix.FixUtils;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import quickfix.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class FixSource implements SourceFunction<Message> {
    private BlockingQueue<Message> queue = new SynchronousQueue<>();


//    @Override
//    public void open(Configuration parameters) throws Exception {
//        super.open(parameters);
//    }

    @Override
    public void run(SourceContext ctx) throws Exception {
        FixUtils.configure(queue);
        while (true){
            Message value = queue.take();
            if (value != null)
                ctx.collect(value);
            Thread.sleep(5000);
        }
    }

    @Override
    public void cancel() {

    }
}
