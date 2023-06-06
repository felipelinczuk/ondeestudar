package com.example.ondeestudar;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;



public class DAO extends SQLiteOpenHelper {

    private static final String DATABASE = "OEBD";
    private static final int VERSION = 1;

    public DAO(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {

        String usuarioTB = "CREATE TABLE usuario(" +
                "id INTEGER NOT NULL," +
                "nome TEXT NOT NULL, " +
                "data_nasc TEXT NOT NULL, " +
                "email TEXT PRIMARY KEY NOT NULL, " +
                "curso TEXT, " +
                "endereco TEXT, " +
                "password TEXT NOT NULL);";

        String cursoTB = "CREATE TABLE curso(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "curso TEXT NOT NULL, " +
                "instituicao TEXT NOT NULL," +
                "campus TEXT NOT NULL, " +
                "endereco_campus TEXT NOT NULL, " +
                "mensalidade REAL NOT NULL, " +
                "diurno INTEGER NOT NULL, " +
                "noturno INTEGER NOT NULL, " +
                "presencial INTEGER NOT NULL, " +
                "ead INTEGER NOT NULL, " +
                "semi_presencial INTEGER NOT NULL, " +
                "tel_instituicao TEXT NOT NULL, " +
                "site_instituicao TEXT NOT NULL, UNIQUE(curso, campus, instituicao));";

        bd.execSQL(usuarioTB);
        bd.execSQL(cursoTB);
    }


    public void cadastrarUsuario(Usuario usuario){
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("id", usuario.getId());
        dados.put("nome", usuario.getNome());
        dados.put("data_nasc", usuario.getData_nasc());
        dados.put("email", usuario.getEmail());
        dados.put("curso", usuario.getCurso());
        dados.put("endereco", usuario.getEndereco());
        dados.put("password", usuario.getPassword());

        bd.insertOrThrow("usuario", null, dados);
        bd.close();
    }


    public void cadastrarCurso(Curso curso){
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("curso", curso.getCurso());
        dados.put("instituicao", curso.getInstituicao());
        dados.put("campus", curso.getCampus());
        dados.put("endereco_campus", curso.getEndereco_campus());
        dados.put("mensalidade", curso.getMensalidade());
        dados.put("diurno", curso.isDiurno());
        dados.put("noturno", curso.isNoturno());
        dados.put("presencial", curso.isPresencial());
        dados.put("ead", curso.isEad());
        dados.put("semi_presencial", curso.isSemi_presencial());
        dados.put("tel_instituicao", curso.getTel_instituicao());
        dados.put("site_instituicao", curso.getSite_instituicao());

        bd.insertOrThrow("curso", null, dados);
        bd.close();
    }

    public ArrayList<Usuario> getUsuario(String email, String password){
        SQLiteDatabase bd = this.getWritableDatabase();
        String sql = "SELECT * FROM usuario WHERE email='" + email + "' COLLATE NOCASE AND password='" + password + "';";
        Cursor result = bd.rawQuery(sql, null);
        ArrayList<Usuario> logado = new ArrayList<>();

        if(result.getCount() == 1) {
            result.moveToFirst();
            Usuario usuario = new Usuario();
            usuario.setId(result.getInt(0));
            usuario.setNome(result.getString(1));
            usuario.setData_nasc(result.getString(2));
            usuario.setEmail(result.getString(3));
            usuario.setCurso(result.getString(4));
            usuario.setEndereco(result.getString(5));

            logado.add(usuario);
        }
        else{
            logado = null;
        }

        bd.close();

        return logado;
    }

    public ArrayList<Curso> getCursos(String cursoInstituicao){

        SQLiteDatabase bd = this.getWritableDatabase();
        cursoInstituicao = "%" + cursoInstituicao + "%";
        String sql = "SELECT * FROM curso WHERE curso LIKE " + '"' + cursoInstituicao + '"' + " COLLATE NOCASE OR instituicao LIKE " + '"' + cursoInstituicao + '"' + " COLLATE NOCASE;";
        Cursor result = bd.rawQuery(sql, null);
        ArrayList<Curso> cursos = new ArrayList<>();

        if(result.getCount() > 0){

            result.moveToFirst();

            do{
                Curso curso = new Curso();
                curso.setId(result.getInt(0));
                curso.setCurso(result.getString(1));
                curso.setInstituicao(result.getString(2));
                curso.setCampus(result.getString(3));
                curso.setEndereco_campus(result.getString(4));
                curso.setMensalidade(result.getDouble(5));
                curso.setDiurno(getBoolean(result.getInt(6)));
                curso.setNoturno(getBoolean(result.getInt(7)));
                curso.setPresencial(getBoolean(result.getInt(8)));
                curso.setEad(getBoolean(result.getInt(9)));
                curso.setSemi_presencial(getBoolean(result.getInt(10)));
                curso.setTel_instituicao(result.getString(11));
                curso.setSite_instituicao(result.getString(12));

                cursos.add(curso);
            }
            while (result.moveToNext());

            bd.close();
            return cursos;
        }
        else{
            bd.close();
            cursos=null;
            return cursos;
        }

    }



    public boolean getBoolean(int bool) {
        if (bool == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
