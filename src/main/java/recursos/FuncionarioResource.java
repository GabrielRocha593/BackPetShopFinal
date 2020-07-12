
package recursos;

import Modulos.Funcionario;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
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
@Path("funcionarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncionarioResource {
    
    @PersistenceContext(unitName = "PetShopPU")
    EntityManager entityManeger;
    
    @GET
    public List<Funcionario> getFuncionario() {
        return  entityManeger.createQuery("SELECT f FROM Funcionario f", Funcionario.class).getResultList();           
    }
    
    @POST
    public Response addFuncionario(Funcionario funcionario){
        entityManeger.persist(funcionario);
        return Response
                .status(Response.Status.CREATED)
                .entity(funcionario)
                .build();
    }
    
    @GET 
    @Path("{id}")
    public Funcionario getFuncionario(@PathParam("id") UUID id){
        return entityManeger.find(Funcionario.class, id);
    }
    
    @DELETE
    @Path("{id}")
    public void removeFuncionario(@PathParam("id") UUID id ){
        Funcionario funcionarioEncontrado = entityManeger.find(Funcionario.class, id);
        entityManeger.remove(funcionarioEncontrado);
    }
    
    @PUT
    @Path("{id}")
    public Funcionario updateFuncionario(@PathParam("id") UUID id, Funcionario funcionario){
        funcionario.setId(id);
        entityManeger.merge(funcionario);
        return funcionario;
    }
    
}
