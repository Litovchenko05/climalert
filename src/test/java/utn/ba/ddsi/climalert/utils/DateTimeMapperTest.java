package utn.ba.ddsi.climalert.utils;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class DateTimeMapperTest {

    @Test
    void toLocalDateTime_conFechaValida_devuelveLocalDateTimeCorrecto() {
        String fechaStr = "2026-07-03 12:42";
        LocalDateTime resultado = DateTimeMapper.toLocalDateTime(fechaStr);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getYear()).isEqualTo(2026);
        assertThat(resultado.getMonthValue()).isEqualTo(7);
        assertThat(resultado.getDayOfMonth()).isEqualTo(3);
        assertThat(resultado.getHour()).isEqualTo(12);
        assertThat(resultado.getMinute()).isEqualTo(42);
    }

    @Test
    void toLocalDateTime_conNulo_devuelveNulo() {
        assertThat(DateTimeMapper.toLocalDateTime(null)).isNull();
    }

    @Test
    void toLocalDateTime_conVacio_devuelveNulo() {
        assertThat(DateTimeMapper.toLocalDateTime("")).isNull();
        assertThat(DateTimeMapper.toLocalDateTime("   ")).isNull();
    }

    @Test
    void toString_conLocalDateTimeValido_devuelveStringCorrecto() {
        LocalDateTime fecha = LocalDateTime.of(2026, 7, 3, 12, 42);
        String resultado = DateTimeMapper.toString(fecha);

        assertThat(resultado).isEqualTo("2026-07-03 12:42");
    }

    @Test
    void toString_conNulo_devuelveNulo() {
        assertThat(DateTimeMapper.toString(null)).isNull();
    }
}
