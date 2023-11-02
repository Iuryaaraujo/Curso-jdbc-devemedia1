package br.edu.devmedia.jdbc.dao;

import br.com.devmedia.jdbc.ConexaoUtil;
import br.com.devmedia.jdbc.exception.PersistenciaExcpetion;
import br.edu.devmedia.jdbc.dto.PessoaDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO implements GenericoDAO <PessoaDTO> {


    @Override
    public void inserir(PessoaDTO pessoaDTO) throws PersistenciaExcpetion {
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "INSERT INTO TB_PESSOA(NOME,CPF,ENDERECO,SEXO, DT_NASC)" +
                    "VALUES(?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pessoaDTO.getNome());
            statement.setLong(2, pessoaDTO.getCpf());
            statement.setString(3, pessoaDTO.getEndereco());
            statement.setString(4,String.valueOf(pessoaDTO.getSexo('M')));
            statement.setDate(5, new Date(pessoaDTO.getDtNascimento().getTime()));

            statement.execute();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaExcpetion(e.getMessage(), e);
        }
    }

    @Override
    public void atualizar(PessoaDTO pessoaDTO) throws PersistenciaExcpetion {
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "UPDATE TB_PESSOA " +
                    "SET NOME = ?," +
                    " CPF = ?," +
                    " ENDERECO = ?," +
                    " SEXO = ?," +
                    " DT_NASC = ? " +
                    " WHERE ID_PESSOA = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pessoaDTO.getNome());
            statement.setLong(2, pessoaDTO.getCpf());
            statement.setString(3, pessoaDTO.getEndereco());
            statement.setString(4, String.valueOf(pessoaDTO.getSexo('M')));
            statement.setDate(5, new Date(pessoaDTO.getDtNascimento().getTime()));
            statement.setInt(6, pessoaDTO.getIdPessoa());

            statement.execute();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaExcpetion(e.getMessage(), e);
        }
    }

    @Override
    public void deletar(Integer idPessoa) throws PersistenciaExcpetion {
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "DELETE FROM TB_PESSOA WHERE ID_PESSOA = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idPessoa);

            statement.execute();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaExcpetion(e.getMessage(), e);
        }
    }

    @Override
    public List<PessoaDTO> listarTodos() throws PersistenciaExcpetion {
        List<PessoaDTO> listaPessoas = new ArrayList<PessoaDTO>()
        try{
            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "SELECT * FROM TB_PESSOA";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();

            while (resulSet.next()){
                PessoaDTO pessoaDTO = new PessoaDTO();
               pessoaDTO.setIdPessoa(resulSet.getInt("id_pessoa"));
               pessoaDTO.setNome(resulSet.getString("nome"));
               pessoaDTO.setCpf(resulSet.getLong("cpf"));
               pessoaDTO.setDtNascimento(resulSet.getDate("dt_nasc"));
               pessoaDTO.setEndereco(resulSet.getString("endereco"));
               pessoaDTO.setSexo(resulSet.getString("sexo").charAt(0));

               listaPessoas.add(pessoaDTO);
            }
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaExcpetion(e.getMessage(), e);
        }
        return listaPessoas;
    }

    @Override
    public PessoaDTO buscarPorId(Integer id) throws PersistenciaExcpetion {
        return null;
    }
}
