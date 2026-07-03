package utn.ba.ddsi.climalert.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Localidad {
  @JsonProperty("name")
  private String nombre;

  @JsonProperty("country")
  private String pais;
  
  @JsonProperty("localtime")
  private String horaLocal;
}
