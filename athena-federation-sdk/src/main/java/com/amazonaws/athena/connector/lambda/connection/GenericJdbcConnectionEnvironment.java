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

import com.google.common.collect.ImmutableSet;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GenericJdbcConnectionEnvironment implements ConnectionEnvironment
{
    private static final ImmutableSet<String> HAS_DATABASE = ImmutableSet.of(
            "MYSQL", "REDSHIFT", "POSTGRESQL", "CLOUDERAHIVE", "HORTONSWORKHIVE"
    );

    private static final String POSTGRESQL_FIRST = "postgres";
    private static final String HIVE_FIRST = "hive";
    private static final String HIVE_SECOND = "hive2";
    @Override
    public Map<String, String> generateEnvironment(String connectionType, String secretName, Map<String, String> connectionProperties)
    {
        Map<String, String> env = new HashMap<>();
        String firstPrefix = connectionType.toLowerCase(Locale.ENGLISH);
        String secondPrefix = connectionType.toLowerCase(Locale.ENGLISH);

        if (connectionType.equals("POSTGRESQL")) {
            firstPrefix = POSTGRESQL_FIRST;
        }
        else if (connectionType.equals("CLOUDERAHIVE") || connectionType.equals("HORTONSWORKHIVE")) {
            firstPrefix = HIVE_FIRST;
            secondPrefix = HIVE_SECOND;
        }
        String connectionString = firstPrefix + "://jdbc:" + secondPrefix + "://" + connectionProperties.get("HOST")
                + ":" + connectionProperties.get("PORT") + "/";

        if (HAS_DATABASE.contains(connectionType)) {
            connectionString = connectionString + connectionProperties.get("DATABASE");
        }

        connectionString = connectionString + "?" + connectionProperties.get("JDBC_PARAMS");
        env.put("default", connectionString);

        return env;
    }
}
