package com.example.ondeestudar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    TextView text_bemVindo;
    EditText editText_pesquisar;
    String nome, cursoInstituicao;
    Button button_deslogar;
    int id;
    ImageView image_linkedin, image_biblioteca, image_podcasts, image_artigos, image_descontos, image_calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        editText_pesquisar = findViewById(R.id.editText_pesquisar);
        text_bemVindo = findViewById(R.id.text_bemVindo);
        button_deslogar = findViewById(R.id.button_deslogar);
        image_linkedin = findViewById(R.id.image_linkedin);
        image_biblioteca = findViewById(R.id.image_biblioteca);
        image_podcasts = findViewById(R.id.image_podcasts);
        image_artigos = findViewById(R.id.image_artigos);
        image_descontos = findViewById(R.id.image_descontos);
        image_calendario = findViewById(R.id.image_calendario);

        SharedPreferences userlog = getSharedPreferences("userlog", MODE_PRIVATE);
        nome = userlog.getString("nome","");
        text_bemVindo.setText("Bem vindo, " + nome);

        editText_pesquisar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode==KeyEvent.KEYCODE_ENTER) {
                    if(editText_pesquisar.getText().toString().trim().length() > 0){
                        cursoInstituicao = editText_pesquisar.getText().toString();
                        Intent intent = new Intent(getApplicationContext(), Resultado_busca.class);
                        intent.putExtra("cursoInstituicao", cursoInstituicao);
                        startActivity(intent);

                        return true;
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), Resultado_busca.class);
                        intent.putExtra("cursoInstituicao", " ");
                        startActivity(intent);
                    }
                }
                return false;
            }
        });

        button_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Até logo!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        image_linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://br.linkedin.com/jobs/information-technology-vagas"));
                startActivity(browserIntent);
            }
        });

        image_artigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tecmundo.com.br/tecnologia-da-informacao"));
                startActivity(browserIntent);
            }
        });

        image_descontos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://querobolsa.com.br"));
                startActivity(browserIntent);
            }
        });

        image_podcasts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.zendesk.com.br/blog/podcasts-sobre-tecnologia/"));
                startActivity(browserIntent);
            }
        });

        image_biblioteca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Missbacon/books/tree/main/books"));
                startActivity(browserIntent);
            }
        });

        image_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vestibulares2022.com.br/vestibular-fatec-2022/"));
                startActivity(browserIntent);
            }
        });


    }
}