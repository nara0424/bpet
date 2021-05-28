package project;
/**
 * 로그인 클래스
 * 
 * @author Gyeonghun Jo
 */

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Login {
	/**
	 * 
	 * 로그인 페이지를 생성한다.
	 * 유저로부터 id, password 정보를 textField에 받고, 일치하면 Main 클래스를 실행한다.
	 * 
	 */
	
	JFrame frame;
	private JTextField idField;
	private JTextField passwordField;
	HashMap<String, Object> map;
	
	String id;
	String name;
	String psw;


	/**
	 * 어플리케이션을 생성하는 메서드
	 */
	public Login() {
		initialize();
	}
	
	/**
	 * 어플리케이션을 실행하는 메서드
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 프레임을 생성하는 메서드
	 */
	private void initialize() {
		frame = new JFrame("삐팻 키우기");
		frame.setBounds(100, 100, 465, 335);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		idField = new JTextField();
		idField.setBounds(219, 140, 121, 21);
		panel.add(idField);
		idField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(219, 174, 121, 21);
		panel.add(passwordField);
		passwordField.setColumns(10);
		
		JButton btnLogin = new JButton();
		btnLogin.setBounds(332, 244, 79, 30);
		btnLogin.addActionListener(okListener);
		btnLogin.setBorderPainted(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setFocusPainted(false);
		panel.add(btnLogin);
		
		JButton btnSignup = new JButton();
		btnSignup.setBounds(36, 244, 114, 30);
		btnSignup.addActionListener(signupListener);
		btnSignup.setBorderPainted(false);
		btnSignup.setContentAreaFilled(false);
		btnSignup.setFocusPainted(false);
		panel.add(btnSignup);
		

		// 배경 이미지 라벨
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 450, 300);
		lblNewLabel_1.setIcon(new ImageIcon("image\\loginbg.jpg"));
		panel.add(lblNewLabel_1);
		
	}
	
	/**
	 * 효과음을 발생시키는 메서드
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
	
	
	// [회원가입] 버튼 리스너
		private ActionListener signupListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playBGM();
				new Signup();
				Signup.frame.setVisible(true);
			}
		};
		
	// [로그인] 버튼 리스너
		private ActionListener okListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playBGM();
				map = loadMap();
				checkId();
				
				String psw3 = (String)map.get("psw");
				
				if (psw.equals(psw3)) {
					HashMap<String, Object> map = Temp.loadMap(id);
					Info.CURRENT_USER.setId((String)map.get(Info.ID));
					Info.CURRENT_USER.setName((String)map.get(Info.NAME));
					Info.CURRENT_USER.setPassword((String)map.get(Info.PSW));
					loadMap();
					OpenFrame();
				}
				else {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다!");
				}
				
			}
		};
		
		/**
		 * 메인 클래스를 호출하는 메서드
		 */
		void OpenFrame() {
			frame.setVisible(false);
			try {
				Main window = new Main();
				window.frame.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		/**
		 * textField에 입력된 정보가 hashmap의 정보와 일치하는지 확인하는 메서드
		 */
		void checkId() {
			id = idField.getText();
			psw = passwordField.getText();
		}
		
		/**
		 * loadMap : 유저 아이디를 통해 hashmap의 정보를 불러온다.
		 * @return map
		 */
		HashMap<String, Object> loadMap() {
			HashMap<String, Object> map = null;
			checkId();
			try (FileInputStream fOut = new FileInputStream(id+".dat");
					ObjectInputStream oOut = new ObjectInputStream(fOut)) {
				map = (HashMap<String, Object>) oOut.readObject();
			} catch (FileNotFoundException e) {
				map = new HashMap<String, Object>();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return map;
		}
}
