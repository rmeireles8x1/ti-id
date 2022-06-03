package model;


import java.sql.Date;

public class Comentario {
	private int id;
	private String texto;
	private Date data;
	private int aplicativo_id;
	private int user_id;
	
	public Comentario() {
		this.id = -1;
		this.texto = "";
		this.data = new Date(1990,1,1);
		this.aplicativo_id = -1;
		this.user_id = -1;
	}
	
	public Comentario(String texto) {
		this.id = -1;
		this.texto = texto;
		this.data = new Date(1990,1,1);
		this.aplicativo_id = -1;
		this.user_id = -1;
	}

	public Comentario(String texto, Date data) {
		this.id = -1;
		this.texto = texto;
		this.data = data;
		this.aplicativo_id = -1;
		this.user_id = -1;
	}

	public Comentario(int id, String texto, Date data) {
		this.id = id;
		this.texto = texto;
		this.data = data;
		this.aplicativo_id = -1;
		this.user_id = -1;
	}

	public Comentario(int id, String texto, Date data, int aplicativo_id) {
		this.id = id;
		this.texto = texto;
		this.data = data;
		this.aplicativo_id = aplicativo_id;
		this.user_id = -1;
	}

	public Comentario(int id, String texto, Date data, int aplicativo_id, int user_id) {
		this.id = id;
		this.texto = texto;
		this.data = data;
		this.aplicativo_id = aplicativo_id;
		this.user_id = user_id;
	}

	public Comentario(String texto, Date data, int aplicativo_id, int user_id) {
		this.id = -1;
		this.texto = texto;
		this.data = data;
		this.aplicativo_id = aplicativo_id;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getAplicativoId() {
		return aplicativo_id;
	}

	public void setAplicativoId(int aplicativo_id) {
		this.aplicativo_id = aplicativo_id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public String toString() {
		return "Comentario [id=" + id + ", texto=" + texto + ", data=" + data + ", aplicativo_id=" + aplicativo_id + ", user_id=" + user_id + "]";
	}

	public String toJSON() {
		return     " {\r\n" +
				"    \"id\": \""+ id + "\",\r\n" +
				"    \"texto\": \""+ texto +"\",\r\n" +
				"    \"data\": \""+ data +"\",\r\n" +
				"    \"aplicativo_id\": \""+ aplicativo_id +"\",\r\n" +
				"     \"user_id\": \""+ user_id +"\"\r\n" +
				"  }";
	}


}
