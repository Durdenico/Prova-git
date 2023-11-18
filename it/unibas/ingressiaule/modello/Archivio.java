/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unibas.ingressiaule.modello;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Domenico
 */
@Getter
@ToString
public class Archivio {

    private List<Aula> aule = new ArrayList<>();

    public void aggiungiAula(Aula a) {
        aule.add(a);
    }

}
