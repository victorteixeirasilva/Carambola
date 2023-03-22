package modelo;

import java.util.Objects;

public class Estabelecimento {

	
	private Integer id;
	private Integer telefone;
	private String nome;
	private String email;
	private String endereco;
	
	Estabelecimento(){
		
	}


	public Estabelecimento(Integer id, Integer telefone, String name, String email, String endereco) {
		this.id = id;
		this.telefone = telefone;
		this.name = name;
		this.email = email;
		this.endereco = endereco;
	}


	public Integer getTelefone() {
		return telefone;
	}


	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
		Estabelecimento other = (Estabelecimento) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
