package org.example.proyectos_thymeleaf.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Usuario {
    private int id;
    private String usuario;
    private String contrasena;
}
