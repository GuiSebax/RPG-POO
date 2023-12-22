/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import Classes.Acessorio;
import Classes.Personagem;
import Classes.Raca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author marco
 */
public class PersonagemDAO {
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
    
    public void salvarPersonagem(Personagem personagem) {
        String sql = "insert into personagem (idUsuario, nome, genero, classe, idRaca, vigor, "
                + "mana, estamina, forca, destreza, inteligencia, fe, sorte) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            // Conexão com DB
            
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // adicionando valores nas '?'
            pstm.setInt(1, personagem.getIdUsuario());
            pstm.setString(2, personagem.getNome());
            pstm.setString(3, personagem.getGenero());
            pstm.setString(4, personagem.getClasse());
            pstm.setInt(5, personagem.getIdRaca());
            pstm.setInt(6, personagem.getVigor());
            pstm.setInt(7, personagem.getMana());
            pstm.setInt(8, personagem.getEstamina());
            pstm.setInt(9, personagem.getForca());
            pstm.setInt(10, personagem.getDestreza());
            pstm.setInt(11, personagem.getInteligencia());
            pstm.setInt(12, personagem.getFe());
            pstm.setInt(13, personagem.getSorte());
            
            pstm.execute();
            
            ResultSet rs = pstm.getGeneratedKeys();
            // Retornando o ID gerado automaticamente pelo SGBD
            if(rs.next())
            {
                int id = rs.getInt(1);
                personagem.setIdPersonagem(id);
            }
            
            rs.close();
            
            JOptionPane.showMessageDialog(null, "Personagem criado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar Personagem!\n"+e);
        } finally {
            //fechar conexões
            encerrarConexao(pstm, c);
        }
    }
    
    public void removerPersonagem(Personagem person) {
        String sql = "DELETE FROM personagem WHERE idPersonagem= ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            // Conexão com DB
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            // adicionando valores nas '?'
            pstm.setInt(1, person.getIdPersonagem());
            
            pstm.execute();

            JOptionPane.showMessageDialog(null, "Remoção de personagem feita com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover o personagem!\n"+e);
        } finally {
            //fechar conexões
            encerrarConexao(pstm, c);
        }
    }
    
    public void editarPersonagem(Personagem personagem) {
        String sql = "UPDATE personagem SET nome = ?, idRaca = ?, vigor = ?,"
        + "mana = ?, estamina = ?, forca = ?, destreza = ?, inteligencia = ?,"
        + "fe = ?, sorte = ? WHERE idPersonagem = ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            // Conexão com DB
            
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            // adicionando valores nas '?'
            pstm.setString(1, personagem.getNome());
            pstm.setInt(2, personagem.getIdRaca());
            pstm.setInt(3, personagem.getVigor());
            pstm.setInt(4, personagem.getMana());
            pstm.setInt(5, personagem.getEstamina());
            pstm.setInt(6, personagem.getForca());
            pstm.setInt(7, personagem.getDestreza());
            pstm.setInt(8, personagem.getInteligencia());
            pstm.setInt(9, personagem.getFe());
            pstm.setInt(10, personagem.getSorte());
            pstm.setInt(11, personagem.getIdPersonagem());
            
            pstm.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Personagem atualizado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Personagem!\n"+e);
        } finally {
            //fechar conexões
            encerrarConexao(pstm, c);
        }
    }
    
    public ArrayList recuperarRacas() {
        ArrayList<Raca> racas = new ArrayList();
        
        String sql = "SELECT * FROM raca;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);

            ResultSet result = pstm.executeQuery();
            
            Raca raca = new Raca();
            
            while(result.next()) {
                raca.setIdRaca(result.getInt("idRaca"));
                raca.setNome(result.getString("nome"));
                racas.add(raca);
                raca = new Raca();
            }
            
            result.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
        }
        
        return racas;
    }
    
    public ArrayList recuperarAcessorios() {
        ArrayList<Acessorio> acessorios = new ArrayList();
        
        String sql = "SELECT * FROM acessorio;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);

            ResultSet result = pstm.executeQuery();
            
            Acessorio acessorio = new Acessorio();
            
            while(result.next()) {
                acessorio.setIdAcessorio(result.getInt("idAcessorio"));
                acessorio.setNome(result.getString("nome"));
                acessorio.setAtributoAlterado(result.getString("atributoAlterado"));
                acessorio.setValorAlterado(result.getInt("valorAlterado"));
                acessorio.setDescr(result.getString("descr"));
                acessorios.add(acessorio);
                acessorio = new Acessorio();
            }
            
            result.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
        }
        
        return acessorios;
    }
    
    public Raca recuperarRacaPersonagem(Personagem person)
    {
        Raca raca = new Raca();
        String sql = "SELECT r.idRaca, r.nome "
                + "FROM raca r, personagem p "
                + "WHERE r.idRaca = p.idRaca "
                + "AND p.idPersonagem = ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            
            pstm.setInt(1, person.getIdPersonagem());
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()) {
                raca.setIdRaca(rs.getInt("idRaca"));
                raca.setNome(rs.getString("nome"));
            }
            
            rs.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
            
        }
        return raca;
    }
    
    public Acessorio recuperarAcessorioPersonagem(Personagem person)
    {   
        Acessorio acessorio = new Acessorio();
        var sql = "SELECT a.idAcessorio, a.nome, a.atributoAlterado, a.valorAlterado, a.descr "
                + "FROM acessorio a, personagem p, " + person.getClasse().toLowerCase() +" c "
                + "WHERE a.idAcessorio = c.idAcessorio "
                + "AND p.idPersonagem = ? "
                + "AND p.idPersonagem = c.idPersonagem;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(String.valueOf(sql));
            
            pstm.setInt(1, person.getIdPersonagem());
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()) {
                acessorio.setIdAcessorio(rs.getInt("idAcessorio"));
                acessorio.setNome(rs.getString("nome"));
                acessorio.setAtributoAlterado(rs.getString("atributoAlterado"));
                acessorio.setValorAlterado(rs.getInt("valorAlterado"));
                acessorio.setDescr(rs.getString("descr"));
            }
            
            rs.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
            
        }
        return acessorio;
    }

}

