package utn.ba.ddsi.climalert.clients.weatherapi;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import utn.ba.ddsi.climalert.config.WeatherApiProperties;
import utn.ba.ddsi.climalert.clients.weatherapi.dto.WeatherApiResponse;
import utn.ba.ddsi.climalert.models.entities.Clima;
import utn.ba.ddsi.climalert.services.WeatherProvider;
import utn.ba.ddsi.climalert.utils.DateTimeMapper;
import java.net.URI;
import java.util.Optional;

@Component
public class WeatherApiClient implements WeatherProvider {
  private final RestTemplate restTemplate;
  private final WeatherApiProperties properties;

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
    WeatherApiResponse response = Optional.ofNullable(restTemplate.getForObject(uri, WeatherApiResponse.class))
        .orElseThrow(() -> new RuntimeException("La API de clima devolvió una respuesta vacía"));
    //TODO: Quizás podría tirar un error personalizado
    return new Clima(
        null,
        response.getLocation().getCountry(),
        response.getLocation().getName(),
        DateTimeMapper.toLocalDateTime(response.getLocation().getLocaltime()),
        response.getCurrent().getTemperature(),
        response.getCurrent().getHumidity(),
        false
    );
  }
}
