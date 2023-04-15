package br.fam.Carambola.Usuarios;

import java.sql.SQLException;

import br.fam.Carambola.Db.ConnectionDb;

public class Usuario {
	private int id;
	private long telefone;
	private String email;
	private String senha;
	private static int count = 1000;
	private ConnectionDb conn = new ConnectionDb();
	
	
	public Usuario(long telefone, String email, String senha) throws SQLException {
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		conn.insert("INSERT INTO TB_USUARIOS(USU_IDUSU,USU_TEL,USU_EMAIL,USU_SENHA) VALUES (NEXT VALUE FOR SQ_USU_IDUSU,"+this.telefone+",'"+this.email+"','"+this.senha+"');");
		id = conn.buscarIdUsuario(email, senha);
	}

	public void identificarNoSistema() {
		
	}

	public long getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public  String getSenha() {
		return senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Usuario.count = count;
	}	
	
}
