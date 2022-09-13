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

package com.ververica.field.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.flink.api.java.utils.ParameterTool;

public class Parameters {

  private final ParameterTool tool;

  public Parameters(ParameterTool tool) {
    this.tool = tool;
  }

  <T> T getOrDefault(Param<T> param) {
    if (!tool.has(param.getName())) {
      return param.getDefaultValue();
    }
    Object value;
    if (param.getType() == Integer.class) {
      value = tool.getInt(param.getName());
    } else if (param.getType() == Long.class) {
      value = tool.getLong(param.getName());
    } else if (param.getType() == Double.class) {
      value = tool.getDouble(param.getName());
    } else if (param.getType() == Boolean.class) {
      value = tool.getBoolean(param.getName());
    } else {
      value = tool.get(param.getName());
    }
    return param.getType().cast(value);
  }

  public static Parameters fromArgs(String[] args) {
    ParameterTool tool = ParameterTool.fromArgs(args);
    return new Parameters(tool);
  }

  // Kafka:
  public static final Param<String> KAFKA_HOST = Param.string("kafka-host", "localhost");
  public static final Param<Integer> KAFKA_PORT = Param.integer("kafka-port", 9092);

  public static final Param<String> DATA_TOPIC = Param.string("data-topic", "livetransactions");
  public static final Param<String> ALERTS_TOPIC = Param.string("alerts-topic", "alerts");
  public static final Param<String> RULES_TOPIC = Param.string("rules-topic", "rules");
  public static final Param<String> LATENCY_TOPIC = Param.string("latency-topic", "latency");
  public static final Param<String> RULES_EXPORT_TOPIC =
      Param.string("current-rules-topic", "current-rules");

  public static final Param<String> OFFSET = Param.string("offset", "latest");

  // GCP PubSub:
  public static final Param<String> GCP_PROJECT_NAME = Param.string("gcp-project", "da-fe-212612");
  public static final Param<String> GCP_PUBSUB_RULES_SUBSCRIPTION =
      Param.string("pubsub-rules", "rules-demo");
  public static final Param<String> GCP_PUBSUB_ALERTS_SUBSCRIPTION =
      Param.string("pubsub-alerts", "alerts-demo");
  public static final Param<String> GCP_PUBSUB_LATENCY_SUBSCRIPTION =
      Param.string("pubsub-latency", "latency-demo");
  public static final Param<String> GCP_PUBSUB_RULES_EXPORT_SUBSCRIPTION =
      Param.string("pubsub-rules-export", "current-rules-demo");

  // Socket
  public static final Param<Integer> SOCKET_PORT = Param.integer("pubsub-rules-export", 9999);

  // General:
  public static final Param<Integer> DEFAULT_PARALLELISM = Param.integer("parallelism", -1);
  //    source/sink types: kafka / pubsub / socket
  public static final Param<String> RULES_SOURCE = Param.string("rules-source", "SOCKET");
  public static final Param<String> TRANSACTIONS_SOURCE = Param.string("data-source", "GENERATOR");
  public static final Param<String> ALERTS_SINK = Param.string("alerts-sink", "STDOUT");
  public static final Param<String> LATENCY_SINK = Param.string("latency-sink", "STDOUT");
  public static final Param<String> RULES_EXPORT_SINK = Param.string("rules-export-sink", "STDOUT");

  public static final Param<Integer> RECORDS_PER_SECOND = Param.integer("records-per-second", 2);

  public static final String LOCAL_MODE_DISABLE_WEB_UI = "-1";

  /**
   * If specified, the port for the web UI or {@link #LOCAL_MODE_DISABLE_WEB_UI} to disable it.
   * Accepts
   *
   * <ul>
   *   <li>a list of ports (“50100,50101”),
   *   <li>ranges (“50100-50200”), or
   *   <li>a combination of both, or
   *   <li>"0" to let the system choose a free port
   * </ul>
   *
   * <p>The chosen port will be shown in the logs in a line like this:
   *
   * <pre>
   *  12:11:04.062 [main] INFO o.a.f.r.d.DispatcherRestEndpoint - Web frontend listening at http://localhost:8081.
   * </pre>
   */
  public static final Param<String> LOCAL_EXECUTION =
      Param.string("local", LOCAL_MODE_DISABLE_WEB_UI);

  public static final Param<Integer> SOURCE_PARALLELISM = Param.integer("source-parallelism", 2);
  public static final Param<Integer> SINK_PARALLELISM = Param.integer("sink-parallelism", 1);
  public static final Param<Integer> CHECKPOINT_INTERVAL =
      Param.integer("checkpoint-interval", 60_000_0);
  public static final Param<Integer> MIN_PAUSE_BETWEEN_CHECKPOINTS =
      Param.integer("min-pause-btwn-checkpoints", 60_000_0);
  public static final Param<Integer> OUT_OF_ORDERNESS = Param.integer("out-of-orderdness", 500);

  //  List<Param> list = Arrays.asList(new String[]{"foo", "bar"});

  public static final List<Param<String>> STRING_PARAMS =
      Arrays.asList(
          LOCAL_EXECUTION,
          KAFKA_HOST,
          DATA_TOPIC,
          ALERTS_TOPIC,
          RULES_TOPIC,
          LATENCY_TOPIC,
          RULES_EXPORT_TOPIC,
          OFFSET,
          GCP_PROJECT_NAME,
          GCP_PUBSUB_RULES_SUBSCRIPTION,
          GCP_PUBSUB_ALERTS_SUBSCRIPTION,
          GCP_PUBSUB_LATENCY_SUBSCRIPTION,
          GCP_PUBSUB_RULES_EXPORT_SUBSCRIPTION,
          RULES_SOURCE,
          TRANSACTIONS_SOURCE,
          ALERTS_SINK,
          LATENCY_SINK,
          RULES_EXPORT_SINK);

  public static final List<Param<Integer>> INT_PARAMS =
      Arrays.asList(
          DEFAULT_PARALLELISM,
          KAFKA_PORT,
          SOCKET_PORT,
          RECORDS_PER_SECOND,
          SOURCE_PARALLELISM,
          SINK_PARALLELISM,
          CHECKPOINT_INTERVAL,
          MIN_PAUSE_BETWEEN_CHECKPOINTS,
          OUT_OF_ORDERNESS);

  public static final List<Param<Boolean>> BOOL_PARAMS = Collections.emptyList();
}
