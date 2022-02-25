package game2048new;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NewGame extends JFrame {

	JFrame jFrame;
	private JPanel contentPane;
	// 用于存放数据的数组
	private int Numbers[][] = new int[4][4];
	int score = 0;// 积分
	private boolean isWin = false;
	private Random rand = new Random();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGame frame = new NewGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewGame() {

		jFrame = this;
		setResizable(false);
		setTitle("2048\u5C0F\u6E38\u620F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();

		panel.setBounds(0, 0, 494, 465);
		contentPane.add(panel);
		panel.setLayout(null);

		// 分值信息
		JLabel label_core = new JLabel("\u79EF\u5206:0");
		label_core.setBounds(340, 39, 72, 18);
		panel.add(label_core);

		JButton btnNewButton = new JButton("");
		btnNewButton.setFocusable(false);// 失去焦点，也就是不用获取焦点
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isWin = false;
				for (int i = 0; i < 4; i++)
					for (int j = 0; j < 4; j++)
						Numbers[i][j] = 0;

				score = 0;// 保证每次重置游戏都是0分开始
				label_core.setText("分数：" + score);
				int r1 = rand.nextInt(4);// 0.1.2.3
				int r2 = rand.nextInt(4);
				int c1 = rand.nextInt(4);
				int c2 = rand.nextInt(4);

				while (r1 == r2 && c1 == c2) {
					r2 = rand.nextInt(4);
					c2 = rand.nextInt(4);
				}

				// 生成数字（2或者4）
				int value1 = rand.nextInt(2) * 2 + 2;// 2/4
				int value2 = rand.nextInt(2) * 2 + 2;

				// 把数字存进对应的位置
				Numbers[r1][c1] = value1;
				Numbers[r2][c2] = value2;
				repaint();
			}
		});
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setIcon(new ImageIcon(NewGame.class.getResource("/game2048new/start.png")));
		btnNewButton.setBounds(122, 26, 193, 53);
		panel.add(btnNewButton);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				/*
				 * 1. 判断游戏是否已结束 没有：继续 有：重新游戏 2. 判断用户点击的是否上下左右 3. 如果按得左： 1》排0
				 * 2》合并 3》排0 4. 判断游戏是否有2048 ，有————》赢 没有---》
				 *
				 *5. 判断是否棋盘满了
				 * 满---》判断相邻的是否有相同的数 有：继续游戏 没有：游戏失败 没满---> 空余空间生成2、4
				 *
				 */
				int count=0;//记录棋盘是否有移动的值
				int numCount=0;//记录棋盘有多少非0的值；
				int nearCount=0;//记录棋盘中领近的位置是否有相同的个数

				if (isWin == false) {
					switch (e.getKeyCode()) {
						case 37:
							/*
							 * 如果按得左： 1》排0 2》合并 3》排0
							 */
							for (int i = 0; i < Numbers.length; i++) {
								for (int j = 0; j < Numbers[i].length; j++) {
									if (Numbers[i][j] != 0) {
										for (int k = 0; k < j; k++) {
											if (Numbers[i][k] == 0) {
												int temp = Numbers[i][j];
												Numbers[i][k] = temp;
												Numbers[i][j] = 0;
												count++;

												break;
											}

										}
									}
								}
							}

							for (int i = 0; i < Numbers.length; i++) {
								for (int j = 0; j < Numbers[i].length - 1; j++) {
									if (Numbers[i][j]!=0 && Numbers[i][j]==Numbers[i][j+1]) {
										Numbers[i][j] += Numbers[i][j + 1];
										Numbers[i][j + 1] = 0;
										count++;
										score += Numbers[i][j];
										if (Numbers[i][j] == 2048) {
											isWin = true;
										}
									}
								}
							}

							for (int i = 0; i < Numbers.length; i++) {
								for (int j = 0; j < Numbers[i].length; j++) {
									if (Numbers[i][j] != 0) {
										for (int k = 0; k < j; k++) {
											if (Numbers[i][k] == 0) {
												int temp = Numbers[i][j];
												Numbers[i][k] = temp;
												Numbers[i][j] = 0;
												count++;

												break;
											}
										}
									}
								}
							}
							count++;
							break;
						case 38:
							for (int j = 0; j < Numbers.length; j++) {
								for (int i = 0; i < Numbers[j].length; i++) {
									if (Numbers[i][j] != 0) {// 00 10
										for (int k = 0; k < i; k++) {
											if (Numbers[k][j] == 0) {
												int temp = Numbers[i][j];
												Numbers[k][j] = temp;
												Numbers[i][j] = 0;
												count++;
												break;
											}
										}
									}
								}
							}
							for (int j = 0; j < Numbers.length; j++) {
								for (int i = 0; i < Numbers[j].length-1; i++) {
									if(Numbers[i][j]==Numbers[i+1][j] && Numbers[i][j]!=0){
										Numbers[i][j]+=Numbers[i+1][j];
										Numbers[i+1][j]=0;
										count++;
										score+=Numbers[i][j];
										if(Numbers[i][j]==2048){
											isWin=true;
										}
									}
								}
							}
							for (int j = 0; j < Numbers.length; j++) {
								for (int i = 0; i < Numbers[j].length; i++) {
									if (Numbers[i][j] != 0) {// 00 10
										for (int k = 0; k < i; k++) {
											if (Numbers[k][j] == 0) {
												int temp = Numbers[i][j];
												Numbers[k][j] = temp;
												Numbers[i][j] = 0;
												count++;
												break;
											}
										}
									}
								}
							}
							count++;
							break;
						case 39:
							for (int i = 0; i <Numbers.length; i++) {
								for (int j = 3; j >= 0; j--) {
									if (Numbers[i][j] != 0) {
										for (int k = 3; k >= j; k--) {
											if (Numbers[i][k] == 0) {
												int temp = Numbers[i][j];
												Numbers[i][k] = temp;
												Numbers[i][j] = 0;
												count++;
												break;
											}
										}
									}
								}
							}
							for (int i = 0; i < Numbers.length; i++) {
								for (int j = 3; j > 0; j--) {
									if (Numbers[i][j]!=0 && Numbers[i][j]==Numbers[i][j-1]) {
										Numbers[i][j] += Numbers[i][j-1];
										Numbers[i][j-1] = 0;
										count++;
										score += Numbers[i][j];
										if (Numbers[i][j] == 2048) {
											isWin = true;
										}
									}
								}
							}
							for (int i = 0; i <Numbers.length; i++) {
								for (int j = 3; j >= 0; j--) {
									if (Numbers[i][j] != 0) {
										for (int k = 3; k >= j; k--) {
											if (Numbers[i][k] == 0) {
												int temp = Numbers[i][j];
												Numbers[i][k] = temp;
												Numbers[i][j] = 0;
												count++;
												break;
											}
										}
									}
								}
							}
							count++;
							break;
						case 40:
							for (int j = 0; j < Numbers.length; j++) {
								for (int i = 3; i >= 0; i--) {
									if (Numbers[i][j] != 0) {
										for (int k = 3; k > i; k--) {
											if (Numbers[k][j] == 0) {
												int temp = Numbers[i][j];
												Numbers[k][j] = temp;
												Numbers[i][j] = 0;
												count++;
												break;
											}
										}
									}
								}
							}
							for (int j = 0; j < Numbers.length; j++) {
								for (int i = 3; i > 0; i--) {
									if(Numbers[i][j]==Numbers[i-1][j] && Numbers[i][j]!=0){
										Numbers[i][j]+=Numbers[i-1][j];
										Numbers[i-1][j]=0;
										count++;
										score+=Numbers[i][j];
										if(Numbers[i][j]==2048){
											isWin=true;
										}
									}
								}
							}
							for (int j = 0; j < Numbers.length; j++) {
								for (int i = 3; i >= 0; i--) {
									if (Numbers[i][j] != 0) {
										for (int k = 3; k > i; k--) {
											if (Numbers[k][j] == 0) {
												int temp = Numbers[i][j];
												Numbers[k][j] = temp;
												Numbers[i][j] = 0;
												count++;
												break;
											}
										}
									}
								}
							}
							count++;
							break;
						default:
							break;
					}

					//5. 判断是否棋盘满了
					for (int i = 0; i < Numbers.length; i++) {
						for (int j = 0; j < Numbers[i].length; j++) {
							if(Numbers[i][j]!=0){
								numCount++;
							}
						}
					}

					//6. 记录棋盘中相邻数相等的个数
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(Numbers[i][j]==Numbers[i][j+1]&&Numbers[i][j]!=0){
								nearCount++;
							}
							if(Numbers[i][j]==Numbers[i+1][j]&&Numbers[i][j]!=0){
								nearCount++;
							}
							if(Numbers[i][3]==Numbers[i+1][3]&&Numbers[i][3]!=0){
								nearCount++;
							}
							if(Numbers[3][j]==Numbers[3][j+1]&&Numbers[3][j]!=0){
								nearCount++;
							}
						}
					}

					//7. 根据棋盘中空余的个数生成随机的2、4
					if(count>0 && numCount!=16){
						int x3 = rand.nextInt(4);// 0.1.2.3
						int y3 = rand.nextInt(4);
						while(Numbers[x3][y3]!=0){
							x3 = rand.nextInt(4);
							y3 = rand.nextInt(4);
						}

						int values3=rand.nextInt(2)*2+2;
						Numbers[x3][y3]=values3;
					}

					//8. 判断游戏赢了没赢
					if(isWin==true){
						repaint();
						JOptionPane.showMessageDialog(jFrame, "恭喜赢了游戏！");
					}

					if(numCount==16&&nearCount==0){
//						System.out.println("Game Over");
						JOptionPane.showMessageDialog(jFrame, "Game Over！");
					}
					repaint();

				}

			}
		});
		this.setFocusable(true);

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(new Color(0xBBADA0));
		g.fillRoundRect(70, 110, 370, 370, 15, 15);// 大矩形框
		// 换个颜色，画小格子
		g.setColor(new Color(0xCDC1B4));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				g.fillRoundRect(80 + i * 90, 120 + j * 90, 80, 80, 15, 15);// 小矩形框
			}
		}

		/*
		 * 根据数字不一样设置不同的字体、颜色、和偏移量
		 */
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (Numbers[j][i] != 0) {
					int FontSize = 30;
					int MoveX = 0, MoveY = 0;
					switch (Numbers[j][i]) {
						case 2:
							g.setColor(new Color(0xeee4da));
							FontSize = 30;
							MoveX = 0;
							MoveY = 0;
							break;
						case 4:
							g.setColor(new Color(0xede0c8));
							FontSize = 30;
							MoveX = 0;
							MoveY = 0;
							break;
						case 8:
							g.setColor(new Color(0xf2b179));
							FontSize = 30;
							MoveX = 0;
							MoveY = 0;
							break;
						case 16:
							g.setColor(new Color(0xf59563));
							FontSize = 29;
							MoveX = -5;
							MoveY = 0;
							break;
						case 32:
							g.setColor(new Color(0xf67c5f));
							FontSize = 29;
							MoveX = -5;
							MoveY = 0;
							break;
						case 64:
							g.setColor(new Color(0xf65e3b));
							FontSize = 29;
							MoveX = -5;
							MoveY = 0;
							break;
						case 128:
							g.setColor(new Color(0xedcf72));
							FontSize = 28;
							MoveX = -10;
							MoveY = 0;
							break;
						case 256:
							g.setColor(new Color(0xedcc61));
							FontSize = 28;
							MoveX = -10;
							MoveY = 0;
							break;
						case 512:
							g.setColor(new Color(0xedc850));
							FontSize = 28;
							MoveX = -10;
							MoveY = 0;
							break;
						case 1024:
							g.setColor(new Color(0xedc53f));
							FontSize = 27;
							MoveX = -15;
							MoveY = 0;
							break;
						case 2048:
							g.setColor(new Color(0xedc22e));
							FontSize = 27;
							MoveX = -15;
							MoveY = 0;
							break;
						default:
							g.setColor(new Color(0x000000));
							break;
					}
					g.fillRoundRect(80 + i * 90, 120 + j * 90, 80, 80, 15, 15);// 小矩形框上色
					g.setColor(new Color(0x000000));// 设置字体颜色（统一）
					g.setFont(new Font("Kristen ITC", Font.PLAIN, FontSize));
					g.drawString(Numbers[j][i] + "", 80 + i * 90 + 30 + MoveX, 120 + j * 90 + 50 + MoveY);// 画对应数字
				}
			}
		}

	}
}
