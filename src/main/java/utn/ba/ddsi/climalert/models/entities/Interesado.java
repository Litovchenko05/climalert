package utn.ba.ddsi.climalert.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Interesado {
  @Setter
  private Long id;
  private String nombre;
  private String email;
}
