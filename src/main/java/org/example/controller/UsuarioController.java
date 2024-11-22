package org.example.controller;

import org.example.dominio.Usuario;
import org.example.infra.dao.UsuarioDAO;
import org.example.service.UsuarioService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("usuario")
public class UsuarioController {


    @OPTIONS
    @Path("/login")
    public Response preflightLogin() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Methods", "POST, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .build();
    }



    private final UsuarioService usuarioService;

    public UsuarioController() {
        // Inicializa o DAO e passa para o serviço
        this.usuarioService = new UsuarioService(new UsuarioDAO());
    }

    // Endpoint para buscar usuário pelo email
    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarUsuarioPorEmail(@PathParam("email") String email) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);

            if (usuario == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Usuário não encontrado para o email: " + email)
                        .build();
            }

            return Response.status(Response.Status.OK)
                    .entity(usuario)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar o usuário: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/cadastro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(Usuario usuarioInput) {
        try {
            if (usuarioInput.getNome() == null || usuarioInput.getNome().isEmpty() ||
                    usuarioInput.getEmail() == null || usuarioInput.getEmail().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\": \"Nome e email são obrigatórios!\"}")
                        .build();
            }

            Usuario usuarioExistente = usuarioService.buscarUsuarioPorEmail(usuarioInput.getEmail());
            if (usuarioExistente != null) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("{\"message\": \"Já existe um usuário cadastrado com esse email.\"}")
                        .build();
            }

            usuarioService.cadastrarUsuario(usuarioInput);

            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\": \"Usuário cadastrado com sucesso!\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\": \"Erro ao cadastrar o usuário: " + e.getMessage() + "\"}")
                    .build();
        }
    }


    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.listarTodosUsuarios();

            if (usuarios.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhum usuário encontrado!")
                        .build();
            }

            return Response.status(Response.Status.OK)
                    .entity(usuarios)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar usuários: " + e.getMessage())
                    .build();
        }
    }


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) // Certifique-se de que a resposta seja JSON
    public Response loginUsuario(Usuario usuarioInput) {
        try {
            if (usuarioInput.getEmail() == null || usuarioInput.getEmail().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .header("Access-Control-Allow-Origin", "http://localhost:3000")
                        .entity("{\"message\": \"O campo 'email' é obrigatório!\"}")
                        .build();
            }

            Usuario usuarioEncontrado = usuarioService.buscarUsuarioPorEmail(usuarioInput.getEmail());

            if (usuarioEncontrado == null) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .header("Access-Control-Allow-Origin", "http://localhost:3000")
                        .entity("{\"message\": \"Email não encontrado!\"}")
                        .build();
            }

            if (!usuarioInput.getSenha().equals(usuarioEncontrado.getSenha())) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .header("Access-Control-Allow-Origin", "http://localhost:3000")
                        .entity("{\"message\": \"Senha incorreta!\"}")
                        .build();
            }

            // Login bem-sucedido
            return Response.status(Response.Status.OK)
                    .header("Access-Control-Allow-Origin", "http://localhost:3000")
                    .entity("{\"message\": \"Login realizado com sucesso! Bem-vindo, " + usuarioEncontrado.getNome() + ".\"}")
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "http://localhost:3000")
                    .entity("{\"message\": \"Erro ao realizar o login: " + e.getMessage() + "\"}")
                    .build();
        }
    }




}

