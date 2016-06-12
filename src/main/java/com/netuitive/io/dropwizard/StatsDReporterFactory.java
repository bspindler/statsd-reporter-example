/*
 * Copyright (c) ${year} ${name} <${email}> All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Netuitive, Inc. and certain third parties ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Netuitive.
 *
 * NETUITIVE MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE,
 * OR NON-INFRINGEMENT. NETUITIVE SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */

package com.netuitive.io.dropwizard;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.readytalk.metrics.StatsDReporter;
import io.dropwizard.metrics.BaseReporterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;

/**
 *
 * Created by bspindler on 6/10/16.
 */
@JsonTypeName("statsd-reporter-example")
public class StatsDReporterFactory extends BaseReporterFactory {

    private static final Logger LOG = LoggerFactory.getLogger(StatsDReporterFactory.class);

    @NotNull
    private String host;

    @JsonProperty
    public String getHost() {
        return host;
    }

    @JsonProperty
    public void setHost(String host) {
        this.host = host;
    }

    @NotNull
    private int port;

    @JsonProperty
    public int getPort() { return port; }

    @JsonProperty
    public void setPort(int port) { this.port = port; }

    /**
     * Configures and builds a {@link ScheduledReporter} instance for the given registry.
     *
     * @param registry the metrics registry to report metrics from.
     * @return a reporter configured for the given metrics registry.
     */
    @Override
    public ScheduledReporter build(MetricRegistry registry) {

        StatsDReporter.Builder builder
                = StatsDReporter.forRegistry(registry)
                                .convertDurationsTo(getDurationUnit())
                                .convertRatesTo(getRateUnit())
                                .filter(getFilter());

        LOG.info("StatsDReporterFactory built with host: {}, port: {}", getHost(), getPort());

        return builder.build(getHost(), getPort());
    }
}
