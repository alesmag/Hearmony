package it.patc.hearmony;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import it.patc.hearmony.classes.Utente;

public class EditAudioActivity extends AppCompatActivity {
    ImageButton backarrow;
    ImageButton elimina;
    ImageButton avanti;
    Utente utente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_audio);

        utente = (Utente) getIntent().getSerializableExtra("user");

        backarrow = findViewById(R.id.back_arrow_add_audio);
        elimina = findViewById(R.id.elimina);
        avanti = findViewById(R.id.avanti);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Faccio tornare l'utente alla schermata di registrazione audio */
                Intent intent = new Intent(getApplicationContext(), AddAudioActivity.class);
                intent.putExtra("user", utente);
                finish();
                startActivity(intent);
            }
        });

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(EditAudioActivity.this);
                alert.setMessage("Eliminare l'audio?");

                alert.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /* Faccio tornare l'utente alla schermata home */
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("user", utente);
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // niente
                    }
                });

                alert.create().show();
            }
        });

        avanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Spedisco l'utente alla pagina di pubblicazione audio */
                Intent intent = new Intent(getApplicationContext(), PublishAudioActivity.class);
                intent.putExtra("user", utente);
                startActivity(intent);
            }
        });
    }
}