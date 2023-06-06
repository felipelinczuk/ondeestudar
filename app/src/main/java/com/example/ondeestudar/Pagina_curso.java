package com.example.ondeestudar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Pagina_curso extends AppCompatActivity {

    TextView textView_instituicao, textView_curso, textView_descricao;
    ImageView imageView_minimap;
    Curso cursoSelecionado;
    String descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_curso);
        getSupportActionBar().hide();

        textView_instituicao = findViewById(R.id.textView_instituicao);
        textView_curso = findViewById(R.id.textView_curso);
        textView_descricao = findViewById(R.id.textView_descricao);
        imageView_minimap = findViewById(R.id.imageView_minimap);

        Intent intent = getIntent();
        cursoSelecionado = (Curso) intent.getSerializableExtra("cursoSelecionado");

        textView_instituicao.setText(cursoSelecionado.getInstituicao());
        textView_curso.setText(cursoSelecionado.getCurso());
        descricao = "Campus: " + cursoSelecionado.getCampus() + "\nEndereço: " + cursoSelecionado.getEndereco_campus() + "\n\nValor mensalidade: R$" + cursoSelecionado.getMensalidade() + "\n\nPeríodos:\nDiurno(" + getBoolean(cursoSelecionado.isDiurno()) + ")\nNoturno(" + getBoolean(cursoSelecionado.isNoturno()) + ")\n\nModalidades:\nPresencial(" + getBoolean(cursoSelecionado.isPresencial()) + ")\nEAD(" + getBoolean(cursoSelecionado.isEad()) + ")\nSemi-presencial(" + getBoolean(cursoSelecionado.isSemi_presencial()) + ")\n\nContatos:\nTelefone(" + cursoSelecionado.getTel_instituicao() + ")\nSite(" + cursoSelecionado.getSite_instituicao() + ").";
        textView_descricao.setText(descricao);

        if(cursoSelecionado.getEndereco_campus().equals("Av. Brigadeiro Luís Antônio, 846 - Bela Vista")){
            imageView_minimap.setImageResource(R.drawable.fmu_belavista);
        }
        if(cursoSelecionado.getEndereco_campus().equals("Av. da Liberdade, 899 - Liberdade")){
            imageView_minimap.setImageResource(R.drawable.fmu_liberdade);
        }
        if(cursoSelecionado.getEndereco_campus().equals("R. Dr. Almeida Lima, 1.134 – Mooca")){
            imageView_minimap.setImageResource(R.drawable.anhembi_mooca);
        }
        if(cursoSelecionado.getEndereco_campus().equals("Av. Yojiro Takaoka, 3500 - Alphaville")){
            imageView_minimap.setImageResource(R.drawable.unip_alphaville);
        }
        if(cursoSelecionado.getEndereco_campus().equals("R. Casa do Ator, 275 - Vila Olímpia")){
            imageView_minimap.setImageResource(R.drawable.anhembi_vlolimpia);
        }


    }

    public String getBoolean(Boolean bool) {
        if (bool == true) {
            return "Sim";
        } else {
            return "Não";
        }
    }
}