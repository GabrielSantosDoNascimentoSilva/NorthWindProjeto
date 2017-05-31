
public class TransportadoraVO {
       
    //declaração dos atributos
    private String codigo, nomeEmpresa,telefone;
    
    //métodos construtores
    public TransportadoraVO(String tmpCodigo, String tmpNomeEmpresa, String tmpNomeRepresentante, String tmpCargoRepresentante, String tmpEndereco, String tmpCidade, String tmpEstado, String tmpPais, String tmpCep, String tmpTelefone, String tmpFax, int tmpStatus){
        //chamada dos metodos set
        this.setCodigo(tmpCodigo);
        this.setNomeEmpresa(tmpNomeEmpresa);
        this.setTelefone(tmpTelefone);
        
    }//fechando construtor 1
    
    public TransportadoraVO(){
        //Chamada dos métodos set
        this.setCodigo(null);
        this.setNomeEmpresa(null);
        this.setTelefone(null);
        
    }//fechando construtor
    
    //métodos set e get
    
    public String getCodigo(){
        return this.codigo;
    }
    
    public void setCodigo(String tmpCodigo){
        this.codigo = tmpCodigo;
    }
    
    public String getNomeEmpresa() {
        return this.nomeEmpresa;
    }

    public void setNomeEmpresa(String tmpNomeEmpresa) {
        this.nomeEmpresa = tmpNomeEmpresa;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String tmpTelefone) {
        this.telefone = tmpTelefone;
    }

}//fechando classe
