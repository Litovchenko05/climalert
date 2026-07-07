package utn.ba.ddsi.climalert.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Clima {
  @Setter
  private Long id;
  private final String pais;
  private final String ciudad;
  private final LocalDateTime fechaYHora;
  private final Double temperatura;
  private final Integer humedad;
  private final boolean analizado;
}
