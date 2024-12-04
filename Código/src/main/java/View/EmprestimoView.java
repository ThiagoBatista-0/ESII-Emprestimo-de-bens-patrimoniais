/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.DevolucaoController;
import Controller.EmprestimoController;
import Controller.MultaController;
import Model.BemPatrimonial;
import Model.Emprestimo;
import Model.Usuario;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lhsan
 */
public class EmprestimoView extends JFrame {

    private JButton btnEmprestar;
    private JButton btnDevolver;
    private JButton btnCalcularMulta;
    private JTextField tfUsuario;
    private JTextField tfBem;

    private EmprestimoController emprestimoController;
    private DevolucaoController devolucaoController;
    private MultaController multaController;

    public EmprestimoView() {
        // Inicializa os objetos
        Emprestimo emprestimo = new Emprestimo();
        BemPatrimonial bem = new BemPatrimonial("Laptop", true);
        Usuario usuario = new Usuario("João", "teste", "Comum");

        // Inicializa os controladores
        //this.emprestimoController = new EmprestimoController(emprestimo, bem, usuario);
        this.devolucaoController = new DevolucaoController(emprestimo, bem, usuario);
        this.multaController = new MultaController(emprestimo);

        setTitle("Empréstimo de Bens Patrimoniais");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializando componentes
        tfUsuario = new JTextField(10);
        tfBem = new JTextField(10);
        btnEmprestar = new JButton("Emprestar");
        btnDevolver = new JButton("Devolver");
        btnCalcularMulta = new JButton("Calcular Multa");

        // Layout
        JPanel panel = new JPanel();
        panel.add(new JLabel("Usuário:"));
        panel.add(tfUsuario);
        panel.add(new JLabel("Bem:"));
        panel.add(tfBem);
        panel.add(btnEmprestar);
        panel.add(btnDevolver);
        panel.add(btnCalcularMulta);

        add(panel);

        // Ações dos botões
        //btnEmprestar.addActionListener(e -> emprestimoController.realizarEmprestimo());
        btnDevolver.addActionListener(e -> devolucaoController.realizarDevolucao());
    }

    public static void main(String[] args) {
        EmprestimoView view = new EmprestimoView();
        view.setVisible(true);
    }
}
