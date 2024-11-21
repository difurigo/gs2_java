package org.example.controller;

import org.example.dominio.Bem;
import org.example.infra.dao.BemDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("bens")
public class BemController {

    private final BemDAO bemDAO;

    public BemController() {
        this.bemDAO = new BemDAO();
    }

    // Adicionar eletrodoméstico
    @POST
    @Path("/eletrodomestico")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarEletrodomestico(Bem bem) {
        try {
            bemDAO.adcionarEletrodomestico(bem);
            return Response.status(Response.Status.CREATED)
                    .entity("Eletrodoméstico adicionado com sucesso!")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar eletrodoméstico: " + e.getMessage())
                    .build();
        }
    }

    // Adicionar carro
    @POST
    @Path("/carro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarCarro(Bem bem) {
        try {
            bemDAO.adcionarCarro(bem);
            return Response.status(Response.Status.CREATED)
                    .entity("Carro adicionado com sucesso!")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar carro: " + e.getMessage())
                    .build();
        }
    }

    // Adicionar gás
    @POST
    @Path("/gas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarGas(Bem bem) {
        try {
            bemDAO.adcionarGas(bem);
            return Response.status(Response.Status.CREATED)
                    .entity("Gás adicionado com sucesso!")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar gás: " + e.getMessage())
                    .build();
        }
    }

    // Buscar bens por residência
    @GET
    @Path("/{residenciaId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarBens(@PathParam("residenciaId") int residenciaId) {
        try {
            ArrayList<Bem> bens = bemDAO.buscarBens(residenciaId);
            if (bens.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhum bem encontrado para a residência ID: " + residenciaId)
                        .build();
            }
            return Response.status(Response.Status.OK).entity(bens).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar bens: " + e.getMessage())
                    .build();
        }
    }
}
