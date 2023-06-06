package com.example.ondeestudar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Resultado_busca extends AppCompatActivity {
    ListView listView_cursos;
    DAO bdHelper = new DAO(this);
    Curso curso;
    ArrayList<Curso> cursos = new ArrayList<>();
    ArrayList<Curso> result = new ArrayList<>();
    ArrayAdapter adapter;
    String cursoInstituicao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_busca);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        cursoInstituicao = intent.getStringExtra("cursoInstituicao");
        listView_cursos = findViewById(R.id.listView_cursos);

        listarCursos(cursoInstituicao);

        listView_cursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long l) {
                Curso cursoSelecionado = (Curso) adapter.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), Pagina_curso.class);
                intent.putExtra("cursoSelecionado", cursoSelecionado);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarCursos(cursoInstituicao);
    }

    public void listarCursos(String cursoInstituicao){
        try{
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cursos);
            listView_cursos.setAdapter(adapter);
            result = bdHelper.getCursos(cursoInstituicao);

            if(result == null){
                Toast.makeText(getApplicationContext(), "Nenhum curso encontrado!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
            else{
                cursos = result;
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}