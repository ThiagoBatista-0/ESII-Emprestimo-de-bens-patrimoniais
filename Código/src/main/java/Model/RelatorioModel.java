/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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
public class RelatorioModel {
    
    public List<Usuario> obterUsuarios() {
        
        List<Usuario> usList = new ArrayList<>();
        
        String sql = "SELECT nome, tipo, codigo FROM usuarios";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("nome"),
                        rs.getInt("codigo"),
                        rs.getString("tipo")
                );
                System.out.println("Usuario: "+usuario.getNome()+"\n");
                usList.add(usuario);
            }
        } catch (SQLException e) {
        }
        
        return usList;
        
    }
    
    public List<BemPatrimonial> obterBens() {
        
        List<BemPatrimonial> bemList = new ArrayList<>();
        
        String sql = "SELECT nome, disponivel, descricao, codigo, tangivel, movel, permanente, fungivel FROM bens";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                BemPatrimonial bemPatrimonial = new BemPatrimonial(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("descricao"), 
                        rs.getBoolean("disponivel"), 
                        rs.getBoolean("tangivel"), 
                        rs.getBoolean("movel"), 
                        rs.getBoolean("permanente"), 
                        rs.getBoolean("fungivel")
                );
                bemList.add(bemPatrimonial);
            }
        } catch (SQLException e) {
        }
        
        return bemList;
        
    }
    
}
