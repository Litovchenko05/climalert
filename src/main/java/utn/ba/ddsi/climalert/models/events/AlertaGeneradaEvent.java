package utn.ba.ddsi.climalert.models.events;

import lombok.Getter;
import utn.ba.ddsi.climalert.models.entities.Alerta;

@Getter
public class AlertaGeneradaEvent {
  private final Alerta alerta;

  public AlertaGeneradaEvent(Alerta alerta) {
    this.alerta = alerta;
  }
}
