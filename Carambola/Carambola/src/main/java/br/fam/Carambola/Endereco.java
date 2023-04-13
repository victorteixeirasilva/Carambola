package br.fam.Carambola;

import java.sql.SQLException;

import br.fam.Carambola.Db.ConnectionDb;
import br.fam.Carambola.Usuarios.UsuarioEstabelecimento;

public class Endereco {
	private String rua, bairro, referencia;
	private UsuarioEstabelecimento estabelecimento;
	private int cep, numero;
	private static int count = 1;
	private ConnectionDb conn = new ConnectionDb();
	
	public Endereco(String rua, String bairro, String referencia, int cep, int numero, UsuarioEstabelecimento estabelecimento) throws SQLException {
		
		this.estabelecimento = estabelecimento;
		this.rua = rua;
		this.bairro = bairro;
		this.referencia = referencia;
		this.cep = cep;
		this.numero = numero;
		
		conn.insert("INSERT INTO TB_ENDERECO(CAT_IDCAT,CAT_NOMECAT) VALUES ();");
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
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
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
	
	
}
