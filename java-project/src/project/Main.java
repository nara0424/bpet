package project;

/**
 * 메인 클래스
 * <p> 다마고치를 모티브로 한 디지털 팻 육성 프로그램 </p>
 *
 * @author Gyeonghun Jo
 * @version 1.0.0
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;


import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class Main implements ActionListener, Runnable, Info, WindowListener {
	/**
	 * <h1> 삐팻 키우기 </h1>
	 * <ol>
	 * 	<li> 로그인, 회원가입 </li>
	 * 	<li> 강아지, 고양이 두 동물 선택하여 육성 가능</li>
	 * 	<li> 레벨업을 통한 펫의 외형 변화 </li>
	 * 	<li> 3x3 그림퍼즐을 통한 게임내 재화 획득 </li>
	 * 	<li> 저장, 불러오기 기능 </li>
	 * 	<li> 의상,장신구 뽑기기능 </li>
	 * 	<li> 펫에게 의류착용 및 장신구 설정 </li>
	 * </ol>
	 * 
	 * 
	 * @param hp
	 * @param exp
	 * @param state;
	 * @param level;
	 * @param feeling;
	 * @param money;
	 * @param potion;
	 * @param drink;
	 * @param pill;
	 * @param snack;
	 * @param gacha1;
	 * @param gacha2;
	 * @param gacha3;
	 * @param gacha4;
	 * @param gacha5;
	 * @param gacha6;
	 * 
	 * 
	 */

	private JLabel timerLabel; 
	String TIMESTAMP_FORMAT = "     YYYY-MM-dd a HH:mm:ss";
	private SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_FORMAT);
	private static JTextArea timeArea;
	
	JFrame frame;
	HashMap<String, Object> map;

	JLabel stateLabel;
	JLabel feelingLabel;
	JLabel characterLabel;
	JTextField lvField;
	JTextField moneyField;
	JProgressBar expBar;
	JProgressBar hpBar;
	JLabel lblPutGacha;
	
	// 실시간으로 바뀌는 값
	// Main 에서만 사용되는 변수, 저장 누르지 않으면 map에 저장하지 않음!
	int hp;
	int exp;
	int state;
	int level;
	int feeling;
	int money;
	int potion;
	int drink;
	int pill;
	int snack;
	
	int gacha1;
	int gacha2;
	int gacha3;
	int gacha4;
	int gacha5;
	int gacha6;
	
	/**
	 * playBGM 효과음을 발생시키는 메서드
	 */
	void playBGM() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound\\sound2.wav"));
					Clip clip = AudioSystem.getClip();
					clip.stop();
					clip.open(ais);
					clip.start();
		} catch (Exception e1) {
			
		}
	}
	
	/**
	 * 어플리케이션을 생성하는 메서드
	 */
	public Main() {
		initialize();
	}

	/**
	 * 어플리케이션을 실행하는 메서드
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

// 버튼 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		playBGM();
		switch (jButton.getText()) {
		case "외출": {
			onOutButtonClick();
			break;
		}case "놀아주기": {
			play(); break;
		}case "밥먹기": {
			eat(); break;
		}case "잠자기": {
			sleep(); break;
		}case "아이템창": {
			onItemButtonClick(); break;
		}case "결과보기": {
			checkFeeling();
			checkState();
			
			break;
		}case "저장하기": {
			onSaveButtonClick(); break;
		}case "불러오기": {
			onLoadButtonClick(); break;
		}default:
		}
	}


	/**
	 * Frame을 생성하는 메서드
	 * 
	 */
	private void initialize() {
		
		map = Temp.loadMap(CURRENT_USER.getId());

		hp = (int) map.get(Info.HP);
		exp = (int) map.get(Info.EXP);
		state = (int) map.get(Info.STATE);
		level = (int) map.get(Info.LEVEL);
		feeling = (int) map.get(Info.FEELING);
		money = (int) map.get(Info.MONEY);
		potion = (int) map.get(Info.POTION);
		drink = (int) map.get(Info.DRINK);
		pill = (int) map.get(Info.PILL);
		snack = (int) map.get(Info.SNACK);
		
		

		gacha1 = (int) map.get(Info.GACHA_1);
		gacha2 = (int) map.get(Info.GACHA_2);
		gacha3 = (int) map.get(Info.GACHA_3);
		gacha4 = (int) map.get(Info.GACHA_4);
		gacha5 = (int) map.get(Info.GACHA_5);
		gacha6 = (int) map.get(Info.GACHA_6);
		
		
		hp();
		
		frame = new JFrame("삐펫 키우기");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(this);
		frame.setLocationRelativeTo(null);
		timeArea = new JTextArea(); // 컴포넌트 생성
		
		if (frame.isVisible() == true) {
			checkEverything();
		}
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(new Color(0, 0, 0, 0));

		
		JButton btnItem = new JButton("아이템창");
		btnItem.setBounds(676, 397, 98, 62);
		btnItem.setForeground(Color.BLACK);
		btnItem.setBackground(Color.ORANGE);
		btnItem.addActionListener(this);
		btnItem.setContentAreaFilled(false);
		panel.setLayout(null);
		panel.add(btnItem);

		
		// 이름
		String name = (String) map.get(Info.NAME); // 로그인 정보에서 "이름" 받아오기
		JLabel nameLabel = new JLabel(name);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(88, 274, 109, 31);
		nameLabel.setFont(new Font("나눔고딕", Font.BOLD, 16));
		panel.add(nameLabel);

		// 레벨
		lvField = new JTextField();
		lvField.setEditable(false);
		lvField.setBounds(98, 302, 96, 21);
		panel.add(lvField);
		lvField.setColumns(10);

		
		// 경험치 게이지 (expBar)
		expBar = new JProgressBar();
		expBar.setForeground(Color.ORANGE);
		expBar.setBounds(88, 333, 146, 25);
		expBar.setStringPainted(true);
		panel.add(expBar);

		
		// 체력 게이지 (hpBar)
		hpBar = new JProgressBar();
		hpBar.setForeground(Color.ORANGE);
		hpBar.setStringPainted(true);
		hpBar.setBounds(88, 387, 146, 25);
		panel.add(hpBar);
	

		// 시간 설정
		timerLabel = new JLabel();
		timerLabel.setBounds(12, 523, 261, 15);
		panel.add(timerLabel);
		
		// 재화 ~원
		moneyField = new JTextField();
		moneyField.setBounds(689, 540, 96, 23);
		moneyField.setEditable(false);
		panel.add(moneyField);
		moneyField.setColumns(10);
		

		// 가챠아이템 적용시
		lblPutGacha = new JLabel();
		lblPutGacha.setBounds(332, 258, 245, 215);
		panel.add(lblPutGacha);


		// 메인 캐릭터 이미지!
		characterLabel = new JLabel();
		characterLabel.setBounds(332, 258, 245, 215);
//		mainImgLabel.setIcon(new ImageIcon("image\\imageMain.png"));
		panel.add(characterLabel);
		
		
		// [놀아주기]
		JButton btnPlay = new JButton("놀아주기");
		btnPlay.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnPlay.setBackground(Color.ORANGE);
		btnPlay.setBounds(204, 66, 85, 45);
		btnPlay.addActionListener(this);
		btnPlay.setContentAreaFilled(false);
		panel.add(btnPlay);

		// [밥먹기]
		JButton btnEat = new JButton("밥먹기");
		btnEat.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnEat.setBackground(Color.ORANGE);
		btnEat.setBounds(320, 66, 81, 45);
		btnEat.addActionListener(this);
		btnEat.setContentAreaFilled(false);
		panel.add(btnEat);
		
		// [잠자기]
		JButton btnSleep = new JButton("잠자기");
		btnSleep.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnSleep.setBackground(Color.ORANGE);
		btnSleep.setBounds(435, 66, 81, 45);
		btnSleep.addActionListener(this);
		btnSleep.setContentAreaFilled(false);
		panel.add(btnSleep);
		
		// [외출]
		JButton btnOut = new JButton("외출");
		btnOut.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnOut.setBackground(Color.ORANGE);
		btnOut.setBounds(553, 66, 80, 45);
		btnOut.addActionListener(this);
		btnOut.setContentAreaFilled(false);
		panel.add(btnOut);
		
		// 현재 기분 이미지 출력 라벨 ( 좋음 - 보통 - 나쁨 )
		feelingLabel = new JLabel();
		feelingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		feelingLabel.setBounds(320, 166, 50, 50);
		panel.add(feelingLabel);
		checkFeeling(); // 현재 기분 실시간 업데이트

		// 현재 상태 출력 ( 정상 - 배고픔 - 아픔 )
		stateLabel = new JLabel();
		stateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stateLabel.setBounds(519, 180, 90, 68);
		panel.add(stateLabel);
		checkState();


		// 메뉴항목 추가
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 786, 23);
		JMenuItem[] menuItem = new JMenuItem[3];
		String[] itemTitle = { "저장", "불러오기", "종료" };
		menuBar.setBackground(Color.WHITE);
		panel.add(menuBar);
		JMenu menu = new JMenu("MENU");
		

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("image\\background.jpg"));
		lblNewLabel.setBounds(0, 0, 800, 580);
		panel.add(lblNewLabel);

		// 메뉴 클릭시 사용할 리스너 클래스 작성
		ActionListener menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				switch (s) {
				case "저장":
					onSaveButtonClick();
					break;
				case "불러오기":
					onLoadButtonClick();
					break;
				case "종료":
					int n = JOptionPane.showConfirmDialog(frame, "저장하시겠습니까?","종료", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.OK_OPTION) {
						saveData();
					}
					else System.exit(0);
					break;
				}
			}
		};
		// 메뉴에 아이템 추가
		for (int i = 0; i < menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(menuListener);
			menu.add(menuItem[i]);
		}
		// 메뉴바에 메뉴 추가
		menuBar.add(menu);
		
		
		// 기본값 체크 
		checkEverything();
		
		
	}
	
