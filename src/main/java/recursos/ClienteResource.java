/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

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
import Modulos.Cliente;
import Modulos.Pet;


@Stateless
@Path("clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {
    
    @PersistenceContext(unitName = "PetShopPU")
    EntityManager entityManeger;
    
    @GET
    public List<Cliente> getCliente() {
        return  entityManeger.createQuery("SELECT a FROM Cliente a", Cliente.class).getResultList();           
    }
    
    @POST
    public Response addCliente(Cliente cliente){
        entityManeger.persist(cliente);
        return Response
                .status(Response.Status.CREATED)
                .entity(cliente)
                .build();
    }
    
    @GET 
    @Path("{id}")
    public Cliente getCliente(@PathParam("id") UUID id){
        return entityManeger.find(Cliente.class, id);
    }
    
    @DELETE
    @Path("{id}")
    public void removeCliente(@PathParam("id") UUID id ){
        Cliente clienteEncontrado = entityManeger.find(Cliente.class, id);
        entityManeger.remove(clienteEncontrado);
    }
    
    @PUT
    @Path("{id}")
    public Cliente updateCliente(@PathParam("id") UUID id, Cliente c){
        c.setId(id);
        entityManeger.merge(c);
        return c;
    }
    
    @Path("{id}/pets")
    @GET
    public List<Pet> getPetsByCLiente(@PathParam("id") UUID id){
        Cliente cliente = entityManeger.find(Cliente.class, id);
        cliente.setId(id);
        return entityManeger.createNamedQuery("Cliente.findByClienteId", Pet.class).setParameter("clienteObj", cliente).getResultList();
    }
    
}
