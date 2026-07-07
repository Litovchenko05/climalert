package utn.ba.ddsi.climalert.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Alerta {
  @Setter
  private Long id;
  private String ciudad;
  private String nombre;
  private LocalDateTime fechaYHora;
}
