/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.DBConnection;

public class EmprestimoController {

    private Connection conn;

    public EmprestimoController() {
        // Inicializa a conexão com o banco de dados
        try {
            //conn = DriverManager.getConnection("jdbc:sqlite:C:/SQLite/bancos/emprestimospatrimoniais/emprestimospatrimoniais.db");
            conn = DBConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados");
        }
    }

    /**
     * Recupera a lista de bens disponíveis para empréstimo.
     * 
     * @return Lista de nomes de bens disponíveis.
     */
    public List<String> carregarBensDisponiveis() {
        List<String> bens = new ArrayList<>();
        String query = "SELECT nome FROM bens WHERE disponivel = 1"; // Query para obter bens disponíveis
        
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                bens.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados");
        }
        return bens;
    }

    /**
     * Recupera a lista de usuários cadastrados.
     * 
     * @return Lista de nomes de usuários cadastrados.
     */
    public List<String> carregarUsuarios() {
        List<String> usuarios = new ArrayList<>();
        String query = "SELECT nome FROM usuarios"; // Query para obter usuários cadastrados
        
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                usuarios.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Registra um novo empréstimo no banco de dados.
     * 
     * @param codigo Código do empréstimo
     * @param usuarioNome Nome do usuário que realizará o empréstimo
     * @param bemNome Nome do bem que será emprestado
     * @param dataEmprestimo Data de empréstimo
     * @param devolvido
     * @param prazoEntrega Prazo de entrega do bem
     * @param autorizado Se o empréstimo foi autorizado (boolean)
     */
    public void realizarEmprestimo(String usuarioNome, String bemNome, Date dataEmprestimo, Date prazoEntrega, boolean devolvido, boolean autorizado, int codigo) {
        try {
            // Recupera o ID do usuário e do bem
            int usuarioId = getUsuarioId(usuarioNome);
            int bemId = getBemId(bemNome);

            if (usuarioId == -1 || bemId == -1) {
                System.out.println("Erro: Usuário ou bem não encontrado.");
                return;
            }

            // Insere o empréstimo na tabela 'emprestimos'
            String insertQuery = "INSERT INTO emprestimos (codigo, usuario_id, bem_id, data_emprestimo, "
                    + "data_devolucao, devolvido, autorizado) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setInt(1, codigo);
                pstmt.setInt(2, usuarioId);
                pstmt.setInt(3, bemId);
                pstmt.setDate(4, new java.sql.Date(dataEmprestimo.getTime()));
                pstmt.setDate(5, new java.sql.Date(prazoEntrega.getTime()));
                pstmt.setBoolean(6, devolvido);
                pstmt.setBoolean(7, autorizado);
                pstmt.executeUpdate();
                System.out.println("Empréstimo registrado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recupera o ID de um usuário baseado no nome.
     * 
     * @param nome Nome do usuário
     * @return ID do usuário ou -1 se não encontrado
     */
    private int getUsuarioId(String nome) {
        String query = "SELECT id FROM usuarios WHERE nome = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nome);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Recupera o ID de um bem baseado no nome.
     * 
     * @param nome Nome do bem
     * @return ID do bem ou -1 se não encontrado
     */
    private int getBemId(String nome) {
        String query = "SELECT id FROM bens WHERE nome = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nome);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Fecha a conexão com o banco de dados.
     */
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


