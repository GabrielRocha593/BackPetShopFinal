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
import Modulos.Pet;
import javax.ws.rs.core.Response;


/**
 *
 * @author Salomão
 */
@Stateless
@Path("pets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PetResource {
    
    @PersistenceContext(unitName = "PetShopPU")
    EntityManager entityManeger;
    
    @GET
    public List<Pet> getPet() {
        return  entityManeger.createQuery("SELECT a FROM Pet a", Pet.class).getResultList();           
    }
    
    @POST
    public Response addPet(Pet pet){
        entityManeger.persist(pet);
        return Response
                .status(Response.Status.CREATED)
                .entity(pet)
                .build();
    }
    
    @GET 
    @Path("{id}")
    public Pet getPet(@PathParam("id") UUID id){
        return entityManeger.find(Pet.class, id);
    }
    
    @DELETE
    @Path("{id}")
    public void removePet(@PathParam("id") UUID id ){
        Pet petEncontrado = entityManeger.find(Pet.class, id);
        entityManeger.remove(petEncontrado);
    }
    
    @PUT
    @Path("{id}")
    public Pet updatePet(@PathParam("id") UUID id, Pet p){
        p.setId(id);
        entityManeger.merge(p);
        return p;
    }
    
}
