package utn.ba.ddsi.climalert.services.impl;

import org.springframework.stereotype.Service;
import utn.ba.ddsi.climalert.models.entities.Clima;
import utn.ba.ddsi.climalert.repositories.ClimaRepository;
import utn.ba.ddsi.climalert.services.ClimaService;
import utn.ba.ddsi.climalert.services.WeatherProvider;

@Service
public class ClimaServiceImpl implements ClimaService {
  WeatherProvider weatherProvider;
  ClimaRepository climaRepository;

  public ClimaServiceImpl(WeatherProvider weatherProvider, ClimaRepository climaRepository) {
    this.weatherProvider = weatherProvider;
    this.climaRepository = climaRepository;
  }

  @Override
  public void obtenerClimaActual(String ciudad) {
    Clima clima = weatherProvider.obtenerClimaActual(ciudad);
    climaRepository.save(clima); // TODO: Quizás poner alguna validación antes (?
  }
}
