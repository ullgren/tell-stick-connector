/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.ullgren.mule.modules.tellstick.tellstick.strategy;

import org.mule.api.ConnectionException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.display.FriendlyName;
import org.mule.api.annotations.display.Placement;
import org.mule.api.annotations.display.Summary;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;

/**
 * Configuration type Strategy
 *
 * @author Pontus Ullgren
 */
@ConnectionManagement(configElementName = "config", 
	friendlyName = "TellStick Connector Configuration")
public class ConnectorConnectionStrategy
{

	@Configurable
	@Optional 
	@Placement(tab="Development Settings", group="Tellstick Client Settings") 
	@FriendlyName("Tellstick Client reference") 
	@Summary("Developer provided reference to a TellstickClient bean. This option is usually only used for testing or development purposes.")
	private TellstickClient client;
	
	private Integer connectionNumber;
	
    @Configurable
    @Default("false")
    @FriendlyName("Enable JNA Debug")
    @Summary("Enabled JNA Debug logging")
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
    public void connect(@ConnectionKey @Summary("This values is used by Mule ESB to keep track of the connector. Simply use a application unique number.") Integer connectionNumber) throws ConnectionException  {
    	if ( this.client == null ) {
    		this.client = new BasicTellstickClient(this.debug);
    	}
    	this.client.init();
    	this.connectionNumber = connectionNumber;
    }
    
    @Disconnect
    public void disconnect() {
    	if ( this.client != null ) {
    		this.client.close();
    		this.client = null;
    	}
    	return;
    }
    
    @ValidateConnection
    public boolean isConnected() {
        if ( this.client != null ) {
        	return true;
        } else {
        	return false;
        }
    }
  
    @ConnectionIdentifier
    public String connectionId() {
        return this.connectionNumber.toString();
    }
    
    public void setClient(TellstickClient client) {
		this.client = client;
	}
    
    public TellstickClient getClient() {
		return client;
	}

}