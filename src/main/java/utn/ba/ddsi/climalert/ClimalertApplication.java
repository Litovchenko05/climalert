package utn.ba.ddsi.climalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import utn.ba.ddsi.climalert.config.WeatherApiProperties;

@SpringBootApplication
@EnableConfigurationProperties(WeatherApiProperties.class)
public class ClimalertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimalertApplication.class, args);
	}

}
