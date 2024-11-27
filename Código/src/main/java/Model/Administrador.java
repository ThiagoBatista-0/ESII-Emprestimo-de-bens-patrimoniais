package Model;

public class Administrador extends Pessoa {

	private int código;

	private String nome;

	private String descrição;

    public Administrador() {
    }

    public Administrador(int código, String nome, String descrição) {
        this.código = código;
        this.nome = nome;
        this.descrição = descrição;
    }

    public int getCódigo() {
        return código;
    }

    public void setCódigo(int código) {
        this.código = código;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }
    
    

}
