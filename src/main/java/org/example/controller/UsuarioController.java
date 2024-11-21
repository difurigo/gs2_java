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

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUsuario(Usuario usuarioInput) {
        try {
            // Valida se o email foi fornecido
            if (usuarioInput.getEmail() == null || usuarioInput.getEmail().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("O campo 'email' é obrigatório!")
                        .build();
            }

            // Busca o usuário pelo email
            Usuario usuarioEncontrado = usuarioService.buscarUsuarioPorEmail(usuarioInput.getEmail());

            // Verifica se o email existe no banco de dados
            if (usuarioEncontrado == null) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Email não encontrado!")
                        .build();
            }

            // Verifica se o nome corresponde ao encontrado no banco
            if (!usuarioInput.getNome().equals(usuarioEncontrado.getNome())) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("O nome fornecido não corresponde ao email!")
                        .build();
            }

            // Verifica se a senha corresponde à encontrada no banco
            if (!usuarioInput.getSenha().equals(usuarioEncontrado.getSenha())) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Senha incorreta!")
                        .build();
            }

            // Login bem-sucedido
            return Response.status(Response.Status.OK)
                    .entity("Login realizado com sucesso! Bem-vindo, " + usuarioEncontrado.getNome() + ".")
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao realizar o login: " + e.getMessage())
                    .build();
        }
    }



}

