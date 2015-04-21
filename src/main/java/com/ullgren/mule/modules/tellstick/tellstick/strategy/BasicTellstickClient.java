package com.ullgren.mule.modules.tellstick.tellstick.strategy;

import java.util.ArrayList;

import net.jstick.api.Device;
import net.jstick.api.DeviceChangeEventListener;
import net.jstick.api.DeviceEventListener;
import net.jstick.api.SensorEventListener;
import net.jstick.api.Tellstick;

public class BasicTellstickClient implements TellstickClient {

	private Tellstick tellstick;

	
	public BasicTellstickClient(boolean debug) {
		this.tellstick = new Tellstick(debug);
	}

	@Override
	public void init() {
		this.tellstick.init();
	}
	
	@Override
	public void close() {
		this.tellstick.close();
		
	}


	@Override
	public void addDeviceChangeEventListener(
			DeviceChangeEventListener deviceChangeEventListener) {
		this.tellstick.addDeviceChangeEventListener(deviceChangeEventListener);
	}


	@Override
	public int sendCmd(int id, String command, Integer repeat) {
		this.tellstick.sendCmd(id, command, repeat);
		return 0;
	}


	@Override
	public int learn(int id) {
		return this.tellstick.learn(id);
	}


	@Override
	public String getLastCmd(int id) {
		return this.tellstick.getLastCmd(id);
	}


	@Override
	public ArrayList<Device> getDevices() {
		return this.tellstick.getDevices();
	}


	@Override
	public void addDeviceEventListener(DeviceEventListener deviceEventListener) {
		this.tellstick.addDeviceEventListener(deviceEventListener);
	}


	@Override
	public void addSensorEventListener(SensorEventListener sensorEventListener) {
		this.tellstick.addSensorEventListener(sensorEventListener);
	}


	@Override
	public int getDeviceIdByName(String device) {
		return this.tellstick.getDeviceIdByName(device);
	}

}
