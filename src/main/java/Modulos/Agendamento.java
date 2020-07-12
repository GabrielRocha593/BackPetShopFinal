
package Modulos;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "agendamento", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Agendamento.findAgendamentoByPetId", query = "SELECT a FROM Agendamento a JOIN Pet p ON(p.id = a.pet) where a.pet = :petObj"),
    @NamedQuery(name = "Agendamento.findAgendamentoByServicosId", query = "SELECT a FROM Agendamento a JOIN Servico s ON(s.id = a.servico) where a.servico = :servicoObj")
})
public class Agendamento implements Serializable{
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @Temporal(TemporalType.DATE) // yyyy-MM-dd
    @Column (nullable = false)
    private Date data;
    
    @Column(nullable = false)
    private String status;        
    
    @ManyToOne
    private Servico servico;
    
    @ManyToOne
    private Pet pet;
    
    @ManyToOne
    private Funcionario funcionario;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    
    
    
    
    
}
