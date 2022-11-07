package bd;

import java.util.Objects;

public class ItemPedido {
	
	private int id;
	private int pedido;
	private int produto;
	private int quantidade;
	
	// GETTER AND SETTER FOR ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	// GETTER AND SETTER FOR PEDIDO
	public int getPedido() {
		return pedido;
	}
	public void setPedido(int pedido) {
		this.pedido = pedido;
	}

	// GETTER AND SETTER FOR PRODUTO
	public int getProduto() {
		return produto;
	}
	public void setProduto(int produto) {
		this.produto = produto;
	}
	
	// GETTER AND SETTER FOR QUANTIDADE
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
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
		ItemPedido other = (ItemPedido) obj;
		return id == other.id;
	}
	
} // End of ItemPedido class
