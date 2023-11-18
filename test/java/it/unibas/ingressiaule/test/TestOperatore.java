/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unibas.ingressiaule.test;

import it.unibas.ingressiaule.modello.Archivio;
import it.unibas.ingressiaule.modello.Costanti;
import it.unibas.ingressiaule.modello.DatiAccessiMese;
import it.unibas.ingressiaule.modello.Operatore;
import it.unibas.ingressiaule.persistenza.DAOArchivioMock;
import it.unibas.ingressiaule.persistenza.DAOException;
import it.unibas.ingressiaule.persistenza.IDAOArchivio;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Domenico
 */
public class TestOperatore {

    IDAOArchivio daoArchivio = new DAOArchivioMock();
    Operatore operatore = new Operatore();

    @Test
    public void testCalcolaDatiAccessiMese() {
        try {
            Archivio archivio = daoArchivio.carica("");
            Assertions.assertNotNull(archivio);
            List<DatiAccessiMese> listaDatiAccessiMese = operatore.calcolaDatiAccessiMese(archivio.getAule());
            Assertions.assertEquals(4, listaDatiAccessiMese.size());
            Assertions.assertEquals(Month.MARCH, listaDatiAccessiMese.get(0).getMese());
            Assertions.assertEquals(Month.APRIL, listaDatiAccessiMese.get(1).getMese());
            Assertions.assertEquals(Month.MAY, listaDatiAccessiMese.get(2).getMese());
            Assertions.assertEquals(Month.JULY, listaDatiAccessiMese.get(3).getMese());
            Assertions.assertEquals(Costanti.ESAME, listaDatiAccessiMese.get(0).getMotivazioniFrequenti().get(0));
            Assertions.assertTrue(listaDatiAccessiMese.get(1).getMotivazioniFrequenti().contains(Costanti.RICEVIMENTO));
            Assertions.assertTrue(listaDatiAccessiMese.get(1).getMotivazioniFrequenti().contains(Costanti.LEZIONE));
            Assertions.assertEquals(Costanti.LEZIONE, listaDatiAccessiMese.get(2).getMotivazioniFrequenti().get(0));
            Assertions.assertEquals(Costanti.ESAME, listaDatiAccessiMese.get(3).getMotivazioniFrequenti().get(0));
            Assertions.assertEquals(480, listaDatiAccessiMese.get(0).getPermanenzaTotale());
            Assertions.assertEquals(270, listaDatiAccessiMese.get(1).getPermanenzaTotale());
            Assertions.assertEquals(300, listaDatiAccessiMese.get(2).getPermanenzaTotale());
            Assertions.assertEquals(120, listaDatiAccessiMese.get(3).getPermanenzaTotale());
        } catch (DAOException ex) {
            Assertions.fail();
        }

    }

}
