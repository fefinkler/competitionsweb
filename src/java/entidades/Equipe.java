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
public class Equipe {
    private int competicao;
    private int atleta;
    
    public Equipe (int competicao, int atleta){
        this.competicao = competicao;
        this.atleta = atleta;
    }

    public int getCompeticao() {
        return competicao;
    }

    public void setCompeticao(int competicao) {
        this.competicao = competicao;
    }

    public int getAtleta() {
        return atleta;
    }

    public void setAtleta(int atleta) {
        this.atleta = atleta;
    }
}
