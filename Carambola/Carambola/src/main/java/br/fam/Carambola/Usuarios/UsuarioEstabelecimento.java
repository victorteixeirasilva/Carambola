package br.fam.Carambola.Usuarios;

import java.sql.SQLException;

import br.fam.Carambola.Endereco;
import br.fam.Carambola.Db.ConnectionDb;

public class UsuarioEstabelecimento  {
	private String nome, email, senha;
	private Endereco endereco;
	private float classificacao;
	private int id;
	private int telefone;
	private long cnpj;
	private Usuario usuario;
	private ConnectionDb conn = new ConnectionDb();
	
	public UsuarioEstabelecimento(Usuario usuario, String nomeEstabelecimento , long cnpj) throws SQLException {
		this.nome = nomeEstabelecimento;
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.cnpj = cnpj;
		this.telefone = usuario.getTelefone();
		this.id = usuario.getId();
		conn.insert("INSERT INTO TB_USUARIOS_ESTABELECIMENTO(USUEST_IDUSUEST, USUEST_IDUSU, USUEST_NOME, USU_CNPJ) VALUES ("+this.usuario.getId()+", "+this.usuario.getId()+", "+this.nome+", "+this.cnpj+");");
		
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

	public long getCnpj() {
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
