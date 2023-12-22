/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import Classes.Cajado;
import Classes.Mago;
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
public class MagoDAO extends ArmasBrancasDAO{
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
    
    public void salvarMago(Mago mago) {
        String sql = "INSERT INTO mago (idPersonagem, idArmaPrimaria, idCajado, idAcessorio) "
                + "values (?, ?, ?, ?);";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            // Conexão com DB
            
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            // adicionando valores nas '?'
            pstm.setInt(1, mago.getId());
            pstm.setInt(2, mago.getIdArmaPrimaria());
            pstm.setInt(3, mago.getIdCajado());
            pstm.setInt(4, mago.getIdAcessorio());
            
            pstm.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar classe!\n"+e);
        } finally {
            //fechar conexões
            encerrarConexao(pstm, c);
        }
    }
    
    public ArrayList recuperarCajados() {
        ArrayList<Cajado> cajados = new ArrayList();
        
        String sql = "SELECT * FROM cajado;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            
            Cajado cajado = new Cajado();
            
            while(rs.next()) {
                cajado.setIdCajado(rs.getInt("idCajado"));
                cajado.setNome(rs.getString("nome"));
                cajado.setProeficiencia(rs.getString("proeficiencia"));
                cajado.setDescr(rs.getString("descr"));
                cajados.add(cajado);
                cajado = new Cajado();
            }
            
            rs.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
        }
        
        return cajados;
    }
    
    public Cajado getCajadoPeloNome(String nome) {
        Cajado cajado = new Cajado();
        String sql = "SELECT * FROM cajado where nome = ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            
            pstm.setString(1, nome);
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()) {
                cajado.setIdCajado(rs.getInt("idCajado"));
                cajado.setNome(rs.getString("nome"));
                cajado.setProeficiencia(rs.getString("proeficiencia"));
                cajado.setDescr(rs.getString("descr"));
            }
            
            rs.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
            
        }
        return cajado;
    }
    
    public Cajado recuperarCajadoPersonagem(Personagem person)
    {
        Cajado cajado = new Cajado();
        String sql = "SELECT c.idCajado, c.nome, c.proeficiencia, c.descr "
                + "FROM cajado c, mago m, personagem p "
                + "WHERE c.idCajado = m.idCajado "
                + "AND p.idPersonagem = m.idPersonagem "
                + "AND p.idPersonagem = ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            
            pstm.setInt(1, person.getIdPersonagem());
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()) {
                cajado.setIdCajado(rs.getInt("idCajado"));
                cajado.setNome(rs.getString("nome"));
                cajado.setProeficiencia(rs.getString("proeficiencia"));
                cajado.setDescr(rs.getString("descr"));
            }
            
            rs.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
            
        }
        return cajado;
    }
    
    public void removerMago(Personagem person) {
        String sql = "DELETE FROM mago WHERE idPersonagem= ?;";
        
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
