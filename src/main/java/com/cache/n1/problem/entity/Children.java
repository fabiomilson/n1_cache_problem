package com.cache.n1.problem.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CHILDREN")
@Getter
@Setter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Children {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CHILDREN")
    @SequenceGenerator(name = "SEQ_CHILDREN", sequenceName = "SEQ_CHILDREN", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESC_CHILDREN")
    private String desc;

    // forcei lazy por questões de boas práticas, salvo exceção (avaliar casos em que o EAGER seria mais performatico),
    // default nesse caso é EAGER
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "FK_FATHER")
    private Father father;

}
