package utn.ba.ddsi.climalert.schedulers;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import utn.ba.ddsi.climalert.infraestucture.Notificador;
import utn.ba.ddsi.climalert.models.entities.Clima;
import utn.ba.ddsi.climalert.models.entities.Interesado;
import utn.ba.ddsi.climalert.repositories.ClimaRepository;
import utn.ba.ddsi.climalert.repositories.InteresadoRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ClimaSchedulerTest {

  @Autowired
  private ClimaScheduler climaScheduler;

  @Autowired
  private ClimaRepository climaRepository;

  @Autowired
  private InteresadoRepository interesadoRepository;

  @MockitoBean
  private Notificador notificador;

  @Test
  void alAnalizarUltimoClimaConAlertaSeEnviaMailAlInteresado() {
    // 1. Guardamos un interesado con el email requerido
    String emailInteresado = "ailensuppi02@gmail.com";
    Interesado interesado = new Interesado(null, "Ailen", emailInteresado);
    interesadoRepository.save(interesado);

    // 2. Guardamos un clima de Buenos Aires que dispare la alerta de Sofocación Ambiental
    // Para que dispare la alerta: temp > 35 y humedad > 60
    Clima climaConAlerta = new Clima(
        null,
        "Argentina",
        "Buenos Aires",
        LocalDateTime.of(2026, 7, 7, 23, 0),
        36.5,
        75,
        false
    );
    climaRepository.save(climaConAlerta);

    // 3. Ejecutamos el método del scheduler
    climaScheduler.analizarUltimoClimaDeBuenosAires();

    // 4. Verificamos que se haya enviado la alerta al email indicado
    ArgumentCaptor<String> cuerpoCaptor = ArgumentCaptor.forClass(String.class);
    verify(notificador).enviarAlerta(eq(emailInteresado), cuerpoCaptor.capture());

    String cuerpo = cuerpoCaptor.getValue();
    assertThat(cuerpo).contains("SISTEMA DE ALERTAS CLIMALERT");
    assertThat(cuerpo).contains("Sofocación Ambiental");
    assertThat(cuerpo).contains("Buenos Aires");
  }
}
