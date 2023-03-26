package modelo;

import java.util.Objects;

public class Pedidos {
	private Long id;
	private Double total;
	
	private Produto item;
	private Cliente cliente;
	private Estabelecimento estabelecimento;
	
	public Pedidos() {
		
	}

	public Pedidos(Long id, Double total, Produto item, Cliente cliente, Estabelecimento estabelecimento) {
		this.id = id;
		this.total = total;
		this.item = item;
		this.cliente = cliente;
		this.estabelecimento = estabelecimento;
	}

	public Long getId() {
		return id;
	}

	public Double getTotal() {
		return total;
	}

	public Produto getItem() {
		return item;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedidos other = (Pedidos) obj;
		return Objects.equals(id, other.id);
	}

}
