package org.example.controller;

import org.example.dominio.RepositorioUsuarios;
import org.example.dominio.Usuario;
import org.example.infra.dao.UsuarioDAO;
import org.example.service.UsuarioService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("usuario")
public class UsuarioController {

    private RepositorioUsuarios usuarioDAO;
    private UsuarioService usuarioService;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
        usuarioService = new UsuarioService(usuarioDAO);
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response buscarUsuarioPorEmail(@PathParam("email") String email) {
        Response.Status status = null;
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);

        if(usuario == null) status = Response.Status.NOT_FOUND;
        else status = Response.Status.OK;
        return Response.status(status).entity(usuario).build();
    }

    @POST
    public Response adicionar(Usuario usuario) {
        usuarioService.adicionar(usuario);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }
}
