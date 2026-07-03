package utn.ba.ddsi.climalert.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weather-api")
@Data
public class WeatherApiProperties {
  private String baseUrl;
  private String key;
}
