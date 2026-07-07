package utn.ba.ddsi.climalert.services;

import org.springframework.stereotype.Component;
import utn.ba.ddsi.climalert.models.entities.Alerta;
import utn.ba.ddsi.climalert.models.entities.Clima;
import utn.ba.ddsi.climalert.models.entities.CondicionDeAlerta;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class EvaluadorClimatico {
  private final List<CondicionDeAlerta> condiciones;

  public EvaluadorClimatico(List<CondicionDeAlerta> condiciones) {
    this.condiciones = condiciones;
  }

  public List<Alerta> evaluar(Clima clima){
    List<Alerta> alertas = new ArrayList<>();
    for (CondicionDeAlerta condicion : condiciones){
      Optional<Alerta> posibleAlerta = condicion.evaluar(clima);
      posibleAlerta.ifPresent(alertas::add);
    }
    if (alertas.isEmpty()) {
      return null;
    } else {
      return alertas;
    }
  }
}
