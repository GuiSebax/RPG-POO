/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author marco
 */
public class ArmaBranca {
    private String nome ,descr;
    private int idArma;
    private boolean pesada;

    public String getNome() {
        return nome;
    }

    public String getDescr() {
        return descr;
    }

    public int getIdArma() {
        return idArma;
    }

    public boolean isPesada() {
        return pesada;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setIdArma(int idArma) {
        this.idArma = idArma;
    }

    public void setPesada(boolean pesada) {
        this.pesada = pesada;
    }

}
