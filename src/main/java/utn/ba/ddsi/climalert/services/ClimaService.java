package utn.ba.ddsi.climalert.services;

public interface ClimaService {
  void obtenerClimaActual(String ciudad);
  void analizarUltimoClima(String ciudad);
}
