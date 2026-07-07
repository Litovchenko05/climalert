package utn.ba.ddsi.climalert.repositories.inmemory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utn.ba.ddsi.climalert.models.entities.Alerta;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class InMemoryAlertaRepositoryTest {

    private InMemoryAlertaRepository alertaRepository;

    @BeforeEach
    void setUp() {
        alertaRepository = new InMemoryAlertaRepository();
    }

    @Test
    void save_conNuevaAlerta_asignaIdYGuarda() {
        Alerta alerta = new Alerta(null, "Buenos Aires", "Ola de Calor", LocalDateTime.now());
        Alerta guardada = alertaRepository.save(alerta);

        assertThat(guardada.getId()).isNotNull();
        assertThat(alertaRepository.findAll()).hasSize(1).contains(guardada);
    }

    @Test
    void save_conAlertaExistente_actualizaRegistro() {
        Alerta alerta = new Alerta(null, "Buenos Aires", "Ola de Calor", LocalDateTime.now());
        Alerta guardada = alertaRepository.save(alerta);
        Long id = guardada.getId();

        Alerta actualizada = new Alerta(id, "Buenos Aires", "Ola de Calor Extremo", LocalDateTime.now());
        Alerta guardadaActualizada = alertaRepository.save(actualizada);

        assertThat(guardadaActualizada.getId()).isEqualTo(id);
        assertThat(alertaRepository.findAll()).hasSize(1);
        assertThat(alertaRepository.findAll().get(0).getNombre()).isEqualTo("Ola de Calor Extremo");
    }

    @Test
    void delete_conAlertaExistente_laElimina() {
        Alerta alerta = new Alerta(null, "Buenos Aires", "Ola de Calor", LocalDateTime.now());
        Alerta guardada = alertaRepository.save(alerta);

        alertaRepository.delete(guardada);

        assertThat(alertaRepository.findAll()).isEmpty();
    }

    @Test
    void findAlertasDeCiudad_devuelveSoloAlertasDeEsaCiudad() {
        Alerta a1 = new Alerta(null, "Buenos Aires", "Ola de Calor", LocalDateTime.now());
        Alerta a2 = new Alerta(null, "Montevideo", "Vientos Fuertes", LocalDateTime.now());
        Alerta a3 = new Alerta(null, "buenos aires", "Tormenta", LocalDateTime.now());

        alertaRepository.save(a1);
        alertaRepository.save(a2);
        alertaRepository.save(a3);

        List<Alerta> alertasBsAs = alertaRepository.findAlertasDeCiudad("Buenos Aires");
        assertThat(alertasBsAs).hasSize(2).extracting(Alerta::getNombre).containsExactlyInAnyOrder("Ola de Calor", "Tormenta");
    }
}
