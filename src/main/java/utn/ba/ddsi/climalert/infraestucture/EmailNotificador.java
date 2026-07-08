package utn.ba.ddsi.climalert.infraestucture;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificador implements Notificador{
  private final JavaMailSender mailSender;

  public EmailNotificador(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void enviarAlerta(String destinatario, String cuerpo) {
    SimpleMailMessage message = new SimpleMailMessage();

    message.setFrom("alertas@climalert.com");
    message.setTo(destinatario);
    message.setSubject("⚠️ Climalert: Alerta Meteorológica detectada");
    message.setText(cuerpo);

    mailSender.send(message);
  }
}
