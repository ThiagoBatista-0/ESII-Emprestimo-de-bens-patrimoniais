/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.AutenticacaoAdminView;

/**
 *
 * @author lhsan
 */
public class BaseController {
    
     // Método comum para todos os controladores
    public void abrirTelaAutenticacao() {
        
        // Obtém a instância única do UsuarioController
        UsuarioController usuarioController = UsuarioController.getInstancia();
        
        // Cria e exibe a janela de autenticação para o administrador
        AutenticacaoAdminView autenticarView = new AutenticacaoAdminView(usuarioController);
        autenticarView.setVisible(true);
        autenticarView.toFront(); // coloca a janela de autenticação  à frente de todas as outras
        autenticarView.requestFocusInWindow(); 
    }
    
}
