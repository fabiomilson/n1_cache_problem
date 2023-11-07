package com.cache.n1.problem.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "FATHER")
public class Father {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FATHER")
    @SequenceGenerator(name = "SEQ_FATHER", sequenceName = "SEQ_FATHER", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESC_FATHER")
    private String desc;

    @JsonManagedReference
    //@BatchSize(size = 2500) // exemplo de uso do BatchSize
    @OneToMany(mappedBy = "father", fetch = FetchType.LAZY)
    // deixei explicito o LAZY. porém não é necessário, pois é o default para OneToMany
    private List<Children> childrens;

    public boolean existsChildrens() {
        return getChildrens() != null && !getChildrens().isEmpty();
    }
}
