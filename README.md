# [DropWizard.io](http://www.dropwizard.io/) statsd-reporter-example
Dropwizard.io uses a [ReporterFactory]
(https://github.com/dropwizard/dropwizard/blob/master/dropwizard-metrics/src/main/java/io/dropwizard/metrics/ReporterFactory.java)
as the SPI for plugging in new [ScheduledReporter](https://github.com/dropwizard/metrics/blob/v3.1.2/metrics-core/src/main/java/com/codahale/metrics/ScheduledReporter.java) reporters.

As mentioned in ReporterFactory class we're going to:

* Create a class which implements {@link ReporterFactory}.
* Annotate it with {@code @JsonTypeName} and give it a unique type name.
* Add a {@code META-INF/services/io.dropwizard.metrics.ReporterFactory} file with your implementation's full class name to the class path.

In this example we're going to use [ReadyTalk's](https://github.com/ReadyTalk/metrics-statsd) implementation of a [StatsDReporter](https://github.com/ReadyTalk/metrics-statsd/blob/master/metrics3-statsd/src/main/java/com/readytalk/metrics/StatsDReporter.java).
 


