package utn.ba.ddsi.climalert.repositories.inmemory;

import org.springframework.stereotype.Repository;
import utn.ba.ddsi.climalert.models.entities.Interesado;
import utn.ba.ddsi.climalert.repositories.InteresadoRepository;
import utn.ba.ddsi.climalert.utils.GeneradorIdSecuencial;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryInteresadoRepository implements InteresadoRepository {
  private final List<Interesado> interesados = new ArrayList<>();
  private final GeneradorIdSecuencial generadorId = new GeneradorIdSecuencial();

  @Override
  public Interesado save(Interesado interesado) {
    if (interesado.getId() == null) {
      interesado.setId(generadorId.siguiente());
      interesados.add(interesado);
      return interesado;
    }
    delete(interesado);
    interesados.add(interesado);
    return interesado;
  }

  public List<Interesado> findAll() {
    return new ArrayList<>(interesados);
  }

  @Override
  public void delete(Interesado interesado) {
    if (interesado.getId() == null) {
      return;
    }
    interesados.removeIf(i -> i.getId().equals(interesado.getId()));
  }
}
