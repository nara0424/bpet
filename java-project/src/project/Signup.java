package project;
/**
 * 회원가입 클래스
 * 
 * @author Gyeonghun Jo
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

public class Signup {
	/**
	 * 유저의 아이디, 이름, 비밀번호, 펫 종류에 대한 정보를 받아서 hashmap에 저장한다.
	 * 
	 */

	static JFrame frame;
	static JTextField idField;
	static JTextField pswField;
	static JTextField nameField;
	
	static String id = null;
	static String psw = null;
	static String name = null;
	static String pet = "cat";

	/**
	 * playBGM 효과음을 발생시키는 메서드
	 */
	void playBGM() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound\\sound3.wav"));
					Clip clip = AudioSystem.getClip();
					clip.stop();
					clip.open(ais);
					clip.start();
		} catch (Exception e1) {
			
		}
	}


	/**
	 * 어플리케이션을 실행하는 메서드
	 */
	public Signup() {
		initialize();
	}

	/**
	 * 프레임을 생성하는 메서드
	 */
	private void initialize() {
		frame = new JFrame("새 친구 만들기");
		frame.setBounds(100, 100, 465, 335);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		idField = new JTextField();
		idField.setBounds(190, 66, 116, 21);
		panel.add(idField);
		idField.setColumns(10);
		
		pswField = new JTextField();
		pswField.setBounds(190, 109, 116, 21);
		panel.add(pswField);
		pswField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(190, 151, 116, 21);
		panel.add(nameField);
		nameField.setColumns(10);
		
		JButton btnCancle = new JButton();
		btnCancle.setBorderPainted(false);
		btnCancle.setContentAreaFilled(false);
		btnCancle.setFocusPainted(false);
		// 취소 버튼 리스너
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playBGM();
				frame.setVisible(false);
			}
		});
		btnCancle.setBounds(350, 238, 69, 37);
		panel.add(btnCancle);
		
		JRadioButton rdbtnCat = new JRadioButton("고양이");
		rdbtnCat.setBorderPainted(false);
		rdbtnCat.setContentAreaFilled(false);
		rdbtnCat.setFocusPainted(false);
		rdbtnCat.setBounds(185, 201, 69, 23);
		rdbtnCat.setSelected(true);
		panel.add(rdbtnCat);
		
		JRadioButton rdbtnDog = new JRadioButton("강아지");
		rdbtnDog.setBorderPainted(false);
		rdbtnDog.setContentAreaFilled(false);
		rdbtnDog.setFocusPainted(false);
		rdbtnDog.setBounds(258, 201, 113, 23);
		panel.add(rdbtnDog);
		
		ButtonGroup bg = new ButtonGroup();
		
		bg.add(rdbtnCat);
		bg.add(rdbtnDog);

		
		
		JButton btnSave = new JButton();
		btnSave.setBorderPainted(false);
		btnSave.setContentAreaFilled(false);
		btnSave.setFocusPainted(false);
		// 등록 버튼 리스너
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playBGM();

				id = idField.getText();
				psw = pswField.getText();
				name = nameField.getText();
				
				if(rdbtnCat.isSelected()) {
					pet = "cat";
				}
				else if(rdbtnDog.isSelected()) {
					pet = "dog";
				}
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				 File f = new File(id + ".dat");
				    if(f.exists()) {
						JOptionPane.showMessageDialog(null, "중복된 아이디 입니다");
				    } else  {
				map.put(Info.ID, id);
				map.put(Info.PSW, psw);
				map.put(Info.NAME, name);
				map.put(Info.EXP, 1);
				map.put(Info.HP, 100);
				map.put(Info.POTION, 0);
				map.put(Info.MONEY, 1000000);
				map.put(Info.LEVEL, 1);
				map.put(Info.FEELING, 90);
				map.put(Info.STATE, 100);
				map.put(Info.PILL, 0);
				map.put(Info.DRINK, 0);
				map.put(Info.SNACK, 0);
				map.put(Info.PET, pet);
				
				map.put(Info.GACHA_1, 0);
				map.put(Info.GACHA_2, 0);
				map.put(Info.GACHA_3, 0);
				map.put(Info.GACHA_4, 0);
				map.put(Info.GACHA_5, 0);
				map.put(Info.GACHA_6, 0);
				
				Temp.saveMap(map);
				
				frame.setVisible(false);
				
				}
			}
		});
		btnSave.setBounds(253, 238, 77, 37);
		panel.add(btnSave);
		

		// 배경 이미지 라벨
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("image\\signupbg2.jpg"));
		lblNewLabel_4.setBounds(0, 0, 450, 300);
		panel.add(lblNewLabel_4);

		
	}
}
