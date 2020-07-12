
package Modulos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "funcionario", schema = "public")
public class Funcionario implements Serializable{
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String endereco;
    
    @Column(nullable = false)
    private String cpf;
    
    @Column(nullable = false)
    private String telefone;            

    @Temporal(TemporalType.DATE)    
    @Column(nullable = false) 
    private Date dataContratacao;
    
    @ManyToOne
    private Funcao funcao;
    
    @OneToMany(mappedBy = "funcionario", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Agendamento> agendamento;


    
    public Funcionario() {
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
   
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getCpf() {
        return cpf;
    }
   
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
   
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public Date getDataContratacao() {
        return dataContratacao;
    }
    
    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }
    
    public Funcao getFuncao() {
        return funcao;
    }
    
    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public List<Agendamento> getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(List<Agendamento> agendamento) {
        this.agendamento = agendamento;
    }
    
    
    
}
