package it.patc.hearmony;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import it.patc.hearmony.classes.Playlist;
import it.patc.hearmony.classes.Utente;

public class HomeActivity extends AppCompatActivity {

    /* Definizione Attributi */
    Utente utente;
    ArrayList <ImageButton> ultimi_ascolti = new ArrayList<>();
    ArrayList <TextView> ultimi_ascolti_nomi = new ArrayList<>();
    ArrayList <ImageButton> tendenze = new ArrayList<>();
    ArrayList <TextView> tendenze_nomi = new ArrayList<>();
    ArrayList <ImageButton> consigliati = new ArrayList<>();
    ArrayList <TextView> consigliati_nomi = new ArrayList<>();
    ArrayList <ImageButton> perte = new ArrayList<>();
    ArrayList <TextView> perte_nomi = new ArrayList<>();

    /* Apertura della Schermata */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /* Recupero l'utente dal login / registrazione */
        utente = (Utente) getIntent().getSerializableExtra("user");

        /* Recupero gli elementi della schermata */
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        ultimi_ascolti.add(findViewById(R.id.playlist1));
        ultimi_ascolti_nomi.add(findViewById(R.id.nomeplaylist1));
        ultimi_ascolti.add(findViewById(R.id.playlist2));
        ultimi_ascolti_nomi.add(findViewById(R.id.nomeplaylist2));
        ultimi_ascolti.add(findViewById(R.id.playlist3));
        ultimi_ascolti_nomi.add(findViewById(R.id.nomeplaylist3));
        ultimi_ascolti.add(findViewById(R.id.playlist4));
        ultimi_ascolti_nomi.add(findViewById(R.id.nomeplaylist4));
        tendenze.add(findViewById(R.id.playlist5));
        tendenze_nomi.add(findViewById(R.id.nomeplaylist5));
        tendenze.add(findViewById(R.id.playlist6));
        tendenze_nomi.add(findViewById(R.id.nomeplaylist6));
        tendenze.add(findViewById(R.id.playlist7));
        tendenze_nomi.add(findViewById(R.id.nomeplaylist7));
        tendenze.add(findViewById(R.id.playlist8));
        tendenze_nomi.add(findViewById(R.id.nomeplaylist8));
        consigliati.add(findViewById(R.id.playlist9));
        consigliati_nomi.add(findViewById(R.id.nomeplaylist9));
        consigliati.add(findViewById(R.id.playlist10));
        consigliati_nomi.add(findViewById(R.id.nomeplaylist10));
        consigliati.add(findViewById(R.id.playlist11));
        consigliati_nomi.add(findViewById(R.id.nomeplaylist11));
        consigliati.add(findViewById(R.id.playlist12));
        consigliati_nomi.add(findViewById(R.id.nomeplaylist12));
        perte.add(findViewById(R.id.playlist13));
        perte_nomi.add(findViewById(R.id.nomeplaylist13));
        perte.add(findViewById(R.id.playlist14));
        perte_nomi.add(findViewById(R.id.nomeplaylist14));
        perte.add(findViewById(R.id.playlist15));
        perte_nomi.add(findViewById(R.id.nomeplaylist15));
        perte.add(findViewById(R.id.playlist16));
        perte_nomi.add(findViewById(R.id.nomeplaylist16));

