package com.ansa.data.platform.fix;

import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.field.*;
import quickfix.fix42.ExecutionReport;

public class DataPublisher implements Runnable {

    private SessionID sessionID;

    public DataPublisher(SessionID sessionID){
        this.sessionID = sessionID;

    }
    @Override
    public void run() {
        while (true){
            try {
                ExecutionReport report = new ExecutionReport();
                /*
                setField(orderID);
		setField(execID);
		setField(execTransType);
		setField(execType);
		setField(ordStatus);
		setField(symbol);
		setField(side);
		setField(leavesQty);
		setField(cumQty);
		setField(avgPx);
                 */
                report.setField(new OrderID("XXXX"));
                report.setField(new ExecID("aaaa"));
                report.setField(new ExecType(ExecType.NEW));

                report.setField(new Side(Side.BUY));

                report.setField(new ExecTransType(ExecTransType.NEW));
                report.setField(new OrdStatus(OrdStatus.NEW));
                report.setField(new Symbol("AAA"));
                report.setField(new LeavesQty(10));
                report.setField(new CumQty(5));
                report.setField(new AvgPx(5));


                Session.sendToTarget(report, sessionID);
                Thread.sleep(5000);

                System.out.println("Send " + report);
            } catch (SessionNotFound sessionNotFound) {
                sessionNotFound.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
