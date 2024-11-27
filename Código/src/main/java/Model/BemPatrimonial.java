package Model;

public class BemPatrimonial {

	private int codigo;
	private String nome;
	private String descricao;
	private boolean disponivel;
	private boolean tangivel;
	private boolean movel;
	private boolean permanente;
	private boolean fungivel;

    /**
     *
     * @param codigo
     * @param nome
     * @param descricao
     * @param disponivel
     * @param tangivel
     * @param movel
     * @param permanente
     * @param fungivel
     */
    public BemPatrimonial(int codigo, String nome, String descricao, boolean disponivel, 
            boolean tangivel, boolean movel, boolean permanente, boolean fungivel) {
        this.codigo = codigo; 
        this.nome = nome;
        this.descricao = descricao;
        this.disponivel = disponivel;
        this.tangivel = tangivel;
        this.movel = movel;
        this.permanente = permanente;
        this.fungivel = fungivel;
    }

    public BemPatrimonial(String nome, boolean fungivel) {
        this.nome = nome;
        this.fungivel = fungivel;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getDisponibilidade() {
        return disponivel;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponivel = disponibilidade;
    }

    public boolean isTangivel() {
        return tangivel;
    }

    public void setTangivel(boolean tangivel) {
        this.tangivel = tangivel;
    }

    public boolean isMovel() {
        return movel;
    }

    public void setMovel(boolean movel) {
        this.movel = movel;
    }

    public boolean isPermanente() {
        return permanente;
    }

    public void setPermanente(boolean permanente) {
        this.permanente = permanente;
    }

    public boolean isFungivel() {
        return fungivel;
    }

    public void setFungivel(boolean fungivel) {
        this.fungivel = fungivel;
    }
        
        

}
