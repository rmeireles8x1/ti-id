package model;

public class Aplicativo {
	private int id;
	private String nome;
	private String descricao;
	private String fonte;
    private String tutorial;
    private String imagem;
    private float nota;
    private String dispositivo;
    private boolean pago;
    private String dicas;

	private String categoria;
	
	public Aplicativo() {
		this.id = -1;
		this.nome = "";
		this.descricao = "";
		this.fonte = "";
        this.tutorial = "";
        this.imagem = "";
        this.nota = 0;
        this.dispositivo = "";
        this.pago = false;
        this.dicas = "";
		this.categoria = "";
	}
	
	public Aplicativo(String nome, String descricao, String fonte, String tutorial, String imagem, float nota, String dispositivo, boolean pago, String dicas, String categoria) {
		this.id = -1;
		this.nome = nome;
		this.descricao = descricao;
		this.fonte = fonte;
        this.tutorial = tutorial;
        this.imagem = imagem;
        this.nota = nota;
        this.dispositivo = dispositivo;
        this.pago = pago;
        this.dicas = dicas;
		this.categoria = categoria;
	}

    public Aplicativo(int id, String nome, String descricao, String fonte, String tutorial, String imagem, float nota, String dispositivo, boolean pago, String dicas, String categoria) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.fonte = fonte;
        this.tutorial = tutorial;
        this.imagem = imagem;
        this.nota = nota;
        this.dispositivo = dispositivo;
        this.pago = pago;
        this.dicas = dicas;
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
//-------------------------------------
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
//------------------------------------
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
//-------------------------------------
	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}
//--------------------------------------
    public String getTutorial() {
		return tutorial;
	}

	public void setTutorial(String tutorial) {
		this.tutorial = tutorial;
	}
//-------------------------------------
    public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
//--------------------------------------
    public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}
//-------------------------------------
    public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}
//--------------------------------------
    public boolean getPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
//--------------------------------------
    public String getDicas() {
		return dicas;
	}

	public void setDicas(String dicas) {
		this.dicas = dicas;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	//---------------------------------------
	public String toString() {
		return "Aplicativo [id=" + id + ", nome=" + nome + ", descrição=" + descricao + ", fonte=" + fonte + ", tutorial=" + tutorial + ", imagem=" + imagem + ", nota=" + nota + ", dispositivo=" + dispositivo + ", pago=" + pago + ", dicas=" + dicas + ", categoria =" + categoria+"]";
	}

	public String toJSON() {
		return     " {\r\n" +
				"    \"id\": \""+ id + "\",\r\n" +
				"    \"nome\": \""+ nome +"\",\r\n" +
				"    \"descricao\": \""+ descricao +"\",\r\n" +
				"    \"fonte\": \""+ fonte +"\",\r\n" +
				"     \"tutorial\": \""+ tutorial +"\",\r\n" +
				"      \"imagem\": \""+ imagem +"\",\r\n" +
				"       \"nota\": \""+ nota +"\",\r\n" +
				"         \"dispositivo\": \""+ dispositivo +"\",\r\n" +
				"         \"pago\": \""+ pago +"\",\r\n" +
				"         \"categoria\": \""+ categoria +"\",\r\n" +
				"         \"dicas\": \""+ dicas +"\"\r\n" +
				"  }";
	}

}

