
package recursos;

import Modulos.Funcao;
import Modulos.Funcionario;
import java.io.Serializable;
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
@Path("funcoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncaoResource {
    
    @PersistenceContext(unitName = "PetShopPU")
    EntityManager entityManeger;
    
    @GET
    public List<Funcao> getFuncao() {
        return  entityManeger.createQuery("SELECT f FROM Funcao f", Funcao.class).getResultList();           
    }
    
    @POST
    public Response addFuncao(Funcao funcao){
        entityManeger.persist(funcao);
        return Response
                .status(Response.Status.CREATED)
                .entity(funcao)
                .build();
    }
    
    @GET 
    @Path("{id}")
    public Funcao getFuncao(@PathParam("id") UUID id){
        return entityManeger.find(Funcao.class, id);
    }
    
    @DELETE
    @Path("{id}")
    public void removeFuncao(@PathParam("id") UUID id ){
        Funcao funcaoEncontrado = entityManeger.find(Funcao.class, id);
        entityManeger.remove(funcaoEncontrado);
    }
    
    @PUT
    @Path("{id}")
    public Funcao updateFuncao(@PathParam("id") UUID id, Funcao f){
        f.setId(id);
        entityManeger.merge(f);
        return f;
    }
    
    @Path("{id}/funcionarios")
    @GET
    public List<Funcionario> getFuncionariosByFuncao(@PathParam("id") UUID id){
        Funcao funcao = entityManeger.find(Funcao.class, id);
        funcao.setId(id);
        return entityManeger.createNamedQuery("Funcao.findByFuncaoId", Funcionario.class).setParameter("funcaoObj", funcao).getResultList();
    }
    
}
