package br.edu.devmedia.jdbc.dao;

import br.com.devmedia.jdbc.exception.PersistenciaExcpetion;
import br.edu.devmedia.jdbc.dto.PessoaDTO;

import java.util.List;

public interface GenericoDAO<T> {

    void inserir(T obj) throws PersistenciaExcpetion;

    void atualizar(T obj) throws PersistenciaExcpetion;

    void deletar(Integer id) throws PersistenciaExcpetion;

    List<T> listarTodos() throws PersistenciaExcpetion;

    T buscarPorId(Integer id) throws PersistenciaExcpetion;

}
