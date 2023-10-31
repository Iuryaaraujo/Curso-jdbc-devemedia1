package br.edu.devmedia.jdbc.dao;

import br.com.devmedia.jdbc.exception.PersistenciaExcpetion;
import br.edu.devmedia.jdbc.dto.PessoaDTO;

import java.util.List;

public class PessoaDAO implements GenericoDAO <PessoaDTO> {


    @Override
    public void inserir(PessoaDTO obj) throws PersistenciaExcpetion {
        
    }

    @Override
    public void atualizar(PessoaDTO obj) throws PersistenciaExcpetion {

    }

    @Override
    public void deletar(Integer id) throws PersistenciaExcpetion {

    }

    @Override
    public List<PessoaDTO> listarTodos() throws PersistenciaExcpetion {
        return null;
    }

    @Override
    public PessoaDTO buscarPorId(Integer id) throws PersistenciaExcpetion {
        return null;
    }
}
