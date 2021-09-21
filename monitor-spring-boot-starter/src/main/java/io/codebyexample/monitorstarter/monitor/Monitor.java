package io.codebyexample.monitorstarter.monitor;

import io.codebyexample.monitorstarter.aop.Metric;
import io.micrometer.core.instrument.Timer;
import java.util.Optional;

/**
 * @author huypva
 */
public interface Monitor {

  void record(Metric monitored, Timer.Sample timer, Optional<Throwable> err);

}
