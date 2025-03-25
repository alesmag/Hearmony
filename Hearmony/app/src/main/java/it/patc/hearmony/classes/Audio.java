/* Definizione Package */
package it.patc.hearmony.classes;

import java.io.Serializable;

/* Definizione Classe Audio */
public class Audio implements Serializable
{

    /* Definizione Attributis */
    private String nome, descrizione, modificatore;
    private int ascolti;
    private String pubblicatore;
    private int durata;
    private String data;
    private int layout;

    /* Metodi Costruttori */
    public Audio (String nome, String descrizione, Utente utente, String modificatore, String data, int layout) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.pubblicatore = utente.getNomeUtente();
        this.ascolti = 0;
        this.modificatore = modificatore;
        this.durata = 0;
        this.data = data;
        this.layout = layout;
    }

    /* Metodi Get */
    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }
    public String getModificatore() { return modificatore; }
    public int getAscolti() { return ascolti; }
    public String getPubblicatore() { return pubblicatore; }
    public int getDurata() { return durata; }
    public String getData() { return data; }

    /* Metodi Set */
    public void setNome(String x) { nome = x; }
    public void setDescrizione(String x) { descrizione = x; }
    public void setModificatore(String x) { modificatore = x; }
    public void setDurata(int x) { durata = x; }

}