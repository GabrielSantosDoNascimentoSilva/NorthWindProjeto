
public class SistemaControl {

    public static void main(String[] args) {
         //td metodo com throws exception deve ser chamado num try-catch
        
         try{
            //testando abert. e fecham. da conexao
            ConexaoDAO.abreConexao();
            ConexaoDAO.fechaConexao();
            
        }catch(Exception erro){
            System.out.println(erro.getMessage());
        }

        //Construção da Tela de 'Load'
        LoadView objTela = new LoadView();
    }
    
}
