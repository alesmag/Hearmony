/* Definizione Package */
package it.patc.hearmony.classes;

/* Import */
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* Definizione Classe Playlist */
public class Playlist implements Serializable {

    /* Definizione Attributi */
    private String nome, descrizione;
    private ArrayList<Audio> audio;
    private int immagine;

    /* Metodo Costruttore */
    public Playlist (String nome, String descrizione) {
        audio = new ArrayList<Audio>();
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public Playlist (String nome, String descrizione, int immagine) {
        audio = new ArrayList<Audio>();
        this.nome = nome;
        this.descrizione = descrizione;
        this.immagine = immagine;
    }

    /* Metodi Get */
    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }
    public Audio getAudio(int cont) { return audio.get(cont); }
    public ArrayList<Audio> getAudios() { return audio; }
    public int getImmagine () { return immagine; }

    /* Metodi Set */
    public void setNome(String nome) { this.nome = nome; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public void setImmagine(int nuovo) { immagine = nuovo; }
    public void aggiungiAudio (Audio nuovo) { audio.add(nuovo); }
    public void rimuoviAudio (int position) { audio.remove(position); }

}