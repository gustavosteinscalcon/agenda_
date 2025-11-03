package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection conexao;

    private ConnectionFactory(){}

    public static Connection getConnection(){
        try {
            if(conexao==null){
                //jdbc:gdbd://ip do servidor do BD:porta/database
                String url = "jdbc:mysql://localhost:3306/agenda";
                String user= "root";
                String password="root";
                conexao = DriverManager.getConnection(url, user, password);
                System.out.println("conectado ao banco com sucesso!");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conexao;

    }

}
