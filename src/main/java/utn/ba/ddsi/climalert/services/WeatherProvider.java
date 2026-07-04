package utn.ba.ddsi.climalert.services;

import utn.ba.ddsi.climalert.models.entities.Clima;

public interface WeatherProvider {
  Clima obtenerClimaActual(String ciudad);
}
