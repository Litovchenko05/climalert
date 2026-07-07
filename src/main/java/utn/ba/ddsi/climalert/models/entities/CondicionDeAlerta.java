package utn.ba.ddsi.climalert.models.entities;

import java.util.Optional;

public interface CondicionDeAlerta {
  Optional<Alerta> evaluar(Clima clima);
}
