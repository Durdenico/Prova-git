/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unibas.ingressiaule.modello;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Domenico
 */
@Slf4j
public class Operatore {

    private List<Aula> filtraggioAulePerResponsabile(List<Aula> aule, String responsabile) {
        List<Aula> listaFiltrata = new ArrayList<>();
        for (Aula aula : aule) {
            if (aula.getEmailResponsabile().equals(responsabile)) {
                listaFiltrata.add(aula);
            }
        }
        return listaFiltrata;
    }

    public List<DatiPiano> getListaDatiPiano(List<Aula> aule, String responsabile) {
        List<Aula> listaFiltrata = filtraggioAulePerResponsabile(aule, responsabile);
        log.debug("Responsabile: " + responsabile + " - Lista Aule Filtrata: " + listaFiltrata.toString());
        Map<Integer, DatiPiano> mappaDatiPiano = new HashMap<>();
        for (Aula aula : listaFiltrata) {
            int piano = aula.getPiano();
            DatiPiano datiPiano = mappaDatiPiano.get(piano);
            if (datiPiano == null) {
                datiPiano = new DatiPiano(responsabile, piano);
                mappaDatiPiano.put(piano, datiPiano);
            }
            datiPiano.aggiungiAulaResponsabile(aula);
        }
        List<DatiPiano> listaDatiPiano = new ArrayList<>(mappaDatiPiano.values());
        Collections.sort(listaDatiPiano);
        return listaDatiPiano;
    }

    public boolean verificaAccessiDuplicati(DatiPiano datiPiano) {
        List<Accesso> listaAccessiDatiPiano = getListaAccessiDatiPiano(datiPiano);
        Set<Accesso> setAccessiDatiPiano = new HashSet<>(listaAccessiDatiPiano);
        log.debug("Numero Accessi: " + listaAccessiDatiPiano + " - Numero Accessi Senza Duplicati: " + setAccessiDatiPiano);
        return setAccessiDatiPiano.size() > setAccessiDatiPiano.size();
    }

    private List<Accesso> getListaAccessiDatiPiano(DatiPiano datiPiano) {
        List<Accesso> accessiDatiPiano = new ArrayList<>();
        for (Aula aula : datiPiano.getAuleResponsabile()) {
            accessiDatiPiano.addAll(aula.getAccessi());
        }
        return accessiDatiPiano;
    }

    public List<DatiAccessiMese> calcolaDatiAccessiMese(List<Aula> aule) {
        List<DatiAccessiMese> listaDatiAccessiMese = new ArrayList<>();
        Map<Month, List<Accesso>> mappaAccessiMese = getMappaAccessiMese(aule);
        for (Month mese : mappaAccessiMese.keySet()) {
            List<Accesso> accessiMese = mappaAccessiMese.get(mese);
            List<String> motivazioniFrequenti = cercaMotivazioniFrequenzaMax(accessiMese);
            int permanenzaTotale = calcolaPermanenzaTotale(accessiMese);
            log.debug("Mese: " + mese + "- Numero Accessi: " + accessiMese.size() + " - Motivazioni Frequenti: " + motivazioniFrequenti + " - Permanenza Totale: " + permanenzaTotale);
            DatiAccessiMese datiAccessiMese = new DatiAccessiMese(mese, motivazioniFrequenti, permanenzaTotale);
            listaDatiAccessiMese.add(datiAccessiMese);
        }
        Collections.sort(listaDatiAccessiMese);
        return listaDatiAccessiMese;
    }

    private Map<Month, List<Accesso>> getMappaAccessiMese(List<Aula> aule) {
        Map<Month, List<Accesso>> mappaAccessiMese = new HashMap<>();
        for (Aula aula : aule) {
            for (Accesso accesso : aula.getAccessi()) {
                Month mese = accesso.getDataOra().getMonth();
                List<Accesso> accessiMese = mappaAccessiMese.get(mese);
                if (accessiMese == null) {
                    accessiMese = new ArrayList<>();
                    mappaAccessiMese.put(mese, accessiMese);
                }
                accessiMese.add(accesso);
            }
        }
        return mappaAccessiMese;
    }

//    private List<String> cercaMotivazioniFrequenzaMax(List<Accesso> accessiMese) {
//        String motivazioneFrequenzaMax = null;
//        Map<String, Integer> mappaOccorrenzeMotivazione = getMappaOccorrenzeMotivazione(accessiMese);
//        for (String motivazione : mappaOccorrenzeMotivazione.keySet()) {
//            if (motivazioneFrequenzaMax == null || mappaOccorrenzeMotivazione.get(motivazione) > mappaOccorrenzeMotivazione.get(motivazioneFrequenzaMax)) {
//                motivazioneFrequenzaMax = motivazione;
//            }
//        }
//        List<String> motivazioniFrequenti = getListaMotivazioniFrequenti(mappaOccorrenzeMotivazione,motivazioneFrequenzaMax); 
//        return motivazioniFrequenti;
//    }
    
    private List<String> cercaMotivazioniFrequenzaMax(List<Accesso> accessiMese) {
        List<String> motivazioniFrequenti = new ArrayList<>();
        Map<String, Integer> mappaOccorrenzeMotivazione = getMappaOccorrenzeMotivazione(accessiMese);
        List<Integer> valoriMappa = new ArrayList<>(mappaOccorrenzeMotivazione.values());
        Collections.sort(valoriMappa);
        Integer occorrenzaFrequenzaMax = valoriMappa.get(valoriMappa.size()-1);
        for (String motivazione : mappaOccorrenzeMotivazione.keySet()) {
            if (mappaOccorrenzeMotivazione.get(motivazione) == occorrenzaFrequenzaMax) {
                motivazioniFrequenti.add(motivazione);
            }
        }
        return motivazioniFrequenti;
    }

    private int calcolaPermanenzaTotale(List<Accesso> accessiMese) {
        int permanenzaTotale = 0;
        for (Accesso accesso : accessiMese) {
            log.debug("Studente: " + accesso.getStudente() + " - Permanenza: " + accesso.getPermanenza());
            permanenzaTotale += accesso.getPermanenza();
        }
        return permanenzaTotale;
    }

    private Map<String, Integer> getMappaOccorrenzeMotivazione(List<Accesso> accessiMese) {
        Map<String, Integer> mappaOccorrenzeMotivazione = new HashMap<>();
        for (Accesso accesso : accessiMese) {
            String motivazione = accesso.getMotivazione();
            Integer occorrenze = mappaOccorrenzeMotivazione.get(motivazione);
            if (occorrenze == null) {
                mappaOccorrenzeMotivazione.put(motivazione, 1);
            } else {
                mappaOccorrenzeMotivazione.put(motivazione, occorrenze + 1);
            }
        }
        return mappaOccorrenzeMotivazione;
    }

//    private List<String> getListaMotivazioniFrequenti(Map<String, Integer> mappaOccorrenzeMotivazione, String motivazioneFrequenzaMax) {
//        List<String> motivazioniFrequenti = new ArrayList<>();
//        for (String motivazione : mappaOccorrenzeMotivazione.keySet()) {
//            if (mappaOccorrenzeMotivazione.get(motivazione) == mappaOccorrenzeMotivazione.get(motivazioneFrequenzaMax)) {
//                motivazioniFrequenti.add(motivazione);
//            }
//        }
//        return motivazioniFrequenti;
//    }
}
