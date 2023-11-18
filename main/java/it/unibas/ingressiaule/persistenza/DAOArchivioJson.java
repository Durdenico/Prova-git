/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unibas.ingressiaule.persistenza;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import it.unibas.ingressiaule.modello.Archivio;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Domenico
 */
@Slf4j
public class DAOArchivioJson implements IDAOArchivio {

    @Override
    public Archivio carica(String nomeFile) throws DAOException {
        BufferedReader flusso = null;
        try {
            flusso = new BufferedReader(new FileReader(nomeFile));
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            builder.registerTypeAdapter(LocalDate.class, new AdapterLocalDate());
            builder.registerTypeAdapter(LocalDateTime.class, new AdapterLocalDateTime());
            Archivio archivio = gson.fromJson(flusso, Archivio.class);
            return archivio;
        } catch (Exception e) {
            log.debug("Eccezione durante il caricamento: " + e);
            throw new DAOException(e);
        } finally {
            if (flusso != null) {
                try {
                    flusso.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    public void salva(String nomeFile, Archivio archivio) throws DAOException {
        PrintWriter flusso = null;
        try {
            flusso = new PrintWriter(new FileWriter(nomeFile));
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            String stringaJson = gson.toJson(archivio);
            flusso.print(stringaJson);
        } catch (Exception e) {
            log.debug("Eccezione durante il salvataggio: " + e);
            throw new DAOException(e);
        } finally {
            if (flusso != null) {
                flusso.close();
            }
        }
    }

    private class AdapterLocalDate implements JsonDeserializer<LocalDate>, JsonSerializer<LocalDate> {

        @Override
        public LocalDate deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            try {
                String stringaData = je.getAsString();
                DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
                LocalDate data = (LocalDate) df.parse(stringaData);
                return data;
            } catch (Exception e) {
                throw new JsonParseException(e);
            }
        }

        @Override
        public JsonElement serialize(LocalDate t, Type type, JsonSerializationContext jsc) {
            DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
            return new JsonPrimitive(t.format(df));
        }

    }

    private class AdapterLocalDateTime implements JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            try {
                String stringaDataOra = je.getAsString();
                DateTimeFormatter df = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT);
                LocalDateTime dataOra = (LocalDateTime) df.parse(stringaDataOra);
                return dataOra;
            } catch (Exception e) {
                throw new JsonParseException(e);
            }
        }

        @Override
        public JsonElement serialize(LocalDateTime t, Type type, JsonSerializationContext jsc) {
            DateTimeFormatter df = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT);
            return new JsonPrimitive(t.format(df));
        }

    }

}
