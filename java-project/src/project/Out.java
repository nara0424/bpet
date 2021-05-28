package project;
/**
 * 외출 클래스
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

public class Out {
	/**
	 * 캐릭터가 갈 곳을 이미지 버튼으로 보여준다.
	 * 버튼 클릭시 다른 클래스를 호출하거나 이벤트를 실행한다.
	 * 
	 */
	private Main parent;
	
	/**
	 * 필드 Out의 mutator
	 * @param parent
	 */
	public Out(Main parent) {
		this.parent = parent;
		initialize();
	}

	
	static JFrame frameOut;
	
	/**
	 * playBGM 효과음을 발생시키는 메서드
	 */
	void playBGM() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound\\sound5.wav"));
					Clip clip = AudioSystem.getClip();
					clip.stop();
					clip.open(ais);
					clip.start();
		} catch (Exception e1) {
			
		}
	}
	
	// 버튼 리스너
	private ActionListener gameListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			playBGM();
			onMiniGameButtonClick();
			}
	}; private ActionListener healListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			playBGM();
			onHealButtonClick();
		}
	}; private ActionListener walkListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			playBGM();
			onWalkButtonClick();
		}
	}; private ActionListener machineListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			playBGM();
			onMachineButtonClick();
		}
	}; private ActionListener gachaListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			playBGM();
			onGachaButtonClick();
		}
	}; private ActionListener backListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			playBGM();
			onBackButtonClick();
		}
	};
	
	
	/**
	 * 프레임을 생성하는 메서드
	 */
	private void initialize() {
		frameOut = new JFrame("외출");
		frameOut.setBounds(100, 100, 815, 635);
		frameOut.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		frameOut.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnMiniGame = new JButton();
		btnMiniGame.setBounds(176, 323, 161, 225);
		btnMiniGame.setBorderPainted(false); // 외각선 없애기
		btnMiniGame.setContentAreaFilled(false); // 내용 색 채우기 없애기
		btnMiniGame.setFocusPainted(false); // 포커스에 생기는 테두리 없애기
		btnMiniGame.addActionListener(gameListener);
		panel.add(btnMiniGame);
		
		JButton btnHeal = new JButton();
		btnHeal.setBounds(79, 92, 161, 210);
		btnHeal.setBorderPainted(false);
		btnHeal.setContentAreaFilled(false);
		btnHeal.setFocusPainted(false);
		btnHeal.addActionListener(healListener);
		panel.add(btnHeal);
		
		JButton btnWalk = new JButton();
		btnWalk.setBounds(313, 82, 195, 188);
		btnWalk.setBorderPainted(false);
		btnWalk.setContentAreaFilled(false);
		btnWalk.setFocusPainted(false);
		btnWalk.addActionListener(walkListener);
		panel.add(btnWalk);
		
		JButton btnMachine = new JButton();
		btnMachine.setBounds(592, 76, 186, 237);
		btnMachine.setBorderPainted(false);
		btnMachine.setContentAreaFilled(false);
		btnMachine.setFocusPainted(false);
		btnMachine.addActionListener(machineListener);
		panel.add(btnMachine);
		
		JButton btnBack = new JButton();
		btnBack.setBounds(648, 542, 141, 46);
		btnBack.setBorderPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setFocusPainted(false);
		btnBack.addActionListener(backListener);
		panel.add(btnBack);
		
		JButton btnGacha = new JButton("\uAC00\uCC60");
		btnGacha.setFocusPainted(false);
		btnGacha.setContentAreaFilled(false);
		btnGacha.setBorderPainted(false);
		btnGacha.setBounds(472, 323, 127, 225);
		btnGacha.addActionListener(gachaListener);
		panel.add(btnGacha);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon("image\\outbg.jpg"));
		lblNewLabel.setBounds(0, 0, 800, 600);
		panel.add(lblNewLabel);
	}
	
	// [오락실] 버튼 클릭
		private void onMiniGameButtonClick() {
			MiniGame Pframe = new MiniGame(parent);
			Pframe.Create_Frame();
		}
	// [병원] 버튼 클릭
		private void onHealButtonClick() {
			parent.state += 100;
			parent.feeling -= 30;
			parent.exp += 2;
			parent.checkEverything();
			JOptionPane.showMessageDialog(null, "이제 아프지 않아요!");
		}
	// [운동하기] 버튼 클릭
		private void onWalkButtonClick() {
			parent.state -= 40;
			parent.feeling += 20;
			parent.hp -= 10;
			parent.exp += 10;
			parent.checkEverything();
			JOptionPane.showMessageDialog(null, "산책 완료");
		}
	// [만능자판기] 버튼 클릭
		private void onMachineButtonClick() {
			frameOut.setVisible(false);
			Machine machine = new Machine(parent);
			machine.frame.setVisible(true);
		}
		
	// [가챠] 버튼 클릭
		private void onGachaButtonClick() {
			
			frameOut.setVisible(false);
			Gacha gacha = new Gacha(parent);
			gacha.frame.setVisible(true);
			
		}
		
	// [돌아가기] 버튼 클릭
		private void onBackButtonClick() {
			frameOut.setVisible(false);
			parent.frame.setVisible(true);
			
			parent.checkEverything();
		}

}
