//pacote de classe de acesso 
//e manipulação de BD

import java.sql.*; 

public class ConexaoDAO {
    
    //classe Connection: abertura e fechamento do BD
    public static Connection connSistema;
    
    public static void abreConexao() throws Exception{
        
        try{
            Class.forName("com.mysql.jdbc.Driver");//driver do BD
            //abertura da conexao com o BD
            connSistema = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind","root","");
            
            System.out.println("Conexão Estabelecida");
            
            
        }catch(Exception erro){
            throw new Exception ("Falha na abertura do BD.\n Erro Original: " + erro.getMessage());
        }
        
    }//fechando abreConexao
    
    
    
    public static void fechaConexao() throws Exception{
    
        try{
            connSistema.close();
            System.out.println("Conexão Encerrada");
        }catch(Exception erro){
            throw new Exception ("Não há conexões válidas.\nErro original: "+ erro.getMessage());
        }
        
    }//fechando fechaConexao
        
}
