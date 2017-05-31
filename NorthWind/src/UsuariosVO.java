public class UsuariosVO {
    private String nomeUsuario, senha;
    private int permissao;
    
    public UsuariosVO(){
        this.setNomeUsuario(null);
        this.setSenha(null);
        this.setPermissao(0);
    }

    public String getNomeUsuario() {
        return this.nomeUsuario;
    }

    public void setNomeUsuario(String tmpNomeUsuario) {
        this.nomeUsuario = tmpNomeUsuario;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String tmpSenha) {
        this.senha = tmpSenha;
    }

    public int getPermissao() {
        return this.permissao;
    }

    public void setPermissao(int tmpPermissao) {
        this.permissao = tmpPermissao;
    }
    
}
