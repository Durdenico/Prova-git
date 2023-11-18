/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unibas.ingressiaule.persistenza;

import it.unibas.ingressiaule.modello.Accesso;
import it.unibas.ingressiaule.modello.Archivio;
import it.unibas.ingressiaule.modello.Aula;
import it.unibas.ingressiaule.modello.Costanti;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author Domenico
 */
public class DAOArchivioMock implements IDAOArchivio {

    @Override
    public Archivio carica(String nomeFile) throws DAOException {
        Archivio archivio = new Archivio();
        
        Aula aula1 = new Aula("AMD1", "Aula Magna DiMIE", 2, "giansalvatoremecca@unibas.it");
        Aula aula2 = new Aula("AL19", "Aula Leonardo", 1, "donatellosantoro@unibas.it");
        Aula aula3 = new Aula("LD71", "Laboratorio Didattico", 2, "giansalvatoremecca@unibas.it");
        Aula aula4 = new Aula("A001", "Aula 1", 3, "giansalvatoremecca@unibas.it");
        archivio.aggiungiAula(aula1);
        archivio.aggiungiAula(aula2);
        archivio.aggiungiAula(aula3);
        archivio.aggiungiAula(aula4);

        Accesso a1 = new Accesso("12345", "Mario Rossi", 180, Costanti.ESAME, LocalDateTime.of(2023, Month.MARCH, 12, 10, 30));
        Accesso a2 = new Accesso("63819", "Giulia Bruno", 120, Costanti.LEZIONE, LocalDateTime.of(2023, Month.MAY, 1, 11, 00));
        Accesso a3 = new Accesso("12345", "Mario Rossi", 120, Costanti.ESAME, LocalDateTime.of(2023, Month.JULY, 15, 9, 30));
        Accesso a4 = new Accesso("74783", "Giorgio Bianchi", 150, Costanti.ESAME, LocalDateTime.of(2023, Month.MARCH, 24, 15, 30));
        Accesso a5 = new Accesso("74783", "Giorgio Bianchi", 120, Costanti.RICEVIMENTO, LocalDateTime.of(2023, Month.APRIL, 18, 9, 30));
        Accesso a6 = new Accesso("54199", "Giuseppe Verdi", 180, Costanti.LEZIONE, LocalDateTime.of(2023, Month.MAY, 30, 10, 00));
        Accesso a7 = new Accesso("12345", "Mario Rossi", 150, Costanti.LEZIONE, LocalDateTime.of(2023, Month.MARCH, 7, 16, 30));
        Accesso a8 = new Accesso("63819", "Giulia Bruno", 150, Costanti.LEZIONE, LocalDateTime.of(2023, Month.APRIL, 27, 15, 00));
        aula1.aggiungiAccesso(a1);
        aula2.aggiungiAccesso(a2);
        aula3.aggiungiAccesso(a3);
        aula4.aggiungiAccesso(a4);
        aula1.aggiungiAccesso(a5);
        aula2.aggiungiAccesso(a6);
        aula3.aggiungiAccesso(a7);
        aula1.aggiungiAccesso(a8);
        
        return archivio;
    }

}
