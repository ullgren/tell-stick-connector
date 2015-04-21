package com.ullgren.mule.modules.tellstick.tellstick.strategy;

import java.util.ArrayList;

import net.jstick.api.Device;
import net.jstick.api.DeviceChangeEventListener;
import net.jstick.api.DeviceEventListener;
import net.jstick.api.SensorEventListener;

public interface TellstickClient {

	public void init();
	
	void close();

	void addDeviceChangeEventListener(
			DeviceChangeEventListener deviceChangeEventListener);

	int sendCmd(int id, String command, Integer repeat);

	int learn(int id);

	String getLastCmd(int id);

	ArrayList<Device> getDevices();

	void addDeviceEventListener(DeviceEventListener deviceEventListener);

	void addSensorEventListener(SensorEventListener sensorEventListener);

	int getDeviceIdByName(String device);

    
}
