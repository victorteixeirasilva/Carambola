package br.fam.Carambola;

import br.fam.Carambola.Db.ConnectionDb;
import br.fam.Carambola.Usuarios.UsuarioCliente;
import br.fam.Carambola.Usuarios.UsuarioEstabelecimento;

public class ItensPedido {
	private int quantidade;
	private int id;
	private Double valor;
	private static int count = 1;
	
	private Produto produto;
	private UsuarioCliente cliente;
	private UsuarioEstabelecimento estabelecimento;
	private ConnectionDb conn = new ConnectionDb();
	
	
	public ItensPedido() {
		
	}
	
	public ItensPedido(int quantidade, Produto produto, Double valor, UsuarioCliente cliente,
			UsuarioEstabelecimento estabelecimento, int id) {
		
		this.quantidade = quantidade;
		this.produto = produto;
		this.valor = valor;
		this.cliente = cliente;
		this.estabelecimento = estabelecimento;
		this.id = count;
		
		//conn.???.("?????????");
	}
	
	public Produto qntdDeIntensPedido(Produto produto) {
		produto.getId();
		return produto;
		
	}
	
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
