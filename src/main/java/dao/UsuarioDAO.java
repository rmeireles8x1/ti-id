package dao;

import java.sql.*;

import model.Usuario;

public class UsuarioDAO {
private Connection conexao;
	
	public UsuarioDAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "mydb";
		int porta = 5432;
		String username = "postgres";
		String password = "ti@cc";
		String url = "jdbc:postgresql://"+serverName+":"+porta+"/"+mydatabase; 
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
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
	public boolean inserirUsuario(Usuario usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO mydb.user (\"nome\", \"senha\", \"email\") "
					       + "VALUES ('" + usuario.getNome() + "', '"
					       + usuario.getSenha() + "', '" + usuario.getEmail() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarUsuario(Usuario usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE mydb.user SET \"nome\" = '" + usuario.getNome() + "', \"senha\" = '"
				       + usuario.getSenha() + "', \"email\" = '" + usuario.getEmail() + "'"
					   + " WHERE \"id\" = " + usuario.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirUsuario(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM mydb.user WHERE \"id\" = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Usuario[] getUsuarios() {
		Usuario[] usuarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM mydb.user");
	         if(rs.next()){
	             rs.last();
	             usuarios = new Usuario[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                usuarios[i] = new Usuario(rs.getInt("id"),rs.getString("nome"), rs.getString("senha"), rs.getString("email"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}

	public Usuario lerUsuario(int id) {
		Usuario u = new Usuario();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM mydb.user WHERE \"id\" = " + id);
			rs.next();
			u = new Usuario(rs.getInt("id"),rs.getString("nome"),rs.getString("senha"),rs.getString("email"));
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return u;
	}

	public Usuario lerUsuario(String s, String em) {
		Usuario u = new Usuario();
		try {
			PreparedStatement st = conexao.prepareStatement("SELECT * FROM mydb.user WHERE email = ? AND senha = ?");
			st.setString(1, em);
			st.setString(2, s);
			ResultSet rs = st.executeQuery();
			rs.next();
			u = new Usuario(rs.getInt("id"),rs.getString("nome"),rs.getString("senha"),rs.getString("email"));
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return u;
	}
	
	
	
}

