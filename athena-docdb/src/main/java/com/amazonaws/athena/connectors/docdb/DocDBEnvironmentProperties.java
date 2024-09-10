/*-
 * #%L
 * Amazon Athena Query Federation SDK
 * %%
 * Copyright (C) 2019 - 2024 Amazon Web Services
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.amazonaws.athena.connectors.docdb;

import com.amazonaws.athena.connector.lambda.EnvironmentProperties;

import java.util.HashMap;
import java.util.Map;

public class DocDBEnvironmentProperties extends EnvironmentProperties
{
    private static final String JDBC_PARAMS = "JDBC_PARAMS";
    private static final String DEFAULT_DOCDB = "default_docdb";
    @Override
    public Map<String, String> connectionPropertiesToEnvironment(Map<String, String> connectionProperties)
    {
        Map<String, String> environment = new HashMap<>();

        String connectionString = "mongodb://${" + connectionProperties.get(SECRET_NAME) + "}@"
                + connectionProperties.get("HOST") + connectionProperties.get("PORT") + "/?"
                + connectionProperties.getOrDefault("JDBC_PARAMS", "");
        environment.put(DEFAULT_DOCDB, connectionString);
        return environment;
    }
}
