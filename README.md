# JMX Example
The Java Management Extensions (JMX) framework provides an easily configurable, scalable, reliable infrastructure for managing Java application either locally or remotely. It comes out of the box with JDK and this is an example on using it for service discovery

# What is inside

- *Module1*  publish a service
- *Module2*  service client
- *Module3*  observer on service changes

# How to Run it
```
$(JDK_HOME)/bin/java -Dcom.sun.management.jmxremote.port=9999 \
                     -Dcom.sun.management.jmxremote.authenticate=false \
                     -Dcom.sun.management.jmxremote.ssl=false \
                     com.test.module1.Main
                     
$(JDK_HOME)/bin/java com.test.module2.Main

$(JDK_HOME)/bin/java com.test.module3.Main
```
