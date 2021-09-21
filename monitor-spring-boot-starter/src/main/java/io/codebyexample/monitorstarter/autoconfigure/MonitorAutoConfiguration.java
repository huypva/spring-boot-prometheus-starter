package io.codebyexample.monitorstarter.autoconfigure;

import io.codebyexample.monitorstarter.aop.MetricAspectJ;
import io.codebyexample.monitorstarter.errortranslator.ErrorTranslator;
import io.codebyexample.monitorstarter.errortranslator.ErrorTranslatorImpl;
import io.codebyexample.monitorstarter.monitor.Monitor;
import io.codebyexample.monitorstarter.monitor.MonitorImpl;
import io.codebyexample.monitorstarter.properties.MonitorProperties;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huypva
 */
@Log4j2
@Configuration
@ConditionalOnProperty(name = "enabled", prefix = "monitor", havingValue = "true")
@EnableConfigurationProperties(MonitorProperties.class)
public class MonitorAutoConfiguration {

  @Value("${spring.application.name}")
  private String applicationName;

  @ConditionalOnMissingBean
  @Bean
  public Monitor monitor(ApplicationContext applicationContext,
      MonitorProperties monitorProperties, MeterRegistry meterRegistry,
      ErrorTranslator errorTranslator) {
    return new MonitorImpl(applicationContext, monitorProperties, errorTranslator, meterRegistry);
  }

  @ConditionalOnMissingBean
  @Bean
  MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
    return registry -> registry.config().commonTags("application", applicationName);
  }

  @ConditionalOnMissingBean
  @Bean
  ErrorTranslator errorTranslator() {
    return new ErrorTranslatorImpl();
  }

  @ConditionalOnMissingBean
  @Bean
  public MetricAspectJ metricAspectJ(Monitor monitor) {
    return new MetricAspectJ(monitor);
  }

}
