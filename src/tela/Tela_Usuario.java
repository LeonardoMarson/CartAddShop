package tela;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import bd.Cliente;
import bd.ClienteDAO;


public class Tela_Usuario extends JFrame implements ActionListener, KeyListener
{
    private JLabel ScreenTitle = new JLabel("Cadastro Usuario");
    private JLabel Name = new JLabel("Nome:");
    private JLabel RG = new JLabel("RG:");
    private JLabel Address = new JLabel("Endereco:");
    private JLabel BirthDate = new JLabel("Data de nascimento:");
    private JLabel Password = new JLabel("Senha:");

    private JTextField nField = new JTextField(40);
    private JTextField RGField = new JTextField(10);
    private JTextField adField = new JTextField(50);
    private JPasswordField pField = new JPasswordField(40);
    
    private JLabel chBoxLabel = new JLabel("Mostrar senha?");
    private JCheckBox chBox = new JCheckBox();
    
    private MaskFormatter bdFieldMask = null;
    private JFormattedTextField bdField;

    private JButton Cancel = new JButton("Voltar");
    private JButton Register = new JButton("Cadastrar");

    private JPanel painel = new JPanel();

    static String v = "";
    
    public static int errodao = -1;
    
    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    Date dataUtil = new java.util.Date();
    
    public Tela_Usuario()
    { 
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/icon.png"));
		setIconImage(icon.getImage());
    		
    	pField.setEchoChar('\u25cf');
    	
        try {
            bdFieldMask = new MaskFormatter("##/##/####");
            bdFieldMask.setPlaceholderCharacter('_');
        } 
        catch (ParseException e) {
            System.out.println("Error!");
        }

        // FORMATTED TEXT FOR THE DATE
        bdField = new JFormattedTextField(bdFieldMask);

        setLayout(new BorderLayout());

        // ADD ELEMENTS TO THE PANEL

        painel.setLayout(null);
        painel.add(ScreenTitle);

        painel.add(Name);
        painel.add(nField);

        painel.add(RG);
        painel.add(RGField);

        painel.add(Address);
        painel.add(adField);
        
        painel.add(BirthDate);
        painel.add(bdField);
        
        painel.add(Password);
        painel.add(pField);

        painel.add(Cancel);
        painel.add(Register);
        
        painel.add(chBoxLabel);
        painel.add(chBox);

        // BOUNDS SET
        ScreenTitle.setFont(new Font("Helveltica", Font.BOLD, 30));
        ScreenTitle.setBounds(160, 50, 300, 30);
        
        Name.setFont(new Font("Arial", Font.BOLD, 18));
        Name.setBounds(90, 130, 100, 40);
        nField.setBounds(220, 130, 300, 40);
        nField.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));

        RG.setFont(new Font("Arial", Font.BOLD, 18));
        RG.setBounds(90, 200, 100, 40);
        RGField.setBounds(220, 200, 300, 40);
        RGField.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));

        Address.setFont(new Font("Arial", Font.BOLD, 18));
        Address.setBounds(90, 270, 120, 40);
        adField.setBounds(220, 270, 300, 40);
        adField.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));

        BirthDate.setFont(new Font("Arial", Font.BOLD, 18));
        BirthDate.setBounds(90, 340, 200, 40);
        bdField.setFont(new Font("Arial", Font.PLAIN, 18));
        bdField.setBounds(300, 340, 150, 40);
        bdField.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
        
        Password.setFont(new Font("Arial", Font.BOLD, 18));
        Password.setBounds(90, 410, 300, 40);
        pField.setBounds(220,410, 300, 40);
        pField.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.5f)));
        
        chBoxLabel.setBounds(391, 450, 150, 20);
        chBox.setBounds(503, 450, 20, 20);

        Cancel.setBounds(160, 500, 140, 40);
        Register.setBounds(310, 500, 140, 40);    
        
        chBox.addActionListener(this);
        Cancel.addActionListener(this);
        Register.addActionListener(this);
        RGField.addKeyListener(this);

        getContentPane().add(painel,BorderLayout.CENTER);

        setSize(600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

//    public void erro() {
 //   	JOptionPane.showMessageDialog(this, "Erro no cadastro, tente novamente", "erro", JOptionPane.WARNING_MESSAGE);
    	
 //   }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if(e.getSource() == Cancel){
            dispose();
    		new Tela_Login();	
    	}
    	
    	if(e.getSource() == Register){
    		
    		if(nField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Campo Nome nao pode estar em Branco", "erro", JOptionPane.WARNING_MESSAGE);
    		}
    		else if(RGField.getText().isEmpty()) {
    			JOptionPane.showMessageDialog(this, "Campo RG nao pode estar em Branco", "erro", JOptionPane.WARNING_MESSAGE);
    		}
            else if(RGField.getText().length() != 10) {
    			JOptionPane.showMessageDialog(this, "Um RG contem 10 caracteres ", "erro", JOptionPane.WARNING_MESSAGE);
    		}
    		else if(adField.getText().isEmpty()) {
    			JOptionPane.showMessageDialog(this, "Campo Endereco nao pode estar em Branco", "erro", JOptionPane.WARNING_MESSAGE);
    		}
    		else if(String.valueOf(pField.getPassword()).isEmpty()) {
    			JOptionPane.showMessageDialog(this, "Campo Senha nao pode estar em Branco", "erro", JOptionPane.WARNING_MESSAGE);
    		}
    		else {
	
    			try {
    				errodao = -1;
    				dataUtil = formatoData.parse(bdField.getText());
    				java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
    				
        			ClienteDAO dao = new ClienteDAO();
            		Cliente cli = new Cliente();
            		cli.setRg(Long.parseLong(RGField.getText()));
            		cli.setNome(nField.getText());
            		cli.setEndereco(adField.getText());
            		cli.setNascimento(dataSql);
            		cli.setSenha(String.valueOf(pField.getPassword()));
            		dao.incluir(cli);
            		if(errodao == -1)
            		{
            		    JOptionPane.showMessageDialog(this, "Registrado", "Parabens", JOptionPane.WARNING_MESSAGE);
            		    new Tela_Login();
                        dispose();
            	    }
            	    else {
            		    JOptionPane.showMessageDialog(this, "Erro no cadastro, tente novamente", "erro", JOptionPane.WARNING_MESSAGE);
            	    }
				} 
                catch (ParseException e1) {
					JOptionPane.showMessageDialog(this, "Campo Data nao pode estar em Branco", "erro", JOptionPane.WARNING_MESSAGE);
				}
    		}
        }
    	
    	if(chBox.isSelected()){
            pField.setEchoChar((char) 0);
        }
        else {
            pField.setEchoChar('\u25cf');
        }
    }

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
			v = RGField.getText();
		}
		else {
			RGField.setText(v);
		}
	}

} // End of Tela_Usuario class