package io.codebyexample.monitor.example.entrypoint;

import io.codebyexample.monitor.example.core.usercase.Greet;
import io.codebyexample.monitorstarter.properties.MonitorProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huypva
 */
@Slf4j
@RestController
public class GreetingController {

  @Autowired
  private Greet greet;

  @GetMapping("/greet/summary")
  public String summary() {
    return greet.summary();
  }

  @GetMapping("/greet/histogram")
  public String histogram() {
    return greet.histogram();
  }
}
