package utn.ba.ddsi.climalert.repositories.inmemory;

import org.springframework.stereotype.Repository;
import utn.ba.ddsi.climalert.models.entities.Alerta;
import utn.ba.ddsi.climalert.repositories.AlertaRepository;
import utn.ba.ddsi.climalert.utils.GeneradorIdSecuencial;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryAlertaRepository implements AlertaRepository {
  private final List<Alerta> alertas = new ArrayList<>();
  private final GeneradorIdSecuencial generadorId = new GeneradorIdSecuencial();

  @Override
  public Alerta save(Alerta alerta) {
    if (alerta.getId() == null) {
      alerta.setId(generadorId.siguiente());
      alertas.add(alerta);
      return alerta;
    }
    delete(alerta);
    alertas.add(alerta);
    return alerta;
  }

  @Override
  public void delete(Alerta alerta) {
    if (alerta.getId() == null) {
      return;
    }
    alertas.removeIf(a -> a.getId().equals(alerta.getId()));
  }

  @Override
  public List<Alerta> findAll() {
    return new ArrayList<>(alertas);
  }

  @Override
  public List<Alerta> findAlertasDeCiudad(String ciudad) {
    if (ciudad == null) {
      return new ArrayList<>();
    }
    return alertas.stream()
        .filter(a -> ciudad.equalsIgnoreCase(a.getCiudad()))
        .toList();
  }
}
