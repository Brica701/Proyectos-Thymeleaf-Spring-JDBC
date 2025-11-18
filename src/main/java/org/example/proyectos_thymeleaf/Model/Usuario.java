package org.example.proyectos_thymeleaf.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Usuario {
    private int id;
    private String usuario;
    private String contrasena;
}
