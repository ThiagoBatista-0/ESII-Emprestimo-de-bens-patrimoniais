/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        try {
            // Carrega o driver JDBC do SQLite
            Class.forName("org.sqlite.JDBC");

            // Caminho para o arquivo de banco de dados na pasta específica do sistema de arquivos
            //File dbFile = new File("C:/SQLite/bancos/emprestimospatrimoniais/emprestimos.db");
            File dbFile = new File("src/main/resources/db/emprestimospatrimoniais.db"); 
            // Verifica se o banco de dados existe no diretório especificado
            if (!dbFile.exists()) {
                throw new SQLException("O banco de dados não foi encontrado no caminho especificado: " 
                                        + dbFile.getAbsolutePath());
            }

            // Conecta ao banco de dados localizado no diretório especificado
            String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();
            System.out.println("caminho do bd: "+url);
            return DriverManager.getConnection(url);

        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados SQLite: " + e.getMessage(), e);
        }
    }
}

