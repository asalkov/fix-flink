package com.ansa.data.platform.source;

import com.ansa.data.platform.fix.FixUtils;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import quickfix.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class FixSource implements SourceFunction<Message> {
    private BlockingQueue<Message> queue = new SynchronousQueue<>();


    private class QueueSizePrinter implements Runnable{

        private BlockingQueue queue;
        public QueueSizePrinter(BlockingQueue queue){
            this.queue = queue;
        }

        @Override
        public void run() {

            while (true){
                System.out.println("Queue size : " + queue.size());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run(SourceContext ctx) throws Exception {
        FixUtils.configure(ctx);
        //new Thread(new QueueSizePrinter(queue)).start();

//        while (true){
//            Message value = queue.take();
//            if (value != null)
//                ctx.collect(value);
//            Thread.sleep(5000);
//        }
    }

    @Override
    public void cancel() {

    }
}
