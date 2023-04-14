package br.fam.Carambola.Usuarios;

import java.sql.SQLException;

import br.fam.Carambola.Endereco;
import br.fam.Carambola.FormaDePagamento;
import br.fam.Carambola.Db.ConnectionDb;

public class UsuarioCliente{
	private Usuario usuario;
	private String nome, email, senha, dataNascimento;
	private long cpf;
	private int telefone;
	private int id;
	private Endereco endereco;
	private FormaDePagamento formaDePagamento;
	private ConnectionDb conn = new ConnectionDb();
	
	
	public UsuarioCliente() {
		
	}
	
	public UsuarioCliente(Usuario usuario,String nome, long cpf, String dataNascimento) throws SQLException {
		this.nome = nome;
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.id = usuario.getId();
		
		conn.insert("INSERT INTO TB_USUARIOS_CLIENTE(USUCLI_IDUSUCLI, USUCLI_IDUSU, USUCLI_NOME, USU_DATANASC, USU_CPF) VALUES ("+this.id+", "+this.id+", '"+this.nome+"', '"+this.dataNascimento+"', "+this.cpf+");");
		
	}
	
	public void verInformacoesDaConta() {
		
	}

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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
