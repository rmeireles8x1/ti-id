package dao;

import java.sql.*;



import model.Comentario;

public class ComentarioDAO {
private Connection conexao;
	
	public ComentarioDAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "mydb";
		int porta = 5432;
		String comentarioname = "postgres";
		String password = "ti@cc";
		String url = "jdbc:postgresql://"+serverName+":"+porta+"/"+mydatabase; 
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, comentarioname, password);
			status = (conexao != null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

//DAO USUARIO 
	public boolean inserirComentario(Comentario comentario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO mydb.comentario (\"texto\", \"data\", \"aplicativo_id\", \"user_id\") "
					       + "VALUES ('" + comentario.getTexto() + "', '"
					       + comentario.getData() + "', '" + comentario.getAplicativoId() + "', '"+ comentario.getUserId() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarComentario(Comentario comentario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE mydb.comentario SET \"texto\" = '" + comentario.getTexto() + "', \"data\" = '" + comentario.getData() + "'"
					   + " WHERE \"id\" = " + comentario.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirComentario(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM mydb.comentario WHERE \"id\" = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Comentario[] getComentarios() {
		Comentario[] comentarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM mydb.comentario");
	         if(rs.next()){
	             rs.last();
	             comentarios = new Comentario[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                comentarios[i] = new Comentario(rs.getInt("id"),rs.getString("texto"), rs.getDate("data"), rs.getInt("aplicativo_id"), rs.getInt("user_id"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return comentarios;
	}

	public Comentario lerComentario(int id) {
		Comentario c = new Comentario();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM mydb.comentario WHERE \"id\" = " + id);
			rs.next();
			c = new Comentario(rs.getInt("id"),rs.getString("texto"), rs.getDate("data"), rs.getInt("aplicativo_id"), rs.getInt("user_id"));
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return c;
	}
	
}

