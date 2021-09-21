package io.codebyexample.monitor.example.core.usercase;

import io.codebyexample.monitorstarter.aop.Metric;
import java.util.Random;
import org.springframework.stereotype.Component;

/**
 * @author huypva
 */
@Component
public class GreetImpl implements Greet {

  @Metric(value = "greetsummary")
  @Override
  public String summary() {
    return "Hello summary";
  }

  @Metric(value = "greethistogram", histogram = true)
  @Override
  public String histogram() {
    Random random = new Random();
    if (random.nextInt(100) < 20) {
      throw new RuntimeException("Test fail");
    }
    return "Hello histogram";
  }
}
