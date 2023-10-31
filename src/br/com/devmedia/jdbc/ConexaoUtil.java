package br.com.devmedia.jdbc;

import br.edu.devmedia.jdbc.dto.PessoaDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class ConexaoUtil {
private static ConexaoUtil conexaoUtil;
    public static ConexaoUtil getInstance(){
       if (conexaoUtil == null){
           conexaoUtil = new ConexaoUtil();
       }
       return conexaoUtil;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3307/teste_devmedia",
                "root","root");
    }

    public void listar() throws  SQLException{
        Connection connection = null;
        try{
            connection = getConnection();

            String sql = "SELECT * FROM TB_PESSOA";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resulSet = statement.executeQuery();

            while (resulSet.next()){
                System.out.println( resulSet.getInt("id_pessoa"));
                System.out.println( resulSet.getString("nome"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //fecha a conexao
            connection.close();
        }
    }
    public void inserir(PessoaDTO pessoaDTO) throws SQLException {
        Connection connection = null;
      try {
          connection = getConnection();

          String sql = "INSERT INTO TB_PESSOA(NOME,CPF,ENDERECO,SEXO, DT_NASC)" +
                  "VALUES(?,?,?,?,?)";

          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setString(1, pessoaDTO.getNome());
          statement.setLong(2, pessoaDTO.getCpf());
          statement.setString(3, pessoaDTO.getEndereco());
          statement.setString(4,String.valueOf(pessoaDTO.getSexo('M')));
          statement.setDate(5, new Date(pessoaDTO.getDtNascimento().getTime()));

          statement.execute();
      }catch (Exception e){
           e.printStackTrace();
      }finally {
          connection.close();
      }
    }

    public void atualizar(PessoaDTO pessoaDTO){
        Connection connection = null;
         try {
              connection = getConnection();

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
         }catch (Exception e){
             e.printStackTrace();
         }
    }

    public void remover(Integer idPessoa) throws SQLException {
        Connection connection = null;
          try {
               connection = getConnection();

               String sql = "DELETE FROM TB_PESSOA WHERE ID_PESSOA = ?";
               PreparedStatement statement = connection.prepareStatement(sql);
              statement.setInt(1, idPessoa);

              statement.execute();

          }catch (Exception e){
              e.printStackTrace();
          }finally {
               connection.close();
          }
    }


    public static void main(String[] args) {
        try {
            PessoaDTO dto = new PessoaDTO();
            dto.setIdPessoa(13);
            dto.setNome("carlos rocha 2");
            dto.setEndereco("R. Jonas Alcantara");
            dto.setCpf(123467895l);
            dto.setSexo('M');
            dto.setDtNascimento(new java.util.Date());

            getInstance().remover(dto.getIdPessoa());

            getInstance().listar();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}

