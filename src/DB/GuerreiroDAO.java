/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import Classes.ArmaBranca;
import Classes.Guerreiro;
import Classes.Personagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author marco
 */
public class GuerreiroDAO extends ArmasBrancasDAO {
    public void encerrarConexao(PreparedStatement pstm, Connection c) {
        try {
            if (pstm != null) {
                pstm.close();
            }
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void salvarGuerreiro(Guerreiro guerreiro) {
        String sql = "INSERT INTO guerreiro (idPersonagem, idArmaPrimaria, idArmaSecundaria, idAcessorio) "
                + "values (?, ?, ?, ?);";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            // Conexão com DB
            
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            // adicionando valores nas '?'
            pstm.setInt(1, guerreiro.getId());
            pstm.setInt(2, guerreiro.getIdArmaPrimaria());
            pstm.setInt(3, guerreiro.getIdArmaSecundaria());
            pstm.setInt(4, guerreiro.getIdAcessorio());
            
            pstm.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar classe!\n"+e);
        } finally {
            //fechar conexões
            encerrarConexao(pstm, c);
        }
    }
    
    @Override
    public ArrayList recuperarArmas() {
        ArrayList<ArmaBranca> armas = new ArrayList();
        
        String sql = "SELECT * FROM armaBranca;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);

            ResultSet result = pstm.executeQuery();
            
            ArmaBranca arma = new ArmaBranca();
            
            while(result.next()) {
                arma.setIdArma(result.getInt("idArma"));
                arma.setNome(result.getString("nome"));
                arma.setPesada(result.getBoolean("pesada"));
                arma.setDescr(result.getString("descr"));
                armas.add(arma);
                arma = new ArmaBranca();
            }
            
            result.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
        }
        
        return armas;
    }
    
    public void removerGuerreiro(Personagem person) {
        String sql = "DELETE FROM guerreiro WHERE idPersonagem= ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            // Conexão com DB
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            // adicionando valores nas '?'
            pstm.setInt(1, person.getIdPersonagem());
            
            pstm.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover a classe!\n"+e);
        } finally {
            //fechar conexões
            encerrarConexao(pstm, c);
        }
    }
    
}
