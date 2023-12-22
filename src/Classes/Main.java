package Classes;
import GUI.TelaLoginCadastroUsuario;

/**
 *
 * @author marco
 * É recomendado o arquivo "TelaLoginCadastroUsuario.java" no pacote "GUI" para
 * Não haver mudanças alterações no design, porém este arquivo também
 * serve para rodar o programa.
 * Caso queira alterar a conexão com o banco de dados, verifique o arquivo
 * "ConnectionFactory.java" no pacote DB.
 */
public class Main {
    public static void main(String[] args) {
        TelaLoginCadastroUsuario tela = new TelaLoginCadastroUsuario();
        tela.setVisible(true);
    }
}
