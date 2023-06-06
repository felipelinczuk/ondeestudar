package com.example.ondeestudar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;



public class Cadastro extends AppCompatActivity {

    EditText editText_nome, editText_dataNasc, editText_email, editText_password, editText_endereco;
    Button button_cadastrar;
    Usuario usuario = new Usuario();
    DAO bdHelper = new DAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();


        Random random = new Random();

        editText_nome = findViewById(R.id.editText_nome);
        editText_dataNasc = findViewById(R.id.editText_dataNasc);
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        editText_endereco = findViewById(R.id.editText_endereco);
        button_cadastrar = findViewById(R.id.button_cadastrar);
        button_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editText_nome.getText().toString().trim().length() != 0 && editText_dataNasc.getText().toString().trim().length() !=0 && editText_email.getText().toString().trim().length() != 0){
                    usuario.setId(random.nextInt(9999));
                    usuario.setNome(editText_nome.getText().toString());
                    usuario.setData_nasc(editText_dataNasc.getText().toString());
                    usuario.setEmail(editText_email.getText().toString());
                    usuario.setEndereco(editText_endereco.getText().toString());
                    usuario.setPassword(editText_password.getText().toString());

                    try{
                        bdHelper.cadastrarUsuario(usuario);
                        Toast.makeText(getApplicationContext(), "Sucesso!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos obrigatórios!", Toast.LENGTH_LONG).show();
                }



            }
        });
    }


}