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
package com.amazonaws.athena.connector.lambda.connection;

import java.util.HashMap;
import java.util.Map;

public class MySqlEnvironmentProperties extends EnvironmentProperties
{
    @Override
    public Map<String, String> generateMissingProperties(Map<String, String> connectionProperties)
    {
        HashMap<String, String> missingProperties = new HashMap<>();

        // now construct jdbc string
        String connectionString = "mysql://jdbc:mysql://" + connectionProperties.get("HOST")
                + ":" + connectionProperties.get("PORT") + "/" + connectionProperties.get("DATABASE")
                + "?" + connectionProperties.get("JDBC_PARAMS");

        if (connectionProperties.containsKey(SECRET_NAME)) {
            connectionString = connectionString + "${" + connectionProperties.get(SECRET_NAME) + "}";
        }
        missingProperties.put("default", connectionString);
        return missingProperties;
    }
}
