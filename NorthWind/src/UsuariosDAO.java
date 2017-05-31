import java.sql.*;

public class UsuariosDAO {
    
    public static Statement stUsuarios;
    public static ResultSet rsUsuarios;
    
    public static UsuariosVO validaUsuario(String tmpNome, String tmpSenha) throws Exception{
        
        UsuariosVO tmpUsuarios = new UsuariosVO();
        
        try {
            ConexaoDAO.abreConexao();
            
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
        
        try {
            String sqlLogin = "SELECT * FROM USERS WHERE USERNAME LIKE '"+tmpNome+"' "
                              + "AND PASSWORD LIKE '"+tmpSenha+"'";
            
            stUsuarios = ConexaoDAO.connSistema.createStatement();
            rsUsuarios = stUsuarios.executeQuery(sqlLogin);
            
            if(rsUsuarios.next()){
                //Preenchendo objeto
                tmpUsuarios.setNomeUsuario(rsUsuarios.getString("userName"));
                tmpUsuarios.setSenha(rsUsuarios.getString("password"));
                tmpUsuarios.setPermissao(rsUsuarios.getInt("permission"));
            }
            
        } catch (Exception erro) {
            throw new Exception("Usuário não encontrado/nErro original: "+erro.getMessage());
        }
        
        try {
            ConexaoDAO.fechaConexao();
            
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
        
        return tmpUsuarios;
    }
}

/*
    Trabalho para entregar:
    6 grupos para desenvolver os 6 módulos que faltam no sistema
*/
