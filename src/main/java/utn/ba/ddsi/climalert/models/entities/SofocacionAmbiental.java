package utn.ba.ddsi.climalert.models.entities;

import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class SofocacionAmbiental implements CondicionDeAlerta{
  double TEMPERATURA = 35;
  int HUMEDAD = 60;
  String NOMBRE = "Sofocación Ambiental";

  @Override
  public Optional<Alerta> evaluar(Clima clima){
    if (clima.getTemperatura() > TEMPERATURA && clima.getHumedad() > HUMEDAD) {
      return Optional.of(new Alerta(
          null,
          clima.getCiudad(),
          NOMBRE,
          clima.getFechaYHora()
      ));
    } else {
      return Optional.empty();
    }
  }
}
