
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

//import jdk.nashorn.internal.ir.ContinueNode;

public class JGalo extends JFrame implements ActionListener {
	private static final long serialVersionUID = -3780928537820216588L;
	private JPanel jPanel = null;
	private JToggleButton bt[];
	JGaloInterface jogo ; 

	public JGalo(JGaloInterface myGreatGame) {
		super("Jogo da Galinha");
		jogo = myGreatGame;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,300);
        setLocation(100,100);
        jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(3,3));
		bt = new JToggleButton[9];
		for (int i=0; i<9; i++) {
			bt[i] = new JToggleButton();
			bt[i].setFont(new Font("Tahoma", Font.BOLD, 50));
			bt[i].setForeground(Color.BLUE);
			bt[i].addActionListener(this);
			jPanel.add(bt[i]);				
		}
		  
		this.setContentPane(jPanel);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().getClass().getSimpleName().equals("JToggleButton")){
			((JToggleButton)e.getSource()).setText(String.valueOf(jogo.getActualPlayer()));
			((JToggleButton)e.getSource()).setEnabled(false);
		}
		for (int i=0; i<9; i++)
			if (e.getSource()==bt[i]) { //which button was clicked
				jogo.setJogada(i/3+1,i%3+1);
			}

		if (jogo.isFinished()){
			char result = jogo.checkResult();
			if (result == ' ')
				JOptionPane.showMessageDialog(getContentPane(), "Empate!");
			else
				JOptionPane.showMessageDialog(getContentPane(), "Venceu o jogador " + result);
			System.exit(0);
		}
	}

	public static char reader (String args[]){
		char player = 'X'; //read the input and check if user wants to choose the letter to start with.
		System.out.println(args.length);
		if (args.length == 1) {
			if (args[0] == "O") {
				player = 'O';
				return player;
			}
		}
		else if (args.length > 1){
			System.err.println("Invalid input! Please write what player you want, X or O. Or no player");
			System.exit(0);
		}
		return player;

	}
	public static void main(String args[]) {
		char player;
		player= reader(args);
		JGaloEx game = new JGaloEx(player);
		new JGalo(game);
	}
} 
