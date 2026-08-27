package org.example.registropaciente;

public class Paciente {

  private String nombres;
  private String apellidos;
  private String genero;
  private boolean enfermo;

  public Paciente(
          String nombres,
          String apellidos,
          String genero,
          boolean enfermo
  ) {

    this.nombres = nombres;
    this.apellidos = apellidos;
    this.genero = genero;
    this.enfermo = enfermo;
  }

  public String getNombres() {
    return nombres;
  }

  public String getApellidos() {
    return apellidos;
  }

  public String getGenero() {
    return genero;
  }

  public boolean isEnfermo() {
    return enfermo;
  }

  public String getEstado() {

    if (enfermo) {
      return "Enfermo";
    } else {
      return "No enfermo";
    }
  }
}