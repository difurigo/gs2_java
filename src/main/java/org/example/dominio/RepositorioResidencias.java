package org.example.dominio;

import java.util.List;

public interface RepositorioResidencias {
    public void adicionar(Residencia residencia);
    public void fechar();
    public Residencia buscarResidencia(int idResidencia);

    List<Residencia> listarPorUsuario(int idUsuario);
}
