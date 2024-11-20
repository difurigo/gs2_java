package org.example.dominio;

public interface RepositorioResidencias {
    public void adicionar(Residencia residencia);
    public void fechar();
    public Residencia buscarResidencia(int idResidencia);
}
