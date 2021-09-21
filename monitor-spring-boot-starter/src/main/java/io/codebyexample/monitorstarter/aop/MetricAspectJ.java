package io.codebyexample.monitorstarter.aop;

import io.codebyexample.monitorstarter.monitor.Monitor;
import io.micrometer.core.instrument.Timer;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author huypva
 */
@Slf4j
@EnableAspectJAutoProxy
@Aspect
@AllArgsConstructor
public class MetricAspectJ {

  @Autowired
  private Monitor monitor;

  @Pointcut("@annotation(metric)")
  public void annotatedMetric(Metric metric) {
  }

  @Around("annotatedMetric(metric)")
  public Object around(ProceedingJoinPoint joinPoint, Metric metric) throws Throwable {
    log.info("Process metric annotation 1.2");
    Timer.Sample timer = Timer.start();
    Optional<Throwable> err = Optional.empty();

    try {
      return joinPoint.proceed();
    } catch (Throwable e) {
      err = Optional.of(e);
      throw e;
    } finally {
      monitor.record(metric, timer, err);
    }

  }
}
