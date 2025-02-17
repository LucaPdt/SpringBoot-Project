package com.lucapdt.challenge.model.dto;

import com.lucapdt.challenge.model.entity.Automobile;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutomobileDTO {
    private int id;
    private String marca;
    private String modello;
    private String motorizzazione;
    private int anno;
    private double prezzo;
    private Automobile.StatoAuto stato;
}
