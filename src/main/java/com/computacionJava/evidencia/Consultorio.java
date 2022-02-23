/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computacionJava.evidencia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jajimenez
 */
public class Consultorio {

    public static List<Usuario> usuarios;

    public static void main(String[] args) {
        boolean existeUsuario;
        String usuario = "";
        String contrasena = "";
        Scanner credenciales = new Scanner(System.in);
        System.out.println("Cargando sistema... ");
        cargarUsuarios();
        System.out.println("-------------------------Inicio de sesion-----------------------");
        System.out.println("Usuario:");
        usuario = credenciales.nextLine();
        System.out.println("Contraseña");
        contrasena = credenciales.nextLine();
        existeUsuario = validarCredenciales(usuario, contrasena);
        if (existeUsuario) {
            System.out.println("existe el usuario");
            //menu();
            load();

        } else {
            System.out.println("el usuario no existe");
        }

    }

    public static void cargarUsuarios() {

        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }

        usuarios.add(new Usuario(1, "carlos", "1234"));
        usuarios.add(new Usuario(2, "sofia", "1234"));
        usuarios.add(new Usuario(2, "ithan", "0000"));
        System.out.println("Los usuarios han sido cargados: " + usuarios.size());

    }

    public static boolean validarCredenciales(String usuario, String contrasena) {
        return usuarios.stream().anyMatch(x -> x.getNombre().equals(usuario) && x.getContrasena().equals(contrasena));
    }

    public static void menu() {
        System.out.println("1.-Dar de alta a medico\n"
                + "2.-Dar de alta a un paciente\n"
                + "3.Crear una cita\n"
                + "4.Ver las citas de todos los medicos\n"
                + "5.-Ver las citas por nombre del medico\n"
                + "6.-Ver las citas por nombre del paciente\n"
                + "7.-Guardar cita");

        Cita cita = new Cita();
        Medico medico = new Medico();
        Paciente paciente = new Paciente();
        medico.setId(1);
        medico.setNombre("Carlos");
        medico.setEspecialida("General");
        paciente.setId(1);
        paciente.setNombre("Maria");
        cita.setId(1);
        cita.setNombreCita("Cita numero 1");
        cita.setMedico(medico);
        cita.setPaciente(paciente);
        cita.setFecha("24/11/2021");
        save(cita);

    }

    public static void save(Cita cita) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(cita);
            System.out.println(json);
        } catch (Exception e) {
            System.out.println("Error->" + e.getMessage());
        }

        /*Guardar variable*/
    }

    public static void load() {
        String json = "{\"id\":1,\"nombreCita\":\"Cita numero 1\",\"fecha\":\"24/11/2021\",\"medico\":{\"id\":1,\"nombre\":\"Carlos\",\"especialida\":\"General\"},\"paciente\":{\"id\":1,\"nombre\":\"Maria\"}}";
        System.out.println("load " + json);
        Gson gson = new Gson();
        Cita cita = gson.fromJson(json, Cita.class);

        System.out.println("nombre del paciente:" + cita.getPaciente().getNombre());
    }

}
