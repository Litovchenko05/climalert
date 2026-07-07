package utn.ba.ddsi.climalert.repositories;

import utn.ba.ddsi.climalert.models.entities.Clima;
import java.util.List;

public interface ClimaRepository {
  Clima save(Clima clima);
  void delete(Clima clima);
  List<Clima> findAll();
  Clima findUltimoClima(String ciudad);
}
