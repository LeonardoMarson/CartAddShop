package bd;

import java.util.Objects;

public class Produto {
	
	private int id;
	private String nome;
	private float valor;
	private int quantidade;
	private String description;

	// GETTER AND SETTER FOR ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	// GETTER AND SETTER FOR NOME
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	// GETTER AND SETTER FOR VALOR
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	// GETTER AND SETTER FOR QUANTIDADE
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	// GETTER AND SETTER FOR DESCRIPTION
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		Produto other = (Produto) obj;
		return id == other.id;
	}
	
} // End of Produto class
