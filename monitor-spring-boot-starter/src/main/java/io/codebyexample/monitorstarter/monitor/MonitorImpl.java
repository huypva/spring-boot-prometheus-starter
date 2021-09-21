package io.codebyexample.monitorstarter.monitor;

import io.codebyexample.monitorstarter.aop.Metric;
import io.codebyexample.monitorstarter.errortranslator.ErrorTranslator;
import io.codebyexample.monitorstarter.properties.MonitorProperties;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;

/**
 * @author huypva
 */
@Log4j2
@Getter
@AllArgsConstructor
public class MonitorImpl implements Monitor {

  private ApplicationContext applicationContext;

  private ErrorTranslator errorTranslator;

  private MonitorProperties monitorProperties;

  private MeterRegistry registry;

  public MonitorImpl(ApplicationContext applicationContext,
      MonitorProperties monitorProperties, ErrorTranslator errorCodeTranslator, MeterRegistry registry) {
    this.applicationContext = applicationContext;
    this.monitorProperties = monitorProperties;
    this.errorTranslator = errorCodeTranslator;
    this.registry = registry;
  }

  @Override
  public void record(Metric metric, Timer.Sample timer, Optional<Throwable> err) {
    String baseMetricName = metric.value().isEmpty() ? "default_metric" : metric.value();

    if (metric.histogram()) {
      recordHistogram(baseMetricName, metric, timer, err);
    } else {
      recordCounter(baseMetricName, metric, err);
    }
  }

  public void recordHistogram(String metricName, Metric metric, Timer.Sample timer, Optional<Throwable> err) {
    try {
      timer.stop(Timer
          .builder(metricName)
          .description(metric.description() == null ? "" : metric.description())
          .tags(metric.extraTags())
          .tag("error", errorTranslator.translate(err))
          .sla(monitorProperties.getSla())
          .register(getRegistry()));
    } catch (Exception ex) {
      log.error("Error while recording performance metrics", ex);
    }
  }

  public void recordCounter(String metricName, Metric metric, Optional<Throwable> err) {
    try {
      Counter.builder(metricName)
          .description(metric.description() == null ? "" : metric.description())
          .tags(metric.extraTags())
          .tag("error", errorTranslator.translate(err))
          .register(getRegistry())
          .increment();
    } catch (Exception ex) {
      log.error("Error while recording error metrics", ex);
    }
  }

}
