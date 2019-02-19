package com.ansa.data.platform;

import com.ansa.data.platform.source.FixSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import quickfix.Message;
import quickfix.fix42.ExecutionReport;

import javax.swing.tree.FixedHeightLayoutCache;

public class ProcessorRunner {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Message> reposts = env.addSource(new FixSource());
        reposts.map(m -> m.toString()).print();


        env.execute();



    }
}
