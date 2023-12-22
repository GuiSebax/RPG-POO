/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import Classes.Talisman;
import Classes.Clerigo;
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
public class ClerigoDAO extends ArmasBrancasDAO {
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
    
    public void salvarClerigo(Clerigo clerigo) {
        String sql = "INSERT INTO clerigo (idPersonagem, idArmaPrimaria, idTalisman, idAcessorio) "
                + "values (?, ?, ?, ?);";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            // Conexão com DB
            
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            // adicionando valores nas '?'
            pstm.setInt(1, clerigo.getId());
            pstm.setInt(2, clerigo.getIdArmaPrimaria());
            pstm.setInt(3, clerigo.getIdTalisman());
            pstm.setInt(4, clerigo.getIdAcessorio());
            
            pstm.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar classe!\n"+e);
            e.printStackTrace();
        } finally {
            //fechar conexões
            encerrarConexao(pstm, c);
        }
    }
    
    public ArrayList recuperarTalismans() {
        ArrayList<Talisman> talismans = new ArrayList();
        
        String sql = "SELECT * FROM talisman;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            
            Talisman talisman = new Talisman();
            
            while(rs.next()) {
                talisman.setIdTalisman(rs.getInt("idTalisman"));
                talisman.setNome(rs.getString("nome"));
                talisman.setProeficiencia(rs.getString("proeficiencia"));
                talisman.setDescr(rs.getString("descr"));
                talismans.add(talisman);
                talisman = new Talisman();
            }
            
            rs.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
        }
        
        return talismans;
    }
        
    
    public Talisman getTalismanPeloNome(String nome) {
        Talisman talisman = new Talisman();
        String sql = "SELECT * FROM talisman where nome = ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            
            pstm.setString(1, nome);
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()) {
                talisman.setIdTalisman(rs.getInt("idTalisman"));
                talisman.setNome(rs.getString("nome"));
                talisman.setProeficiencia(rs.getString("proeficiencia"));
                talisman.setDescr(rs.getString("descr"));
            }
            
            rs.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
            
        }
        return talisman;
    }
    
    public Talisman recuperarTalismanPersonagem(Personagem person)
    {
        Talisman talisman = new Talisman();
        String sql = "SELECT t.idTalisman, t.nome, t.proeficiencia, t.descr "
                + "FROM talisman t, Clerigo k, personagem p "
                + "WHERE t.idTalisman = k.idTalisman "
                + "AND p.idPersonagem = k.idPersonagem "
                + "AND p.idPersonagem = ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            
            pstm.setInt(1, person.getIdPersonagem());
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()) {
                talisman.setIdTalisman(rs.getInt("idTalisman"));
                talisman.setNome(rs.getString("nome"));
                talisman.setProeficiencia(rs.getString("proeficiencia"));
                talisman.setDescr(rs.getString("descr"));
            }
            
            rs.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
            
        }
        return talisman;
    }
    
    public void removerClerigo(Personagem person) {
        String sql = "DELETE FROM clerigo WHERE idPersonagem= ?;";
        
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
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao remover a classe!\n"+e);
        } finally {
            //fechar conexões
            encerrarConexao(pstm, c);
        }
    }
}
