package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Limites implements Serializable{
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private CasoDeTeste casoDeTeste;
    @ManyToOne(cascade = CascadeType.ALL)
    private Categoria categoria;
    private double tempoExecucao;

    public CasoDeTeste getCasoDeTeste() {
        return casoDeTeste;
    }

    public void setCasoDeTeste(CasoDeTeste casoDeTeste) {
        this.casoDeTeste = casoDeTeste;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(double tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }
    
    
    
    
}
