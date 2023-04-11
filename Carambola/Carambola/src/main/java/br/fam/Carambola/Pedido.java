package br.fam.Carambola;

import br.fam.Carambola.Usuarios.UsuarioCliente;

public class Pedido {
	private UsuarioCliente usuarioCliente;
	private MesaComanda mesaComanda;
	private ItensPedido produtos;
	private float taxaDesconto, taxa10porcento;
	private FormaDePagamento formaDePagamento;
	private Double valorTotalDoPedido;
	private String status;
}
