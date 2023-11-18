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
public class Aula {

    private String codice;
    private String nome;
    private int piano;
    private String emailResponsabile;
    private List<Accesso> accessi = new ArrayList<>();

    public Aula(String codice, String nome, int piano, String emailResponsabile) {
        this.codice = codice;
        this.nome = nome;
        this.piano = piano;
        this.emailResponsabile = emailResponsabile;
    }

    public void aggiungiAccesso(Accesso a) {
        accessi.add(a);
    }

    public String toStringShort() {
        return "Aula{" + "Nome: " + nome + '}';
    }

}
