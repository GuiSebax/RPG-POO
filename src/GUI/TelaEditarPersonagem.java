/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Acessorio;
import Classes.ArmaBranca;
import Classes.Bandido;
import Classes.Cajado;
import Classes.Guerreiro;
import Classes.Mago;
import Classes.Usuario;
import Classes.Personagem;
import Classes.Raca;
import Classes.Talisman;
import Classes.Clerigo;
import DB.BandidoDAO;
import DB.ClerigoDAO;
import DB.GuerreiroDAO;
import DB.MagoDAO;
import DB.PersonagemDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author guest-b4eywu
 */
public class TelaEditarPersonagem extends javax.swing.JFrame {

    /**
     * Creates new form TelaCriacaoPersonagem
     */
    Usuario user;
    Personagem person;
    String classe = "";
    ArrayList<ArmaBranca> armas;
    ArrayList<Cajado> cajados;
    ArrayList<Talisman> talismans;
    ArrayList<Acessorio> acessorios;
    ArrayList<Raca> racas;
    
    int vigorAnterior;
    int manaAnterior;
    int estaminaAnterior;
    int forcaAnterior;
    int destrezaAnterior;
    int inteligenciaAnterior;
    int feAnterior;
    int sorteAnterior;
    
    
    public TelaEditarPersonagem(Usuario us, Personagem ps) {
        user = us;
        person = ps;
        classe = person.getClasse();
        initComponents();
        PersonagemDAO personagemdao = new PersonagemDAO();
        
        vigorAnterior = person.getVigor();
        manaAnterior = person.getMana();
        estaminaAnterior = person.getEstamina();
        forcaAnterior = person.getForca();
        destrezaAnterior = person.getDestreza();
        inteligenciaAnterior = person.getInteligencia();
        feAnterior = person.getFe();
        sorteAnterior = person.getSorte();
        
        TFNome.setText(person.getNome());
        
        int pontosUtilizados = (vigorAnterior + manaAnterior + estaminaAnterior + forcaAnterior + 
                destrezaAnterior + inteligenciaAnterior + feAnterior + sorteAnterior);
        
        LBPontosRestantes.setText(String.valueOf(63 - pontosUtilizados));
            
        SpinnerVigor.setValue(person.getVigor());
        SpinnerMana.setValue(person.getMana());
        SpinnerEstamina.setValue(person.getEstamina());
        SpinnerForca.setValue(person.getForca());
        SpinnerDestreza.setValue(person.getDestreza());
        SpinnerInteligencia.setValue(person.getInteligencia());
        SpinnerFe.setValue(person.getFe());
        SpinnerSorte.setValue(person.getSorte());
        
        racas = personagemdao.recuperarRacas();
        acessorios = personagemdao.recuperarAcessorios();
        
        for(int i = 0; i < racas.size(); i++)
        {
            CBRaca.addItem(racas.get(i).getNome());
        }
        
        for(int i = 0; i < acessorios.size(); i++)
        {
            CBacessorio.addItem(acessorios.get(i).getNome());
        }
        
        CBacessorio.setSelectedItem(personagemdao.recuperarAcessorioPersonagem(person).getNome());
        CBRaca.setSelectedItem(personagemdao.recuperarRacaPersonagem(person).getNome());
        
        switch (classe) {
            case "Guerreiro" ->                 {
                    GuerreiroDAO classedao = new GuerreiroDAO();
                    armas = classedao.recuperarArmas();
                    jLatributoExtraForca.setText(String.valueOf(Integer.parseInt(jLatributoExtraForca.getText()) + 5));
                    for(int i = 0; i < armas.size(); i++)
                    {
                        CBequipamento1.addItem(armas.get(i).getNome());
                        CBequipamento2.addItem(armas.get(i).getNome());
                    }
                    
                    CBequipamento1.setSelectedItem(classedao.recuperarArmaBrancaPrimaria(person).getNome());
                    CBequipamento2.setSelectedItem(classedao.recuperarArmaBrancaSecundaria(person).getNome());
                    
                }
            case "Bandido" ->                 {
                    BandidoDAO classedao = new BandidoDAO();
                    armas = classedao.recuperarArmas();
                    jLatributoExtraDestreza.setText(String.valueOf(Integer.parseInt(jLatributoExtraDestreza.getText()) + 5));
                    for(int i = 0; i < armas.size(); i++)
                    {
                        CBequipamento1.addItem(armas.get(i).getNome());
                        CBequipamento2.addItem(armas.get(i).getNome());
                    }
                    
                    CBequipamento1.setSelectedItem(classedao.recuperarArmaBrancaPrimaria(person).getNome());
                    CBequipamento2.setSelectedItem(classedao.recuperarArmaBrancaSecundaria(person).getNome());
                    
            }
            case "Clerigo" ->                 {
                    ClerigoDAO classedao = new ClerigoDAO();
                    armas = classedao.recuperarArmas();
                    talismans = classedao.recuperarTalismans();
                    jLatributoExtraFe.setText(String.valueOf(Integer.parseInt(jLatributoExtraFe.getText()) + 5));
                    for(int i = 0; i < armas.size(); i++)
                    {
                        CBequipamento1.addItem(armas.get(i).getNome());
                    }       for(int i = 0; i < talismans.size(); i++)
                    {
                        CBequipamento2.addItem(talismans.get(i).getNome());
                    }
                    
                    CBequipamento1.setSelectedItem(classedao.recuperarArmaBrancaPrimaria(person).getNome());
                    CBequipamento2.setSelectedItem(classedao.recuperarTalismanPersonagem(person).getNome());
            }
            case "Mago" ->                 {
                    MagoDAO classedao = new MagoDAO();
                    armas = classedao.recuperarArmas();
                    cajados = classedao.recuperarCajados();
                    jLatributoExtraInteligencia.setText(String.valueOf(Integer.parseInt(jLatributoExtraInteligencia.getText()) + 5));
                    for(int i = 0; i < armas.size(); i++)
                    {
                        CBequipamento1.addItem(armas.get(i).getNome());
                    }       for(int i = 0; i < cajados.size(); i++)
                    {
                        CBequipamento2.addItem(cajados.get(i).getNome());
                    }
                    
                    CBequipamento1.setSelectedItem(classedao.recuperarArmaBrancaPrimaria(person).getNome());
                    CBequipamento2.setSelectedItem(classedao.recuperarCajadoPersonagem(person).getNome());
            }
            default -> {
            }
        }
    }
    
