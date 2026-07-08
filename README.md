# Climalert ── Sistema de Monitoreo Climático y Alertas Autónomas 🌦️

Climalert es un servicio backend autónomo e integrado desarrollado sobre Spring Boot. Su propósito principal es conectarse de forma periódica a un proveedor meteorológico externo, persistir el historial, evaluar las condiciones actuales mediante un motor de reglas flexible y despachar alertas automatizadas por correo electrónico ante la detección de anomalías climáticas críticas.

En esta primera iteración, el sistema opera sin interfaz de usuario y define una situación de alerta bajo un criterio unificado: **Temperatura > 35°C** y **Humedad > 60%** simultáneamente (Sofocación Ambiental).

---

*   **Institución:** Universidad Tecnológica Nacional - Facultad Regional Buenos Aires (UTN FRBA)
*   **Materia:** Diseño de Sistemas de Información (DDSI)
*   **Año:** 2026