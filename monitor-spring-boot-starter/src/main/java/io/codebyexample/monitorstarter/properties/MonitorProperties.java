package io.codebyexample.monitorstarter.properties;

import java.time.Duration;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huypva
 */
@Getter
@Setter
@ToString
@ConfigurationProperties("monitor")
public class MonitorProperties {

  private boolean enabled = false;
  private Duration[] sla;
}
