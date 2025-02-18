package com.lucapdt.challenge.model.dto;

import com.lucapdt.challenge.model.entity.Automobile;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Year;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutomobileDTO {
    private Integer id;
    private String marca;
    private String modello;
    private String motorizzazione;
    private Year anno;
    private Double prezzo;
    private Automobile.StatoAuto stato;
}