    public void editarPersonagem()
    {
        PersonagemDAO persondao = new PersonagemDAO();
        int idAcessorioF = 0, idRacaF = 0;
        
        for(int i = 0; i < racas.size(); i++)
        {
            if(racas.get(i).getNome().equals(CBRaca.getSelectedItem())) {
                idRacaF = racas.get(i).getIdRaca();
            }
        }
        
        for(int i = 0; i < acessorios.size(); i++)
        {
            if(acessorios.get(i).getNome().equals(CBacessorio.getSelectedItem())) {
                idAcessorioF = acessorios.get(i).getIdAcessorio();
            }
        }
        
        String nomeF = TFNome.getText();
        int attrVigor = Integer.parseInt(String.valueOf(SpinnerVigor.getValue()));
        int attrMana = Integer.parseInt(String.valueOf(SpinnerMana.getValue()));
        int attrForca = Integer.parseInt(String.valueOf(SpinnerForca.getValue()));
        int attrDestreza = Integer.parseInt(String.valueOf(SpinnerDestreza.getValue()));
        int attrInteligencia = Integer.parseInt(String.valueOf(SpinnerInteligencia.getValue()));
        int attrFe = Integer.parseInt(String.valueOf(SpinnerFe.getValue()));
        int attrSorte = Integer.parseInt(String.valueOf(SpinnerSorte.getValue()));
        int attrEstamina = Integer.parseInt(String.valueOf(SpinnerEstamina.getValue()));
        
        person.setNome(nomeF);
        person.setIdUsuario(user.getIdUsuario());
        person.setIdRaca(idRacaF);
        person.setClasse(classe);
        person.setForca(attrForca);
        person.setDestreza(attrDestreza);
        person.setInteligencia(attrInteligencia);
        person.setFe(attrFe);
        person.setVigor(attrVigor);
        person.setMana(attrMana);
        person.setSorte(attrSorte);
        person.setEstamina(attrEstamina);
        
        persondao.editarPersonagem(person);
        
        switch (classe) {
            case "Guerreiro":
                {
                    GuerreiroDAO guerreirodao = new GuerreiroDAO();
                    Guerreiro guerreiro = new Guerreiro();
                    ArmaBranca primaria = guerreirodao.getArmaBrancaPeloNome(String.valueOf(CBequipamento1.getSelectedItem()));
                    ArmaBranca secundaria = guerreirodao.getArmaBrancaPeloNome(String.valueOf(CBequipamento2.getSelectedItem()));
                    
                    guerreiro.setId(person.getIdPersonagem());
                    guerreiro.setIdArmaPrimaria(primaria.getIdArma());
                    guerreiro.setIdArmaSecundaria(secundaria.getIdArma());
                    guerreiro.setIdAcessorio(idAcessorioF);
                    guerreirodao.removerGuerreiro(person);
                    guerreirodao.salvarGuerreiro(guerreiro);
                    break;
                }
            case "Bandido":
                {
                    BandidoDAO bandidodao = new BandidoDAO();
                    Bandido bandido = new Bandido();
                    ArmaBranca primaria = bandidodao.getArmaBrancaPeloNome(String.valueOf(CBequipamento1.getSelectedItem()));
                    ArmaBranca secundaria = bandidodao.getArmaBrancaPeloNome(String.valueOf(CBequipamento2.getSelectedItem()));
                    
                    bandido.setId(person.getIdPersonagem());
                    bandido.setIdArmaPrimaria(primaria.getIdArma());
                    bandido.setIdArmaSecundaria(secundaria.getIdArma());
                    bandido.setIdAcessorio(idAcessorioF);
                    bandidodao.removerBandido(person);
                    bandidodao.salvarBandido(bandido);
                    break;
                }
            case "Clerigo":
                {
                    ClerigoDAO clerigodao = new ClerigoDAO();
                    Clerigo clerigo = new Clerigo();
                    ArmaBranca primaria = clerigodao.getArmaBrancaPeloNome(String.valueOf(CBequipamento1.getSelectedItem()));
                    Talisman secundaria = clerigodao.getTalismanPeloNome(String.valueOf(CBequipamento2.getSelectedItem()));
                    
                    clerigo.setId(person.getIdPersonagem());
                    clerigo.setIdArmaPrimaria(primaria.getIdArma());
                    clerigo.setIdTalisman(secundaria.getIdTalisman());
                    clerigo.setIdAcessorio(idAcessorioF);
                    clerigodao.removerClerigo(person);
                    clerigodao.salvarClerigo(clerigo);
                    break;
                }
            case "Mago":
                {
                    MagoDAO magodao = new MagoDAO();
                    Mago mago = new Mago();
                    ArmaBranca primaria = magodao.getArmaBrancaPeloNome(String.valueOf(CBequipamento1.getSelectedItem()));
                    Cajado secundaria = magodao.getCajadoPeloNome(String.valueOf(CBequipamento2.getSelectedItem()));
                    
                    mago.setId(person.getIdPersonagem());
                    mago.setIdArmaPrimaria(primaria.getIdArma());
                    mago.setIdCajado(secundaria.getIdCajado());
                    mago.setIdAcessorio(idAcessorioF);
                    magodao.removerMago(person);
                    magodao.salvarMago(mago);
                    break;
                }
            default:
                break;
        }
        
        TelaInicialPersonagem telaInicial = new TelaInicialPersonagem(user);
        telaInicial.setVisible(true);
        dispose();
    }
    
