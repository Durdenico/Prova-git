/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unibas.ingressiaule.modello;

import java.time.Month;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Domenico
 */
@Data
public class DatiAccessiMese implements Comparable<DatiAccessiMese> {

    private Month mese;
    private List<String> motivazioniFrequenti;
    private int permanenzaTotale;

    public DatiAccessiMese(Month mese, List<String> motivazioniFrequenti, int permanenzaTotale) {
        this.mese = mese;
        this.motivazioniFrequenti = motivazioniFrequenti;
        this.permanenzaTotale = permanenzaTotale;
    }

    @Override
    public int compareTo(DatiAccessiMese o) {
        return this.getMese().compareTo(o.getMese());
    }

}
