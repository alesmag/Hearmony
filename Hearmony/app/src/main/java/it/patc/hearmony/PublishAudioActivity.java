package it.patc.hearmony;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.patc.hearmony.classes.AutoCompleteProfileAdapter;
import it.patc.hearmony.classes.ProfileItem;
import it.patc.hearmony.classes.Utente;

public class PublishAudioActivity extends AppCompatActivity
{
    private List<ProfileItem> profilelist;
    ImageButton backarrow;
    Button pubblica;
    Utente utente;
    EditText audio_titolo;
    TextView label;

    LinearLayout tags_section, friends_section;

    Switch onOffSwitch;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_audio);

        utente = (Utente) getIntent().getSerializableExtra("user");

        backarrow = findViewById(R.id.back_arrow_edit_audio);
        pubblica = findViewById(R.id.pubblica);
        audio_titolo = findViewById(R.id.audio_titolo);
        onOffSwitch = findViewById(R.id.switchButton);
        label = findViewById(R.id.label);
        tags_section = findViewById(R.id.tags_section);
        friends_section = findViewById(R.id.friends_section);
        fillProfileList();

        AutoCompleteTextView editText = findViewById(R.id.amici);
        AutoCompleteProfileAdapter adapter = new AutoCompleteProfileAdapter(this, profilelist);
        editText.setAdapter(adapter);

        pubblica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                /* Faccio tornare l'utente al suo profilo se non ci sono errori*/
                if(checkInput())
                {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.putExtra("user", utente);
                    finish();
                    startActivity(intent);
                }
            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Faccio tornare l'utente alla schermata di modifica audio */
                Intent intent = new Intent(getApplicationContext(), EditAudioActivity.class);
                intent.putExtra("user", utente);
                finish();
                startActivity(intent);
            }
        });

        onOffSwitch.setOnClickListener(v->
        {
            if(onOffSwitch.isChecked())
            {
                label.setText("Pubblico");
                tags_section.setVisibility(View.VISIBLE);
                friends_section.setVisibility(View.GONE);
            }
            else
            {
                label.setText("Solo amici");
                tags_section.setVisibility(View.GONE);
                friends_section.setVisibility(View.VISIBLE);
            }
        });
    }

    private void fillProfileList()
    {
        profilelist = new ArrayList<>();
        profilelist.add(new ProfileItem("Giorgiaofficial9", R.drawable.user1));
        profilelist.add(new ProfileItem("Giada3", R.drawable.user2));
        profilelist.add(new ProfileItem("Giacomo_ferreri_83", R.drawable.user3));
        profilelist.add(new ProfileItem("Giuseppe4316", R.drawable.user4));
        profilelist.add(new ProfileItem("GiuliaBizWellness123", R.drawable.user5));
        profilelist.add(new ProfileItem("Gianni.Podcaster_87", R.drawable.user6));
        profilelist.add(new ProfileItem("Gabribros", R.drawable.araragi));
    }

    // Metodo che controlla i dati inseriti
    protected boolean checkInput()
    {
        int errors = 0;

        // reset degli alert d'errore
        audio_titolo.setError(null);

        // mostra un alert d'errore se il campo è vuoto o nullo
        if (audio_titolo.getText() == null || audio_titolo.getText().length() == 0)
        {
            audio_titolo.setError("Inserire un titolo");
            errors++;
        }

        return errors == 0;
    }
}