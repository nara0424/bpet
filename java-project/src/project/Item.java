package project;
/**
 * 아이템 클래스
 * 
 * @author Gyeonghun Jo
 * 
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Item implements Info  {
	/**
	 * 가지고 있는 아이템 수량을 보여준다
	 * 아이템을 사용하여 캐릭터를 강화한다
	 * 옷장 버튼을 누르면 가지고 있는 가챠 아이템을 보여준다
	 * 옷장의 가챠 아이템 착용하기를 누르면 메인클래스의 캐릭터에게 해당 아이템을 착용시킨다. 
	 */

	JFrame frame;
	private JTextField txtPotion;
	private JTextField txtDrink;
	private JTextField txtPill;
	private JTextField txtSnack;
	JLabel lblItembg;
	
	JButton btnUsePotion;
	JButton btnUseEnergyDrink;
	JButton btnUseMedicine;
	JButton btnUseSnack;
	
	JButton btnItemClose;
	JButton btnItemBag;
	JButton btnGachaCloset;
	
	JPanel panel;
	JPanel closetPanel;
	

	private Main parent;
	
	/**
	 * playBGM 효과음을 발생시키는 메서드
	 */
	void playBGM() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound\\sound4.wav"));
					Clip clip = AudioSystem.getClip();
					clip.stop();
					clip.open(ais);
					clip.start();
		} catch (Exception e1) {
		}
	}
	
	/**
	 * Item의 mutator 
	 * @param parent
	 */
	public Item(Main parent) {
		this.parent = parent;
		initialize();
	}
	
	// 버튼 리스너
	private ActionListener potionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			playBGM();
			if (parent.potion > 0) {
				parent.potion -= 1;
				if(parent.exp >= 100) {
					parent.level++;
					parent.exp -= 100;
				}
				else {
					parent.exp += 30;
				}
			JOptionPane.showMessageDialog(null, "경험치 30 증가");
			countItem();
			}
			else {
				JOptionPane.showMessageDialog(null, "수량이 없습니다");
		}
		}
	};
	private ActionListener drinkListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			playBGM();
			if (parent.drink > 0) {
				parent.drink -= 1;
				parent.feeling += 40;
			JOptionPane.showMessageDialog(null, "기분 UP!");
			countItem();
			}
			else {
				JOptionPane.showMessageDialog(null, "수량이 없습니다");
			}
		}
	};
	private ActionListener pillListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			playBGM();
			if (parent.pill > 0) {
				if (parent.state > 20) {
					JOptionPane.showMessageDialog(null, "아프지 않아요!");
				}
				else {
					parent.pill -= 1;
					parent.state += 50;
				JOptionPane.showMessageDialog(null, "이제 괜찮아요!");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "수량이 없습니다");
			}
			countItem();
		}
	};
	private ActionListener snackListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			playBGM();
			if (parent.snack > 0) {
				parent.snack -= 1;
				parent.snack += 40;
			JOptionPane.showMessageDialog(null, "냠냠");
			countItem();
			}
			else {
				JOptionPane.showMessageDialog(null, "수량이 없습니다");
			}
		}
	};private ActionListener itemListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			playBGM();
			showItem();
		}
	};private ActionListener closetListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			playBGM();
			showCloset();
			countCloset();
		}
	};private ActionListener backListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			playBGM();
			frame.setVisible(false);
			parent.frame.setVisible(true);
			parent.checkEverything();
		}
	};
	JLabel lblGacha1;
