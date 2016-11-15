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
public class Modalidades {
    private int idModalidades;
    private String nome = "";
    private boolean ativo;

    public int getIdModalidades() {
        return idModalidades;
    }

    public void setIdModalidades(int idModalidades) {
        this.idModalidades = idModalidades;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Modalidades){
            return ((Modalidades) obj).getIdModalidades() == idModalidades;
        }
        return false;
    }
}
