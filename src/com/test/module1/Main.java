package com.test.module1;

import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import com.test.util.Logger;

public class Main {

    public static void main(String[] args) throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, IOException, InstanceNotFoundException {
        Logger.log("Starting Module 1 (Service Provider)");
        
        // Get the Platform MBean Server
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        // Construct any arbitrary service name 
        ObjectName serviceName = new ObjectName("com.test:type=Service1");

        // Create 
        MyService service = new MyService();

        // Register 
        mbs.registerMBean(service, serviceName);

        // Wait forever
        System.in.read();
        
        Logger.log("Termined Module 1");
    }
}
