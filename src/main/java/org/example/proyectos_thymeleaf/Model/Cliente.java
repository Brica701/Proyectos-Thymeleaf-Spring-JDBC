package org.example.proyectos_thymeleaf.Model;

import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Cliente {
    private int cliente_id;
    private String nombre_completo;
    private String direccion;
    private String telefono;
    private LocalDate fecha_nacimiento;
}

