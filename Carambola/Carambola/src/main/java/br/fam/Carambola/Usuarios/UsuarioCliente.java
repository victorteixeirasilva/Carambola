package br.fam.Carambola.Usuarios;

import br.fam.Carambola.Endereco;
import br.fam.Carambola.FormaDePagamento;

public class UsuarioCliente implements Usuario {
	private String nome, email, senha;
	private int telefone;
	private Endereco endereco;
	private FormaDePagamento formaDePagamento;
	
	public void reservarMesa() {
		
	}
	
	public void buscarEstabelecimento() {
		
	}
	
	public void escolherEstabelecimento() {
		
	}
	
	public void comprarProdutos() {
		
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

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
	
	
	
}
