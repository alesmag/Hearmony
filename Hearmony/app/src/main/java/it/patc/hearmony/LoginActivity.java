package it.patc.hearmony;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import it.patc.hearmony.classes.*;

public class LoginActivity extends AppCompatActivity
{
    Utente matteo, smag, chiara, scoc;
    ArrayList<Utente> profiliRegistrati;
    EditText login_user;
    EditText login_pass;
    Button register_link;
    Button login_button;
    String userView, passView;
    TextView error_both;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        position = -1;
        profiliRegistrati = new ArrayList<Utente>();

        login_user = findViewById(R.id.login_user);
        login_pass = findViewById(R.id.login_pass);
        login_button = findViewById(R.id.login_button);
        register_link = findViewById(R.id.register_link);
        error_both = findViewById(R.id.error_both);


        matteo = new Utente("m.aresu38", "18elode", "m.aresu38@studenti.unica.it", R.drawable.fordpines, new Data(10,11,2002));
        matteo.setCaricamenti(13);
        matteo.setDescrizione("Admin ufficiale di Hearmony!");
        matteo.setFollower(14000);
        matteo.setSeguiti(3);
        matteo.addAudio(new Audio("Audio di Prova", "Niente di particolare!", matteo, "Pubblico", "09/02/2024", R.raw.prova1));

        smag = new Utente("a.muggittu", "skibidi", "a.muggittu@studenti.unica.it", R.drawable.smag, new Data(10, 8, 2001));
        smag.setCaricamenti(52);
        smag.setDescrizione("Secondo admin in carica ig?");
        smag.setFollower(2);
        smag.setSeguiti(5555);

        chiara = new Utente("a.mameli31", "panda", "a.mameli31@studenti.unica.it", R.drawable.panda, new Data(27, 7, 2002));
        chiara.setCaricamenti(5);
        chiara.setDescrizione("Esperta d'immagine.");
        chiara.setFollower(1000);
        chiara.setSeguiti(3);

        scoc = new Utente("m.cocco103", "kyle", "m.cocco103@studenti.unica.it", R.drawable.kyle, new Data(15, 7, 2001));
        scoc.setCaricamenti(190);
        scoc.setDescrizione("Iper grafico");
        scoc.setFollower(2315);
        scoc.setSeguiti(90);

        profiliRegistrati.add(matteo);
        profiliRegistrati.add(scoc);
        profiliRegistrati.add(chiara);
        profiliRegistrati.add(smag);

        login_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /* Spedisco l'utente alla homepage se non vi sono errori */
                if(checkInput() && hasAccount(login_user.getText().toString(), login_pass.getText().toString()) != null)
                {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("user", profiliRegistrati.get(position));
                    finish();
                    startActivity(intent);
                }
            }
        });

        register_link.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    // controlla che un utente possieda un account
    protected Utente hasAccount(String username, String password)
    {
        for(int cont = 0; cont < profiliRegistrati.size(); cont++)
        {
            if (username.equals(profiliRegistrati.get(cont).getNomeUtente()) &&
                password.equals(profiliRegistrati.get(cont).getPassword()))
            {
                position = cont;
                return profiliRegistrati.get(cont);
            }
        }

        return null;
    }

    // Metodo che controlla i dati inseriti
    protected boolean checkInput()
    {
        int errors = 0;
        boolean flag = true;

        // reset degli alert d'errore
        login_user.setError(null);
        login_pass.setError(null);
        error_both.setVisibility(View.INVISIBLE);

        // mostra un alert d'errore se il campo è vuoto o nullo
        if (login_user.getText() == null || login_user.getText().length() == 0)
        {
            login_user.setError("Inserire un nome utente");
            errors++;
        }

        // mostra un alert d'errore se il campo è vuoto o nullo
        if(login_pass.getText() == null || login_pass.getText().length() == 0)
        {
            login_pass.setError("Inserire una password");
            errors++;
        }

        if(login_user.getText() != null && login_user.getText().length() != 0
            && login_pass.getText() != null && login_pass.getText().length() != 0
            && hasAccount(login_user.getText().toString(), login_pass.getText().toString()) == null)
        {
            error_both.setText("Username o Password errati");
            error_both.setVisibility(View.VISIBLE);
        }

        return (errors == 0);
    }
}