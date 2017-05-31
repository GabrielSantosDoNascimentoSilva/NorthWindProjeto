import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class ClientesView extends JInternalFrame implements ActionListener{
    
    //declaração dos objetos
    public static JMenuBar mbrClientes;
    public static JMenu mnuArquivo, mnuConsultas, mnuAjuda;
    public static JMenuItem mniNovo, mniEditar, mniSalvar, mniExcluir, mniFechar;
    public static JMenuItem mniCodigo, mniNome;
    public static JMenuItem mniSobre;
    
    public static Container ctnClientes;
    public static JToolBar  tbrClientes;
    public static ImageIcon icnNovo, icnEditar, icnSalvar, icnConsulta, icnExcluir, icnImprimir, icnFechar;
    public static BotaoBarra btbNovo, btbEditar, btbSalvar, btbConsulta, btbExcluir, btbImprimir, btbFechar;
    
    public static String strCampos[] = {"Código do Cliente:","Nome da Empresa:","Representante:","Cargo:","Endereço:","Cidade:","Estado:","País:","CEP:","Telefone:","Fax:"};
    public static JLabel lblCampos[];
    public static JTextField txtCampos[];
    
    public static JScrollPane scrClientes;//painel de rolagem
    public static JTable tblClientes;//tabela grafica
    public static DefaultTableModel mdlClientes;//responsavel por gerenciar os dados da tabela    
    
    public static JLabel lblBusca;
    public static JTextField txtBusca;
    public static JButton btnBusca;
    
    public static JButton btnSalvar, btnAtivar, btnEditar;
    
    public ClientesView (String tmpTitulo){
        
        super("NORTHWIND SYSTEM - Módulo " + tmpTitulo);
        
        //Construção e configuração dos objetos
        
        //BARRA DE MENU
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
        
        ctnClientes = new Container();
        ctnClientes.setLayout(null);
        this.add(ctnClientes);
        
        
        tbrClientes = new JToolBar();
        tbrClientes.setBounds(0,0,250,32);
        ctnClientes.add(tbrClientes);
        
        icnNovo = new ImageIcon("img/icn/iconNovo.png");
        btbNovo = new BotaoBarra(0, icnNovo, "Novo Cliente");
        tbrClientes.add(btbNovo);
        
        icnEditar = new ImageIcon("img/icn/iconEditar.png");
        btbEditar = new BotaoBarra(1, icnEditar, "Editar Cliente");
        tbrClientes.add(btbEditar);
   
        icnSalvar = new ImageIcon("img/icn/iconSalvar.png");
        btbSalvar = new BotaoBarra(2, icnSalvar, "Salvar Cliente");
        tbrClientes.add(btbSalvar);
        
        icnConsulta = new ImageIcon("img/icn/iconConsultar.png");
        btbConsulta = new BotaoBarra(3, icnConsulta, "Consultar Cliente");
        tbrClientes.add(btbConsulta);
        
        icnExcluir = new ImageIcon("img/icn/iconExcluir.gif");
        btbExcluir = new BotaoBarra(4, icnExcluir, "Excluir Cliente");
        tbrClientes.add(btbExcluir);
        
        icnImprimir = new ImageIcon("img/icn/iconImprimir.png");
        btbImprimir = new BotaoBarra(5, icnImprimir, "Imprimir Cliente");
        tbrClientes.add(btbImprimir);
        
        icnFechar = new ImageIcon("img/icn/iconFechar.png");
        btbFechar = new BotaoBarra(6, icnFechar, "Fechar Cliente");
        tbrClientes.add(btbFechar);
        
        //definindo vetores de labels e txts
        lblCampos = new JLabel[strCampos.length];
        txtCampos = new JTextField[strCampos.length];
        
        for(int i =0; i<strCampos.length; i++){
            lblCampos[i] = new JLabel(strCampos[i]);
            lblCampos[i].setFont(new Font("Tahoma",Font.PLAIN,16));
            lblCampos[i].setForeground(new Color(0,0,0));
            lblCampos[i].setBounds(15,(i*35)+50,180,35);
            ctnClientes.add(lblCampos[i]);
            
            txtCampos[i] = new JTextField();
            txtCampos[i].setFont(new Font("Verdana",Font.PLAIN,16));
            txtCampos[i].setBounds(200,(i*40)+50,300,35);
            ctnClientes.add(txtCampos[i]);
        }//fechando for
       
        tblClientes = new JTable();
        scrClientes = new JScrollPane(tblClientes);
        mdlClientes = (DefaultTableModel) tblClientes.getModel();
        String camposTabela[] = {"Código","Empresa","Cidade","Telefone"};
        
        
        for(int i=0; i<camposTabela.length;i++){
            mdlClientes.addColumn(camposTabela[i]);
        }
        
        scrClientes.setBounds(650,85,450,410);
        ctnClientes.add(scrClientes);
        
        carregarLista();
        
        tblClientes.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_DOWN || 
                   evt.getKeyCode() == KeyEvent.VK_UP ||
                   evt.getKeyCode() == KeyEvent.VK_ENTER){
                    
                    String codigo;
                    codigo = tblClientes.getValueAt(tblClientes.getSelectedRow(),0).toString();
                    executaConsulta(codigo);
                }
            }
        });
        
        tblClientes.addMouseListener(new MouseAdapter(){

            public void mouseClicked(MouseEvent evt){
                String codigo;
                codigo = tblClientes.getValueAt(tblClientes.getSelectedRow(),0).toString();
                executaConsulta(codigo);
            }
            
        }
        );
        
        
        lblBusca = new JLabel("Digite o código:");
        lblBusca.setBounds(650,55,160,20);
        ctnClientes.add(lblBusca);
        
        txtBusca = new JTextField();
        txtBusca.setBounds(750,55,180,20);
        ctnClientes.add(txtBusca);
        
        btnBusca = new JButton("Busca Rápida");
        btnBusca.addActionListener(this);
        btnBusca.setBounds(940,55,160,20);
        ctnClientes.add(btnBusca);
        
        btnAtivar = new JButton("Ativar");
        btnAtivar.setVisible(false);
        btnAtivar.addActionListener(this);
        btnAtivar.setBounds(520,90,100,30);
        ctnClientes.add(btnAtivar);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this);
        btnSalvar.setBounds(520,130,100,100);
        ctnClientes.add(btnSalvar);
        
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(this);
        btnEditar.setBounds(520,240,100,100);
        ctnClientes.add(btnEditar);
        
        this.addInternalFrameListener(new InternalFrameAdapter(){
        
            public void internalFrameClosing(InternalFrameEvent evt){
                SistemaView.btnMenu[0].setEnabled(true);
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
    
    //Acionando os botões
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
        
        else if(evt.getSource() == mniNovo){
            limparCampos();
            desbloquearCampos(true);
        }
        else if(evt.getSource() == btnAtivar){
            try {
                ClientesVO tmpCliente = new ClientesVO();
                tmpCliente = ClientesDAO.consultaCliente(1, txtCampos[0].getText());
                int statusAtual = tmpCliente.getStatus();
                int novoStatus;
                
                if (statusAtual == 1)
                    novoStatus = 0;
                else
                    novoStatus = 1;
                
                ClientesDAO.habilitarClientes(tmpCliente.getCodigo(), novoStatus);
                
                tmpCliente = ClientesDAO.consultaCliente(1, txtCampos[0].getText());
                
                preencheCampos(tmpCliente);
                
                JOptionPane.showMessageDialog(null, "Status alterado");
                
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }
    }//fechando actionPerformed
        
    public static void executaConsulta(String tmpBusca){
      
        ClientesVO tmpCliente = new ClientesVO();
       
       try{
           
           tmpCliente = ClientesDAO.consultaCliente(1, tmpBusca);
           
           if(tmpCliente.getCodigo() == null){
               JOptionPane.showMessageDialog(null,
                       "Cliente não encontrado.");
           }else{
               preencheCampos(tmpCliente);
           }
           
       }catch(Exception erro){
           JOptionPane.showMessageDialog(null, 
                            erro.getMessage());
       }
        
        
    }
    
    public static void executaCadastro(ClientesVO tmpCliente){
   
        try{
          
            boolean statusCad;
            
            if(validaCampos()){
                
                statusCad = ClientesDAO.cadastraCliente(tmpCliente);
                
                if(statusCad == true){
                    JOptionPane.showMessageDialog(null, 
                        "Cliente " + tmpCliente.getNomeEmpresa() +
                        " cadastrado.");
                    limparCampos();
                    carregarLista();
                }else{
                    JOptionPane.showMessageDialog(null, 
                            "Código já existente.");
                            
                }
                
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog
                (null, erro.getMessage());
        }
        
    }
    
    public static void preencheCampos(ClientesVO tmpCliente){
        txtCampos[0].setText(tmpCliente.getCodigo());
        txtCampos[1].setText(tmpCliente.getNomeEmpresa());
        txtCampos[2].setText(tmpCliente.getNomeRepresentante());
        txtCampos[3].setText(tmpCliente.getCargoRepresentante());
        txtCampos[4].setText(tmpCliente.getEndereco());
        txtCampos[5].setText(tmpCliente.getCidade());
        txtCampos[6].setText(tmpCliente.getEstado());
        txtCampos[7].setText(tmpCliente.getCep());
        txtCampos[8].setText(tmpCliente.getPais());
        txtCampos[9].setText(tmpCliente.getTelefone());
        txtCampos[10].setText(tmpCliente.getFax());
        
        btnAtivar.setVisible(true);
        
        if(tmpCliente.getStatus() == 0){
            txtCampos[1].setForeground(Color.red);                    
            btnAtivar.setText("Ativar");
        }else{
            txtCampos[1].setForeground(Color.black);                    
            btnAtivar.setText("Desativar");
        }
        
    }//fechando preencheCampos
    
    public static void carregarLista(){
      
        while(mdlClientes.getRowCount()>0){
            mdlClientes.removeRow(0);
        }
        
        try{
            java.util.List<ClientesVO> vetorClientes = new java.util.ArrayList<ClientesVO>();
         
            vetorClientes = ClientesDAO.listaClientes();
            
            
            for(ClientesVO clienteAtual:vetorClientes){
                String dados[] = new String[4];
                dados[0] = clienteAtual.getCodigo();
                dados[1] = clienteAtual.getNomeEmpresa();
                dados[2] = clienteAtual.getCidade();
                dados[3] = clienteAtual.getTelefone();
                
                //add vetor de string na lista
                mdlClientes.addRow(dados);                                
            }//fechando for
                        
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        
    }//fechando carregarLista
    
    public static ClientesVO preencheObjeto(){
        
        //Instanciando objeto VO
        ClientesVO tmpCliente = new ClientesVO();
        
        tmpCliente.setCodigo(txtCampos[0].getText());
        tmpCliente.setNomeEmpresa(txtCampos[1].getText());
        tmpCliente.setNomeRepresentante(txtCampos[2].getText());
        tmpCliente.setCargoRepresentante(txtCampos[3].getText());
        tmpCliente.setEndereco(txtCampos[4].getText());
        tmpCliente.setCidade(txtCampos[5].getText());
        tmpCliente.setEstado(txtCampos[6].getText());
        tmpCliente.setPais(txtCampos[7].getText());
        tmpCliente.setCep(txtCampos[8].getText());
        tmpCliente.setTelefone(txtCampos[9].getText());
        tmpCliente.setFax(txtCampos[10].getText());
        tmpCliente.setStatus(1);
        
        return tmpCliente;
        
    } //fechando preencheObjeto    
    
    public static boolean validaCampos(){
        
        for(int i=0; i<txtCampos.length; i++){
            if(txtCampos[i].getText().trim().compareTo("")==0){
               JOptionPane.showMessageDialog
                    (null, "Preencha o campo " + strCampos[i].toUpperCase());
               txtCampos[i].grabFocus();
               return false;
            }//fechando if
        }//fechando for
        
        return true;
    }//fechando validaCampos
    
    public static void limparCampos(){
        for(int i=0; i<txtCampos.length; i++){
            txtCampos[i].setText(null);
        }//fechando for
    }//fechando limparCampos

    public static void desbloquearCampos(boolean tmpStatus){
        for (int i=0; i< txtCampos.length; i++){
            txtCampos[i].setEditable(tmpStatus);
        }
    }//Fechando desbloquearCampos
}//fechando classe
