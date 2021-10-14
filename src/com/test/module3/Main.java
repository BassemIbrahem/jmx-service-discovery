package com.test.module3;

import java.io.IOException;

import javax.management.AttributeChangeNotification;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.test.util.Logger;

public class Main {

    public static void main(String[] args) throws MalformedObjectNameException, IOException, InstanceNotFoundException {
        Logger.log("Starting Module 3 (Observer)");

        // Construct service name
        ObjectName serviceName = new ObjectName("com.test:type=Service1");

        // Notification handler
        NotificationListener notificationRecevied = (Notification notification, Object handback) -> { 
            Logger.log("Received notification from " + notification.getClass().getName() + " at: " + notification.getSource() + " event: " + notification.getMessage());
            if (notification instanceof AttributeChangeNotification) {
                AttributeChangeNotification acn = (AttributeChangeNotification) notification;
                Logger.log(">>> " + acn.getAttributeName() + ":" + acn.getAttributeType() + " changed from " + acn.getOldValue() + " to " + acn.getNewValue());
            }
        };
        
        // Listen on events
        try(JMXConnector jmxc = JMXConnectorFactory.connect(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi"), null)) {
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            mbsc.addNotificationListener(serviceName, notificationRecevied, null, null);
          
            // Wait forever
            System.in.read();
        }
        
        Logger.log("Termined Module 3");
    }
}
