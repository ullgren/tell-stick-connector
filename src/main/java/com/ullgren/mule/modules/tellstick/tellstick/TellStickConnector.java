/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.ullgren.mule.modules.tellstick.tellstick;

import java.util.ArrayList;

import net.jstick.api.Device;
import net.jstick.api.DeviceChangeEvent;
import net.jstick.api.DeviceChangeEventListener;
import net.jstick.api.DeviceEvent;
import net.jstick.api.DeviceEventListener;
import net.jstick.api.SensorEvent;
import net.jstick.api.SensorEventListener;

import org.mule.api.annotations.ConnectionStrategy;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.Source;
import org.mule.api.annotations.param.Default;
import org.mule.api.callback.SourceCallback;

import com.ullgren.mule.modules.tellstick.tellstick.strategy.ConnectorConnectionStrategy;

/**
 * Anypoint Connector
 *
 * @author Pontus Ullgren
 */
@Connector(name="tell-stick", friendlyName="TellStick")
public class TellStickConnector
{
    @ConnectionStrategy
    ConnectorConnectionStrategy connectionStrategy;

    /*
    public int getNumberOfDevices() {
    	
    }
    
    public int getDeviceId( int i)
    public int getDeviceIdByName( String name)
    public String getName(int id)
    public boolean setName(int id, String name)
    public int learn(int id)
    public String getDeviceType(int id)
    public String getErrorString(int errorCode)
    public int sendRawCommand(String cmd)
    public String getModel(int id)
    public boolean setModel(int id, String model)
    public String getProtocoll(int id)
    public boolean setProtocol(int id, String protocol)
    public ArrayList getControllers()
    public ArrayList getConnectedControllers()
    public ArrayList getDevices()
    public Device getDevice(int id)
    public List getSimpleSensors()
    public ArrayList getSensors()
    public Sensor getSensor(int id)
    public String getDeviceParameter(int id, String name, String defaultValue)
    public boolean setDeviceParameter(int id, String name, String value)
    public int addDevice(DeviceConfig cfgDev)
    public boolean removeDevice(int id)
    public void listenRaw(long time) 
    public void listenDeviceEvents(long time) 
    public void getSupportedMethods(int id)
    public String cmdToString(int cmd)
    public String getJstickVersion()
    */
    /**
     * Send a command to the device
     *
     * {@sample.xml ../../../doc/tell-stick-connector.xml.sample tell-stick:send-command}
     *
     * @param command Command to send. Valid commands are ON, OFF, DIM {dimlevel}, BELL, UP, DOWN, STOP and EXECUTE.
     * @param device Name of the device to send the command to.
     * @param repeat Number of times to repeat the command.
     */
    @Processor
    public void sendCommand(String command, String device, @Default("1") Integer repeat) {
        // TODO: Validate command string
    	
    	int id = getDeviceIdFromString(device);
    	
    	int status = this.connectionStrategy.getTellstick().sendCmd(id, command, repeat);
    	// TODO: Error handler when status is not OK
        return;
    }
    
	/**
     * Issue a learn command for the specific device.
     * 
     * {@sample.xml ../../../doc/tell-stick-connector.xml.sample tell-stick:learn}
     * 
     * @param device Name of the device to learn.
     */
    @Processor
    public void learn(String device) {
    	int id = getDeviceIdFromString(device);
    	int status = this.connectionStrategy.getTellstick().learn(id);
    	// TODO: Error handler when status is not OK
        return;
    }
    
    /**
     * Returns a string with the last know command sent to device.
     * 
     * {@sample.xml ../../../doc/tell-stick-connector.xml.sample tell-stick:get-last-command}
     * 
     * @param device Name of the device to get the last known command for.
     * @return The last know command send to the device. Example "ON", "OFF", "DIM 150" etc
     * @see TellStickConnector#sendCommand(String, String, Integer)
     */
    @Processor
    public String getLastCommand(String device) {
    	int id = getDeviceIdFromString(device);
    	return this.connectionStrategy.getTellstick().getLastCmd(id);
    }
    
    /**
     * Retrieves a list of devices configured in the local telldusd.
     * 
     * {@sample.xml ../../../doc/tell-stick-connector.xml.sample tell-stick:get-devices}
     * 
     * @return a list of devices
     */
    @Processor
    public ArrayList<Device> getDevices() {
    	return this.connectionStrategy.getTellstick().getDevices();
    }
    
    /**
     * Receives a device event from the Telldus Tellstick.
     * 
     * {@sample.xml ../../../doc/tell-stick-connector.xml.sample tell-stick:device-event}
     * 
     * @param callback the {@link SourceCallback} used to propagate the event
     * to the rest of the flow.
     */
    @Source
    public void deviceEvent(final SourceCallback callback) {
    	this.connectionStrategy.getTellstick().addDeviceEventListener(new DeviceEventListener() {
			
			@Override
			public void eventReceived(DeviceEvent event) {
				try {
					callback.process(event);
				} catch (Exception e) {
					// TODO: Handle exception
				}
			}
		});
    }
    
    /**
     * Receives a device change event from the Telldus Tellstick.
     * 
     * {@sample.xml ../../../doc/tell-stick-connector.xml.sample tell-stick:device-change-event}
     * 
     * @param callback the {@link SourceCallback} used to propagate the event
     * to the rest of the flow.
     */
    @Source
    public void deviceChangeEvent(final SourceCallback callback) {
    	this.connectionStrategy.getTellstick().addDeviceChangeEventListener(new DeviceChangeEventListener() {
			
			@Override
			public void eventReceived(DeviceChangeEvent event) {
				try {
					callback.process(event);
				} catch (Exception e) {
					// TODO: Handle exception
				}
			}
		});
    }
    
    /**
     * Receives a sensor event from the Telldus Tellstick.
     * 
     * {@sample.xml ../../../doc/tell-stick-connector.xml.sample tell-stick:sensor-event}
     * 
     * @param callback the {@link SourceCallback} used to propagate the event
     * to the rest of the flow.
     */
    @Source
    public void sensorEvent(final SourceCallback callback) {
    	this.connectionStrategy.getTellstick().addSensorEventListener(new SensorEventListener() {
			
			@Override
			public void eventReceived(SensorEvent event) {
				try {
					callback.process(event);
				} catch (Exception e) {
					// TODO: Handle exception
				}
			}
		});
    }

    public ConnectorConnectionStrategy getConnectionStrategy() {
        return connectionStrategy;
    }

    public void setConnectionStrategy(ConnectorConnectionStrategy connectionStrategy) {
        this.connectionStrategy = connectionStrategy;
    }
    
    private int getDeviceIdFromString(String device) {
		return this.connectionStrategy.getTellstick().getDeviceIdByName(device);
	}

}