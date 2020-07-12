
package recursos;

import Modulos.Agendamento;
import Modulos.Pet;
import Modulos.Servico;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Stateless
@Path("Agendamentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgendamentoResource {
    
    @PersistenceContext(unitName = "PetShopPU")
    EntityManager entityManeger;
    
    @GET
    public List<Agendamento> getAgendamento() {
        return  entityManeger.createQuery("SELECT a FROM Agendamento a", Agendamento.class).getResultList();           
    }
    
    @POST
    public Response addAgendamento(Agendamento agendamento){
        entityManeger.persist(agendamento);
        return Response
                .status(Response.Status.CREATED)
                .entity(agendamento)
                .build();
    }
    
    @GET 
    @Path("{id}")
    public Agendamento getAgendamento(@PathParam("id") UUID id){
        return entityManeger.find(Agendamento.class, id);
    }
    
    @DELETE
    @Path("{id}")
    public void removeAgendamento(@PathParam("id") UUID id ){
        Agendamento agendamentoEncontrado = entityManeger.find(Agendamento.class, id);
        entityManeger.remove(agendamentoEncontrado);
    }
    
    @PUT
    @Path("{id}")
    public Agendamento updateAgendamento(@PathParam("id") UUID id, Agendamento a){
        a.setId(id);
        entityManeger.merge(a);
        return a;
    }
    
    @Path("/all/pet/{id}")
    @GET
    public List<Agendamento> getPetsByAgendamento(@PathParam("id") UUID id){
        Pet pet = entityManeger.find(Pet.class, id);
        pet.setId(id);
        return entityManeger.createNamedQuery("Agendamento.findAgendamentoByPetId", Agendamento.class).setParameter("petObj", pet).getResultList();
    }
    
    @Path("/all/servico/{id}")
    @GET
    public List<Agendamento> getServicosByAgendamento(@PathParam("id") UUID id){
        Servico servico = entityManeger.find(Servico.class, id);
        servico.setId(id);
        return entityManeger.createNamedQuery("Agendamento.findAgendamentoByServicosId", Agendamento.class).setParameter("servicoObj", servico).getResultList();
    }
}
