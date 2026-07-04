package utn.ba.ddsi.climalert.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import utn.ba.ddsi.climalert.services.ClimaService;

@Component
public class ClimaScheduler {
  private final ClimaService climaService;

  public ClimaScheduler(ClimaService climaService) {
    this.climaService = climaService;
  }

  @Scheduled(fixedRate = 300000)
  public void obtenerClimaActualDeBuenosAires(){
    climaService.obtenerClimaActual("Buenos Aires");
  }
}
