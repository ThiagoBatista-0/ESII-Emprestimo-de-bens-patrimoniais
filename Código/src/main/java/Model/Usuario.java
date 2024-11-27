package Model;

public class Usuario extends Pessoa {

    private int código;

    private String nome;
    
    private String senha; 

    private String descrição;

    private String tipo; // Pode ser "Comum" ou "Administrador"

    public Usuario(String nome, int codigo, String tipo) {
        this.nome = nome;
        this.código = codigo; 
        this.tipo = tipo;
    }
    
    public Usuario(String nome, String senha, String tipo) {
        this.nome = nome;
        this.senha = senha; 
        this.tipo = tipo;
    }
    
    

    @Override
    public int getCódigo() {
        return código;
    }

    @Override
    public void setCódigo(int código) {
        this.código = código;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDescrição() {
        return descrição;
    }

    @Override
    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    

}
