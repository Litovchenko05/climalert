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

public class Condiciones {
  @JsonProperty("temp_c")
  private double temperatura;
  
  @JsonProperty("humidity")
  private int humedad;
}
