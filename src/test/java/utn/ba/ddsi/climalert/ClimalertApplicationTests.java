package utn.ba.ddsi.climalert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utn.ba.ddsi.climalert.clients.weatherapi.WeatherApiClient;
import utn.ba.ddsi.climalert.clients.weatherapi.dto.WeatherApiResponse;
import utn.ba.ddsi.climalert.models.entities.Clima;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ClimalertApplicationTests {
	@Autowired
	private WeatherApiClient weatherApiClient;

	@Test
	void contextLoads() {
	}

	@Test
	void obtenerClimaActualDevuelveClimaDeBuenosAires(){
		Clima clima = weatherApiClient.obtenerClimaActual("Buenos Aires");
		assertThat(clima).isNotNull();
		assertThat(clima.getCiudad()).isEqualTo("Buenos Aires");
	}


}
