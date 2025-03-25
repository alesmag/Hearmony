package it.patc.hearmony;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import it.patc.hearmony.classes.Data;
import it.patc.hearmony.classes.Utente;

public class RegisterActivity extends AppCompatActivity
{
    Utente utente;
    Data data;
    EditText register_email;
    EditText register_user;
    EditText register_dataN;
    EditText register_pass;
    TextView error_email;
    TextView error_username;
    TextView error_dataN;
    TextView error_pass;
    Button register_button;
    ImageButton backarrow;
    Calendar c = Calendar.getInstance();
    int anno = c.get(Calendar.YEAR);
    int mese = c.get(Calendar.MONTH);
    int giorno = c.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        utente = new Utente();
        register_email = findViewById(R.id.register_email);
        register_user = findViewById(R.id.register_user);
        register_dataN = findViewById(R.id.register_dataN);
        register_pass = findViewById(R.id.register_pass);
        register_button = findViewById(R.id.register_button);
        backarrow = findViewById(R.id.back_arrow_login);

        register_dataN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        register_dataN.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        data = new Data();
                        data.setGiorno(dayOfMonth);
                        data.setMese(monthOfYear+1);
                        data.setAnno(year);
                    }
                }, anno, mese, giorno);
                datePickerDialog.show();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /* Spedisco l'utente alla HomePage se non ci sono errori */
                if(checkInput())
                {
                    /* Aggiorna i dati dell'utente */
                    updateUtente();

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("user", utente);
                    finish();
                    startActivity(intent);
                }
            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Faccio tornare l'utente alla schermata di login */
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    protected void updateUtente()
    {
        utente.setEmail(register_email.getText().toString());
        utente.setNomeUtente(register_user.getText().toString());
        utente.setDataNascita(data);
        utente.setPassword(register_pass.getText().toString());
    }

    // Metodo che controlla i dati inseriti
    protected boolean checkInput()
    {
        int errors = 0;

        // reset degli alert d'errore
        register_email.setError(null);
        register_user.setError(null);
        register_dataN.setError(null);
        register_pass.setError(null);

        // mostra un alert d'errore se il campo è vuoto o nullo
        if (register_email.getText() == null || register_email.getText().length() == 0)
        {
            register_email.setError("Inserire una email");
            errors++;
        }

        // mostra un alert d'errore se il campo è vuoto o nullo
        if(register_user.getText() == null || register_user.getText().length() == 0)
        {
            register_user.setError("Inserire un nome utente");
            errors++;
        }

        // mostra un alert d'errore se il campo è vuoto o nullo
        if(register_dataN.getText() == null || register_dataN.getText().length() == 0 || data.getAnno() < 1920)
        {
            register_dataN.setError("Inserire una data valida");
            errors++;
        }

        // mostra un alert d'errore se il campo è vuoto o nullo
        if(register_pass.getText() == null || register_pass.getText().length() == 0)
        {
            register_pass.setError("Inserire una password");
            errors++;
        }

        return (errors == 0);
    }
}