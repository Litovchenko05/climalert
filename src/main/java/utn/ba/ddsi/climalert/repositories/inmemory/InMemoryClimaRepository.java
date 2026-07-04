package utn.ba.ddsi.climalert.repositories.inmemory;

import org.springframework.stereotype.Repository;
import utn.ba.ddsi.climalert.models.entities.Clima;
import utn.ba.ddsi.climalert.repositories.ClimaRepository;
import utn.ba.ddsi.climalert.utils.GeneradorIdSecuencial;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryClimaRepository implements ClimaRepository {
  private final List<Clima> climas = new ArrayList<>();
  private final GeneradorIdSecuencial generadorId = new GeneradorIdSecuencial();

  @Override
  public Clima save(Clima clima) {
    if (clima.getId() == null) {
      clima.setId(generadorId.siguiente());
      climas.add(clima);
      return clima;
    }
    delete(clima);
    climas.add(clima);
    return clima;
  }
  public List<Clima> findAll(){
    return new ArrayList<>(climas);
  }
  @Override
  public void delete(Clima clima) {
    if (clima.getId() == null) {
      return;
    }
    climas.removeIf(c -> c.getId().equals(clima.getId()));
  }
}
