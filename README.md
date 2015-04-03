# TellStick Anypoint Connector

This connector makes it possible to connect to a  [telldus-core](http://developer.telldus.com/wiki/TellStickInstallationUbuntu) telldusd and control devices connected to it.

# Mule supported versions

Mule 3.6.x

# Telldus TellStick supported versions

This module has been tested with TellStick DUO.

# Build

This connector depends on [jstick api](http://jstick.net/) and since it is not available in any public maven repo you need to install it into your local maven repository.

```
mvn install:install-file -Dfile=lib/jstick-api-1.1.jar -DgroupId=net.jstick \
    -DartifactId=jstick-api -Dversion=1.1 -Dpackaging=jar
```

To be abel to use this connector you need to have a Tellstick device (standard or DUO) along with [telldus-core](http://developer.telldus.com/wiki/TellStickInstallationUbuntu) 
software running on a Linux box (currently only Linux is supported due to limitation in the jstick-api). 
The runtime library libtelldus-core.so should be installed in system library path to make life easier.

After this you should be able to build the connector.

# Installation 
For beta connectors you can download the source code and build it with devkit to find it available on your local repository. Then you can add it to Studio…<TBD>

For released connectors you can download them from the update site in Studio. 
Open MuleStudio, go to Help → Install New Software and select MuleStudio Cloud Connectors Update Site where you’ll find all avaliable connectors.

#Usage
For information about usage our documentation at http://github.com/ullgren/tell-stick-connector.

# Reporting Issues

We use GitHub:Issues for tracking issues with this connector. You can report new issues at this link http://github.com/ullgren/tell-stick-connector/issues.