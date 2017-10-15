package model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Problema implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Usuario owner;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Problema_has_Taxonomia")
    private Set<Taxonomia> taxonomia;
    @Embedded
    private ProblemHasCategoria problemHasCategoria;

    public ProblemHasCategoria getProblemHasCategoria() {
        return problemHasCategoria;
    }

    public void setProblemHasCategoria(ProblemHasCategoria problemHasCategoria) {
        this.problemHasCategoria = problemHasCategoria;
    }
    
    public Set<Taxonomia> getTaxonomia() {
        return taxonomia;
    }

    public void setTaxonomia(Set<Taxonomia> taxonomia) {
        this.taxonomia = taxonomia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getOwner() {
        return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }
    
    
    
}
