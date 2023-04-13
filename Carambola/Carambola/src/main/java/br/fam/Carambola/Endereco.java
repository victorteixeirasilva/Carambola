package br.fam.Carambola;

import java.sql.SQLException;

import br.fam.Carambola.Db.ConnectionDb;
import br.fam.Carambola.Usuarios.UsuarioCliente;
import br.fam.Carambola.Usuarios.UsuarioEstabelecimento;

public class Endereco {
	private String rua, bairro, cep, referencia;
	private UsuarioEstabelecimento estabelecimento;
	private UsuarioCliente usuarioCliente;
	private int numero;
	private static int count = 1;
	private ConnectionDb conn = new ConnectionDb();
	
	public Endereco(String rua, String bairro, String referencia, String cep, int numero, UsuarioEstabelecimento estabelecimento) throws SQLException {
		
		this.setEstabelecimento(estabelecimento);
		this.rua = rua;
		this.bairro = bairro;
		this.referencia = referencia;
		this.cep = cep;
		this.numero = numero;
		
		conn.insert("INSERT INTO TB_ENDERECO(END_IDEND, END_IDUSU, END_RUA, END_BAIRRO, END_REFERENCIA, END_CEP, END_NUMERO) VALUES (NEXT VALUE FOR SQ_END_IDEND, '"+this.estabelecimento.getId()+"', '"+this.rua+"', '"+this.bairro+"', '"+this.referencia+"', '"+this.cep+"', '"+this.numero+"');");
		Endereco.setCount(Endereco.getCount() + 1);
	}
	
	public Endereco(String rua, String bairro, String referencia, String cep, int numero, UsuarioCliente usuarioCliente) throws SQLException {
		
		this.usuarioCliente = usuarioCliente;
		this.rua = rua;
		this.bairro = bairro;
		this.referencia = referencia;
		this.cep = cep;
		this.numero = numero;
		
		conn.insert("INSERT INTO TB_ENDERECO(END_IDEND, END_IDUSU, END_RUA, END_BAIRRO, END_REFERENCIA, END_CEP, END_NUMERO) VALUES (NEXT VALUE FOR SQ_END_IDEND, '"+this.usuarioCliente.getId()+"', '"+this.rua+"', '"+this.bairro+"', '"+this.referencia+"', '"+this.cep+"', '"+this.numero+"');");
		Endereco.setCount(Endereco.getCount() + 1);
	}
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Endereco.count = count;
	}

	public UsuarioEstabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(UsuarioEstabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
	
}
