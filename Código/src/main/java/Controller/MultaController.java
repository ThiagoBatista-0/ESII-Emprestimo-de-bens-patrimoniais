/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Emprestimo;

/**
 *
 * @author lhsan
 */
public class MultaController {

    private Emprestimo emprestimo;

    public MultaController() {
    }
    
   
    public MultaController(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

   // Método para registrar multa por atraso
    public void registrarMultaPorAtraso(int usuarioId, int emprestimoId, double valor) {
        // Código para registrar a multa no banco de dados
        System.out.println("Multa por atraso registrada.");
    }

    // Método para registrar multa por dano
    public void registrarMultaPorDano(int usuarioId, int bemId, double valor) {
        // Código para registrar a multa por dano
        System.out.println("Multa por dano registrada.");
    }

    // Método para registrar multa por perda
    public void registrarMultaPorPerda(int usuarioId, int bemId, double valor) {
        // Código para registrar a multa por perda
        System.out.println("Multa por perda registrada.");
    }
}
