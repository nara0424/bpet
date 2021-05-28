package project;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//프레임 생성과 이미지 불러오기 및 쪼개기

//GUI Swing
/**
 * MiniGame 클래스는 미니 퍼즐 게임을
 *  실행시키는 클래스 이다.
 * @author Gyeonghun Jo
 *
 */
@SuppressWarnings("serial")
public class MiniGame extends JFrame implements MouseListener{
	
	private Main parent;
	
	public BufferedImage ori_img;	// 원본 이미지
	public BufferedImage[][] sub_img = new BufferedImage[3][3];	// 분할 이미지 버퍼
	public int[][] sub_index = new int[3][3];					// 분할 이미지 번호
	Graphics g;					// paint()를 수행할 Graphics 객체	
	int B_row, B_col;			// 빈 칸의 인덱스 
	int M_count;				// 조각 옮긴 수
	long S_Timer;				// 시작 시간
	public long Timer;			// 걸린 시간
	
	/** 
	 *  Main을 불러오는 부모 생성자 
	 */
	public MiniGame(Main parent) {
		this.parent = parent;
	}
	
	

	/** 
	 *  프래임을 생성 하는 매서드
	 */
	public void Create_Frame(){
		setLayout(null);
		
		// UI
		setSize(600,400);
		setTitle("그림 맞추기 게임");
		setBackground(Color.BLUE);
		setLocationRelativeTo(null);
		
		
		Create_Image();		// 이미지 생성 메소드 호출
		Sub_Image(9);		// 이미지 분할 메소드 호출
		M_count = 200;		// 조각 옮긴 수 초기화
		Timer = 200;
		S_Timer = System.currentTimeMillis();	// 시작 시간 저장
		addMouseListener(this);		// MouseListener 연결
		setVisible(true);	// paint() 수행
		
		
		// 창종료 이벤트(프로세스 완전 종료)
//		addWindowListener(new WindowAdapter(){
//			public void windowClosing(WindowEvent we){
//				System.exit(0);
//			}
//		});
	}
	