private JLabel lblGacha2;
private JLabel lblGacha3;
private JLabel lblGacha4;
private JLabel lblGacha5;
private JLabel lblGacha6;
private JButton btnUseGacha1;
private JButton btnUseGacha2;
private JButton btnUseGacha3;
private JButton btnUseGacha4;
private JButton btnUseGacha5;
private JButton btnUseGacha6;

	/**
	 * 프레임을 생성하는 메서드
	 */
	private void initialize() {
		
		frame = new JFrame("아이템창");
		frame.setBounds(0, 0, 415, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBounds(0, 0, 400, 600);
		panel.setLayout(null);
		
		closetPanel = new JPanel();
		closetPanel.setBounds(0, 0, 400, 600);
		closetPanel.setLayout(null);

		
/////////////////////////////////////아이템 창 /////////////////////////////////////
		// 아이템 현재 수량 출력
		txtPotion = new JTextField();
		txtPotion.setEditable(false);
		txtPotion.setBounds(145, 162, 96, 21);
		panel.add(txtPotion);
		txtPotion.setColumns(10);
		
		txtDrink = new JTextField();
		txtDrink.setEditable(false);
		txtDrink.setBounds(145, 270, 96, 21);
		panel.add(txtDrink);
		txtDrink.setColumns(10);
		
		txtPill = new JTextField();
		txtPill.setEditable(false);
		txtPill.setBounds(145, 377, 96, 21);
		panel.add(txtPill);
		txtPill.setColumns(10);
		
		txtSnack = new JTextField();
		txtSnack.setEditable(false);
		txtSnack.setBounds(145, 482, 96, 21);
		panel.add(txtSnack);
		txtSnack.setColumns(10);
		
		countItem();
		
		// 아이템 사용하기 버튼 ( 아이템 1씩 감소 )
		btnUsePotion = new JButton("사용하기");
		btnUsePotion.setBounds(269, 161, 91, 23);
		btnUsePotion.addActionListener(potionListener);
		panel.add(btnUsePotion);
		
		btnUseEnergyDrink = new JButton("사용하기");
		btnUseEnergyDrink.setBounds(269, 269, 91, 23);
		btnUseEnergyDrink.addActionListener(drinkListener);
		panel.add(btnUseEnergyDrink);
		
		btnUseMedicine = new JButton("사용하기");
		btnUseMedicine.setBounds(269, 376, 91, 23);
		btnUseMedicine.addActionListener(pillListener);
		panel.add(btnUseMedicine);
		
		btnUseSnack = new JButton("사용하기");
		btnUseSnack.setBounds(269, 481, 91, 23);
		btnUseSnack.addActionListener(snackListener);
		panel.add(btnUseSnack);
		
		// 창닫기 - 돌아가기
		btnItemClose = new JButton("");
		btnItemClose.setBounds(289, 541, 91, 36);
		btnItemClose.setBorderPainted(false);
		btnItemClose.setContentAreaFilled(false);
		btnItemClose.setFocusPainted(false);
		btnItemClose.addActionListener(backListener);
		panel.add(btnItemClose);
		
		btnItemBag = new JButton("");
		btnItemBag.setBounds(25, 49, 108, 47);
		btnItemBag.setBorderPainted(false);
		btnItemBag.setContentAreaFilled(false);
		btnItemBag.setFocusPainted(false);
		btnItemBag.addActionListener(itemListener);
		panel.add(btnItemBag);
		
		btnGachaCloset = new JButton("");
		btnGachaCloset.setBounds(145, 49, 96, 47);
		btnGachaCloset.setBorderPainted(false);
		btnGachaCloset.setContentAreaFilled(false);
		btnGachaCloset.setFocusPainted(false);
		btnGachaCloset.addActionListener(closetListener);
		panel.add(btnGachaCloset);
		
		lblItembg = new JLabel("New label");
		lblItembg.setBounds(0, 0, 400, 600);
		lblItembg.setIcon(new ImageIcon("image\\testImg.jpg"));
		panel.add(lblItembg);
		

		showItem();

/////////////////////////////////////아이템 창 끝!!/////////////////////////////////////
		

/////////////////////////////////////	옷장		 /////////////////////////////////////
		
		lblGacha1 = new JLabel("가챠1");
		lblGacha1.setBounds(45, 135, 90, 90);
		closetPanel.add(lblGacha1);
		
		lblGacha2 = new JLabel("가챠2");
		lblGacha2.setBounds(155, 135, 90, 90);
		closetPanel.add(lblGacha2);
		
		lblGacha3 = new JLabel("가챠3");
		lblGacha3.setBounds(265, 135, 90, 90);
		closetPanel.add(lblGacha3);
		
		lblGacha4 = new JLabel("가챠4");
		lblGacha4.setBounds(45, 277, 90, 90);
		closetPanel.add(lblGacha4);
		
		lblGacha5 = new JLabel("가챠5");
		lblGacha5.setBounds(155, 277, 90, 90);
		closetPanel.add(lblGacha5);
		
		lblGacha6 = new JLabel("가챠6");
		lblGacha6.setBounds(265, 277, 90, 90);
		closetPanel.add(lblGacha6);
		
		
		btnUseGacha1 = new JButton("착용하기");
		btnUseGacha1.setBounds(45, 235, 91, 23);
		closetPanel.add(btnUseGacha1);
		btnUseGacha1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playBGM();
				if(parent.gacha1 == 1) {
					parent.gacha1 ++;
					JOptionPane.showMessageDialog(null, "착용 완료!");
				}
				else {
					JOptionPane.showMessageDialog(null, "수량이 없습니다! \n가챠를 진행하여 뽑아주세요");
				}
			}
		});
		
		btnUseGacha2 = new JButton("착용하기");
		btnUseGacha2.setBounds(150, 237, 91, 23);
		closetPanel.add(btnUseGacha2);
		btnUseGacha2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playBGM();
				if(parent.gacha2 == 1) {
					parent.gacha2 ++;
					JOptionPane.showMessageDialog(null, "착용 완료!");
				}
				else {
					JOptionPane.showMessageDialog(null, "수량이 없습니다! \n가챠를 진행하여 뽑아주세요");
				}
			}
		});
		
		btnUseGacha3 = new JButton("착용하기");
		btnUseGacha3.setBounds(264, 235, 91, 23);
		closetPanel.add(btnUseGacha3);
		btnUseGacha3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playBGM();
				if(parent.gacha3 == 1) {
					parent.gacha3 ++;
					JOptionPane.showMessageDialog(null, "착용 완료!");
				}
				else {
					JOptionPane.showMessageDialog(null, "수량이 없습니다! \n가챠를 진행하여 뽑아주세요");
				}
			}
		});
		
		btnUseGacha4 = new JButton("착용하기");
		btnUseGacha4.setBounds(45, 376, 91, 23);
		closetPanel.add(btnUseGacha4);
		btnUseGacha4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playBGM();
				if(parent.gacha4 == 1) {
					parent.gacha4 ++;
					JOptionPane.showMessageDialog(null, "착용 완료!");
				}
				else {
					JOptionPane.showMessageDialog(null, "수량이 없습니다! \n가챠를 진행하여 뽑아주세요");
				}
			}
		});
		
		btnUseGacha5 = new JButton("착용하기");
		btnUseGacha5.setBounds(150, 376, 91, 23);
		closetPanel.add(btnUseGacha5);
		btnUseGacha5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playBGM();
				if(parent.gacha5 == 1) {
					parent.gacha5 ++;
					JOptionPane.showMessageDialog(null, "착용 완료!");
				}
				else {
					JOptionPane.showMessageDialog(null, "수량이 없습니다! \n가챠를 진행하여 뽑아주세요");
				}
			}
		});
		
		btnUseGacha6 = new JButton("착용하기");
		btnUseGacha6.setBounds(264, 376, 91, 23);
		closetPanel.add(btnUseGacha6);
		btnUseGacha6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playBGM();
				if(parent.gacha6 == 1) {
					parent.gacha6 ++;
					JOptionPane.showMessageDialog(null, "착용 완료!");
				}
				else {
					JOptionPane.showMessageDialog(null, "수량이 없습니다! \n가챠를 진행하여 뽑아주세요");
				}
			}
		});
		

