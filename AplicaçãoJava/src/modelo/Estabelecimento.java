package modelo;

import java.util.Objects;

public class Estabelecimento {
	private Long id;
	private String name;
	private String telefone;
	private String endereco;
	
	public Estabelecimento() {
	}

	public Estabelecimento(Long id, String name, String telefone, String endereco) {
		this.id = id;
		this.name = name;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
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
