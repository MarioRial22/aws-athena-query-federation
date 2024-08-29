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

import java.util.Map;

public interface ConnectionEnvironment
{
    /**
     * Generates necessary connection environment variables based off of glue properties
     *
     * @param secretName name of secret where credentials for database are stored
     * @param connectionProperties map of connection properties such as HOST, PORT, etc.
     * @return Map of keys to constructed properties
     */
    Map<String, String> generateEnvironment(String connectionType, String secretName, Map<String, String> connectionProperties);
}
