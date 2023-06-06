package com.example.ondeestudar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    Button button_acessar;
    EditText editText_email, editText_password;
    Usuario usuario = new Usuario();
    DAO bdHelper = new DAO(this);
    ArrayList<Usuario> usuarioLogado;
    TextView text_novoCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        editText_email = findViewById(R.id.editText_loginEmail);
        editText_password = findViewById(R.id.editText_loginPassword);
        button_acessar = findViewById(R.id.button_acessar);
        text_novoCadastro = findViewById(R.id.text_novoCadastro);

        button_acessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText_email.getText().toString();
                String password = editText_password.getText().toString();

                try{
                    usuarioLogado = bdHelper.getUsuario(email, password);
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

                if(usuarioLogado == null){
                    Toast.makeText(getApplicationContext(), "Usuário não encontrado ou dados incorretos!", Toast.LENGTH_LONG).show();
                }
                else{
                    SharedPreferences userlog = getSharedPreferences("userlog", MODE_PRIVATE);
                    SharedPreferences.Editor editor = userlog.edit();
                    editor.putInt("id", usuarioLogado.get(0).getId());
                    editor.putString("nome", usuarioLogado.get(0).getNome());
                    editor.putString("dataNasc", usuarioLogado.get(0).getData_nasc());
                    editor.putString("email", usuarioLogado.get(0).getEmail());
                    editor.putString("curso", usuarioLogado.get(0).getCurso());
                    editor.putString("endereco", usuarioLogado.get(0).getEndereco());
                    editor.putString("password", usuarioLogado.get(0).getPassword());
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Login efetuado!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                }

            }
        });

        text_novoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Cadastro.class);
                startActivity(intent);
            }
        });





    }
}