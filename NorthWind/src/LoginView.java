import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends JInternalFrame implements ActionListener{
    public static Container ctnLogin;
    public static JLabel lblTexto, lblUsuario, lblSenha;
    public static JTextField txtUsuario;
    public static JPasswordField pwdSenha;
    public static JButton btnAcessar;
    
    public LoginView(){
        super("Acesso ao sistema");
        
        ctnLogin = new Container();
        ctnLogin.setLayout(null);
        this.add(ctnLogin);
        
        lblTexto = new JLabel("Preencha os campos abaixo");
        lblTexto.setBounds(50,15,300,20);
        ctnLogin.add(lblTexto);
        
        lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(30,55,150,20);
        ctnLogin.add(lblUsuario);
        
        lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30,105,150,20);
        ctnLogin.add(lblSenha);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(90,65,275,20);
        ctnLogin.add(txtUsuario);
        
        pwdSenha = new JPasswordField();
        pwdSenha.setBounds(90,105,275,20);
        ctnLogin.add(pwdSenha);
        
        btnAcessar = new JButton("Entrar");
        btnAcessar.addActionListener(this);
        btnAcessar.setBounds(30,155,340,30);
        ctnLogin.add(btnAcessar);
        
        this.setSize(400,250);
        this.show();
        
        txtUsuario.setText("joaoortiz");
        pwdSenha.setText("123456");
    }
    
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource()==btnAcessar){}
            try {
                UsuariosVO dadosUsuario = new UsuariosVO();
                
                dadosUsuario = UsuariosDAO.validaUsuario(txtUsuario.getText(), pwdSenha.getText());
                
                int perm = dadosUsuario.getPermissao();//Acessando permissão do usuári
                
                if(perm==0){
                    JOptionPane.showMessageDialog(null, "Dados inválidos!!", "ERRO", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, dadosUsuario.getNomeUsuario()+", Bem Vindo ao sistema");
                    //Habilitar botões
                    if(perm == 1){
                       for(int i =0; i<SistemaView.btnMenu.length; i++){
                           SistemaView.habilitaBotao(SistemaView.btnMenu[i], true);
                       }
                    }else if(perm == 2){
                        for(int i =0; i<SistemaView.btnMenu.length; i++){
                           if(i!=6)
                           SistemaView.habilitaBotao(SistemaView.btnMenu[i], true);
                       }
                    }else if(perm == 3){
                        for(int i =0; i<SistemaView.btnMenu.length; i++){
                           if(i!=2 && i!=6)
                           SistemaView.habilitaBotao(SistemaView.btnMenu[i], true);
                       }//Fechando for
                    }//Fechando else if
                }//Fechando else
                
                this.dispose();
                
            } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }
}
