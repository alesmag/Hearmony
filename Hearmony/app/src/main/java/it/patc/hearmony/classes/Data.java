/* Definizione Package */
package it.patc.hearmony.classes;

/* Import */
import java.io.Serializable;

/* Definizione Classe Data */
public class Data implements Serializable {

    /* Definizione Attributi */
    private int giorno, mese, anno;

    /* Metodi Costruttori */
    public Data() {
        giorno = 1;
        mese = 1;
        anno = 1995;
    }

    public Data(int giorno, int mese, int anno) {
        this.giorno = giorno;
        this.mese = mese;
        this.anno = anno;
    }

    /* Metodi Get */
    public int getGiorno() { return giorno; }
    public int getMese() { return mese; }
    public int getAnno() { return anno; }

    /* Metodi Set */
    public void setGiorno(int newGiorno) { giorno = newGiorno; }
    public void setMese(int newMese) { mese = newMese; }
    public void setAnno(int newAnno) { anno = newAnno; }

}
