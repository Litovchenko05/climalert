package utn.ba.ddsi.climalert.infraestucture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailNotificadorTest {

  @Mock
  private JavaMailSender mailSender;

  @InjectMocks
  private EmailNotificador emailNotificador;

  @Test
  void alEnviarAlertaSeConstruyeYEnviaElMensajeCorrectamente() {
    String destinatario = "test@example.com";
    String cuerpo = "Alerta de sofocación ambiental en Buenos Aires";

    emailNotificador.enviarAlerta(destinatario, cuerpo);

    ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
    verify(mailSender).send(messageCaptor.capture());

    SimpleMailMessage messageEnviado = messageCaptor.getValue();
    assertThat(messageEnviado).isNotNull();
    assertThat(messageEnviado.getFrom()).isEqualTo("alertas@climalert.com");
    assertThat(messageEnviado.getTo()).containsExactly(destinatario);
    assertThat(messageEnviado.getSubject()).isEqualTo("⚠️ Climalert: Alerta Meteorológica detectada");
    assertThat(messageEnviado.getText()).isEqualTo(cuerpo);
  }
}
