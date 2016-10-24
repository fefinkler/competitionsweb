/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.sql.Date;

/**
 *
 * @author User
 */
public class Competicao {
    private int id;
    private String nome;
    private Date dia;
    private char status;
    private int cidade;
    private String localidade;
    private String colocacao;
    private String premiacao;
    private String relato;
    private Atleta[] equipe;
    private Modalidades[] modalidades;
    private TiposDespesas[] despesas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getCidade() {
        return cidade;
    }

    public void setCidade(int cidade) {
        this.cidade = cidade;
    }

    public String getColocacao() {
        return colocacao;
    }

    public void setColocacao(String colocacao) {
        this.colocacao = colocacao;
    }

    public String getPremiacao() {
        return premiacao;
    }

    public void setPremiacao(String premiacao) {
        this.premiacao = premiacao;
    }

    public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
    }

    public Atleta[] getEquipe() {
        return equipe;
    }

    public void setEquipe(Atleta[] equipe) {
        this.equipe = equipe;
    }

    public Modalidades[] getModalidades() {
        return modalidades;
    }

    public void setModalidades(Modalidades[] modalidades) {
        this.modalidades = modalidades;
    }

    public TiposDespesas[] getDespesas() {
        return despesas;
    }

    public void setDespesas(TiposDespesas[] despesas) {
        this.despesas = despesas;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
    
}
