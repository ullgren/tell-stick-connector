/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.ullgren.mule.modules.tellstick.tellstick;

import org.mule.modules.tests.ConnectorTestCase;
import org.junit.Assert;
import org.junit.Test;

public class TellStickConnectorTest extends ConnectorTestCase {
    
    @Override
    protected String getConfigResources() {
        return "tell-stick-config.xml";
    }

    @Test
    public void testSendCommandFlow() throws Exception {
    	StubTellstickClient client = muleContext.getRegistry().get("stubTellstick");
    	runFlowWithPayloadAndExpect("testSendCommandFlow", "Another string", "My device");
    	// Verify that the tellstick client has been called for device "My device" (id == 1)
        Assert.assertEquals("ON", client.getLastCmd(1));
    }
}
