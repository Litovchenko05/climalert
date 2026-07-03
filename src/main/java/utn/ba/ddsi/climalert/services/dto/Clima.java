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

public class Clima {
  @JsonProperty("location")
  private Localidad localidad;
  
  @JsonProperty("current")
  private Condiciones condiciones;
}
