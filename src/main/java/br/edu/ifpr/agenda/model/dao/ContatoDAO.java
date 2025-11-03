package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ifpr.agenda.model.Contato;
import br.edu.ifpr.agenda.model.Endereco;

public class ContatoDAO {
    //Create
    public void salvarSemEndereco(Contato contato){
        String sqlContato = "INSERT INTO contatos(nome, celular,email) VALUES(?,?,?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement psContato = con.prepareStatement(sqlContato);
            psContato.setString(1, contato.getNome());
            psContato.setString(2, contato.getCelular());
            psContato.setString(3, contato.getEmail());
            psContato.executeUpdate();
            System.out.println("Contato inserido com sucesso");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } 

    public void salvar(Contato contato){
        Connection con = ConnectionFactory.getConnection();

        //inserir o endereco primeiro
        String sqlEndereco = "INSERT INTO enderecos (rua,numero,cidade,estado) VALUES(?,?,?,?)";
        try {//posicoes atributos na tabela 0,1,2 ...
            PreparedStatement psEndereco = 
                      con.prepareStatement(sqlEndereco,Statement.RETURN_GENERATED_KEYS);
            psEndereco.setString(1, contato.getEndereco().getRua());   
            psEndereco.setString(2, contato.getEndereco().getNumero());
            psEndereco.setString(3, contato.getEndereco().getCidade());
            psEndereco.setString(4,contato.getEndereco().getEstado());
            psEndereco.executeUpdate();

            ResultSet rs = psEndereco.getGeneratedKeys();
            int idEndereco = 0;
            //para o resultSet posicoes atributos na tabela 1,2,3...
            if(rs.next()) idEndereco=rs.getInt(1);//pega o primeiro atributo da tabela

            //inserir contato
            String sqlContato = 
            "INSERT INTO contatos(nome,celular,email,id_endereco) VALUES (?,?,?,?)";
            PreparedStatement psContato = con.prepareStatement(sqlContato);
            psContato.setString(1, contato.getNome());
            psContato.setString(2, contato.getCelular());
            psContato.setString(3, contato.getEmail());
            psContato.setInt(4, idEndereco);
            psContato.executeUpdate();
            System.out.println("Contato inserido com sucesso!");


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