    public TelaEditarPersonagem() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        TFNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TAdescricaoEquip1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        TAdescricaoEquip2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        TAdescricaoAcessorio = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        SpinnerFe = new javax.swing.JSpinner();
        SpinnerInteligencia = new javax.swing.JSpinner();
        SpinnerDestreza = new javax.swing.JSpinner();
        SpinnerVigor = new javax.swing.JSpinner();
        SpinnerEstamina = new javax.swing.JSpinner();
        SpinnerMana = new javax.swing.JSpinner();
        SpinnerForca = new javax.swing.JSpinner();
        SpinnerSorte = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLatributoExtraFe = new javax.swing.JLabel();
        jLatributoExtraInteligencia = new javax.swing.JLabel();
        jLatributoExtraDestreza = new javax.swing.JLabel();
        jLatributoExtraVigor = new javax.swing.JLabel();
        jLatributoExtraEstamina = new javax.swing.JLabel();
        jLatributoExtraMana = new javax.swing.JLabel();
        jLatributoExtraSorte = new javax.swing.JLabel();
        jLatributoExtraForca = new javax.swing.JLabel();
        BtnConfirmar = new javax.swing.JButton();
        BtnVoltar = new javax.swing.JButton();
        LBtextoRestantes = new javax.swing.JLabel();
        CBRaca = new javax.swing.JComboBox<>();
        CBequipamento1 = new javax.swing.JComboBox<>();
        CBequipamento2 = new javax.swing.JComboBox<>();
        CBacessorio = new javax.swing.JComboBox<>();
        LBPontosRestantes = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();

