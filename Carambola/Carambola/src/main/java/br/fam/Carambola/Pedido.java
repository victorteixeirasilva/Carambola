package br.fam.Carambola;

public class Pedido {
	private int idUsuarioCliente;
	private int idPedido;
	private int idMesa;
	private float taxaDesconto, taxa10porcento;
	private int idFormaDePagamento;
	private Double valorTotalDoPedido;
	private String status;
	
	public Pedido() {
		
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
	public int getIdFormaDePagamento() {
		return idFormaDePagamento;
	}
	public void setIdFormaDePagamento(int idFormaDePagamento) {
		this.idFormaDePagamento = idFormaDePagamento;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdUsuarioCliente() {
		return idUsuarioCliente;
	}
	public void setIdUsuarioCliente(int idUsuarioCliente) {
		this.idUsuarioCliente = idUsuarioCliente;
	}

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	
	
}
