/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author snow
 */
public class Monitor extends Usuario {

    //private String disciplina;
    private Disciplina disp;
    private Date dtInicio;
    private Date dtFinal;

    public Monitor() {
        this.disp = new Disciplina();
    }

    public Monitor(Usuario u) {
        super(u.getMatricula(), u.getNome(), u.getSenha(), u.getTipo());
        this.disp = new Disciplina();
    }

    /*
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }
     */
    public Disciplina getDisp() {
        return disp;
    }

    public void setDisp(Disciplina disp) {
        this.disp = disp;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFinal() {
        return dtFinal;
    }

    public void setDtFinal(Date dtFinal) {
        this.dtFinal = dtFinal;
    }

}
