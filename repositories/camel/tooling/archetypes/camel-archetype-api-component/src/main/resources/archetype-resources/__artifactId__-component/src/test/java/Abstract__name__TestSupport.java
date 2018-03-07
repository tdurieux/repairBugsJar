## ------------------------------------------------------------------------
## Licensed to the Apache Software Foundation (ASF) under one or more
## contributor license agreements.  See the NOTICE file distributed with
## this work for additional information regarding copyright ownership.
## The ASF licenses this file to You under the Apache License, Version 2.0
## (the "License"); you may not use this file except in compliance with
## the License.  You may obtain a copy of the License at
##
## http://www.apache.org/licenses/LICENSE-2.0
##
## Unless required by applicable law or agreed to in writing, software
## distributed under the License is distributed on an "AS IS" BASIS,
## WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
## See the License for the specific language governing permissions and
## limitations under the License.
## ------------------------------------------------------------------------
package ${package};

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.util.IntrospectionSupport;

/**
 * Abstract base class for ${name} Integration tests generated by Camel API component maven plugin.
 */
public class Abstract${name}TestSupport extends CamelTestSupport {

    private static final String TEST_OPTIONS_PROPERTIES = "/test-options.properties";

    @Override
    protected CamelContext createCamelContext() throws Exception {

        final CamelContext context = super.createCamelContext();

        // read ${name} component configuration from TEST_OPTIONS_PROPERTIES
        final Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(TEST_OPTIONS_PROPERTIES));
        } catch (Exception e) {
            throw new IOException(String.format("%s could not be loaded: %s", TEST_OPTIONS_PROPERTIES, e.getMessage()),
                e);
        }

        Map<String, Object> options = new HashMap<String, Object>();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            options.put(entry.getKey().toString(), entry.getValue());
        }

        final ${name}Configuration configuration = new ${name}Configuration();
        IntrospectionSupport.setProperties(configuration, options);

        // add ${name}Component to Camel context
        final ${name}Component component = new ${name}Component(context);
        component.setConfiguration(configuration);
        context.addComponent("${scheme}", component);

        return context;
    }

    @Override
    public boolean isCreateCamelContextPerClass() {
        // only create the context once for this class
        return true;
    }

    @SuppressWarnings("unchecked")
    protected <T> T requestBodyAndHeaders(String endpointUri, Object body, Map<String, Object> headers)
        throws CamelExecutionException {
        return (T) template().requestBodyAndHeaders(endpointUri, body, headers);
    }

    @SuppressWarnings("unchecked")
    protected <T> T requestBody(String endpoint, Object body) throws CamelExecutionException {
        return (T) template().requestBody(endpoint, body);
    }
}
