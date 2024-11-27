/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DBConnection;
import View.UIRelatorio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lhsan
 */
public class RelatorioController {

    private final UIRelatorio relatorioView;

    public RelatorioController(UIRelatorio view) {
        this.relatorioView = view;
    }

    public void carregarRelatorio(String tipo) {
        String sql = "";
        String[] colunas = {};

        if (null != tipo) // Dependendo do tipo do relatório, ajusta a consulta SQL e as colunas
        {
            // Cria a query especifica para a tabela especificada por 'tipo' 
            switch (tipo) {
                case "usuarios" -> {
                    sql = "SELECT nome, tipo, codigo FROM usuarios";
                    colunas = new String[]{"Nome", "Tipo", "Código"};
                }
                case "bens" -> {
                    sql = "SELECT nome, disponivel, descricao, codigo, tangivel, movel, permanente, fungivel FROM bens";
                    //nome, disponivel, descricao, codigo, tangivel, movel, permanente, fungivel
                    colunas = new String[]{"Nome", "Disponível", "Descrição",
                        "Código", "Tangível", "Móvel", "Permanente", "Fungível"};
                }
                case "emprestimos" -> {
                    // Query com JOIN para pegar os nomes dos bens e usuários
                    sql = """
                          SELECT 
                              e.codigo, u.nome AS usuario_nome, 
                              b.nome AS bem_nome, datetime(e.data_emprestimo / 1000, 'unixepoch') AS data_emprestimo,
                              datetime(e.data_Devolucao / 1000, 'unixepoch') AS data_devolucao, e.devolvido, e.autorizado 
                          FROM emprestimos e
                          JOIN usuarios u ON e.usuario_id = u.id
                          JOIN bens b ON e.bem_id = b.id""";
                    colunas = new String[]{"Código", "Usuário", "Bem", "Data de Empréstimo",
                        "Data de Devolução", "Devolvido", "Autorizado"};
                }
                default -> {
                }
            }
        }
        // Chama o método para carregar os dados da tabela
        List<Object[]> dados = obterDadosDoBanco(sql);
        // Passa os dados para a view
        relatorioView.preencherTabela(dados, colunas);
    }

    private List<Object[]> obterDadosDoBanco1(String sql) {
        List<Object[]> dados = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            // Depuração - imprime o conteúdo das colunas
            int columnCount = rs.getMetaData().getColumnCount();
            //System.out.println("Número de colunas: " + columnCount);

            // Verifica se a consulta retorna resultados
            if (!rs.next()) {
                System.out.println("Nenhum dado encontrado.");
                return dados; // Retorna uma lista vazia caso não haja dados
            }

            // Caso a consulta tenha retornado dados, preenche a lista com os dados
            do {
                Object[] linha = new Object[columnCount];

                // Imprimir os dados de cada linha para depuração
                for (int i = 0; i < columnCount; i++) {
                    linha[i] = rs.getObject(i + 1);
                    //System.out.print("Coluna " + (i + 1) + ": " + linha[i] + " | ");
                }
                System.out.println(); // Nova linha após cada linha de dados
                dados.add(linha);
            } while (rs.next());
        } catch (SQLException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }

        return dados;
    }

    private List<Object[]> obterDadosDoBanco(String sql) {
        List<Object[]> dados = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(sql)) {
            

            while (rs.next()) {
                Object[] linha = new Object[rs.getMetaData().getColumnCount()];

                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // Obtém o valor da coluna
                    Object valor = rs.getObject(i + 1);

                    // Se for um timestamp Unix (INTEGER), converta para um tipo Date ou Timestamp
                    if (valor instanceof Long aLong) {
                        // Converte para java.sql.Date (se for Unix Timestamp em milissegundos)
                        linha[i] = new java.sql.Date(aLong);  // Se for timestamp Unix, converte para Date
                    } else if (valor instanceof java.sql.Timestamp) {
                        // Já é Timestamp, nada precisa ser feito
                        linha[i] = valor;
                    } else {
                        linha[i] = valor;
                    }
                }

                dados.add(linha);
//                stmt.close();
//                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        } finally {
            
        }

        return dados;
    }

}
