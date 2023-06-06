package com.example.ondeestudar;

import java.io.Serializable;

public class Curso implements Serializable {

    private int id;
    private String curso;
    private String instituicao;
    private String campus;
    private String descricao;
    private String endereco_campus;
    private Double mensalidade;
    private boolean diurno;
    private boolean noturno;
    private boolean presencial;
    private boolean ead;
    private boolean semi_presencial;
    private String tel_instituicao;
    private String site_instituicao;

    public String toString(){

        return curso.toString() + " - " + instituicao.toString();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco_campus() {
        return endereco_campus;
    }

    public void setEndereco_campus(String endereco_campus) {
        this.endereco_campus = endereco_campus;
    }

    public Double getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(Double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public boolean isDiurno() {
        return diurno;
    }

    public void setDiurno(boolean diurno) {
        this.diurno = diurno;
    }

    public boolean isNoturno() {
        return noturno;
    }

    public void setNoturno(boolean noturno) {
        this.noturno = noturno;
    }

    public boolean isPresencial() {
        return presencial;
    }

    public void setPresencial(boolean presencial) {
        this.presencial = presencial;
    }

    public boolean isEad() {
        return ead;
    }

    public void setEad(boolean ead) {
        this.ead = ead;
    }

    public boolean isSemi_presencial() {
        return semi_presencial;
    }

    public void setSemi_presencial(boolean semi_presencial) {
        this.semi_presencial = semi_presencial;
    }

    public String getTel_instituicao() {
        return tel_instituicao;
    }

    public void setTel_instituicao(String tel_instituicao) {
        this.tel_instituicao = tel_instituicao;
    }

    public String getSite_instituicao() {
        return site_instituicao;
    }

    public void setSite_instituicao(String site_instituicao) {
        this.site_instituicao = site_instituicao;
    }
}
