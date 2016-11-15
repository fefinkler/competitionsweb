/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Fernanda Finkler
 */
public class Cidade {
  
    private int idcidade;
    private String nome = "";
    private int estado;
    private boolean ativo;

    public int getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(int idcidade) {
        this.idcidade = idcidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString () {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj instanceof Cidade) {
            return ((Cidade)obj).getIdcidade()== idcidade;
        } else {
            return false;
        }
        
    } 

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
