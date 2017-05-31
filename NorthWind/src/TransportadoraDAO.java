import java.sql.*;
import java.util.*;

public class TransportadoraDAO {
    
    //Classe responsavel pela execução dos comandos SQL
    public static Statement stTrasportadora;
    //Classe responsavel pelo armazenamento de uma consulta SELECT
    public static ResultSet rsTransportadora;
    
    public static boolean cadastrarTrasportadora(TransportadoraVO tmpTransportadora) throws Exception{
     
        /***ABRE CONEXAO***/
        try{
            ConexaoDAO.abreConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //INSERT - Linhas do Cadastro
            String sqlCadastra = "";
            sqlCadastra += "insert into SHIPPERS(";
            sqlCadastra += "ShipperID, CompanyName, ";
            sqlCadastra += "Phone) values(";
            sqlCadastra += "'"+tmpTransportadora.getCodigo()+"',";
            sqlCadastra += "'"+tmpTransportadora.getNomeEmpresa()+"',";
            sqlCadastra += "'"+tmpTransportadora.getTelefone()+"')";
            
            if(consultaTransportadora(1, tmpTransportadora.getCodigo()).getNomeEmpresa() == null){
                ConexaoDAO.abreConexao();
                stTrasportadora = ConexaoDAO.connSistema.createStatement();
                stTrasportadora.executeUpdate(sqlCadastra);
                System.out.println(sqlCadastra);
                ConexaoDAO.fechaConexao();
                return true;
            } else{
                ConexaoDAO.fechaConexao();
                return false;
            }
        }catch(Exception erro){
            throw new Exception (
                    "Falha no Insert. "
                            + "Verifique sintaxe da instrução."
                            + "\nErro Original:" 
                                    + erro.getMessage());
        }
        
    }
    
