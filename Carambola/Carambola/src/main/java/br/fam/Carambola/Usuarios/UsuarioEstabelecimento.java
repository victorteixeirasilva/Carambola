package br.fam.Carambola.Usuarios;

import java.sql.SQLException;

import br.fam.Carambola.Endereco;
import br.fam.Carambola.Db.ConnectionDb;

public class UsuarioEstabelecimento extends Usuario {
	private String nome;
	private Endereco endereco;
	private float classificacao;
	private long cnpj;
	private Usuario usuario;
	private ConnectionDb conn = new ConnectionDb();
	
	public UsuarioEstabelecimento(Usuario usuario, String nomeEstabelecimento , long cnpj) throws SQLException {
		super();
		this.usuario = usuario;
		this.nome = nomeEstabelecimento;
		this.cnpj = cnpj;
		conn.insert("INSERT INTO TB_USUARIOS_ESTABELECIMENTO(USUEST_IDUSUEST, USUEST_IDUSU, USUEST_NOME, USU_CNPJ) VALUES ("+getId()+", "+getId()+", '"+this.nome+"', "+this.cnpj+");");
		
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


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
}
