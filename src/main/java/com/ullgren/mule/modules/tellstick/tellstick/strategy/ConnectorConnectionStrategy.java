/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.ullgren.mule.modules.tellstick.tellstick.strategy;

import net.jstick.api.Tellstick;

import org.mule.api.ConnectionException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;

/**
 * Configuration type Strategy
 *
 * @author MuleSoft, Inc.
 */
@ConnectionManagement(configElementName = "config", friendlyName = "TellStick Connector Configuration")
public class ConnectorConnectionStrategy
{
	
	private Tellstick tellstick;
	
	private Integer connectionNumber;
	
    /**
     * Configurable
     */
    @Configurable
    @Default("false")
    private boolean debug;

    /**
     * Set strategy property
     *
     * @param myStrategyProperty my strategy property
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * Get property
     */
    public boolean getDebug() {
        return this.debug;
    }
    
    @Connect
    @TestConnectivity
    public void connect(@ConnectionKey Integer connectionNumber) throws ConnectionException  {
    	this.tellstick = new Tellstick(this.debug);
    	
    	this.connectionNumber = connectionNumber;
    }
    
    @Disconnect
    public void disconnect() {
    	if ( this.tellstick != null ) {
    		this.tellstick.close();
    		this.tellstick = null;
    	}
    	return;
    }
    
    @ValidateConnection
    public boolean isConnected() {
        if ( this.tellstick != null ) {
        	return true;
        } else {
        	return false;
        }
    }
  
    @ConnectionIdentifier
    public String connectionId() {
        return this.connectionNumber.toString();
    }
    
    public Tellstick getTellstick() {
		return tellstick;
	}

}