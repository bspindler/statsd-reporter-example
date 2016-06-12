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

package com.netuitive.io.dropwizard;import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.readytalk.metrics.StatsDReporter;
import io.dropwizard.metrics.BaseReporterFactory;

/**
 * Created by bspindler on 6/10/16.
 */
@JsonTypeName("statsd-reporter-example")
public class StatsDReporterFactory extends BaseReporterFactory {

    /**
     * Configures and builds a {@link ScheduledReporter} instance for the given registry.
     *
     * @param registry the metrics registry to report metrics from.
     * @return a reporter configured for the given metrics registry.
     */
    @Override
    public ScheduledReporter build(MetricRegistry registry) {
        StatsDReporter statsDReporter
                = StatsDReporter.forRegistry(registry)
                                .filter(getFilter())
                                .build("localhost", 8125);

        return statsDReporter;
    }
}
