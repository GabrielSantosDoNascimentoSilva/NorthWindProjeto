import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.*;

public class TransportadoraView extends JInternalFrame implements ActionListener{
    
    //declaração dos objetos
    public static JMenuBar mbrClientes;
    public static JMenu mnuArquivo, mnuConsultas, mnuAjuda;
    public static JMenuItem mniNovo, mniEditar, mniSalvar, mniExcluir, mniFechar;
    public static JMenuItem mniCodigo, mniNome;
    public static JMenuItem mniSobre;
    
    public static Container ctnTransportadora;
    public static JToolBar  tbrTransportadora;
    public static ImageIcon icnNovo, icnEditar, icnSalvar, icnConsulta, icnExcluir, icnImprimir, icnFechar;
    public static BotaoBarra btbNovo, btbEditar, btbSalvar, btbConsulta, btbExcluir, btbImprimir, btbFechar;
    
    public static String strCampos[] = {"Código da Transportadora:","Nome da Empresa:","Telefone:"};
    public static JLabel lblCampos[];
    public static JTextField txtCampos[];
    
    public static JScrollPane srcTransportadora;//painel de rolagem
    public static JTable tblTransportadora;//tabela grafica
    public static DefaultTableModel mdlTrasportadora;//responsavel por gerenciar os dados da tabela    
    
    public static JLabel lblBusca;
    public static JTextField txtBusca;
    public static JButton btnBusca,btnNovo;
    
    public static JButton btnSalvar, btnEditar, btnDeletar;
    
