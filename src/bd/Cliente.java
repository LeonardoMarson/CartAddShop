package bd;

import java.sql.Date;
import java.util.Objects;

public class Cliente {
	
	private long rg;
	private String nome;
	private Date nascimento;
	private String endereco;
	private String senha;
	
	// GETTER AND SETTER FOR RG
	public long getRg() {
		return rg;
	}
	public void setRg(long rg) {
		this.rg = rg;
	}

	// GETTER AND SETTER FOR NOME
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	// GETTER AND SETTER FOR NASCIMENTO
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	// GETTER AND SETTER FOR ENDERECO
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	// GETTER AND SETTER FOR SENHA
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}


	@Override
	public int hashCode() {
		return Objects.hash(rg);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return rg == other.rg;
	}

} // End of Cliente class
