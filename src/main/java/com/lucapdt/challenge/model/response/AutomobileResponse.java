package com.lucapdt.challenge.model.response;

import com.lucapdt.challenge.model.dto.AutomobileDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO di risposta per restituire piu' automobili")
public class AutomobileResponse {

    @Schema(description = "Il contenuto della risposta")
    private List<AutomobileDTO> content;

    @Schema(description = "Il numero di pagina")
    private int pageNo;

    @Schema(description = "la dimensione di una pagina")
    private int pageSize;

    @Schema(description = "Il numero complessivo di elementi presenti")
    private long totalElements;

    @Schema(description = "Il numero totale di pagine")
    private int totalPages;

    @Schema(description = "Flag che ci dice se siamo nell'ultima pagina")
    private boolean last;
}
