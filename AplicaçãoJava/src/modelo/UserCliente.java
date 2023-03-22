package modelo;

import java.util.Objects;

public class UserCliente {
	 
	private Integer id;
	private Integer telefone;
	private String email;
	private String nome;
	
	public UserCliente() {
		
	}

	public UserCliente(Integer id, Integer telefone, String email, String nome) {
		this.id = id;
		this.telefone = telefone;
		this.email = email;
		this.nome = nome;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserCliente other = (UserCliente) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
	
