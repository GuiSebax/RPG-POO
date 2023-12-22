package DB;

import Classes.Usuario;
import Classes.Personagem;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    
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
    
    public void salvarUsuario(Usuario user) {
        String sql = "INSERT INTO usuario (nome, email, senha) values (?, ?, ?);";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            // Conexão com DB
            
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // adicionando valores nas '?'
            pstm.setString(1, user.getNome());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getSenha());
            
            pstm.execute();
            ResultSet rs = pstm.getGeneratedKeys();
            if(rs.next())
            {
                int id = rs.getInt(1);
                user.setIdUsuario(id);
            }
            
            rs.close();
            JOptionPane.showMessageDialog(null, "Cadastro do usuário efetuado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o usuário!\n"+e);
        } finally {
            //fechar conexões
            encerrarConexao(pstm, c);
        }
        
    }
    
    public void removerUsuario(Usuario user) {
        String sql = "DELETE FROM usuario WHERE idUsuario = ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            // Conexão com DB
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            // adicionando valores nas '?'
            pstm.setInt(1, user.getIdUsuario());
            
            pstm.execute();

            JOptionPane.showMessageDialog(null, "Remoção do usuário feita com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover o usuário!\n"+e);
        } finally {
            //fechar conexões
            encerrarConexao(pstm, c);
        }
    }
    
    public boolean validarLogin(Usuario user) {
        boolean autenticado = false;
        String sql = "select email, senha from usuario where email = ? and senha = ?";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try{
            //criar conexao com BD
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            pstm.setString(1, user.getEmail());
            pstm.setString(2, user.getSenha());
            
            ResultSet rs;
            rs = pstm.executeQuery();
            
            while(rs.next()){
                //String loginBanco = rs.getString("login");
                //String senhaBanco = rs.getString("senha");
                autenticado = true;
            }
        
        } catch (Exception e){
           JOptionPane.showMessageDialog(null, "Erro no login do usuário!\n"+e);
        } finally{
            //fechar conexões
            encerrarConexao(pstm, c);
        }    
        
        return autenticado;
    }
    
    public Usuario encontrarUsuarioPorEmail(String email) {
        Usuario user = new Usuario();
        String sql = "select * from usuario where email = ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try{
            //criar conexao com BD
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            pstm.setString(1, email);
            
            ResultSet rs;
            rs = pstm.executeQuery();
            
            while(rs.next()){
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
            }
        
        } catch (Exception e){
           JOptionPane.showMessageDialog(null, "Erro para encontrar usuário!\n"+e);
        } finally{
            //fechar conexões
            encerrarConexao(pstm, c);
        }
        
        return user;
    }
    
    public ArrayList pesquisarPersonagens(Usuario user) {
        ArrayList<Personagem> personagens = new ArrayList();
        
        String sql = "SELECT * FROM personagem WHERE idUsuario = ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            
            pstm.setInt(1, user.getIdUsuario());
            
            ResultSet result = pstm.executeQuery();
            
            Personagem p = new Personagem();
            
            while(result.next()) {
                p.setIdUsuario(result.getInt("idUsuario"));
                p.setNome(result.getString("nome"));
                p.setIdRaca(result.getInt("idRaca"));
                p.setIdPersonagem(result.getInt("idPersonagem"));
                p.setGenero(result.getString("genero"));
                p.setVigor(result.getInt("vigor"));
                p.setMana(result.getInt("mana"));
                p.setEstamina(result.getInt("estamina"));
                p.setForca(result.getInt("forca"));
                p.setDestreza(result.getInt("destreza")); 
                p.setInteligencia(result.getInt("inteligencia"));
                p.setFe(result.getInt("fe"));
                p.setSorte(result.getInt("sorte"));;
                p.setClasse(result.getString("classe"));
                personagens.add(p);
                p = new Personagem();
            }
            
            result.close();
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            encerrarConexao(pstm, c);
        }
        
        return personagens;
    }
    
    public void atualizarCadastro(Usuario user) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE idUsuario = ?;";
        
        Connection c = null;
        PreparedStatement pstm = null;
        
        try {
            // Conexão com DB
            
            c = ConnectionFactory.createConnectionToMySQL();
            pstm = c.prepareStatement(sql);
            // adicionando valores nas '?'
            pstm.setString(1, user.getNome());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getSenha());
            pstm.setInt(4, user.getIdUsuario());
            
            pstm.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário!\n"+e);
        } finally {
            //fechar conexões
            encerrarConexao(pstm, c);
        }
    }
}