    public TransportadoraView (String tmpTitulo){
        
        super("NORTHWIND SYSTEM - Módulo " + tmpTitulo);
        
        //Construção e configuração dos objetos
         mbrClientes = new JMenuBar();
        this.setJMenuBar(mbrClientes);//adicionando o mbr na janela
        
        //MENUS
        mnuArquivo = new JMenu("Arquivo");
        mnuArquivo.setMnemonic('a');//habilita ALT+A
        mbrClientes.add(mnuArquivo);//adicionando na barra
        
        mnuConsultas = new JMenu("Consultas");
        mnuConsultas.setMnemonic('c');//habilita ALT+C
        mbrClientes.add(mnuConsultas);//adicionando na barra
        
        mnuAjuda = new JMenu("Ajuda");
        mnuAjuda.setMnemonic('j');//habilita ALT+J
        mbrClientes.add(mnuAjuda);//adicionando na barra
        
        mniNovo = new JMenuItem("Novo " + tmpTitulo.substring(0, tmpTitulo.length()-1));
        mniNovo.addActionListener(this);
        mnuArquivo.add(mniNovo);
        
        mnuArquivo.add(new JSeparator());
        
        mniEditar = new JMenuItem("Editar " + tmpTitulo.substring(0, tmpTitulo.length()-1));
        mniEditar.addActionListener(this);
        mnuArquivo.add(mniEditar);
       
        mniSalvar = new JMenuItem("Salvar " + tmpTitulo.substring(0, tmpTitulo.length()-1));
        mniSalvar.addActionListener(this);
        mnuArquivo.add(mniSalvar);
        
        mnuArquivo.add(new JSeparator());
        
        mniExcluir = new JMenuItem("Excluir " + tmpTitulo.substring(0, tmpTitulo.length()-1));
        mniExcluir.addActionListener(this);
        mnuArquivo.add(mniExcluir);
        
        mniFechar = new JMenuItem("Fechar");
        mniFechar.addActionListener(this);
        mnuArquivo.add(mniFechar);
        
        mnuArquivo.add(new JSeparator());
        
        mniCodigo = new JMenuItem("Código");
        mniCodigo.addActionListener(this);
        mnuConsultas.add(mniCodigo);
        
        mniNome = new JMenuItem("Nome");
        mniNome.addActionListener(this);
        mnuConsultas.add(mniNome);
        
        mniSobre = new JMenuItem("Sobre");
        mniSobre.addActionListener(this);
        mnuAjuda.add(mniSobre);
        
        ctnTransportadora = new Container();
        ctnTransportadora.setLayout(null);
        this.add(ctnTransportadora);
        
        
        tbrTransportadora = new JToolBar();
        tbrTransportadora.setBounds(0,0,250,32);
        ctnTransportadora.add(tbrTransportadora);
        
        icnNovo = new ImageIcon("img/icn/iconNovo.png");
        btbNovo = new BotaoBarra(0, icnNovo, "Novo Cliente");
        tbrTransportadora.add(btbNovo);
        
        icnEditar = new ImageIcon("img/icn/iconEditar.png");
        btbEditar = new BotaoBarra(1, icnEditar, "Editar Cliente");
        tbrTransportadora.add(btbEditar);
   
        icnSalvar = new ImageIcon("img/icn/iconSalvar.png");
        btbSalvar = new BotaoBarra(2, icnSalvar, "Salvar Cliente");
        tbrTransportadora.add(btbSalvar);
        
        icnConsulta = new ImageIcon("img/icn/iconConsultar.png");
        btbConsulta = new BotaoBarra(3, icnConsulta, "Consultar Cliente");
        tbrTransportadora.add(btbConsulta);
        
        icnExcluir = new ImageIcon("img/icn/iconExcluir.gif");
        btbExcluir = new BotaoBarra(4, icnExcluir, "Excluir Cliente");
        tbrTransportadora.add(btbExcluir);
        
        icnImprimir = new ImageIcon("img/icn/iconImprimir.png");
        btbImprimir = new BotaoBarra(5, icnImprimir, "Imprimir Cliente");
        tbrTransportadora.add(btbImprimir);
        
        icnFechar = new ImageIcon("img/icn/iconFechar.png");
        btbFechar = new BotaoBarra(6, icnFechar, "Fechar Cliente");
        tbrTransportadora.add(btbFechar);
        
        //definindo vetores de labels e txts
        lblCampos = new JLabel[strCampos.length];
        txtCampos = new JTextField[strCampos.length];
        
        for(int i =0; i<strCampos.length; i++){
            lblCampos[i] = new JLabel(strCampos[i]);
            lblCampos[i].setFont(new Font("Tahoma",Font.PLAIN,16));
            lblCampos[i].setForeground(new Color(0,0,0));
            lblCampos[i].setBounds(15,(i*35)+50,180,35);
            ctnTransportadora.add(lblCampos[i]);
            
            txtCampos[i] = new JTextField();
            txtCampos[i].setFont(new Font("Verdana",Font.PLAIN,16));
            txtCampos[i].setBounds(200,(i*40)+50,300,35);
            ctnTransportadora.add(txtCampos[i]);
        }//fechando for
       
        tblTransportadora = new JTable();
        srcTransportadora = new JScrollPane(tblTransportadora);
        mdlTrasportadora = (DefaultTableModel) tblTransportadora.getModel();
        String camposTabela[] = {"Código","Empresa","Telefone"};
        
        
        for(int i=0; i<camposTabela.length;i++){
            mdlTrasportadora.addColumn(camposTabela[i]);
        }
        
        srcTransportadora.setBounds(650,85,450,410);
        ctnTransportadora.add(srcTransportadora);
        
        carregarLista();
        
        
        tblTransportadora.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_DOWN || 
                   evt.getKeyCode() == KeyEvent.VK_UP ||
                   evt.getKeyCode() == KeyEvent.VK_ENTER){
                    
                    String codigo;
                    codigo = tblTransportadora.getValueAt(tblTransportadora.getSelectedRow(),0).toString();
                    executaConsulta(codigo);
                }
            }
        });
        
        tblTransportadora.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt){
                String codigo;
                codigo = tblTransportadora.getValueAt(tblTransportadora.getSelectedRow(), 0).toString();
                executaConsulta(codigo);
                //JOptionPane.showMessageDialog(null, codigo);
            }
        
        }
        );
        
        lblBusca = new JLabel("Digite o código:");
        lblBusca.setBounds(650,55,160,20);
        ctnTransportadora.add(lblBusca);
        
        txtBusca = new JTextField();
        txtBusca.setBounds(750,55,180,20);
        ctnTransportadora.add(txtBusca);
        
        btnBusca = new JButton("Busca Rápida");
        btnBusca.addActionListener(this);
        btnBusca.setBounds(940,55,160,20);
        ctnTransportadora.add(btnBusca);

        btnNovo = new JButton("Novo");
        btnNovo.addActionListener(this);
        btnNovo.setBounds(520,90,100,30);
        ctnTransportadora.add(btnNovo);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this);
        btnSalvar.setBounds(520,130,100,30);
        ctnTransportadora.add(btnSalvar);
         
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(this);
        btnEditar.setBounds(520,170,100,30);
        btnEditar.setVisible(false);
        ctnTransportadora.add(btnEditar);    
        
        btnDeletar = new JButton("Deletar");
        btnDeletar.addActionListener(this);
        btnDeletar.setBounds(520,210,100,30);
        btnDeletar.setVisible(false);
        ctnTransportadora.add(btnDeletar);
        
        this.addInternalFrameListener(new InternalFrameAdapter(){
        
            public void internalFrameClosing(InternalFrameEvent evt){
                SistemaView.btnMenu[3].setEnabled(true);
            }
        }
        );
        
        
        desbloquearCampos(false);//Bloqueado campos
        this.setClosable(true);//fechar
        this.setIconifiable(true);//minimizar
        this.setResizable(false);//nao redimensionavel
        
        this.setSize(SistemaView.dskJanelas.getWidth(),SistemaView.dskJanelas.getHeight());
        this.show();
        
    }//fechando construtor
    
    public void actionPerformed(ActionEvent evt){
        
        if(evt.getSource() == btnBusca){
            executaConsulta(txtBusca.getText());
        }       
        else if(evt.getSource() == btnSalvar || evt.getSource() == mniSalvar){
                executaCadastro(preencheObjeto());
        }
        else if(evt.getSource() == btnEditar || evt.getSource() == mniEditar){
            desbloquearCampos(true);
        }
        else if(evt.getSource() == mniNovo || evt.getSource() == btnNovo){
            desbloquearCampos(true);
            limparCampos();
            txtCampos[0].setText(Integer.toString(qtdTransportadora()+1));
            btnEditar.setVisible(false);
            btnDeletar.setVisible(false);
        }
        else if(evt.getSource() == mniExcluir || evt.getSource() == btnDeletar){
            desbloquearCampos(false);
            deletarTransportadora(preencheObjeto());
            limparCampos();
        }
        
    }//fechando actionPerformed
    
    public static void executaConsulta(String tmpBusca){
      
        TransportadoraVO tmpTransportadora = new TransportadoraVO();
       
       try{
           
           tmpTransportadora = TransportadoraDAO.consultaTransportadora(1, tmpBusca);
           
           if(tmpTransportadora.getCodigo() == null){
               JOptionPane.showMessageDialog(null,
                       "Cliente não encontrado.");
           }else{
               preencheCampos(tmpTransportadora);
           }
           
       }catch(Exception erro){
           JOptionPane.showMessageDialog(null, 
                            erro.getMessage());
       }
        
        
    }
    
    public static void executaCadastro(TransportadoraVO tmpTransportadora){
        
        boolean statusCad;
        try {
            if(validaCampos()){
                statusCad = TransportadoraDAO.cadastrarTrasportadora(tmpTransportadora);
                if(statusCad){
                    JOptionPane.showMessageDialog(null,"Transportadora " + tmpTransportadora.getNomeEmpresa()+ " Cadastrado.");
                    carregarLista();
                }else{
                    TransportadoraDAO.alterarTransportadora(tmpTransportadora,txtCampos[0].getText());
                    JOptionPane.showMessageDialog(null,"Transportadora alterado.");
                    carregarLista();
                }
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
     public static void deletarTransportadora(TransportadoraVO tmpTransportadora){
        
        boolean statusCad;
        try {
            if(validaCampos()){
                TransportadoraDAO.deletarTrasportadora(tmpTransportadora);
                JOptionPane.showMessageDialog(null,"Transportadora " + tmpTransportadora.getNomeEmpresa()+ " Deletada.");
                carregarLista();
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    public static void preencheCampos(TransportadoraVO tmpCliente){
        txtCampos[0].setText(tmpCliente.getCodigo());
        txtCampos[1].setText(tmpCliente.getNomeEmpresa());
        txtCampos[2].setText(tmpCliente.getTelefone());
        
        btnEditar.setVisible(true);
        btnDeletar.setVisible(true);
        
    }//fechando preencheCampos 
    
    public static void carregarLista(){
        
        limparLista();
        try {
            java.util.List<TransportadoraVO> vetorTansportadora = new java.util.ArrayList<TransportadoraVO>();
            
            vetorTansportadora = TransportadoraDAO.listaTransportadoras();
            
            for (TransportadoraVO TransportadoraAtual:vetorTansportadora) {
                String dados[] = new String[4];
                dados[0] = TransportadoraAtual.getCodigo();
                dados[1] = TransportadoraAtual.getNomeEmpresa();
                dados[2] = TransportadoraAtual.getTelefone();
                
                mdlTrasportadora.addRow(dados);
            }
        } catch (Exception erro) {
           JOptionPane.showMessageDialog(null,erro.getMessage());
        }
        
    }
    
    public static void limparLista(){
        while(mdlTrasportadora.getRowCount()>0){
            mdlTrasportadora.removeRow(0);
        }
    }
    
    public static TransportadoraVO preencheObjeto(){
        TransportadoraVO tmpTransportadora = new TransportadoraVO();
        tmpTransportadora.setCodigo(txtCampos[0].getText());
        tmpTransportadora.setNomeEmpresa(txtCampos[1].getText());
        tmpTransportadora.setTelefone(txtCampos[2].getText());
        
        return tmpTransportadora;
    }
    
     public static boolean validaCampos(){
        for (int i = 0; i < txtCampos.length; i++) {
            if(txtCampos[i].getText().trim().compareTo("")==0){
                JOptionPane.showMessageDialog(null, "preenca o campo: " + strCampos[i]);
                txtCampos[i].grabFocus();
                return false;
            }
        }
        return true;
    }
     
     public static void limparCampos(){
        for(int i=0; i<txtCampos.length; i++){
            txtCampos[i].setText(null);
        }//fechando for
    }
     
     public static int qtdTransportadora(){
        int qtd = 0;
        try{
            qtd = TransportadoraDAO.qtdTransportadora();
       }catch(Exception erro){
           JOptionPane.showMessageDialog(null, 
                            erro.getMessage());
       }
        return qtd;
     }
     
    public static void desbloquearCampos(boolean tmpStatus){
        for (int i=0; i< txtCampos.length; i++){
            txtCampos[i].setEditable(tmpStatus);
            txtCampos[0].setEditable(false);
        }
    }//Fechando desbloquearCampos
}//fechando classe