////////////////////////////////////	옷장 끝!!! 	 /////////////////////////////////////
		
		
	}
	
	/**
	 * 가지고 있는 아이템 수량을 출력하는 메서드
	 */
	void showItem() {
		lblItembg.setIcon(new ImageIcon("image\\itembgitem.jpg"));

		frame.getContentPane().remove(closetPanel);
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		panel.add(btnGachaCloset);
		panel.add(btnItemBag);
		panel.add(btnItemClose);
		
		panel.add(txtPotion);
		panel.add(txtDrink);
		panel.add(txtPill);
		panel.add(txtSnack);
		panel.add(btnUsePotion);
		panel.add(btnUseEnergyDrink);
		panel.add(btnUseMedicine);
		panel.add(btnUseSnack);
		panel.add(lblItembg);
	}
	
	/**
	 * 가지고 있는 가챠 아이템을 출력하는 메서드
	 */
	void showCloset() {

		lblItembg.setIcon(new ImageIcon("image\\itembgcloset.jpg"));
		
		frame.getContentPane().remove(panel);
		frame.getContentPane().add(closetPanel, BorderLayout.CENTER);
			
		closetPanel.add(btnItemClose);
		closetPanel.add(btnItemBag);
		closetPanel.add(btnGachaCloset);
		
		closetPanel.add(btnUseGacha1);
		closetPanel.add(btnUseGacha2);
		closetPanel.add(btnUseGacha3);
		closetPanel.add(btnUseGacha4);
		closetPanel.add(btnUseGacha5);
		closetPanel.add(btnUseGacha6);
		
		closetPanel.add(lblGacha1);
		closetPanel.add(lblGacha2);
		closetPanel.add(lblGacha3);
		closetPanel.add(lblGacha4);
		closetPanel.add(lblGacha5);
		closetPanel.add(lblGacha6);

		closetPanel.add(lblItembg);	
		countCloset();

	}
	
	/**
	 * 가지고 있는 아이템 수량을 textField에 출력하는 메서드
	 */
	void countItem() {
		txtPotion.setText("경험치물약 x " + parent.potion);
		txtDrink.setText("에너지드링크 x " + parent.drink);
		txtPill.setText("비상약 x " + parent.pill);
		txtSnack.setText("간식 x " + parent.snack);
		
	}
	
	/**
	 * 가지고 있는 가챠 아이템 이미지를 라벨에 출력하는 메서드
	 */
	void countCloset() {
		if (parent.gacha1 >= 1) {
			lblGacha1.setIcon(new ImageIcon("image\\gacha1.png"));
		} if (parent.gacha2 >= 1) {
			lblGacha2.setIcon(new ImageIcon("image\\gacha2.png"));
		} if (parent.gacha3 >= 1) {
			lblGacha3.setIcon(new ImageIcon("image\\gacha3.png"));
		} if (parent.gacha4 >= 1) {
			lblGacha4.setIcon(new ImageIcon("image\\gacha4.png"));
		} if (parent.gacha5 >= 1) {
			lblGacha5.setIcon(new ImageIcon("image\\gacha5.png"));
		} if (parent.gacha6 >= 1) {
			lblGacha6.setIcon(new ImageIcon("image\\gacha6.png"));
		}
		
		
	}
}