        jLabel16.setText("+0");

        jLabel18.setText("+0");

        jLabel24.setText("+0");

        jLabel26.setText("+0");

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(783, 654));
        setResizable(false);
        setSize(new java.awt.Dimension(783, 654));

        jLabel1.setText("Nome");

        TFNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFNomeActionPerformed(evt);
            }
        });

        jLabel2.setText("Raça");

        jLabel3.setText("Equipamento 1");

        jLabel4.setText("Equipamento 2");

        jLabel5.setText("Acessório");

        TAdescricaoEquip1.setEditable(false);
        TAdescricaoEquip1.setColumns(20);
        TAdescricaoEquip1.setLineWrap(true);
        TAdescricaoEquip1.setRows(5);
        TAdescricaoEquip1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(TAdescricaoEquip1);

        TAdescricaoEquip2.setEditable(false);
        TAdescricaoEquip2.setColumns(20);
        TAdescricaoEquip2.setLineWrap(true);
        TAdescricaoEquip2.setRows(5);
        TAdescricaoEquip2.setWrapStyleWord(true);
        jScrollPane2.setViewportView(TAdescricaoEquip2);

        TAdescricaoAcessorio.setEditable(false);
        TAdescricaoAcessorio.setColumns(20);
        TAdescricaoAcessorio.setLineWrap(true);
        TAdescricaoAcessorio.setRows(5);
        TAdescricaoAcessorio.setWrapStyleWord(true);
        jScrollPane3.setViewportView(TAdescricaoAcessorio);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("Pontuação de Habilidade");

        SpinnerFe.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        SpinnerFe.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerFeStateChanged(evt);
            }
        });

        SpinnerInteligencia.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        SpinnerInteligencia.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerInteligenciaStateChanged(evt);
            }
        });

        SpinnerDestreza.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        SpinnerDestreza.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerDestrezaStateChanged(evt);
            }
        });

        SpinnerVigor.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        SpinnerVigor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerVigorStateChanged(evt);
            }
        });

        SpinnerEstamina.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        SpinnerEstamina.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerEstaminaStateChanged(evt);
            }
        });

        SpinnerMana.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        SpinnerMana.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerManaStateChanged(evt);
            }
        });

        SpinnerForca.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        SpinnerForca.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerForcaStateChanged(evt);
            }
        });

        SpinnerSorte.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        SpinnerSorte.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerSorteStateChanged(evt);
            }
        });

        jLabel7.setText("Fé");

        jLabel8.setText("Inteligência");

        jLabel9.setText("Destreza");

        jLabel10.setText("Vigor");

        jLabel11.setText("Estamina");

        jLabel12.setText("Mana");

        jLabel13.setText("Sorte");

        jLabel14.setText("Força");

        jLatributoExtraFe.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLatributoExtraFe.setText("0");

        jLatributoExtraInteligencia.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLatributoExtraInteligencia.setText("0");

        jLatributoExtraDestreza.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLatributoExtraDestreza.setText("0");

        jLatributoExtraVigor.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLatributoExtraVigor.setText("0");

        jLatributoExtraEstamina.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLatributoExtraEstamina.setText("0");

        jLatributoExtraMana.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLatributoExtraMana.setText("0");

        jLatributoExtraSorte.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLatributoExtraSorte.setText("0");

        jLatributoExtraForca.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLatributoExtraForca.setText("0");

        BtnConfirmar.setText("Confirmar");
        BtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConfirmarActionPerformed(evt);
            }
        });

        BtnVoltar.setText("Voltar ao inicio");
        BtnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltarActionPerformed(evt);
            }
        });

        LBtextoRestantes.setFont(new java.awt.Font("Corbel Light", 1, 18)); // NOI18N
        LBtextoRestantes.setText("Pontos Restantes");

        CBRaca.setMaximumRowCount(30);

        CBequipamento1.setMaximumRowCount(30);
        CBequipamento1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBequipamento1ItemStateChanged(evt);
            }
        });

        CBequipamento2.setMaximumRowCount(30);
        CBequipamento2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBequipamento2ItemStateChanged(evt);
            }
        });

        CBacessorio.setMaximumRowCount(30);
        CBacessorio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBacessorioItemStateChanged(evt);
            }
        });

        LBPontosRestantes.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        LBPontosRestantes.setText("0");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel17.setText("+");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel19.setText("+");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel20.setText("+");

        jLabel22.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel22.setText("+");

        jLabel23.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel23.setText("+");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel25.setText("+");

        jLabel27.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel27.setText("+");

        jLabel28.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel28.setText("+");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TFNome, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CBequipamento1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBequipamento2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(8, 8, 8)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel3)))
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(12, 12, 12)
                                            .addComponent(jLabel5)))
                                    .addComponent(CBRaca, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 15, Short.MAX_VALUE))
                            .addComponent(CBacessorio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLatributoExtraDestreza))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(SpinnerSorte, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(6, 6, 6)
                                                        .addComponent(jLabel13)))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jLabel25)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLatributoExtraSorte)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel27)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLatributoExtraEstamina))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(SpinnerDestreza, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(SpinnerFe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(SpinnerEstamina, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel11)))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLatributoExtraVigor)
                                        .addGap(97, 97, 97))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(96, 96, 96)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(SpinnerMana, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(SpinnerForca, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel12)
                                                                    .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jLabel28)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(jLatributoExtraForca))
                                                                    .addComponent(jLabel14)))))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(104, 104, 104)
                                                        .addComponent(jLabel22)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLatributoExtraMana)))
                                                .addGap(1, 1, 1))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(89, 89, 89)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(6, 6, 6)
                                                        .addComponent(jLabel10))
                                                    .addComponent(SpinnerVigor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(25, 25, 25))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLatributoExtraFe)
                                                .addGap(19, 19, 19)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(95, 95, 95)
                                                    .addComponent(jLabel17)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLatributoExtraInteligencia))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addGap(89, 89, 89)
                                                    .addComponent(jLabel8)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(95, 95, 95)
                                                .addComponent(SpinnerInteligencia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(LBtextoRestantes)
                                        .addGap(18, 18, 18)
                                        .addComponent(LBPontosRestantes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 32, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(BtnVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnConfirmar)
                .addGap(147, 147, 147))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LBtextoRestantes)
                            .addComponent(LBPontosRestantes))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLatributoExtraFe)
                                .addComponent(jLatributoExtraInteligencia)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SpinnerFe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpinnerInteligencia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLatributoExtraVigor)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SpinnerVigor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLatributoExtraDestreza)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SpinnerDestreza, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addGap(1, 1, 1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLatributoExtraSorte)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SpinnerSorte, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLatributoExtraMana))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SpinnerMana, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLatributoExtraForca)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SpinnerForca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)))
                        .addGap(98, 110, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CBRaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CBequipamento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(CBequipamento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLatributoExtraEstamina)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SpinnerEstamina, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CBacessorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnConfirmar)
                            .addComponent(BtnVoltar))
                        .addGap(26, 26, 26))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TFNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFNomeActionPerformed

    private void BtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConfirmarActionPerformed
        if(!TFNome.getText().isEmpty())
        {
            editarPersonagem();
        }else
        {
            JOptionPane.showMessageDialog(null, "Campo nome obrigatório!");
        }
    }//GEN-LAST:event_BtnConfirmarActionPerformed

    private void BtnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarActionPerformed
        TelaInicialPersonagem telaInicial = new TelaInicialPersonagem(user);
        telaInicial.setVisible(true);
        dispose();
    }//GEN-LAST:event_BtnVoltarActionPerformed

    private void CBequipamento1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBequipamento1ItemStateChanged
        if(armas != null && CBequipamento1.getSelectedItem() != null){
            for(int i = 0; i < armas.size(); i++)
            {
                if(CBequipamento1.getSelectedItem().equals(armas.get(i).getNome()))
                {
                    TAdescricaoEquip1.setText(armas.get(i).getDescr());
                }
            } 
        }
    }//GEN-LAST:event_CBequipamento1ItemStateChanged

    private void CBequipamento2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBequipamento2ItemStateChanged
        if(CBequipamento2.getSelectedItem() != null)
        {
            switch (classe) {
                case "Guerreiro":
                case "Bandido":
                    if(armas != null){
                        for(int i = 0; i < armas.size(); i++)
                        {
                            if(CBequipamento2.getSelectedItem().equals(armas.get(i).getNome()))
                            {
                                TAdescricaoEquip2.setText(armas.get(i).getDescr());
                            }
                        }
                    }   break;
                case "Mago":
                    if(cajados != null)
                    {
                        for(int i = 0; i < cajados.size(); i++)
                        {
                            if(CBequipamento2.getSelectedItem().equals(cajados.get(i).getNome()))
                            {
                                TAdescricaoEquip2.setText(cajados.get(i).getDescr());
                            }
                        }
                    }   break;
                case "Clerigo":
                    if(talismans != null)
                    {
                        for(int i = 0; i < talismans.size(); i++)
                        {
                            if(CBequipamento2.getSelectedItem().equals(talismans.get(i).getNome()))
                            {
                                TAdescricaoEquip2.setText(talismans.get(i).getDescr());
                            }
                        }
                    }   break;
                default:
                    break;
            }
        }
    }//GEN-LAST:event_CBequipamento2ItemStateChanged

    private void CBacessorioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBacessorioItemStateChanged
        if (acessorios != null)
        {
            for(int i = 0; i < acessorios.size(); i++)
            {
                if(CBacessorio.getSelectedItem().equals(acessorios.get(i).getNome())) {
                    TAdescricaoAcessorio.setText(acessorios.get(i).getDescr());
                }
            }
        }
    }//GEN-LAST:event_CBacessorioItemStateChanged

    private void SpinnerFeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerFeStateChanged
        
        int novoValor = (int) SpinnerFe.getValue();

        if (novoValor > feAnterior) {
            if(Integer.parseInt(LBPontosRestantes.getText()) >= (novoValor - feAnterior))
            {
                LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) - (novoValor - feAnterior)));
                feAnterior = novoValor;
            }else {
                SpinnerFe.setValue(inteligenciaAnterior);
            }
        } else {
            LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) + (feAnterior - novoValor)));
            feAnterior = novoValor;
        }

    }//GEN-LAST:event_SpinnerFeStateChanged

    private void SpinnerInteligenciaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerInteligenciaStateChanged
        
        int novoValor = (int) SpinnerInteligencia.getValue();

        if (novoValor > inteligenciaAnterior) {
            if(Integer.parseInt(LBPontosRestantes.getText()) >= (novoValor - inteligenciaAnterior))
            {
                LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) - (novoValor - inteligenciaAnterior)));
                inteligenciaAnterior = novoValor;
            }else {
                SpinnerInteligencia.setValue(inteligenciaAnterior);
            }
        } else {
            LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) + (inteligenciaAnterior - novoValor)));
            inteligenciaAnterior = novoValor;
        }

    }//GEN-LAST:event_SpinnerInteligenciaStateChanged

    private void SpinnerDestrezaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerDestrezaStateChanged
        
        int novoValor = (int) SpinnerDestreza.getValue();

        if (novoValor > destrezaAnterior) {
            if(Integer.parseInt(LBPontosRestantes.getText()) >= (novoValor - destrezaAnterior))
            {
                LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) - (novoValor - destrezaAnterior)));
                destrezaAnterior = novoValor;
            }else {
                SpinnerDestreza.setValue(destrezaAnterior);
            }
        } else {
            LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) + (destrezaAnterior - novoValor)));
            destrezaAnterior = novoValor;
        }

    }//GEN-LAST:event_SpinnerDestrezaStateChanged

    private void SpinnerVigorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerVigorStateChanged
        int novoValor = (int) SpinnerVigor.getValue();

        if (novoValor > vigorAnterior) {
            if(Integer.parseInt(LBPontosRestantes.getText()) >= (novoValor - vigorAnterior))
            {
                LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) - (novoValor - vigorAnterior)));
                vigorAnterior = novoValor;
            }else {
                SpinnerVigor.setValue(vigorAnterior);
            }
        } else {
            LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) + (vigorAnterior - novoValor)));
            vigorAnterior = novoValor;
        }
    }//GEN-LAST:event_SpinnerVigorStateChanged

    private void SpinnerEstaminaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerEstaminaStateChanged
        
        int novoValor = (int) SpinnerEstamina.getValue();

        if (novoValor > estaminaAnterior) {
            if(Integer.parseInt(LBPontosRestantes.getText()) >= (novoValor - estaminaAnterior))
            {
                LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) - (novoValor - estaminaAnterior)));
                estaminaAnterior = novoValor;
            }else {
                SpinnerEstamina.setValue(estaminaAnterior);
            }
        } else {
            LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) + (estaminaAnterior - novoValor)));
            estaminaAnterior = novoValor;
        }

    }//GEN-LAST:event_SpinnerEstaminaStateChanged

    private void SpinnerManaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerManaStateChanged
        
        int novoValor = (int) SpinnerMana.getValue();

        if (novoValor > manaAnterior) {
            if(Integer.parseInt(LBPontosRestantes.getText()) >= (novoValor - manaAnterior))
            {
                LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) - (novoValor - manaAnterior)));
                manaAnterior = novoValor;
            }else {
                SpinnerMana.setValue(manaAnterior);
            }
        } else {
            LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) + (manaAnterior - novoValor)));
            manaAnterior = novoValor;
        }

    }//GEN-LAST:event_SpinnerManaStateChanged

    private void SpinnerSorteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerSorteStateChanged
        
        int novoValor = (int) SpinnerSorte.getValue();

        if (novoValor > sorteAnterior) {
            if(Integer.parseInt(LBPontosRestantes.getText()) >= (novoValor - sorteAnterior))
            {
                LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) - (novoValor - sorteAnterior)));
                sorteAnterior = novoValor;
            }else {
                SpinnerSorte.setValue(sorteAnterior);
            }
        } else {
            LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) + (sorteAnterior - novoValor)));
            sorteAnterior = novoValor;
        }

    }//GEN-LAST:event_SpinnerSorteStateChanged

    private void SpinnerForcaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerForcaStateChanged
        
        int novoValor = (int) SpinnerForca.getValue();

        if (novoValor > forcaAnterior) {
            if(Integer.parseInt(LBPontosRestantes.getText()) >= (novoValor - forcaAnterior))
            {
                LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) - (novoValor - forcaAnterior)));
                forcaAnterior = novoValor;
            }else {
                SpinnerForca.setValue(forcaAnterior);
            }
        } else {
            LBPontosRestantes.setText(String.valueOf(Integer.parseInt(LBPontosRestantes.getText()) + (forcaAnterior - novoValor)));
            forcaAnterior = novoValor;
        }

    }//GEN-LAST:event_SpinnerForcaStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaEditarPersonagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEditarPersonagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEditarPersonagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEditarPersonagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEditarPersonagem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnConfirmar;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JComboBox<String> CBRaca;
    private javax.swing.JComboBox<String> CBacessorio;
    private javax.swing.JComboBox<String> CBequipamento1;
    private javax.swing.JComboBox<String> CBequipamento2;
    private javax.swing.JLabel LBPontosRestantes;
    private javax.swing.JLabel LBtextoRestantes;
    private javax.swing.JSpinner SpinnerDestreza;
    private javax.swing.JSpinner SpinnerEstamina;
    private javax.swing.JSpinner SpinnerFe;
    private javax.swing.JSpinner SpinnerForca;
    private javax.swing.JSpinner SpinnerInteligencia;
    private javax.swing.JSpinner SpinnerMana;
    private javax.swing.JSpinner SpinnerSorte;
    private javax.swing.JSpinner SpinnerVigor;
    private javax.swing.JTextArea TAdescricaoAcessorio;
    private javax.swing.JTextArea TAdescricaoEquip1;
    private javax.swing.JTextArea TAdescricaoEquip2;
    private javax.swing.JTextField TFNome;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLatributoExtraDestreza;
    private javax.swing.JLabel jLatributoExtraEstamina;
    private javax.swing.JLabel jLatributoExtraFe;
    private javax.swing.JLabel jLatributoExtraForca;
    private javax.swing.JLabel jLatributoExtraInteligencia;
    private javax.swing.JLabel jLatributoExtraMana;
    private javax.swing.JLabel jLatributoExtraSorte;
    private javax.swing.JLabel jLatributoExtraVigor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}

