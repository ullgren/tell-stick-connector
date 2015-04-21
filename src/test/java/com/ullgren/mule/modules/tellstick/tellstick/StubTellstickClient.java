package com.ullgren.mule.modules.tellstick.tellstick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.jstick.api.Device;
import net.jstick.api.DeviceChangeEvent;
import net.jstick.api.DeviceChangeEventListener;
import net.jstick.api.DeviceEvent;
import net.jstick.api.DeviceEventListener;
import net.jstick.api.SensorEvent;
import net.jstick.api.SensorEventListener;

import com.ullgren.mule.modules.tellstick.tellstick.strategy.TellstickClient;

public class StubTellstickClient implements TellstickClient {

	
//	private List<RawEventListener> rawEventListeners = new ArrayList<RawEventListener>();
	
	private List<DeviceEventListener> deviceEventListeners = new ArrayList<DeviceEventListener>();
	
	private List<DeviceChangeEventListener> deviceChangeEventListeners = new ArrayList<DeviceChangeEventListener>();
	
	private List<SensorEventListener>  sensorEventListeners = new ArrayList<SensorEventListener>();
	
	private HashMap<Integer, String> lastCommands = new HashMap<>();
	
	
	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDeviceChangeEventListener(
			DeviceChangeEventListener deviceChangeEventListener) {
		deviceChangeEventListeners.add(deviceChangeEventListener);
	}

	@Override
	public int sendCmd(int id, String command, Integer repeat) {
		this.lastCommands.put(id, command);
		return 0;
	}

	@Override
	public int learn(int id) {
		return 0;
	}

	@Override
	public String getLastCmd(int id) {
		// TODO Auto-generated method stub
		return this.lastCommands.get(id);
	}

	@Override
	public ArrayList<Device> getDevices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDeviceEventListener(DeviceEventListener deviceEventListener) {
		deviceEventListeners.add(deviceEventListener);
	}

	@Override
	public void addSensorEventListener(SensorEventListener sensorEventListener) {
		sensorEventListeners.add(sensorEventListener);
	}

	@Override
	public int getDeviceIdByName(String device) {
		switch(device) {
		case "My device":
			return 1;
		default:
			return 0;
		}
	}

	public void triggerDeviceEvent(DeviceEvent event) {
		for(DeviceEventListener listner : deviceEventListeners) {
			listner.eventReceived(event);
		}
	}
	
	public void triggerDeviceChangeEvent(DeviceChangeEvent event) {
		for(DeviceChangeEventListener listner : deviceChangeEventListeners) {
			listner.eventReceived(event);
		}
	}
	
	public void triggerSensorEvent(SensorEvent event) {
		for(SensorEventListener listner : sensorEventListeners) {
			listner.eventReceived(event);
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
