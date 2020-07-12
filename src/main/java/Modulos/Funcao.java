/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import Modulos.Funcionario;

/**
 *
 * @author Salomão p = f1 , c = f2
 */

@Entity
@Table(name = "funcao", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Funcao.findAll", query = "SELECT f FROM Funcao f"),
    //tabela de Funcionario = f1 , tabela de Funcao = f2
    @NamedQuery(name = "Funcao.findByFuncaoId", query = "SELECT f1 FROM Funcionario f1 JOIN Funcao f2 ON(f1.funcao = f2.id) where f1.funcao = :funcaoObj")
})
public class Funcao implements Serializable{
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false)
    private String nome;
    
     @Column(nullable = false)
    private float salario;
     
    @OneToMany(mappedBy = "funcao", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Funcionario> funcionario;

    
    public Funcao() {
        
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public List<Funcionario> getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(List<Funcionario> funcionario) {
        this.funcionario = funcionario;
    }

}
