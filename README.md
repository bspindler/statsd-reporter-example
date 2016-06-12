# [DropWizard.io](http://www.dropwizard.io/) statsd-reporter-example
Example showing how to integrate a custom statsd-reporter into Dropwizard.io. via the SPI [ReporterFactory]
(https://github.com/dropwizard/dropwizard/blob/master/dropwizard-metrics/src/main/java/io/dropwizard/metrics/ReporterFactory.java).  This is the native way of plugging in new [ScheduledReporter's'](https://github.com/dropwizard/metrics/blob/v3.1.2/metrics-core/src/main/java/com/codahale/metrics/ScheduledReporter.java) 

As mentioned in ReporterFactory class we're going to:

* Create a class which implements [ReporterFactory](https://github.com/dropwizard/dropwizard/blob/master/dropwizard-metrics/src/main/java/io/dropwizard/metrics/ReporterFactory.java) .
* Annotate it with [JsonTypeName](https://github.com/FasterXML/jackson-annotations/blob/master/src/main/java/com/fasterxml/jackson/annotation/JsonTypeName.java) and give it a unique type name.
* Add a `META-INF/services/io.dropwizard.metrics.ReporterFactory` file with your implementation's full class name to the class path.

## The StatsD reporter
In this example we're going to use [ReadyTalk's](https://github.com/ReadyTalk/metrics-statsd) implementation of a [StatsDReporter](https://github.com/ReadyTalk/metrics-statsd/blob/master/metrics3-statsd/src/main/java/com/readytalk/metrics/StatsDReporter.java).

## Adding ReadyTalk's metrics-statsd reporter as a dependency
* add the bintray repository and dependency 

## Adding the StatsDReporterFactory
* the StatsDReporterFactory takes care of building the StatsDReporter 
** and sets the name via the @JsonTypeName annotation, we named it "statsd-reporter-example"

## Adding the io.dropwizard.metrics.ReporterFactory services file
* this file is added in the src/main/resources/META-INF/services directory with our StatsDReporterFactory class defined as the ReporterFactory to add to the Discoverable set of Reporters.

That's it, this can now be built and integrated into your dropwizard.io application. 
[Check out this example to see how.](http://github.com/example-for-integratinthisthing)

