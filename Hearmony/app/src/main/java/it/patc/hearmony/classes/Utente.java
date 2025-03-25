/* Definizione Package */
package it.patc.hearmony.classes;

import java.io.Serializable;
import java.util.Date;

import it.patc.hearmony.R;

/* Definizione Classe Utente */
public class Utente implements Serializable
{
    /* Definizione Attributi */
    private String nomeUtente;
    private String password;
    private String email;
    private int immagine;
    private Data dataNascita;
    private Playlist audioPersonali;
    private String descrizione;
    private int seguiti, follower, caricamenti;

    /* Metodi Costruttore */
    public Utente (String nomeUtente, String password, String email, int immagine, Data dataNascita)
    {
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.email = email;
        this.immagine = immagine;
        this.dataNascita = dataNascita;
        this.audioPersonali = new Playlist("Personale di " + nomeUtente, "Playlist personale.");
        this.descrizione = "Benvenuti nel mio profilo personale!";
        this.seguiti = 0;
        this.follower = 0;
        this.caricamenti = 4;
    }

    public Utente()
    {
        this.nomeUtente = "Albertino98";
        this.password = "GigiRivaMioIdolo";
        this.email = "forzacagliariyeah@gmail.com";
        this.immagine = R.drawable.black;
        this.dataNascita = new Data(1, 1, 1970);
        this.audioPersonali = new Playlist("Personale di " + nomeUtente, "Playlist personale.");
        this.descrizione = "";
        this.seguiti = 0;
        this.follower = 0;
        this.caricamenti = 0;
    }

    /* Metodi Get */
    public String getNomeUtente() { return nomeUtente; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public int getImmagine() { return immagine; }
    public Data getDataNascita() { return dataNascita; }
    public Playlist getAudioPersonali() { return audioPersonali; }
    public String getDescrizione() { return descrizione; }
    public int getSeguiti() { return seguiti; }
    public int getFollower() { return follower; }
    public int getCaricamenti() { return caricamenti; }

    /* Metodi Set */
    public void setNomeUtente(String x) { nomeUtente = x; }
    public void setPassword(String x) { password = x; }
    public void setEmail(String x) { email = x; }
    public void setImmagine(int x) { immagine = x; }
    public void setDataNascita(Data x) { dataNascita = x; }
    public void setDescrizione(String x) { descrizione = x; }
    public void setSeguiti(int x) { seguiti = x; }
    public void setFollower(int x) { follower = x; }
    public void setCaricamenti(int x) { caricamenti = x; }

    /* Altri Metodi */
    @Override
    public String toString()
    {
        return nomeUtente + ", " + password + ", " + email + ", " + immagine + " ," + dataNascita;
    }

    /* Metodi per aggiungere e rimuovere audio */
    public void addAudio(Audio nuovo) { audioPersonali.aggiungiAudio(nuovo); }
    public void removeAudio(int position) { audioPersonali.rimuoviAudio(position); }

}