//////////////// 레이아웃 끝 ////////////////////////////
	/**
	 * 스레드 클래스
	 * 
	 * @author Jiyoon
	 *
	 */
class MyRunnable implements Runnable {
	/**
	 * 100초에 한번씩 스레드를 실행한다.
	 */
	@Override
	public void run() {
		for(int i = 1; i <= 10; ++i) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
		
	
	// [놀아주기]
	void play() {
		feeling += 50;
		state -= 10;
		hp -= 1;
		exp += 60;
		money += 100;
		
		checkEverything();
	}
	
	// [밥먹기] 버튼 클릭
	void eat() {
		state += 30;
		hp += 20;
		exp += 1;
		money += 100;

		checkEverything();
	}
	
	// [잠자기] 버튼 클릭
	void sleep() {
		
		hp += 60;
		exp += 1;
		state -= 50;
		money += 100;
		
		checkEverything();
		
	}

	// [외출] 버튼 클릭
	private void onOutButtonClick() {
		
		Out out = new Out(this);
		frame.setVisible(false);
		out.frameOut.setVisible(true);
	}
	
	// [save]
	public void onSaveButtonClick() {
		saveData();
	}

	// [load]
	public void onLoadButtonClick() {
		loadData();
	}

	// [아이템창] 버튼 클릭

	private void onItemButtonClick() {
		
		Item itemFrame = new Item(this);
		frame.setVisible(false);
		itemFrame.frame.setVisible(true);

	}


	
/////////// exp, level, hp, feeling, state CHECK!!!!
	
	// exp 실시간 체크해서 expBar에 내용 업데이트 + level up !
	void levelUp() {
		if (exp >= 100) {
			exp -= 100;
			level++;
		}
	}
	
	// HP 실시간 체크해서 hpBar에 내용 업데이트
	void checkHp() {
		if (hp > MAX_HP) {
			hp = MAX_HP;
		}
		if (hp <= 0) {
			hp = 0;
		}
	}
	
	// 상태 체크하기 - 상태 실시간 업데이트
	void checkState() {
		if (state > MAX_STATE)
			state = MAX_STATE;
		if (state <= 0) {
			state = 0;
		}

		String nowState;
		if (state >= 40) {
			nowState = "좋아요!";
		} else if (state < 40 && state >= 20) {
			nowState = "배고파요..";
		} else {
			nowState = "아파요ㅠㅠ";
		}
		stateLabel.setText(nowState);
	}

	// 기분 체크하기 - 기분 실시간 업데이트
	void checkFeeling() {
		if (feeling > MAX_FEELING)
			feeling = MAX_FEELING;
		if (feeling <= 0) {
			feeling = 0;
		}
		String nowFeeling;
		if (feeling >= 60) // 기분 - (좋음)
		{
			nowFeeling = "image\\feel_good.png";
		} else if (60 > feeling && feeling >= 30) // 기분 (보통)
		{
			nowFeeling = "image\\feel_soso.png";
		} else { // 기분 (나쁨)
			nowFeeling = "image\\feel_bad.png";
		}
		feelingLabel.setIcon(new ImageIcon(nowFeeling));
	}

	void checkGacha() {
		if (gacha1 == 2) {
			lblPutGacha.setIcon(new ImageIcon("image\\put_gacha1.png"));
			gacha1 --;
		} if (gacha2 == 2) {
			lblPutGacha.setIcon(new ImageIcon("image\\put_gacha2.png"));
			gacha2 --;
		} if (gacha3 == 2) {
			lblPutGacha.setIcon(new ImageIcon("image\\put_gacha3.png"));
			gacha3 --;
		} if (gacha4 == 2) {
			lblPutGacha.setIcon(new ImageIcon("image\\put_gacha4.png"));
			gacha4 --;
		} if (gacha5 == 2) {
			lblPutGacha.setIcon(new ImageIcon("image\\put_gacha5.png"));
			gacha5 --;
		} if (gacha6 == 2) {
			lblPutGacha.setIcon(new ImageIcon("image\\put_gacha6.png"));
			gacha6 --;
		}
	}
	
	
	// 메인 이미지 체크
	void checkCharacter() {
		String pet = (String) map.get(Info.PET);
		String nowCharacter = "cat";
		//선택 펫이 고양이일때 성장 이미지
		if (pet.equals("cat")) {
//			int level = (int) map.get(Info.LEVEL);
			if(level>=1 && level <= 9) {
				nowCharacter = "image\\cat1.png";
			}
			else if(level>=10 && level <20) {
				nowCharacter = "image\\cat2.png";
			}
			else if(level>=20) {
				nowCharacter = "image\\cat3.png";
			}
		}
		//선택 펫이 강아지 일때 성장 이미지
		if (pet.equals("dog")) {
//			int level = (int) map.get(Info.LEVEL);
			if(level>=1 && level <= 9) {
				nowCharacter = "image\\dog1.png";
				}
				else if(level>=10 && level <20) {
					nowCharacter = "image\\dog2.png";
				}
				else if(level>=20) {
					nowCharacter = "image\\dog3.png";
				}
		}
		characterLabel.setIcon(new ImageIcon(nowCharacter));
	}
	
	
	// 레벨 체크하기 - 최대 레벨 초과할수없음
	void checkLevel() {
		if (level > MAX_LEVEL) {
			level = MAX_LEVEL;
			exp = MAX_EXP;
		}
	}
	
	
	//////////////////////////////// 현재상태 모두 확인!!!!!!
	public void checkEverything() {

		levelUp();
		checkHp();

		lvField.setText("레벨 : " + level); // 현재 레벨 표시
		expBar.setValue(exp); // 현재 경험치 표시
		hpBar.setValue(hp); // 체력
		moneyField.setText(money + " 원"); // 재화

		checkGacha();
		checkFeeling();	// 기분
		checkState(); // 상태
		endGame(); // 게임 종료
		checkLevel(); // 레벨업 체크
		checkCharacter(); // 메인 캐릭터 체크 - 레벨마다 변경

	}
	
	
/////////// 실시간 업데이트 ( 실시간으로 체력, 기분, 상태 감소)
	void minus() {
			hp--;
			state--;
			feeling--;
			checkEverything();
	}
	
	void hp() {
		new Timer(100000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				minus();
				}
		}).start();
		}


	// 체력 0 되면 게임 종료
	/**
	 * endGame 체력이 0이되면 프로그램을 종료한다.
	 * @return returnValue 
	 */
	public boolean endGame() {
		boolean returnValue = true;
		if (hp == 0) {
			JOptionPane.showMessageDialog(null, "Game Over..");
			System.exit(0);
		}
		return returnValue;
	}

	void saveData() {
		map.put(Info.EXP, exp);
		map.put(Info.HP, hp);
		map.put(Info.POTION, potion);
		map.put(Info.MONEY, money);
		map.put(Info.LEVEL, level);
		map.put(Info.FEELING, feeling);
		map.put(Info.STATE, state);
		map.put(Info.PILL, pill);
		map.put(Info.DRINK, drink);
		map.put(Info.SNACK,snack);
		

		map.put(Info.GACHA_1, gacha1);
		map.put(Info.GACHA_2, gacha2);
		map.put(Info.GACHA_3, gacha3);
		map.put(Info.GACHA_4, gacha4);
		map.put(Info.GACHA_5, gacha5);
		map.put(Info.GACHA_6, gacha6);
		
		Temp.saveMap(map);
	}
	
	void loadData() {
		
		map = Temp.loadMap(CURRENT_USER.getId());
		
		hp = (int) map.get(Info.HP);
		exp = (int) map.get(Info.EXP);
		state = (int) map.get(Info.STATE);
		level = (int) map.get(Info.LEVEL);
		feeling = (int) map.get(Info.FEELING);
		money = (int) map.get(Info.MONEY);
		potion = (int) map.get(Info.POTION);
		drink = (int) map.get(Info.DRINK);
		pill = (int) map.get(Info.PILL);
		snack = (int) map.get(Info.SNACK);
		
		gacha1 = (int) map.get(Info.GACHA_1);
		gacha2 = (int) map.get(Info.GACHA_2);
		gacha3 = (int) map.get(Info.GACHA_3);
		gacha4 = (int) map.get(Info.GACHA_4);
		gacha5 = (int) map.get(Info.GACHA_5);
		gacha6 = (int) map.get(Info.GACHA_6);
		
		checkEverything();
	}
	
	
	@Override
	public void run() {
		
	}
	//////////////////////////////////////////////////////////////
	// WindowListener 오버라이드

	@Override
	public void windowOpened(WindowEvent e) {
		Temp.loadMap(CURRENT_USER.getId());
	}

	// 닫기 버튼 눌렀을때 
	@Override
	public void windowClosing(WindowEvent e) {
		int n = JOptionPane.showConfirmDialog(frame, "저장하시겠습니까?","종료", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.OK_OPTION) {
			saveData();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
	
	
	

	// WindowListener 오버라이드 끝
	//////////////////////////////////////////////////////////////
}