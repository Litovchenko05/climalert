package utn.ba.ddsi.climalert.models.events;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import utn.ba.ddsi.climalert.models.entities.Alerta;
import utn.ba.ddsi.climalert.models.entities.Interesado;
import utn.ba.ddsi.climalert.repositories.InteresadoRepository;
import utn.ba.ddsi.climalert.infraestucture.Notificador;
import utn.ba.ddsi.climalert.utils.DateTimeMapper;
import java.util.List;

@Component
@AllArgsConstructor
public class NotificadorAlertaListener {
  private final Notificador notificador;
  private final InteresadoRepository interesadoRepository;

  @EventListener
  public void mapearYEnviarMails(AlertaGeneradaEvent event){
    Alerta alerta = event.getAlerta();

    List<String> destinatarios = interesadoRepository.findAll().stream()
        .map(Interesado::getEmail)
        .toList();

    String fechaFormateada = DateTimeMapper.toString(alerta.getFechaYHora());

    String cuerpoMensaje = String.format(
        """
            SISTEMA DE ALERTAS CLIMALERT
            -------------------------------------
            Se ha registrado una nueva alerta meteorológica en el sistema.
            
            • Tipo de Alerta: %s
            • Ciudad Afectada: %s
            • Fecha y Hora de Detección: %s
            
            Por favor, tome las medidas de prevención correspondientes.""",
        alerta.getNombre(),
        alerta.getCiudad(),
        fechaFormateada
    );
    for (String destinatario : destinatarios) {
      notificador.enviarAlerta(destinatario, cuerpoMensaje);
    }
  }

}
