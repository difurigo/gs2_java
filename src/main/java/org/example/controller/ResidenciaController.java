package org.example.controller;

import org.example.dominio.Residencia;
import org.example.infra.dao.ResidenciaDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("residencias")
public class ResidenciaController {

    private final ResidenciaDAO residenciaDAO;

    public ResidenciaController() {
        this.residenciaDAO = new ResidenciaDAO();
    }

    // Endpoint para adicionar uma nova residência
    @POST
    @Path("/adicionar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarResidencia(Residencia residencia) {
        try {
            residenciaDAO.adicionar(residencia);
            return Response.status(Response.Status.CREATED)
                    .entity("Residência adicionada com sucesso!")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao adicionar residência: " + e.getMessage())
                    .build();
        }
    }

    // Endpoint para buscar residência por ID
    @GET
    @Path("/{residenciaId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarResidenciaPorId(@PathParam("residenciaId") int residenciaId) {
        try {
            Residencia residencia = residenciaDAO.buscarResidencia(residenciaId);
            if (residencia == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Residência não encontrada!")
                        .build();
            }
            return Response.status(Response.Status.OK).entity(residencia).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar residência: " + e.getMessage())
                    .build();
        }
    }

    // Endpoint para listar todas as residências de um usuário
    @GET
    @Path("/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarResidenciasPorUsuario(@PathParam("idUsuario") int idUsuario) {
        try {
            List<Residencia> residencias = residenciaDAO.listarPorUsuario(idUsuario);
            if (residencias.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhuma residência encontrada para o usuário!")
                        .build();
            }
            return Response.status(Response.Status.OK).entity(residencias).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar residências: " + e.getMessage())
                    .build();
        }
    }
}
