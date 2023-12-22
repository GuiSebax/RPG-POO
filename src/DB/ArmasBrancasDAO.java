/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import Classes.ArmaBranca;
import Classes.Personagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class ArmasBrancasDAO {
    
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
    
    public ArrayList recuperarArmas() {
        ArrayList<ArmaBranca> armas = new ArrayList();
        
        String sql = "SELECT * FROM armaBranca where pesada = False;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            
            ArmaBranca arma = new ArmaBranca();
            
            while(rs.next()) {
                arma.setIdArma(rs.getInt("idArma"));
                arma.setNome(rs.getString("nome"));
                arma.setPesada(rs.getBoolean("pesada"));
                arma.setDescr(rs.getString("descr"));
                armas.add(arma);
                arma = new ArmaBranca();
            }
            
            rs.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
        }
        
        return armas;
    }
    
    public ArmaBranca getArmaBrancaPeloNome(String nome) {
        ArmaBranca arma = new ArmaBranca();
        String sql = "SELECT * FROM armaBranca where nome = ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            
            pstm.setString(1, nome);
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()) {
                arma.setIdArma(rs.getInt("idArma"));
                arma.setNome(rs.getString("nome"));
                arma.setPesada(rs.getBoolean("pesada"));
                arma.setDescr(rs.getString("descr"));
            }
            
            rs.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
            
        }
        return arma;
    }
    
    public ArmaBranca recuperarArmaBrancaPrimaria(Personagem person)
    {
        ArmaBranca arma = new ArmaBranca();
        var sql = "SELECT a.idArma, a.nome, a.pesada, a.descr "
                + "FROM armaBranca a, " + person.getClasse().toLowerCase() + " k, personagem p "
                + "WHERE a.idArma = k.idArmaPrimaria "
                + "AND p.idPersonagem = k.idPersonagem "
                + "AND p.idPersonagem = ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(String.valueOf(sql));
            
            pstm.setInt(1, person.getIdPersonagem());
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()) {
                arma.setIdArma(rs.getInt("idArma"));
                arma.setNome(rs.getString("nome"));
                arma.setPesada(rs.getBoolean("pesada"));
                arma.setDescr(rs.getString("descr"));
            }
            
            rs.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
            
        }
        return arma;
    }
        
    public ArmaBranca recuperarArmaBrancaSecundaria(Personagem person)
    {
        ArmaBranca arma = new ArmaBranca();
        String sql = "SELECT a.idArma, a.nome, a.pesada, a.descr "
                + "FROM armaBranca a, " + person.getClasse().toLowerCase() + " k, personagem p "
                + "WHERE a.idArma = k.idArmaSecundaria "
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
                arma.setIdArma(rs.getInt("idArma"));
                arma.setNome(rs.getString("nome"));
                arma.setPesada(rs.getBoolean("pesada"));
                arma.setDescr(rs.getString("descr"));
            }
            
            rs.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
            
        }
        return arma;
    }

}
