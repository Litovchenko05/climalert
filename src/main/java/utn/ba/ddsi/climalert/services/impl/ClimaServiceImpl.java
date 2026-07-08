package utn.ba.ddsi.climalert.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import utn.ba.ddsi.climalert.models.entities.Alerta;
import utn.ba.ddsi.climalert.models.entities.Clima;
import utn.ba.ddsi.climalert.models.events.AlertaGeneradaEvent;
import utn.ba.ddsi.climalert.repositories.AlertaRepository;
import utn.ba.ddsi.climalert.repositories.ClimaRepository;
import utn.ba.ddsi.climalert.services.ClimaService;
import utn.ba.ddsi.climalert.services.EvaluadorClimatico;
import utn.ba.ddsi.climalert.services.WeatherProvider;
import java.util.List;

@AllArgsConstructor
@Service
public class ClimaServiceImpl implements ClimaService {
  private final WeatherProvider weatherProvider;
  private final ClimaRepository climaRepository;
  private final AlertaRepository alertaRepository;
  private final EvaluadorClimatico evaluadorClimatico;
  private final ApplicationEventPublisher eventPublisher;


  @Override
  public void obtenerClimaActual(String ciudad) {
    Clima clima = weatherProvider.obtenerClimaActual(ciudad);
    climaRepository.save(clima); // TODO: Quizás poner alguna validación antes (?
  }

  @Override
  public void analizarUltimoClima(String ciudad){
    Clima clima = climaRepository.findUltimoClima(ciudad);
    if (clima == null || clima.isAnalizado()) {
      return;
    }
    List<Alerta> alertas = evaluadorClimatico.evaluar(clima);
    if (alertas != null) {
      alertas.forEach(alerta -> {
        alertaRepository.save(alerta);
        eventPublisher.publishEvent(new AlertaGeneradaEvent(alerta));
      });
    }
  }
}
