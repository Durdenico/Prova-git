/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unibas.ingressiaule.modello;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Domenico
 */
@Data
public class Accesso {

    private String matricola;
    private String studente;
    @EqualsAndHashCode.Exclude
    private int permanenza;
    @EqualsAndHashCode.Exclude
    private String motivazione;
    @EqualsAndHashCode.Exclude
    private LocalDateTime dataOra;

    public Accesso(String matricola, String studente, int permanenza, String motivazione, LocalDateTime dataOra) {
        this.matricola = matricola;
        this.studente = studente;
        this.permanenza = permanenza;
        this.motivazione = motivazione;
        this.dataOra = dataOra;
    }

}
