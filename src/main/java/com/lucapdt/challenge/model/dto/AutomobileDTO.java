package com.lucapdt.challenge.model.dto;

import com.lucapdt.challenge.model.entity.Automobile;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Year;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO per il trasferimento dati di Automobile")
public class AutomobileDTO {

    @Schema(description = "Identificativo univoco dell'automobile", example = "10")
    private Integer id;

    @Schema(description = "Marca dell'automobile", example = "Fiat")
    private String marca;

    @Schema(description = "Modello dell'automobile", example = "Panda")
    private String modello;

    @Schema(description = "Tipo di motorizzazione", example = "2.0 JTDM")
    private String motorizzazione;

    @Schema(description = "Anno di produzione", example = "2022")
    private Year anno;

    @Schema(description = "Prezzo dell'automobile (se disponibile)", example = "15000.00")
    @PositiveOrZero(message = "Se presente il prezzo deve essere maggiore di 0")
    private Double prezzo;

    @Schema(description = "Stato dell'automobile (disponibile, venduta)", example = "disponibile")
    private Automobile.StatoAuto stato;
}
