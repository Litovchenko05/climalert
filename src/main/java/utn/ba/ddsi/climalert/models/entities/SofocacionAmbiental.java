package utn.ba.ddsi.climalert.models.entities;

import java.util.Optional;

public class SofocacionAmbiental implements CondicionDeAlerta{
  double TEMPERATURA = 35;
  int HUMEDAD = 70;
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
