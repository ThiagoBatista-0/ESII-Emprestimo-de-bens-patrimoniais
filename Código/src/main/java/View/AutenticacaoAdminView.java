/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author lhsan
 */
import Controller.AuthenticationManager;
import Controller.UsuarioController;
import javax.swing.*;

public class AutenticacaoAdminView extends JDialog {

    private final UsuarioController usuarioController;

    private final JTextField nomeField;
    private final JPasswordField senhaField;
    private final JButton autenticarButton;

    // Construtor da janela de autenticação
    public AutenticacaoAdminView(UsuarioController usuarioController) {
        super((java.awt.Frame) null, "Autenticação de Administrador", true);
        this.usuarioController = usuarioController;

        // Configurações da janela
        setTitle("Autenticação de Administrador");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Inicializa os componentes da janela
        nomeField = new JTextField(20);
        senhaField = new JPasswordField(20);
        autenticarButton = new JButton("Autenticar");

        // Define a ação do botão
        autenticarButton.addActionListener(evt -> autenticar());

        // Layout da janela
        JPanel panel = new JPanel();
        panel.add(new JLabel("Nome do Administrador:"));
        panel.add(nomeField);
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);
        panel.add(autenticarButton);

        add(panel);
    }

    // Método para autenticar o administrador
    private void autenticar() {
        String nome = nomeField.getText();
        String senha = new String(senhaField.getPassword());
        
        AuthenticationManager authManager = AuthenticationManager.getInstancia();
        if (authManager == null)
            System.out.println("auth manager null");

        // Tenta autenticar com as credenciais fornecidas
        if (authManager.autenticar(nome, senha)) {
            usuarioController.setUsuarioAtual(authManager.getUsuarioAtual());
            JOptionPane.showMessageDialog(this, "Autenticação bem-sucedida!");
            System.out.println("(authManager)nome usuarioAtual: "+authManager.getUsuarioAtual().getNome());
            dispose();  // Fecha a janela de autenticação
            // Agora, o administrador pode adicionar usuários
        } else {
            JOptionPane.showMessageDialog(this, "Falha na autenticação.");
        }
    }
}
