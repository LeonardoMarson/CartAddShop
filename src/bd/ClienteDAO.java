package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tela.Tela_Usuario;

public class ClienteDAO {

	public void alterar(Cliente c) {

		try {
			Connection con = Connect.getInstance();
			
			PreparedStatement st = con.prepareStatement("UPDATE Cliente SET nome=?, nascimento=?, endereco=?, senha=?  WHERE rg=?");
			st.setString(1, c.getNome());
			st.setDate(2, c.getNascimento());
			st.setString(3, c.getEndereco());
			st.setString(4, c.getSenha());
			st.setLong(5, c.getRg());
			
			st.executeUpdate();
			
			st.close();
			
			
		}	
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();
		}
	}
	
	public void incluir(Cliente c) {

		try {
			Connection con = Connect.getInstance();
			PreparedStatement st =  con.prepareStatement("INSERT INTO Cliente Values(?,?,?,?,?)");
			st.setLong(1, c.getRg());
			st.setString(2, c.getNome());
			st.setDate(3, c.getNascimento());
			st.setString(4, c.getEndereco());
			st.setString(5, c.getSenha());
			
			st.executeUpdate();
			st.close();
			
		}
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			//Tela_Usuario erro = new Tela_Usuario();
			Tela_Usuario.errodao = 0; 
			Connect.closeCon();
			//erro.erro();
		}	
	}
	
	public void excluir(int rg) {
		
		try {
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("DELETE FROM Cliente WHERE rg=?");
			st.setLong(1, rg);
			
			st.executeUpdate();
			
		}	
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();;
		}
	}
	
	public List<Cliente> listar() {

		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("SELECT * From Cliente");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setRg(rs.getInt("rg"));
				c.setNome(rs.getString("nome"));
				c.setNascimento(rs.getDate("nascimento"));
				c.setEndereco(rs.getString("endereco"));
				c.setSenha(rs.getString("senha"));
				clientes.add(c);
			}
			
			
		}
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();
		}	
		return clientes;
	}
	
	public List<Cliente> listarPorNome(String nome) {

		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("SELECT * From Cliente WHERE nome = ?");
			st.setString(1, nome);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setRg(rs.getInt("rg"));
				c.setNome(rs.getString("nome"));
				c.setNascimento(rs.getDate("nascimento"));
				c.setEndereco(rs.getString("endereco"));
				c.setSenha(rs.getString("senha"));
				clientes.add(c);
				
			}
			
			
		}
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();
		}
		return clientes;
	}
	
	public Cliente listarPorId(int rg) {

		Cliente cliente = new Cliente();
		
		try {
			Connect.getInstance();
			
			PreparedStatement st = Connect.getInstance().prepareStatement("SELECT * From Cliente WHERE rg=?");
			st.setLong(1, rg);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setRg(rs.getInt("rg"));
				c.setNome(rs.getString("nome"));
				c.setNascimento(rs.getDate("nascimento"));
				c.setEndereco(rs.getString("endereco"));
				c.setSenha(rs.getString("senha"));
				
			}
			
			
		}
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();
		}

		return cliente;
	}
	
	public boolean consultar(long rg, String senha) {

		boolean autenticado = false;

		try {
			
			Connect.getInstance();
			PreparedStatement st = Connect.getInstance().prepareStatement("SELECT rg, senha From Cliente WHERE rg=? and senha=?");
			st.setLong(1, rg);
			st.setString(2, senha);
			
			ResultSet rs;
			rs = st.executeQuery();

			if (rs.next()) {	
				autenticado = true;
			} 
			else {
				st.close();
				return autenticado;
			}
		} 
		catch (SQLException e) {
			System.out.println("Erro ao carregar: " + e.getMessage());
			Connect.closeCon();
		}

		return autenticado;	
	}

} // End of ClienteDAO class
