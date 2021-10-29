/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.evidencia;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jajimenez
 */
public class Main {

    public static List<Usuario> usuarios;

    public static void main(String[] args) {
        boolean existeUsuario;
        String usuario = "";
        String contrasena = "";
        Scanner credenciales = new Scanner(System.in);
        System.out.println("Cargando sistema... ");
        cargarUsuarios();
        System.out.println("Inicio de sesion:");
        System.out.println("Usuario:");
        usuario = credenciales.nextLine();
        System.out.println("Contraseña");
        contrasena = credenciales.nextLine();
        existeUsuario = validarCredenciales(usuario, contrasena);
        if (existeUsuario) {
            System.out.println("existe el usuario");
            menu();

        } else {
            System.out.println("el usuario no existe");
        }

    }

    public static void cargarUsuarios() {

        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }

        usuarios.add(new Usuario(1, "Carlos", "0000"));
        usuarios.add(new Usuario(2, "Sofia", "0000")
        );
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
                + "6.-Ver las citas por nombre del paciente\n");
    }

}