    public static TransportadoraVO consultaTransportadora(int tmpTipo, String tmpBusca) throws Exception{
     
         TransportadoraVO tmpTransportadora = new TransportadoraVO();
        
         /***ABRE CONEXAO***/
        try{
            ConexaoDAO.abreConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            
            String sqlConsulta = "";
            
            //SELECT - Execução da Consulta
            if(tmpTipo == 1){ //Busca por codigo
                sqlConsulta = "SELECT * FROM shippers where ShipperID like '" + tmpBusca + "'";
             }else if(tmpTipo == 2){ // busca por nome
                sqlConsulta = "SELECT * FROM shippers where companyname like '%" + tmpBusca + "%'";
             }
            
            //ativando statement para execução do SQL
            stTrasportadora = ConexaoDAO.connSistema.createStatement();
            
            //executando Select e armazenando resultado no resultSet
            rsTransportadora = stTrasportadora.executeQuery(sqlConsulta);
            
            if(rsTransportadora.next()){//se houver Clientes com o codigo informado
                //preenchendo objeto ClientesVO
                
                //pegando conteudo do campo codigo dentro do rsClientes
                //e armazenando no atributo codigo do objeto tmpCLiente
                //através do metodo set
                tmpTransportadora.setCodigo(rsTransportadora.getString("ShipperID"));
                tmpTransportadora.setNomeEmpresa(rsTransportadora.getString("companyName"));
                tmpTransportadora.setTelefone(rsTransportadora.getString("phone"));
                
            }
            
        }catch(Exception erro){
           String msgErro = "Falha na Consulta. Verifique a instrução SELECT";
           
           throw new Exception (msgErro + "\nErro original: " 
                                + erro.getMessage());
        }
        
        /***FECHA CONEXAO***/
        try{
            ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        return tmpTransportadora;
        
    }//fechando consultaCliente
    
    public static boolean alterarTransportadora(TransportadoraVO tmpTransportadora, String tmpCodigo)throws Exception{
         /***ABRE CONEXAO***/
        try{
            ConexaoDAO.abreConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //INSERT - Linhas do Cadastro
            String sqlCadastra = "";
            sqlCadastra += "update SHIPPERS set CompanyName = '"+tmpTransportadora.getNomeEmpresa()+"'";
            sqlCadastra += ", Phone = '"+tmpTransportadora.getTelefone()+"'";
            sqlCadastra += "where shipperid = '"+tmpTransportadora.getCodigo()+"'";
            
            if(consultaTransportadora(1, tmpTransportadora.getCodigo()).getNomeEmpresa() != null){
                ConexaoDAO.abreConexao();
                stTrasportadora = ConexaoDAO.connSistema.createStatement();
                stTrasportadora.executeUpdate(sqlCadastra);
                System.out.println(sqlCadastra);
                ConexaoDAO.fechaConexao();
                return true;
            } else{
                ConexaoDAO.fechaConexao();
                return false;
            }
        }catch(Exception erro){
            throw new Exception (
                    "Falha no Update. "
                            + "Verifique sintaxe da instrução."
                            + "\nErro Original:" 
                                    + erro.getMessage());
        }
    }//fechando alteraCliente
    
    public static void deletarTrasportadora(TransportadoraVO tmpTransportadora) throws Exception{
        /***ABRE CONEXAO***/
        try{
            ConexaoDAO.abreConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //INSERT - Linhas do Cadastro
            String sqlCadastra = "";
            sqlCadastra += "delete from SHIPPERS where shipperid = '"+tmpTransportadora.getCodigo()+"'";
            
                ConexaoDAO.abreConexao();
                stTrasportadora = ConexaoDAO.connSistema.createStatement();
                stTrasportadora.executeUpdate(sqlCadastra);
                System.out.println(sqlCadastra);
                ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception (
                    "Falha no Delete. "
                            + "Verifique sintaxe da instrução."
                            + "\nErro Original:" 
                                    + erro.getMessage());
        }
    }
    
    public static List<TransportadoraVO> listaTransportadoras() throws Exception{
        
        //construção do vetor clientes
        List<TransportadoraVO> vetorClientes = new ArrayList<TransportadoraVO>();
        
        /***ABRE CONEXAO***/
        try{
            ConexaoDAO.abreConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
        //Execução da listagem
        String sqlClientes = "select * from shippers";
        
        //ativando statement para execução do SQL
        stTrasportadora = ConexaoDAO.connSistema.createStatement();
        rsTransportadora = stTrasportadora.executeQuery(sqlClientes);
        
        //enquato houver clientes
        while(rsTransportadora.next()){
            //para cada linha de registro no banco será criado um objeto
            //cliente para ser adicionado posteriormente ao vetor <LIST>
            TransportadoraVO transportadoraAtual = new TransportadoraVO();
            transportadoraAtual.setCodigo(rsTransportadora.getString("ShipperID"));
            transportadoraAtual.setNomeEmpresa(rsTransportadora.getString("companyname"));
            transportadoraAtual.setTelefone(rsTransportadora.getString("phone"));
            
            //adicionando clienteAtual no vetor
            vetorClientes.add(transportadoraAtual);
            
        
        }//fechando while
        
        }catch(Exception erro){
            throw new Exception("Falha na listagem. Verifique os nomes dos campos; \n Erro original: "+ erro.getMessage());
            
        }
        
        /***FECHA CONEXAO***/
        try{
            ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        return vetorClientes;
    }
    
    public static int qtdTransportadora() throws Exception{
            
         TransportadoraVO tmpTransportadora = new TransportadoraVO();
         int resultado, contadora=0;
        
         /***ABRE CONEXAO***/
        try{
            ConexaoDAO.abreConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            
            String sqlConsulta = "";
            sqlConsulta = "SELECT * FROM shippers";
            
            //ativando statement para execução do SQL
            stTrasportadora = ConexaoDAO.connSistema.createStatement();
            
            //executando Select e armazenando resultado no resultSet
            rsTransportadora = stTrasportadora.executeQuery(sqlConsulta);
            
            while(rsTransportadora.next()){
                contadora++;
            }
            resultado = contadora;
        }catch(Exception erro){
           String msgErro = "Falha na Consulta. Verifique a instrução SELECT";
           
           throw new Exception (msgErro + "\nErro original: " 
                                + erro.getMessage());
        }
        
        /***FECHA CONEXAO***/
        try{
            ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        return resultado;
    }
}//fechando classe
