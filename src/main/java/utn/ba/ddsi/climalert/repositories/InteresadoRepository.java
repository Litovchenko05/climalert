package utn.ba.ddsi.climalert.repositories;

import utn.ba.ddsi.climalert.models.entities.Interesado;
import java.util.List;

public interface InteresadoRepository {
  Interesado save(Interesado interesado);
  void delete(Interesado interesado);
  List<Interesado> findAll();
}
