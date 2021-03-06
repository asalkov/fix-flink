package com.ansa.data.platform;

import com.ansa.data.platform.source.FixSource;
import com.ansa.data.platform.source.FixSourceWithString;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import quickfix.Message;
import quickfix.fix42.ExecutionReport;

import javax.swing.tree.FixedHeightLayoutCache;

public class ProcessorRunner {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> reposts = env.addSource(new FixSourceWithString());
        reposts.map(m -> m.toString()).print();


        env.execute();



    }
}
