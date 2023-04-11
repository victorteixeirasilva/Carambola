package br.fam.Carambola;

import br.fam.Carambola.Usuarios.UsuarioEstabelecimento;

public class MesaComanda {
	private Pedido pedidos;
	private int numeroMesa;
	private UsuarioEstabelecimento estabelecimento;
	public Pedido getPedidos() {
		return pedidos;
	}
	public void setPedidos(Pedido pedidos) {
		this.pedidos = pedidos;
	}
	public int getNumeroMesa() {
		return numeroMesa;
	}
	public void setNumeroMesa(int numeroMesa) {
		this.numeroMesa = numeroMesa;
	}
	public UsuarioEstabelecimento getEstabelecimento() {
		return estabelecimento;
	}
	public void setEstabelecimento(UsuarioEstabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
	
	
}
