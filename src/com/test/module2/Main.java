package com.test.module2;

import java.io.IOException;
import java.util.Scanner;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.test.module1.MyServiceMBean;
import com.test.util.Logger;

public class Main {

    public static void main(String[] args) throws MalformedObjectNameException, IOException {
        Logger.log("Starting Module 2 (Client)");

        // Construct service name
        ObjectName serviceName = new ObjectName("com.test:type=Service1");

        try(JMXConnector jmxc = JMXConnectorFactory.connect(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi"), null); Scanner in = new Scanner(System.in)) {
            boolean exit = false;
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            while(!exit) {
                MyServiceMBean serviceProxy = JMX.newMBeanProxy(mbsc, serviceName, MyServiceMBean.class, true);

                switch(in.next().toUpperCase()) {
                    case "A": {
                        int randomX = (int)(10 * Math.random());
                        int randomY = (int)(10 * Math.random());
                        int sum = serviceProxy.add(randomX, randomY);
                        Logger.log(String.format("%d+%d=%d", randomX, randomY, sum));
                        break;
                    }
                    case "D": { 
                        int randomX = (int)(10 * Math.random());
                        int randomY = (int)(10 * Math.random());
                        int sum = serviceProxy.div(randomX, randomY);
                        Logger.log(String.format("%d+%d=%d", randomX, randomY, sum));
                        break;
                    }
                    case "C": { 
                        int randomValue = (int)(10 * Math.random()); 
                        serviceProxy.accumelate(randomValue);
                        Logger.log("Accumelate " + randomValue); 
                        break;
                    }
                    case "G": {
                        Logger.log("Value = " + serviceProxy.get()); 
                        break;
                    }
                    case "E": { 
                        exit = true; 
                        break;
                    }
                }
            }
        }
        
        Logger.log("Termined Module 2");
    }

}
