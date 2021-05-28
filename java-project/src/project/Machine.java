package project;
/**
 * 자판기 클래스
 * 
 * @author Gyeonghun Jo
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Machine {
/**
 * 살 수 있는 아이템과 가격을 보여준다.
 * 구매하기 버튼 클릭시 가지고 있는 아이템 수량이 증가한다.
 * 
 */
	JFrame frame;
	private Main parent;
	
	/**
	 * playBGM 효과음을 발생시키는 메서드
	 */
	void playBGM() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound\\sound6.wav"));
					Clip clip = AudioSystem.getClip();
					clip.stop();
					clip.open(ais);
					clip.start();
		} catch (Exception e1) {
			
		}
	}
	
	
	/**
	 * 필드 Machine 의 mutator
	 * @param parent
	 */
	public Machine(Main parent) {
		this.parent = parent;
		initialize();
	}

	
	// 버튼 리스너
		private ActionListener potionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playBGM();
				if (parent.money >= 5000) {
				parent.potion += 1;
				parent.money -= 5000;
				JOptionPane.showMessageDialog(null, "경험치 물약 1개 구매 완료!");
				
				countMoney();
				}
				else {
					JOptionPane.showMessageDialog(null, "돈이 부족해요");
				}
			}
		};
		private ActionListener drinkListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playBGM();
				if (parent.money >= 3000) {
					parent.drink += 1;
					parent.money -= 3000;
				JOptionPane.showMessageDialog(null, "에너지드링크 1개 구매 완료!");
				countMoney();
				}
				else {
					JOptionPane.showMessageDialog(null, "돈이 부족해요");
				}
			}
		};
		private ActionListener pillListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playBGM();
				if (parent.money >= 4000) {
					parent.pill += 1;
					parent.money -= 4000;
				JOptionPane.showMessageDialog(null, "비상약 1개 구매 완료!");
				countMoney();
				}
				else {
					JOptionPane.showMessageDialog(null, "돈이 부족해요");
				}
			}
		};
		private ActionListener snackListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playBGM();
				if (parent.money >= 3000) {
					parent.snack += 1;
					parent.money -= 3000;
				JOptionPane.showMessageDialog(null, "간식 1개 구매 완료!");
				countMoney();
				}
				else {
					JOptionPane.showMessageDialog(null, "돈이 부족해요");
				}
			}
		};private ActionListener backListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Out.frameOut.setVisible(true);
			}
		};
private JTextField nowMoneyField;
	

	/**
	 * 프레임을 생성하는 메서드
	 */
	private void initialize() {
		frame = new JFrame("만능자판기");
		frame.setBounds(100, 100, 415, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnExpPotion = new JButton();
		btnExpPotion.setBounds(89, 166, 191, 49);
		btnExpPotion.setContentAreaFilled(false);
		btnExpPotion.addActionListener(potionListener);
		panel.add(btnExpPotion);
		
		JButton btnMedicine = new JButton();
		btnMedicine.setBounds(89, 295, 191, 49);
		btnMedicine.setContentAreaFilled(false);
		btnMedicine.addActionListener(pillListener);
		panel.add(btnMedicine);
		
		JButton btnEnergyDrink = new JButton();
		btnEnergyDrink.setBounds(89, 236, 191, 49);
		btnEnergyDrink.setContentAreaFilled(false);
		btnEnergyDrink.addActionListener(drinkListener);
		panel.add(btnEnergyDrink);
		
		JButton btnSnack = new JButton();
		btnSnack.setBounds(89, 365, 191, 49);
		btnSnack.setContentAreaFilled(false);
		btnSnack.addActionListener(snackListener);
		panel.add(btnSnack);
		
		JButton btnMachineClose = new JButton();
		btnMachineClose.setBounds(288, 553, 101, 35);
		btnMachineClose.setBorderPainted(false);
		btnMachineClose.setContentAreaFilled(false);
		btnMachineClose.setFocusPainted(false);
		btnMachineClose.addActionListener(backListener);
		panel.add(btnMachineClose);
		
		nowMoneyField = new JTextField();
		nowMoneyField.setEditable(false);
		nowMoneyField.setBounds(12, 567, 151, 21);
		panel.add(nowMoneyField);
		nowMoneyField.setColumns(20);
		
		JLabel lblNewLabel_4 = new JLabel();
		lblNewLabel_4.setIcon(new ImageIcon("image\\machinebg.jpg"));
		lblNewLabel_4.setBounds(0, 0, 400, 600);
		panel.add(lblNewLabel_4);
		
		countMoney();
	}
	
	/**
	 * 가지고 있는 재화를 textArea에 출력하는 메서드
	 */
	void countMoney() {
		String nowMoney = "현재 용돈 : " + parent.money + " 원";
		nowMoneyField.setText(nowMoney);
		
	}
}

		

