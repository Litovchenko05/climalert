package utn.ba.ddsi.climalert.repositories;

import utn.ba.ddsi.climalert.models.entities.Clima;

public interface ClimaRepository {
  Clima save(Clima clima);
  void delete(Clima clima);
}
