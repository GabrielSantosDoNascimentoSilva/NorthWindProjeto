
public class ClientesVO {
       
    //declaração dos atributos
    private String codigo, nomeEmpresa;
    private String nomeRepresentante, cargoRepresentante;
    private String endereco, cidade,  estado, pais, cep;
    private String telefone, fax;
    private int status;
    
    //métodos construtores
    public ClientesVO(String tmpCodigo, String tmpNomeEmpresa, String tmpNomeRepresentante, String tmpCargoRepresentante, String tmpEndereco, String tmpCidade, String tmpEstado, String tmpPais, String tmpCep, String tmpTelefone, String tmpFax, int tmpStatus){
        //chamada dos metodos set
        this.setCodigo(tmpCodigo);
        this.setNomeEmpresa(tmpNomeEmpresa);
        this.setNomeRepresentante(tmpNomeRepresentante);
        this.setCargoRepresentante(tmpCargoRepresentante);
        this.setEndereco(tmpEndereco);
        this.setCidade(tmpCidade);
        this.setEstado(tmpEstado);
        this.setCep(tmpCep);
        this.setPais(tmpPais);
        this.setTelefone(tmpTelefone);
        this.setFax(tmpFax);
        this.setStatus(tmpStatus);        
        
    }//fechando construtor 1
    
    public ClientesVO(){
        //Chamada dos métodos set
        this.setCodigo(null);
        this.setNomeEmpresa(null);
        this.setNomeRepresentante(null);
        this.setCargoRepresentante(null);
        this.setEndereco(null);
        this.setCidade(null);
        this.setEstado(null);
        this.setCep(null);
        this.setPais(null);
        this.setTelefone(null);
        this.setFax(null);
        this.setStatus(0);
        
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

    public String getNomeRepresentante() {
        return this.nomeRepresentante;
    }

    public void setNomeRepresentante(String tmpNomeRepresentante) {
        this.nomeRepresentante = tmpNomeRepresentante;
    }

    public String getCargoRepresentante() {
        return this.cargoRepresentante;
    }

    public void setCargoRepresentante(String tmpCargoRepresentante) {
        this.cargoRepresentante = tmpCargoRepresentante;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String tmpEndereco) {
        this.endereco = tmpEndereco;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String tmpCidade) {
        this.cidade = tmpCidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String tmpEstado) {
        this.estado = tmpEstado;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String tmpPais) {
        this.pais = tmpPais;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String tmpCep) {
        this.cep = tmpCep;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String tmpTelefone) {
        this.telefone = tmpTelefone;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String tmpFax) {
        this.fax = tmpFax;
    }
    
    public int getStatus(){
        return this.status;
    }
    
    public void setStatus(int tmpStatus){
        this.status = tmpStatus;
    }
    
    
    
}//fechando classe
