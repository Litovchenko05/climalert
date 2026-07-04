package utn.ba.ddsi.climalert.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Clima {
  @Setter
  private Long id;
  private final String pais;
  private final String ciudad;
  private final String fechaYHora;
  private final double temperatura;
  private final int humedad;
}
