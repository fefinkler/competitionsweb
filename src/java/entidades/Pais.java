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
public class Pais {
    
    private int idpais;
    private String sigla = "";
    private String nome = "";
    private boolean ativo;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdpais() {
        return idpais;
    }

    public void setIdpais(int idpais) {
        this.idpais = idpais;
    }
    
    @Override
    public String toString () {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj instanceof Pais) {
            return ((Pais)obj).getIdpais() == idpais;
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
