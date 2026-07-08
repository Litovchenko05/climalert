package utn.ba.ddsi.climalert.infraestucture;

import org.springframework.stereotype.Component;

@Component
public interface Notificador {
  void enviarAlerta(String destinatario, String cuerpo);
}
