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
public class Estado {
    private int idestado;
    private String sigla;
    private String nome;
    private int pais;
    private boolean ativo;

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

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

    public int getPais() {
        return pais;
    }

    public void setPais(int pais) {
        this.pais = pais;
    }
    
    @Override
    public String toString () {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj instanceof Estado) {
            return ((Estado)obj).getIdestado() == idestado;
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
