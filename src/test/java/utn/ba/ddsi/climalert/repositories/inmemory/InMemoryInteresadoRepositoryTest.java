package utn.ba.ddsi.climalert.repositories.inmemory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utn.ba.ddsi.climalert.models.entities.Interesado;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryInteresadoRepositoryTest {

  private InMemoryInteresadoRepository repository;

  @BeforeEach
  void setUp() {
    repository = new InMemoryInteresadoRepository();
  }

  @Test
  void alGuardarUnInteresadoNuevoSeLeAsignaUnIdYSeGuardaCorrectamente() {
    Interesado interesado = new Interesado(null, "Juan Perez", "juan@example.com");

    Interesado guardado = repository.save(interesado);

    assertThat(guardado.getId()).isNotNull();
    assertThat(guardado.getNombre()).isEqualTo("Juan Perez");
    assertThat(guardado.getEmail()).isEqualTo("juan@example.com");

    List<Interesado> todos = repository.findAll();
    assertThat(todos).hasSize(1);
    assertThat(todos.get(0)).isEqualTo(guardado);
  }

  @Test
  void alActualizarUnInteresadoExistenteSeModificaCorrectamente() {
    Interesado interesado = new Interesado(null, "Juan Perez", "juan@example.com");
    Interesado guardado = repository.save(interesado);

    Interesado aActualizar = new Interesado(guardado.getId(), "Juan Gomez", "juan.gomez@example.com");
    repository.save(aActualizar);

    List<Interesado> todos = repository.findAll();
    assertThat(todos).hasSize(1);
    assertThat(todos.get(0).getNombre()).isEqualTo("Juan Gomez");
    assertThat(todos.get(0).getEmail()).isEqualTo("juan.gomez@example.com");
  }

  @Test
  void alEliminarUnInteresadoSeRemueveDelRepositorio() {
    Interesado interesado = new Interesado(null, "Juan Perez", "juan@example.com");
    Interesado guardado = repository.save(interesado);

    repository.delete(guardado);

    List<Interesado> todos = repository.findAll();
    assertThat(todos).isEmpty();
  }
}
