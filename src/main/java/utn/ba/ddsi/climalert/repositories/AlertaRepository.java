package utn.ba.ddsi.climalert.repositories;

import utn.ba.ddsi.climalert.models.entities.Alerta;
import java.util.List;

public interface AlertaRepository {
  Alerta save(Alerta alerta);
  void delete(Alerta alerta);
  List<Alerta> findAll();
  List<Alerta> findAlertasDeCiudad(String ciudad);
}
