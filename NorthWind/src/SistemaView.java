import javax.swing.*; // Elementos Graficos
import java.awt.*; // Container
import java.awt.event.*; // ActionListener

public class SistemaView extends JFrame implements ActionListener{
    
    /********* Declaração dos Objetos ************/
    
    public static Container ctnPrincipal, ctnTopo, ctnMenu;
    public static JLabel lblBanner;
    public static JButton btnMenu[]; // Vetor dos Botões
    public static JDesktopPane dskJanelas; // Permite a Abertura de Janelas Internas
    
    
    public SistemaView(){ // Abrindo Construtor
        super("NorthWind - Sistema Gerenciador de Vendas");
        
        
    /********* Construção (Instancia) E Configuração dos Objetos ************/
        
    ctnPrincipal = new Container(); // Instanciando
    ctnPrincipal.setLayout(new BorderLayout()); // Definindo Layout
    this.add(ctnPrincipal); // Adicionando ctnPrincipal na Janela
    
    ctnTopo = new Container();
    ctnTopo.setLayout(new GridLayout(2,1)); // Linhas por Colunas
    ctnPrincipal.add(ctnTopo,BorderLayout.NORTH); // Adicionando ctnTopo no North do Principal
    
    lblBanner = new JLabel(new ImageIcon("img/imgBanner.jpg"));
    ctnTopo.add(lblBanner); // Adicionando o Banner na Primeira Linha do Topo
    
    ctnMenu = new Container();
    ctnMenu.setLayout(new GridLayout(2,4));
    ctnTopo.add(ctnMenu);
    
    // Declaração de Vetor com Valores
    String strModulos[]={"CLIENTES","FUNCIONARIOS","FORNECEDORES","PRODUTOS","VENDAS","CAIXA","USUARIOS","SAIR"};
    
   
    btnMenu = new JButton[8]; // Instanciando Vetor de Botões
    for (int i=0;i<btnMenu.length;i++){
        btnMenu[i] = new JButton(new ImageIcon("img/" + i + ".jpg"));
        btnMenu[i].addActionListener(this);// Adicionando Propriedade de Ouvinte de Ações
        ctnMenu.add(btnMenu[i]);
        habilitaBotao(btnMenu[i], false);//Desabilitando botões
    } // Fechando For
    
    dskJanelas = new JDesktopPane();
    ctnPrincipal.add(dskJanelas,BorderLayout.CENTER);
    
    /***** CONFIGURAÇÃO DA JANELA (JFRAME) *******/
    
    this.addWindowListener( new WindowAdapter() {
        public void windowClosing(WindowEvent evt){
            if(!fecharJanela())
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            else
                dispose(); // Fecha Janela
            
        }
        
    }
    );
    
    
    
        // Dimensionando a Janela FullScreen
        this.setSize(montarTela());
        this.show();
        // Encerrando o Processo Java ao Fechar a Tela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Adicionando te,a interna de logion ao desktop
        dskJanelas.add(new LoginView());
        
    } // Fechando Construtor
    
    
    public static Dimension montarTela(){
    Toolkit info = Toolkit.getDefaultToolkit();
    Dimension res = info.getScreenSize();
    
    return res;
            }
    
    
    public static boolean fecharJanela(){
        int resp = JOptionPane.showConfirmDialog(null,"Deseja Realmente Sair?","SAIR",JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION)
            return true;
        else
            return false;
    }
    
    // Metodo que Identifica Qual Botão foi Acionado e Armazena o Botão no Objeto evt (ActionEvent evt)
    public void actionPerformed(ActionEvent evt){ // Abre Action
      
        if(evt.getSource()==btnMenu[0]){
        dskJanelas.add(new ClientesView("CLIENTES"));
        btnMenu[0].setEnabled(false); //des. botao clientes
    }
        if(evt.getSource()==btnMenu[3]){
        dskJanelas.add(new TransportadoraView("TRANSPORTADORA"));
        btnMenu[3].setEnabled(false); //des. botao transportadora
    }
       
       
        if(evt.getSource() == btnMenu[7]){
          if(fecharJanela())
              System.exit(0);
      }
        
    } //Fechando ActionPerformed
    
    public static void habilitaBotao(JButton tmpButao, boolean tmpStatus){
        tmpButao.setEnabled(tmpStatus);
    }
    
} // Fechando Classe
