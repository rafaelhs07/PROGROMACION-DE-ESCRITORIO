package org.example.registropaciente.dao;

import org.example.registropaciente.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteDAD {
    List<Paciente> pacientes;

    public PacienteDAD(){
        pacientes = new ArrayList<>();
    }

    public  void AgregarPaciente(Paciente paciente){
        pacientes.add(paciente);

    }

    public List<Paciente> listarPacientes(){
        return pacientes;
    }
}
