package org.example.controller;

import org.example.dominio.Usuario;
import org.example.infra.dao.UsuarioDAO;
import org.example.service.UsuarioService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("usuario")
public class UsuarioController {

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

    // Endpoint para cadastrar um usuário
    @POST
    @Path("/cadastro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(Usuario usuarioInput) {
        try {
            // Validação básica: nome e email não podem estar vazios
            if (usuarioInput.getNome() == null || usuarioInput.getNome().isEmpty() ||
                    usuarioInput.getEmail() == null || usuarioInput.getEmail().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Nome e email são obrigatórios!")
                        .build();
            }

            // Verifica se já existe um usuário com o mesmo email
            Usuario usuarioExistente = usuarioService.buscarUsuarioPorEmail(usuarioInput.getEmail());
            if (usuarioExistente != null) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("Já existe um usuário cadastrado com esse email.")
                        .build();
            }

            // Adiciona o usuário ao banco de dados
            usuarioService.cadastrarUsuario(usuarioInput);

            // Retorna sucesso no cadastro
            return Response.status(Response.Status.CREATED)
                    .entity("Usuário cadastrado com sucesso!")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar o usuário: " + e.getMessage())
                    .build();
        }
    }

    // Endpoint para login do usuário
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUsuario(Usuario usuarioInput) {
        try {
            // Busca o usuário pelo email
            Usuario usuarioPorEmail = usuarioService.buscarUsuarioPorEmail(usuarioInput.getEmail());

            // Busca o usuário pelo nome
            Usuario usuarioPorNome = usuarioService.buscarUsuarioPorNome(usuarioInput.getNome());

            // Valida se o usuário existe com o nome e email fornecidos
            if (usuarioPorEmail == null || usuarioPorNome == null ||
                    !usuarioPorEmail.getEmail().equals(usuarioInput.getEmail()) ||
                    !usuarioPorNome.getNome().equals(usuarioInput.getNome())) {

                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Credenciais inválidas! Nome ou email não correspondem.")
                        .build();
            }

            // Retorna sucesso no login
            return Response.status(Response.Status.OK)
                    .entity("Login realizado com sucesso! Bem-vindo, " + usuarioPorNome.getNome() + ".")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao realizar o login: " + e.getMessage())
                    .build();
        }
    }
}
