package principal;

import tela.Tela_Login;

public class Principal {
    public static void main(String[] args) throws Exception {
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Driver n�o carregado");
		}
    	
		new Tela_Login();
    }
}
