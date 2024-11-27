package View;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UICadastroEmprestimo extends JDialog {

    private JTextField tfCodigoEmprestimo;
    private JDateChooser tfDataEmprestimo;
    private JDateChooser tfPrazoEntrega;
    private JCheckBox cbAutorizado;
    private JButton btnCriarEmprestimo;
    private JButton btnCancelar;

    public UICadastroEmprestimo(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastrar Novo Empréstimo");
        setLayout(new GridLayout(6, 2));

        // Labels e campos de entrada
        JLabel lblCodigo = new JLabel("Código do Empréstimo:");
        tfCodigoEmprestimo = new JTextField();

        JLabel lblDataEmprestimo = new JLabel("Data do Empréstimo:");
        tfDataEmprestimo = new JDateChooser(); // Aqui usamos o JDateChooser

        JLabel lblPrazoEntrega = new JLabel("Prazo de Entrega:");
        tfPrazoEntrega = new JDateChooser(); // Outro campo JDateChooser

        JLabel lblAutorizado = new JLabel("Autorizado:");
        cbAutorizado = new JCheckBox("Sim");

        // Botões de ação
        btnCriarEmprestimo = new JButton("Criar Empréstimo");
        btnCancelar = new JButton("Cancelar");

        // Adiciona os componentes ao layout
        add(lblCodigo);
        add(tfCodigoEmprestimo);

        add(lblDataEmprestimo);
        add(tfDataEmprestimo);

        add(lblPrazoEntrega);
        add(tfPrazoEntrega);

        add(lblAutorizado);
        add(cbAutorizado);

        add(btnCriarEmprestimo);
        add(btnCancelar);

        // Ajusta o tamanho do JDialog
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Ações dos botões
        btnCriarEmprestimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para criar o empréstimo
                criarEmprestimo();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Fecha a janela de cadastro
            }
        });
    }

    private void criarEmprestimo() {
        try {
            // Obtendo os dados inseridos pelo usuário
            int codigo = Integer.parseInt(tfCodigoEmprestimo.getText());
            java.util.Date dataEmprestimo = tfDataEmprestimo.getDate();
            java.util.Date prazoEntrega = tfPrazoEntrega.getDate();
            boolean autorizado = cbAutorizado.isSelected();

            // Lógica para criar o empréstimo
            System.out.println("Empréstimo criado: " + codigo + ", Data: " + dataEmprestimo + ", Prazo: " + prazoEntrega + ", Autorizado: " + autorizado);

            // Mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Empréstimo criado com sucesso!");

            // Fecha a janela de cadastro
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao criar o empréstimo: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            UICadastroEmprestimo dialog = new UICadastroEmprestimo(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setLocationRelativeTo(null); // Centraliza na tela
            dialog.setVisible(true);
        });
    }
}
