package br.fam.Carambola.Usuarios;

import br.fam.Carambola.Endereco;

public class UsuarioEstabelecimento implements Usuario {
	private String nome, email, senha;
	private Endereco endereco;
	private float classificacao;
	private int cnpj, telefone, id;//O Atributo Id, está fora do Diagrama inicial 
	
	public void verificarMesas() {
		
	}
	
	public void cadastrarProduto() {
		
	}
	
	public void editarProdutos() {
		
	}
	
	public void verProdutos() {
		
	}
	
	public void excluirProdutos() {
		
	}
	
	public void cadastrarFuncionario() {
		
	}
	
	public void excluirFuncionarios() {
		
	}
	
	public void verFuncionarios() {
		
	}
	
	public void editarFuncionarios() {
		
	}
	
	public void simularCompra() {
		
	}
	
	public void impulsionarEstabelecimento() {
		
	}
	
	public void verificarFaturamento() {
		
	}
	
	public void atualizarEstoque() {
		
	}
	
	public void verCatalogo() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public float getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(float classificacao) {
		this.classificacao = classificacao;
	}

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
