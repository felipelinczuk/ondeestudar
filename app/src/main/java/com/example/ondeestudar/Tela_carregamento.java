package com.example.ondeestudar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Tela_carregamento extends AppCompatActivity {
    DAO bdHelper = new DAO(this);
    ProgressBar progressBar;
    Curso curso = new Curso();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_carregamento);
        progressBar = findViewById(R.id.progressBar_carregamento);
        getSupportActionBar().hide();

        countDownTimer.start();

        try{
            gerarCursos("Análise e desenvolvimento de sistemas", "FMU", "Liberdade", "Av. Brigadeiro Luís Antônio, 846 - Bela Vista", 799.90, true, true, true, true, false, "(11)3132-3000", "https://portal.fmu.br/cursos/graduacao/analise-e-desenvolvimento-de-sistemas/");
            gerarCursos("Ciência da computação", "FMU", "Liberdade", "Av. da Liberdade, 899 - Liberdade", 1399.90, true, true, true, false, true, "(11)3132-3000", "https://portal.fmu.br/cursos/graduacao/ciencia-da-computacao/");
            gerarCursos("Engenharia de software", "Anhembi morumbi", "Mooca", "R. Dr. Almeida Lima, 1.134 – Mooca", 1879.99, false, true, true, false, false, "(11)4007-1192", "https://portal.anhembi.br/graduacao/engenharia-de-software/");
            gerarCursos("Segurança da informação", "UNIP", "Alphaville", "Av. Yojiro Takaoka, 3500 - Alphaville", 2559.99, true, false, true, true, false, "(11)94303-5000", "https://www.unip.br/cursos/graduacao/tecnologicos/seguranca_informacao.aspx");
            gerarCursos("Análise e desenvolvimento de sistemas", "Anhembi morumbi", "Vila Olímpia", "R. Casa do Ator, 275 - Vila Olímpia", 859.89, false, true, true, true, true, "(11)4007-1192", "https://portal.anhembi.br/graduacao/analise-e-desenvolvimento-de-sistemas/");

            Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Log.e("LogBD", "Exception: " + e.getMessage());
        }


    }

    private CountDownTimer countDownTimer = new CountDownTimer(10000, 100) {
        public void onTick(long millisUntilFinished) {
            progressBar.setProgress(Math.abs((int) millisUntilFinished / 100 - 100));
        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
    };

    private void gerarCursos(String ncurso, String instituicao, String campus, String endereco_campus, Double mensalidade, boolean diurno, boolean noturno, boolean presencial, boolean ead, boolean semi_presencial, String tel_instituicao, String site_instituicao){
        curso.setCurso(ncurso);
        curso.setInstituicao(instituicao);
        curso.setCampus(campus);
        curso.setEndereco_campus(endereco_campus);
        curso.setMensalidade(mensalidade);
        curso.setPresencial(presencial);
        curso.setEad(ead);
        curso.setSemi_presencial(semi_presencial);
        curso.setTel_instituicao(tel_instituicao);
        curso.setSite_instituicao(site_instituicao);

        bdHelper.cadastrarCurso(curso);
    }

}