        /* Gestisco la pressione delle playlists */
        gestisciPlaylist(ultimi_ascolti.get(0), new Playlist("Dark Academia", "Live without fear, discover yourself.", R.drawable.darkacademia), ultimi_ascolti_nomi.get(0));
        gestisciPlaylist(ultimi_ascolti.get(1), new Playlist("Architects", "For Thoose That Wish to Exist!", R.drawable.architects), ultimi_ascolti_nomi.get(1));
        gestisciPlaylist(ultimi_ascolti.get(2), new Playlist("Negramaro", "Mentre tutto scorre...", R.drawable.negramaro), ultimi_ascolti_nomi.get(2));
        gestisciPlaylist(ultimi_ascolti.get(3), new Playlist("Kavisnky", "Nightcall", R.drawable.nightcall), ultimi_ascolti_nomi.get(3));
        gestisciPlaylist(tendenze.get(0), new Playlist("Good Vibes", "La migliore musica e audio ambientali per il tuo relax!", R.drawable.goodvibes), tendenze_nomi.get(0));
        gestisciPlaylist(tendenze.get(1), new Playlist("Raggaeton Mix", "Ascolta gli ultimi brani di JBalvin, Ozuna...", R.drawable.raggaetonmix), tendenze_nomi.get(1));
        gestisciPlaylist(tendenze.get(2), new Playlist("Classics", "Ascolta i grandi classici di tutti i tempi!", R.drawable.classics), tendenze_nomi.get(2));
        gestisciPlaylist(tendenze.get(3), new Playlist("Rain", "Rumori di pioggia e tuoni per i tuoi...", R.drawable.rain), tendenze_nomi.get(3));
        gestisciPlaylist(consigliati.get(0), new Playlist("Metalhead Interviews", "Listen to the last interview to metal artist!", R.drawable.metalhead), consigliati_nomi.get(0));
        gestisciPlaylist(consigliati.get(1), new Playlist("Phonk Releases", "Listen to the best phonk music ever.", R.drawable.phonk), consigliati_nomi.get(1));
        gestisciPlaylist(consigliati.get(2), new Playlist("PETA", "Ultime notizie sulla PETA", R.drawable.peta), consigliati_nomi.get(2));
        gestisciPlaylist(consigliati.get(3), new Playlist("Climate Change", "Ultime notizie sul cambiamento climatico!", R.drawable.climatechange), consigliati_nomi.get(3));
        gestisciPlaylist(perte.get(0), new Playlist("Horror", "Discover the horror tag...", R.drawable.horrortag), perte_nomi.get(0));
        gestisciPlaylist(perte.get(1), new Playlist("Dance", "Discover the dance tag...", R.drawable.dancetag), perte_nomi.get(1));
        gestisciPlaylist(perte.get(2), new Playlist("Consigli Finanziari", "Ascolta i consigli finanziari di Andrew Tate...", R.drawable.crypto), perte_nomi.get(2));
        gestisciPlaylist(perte.get(3), new Playlist("Sardegna", "Ultime notizie regione Sardegna...", R.drawable.sardegna), perte_nomi.get(3));

        /* Gestisco la navbar */
        bottomNavigationView.setOnItemSelectedListener(item ->
        {
            if(item.getItemId() == R.id.nav_home)
                return true;

            else if(item.getItemId() == R.id.nav_search)
            {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra("user", utente);
                startActivity(intent);
                return true;
            }

            else if(item.getItemId() == R.id.nav_addaudio)
            {
                Intent intent = new Intent(getApplicationContext(), AddAudioActivity.class);
                intent.putExtra("user", utente);
                startActivity(intent);
                return true;
            }

            else if(item.getItemId() == R.id.nav_profile)
            {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("user", utente);
                startActivity(intent);
                return true;
            }

            else if(item.getItemId() == R.id.nav_segreteria)
            {
                Playlist segreteria = new Playlist("Segreteria", "Audio inviati dai tuoi amici", 0);
                Intent intent = new Intent(getApplicationContext(), SegreteriaActivity.class);
                intent.putExtra("user", utente);
                intent.putExtra("playlist", segreteria);
                startActivity(intent);
                return true;
            }

            return false;
        });
    }

    public void gestisciPlaylist (ImageButton b, Playlist pl, TextView titolo) {

        /* Modifico il titolo */
        titolo.setText(pl.getNome());

        /* Imposto l'evento per il click */
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Trasporto l'utente alla PlaylistActivity */
                Intent i = new Intent(getApplicationContext(), PlaylistActivity.class);
                i.putExtra("user", utente);
                i.putExtra("playlist", pl);
                startActivity(i);
            }
        });
    }


}