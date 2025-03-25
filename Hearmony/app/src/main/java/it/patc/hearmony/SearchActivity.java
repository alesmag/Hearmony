/* Definizione Package */
package it.patc.hearmony;

/* Import */
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.patc.hearmony.classes.Data;
import it.patc.hearmony.classes.Playlist;
import it.patc.hearmony.classes.Utente;

/* Definizione Activity di Ricerca */
public class SearchActivity extends AppCompatActivity {

    /* Definizione Attributi */
    Utente utente;
    LinearLayout history, searching;
    EditText search_query;
    TextView fakequery;
    ImageView backarrow;
    ArrayList <TextView> tags = new ArrayList<>();
    ArrayList <TextView> nomi = new ArrayList<>();
    ArrayList <TextView> descrizioni = new ArrayList<>();
    ArrayList <ImageView> immagini = new ArrayList<>();
    ArrayList <Utente> utenti = new ArrayList<>();
    ArrayList <TextView> nomi_utente = new ArrayList<>();
    ArrayList<Playlist> tagPlaylist = new ArrayList<>();
    ArrayList<Playlist> historyPlaylist = new ArrayList<>();

    /* Apertura della Schemata */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        /* Recupero l'utente */
        utente = (Utente) getIntent().getSerializableExtra("user");

        /* Imposto le view */
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);
        history = findViewById(R.id.history);
        searching = findViewById(R.id.searching);
        search_query = findViewById(R.id.search_query_empty);
        fakequery = findViewById(R.id.search_query_fake);
        backarrow = findViewById(R.id.back_arrow);

        /* Imposto i nomi dei risultati a schermo */
        tagPlaylist.add(new Playlist("#Sanremo2024", "Ascolta le ultime canzoni di Sanremo 2024.", R.drawable.metalhead));
        tagPlaylist.add(new Playlist("#SangiorgiGiuliano", "SangiorgiGiuliano.", R.drawable.phonk));
        tagPlaylist.add(new Playlist("#SanSperate", "San Sperate", R.drawable.peta));
        tags.add(findViewById(R.id.tag1));
        tags.add(findViewById(R.id.tag2));
        tags.add(findViewById(R.id.tag3));
        for (int cont = 0; cont < tags.size(); cont++) {
            tags.get(cont).setText(tagPlaylist.get(cont).getNome() + "");
            pressPlaylist(tags.get(cont), tagPlaylist.get(cont));
        }
        historyPlaylist.add(new Playlist("Sanremo 2024", "Ascolta le ultime canzoni di Sanremo 2024.", R.drawable.sanremo));
        historyPlaylist.add(new Playlist("Sanitarium OST", "Official Sanitarium soundtrack", R.drawable.sanitarium));
        historyPlaylist.add(new Playlist("Santi Francesi", "Mix delle canzoni dei Santi Francesi", R.drawable.chopper));
        nomi.add(findViewById(R.id.nome_playlist1));
        descrizioni.add(findViewById(R.id.descrizione_playlist1));
        immagini.add(findViewById(R.id.immagine_playlist1));
        nomi.add(findViewById(R.id.nome_playlist2));
        descrizioni.add(findViewById(R.id.descrizione_playlist2));
        immagini.add(findViewById(R.id.immagine_playlist2));
        nomi.add(findViewById(R.id.nome_playlist3));
        descrizioni.add(findViewById(R.id.descrizione_playlist3));
        immagini.add(findViewById(R.id.immagine_playlist3));
        for (int cont = 0; cont < historyPlaylist.size(); cont++) {
            nomi.get(cont).setText(historyPlaylist.get(cont).getNome());
            descrizioni.get(cont).setText(historyPlaylist.get(cont).getDescrizione());
            immagini.get(cont).setImageResource(historyPlaylist.get(cont).getImmagine());
            pressPlaylist(nomi.get(cont), historyPlaylist.get(cont));
        }
        utenti.add(new Utente("SangiovanniOfficial", "supermario3dland", "g.esu18@studenti.unica.it", R.drawable.araragi, new Data(10,11,2002))); //sì, ho avuto la sua approvazione per questa cosa
        utenti.add(new Utente("Sandro Ambrosoni 4316", "mmhhhbuonoilmiele", "m.ambrosoni1@studenti.unica.it", R.drawable.miele, new Data(3,3,2002)));
        nomi_utente.add(findViewById(R.id.nome_profilo1));
        nomi_utente.add(findViewById(R.id.nome_profilo2));
        nomi_utente.get(0).setText(utenti.get(0).getNomeUtente());
        nomi_utente.get(1).setText(utenti.get(1).getNomeUtente());
        pressUtente(nomi_utente.get(0), utenti.get(0));
        pressUtente(nomi_utente.get(1), utenti.get(1));

        /* Se l'utente apre la ricerca, scompare il searching e compare la history */
        search_query.setOnTouchListener((v, event) -> {
            if (MotionEvent.ACTION_UP == event.getAction()) {
                searching.setVisibility(View.VISIBLE);
                history.setVisibility(View.GONE);
                fakequery.setVisibility(View.VISIBLE);
                search_query.setVisibility(View.GONE);
                backarrow.setVisibility(View.VISIBLE);
            }
            return false;
        }); //sì, ho usato le lambda perchè sono figo 8v)

        /* Gestisco la pressione del backarrow */
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searching.setVisibility(View.GONE);
                history.setVisibility(View.VISIBLE);
                fakequery.setVisibility(View.GONE);
                search_query.setVisibility(View.VISIBLE);
                backarrow.setVisibility(View.GONE);
            }
        });

        /* Gestisco la navbar */
        bottomNavigationView.setOnItemSelectedListener(item ->
        {
            if(item.getItemId() == R.id.nav_search)
                return true;

            else if(item.getItemId() == R.id.nav_home)
            {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("user", utente);
                finish();
                startActivity(intent);
                return true;
            }

            else if(item.getItemId() == R.id.nav_addaudio)
            {
                Intent intent = new Intent(getApplicationContext(), AddAudioActivity.class);
                intent.putExtra("user", utente);
                finish();
                startActivity(intent);
                return true;
            }

            else if(item.getItemId() == R.id.nav_profile)
            {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("user", utente);
                finish();
                startActivity(intent);
                return true;
            }

            else if(item.getItemId() == R.id.nav_segreteria)
            {
                Playlist segreteria = new Playlist("Segreteria", "Audio inviati dai tuoi amici", 0);
                Intent intent = new Intent(getApplicationContext(), SegreteriaActivity.class);
                intent.putExtra("user", utente);
                intent.putExtra("playlist", segreteria);
                finish();
                startActivity(intent);
                return true;
            }

            return false;
        });
    }

    /* Metodo per la pressione delle playlist */
    public void pressPlaylist(View tag, Playlist pl) {

        tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Trasporto l'utente alla PlaylistActivity */
                Intent i = new Intent(getApplicationContext(), PlaylistActivity.class);
                i.putExtra("user", utente);
                i.putExtra("playlist", pl);
                i.putExtra("searching", true);
                finish();
                startActivity(i);
            }
        });

    }

    /* Metodo per la pressione degli utenti */
    public void pressUtente(View u, Utente x) {

        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Trasporto l'utente */
                Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                i.putExtra("user", utente);
                i.putExtra("visitor", x);
                finish();
                startActivity(i);
            }
        });

    }

}