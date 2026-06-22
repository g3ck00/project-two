package org.example.projecttwo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public record CredencialesInvalidasDTO (
    int status,
    String mensaje,
    LocalDateTime fechaHora)
{}
