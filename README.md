# [DropWizard.io](http://www.dropwizard.io/) statsd-reporter-example
Example showing how to integrate a custom statsd-reporter into Dropwizard.io. via the SPI [ReporterFactory]
(https://github.com/dropwizard/dropwizard/blob/master/dropwizard-metrics/src/main/java/io/dropwizard/metrics/ReporterFactory.java).  This is the native way of plugging in new [ScheduledReporter's'](https://github.com/dropwizard/metrics/blob/v3.1.2/metrics-core/src/main/java/com/codahale/metrics/ScheduledReporter.java) 

As mentioned in ReporterFactory class we're going to:

* Create a class which implements [ReporterFactory](https://github.com/dropwizard/dropwizard/blob/master/dropwizard-metrics/src/main/java/io/dropwizard/metrics/ReporterFactory.java) .
* Annotate it with [@JsonTypeName](https://github.com/FasterXML/jackson-annotations/blob/master/src/main/java/com/fasterxml/jackson/annotation/JsonTypeName.java) and give it a unique type name.
* Add a [`src/main/resources/META-INF/services/io.dropwizard.metrics.ReporterFactory`](https://github.com/bspindler/statsd-reporter-example/blob/master/src/main/resources/META-INF/services/io.dropwizard.metrics.ReporterFactory) file with your implementation's full class name to the class path.

## Integrating the [StatsDReporter](https://github.com/ReadyTalk/metrics-statsd/blob/master/metrics3-statsd/src/main/java/com/readytalk/metrics/StatsDReporter.java) from [ReadyTalk](https://github.com/ReadyTalk/metrics-statsd) 

### Add metrics-statsd reporter as a dependency
* add the [bintray repository](https://github.com/bspindler/statsd-reporter-example/blob/master/build.gradle#L17) 
* add [ready talk dependency ](https://github.com/bspindler/statsd-reporter-example/blob/master/build.gradle#L27)

### Add the StatsDReporterFactory
* [StatsDReporterFactory](https://github.com/bspindler/statsd-reporter-example/blob/master/src/main/java/com/netuitive/io/dropwizard/StatsDReporterFactory.java) takes care of 
    * building the [StatsDReporter](https://github.com/ReadyTalk/metrics-statsd/blob/master/metrics3-statsd/src/main/java/com/readytalk/metrics/StatsDReporter.java) and binding to [MetricRegistry](https://github.com/dropwizard/metrics/blob/3.1-maintenance/metrics-core/src/main/java/com/codahale/metrics/MetricRegistry.java)
    * sets the name via the [@JsonTypeName](https://github.com/FasterXML/jackson-annotations/blob/master/src/main/java/com/fasterxml/jackson/annotation/JsonTypeName.java)  annotation, we named it "statsd-reporter-example"


### Adding the io.dropwizard.metrics.ReporterFactory services file
* added [`src/main/resources/META-INF/services/io.dropwizard.metrics.ReporterFactory`](https://github.com/bspindler/statsd-reporter-example/blob/master/src/main/resources/META-INF/services/io.dropwizard.metrics.ReporterFactory) with our [StatsDReporterFactory](https://github.com/bspindler/statsd-reporter-example/blob/master/src/main/java/com/netuitive/io/dropwizard/StatsDReporterFactory.java) class defined as the [ReporterFactory](https://github.com/dropwizard/dropwizard/blob/master/dropwizard-metrics/src/main/java/io/dropwizard/metrics/ReporterFactory.java).

That's it, this can now be built and integrated into your dropwizard.io application. 
[Check out this example to see how.](http://github.com/example-for-integratinthisthing)

## Build it 
`./gradlew build`

## Install it
`./gradlew install`

## Use it in your own project
add as a dependency: 
### Gradle w/local install 
`compile('com.netuitive.statsd-reporter-example:1.0-SNAPSHOT')`
### Update your config.yml
```
metrics:
  frequency: 1 minute
  reporters:
      - type: statsd-reporter-example
        host: localhost
        port: 8125
```

### Using bintray 
...