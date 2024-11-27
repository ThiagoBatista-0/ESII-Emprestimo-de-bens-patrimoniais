/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DBConnection;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author lhsan
 */
public class BemPatrimonialController  extends BaseController {
    
    private final AuthenticationManager authManager = AuthenticationManager.getInstancia();

    public BemPatrimonialController() {
    }

    // Método para adicionar um novo bem (somente administradores)
    public void adicionarBem(int codigo, String nome, String descricao, Boolean disponivel, 
            boolean tangivel, boolean movel, boolean permanente, boolean fungivel) {
        
        // Obtém o usuário atual diretamente do AuthenticationManager
        Usuario usuarioAtual = authManager.getUsuarioAtual();
        
        
        // Verifica se o usuário é administrador
        if (usuarioAtual == null || !usuarioAtual.getTipo().equals("Administrador")) {
            System.out.println("Apenas administradores podem adicionar usuarios.");
            // Se o usuario não for administrador, abre a tela de autenticação
            abrirTelaAutenticacao();
            usuarioAtual = authManager.getUsuarioAtual();
            System.out.println("(bemPatrimonialController) nome usuarioAtual  "+ authManager.getUsuarioAtual().getNome()+"\n");
            
            // Código para adicionar o bem ao banco de dados
            // nome, disponível, descrição, código, tangível, móvel, permanente, fungivel
            String sql = "INSERT INTO bens (nome, disponivel, descricao, codigo, tangivel, movel, permanente, fungivel) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = DBConnection.getConnection(); 
                    PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, nome);
                stmt.setBoolean(2, disponivel);
                stmt.setString(3, descricao);
                stmt.setInt(4, codigo);
                stmt.setBoolean(5, tangivel);
                stmt.setBoolean(6, movel);
                stmt.setBoolean(7, permanente);
                stmt.setBoolean(8, fungivel);
                stmt.executeUpdate();
                System.out.println("Bem patrimonial adicionado com sucesso.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            
            return;
        }
        
    }

//    // Método para alterar um bem
//    public void alterarBem(int id, String nome, String descricao) {
//        if (!isAdmin()) {
//            System.out.println("Apenas administradores podem alterar bens.");
//            return;
//        }
//        // Código para atualizar o bem no banco de dados
//    }
//
//    // Método para remover um bem
//    public void removerBem(int id) {
//        if (!isAdmin()) {
//            System.out.println("Apenas administradores podem remover bens.");
//            return;
//        }
//        // Código para remover o bem do banco de dados
//    }
//
//    private boolean isAdmin() {
//        return usuarioAtual != null && usuarioAtual.getTipo().equals("Administrador");
//    }
}
