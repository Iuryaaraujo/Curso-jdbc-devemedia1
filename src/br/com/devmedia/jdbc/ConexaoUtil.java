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

    }
    public void inserir(PessoaDTO pessoaDTO) throws SQLException {

    }

    public void atualizar(PessoaDTO pessoaDTO){

    }

    public void remover(Integer idPessoa) throws SQLException {

    }


    public static void main(String[] args) {
        try {
            PessoaDTO dto = new PessoaDTO();
            dto.setIdPessoa(6);
            dto.setNome("Gildo Gomes");
            dto.setEndereco("R. Jonas alcaratara");
            dto.setCpf(123467564l);
            dto.setSexo('M');
            dto.setDtNascimento(new java.util.Date());

            getInstance().atualizar(dto);

            getInstance().listar();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}

