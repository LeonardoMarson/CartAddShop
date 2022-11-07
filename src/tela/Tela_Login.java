package tela;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bd.ClienteDAO;

public class Tela_Login extends JFrame implements ActionListener {
	private JTextField Name_Field = new JTextField(40);
	private JPasswordField Password = new JPasswordField(40);
	private JButton Log_In = new JButton("Entrar");
	private JButton Register = new JButton("Cadastrar-se");
	private JButton Cancel = new JButton("Limpar");
	private JPanel painel = new JPanel();
	private JLabel user = new JLabel("Usuario (RG):");
	private JLabel password = new JLabel("Senha:");
	private JLabel chBoxLabel = new JLabel("Mostrar senha?");
	private JCheckBox chBox = new JCheckBox();
	private JLabel rotuloImagem;
	private ImageIcon imageIcon = new ImageIcon(getClass().getResource("/img/image.png"));
	
	
	public Tela_Login() {
		setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/icon.png"));
		setIconImage(icon.getImage());
		
		// RESIZE THE IMAGE
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(250, 100, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg); 
        rotuloImagem = new JLabel(imageIcon);
        
		Password.setEchoChar('\u25cf');

		painel.setLayout(null);
		painel.add(Name_Field);
		painel.add(Password);
		painel.add(Log_In);
		painel.add(Cancel);
		painel.add(Register);
		painel.add(user);
		painel.add(password);
		painel.add(chBoxLabel);
		painel.add(chBox);
		painel.add(rotuloImagem);
		
		rotuloImagem.setBounds(150, 25, 300, 100);
		user.setBounds(160, 150, 300, 20);
		Name_Field.setBounds(160, 180, 300, 40);
		password.setBounds(160, 250, 300, 20);
		Password.setBounds(160, 280, 300, 40);
		Log_In.setBounds(160, 380, 140, 40);
		Cancel.setBounds(320, 380, 140, 40);
		Register.setBounds(160, 470, 300, 40);
		chBoxLabel.setBounds(330, 330, 150, 20);
		chBox.setBounds(442, 330, 20, 20);

		Name_Field.addActionListener(this);
		Password.addActionListener(this);
		Log_In.addActionListener(this);
		Cancel.addActionListener(this);
		Register.addActionListener(this);
		chBox.addActionListener(this);

		add(painel, BorderLayout.CENTER);

		setTitle("Login");
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Cancel) {
			Name_Field.setText("");
			Password.setText("");
		}

		if (e.getSource() == Log_In) {
			try {
			String valor = new String(Password.getPassword());
			ClienteDAO dao = new ClienteDAO();
			boolean resposta = dao.consultar(Long.parseLong(Name_Field.getText()), valor);
			
			if (resposta == true) {
				if ((Long.parseLong(Name_Field.getText())) == 123) {
					new Tela_Adm();
					dispose();
				} 
				else {
					Tela_Carrinho.logCliente = Long.parseLong(Name_Field.getText());
					new Tela_Carrinho();
					dispose();
				}
			} 
			else {
				JOptionPane.showMessageDialog(this, "Acesso negado", "erro", JOptionPane.WARNING_MESSAGE);
			}
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(this, "Acesso negado", "erro", JOptionPane.WARNING_MESSAGE);
			}

		}

		if (e.getSource() == Register) {
			new Tela_Usuario();
			dispose();
		}

		if (chBox.isSelected()) {
			Password.setEchoChar((char) 0);
		} 
		else {
			Password.setEchoChar('\u25cf');
		}
	}
	
} // End of Tela_Login class