	/** 
	 *  퍼즐의 그림을 생성함
	 */
	public void Create_Image(){
		try {
			String[] arr = {"pet.jpg","pet2.jpg","pet3.jpg","pet4.jpg"};
			int rand = (int)(Math.random() * arr.length);
			ori_img = ImageIO.read(new File("image\\" + arr[rand]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 
	 *  퍼즐의 그림을 나누어서 배열에 저장하는 매서드
	 */
	public void Sub_Image(int m_piece){
		int width = ori_img.getWidth(this);		// 가로분할길이
		int height = ori_img.getHeight(this);	// 세로분할길이
		int R_number[] = new int[9];			// 난수 저장 배열
		int number = 0;							// 난수 저장 버퍼
		boolean compare;						// 난수 비교
		int row, col;							// 분할 이미지 저장 인덱스
		int R_index = 0;						// 난수 배열 인덱스
		
		if (m_piece == 9){			// 9조각
			
			for (int i=0 ; i<9 ; i++){	// 난수 저장 배열 초기화		
				R_number[i] = 0;
			}
			R_number[0] = Random_Setting();	// 난수 생성
			for (int j=1 ; j<9 ; j++){	// 난수 저장 배열에 1~9의 서로 다른 수 저장
				compare = true;
				number = Random_Setting();
				for (int z=0 ; z<9 ; z++){
					if (R_number[z] == number){
						compare = false;
					}
				}
				if (compare == true){
					R_number[j] = number;
				}
				else{
					j--;
				}
			}
			width /= 3;
			height /= 3;
			
			for (row=0 ; row<3 ; row++){	// 난수 저장 배열값에 따른 랜덤 조각 이미지 저장
				for (col=0 ; col<3 ; col++){
					switch (R_number[R_index]){
					case 1:
						sub_img[row][col] = ori_img.getSubimage(0,  0, width, height);
						sub_index[row][col] = 1;
						break;
					case 2:
						sub_img[row][col] = ori_img.getSubimage(width,  0, width, height);
						sub_index[row][col] = 2;
						break;
					case 3:
						sub_img[row][col] = ori_img.getSubimage(width * 2,  0, width, height);
						sub_index[row][col] = 3;
						break;
					case 4:
						sub_img[row][col] = ori_img.getSubimage(0,  height, width, height);
						sub_index[row][col] = 4;
						break;
					case 5:
						sub_img[row][col] = ori_img.getSubimage(width,  height, width, height);
						sub_index[row][col] = 5;
						break;
					case 6:
						sub_img[row][col] = ori_img.getSubimage(width * 2,  height, width, height);
						sub_index[row][col] = 6;
						break;
					case 7:
						sub_img[row][col] = ori_img.getSubimage(0,  height * 2, width, height);
						sub_index[row][col] = 7;
						break;
					case 8:
						sub_img[row][col] = ori_img.getSubimage(width,  height * 2, width, height);
						sub_index[row][col] = 8;
						break;
					case 9:		// 퍼즐 맞추기를 위한 빈 칸 
						sub_index[row][col] = 9;
						B_row = row;
						B_col = col;
						break;
					default:
						System.out.println("범위 밖 난수 생성 오류");
						break;
					}
					R_index++;
				}
			}
		}
	}	// Sub_Image
	
	/** 
	 *  퍼즐의 그림을 나누어서 배열에 저장한 값들을
	 *  임의 값으로 만드는 매서드
	 */	
	public int Random_Setting(){
		int number = (int)(Math.random()*9 + 1);	// 1~9난수 생성
		return number;
	}
	/** 
	 *  임의로 저장된 값을 지정된 좌표로 전송해
	 *  이미지를 출력하는 매서드
	 */
	public void paint(Graphics g){
		if (ori_img != null){
			// drawImage(객체, x 좌표, y 좌표, width, height, Observer)
			g.drawImage(ori_img, 30, 50, 100, 100, this);
			g.drawImage(sub_img[0][0], 180, 50, 100, 100, this);
			g.drawImage(sub_img[0][1], 280, 50, 100, 100, this);
			g.drawImage(sub_img[0][2], 380, 50, 100, 100, this);
			g.drawImage(sub_img[1][0], 180, 150, 100, 100, this);
			g.drawImage(sub_img[1][1], 280, 150, 100, 100, this);
			g.drawImage(sub_img[1][2], 380, 150, 100, 100, this);
			g.drawImage(sub_img[2][0], 180, 250, 100, 100, this);
			g.drawImage(sub_img[2][1], 280, 250, 100, 100, this);
			g.drawImage(sub_img[2][2], 380, 250, 100, 100, this);
		}
		else{
			System.out.println("이미지 로드 오류");
		}
	}
	/** 
	 *  조각이 이동 할 수있는지 판단하는 매서드
	 */	
	public boolean Correct_clicked(int P_index){
		switch (P_index){
		case 1:
			if (sub_index[0][1] == 9 || sub_index[1][0] == 9){
				return true;
			}
			break;
		case 2:
			if (sub_index[0][0] == 9 || sub_index[0][2] == 9 || sub_index[1][1] == 9){
				return true;
			}
			break;
		case 3:
			if (sub_index[0][1] == 9 || sub_index[1][2] == 9){
				return true;
			}
			break;
		case 4:
			if (sub_index[0][0] == 9 || sub_index[1][1] == 9 || sub_index[2][0] == 9){
				return true;
			}
			break;
		case 5:
			if (sub_index[0][1] == 9 || sub_index[1][0] == 9 || sub_index[1][2] == 9 || sub_index[2][1] == 9){
				return true;
			}
			break;
		case 6:
			if (sub_index[0][2] == 9 || sub_index[1][1] == 9 || sub_index[2][2] == 9){
				return true;
			}
			break;
		case 7:
			if (sub_index[1][0] == 9 || sub_index[2][1] == 9){
				return true;
			}
			break;
		case 8:
			if (sub_index[1][1] == 9 || sub_index[2][0] == 9 || sub_index[2][2] == 9){
				return true;
			}
			break;
		case 9:
			if (sub_index[1][2] == 9 || sub_index[2][1] == 9){
				return true;
			}
			break;
		default:
			System.out.println("조각 배열 오류");
			break;
		}
		return false;
	}
	/** 
	 *  퍼즐이 옮겨진후 남은 칸을
	 *  흰색으로 만드는 매서드
	 */
	public BufferedImage Make_White(){
		BufferedImage m_white = null;
		try {
			m_white = ImageIO.read(new File("image\\white.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return m_white;
	}
	/** 
	 *  조각을 이동하게 만드는 매서드
	 */
	public void Change_Image(int x, int y, int index){
		if (Correct_clicked(index)){	// 클릭한 조각과 인접한 곳에 빈 칸이 있을 경우
			sub_img[B_row][B_col] = sub_img[x][y];	
			sub_img[x][y] = Make_White();	// 이미지 이동 및 빈 칸 이미지 저장
			sub_index[B_row][B_col] = sub_index[x][y];
			sub_index[x][y] = 9;			// 인덱스 교환
			B_row = x;				
			B_col = y;						// 빈 칸 인덱스 갱신
			M_count++;						// 조각 옮긴 수 + 1
		}
	}
	/** 
	 *  조각의 완성을 체크하는 메서드
	 */
	public boolean Check_Image(){
		if (sub_index[0][0] == 1 && sub_index[0][1] == 2 && sub_index[0][2] == 3 &&
				sub_index[1][0] == 4 && sub_index[1][1] == 5 && sub_index[1][2] == 6 &&
				sub_index[2][0] == 7 && sub_index[2][1] == 8 && sub_index[2][2] == 9){
			return true;
		}
		else{
			return false;
		}
	}
	
	/** 
	 *  게임을 클리어를 확인하고
	 *  걸린 시간, 클릭수를 측정하여
	 *  재화를 주는 매서드
	 */
	public void End_Game(){
		int width = ori_img.getWidth(this)/3;		// 가로분할길이
		int height = ori_img.getHeight(this)/3;	// 세로분할길이
		sub_img[2][2] = ori_img.getSubimage(width * 2,  height * 2, width, height);
		repaint();		// 그림 완성
		// MessageDialog
		long E_Timer = System.currentTimeMillis();
		Timer = (E_Timer - S_Timer) / 1000;
		int earnmoney;
		if (Timer<=60) {
			parent.money += 50000;
			earnmoney = 50000;
		}
		else if (Timer <= 150 && Timer > 60) {
			parent.money += 20000;
			earnmoney = 20000;
		}
		else {
			parent.money += 10000;
			earnmoney = 10000;
		}
		JOptionPane.showMessageDialog(this, 
						"클릭 수 : " 
						+ M_count 
						+ "번, 걸린 시간 : " 
						+ Timer + "초" 
						+ " \n 얻은 용돈 : " 
						+ earnmoney + "원",
			"Complete Message", JOptionPane.PLAIN_MESSAGE);
		
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	/** 
	 *  마우스 클릭 이벤트 처리하는 매서드
	 */
	// 마우스 클릭 이벤트 처리
	@Override
	public void mousePressed(MouseEvent e) {
		int inX = e.getX();		// 마우스 x 좌표
		int inY = e.getY();		// 마우스 y 좌표
		if (inX > 180 && inX < 280 && inY > 50 && inY < 150){		// [0][0]
			Change_Image(0,0,1);	// 조각 이미지 교환 처리 함수 
		}
		else if(inX > 280 && inX < 380 && inY > 50 && inY < 150){	// [0][1]
			Change_Image(0,1,2);	// 조각 이미지 교환 처리 함수
		}
		else if(inX > 380 && inX < 480 && inY > 50 && inY < 150){	// [0][2]
			Change_Image(0,2,3);	// 조각 이미지 교환 처리 함수
		}
		else if(inX > 180 && inX < 280 && inY > 150 && inY < 250){	// [1][0]
			Change_Image(1,0,4);	// 조각 이미지 교환 처리 함수
		}
		else if(inX > 280 && inX < 380 && inY > 150 && inY < 250){	// [1][1]
			Change_Image(1,1,5);	// 조각 이미지 교환 처리 함수
		}
		else if(inX > 380 && inX < 480 && inY > 150 && inY < 250){	// [1][2]
			Change_Image(1,2,6);	// 조각 이미지 교환 처리 함수
		}
		else if(inX > 180 && inX < 280 && inY > 250 && inY < 350){	// [2][0]
			Change_Image(2,0,7);	// 조각 이미지 교환 처리 함수
		}
		else if(inX > 280 && inX < 380 && inY > 250 && inY < 350){	// [2][1]
			Change_Image(2,1,8);	// 조각 이미지 교환 처리 함수
		}
		else if(inX > 380 && inX < 480 && inY > 250 && inY < 350){	// [2][2]
			Change_Image(2,2,9);	// 조각 이미지 교환 처리 함수
		}
		repaint();			// paint()메소드 실행
		if (Check_Image()){	// 조각 이미지 완성 체크
			End_Game();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}