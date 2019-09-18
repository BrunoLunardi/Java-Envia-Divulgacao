package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.TopicoDAO;
import dto.TopicoDTO;

public class EnviarMensagem extends JFrame{


	private JPanel contentPane;
	private JTextField textField;
	private JComboBox jcombobox;
	//objeto para executar sql de insert no bd
	TopicoDAO topicoDAO = new TopicoDAO();
	//cria objeto do tipo TopicoDTO com o nome do topico passado
	TopicoDTO topicoDTO;
	
	/**
	 * Create the frame.
	 */
	public EnviarMensagem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tópico:");
		lblNewLabel.setBounds(12, 15, 322, 15);
		contentPane.add(lblNewLabel);
		
		jcombobox = new JComboBox();
		/////////////////JComboBox recebe dados do BD, tabela topicos
		try {
			for(TopicoDTO t: topicoDAO.listaTopicos()) {
				jcombobox.addItem(t.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		jcombobox.setBounds(12, 42, 426, 19);
		contentPane.add(jcombobox);
		//jcombobox.setColumns(10);


		JButton btnEnviarMensagem = new JButton("Enivar Mensagem");
		
		btnEnviarMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String topicoSelecionado = (String) jcombobox.getSelectedItem();
				
				//exibe resultado na tela
				JOptionPane.showMessageDialog(null, topicoSelecionado ,"Tópico cadastrado",
						JOptionPane.INFORMATION_MESSAGE);	
				
				Main frame = new Main();
				frame.setVisible(true);
				dispose();				
				
			}
		});		

		btnEnviarMensagem.setBounds(273, 125, 114, 25);
		contentPane.add(btnEnviarMensagem);

		
		
		JButton btnCancelar = new JButton("Cancelar");
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main frame = new Main();
				frame.setVisible(true);
				dispose();
			}
		});				
		
		btnCancelar.setBounds(73, 125, 114, 25);
		contentPane.add(btnCancelar);
	}	
		
	
}
