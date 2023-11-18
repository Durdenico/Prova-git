/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unibas.ingressiaule.modello;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Domenico
 */
@Data
public class DatiPiano implements Comparable<DatiPiano> {

    private String responsabile;
    private int piano;
    private List<Aula> auleResponsabile = new ArrayList<>();

    public DatiPiano(String responsabile, int piano) {
        this.responsabile = responsabile;
        this.piano = piano;
    }

    public int getNumeroAuleResponsabile() {
        return auleResponsabile.size();
    }

    public boolean aggiungiAulaResponsabile(Aula a) {
        return auleResponsabile.add(a);
    }

    @Override
    public int compareTo(DatiPiano o) {
        return this.getPiano() - o.getPiano();
    }

}
