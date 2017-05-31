import java.sql.*;
import java.util.*;//pacote da arraylist

public class ClientesDAO {
    
    //Classe responsavel pela execução dos comandos SQL
    public static Statement stClientes;
    //Classe responsavel pelo armazenamento de uma consulta SELECT
    public static ResultSet rsClientes;
    
    public static boolean cadastraCliente(ClientesVO tmpCliente) throws Exception{
     
        /***ABRECONEXAO***/
        try{
            ConexaoDAO.abreConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //INSERT - Linhas do Cadastro
            String sqlCadastra="";
            
            sqlCadastra += "Insert into CUSTOMERS(";
            sqlCadastra += "customerID, companyName,";
            sqlCadastra += "contactName,contactTitle,";
            sqlCadastra += "Address, City,";
            sqlCadastra += "Region, postalCode, country,";
            sqlCadastra += "Phone, Fax, status)values(";
            sqlCadastra += "'" + tmpCliente.getCodigo() + "',";
            sqlCadastra += "'" + tmpCliente.getNomeEmpresa() + "',";
            sqlCadastra += "'" + tmpCliente.getNomeRepresentante()+ "',";
            sqlCadastra += "'" + tmpCliente.getCargoRepresentante()+ "',";
            sqlCadastra += "'" + tmpCliente.getEndereco()+ "',";
            sqlCadastra += "'" + tmpCliente.getCidade()+ "',";
            sqlCadastra += "'" + tmpCliente.getEstado()+ "',";
            sqlCadastra += "'" + tmpCliente.getCep()+ "',";
            sqlCadastra += "'" + tmpCliente.getPais()+ "',";
            sqlCadastra += "'" + tmpCliente.getTelefone()+ "',";
            sqlCadastra += "'" + tmpCliente.getFax()+ "',1)";
            
            
            //verificando se codigo ja existe
            if(consultaCliente(1, tmpCliente.getCodigo()).getNomeEmpresa() == null){
                //executando instrução INSERT
                //metodo executeUpdate é usado para INSERT, UPDATE e DELETE
                //Não retorna valor, portanto nao é igualado a nenhuma var.
                ConexaoDAO.abreConexao();
                //ativando statement para execução do SQL
                stClientes = ConexaoDAO.connSistema.createStatement();
                stClientes.executeUpdate(sqlCadastra);            
                System.out.println(sqlCadastra);
                
                ConexaoDAO.fechaConexao();                
                return true;
            
            }else{
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
    
    public static ClientesVO consultaCliente(int tmpTipo, String tmpBusca) throws Exception{
     
         ClientesVO tmpCliente = new ClientesVO();
        
         /***ABRECONEXAO***/
        try{
            ConexaoDAO.abreConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            
            String sqlConsulta = "";
            
            //SELECT - Execução da Consulta
            if(tmpTipo == 1){ //Busca por codigo
                sqlConsulta = "SELECT * FROM customers where customerid like '" + tmpBusca + "'";
             }else if(tmpTipo == 2){ // busca por nome
                sqlConsulta = "SELECT * FROM customers where companyname like '%" + tmpBusca + "%'";
             }
            
            //ativando statement para execução do SQL
            stClientes = ConexaoDAO.connSistema.createStatement();
            
            //executando Select e armazenando resultado no resultSet
            rsClientes = stClientes.executeQuery(sqlConsulta);
            
            if(rsClientes.next()){//se houver Clientes com o codigo informado
                //preenchendo objeto ClientesVO
                
                //pegando conteudo do campo codigo dentro do rsClientes
                //e armazenando no atributo codigo do objeto tmpCLiente
                //através do metodo set
                tmpCliente.setCodigo(rsClientes.getString("customerId"));
                tmpCliente.setNomeEmpresa(rsClientes.getString("companyName"));
                tmpCliente.setNomeRepresentante(rsClientes.getString("contactName"));
                tmpCliente.setCargoRepresentante(rsClientes.getString("contactTitle"));
                tmpCliente.setEndereco(rsClientes.getString("Address"));
                tmpCliente.setCidade(rsClientes.getString("City"));
                tmpCliente.setEstado(rsClientes.getString("Region"));
                tmpCliente.setCep(rsClientes.getString("postalCode"));
                tmpCliente.setPais(rsClientes.getString("country"));
                tmpCliente.setTelefone(rsClientes.getString("phone"));
                tmpCliente.setFax(rsClientes.getString("fax"));
                tmpCliente.setStatus(rsClientes.getInt("status"));
                
            }
            
        }catch(Exception erro){
           String msgErro = "Falha na Consulta. Verifique a instrução SELECT";
           
           throw new Exception (msgErro + "\nErro original: " 
                                + erro.getMessage());
        }
        
        /***FECHACONEXAO***/
        try{
            ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        return tmpCliente;
        
    }//fechando consultaCliente
    
    public static void alteraCliente(ClientesVO tmpCliente, String tmpCodigo)throws Exception{
         /***ABRECONEXAO***/
        try{
            ConexaoDAO.abreConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //Update - Execução da alteração
        }catch(Exception erro){
            
        }
        
        /***FECHACONEXAO***/
        try{
            ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
    }//fechando alteraCliente
    
    public static List<ClientesVO> listaClientes() throws Exception{
    
        //construção do vetor clientes que será retornado ao fim do metodo
        List<ClientesVO> vetorClientes = new ArrayList<ClientesVO>();
        
        /***ABRECONEXAO***/
        try{
            ConexaoDAO.abreConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //Execução da listagem
            String sqlClientes = "Select * from Customers";
            
            //ativando statement para execução do comando SQL
            stClientes = ConexaoDAO.connSistema.createStatement();
            
            rsClientes = stClientes.executeQuery(sqlClientes);
            
           //Enquanto houver clientes
            while(rsClientes.next()){
                //para cada linha de cregistro no bd
                //será criado um objeto cliente para ser
                //adicionado posteriormente ao vetor <LIST>            
                ClientesVO clienteAtual = new ClientesVO();
                
                //Preenchendo objcliente com o registro atual (ponteiro)
                clienteAtual.setCodigo(rsClientes.getString("customerID"));
                clienteAtual.setNomeEmpresa(rsClientes.getString("companyName"));
                clienteAtual.setCidade(rsClientes.getString("city"));
                clienteAtual.setTelefone(rsClientes.getString("phone"));
            
                //adicionado clienteAtual ao vetor <List>
                vetorClientes.add(clienteAtual);
                
            }//fechando while
            
        }catch(Exception erro){
            throw new Exception("Falha na listagem. Verifique o nome dos campos.\n Erro Original: " + erro.getMessage());
        }
        
        /***FECHACONEXAO***/
        try{
            ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        return vetorClientes;
        
    }//fechando listaClientes
    
    public static void habilitarClientes(String tmpCodigo, int tmpStatus) throws Exception{
        /***ABRECONEXAO***/
        try{
            ConexaoDAO.abreConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
    
        try {
            
            String sqlStatus = "";
            
            sqlStatus = "update customers set status = " + tmpStatus + " where customerid like '" + tmpCodigo +"'";
            stClientes = ConexaoDAO.connSistema.createStatement();
            stClientes.executeUpdate(sqlStatus);

        } catch (Exception erro) {
            throw new Exception("Falha ao alterar status\nErro original:" + erro.getMessage());
        }
        
        
        /***FECHACONEXAO***/
        try{
            ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
    }
}//fechando classe
