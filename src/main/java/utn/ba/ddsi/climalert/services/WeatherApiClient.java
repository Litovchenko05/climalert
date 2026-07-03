package utn.ba.ddsi.climalert.services;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import utn.ba.ddsi.climalert.config.WeatherApiProperties;
import utn.ba.ddsi.climalert.services.dto.Clima;
import java.net.URI;

@Component
public class WeatherApiClient implements WeatherProvider{
  private RestTemplate restTemplate;
  private WeatherApiProperties properties;

  public WeatherApiClient(RestTemplate restTemplate, WeatherApiProperties properties) {
    this.restTemplate = restTemplate;
    this.properties = properties;
  }

  public Clima obtenerClimaActual(String ciudad){
    URI uri =
        UriComponentsBuilder.fromUriString(properties.getBaseUrl())
            .path("/current.json")
            .queryParam("key", properties.getKey())
            .queryParam("q", ciudad)
            .build()
            .toUri();
    return restTemplate.getForObject(uri, Clima.class);
  }
}
