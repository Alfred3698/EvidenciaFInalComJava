/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computacionJava.evidencia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jajimenez
 */
public class ConsultorioMain {

    public static List<Usuario> usuarios;
    public static List<Cita> citas = new ArrayList();

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
            cargarCita();
            menu();

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
        usuarios.add(new Usuario(2, "alfredo", "0000"));
        System.out.println("Los usuarios han sido cargados: " + usuarios.size());

    }

    public static boolean validarCredenciales(String usuario, String contrasena) {
        return usuarios.stream().anyMatch(x -> x.getNombre().equals(usuario) && x.getContrasena().equals(contrasena));
    }

    public static void menu() {
        Integer opcion = -1;
        while (opcion != 0) {

            Scanner opcionScanner = new Scanner(System.in);
            System.out.println("1.-Dar de alta a medico\n"
                    + "2.-Dar de alta a un paciente\n"
                    + "4.Ver las citas de todos los medicos\n"
                    + "5.-Ver las citas por nombre del medico\n"
                    + "6.-Ver las citas por nombre del paciente\n"
                    + "7.-Crear cita\n"
                    + "8.-ver todas las citas\n"
                    + "9.-Guardar\n"
                    + "0.-Salir");
            System.out.println("Opción:");
            opcion = opcionScanner.nextInt();

            switch (opcion) {
                case 7:
                    crearCita();
                    break;
                case 8:
                    imprimirTodasCitas();
                    break;
                case 9:
                    save();
                    break;

            }
        }

    }

    public static void save() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(citas);
            System.out.println(json);
            String ruta = "citas.json";

            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(json);
            bw.close();
        } catch (Exception e) {
            System.out.println("Error->" + e.getMessage());
        }

        /*Guardar variable*/
    }

    public static void cargarCita() {
        String json = "[{\"id\":1,\"nombreCita\":\"Cita numero 1\",\"fecha\":\"24/11/2021\",\"medico\":{\"id\":1,\"nombre\":\"Carlos\",\"especialida\":\"General\"},\"paciente\":{\"id\":1,\"nombre\":\"Maria\"}},{\"id\":1,\"nombreCita\":\"Cita numero 2\",\"fecha\":\"24/11/2021\",\"medico\":{\"id\":1,\"nombre\":\"Roberto\",\"especialida\":\"General\"},\"paciente\":{\"id\":1,\"nombre\":\"Arturo\"}}]";
        Gson gson = new Gson();
        Cita[] cita = gson.fromJson(json, Cita[].class);
        //citas.add(cita);
        //System.out.println("nombre del paciente:" + cita.getPaciente().getNombre());
        for (Cita temp : cita) {
            citas.add(temp);
        }
        System.out.println("Hola mundo");
    }

    public static void imprimirTodasCitas() {
        for (Cita cita : citas) {
            System.out.println("---------------------------------------------------");
            System.out.println("Nombre cita:" + cita.getNombreCita());
            System.out.println("Nombre paciente:" + cita.getPaciente().getNombre());
            System.out.println("Nombre medico:" + cita.getMedico().getNombre());
        }
    }

    public static void crearCita() {

        Cita cita = new Cita();
        Medico medico = new Medico();
        Paciente paciente = new Paciente();
        medico.setId(1);
        medico.setNombre("Salvador");
        medico.setEspecialida("General");
        paciente.setId(1);
        paciente.setNombre("Maria");
        cita.setId(1);
        cita.setNombreCita("Cita numero 1");
        cita.setMedico(medico);
        cita.setPaciente(paciente);
        cita.setFecha("24/11/2021");
        //save(cita);
        citas.add(cita);
    }

}
