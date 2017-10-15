package model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ProblemHasCategoria implements Serializable{
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Problema problema;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario user;

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    
    
}
