package com.test.module1;

import javax.management.AttributeChangeNotification;
import javax.management.NotificationBroadcasterSupport;

import com.test.util.Logger;

public class MyService extends NotificationBroadcasterSupport implements MyServiceMBean{

    private static long sequenceNumber = 0;
    
    private int value;
    
    @Override
    public int add(int x, int y) {
        Logger.log("add " + x + "," + y);
        return x + y;
    }
    
    @Override
    public int div(int x, int y) {
        Logger.log("div " + x + "," + y);
        return x / y;
    }

    @Override
    public void accumelate(int x) {
        Logger.log("acc " + x);
        value += x;
        
        sendNotification(new AttributeChangeNotification(this, sequenceNumber++, System.currentTimeMillis(), "Accumelate", "value", "int", value - x, value));
        if (value % 2 == 0)
            sendNotification(new AttributeChangeNotification(this, sequenceNumber++, System.currentTimeMillis(), "Even", "value", "int", value - x, value));
    }

    @Override
    public int get() {
        Logger.log("get " + value);
        return value;
    }

}
