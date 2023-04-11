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
	public UsuarioCliente getUsuarioCliente() {
		return usuarioCliente;
	}
	public void setUsuarioCliente(UsuarioCliente usuarioCliente) {
		this.usuarioCliente = usuarioCliente;
	}
	public MesaComanda getMesaComanda() {
		return mesaComanda;
	}
	public void setMesaComanda(MesaComanda mesaComanda) {
		this.mesaComanda = mesaComanda;
	}
	public ItensPedido getProdutos() {
		return produtos;
	}
	public void setProdutos(ItensPedido produtos) {
		this.produtos = produtos;
	}
	public float getTaxaDesconto() {
		return taxaDesconto;
	}
	public void setTaxaDesconto(float taxaDesconto) {
		this.taxaDesconto = taxaDesconto;
	}
	public float getTaxa10porcento() {
		return taxa10porcento;
	}
	public void setTaxa10porcento(float taxa10porcento) {
		this.taxa10porcento = taxa10porcento;
	}
	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}
	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
	public Double getValorTotalDoPedido() {
		return valorTotalDoPedido;
	}
	public void setValorTotalDoPedido(Double valorTotalDoPedido) {
		this.valorTotalDoPedido = valorTotalDoPedido;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
