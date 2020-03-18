/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ververica.field.dynamicrules.functions;

import com.ververica.field.sources.BaseGenerator;
import java.util.SplittableRandom;

public class RulesStaticJsonGenerator extends BaseGenerator<String> {
  // Imported from com.ververica.demo.backend.datasource.RulesBootstrapper in the webapp project:
  private static final String[] RULES =
      new String[] {
        "{\"ruleId\":\"1\","
            + "\"aggregateFieldName\":\"paymentAmount\","
            + "\"aggregatorFunctionType\":\"SUM\","
            + "\"groupingKeyNames\":[\"payeeId\", \"beneficiaryId\"],"
            + "\"limit\":\"20000000\","
            + "\"limitOperatorType\":\"GREATER\","
            + "\"ruleState\":\"ACTIVE\","
            + "\"windowMinutes\":\"43200\"}",
        "{\"ruleId\":\"2\","
            + "\"aggregateFieldName\":\"COUNT_FLINK\","
            + "\"aggregatorFunctionType\":\"SUM\","
            + "\"groupingKeyNames\":[\"paymentType\"],"
            + "\"limit\":\"300\","
            + "\"limitOperatorType\":\"LESS\","
            + "\"ruleState\":\"PAUSE\","
            + "\"windowMinutes\":\"1440\"}",
        "{\"ruleId\":\"3\","
            + "\"aggregateFieldName\":\"paymentAmount\","
            + "\"aggregatorFunctionType\":\"SUM\","
            + "\"groupingKeyNames\":[\"beneficiaryId\"],"
            + "\"limit\":\"10000000\","
            + "\"limitOperatorType\":\"GREATER_EQUAL\","
            + "\"ruleState\":\"ACTIVE\","
            + "\"windowMinutes\":\"1440\"}",
        "{\"ruleId\":\"4\","
            + "\"aggregateFieldName\":\"COUNT_WITH_RESET_FLINK\","
            + "\"aggregatorFunctionType\":\"SUM\","
            + "\"groupingKeyNames\":[\"paymentType\"],"
            + "\"limit\":\"100\","
            + "\"limitOperatorType\":\"GREATER_EQUAL\","
            + "\"ruleState\":\"ACTIVE\","
            + "\"windowMinutes\":\"1440\"}",
      };

  @Override
  public String randomEvent(SplittableRandom rnd, long id) {
    if (id >= 0 && id < RULES.length) {
      return RULES[(int) id];
    } else {
      return null;
    }
  }
}
