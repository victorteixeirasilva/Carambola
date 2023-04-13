package br.fam.Carambola.Usuarios;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.fam.Carambola.Categoria;
import br.fam.Carambola.Endereco;

public class UsuarioEstabelecimento  {
	private Usuario usuario = new Usuario();
	private String nome, email, senha;
	private Endereco endereco;
	private float classificacao;
	private int cnpj, telefone, id;
	
	public UsuarioEstabelecimento(Usuario usuario, Endereco endereco, float classificacao, int cnpj, int id) {
		super();
		this.setUsuario(usuario);
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.endereco = endereco;
		this.classificacao = classificacao;
		this.cnpj = cnpj;
		this.telefone = usuario.getTelefone();
		this.id = id;
	}
	
	public void editarEndereco() throws SQLException {
	    String input = JOptionPane.showInputDialog("Informe o novo nome da rua:");
		Endereco endereco = new Endereco( );
		
		JOptionPane.showMessageDialog(null, "A categoria "+categoria.getNome()+" foi cadastrada corretamente!");
	}

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
