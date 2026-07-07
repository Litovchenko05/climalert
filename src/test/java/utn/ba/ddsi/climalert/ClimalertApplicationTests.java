package utn.ba.ddsi.climalert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utn.ba.ddsi.climalert.clients.weatherapi.WeatherApiClient;
import utn.ba.ddsi.climalert.models.entities.Clima;
import utn.ba.ddsi.climalert.repositories.ClimaRepository;
import utn.ba.ddsi.climalert.services.ClimaService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ClimalertApplicationTests {
	@Autowired
	private WeatherApiClient weatherApiClient;

	@Autowired
	private ClimaService climaService;

	@Autowired
	private ClimaRepository climaRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void obtenerClimaActualDevuelveClimaDeBuenosAires(){
		Clima clima = weatherApiClient.obtenerClimaActual("Buenos Aires");
		assertThat(clima).isNotNull();
		assertThat(clima.getCiudad()).isEqualTo("Buenos Aires");
	}

	@Test
	void alTraerElClimaDeBuenosAiresEsteSeGuardaCorrectamente() {
		int cantidadInicial = climaRepository.findAll().size();
		climaService.obtenerClimaActual("Buenos Aires");
		List<Clima> climas = climaRepository.findAll();
		assertThat(climas).hasSize(cantidadInicial + 1);
		
		Clima climaGuardado = climas.stream()
				.filter(c -> "Buenos Aires".equals(c.getCiudad()))
				.reduce((first, second) -> second)
				.orElse(null);

		assertThat(climaGuardado).isNotNull();
		assertThat(climaGuardado.getId()).isNotNull();
		assertThat(climaGuardado.getCiudad()).isEqualTo("Buenos Aires");
		assertThat(climaGuardado.getPais()).isNotNull();
		assertThat(climaGuardado.getFechaYHora()).isNotNull();
		assertThat(climaGuardado.getTemperatura()).isNotNull();
		assertThat(climaGuardado.getHumedad()).isGreaterThanOrEqualTo(0);
		assertThat(climaGuardado.isAnalizado()).isFalse();
	}

	@Test
	void findUltimoClima_conCiudad_devuelveElUltimoClimaDeEsaCiudad() {
		Clima c1 = new Clima(null, "Argentina", "Buenos Aires", java.time.LocalDateTime.now(), 20.0, 60, false);
		climaRepository.save(c1);

		Clima c2 = new Clima(null, "Uruguay", "Montevideo", java.time.LocalDateTime.now(), 18.0, 65, false);
		climaRepository.save(c2);

		Clima c3 = new Clima(null, "Argentina", "Buenos Aires", java.time.LocalDateTime.now().plusHours(1), 22.0, 55, false);
		climaRepository.save(c3);

		Clima ultimoBuenosAires = climaRepository.findUltimoClima("Buenos Aires");
		assertThat(ultimoBuenosAires).isNotNull();
		assertThat(ultimoBuenosAires.getCiudad()).isEqualTo("Buenos Aires");
		assertThat(ultimoBuenosAires.getTemperatura()).isEqualTo(22.0);

		Clima ultimoMontevideo = climaRepository.findUltimoClima("Montevideo");
		assertThat(ultimoMontevideo).isNotNull();
		assertThat(ultimoMontevideo.getCiudad()).isEqualTo("Montevideo");
		assertThat(ultimoMontevideo.getTemperatura()).isEqualTo(18.0);
	}
}

