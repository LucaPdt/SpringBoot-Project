package com.lucapdt.challenge.controller;

import com.lucapdt.challenge.command.AutomobileCommand;
import com.lucapdt.challenge.exception.HandlerResponse;
import com.lucapdt.challenge.model.dto.AutomobileDTO;
import com.lucapdt.challenge.model.entity.Automobile;
import com.lucapdt.challenge.model.response.AutomobileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class AutomobileRestController {

    @Autowired
    private AutomobileCommand automobileCommand;

    @Operation(
            summary = "Recupera una automobile per id",
            description = "Endpoint per recuperare dal database una Automobile per id",
            responses = {
                    @ApiResponse(
                            description = "Automobile recuperata con successo",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutomobileDTO.class))
                    ),
                    @ApiResponse(
                            description = "Richiesta malformata controlla che i campi/parametri/variabili inseriti siano corretti",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Accesso negato",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))

                    ),
                    @ApiResponse(
                            description = "Automobile non trovata",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    )
            },
            parameters = {
                    @Parameter(name = "id", description = "ID dell'automobile da recuperare", required = true, example = "1")
            }
    )
    @GetMapping("/automobili/{id}")
    public ResponseEntity<AutomobileDTO> getAutomobileById(@PathVariable("id") int id) {
//        ResponseEntity<AutomobileDTO> response;
//
//        try{
//            response = ResponseEntity.ok(automobileCommand.findById(id));
//        } catch(Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return  response;
        return ResponseEntity.ok(automobileCommand.findById(id));
    }

    @Operation(
            summary = "Crea una nuova Automobile nel database",
            description = "Endpoint per inserire nel database una Automobile accessibile solo agli utenti con il ruolo 'ROLE_ADMIN'.",
            responses = {
                    @ApiResponse(
                            description = "Automobile creata con successo",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutomobileDTO.class))
                    ),
                    @ApiResponse(
                            description = "Richiesta malformata controlla che i campi/parametri/variabili inseriti siano corretti",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Accesso negato",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Accesso negato, l'utente non ha il permesso richiesto",
                            responseCode = "403",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dettagli dell'automobile da creare",
                    required = true
            )
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/automobili")
    public ResponseEntity<AutomobileDTO> createAutomobile(@Validated @RequestBody AutomobileDTO automobileDTO){
        return new ResponseEntity<>(automobileCommand.save(automobileDTO), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Aggiorna una Automobile nel database",
            description = "Endpoint per aggiornare una Automobile nel database, sovrascrivera' solamente i campi specificati (eccetto id),  accessibile solo agli utenti con il ruolo 'ROLE_ADMIN'.",
            responses = {
                    @ApiResponse(
                            description = "Automobile aggiornata con successo",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutomobileDTO.class))
                    ),
                    @ApiResponse(
                            description = "Richiesta malformata controlla che i campi/parametri/variabili inseriti siano corretti",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Automobile non trovata",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Accesso negato",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Accesso negato, l'utente non ha il permesso richiesto",
                            responseCode = "403",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    )
            },
            parameters = {
                    @Parameter(name = "id", description = "ID dell'automobile da aggiornare", required = true, example = "1")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Campi da aggiornare dell'automobile",
                    required = true
            )
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/automobili/{id}")
    public ResponseEntity<AutomobileDTO> updateAutomobile(@PathVariable("id") int id,@Validated  @RequestBody AutomobileDTO automobileDTO){
        return ResponseEntity.ok(automobileCommand.update(id, automobileDTO));
    }

    @Operation(
            summary = "Elimina una Automobile nel database dato il suo id",
            description = "Endpoint per eliminare dal database una Automobile accessibile solo agli utenti con il ruolo 'ROLE_ADMIN'.",
            responses = {
                    @ApiResponse(
                            description = "Automobile eliminata con successo",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Richiesta malformata controlla che i campi/parametri/variabili inseriti siano corretti",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Automobile non trovata",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Accesso negato",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Accesso negato, l'utente non ha il permesso richiesto",
                            responseCode = "403",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    )
            },
            parameters = {
                    @Parameter(name = "id", description = "ID dell'automobile da eliminare", required = true, example = "1")
            }
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/automobili/{id}")
    public ResponseEntity<String> deleteAutomobile(@PathVariable("id") int id){
        automobileCommand.deleteById(id);
        return new ResponseEntity<>("Automobile delete", HttpStatus.OK);
    }

    @Operation(
            summary = "Restituisce la lista di tutte le automobili nel Database",
            description = "Endpoint per recuperare la lista di tutte le automobili nel database",
            responses = {
                    @ApiResponse(
                            description = "Richiesta effettuata con successo",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
                    ),
                    @ApiResponse(
                            description = "Richiesta malformata controlla che i campi/parametri/variabili inseriti siano corretti",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Accesso negato",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    )
            }
    )
    @GetMapping("/automobili/list")
    public ResponseEntity<List<AutomobileDTO>> findAllList(){
        return ResponseEntity.ok(automobileCommand.findAll());
    }

    @Operation(
            summary = "Restituisce una richiesta paginata effettuata su tutte le automobili nel Database",
            description = "Endpoint per effettuare una richiesta paginata su tutte le automobili nel Database, 'page' specifica il numero di pagina da restituire, 'size' specifica la dimensione di una pagina",
            responses = {
                    @ApiResponse(
                            description = "Richiesta effettuata con successo",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutomobileResponse.class))
                    ),
                    @ApiResponse(
                            description = "Richiesta malformata controlla che i campi/parametri/variabili inseriti siano corretti",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Accesso negato",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    )
            }
    )
    @GetMapping("/automobili")
    public ResponseEntity<AutomobileResponse> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(automobileCommand.findAll(page,size));
    }

    @Operation(
            summary = "Restituisce una richiesta paginata effettuata su tutte le automobili nel database con la marca specificata",
            description = "Endpoint per effettuare una richiesta paginata su tutte le automobili nel Database con la marca specificata, 'page' specifica il numero di pagina da restituire, 'size' specifica la dimensione di una pagina",
            responses = {
                    @ApiResponse(
                            description = "Richiesta effettuata con successo",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutomobileResponse.class))
                    ),
                    @ApiResponse(
                            description = "Richiesta malformata controlla che i campi/parametri/variabili inseriti siano corretti",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Accesso negato",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    )
            },
            parameters = {
                    @Parameter(name = "page", description = "Numero della pagina da restituire", required = false, example = "0"),
                    @Parameter(name = "size", description = "Numero di elementi per pagina", required = false, example = "10"),
                    @Parameter(name = "marca", description = "Marca da cercare", required = true, example = "Fiat")
            }
    )
    @GetMapping("/automobili/marca")
    public ResponseEntity<AutomobileResponse> findByMarca(
            @RequestParam(value = "marca") String marca,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        return ResponseEntity.ok(automobileCommand.findByMarca(marca, page, size));

    }

    @Operation(
            summary = "Restituisce una richiesta paginata effettuata su tutte le automobili nel database con il range di prezzo indicato",
            description = "Endpoint per effettuare una richiesta paginata su tutte le automobili nel Database con il range di prezzo indicato, " +
                    "'page' specifica il numero di pagina da restituire, 'size' specifica la dimensione di una pagina, " +
                    "'prezzomin' specifica il prezzo minimo da cui far partire il filtro, 'prezzomax' indica il prezzo massimo in cui fermare il filtro",
            responses = {
                    @ApiResponse(
                            description = "Richiesta effettuata con successo",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutomobileResponse.class))
                    ),
                    @ApiResponse(
                            description = "Richiesta malformata controlla che i campi/parametri/variabili inseriti siano corretti",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Accesso negato",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    )
            },
            parameters = {
                    @Parameter(name = "page", description = "Numero della pagina da restituire", required = false, example = "0"),
                    @Parameter(name = "size", description = "Numero di elementi per pagina", required = false, example = "10"),
                    @Parameter(name = "prezzoMin", description = "Prezzo da cui far partire il filtro", required = true, example = "0"),
                    @Parameter(name = "prezzoMax", description = "Prezzo in cui far terminare il filtro", required = true, example = "10000")
            }
    )
    @GetMapping("/automobili/prezzo/range")
    public ResponseEntity<AutomobileResponse> findByPrezzoBetween(
            @RequestParam(value = "prezzoMin") double prezzoMin,
            @RequestParam(value = "prezzoMax") double prezzoMax,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        return ResponseEntity.ok(automobileCommand.findByPrezzoBetween(prezzoMin, prezzoMax, page, size));
    }

    @Operation(
            summary = "Restituisce una richiesta paginata effettuata su tutte le automobili nel database con lo stato specificato",
            description = "Endpoint per effettuare una richiesta paginata su tutte le automobili nel Database con lo stato specificato, " +
                    "'page' specifica il numero di pagina da restituire, 'size' specifica la dimensione di una pagina, 'stato' specifica lo stato delle automobili richiesto",
            responses = {
                    @ApiResponse(
                            description = "Richiesta effettuata con successo",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutomobileResponse.class))
                    ),
                    @ApiResponse(
                            description = "Richiesta malformata controlla che i campi/parametri/variabili inseriti siano corretti",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    ),
                    @ApiResponse(
                            description = "Accesso negato",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerResponse.class))
                    )
            },
            parameters = {
                    @Parameter(name = "page", description = "Numero della pagina da restituire", required = false, example = "0"),
                    @Parameter(name = "size", description = "Numero di elementi per pagina", required = false, example = "10"),
                    @Parameter(name = "stato", description = "Stato da cercare", required = true, example = "disponibile")
            }
    )
    @GetMapping("/automobili/stato")
    public ResponseEntity<AutomobileResponse> findByStato(
            @RequestParam(value = "stato") Automobile.StatoAuto stato,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        return ResponseEntity.ok(automobileCommand.findByStato(stato, page, size));
    }
}
