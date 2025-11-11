package org.example.proyectos_thymeleaf.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    private int id;
    private String nombre;
    private String email;
}
