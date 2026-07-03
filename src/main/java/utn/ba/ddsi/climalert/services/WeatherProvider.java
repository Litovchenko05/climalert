package utn.ba.ddsi.climalert.services;

import utn.ba.ddsi.climalert.services.dto.Clima;

public interface WeatherProvider {
  Clima obtenerClimaActual(String ciudad);
}
