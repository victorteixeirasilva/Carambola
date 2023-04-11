package br.fam.Carambola.Usuarios;

public class Usuario {
	private String nome;
	private int telefone, id;
	private String email;
	private String senha;
	private static int count = 1000;
	
	
	
	public Usuario() {
		super();
		this.nome = null;
		this.telefone = 0;
		this.email = null;
		this.senha = null;
		Usuario.setCount(Usuario.getCount() + 1);
	}

	public void identificarNoSistema() {
		
	}

	public String getNome() {
		return nome;
	}

	public int getTelefone() {
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
