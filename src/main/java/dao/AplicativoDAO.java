package dao;

import java.sql.*;

import model.Aplicativo;

public class AplicativoDAO {
private Connection conexao;
	
	public AplicativoDAO() {
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

//DAO Aplicativo 
	public boolean inserirAplicativo(Aplicativo aplicativo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO mydb.aplicativo (\"nome\", \"descricao\", \"fonte\", \"tutorial\", \"imagem\", \"nota\", \"dispositivo\", \"pago\", \"dicas\", \"categoria\") "
					       + "VALUES ('" + aplicativo.getNome() + "', '"
					       + aplicativo.getDescricao() + "', '" + aplicativo.getFonte() + "', '" + aplicativo.getTutorial() + "', '" + aplicativo.getImagem() + "', '" + aplicativo.getNota() + "', '" + aplicativo.getDispositivo() + "', '" + aplicativo.getPago() + "', '" + aplicativo.getDicas() + "', '" + aplicativo.getCategoria() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarAplicativo(Aplicativo aplicativo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE mydb.aplicativo SET \"nome\" = '" + aplicativo.getNome() + "', \"descricao\" = '"
				       + aplicativo.getDescricao() + "', \"fonte\" = '"
				       + aplicativo.getFonte() + "', \"tutorial\" = '"
				       + aplicativo.getTutorial() + "', \"imagem\" = '"
				       + aplicativo.getImagem() + "', \"nota\" = '"
				       + aplicativo.getNota() + "', \"dispositivo\" = '"
				       + aplicativo.getDispositivo() + "', \"pago\" = '"
				       + aplicativo.getPago() + "', \"dicas\" = '"
					   + aplicativo.getDicas() + "', \"categoria\" = '"
				       + aplicativo.getCategoria() + "' WHERE \"id\" = " + aplicativo.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirAplicativo(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM mydb.aplicativo WHERE \"id\" = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Aplicativo[] getAplicativo() {
		Aplicativo[] aplicativo = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM mydb.aplicativo");
	         if(rs.next()){
	             rs.last();
	             aplicativo = new Aplicativo[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                aplicativo[i] = new Aplicativo(rs.getInt("id"),rs.getString("nome"), rs.getString("descricao"), rs.getString("fonte"), rs.getString("tutorial"), rs.getString("imagem"), rs.getFloat("nota"), rs.getString("dispositivo"), rs.getBoolean("pago"), rs.getString("dicas"), rs.getString("categoria"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return aplicativo;
	}

	public Aplicativo lerAplicativo(int id) {
		Aplicativo u = new Aplicativo();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM mydb.aplicativo WHERE \"id\" = " + id);
			rs.next();
			u = new Aplicativo(rs.getInt("id"),rs.getString("nome"),rs.getString("descricao"),rs.getString("fonte"),rs.getString("tutorial"),rs.getString("imagem"),rs.getFloat("nota"),rs.getString("dispositivo"),rs.getBoolean("pago"),rs.getString("dicas"), rs.getString("categoria"));
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return u;
	}


	
}