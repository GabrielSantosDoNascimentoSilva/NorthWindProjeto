import javax.swing.*; // Pacote de Elementos Graficos
import java.awt.*; // Pacote que pega a Medida da Tela

// Clausa de "Extends" Simboliza a herança de classes.
public class LoadView extends JFrame{
    
        public static int xFinal, yFinal;
    
    public LoadView(){ // Abre Construtor
        
        int largTela = 640;
        int altTela = 480;
        
        // Container é uma camada responsavel por adicionar os Elementos na Janela
        Container ctn = new Container();
        ctn.setLayout(null); // Permite o Livre posicionamento dos Elementos
        
        // São Rotulos e recebem imagem ou Textos
        JLabel lblImagem = new JLabel(new ImageIcon("IMG/ImgInicial.jpg"));
        
        this.add(ctn); // Adicionando o Container na Janela
        lblImagem.setBounds(0,0,largTela,altTela); // Definindo posição da Label
        ctn.add(lblImagem); // Adicionando a Label no Container
        
        centralizarTela(largTela, altTela);
        
        this.setUndecorated(true); // Retira as Bordas
        this.setLocation(xFinal,yFinal); // Coordenadas para Abrir a Janela
        this.setSize(largTela,altTela);
        
        try{
                this.show(); // Mostra a Tela
                Thread.sleep(3000); // 1 Segundo
                this.dispose(); // Fecha a Janela
                
                // Instanciando a Tela Principal 
                
                SistemaView obTelaSistema = new SistemaView();
                
        } catch(Exception erro){
            JOptionPane.showMessageDialog(null,"Falha no Carregamento do Arquivo",
                                          "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
        
    } // Fechando Construtor
    
    public static void centralizarTela(int tmpL, int tmpA){
        
        Toolkit info = Toolkit.getDefaultToolkit(); // Acesso a Informações do Dispositivo
        Dimension dim = info.getScreenSize();
        System.out.println (dim.getWidth());
        System.out.println (dim.getHeight());
        
       
       xFinal = (int)(dim.getWidth()/2)-(tmpL/2); // Definindo Coordenada X
       yFinal = (int)(dim.getHeight()/2)-(tmpA/2); // Definindo Coordenada Y
       
      

    }
    
} // Fechando Classe


