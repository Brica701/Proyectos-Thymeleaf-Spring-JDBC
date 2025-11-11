package org.example.proyectos_thymeleaf.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    private String username;
    private String password;
}
