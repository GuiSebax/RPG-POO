/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import Classes.Bandido;
import Classes.Personagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author marco
 */
public class BandidoDAO extends ArmasBrancasDAO {
    
    public void salvarBandido(Bandido bandido) {
        String sql = "INSERT INTO bandido (idPersonagem, idArmaPrimaria, idArmaSecundaria, idAcessorio) "
                + "values (?, ?, ?, ?);";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            // Conexão com DB
            
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            // adicionando valores nas '?'
            pstm.setInt(1, bandido.getId());
            pstm.setInt(2, bandido.getIdArmaPrimaria());
            pstm.setInt(3, bandido.getIdArmaSecundaria());
            pstm.setInt(4, bandido.getIdAcessorio());
            
            pstm.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar classe!\n"+e);
        } finally {
            //fechar conexões
            encerrarConexao(pstm, c);
        }
    }
    
    public void removerBandido(Personagem person) {
        String sql = "DELETE FROM bandido WHERE idPersonagem= ?;";
        
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
