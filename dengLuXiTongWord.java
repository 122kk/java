package mysql_lianjian;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.RunnableScheduledFuture;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Toolkit;

public class dengLuXiTongWord extends JFrame {
	Connection connection=null;
	Statement stmt=null;
	PreparedStatement preparedStatement=null;

	private JPanel PlayGram;
	private JTextField name;
	private JTextField ID;
	private JTextField dizi;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	String id=null;
	String Name=null;
	String School=null;
	String Dizi=null;
	String Interior1=null;
	String Interior2=null;
	String Interior3=null;
	String Interior4=null;
	String Sex=null;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSchool() {
		return School;
	}

	public void setSchool(String school) {
		School = school;
	}

	public String getDizi() {
		return Dizi;
	}

	public void setDizi(String dizi) {
		Dizi = dizi;
	}

	public String getInterior1() {
		return Interior1;
	}
	public String getInterior2() { return Interior2; }
	public String getInterior3() {
		return Interior3;
	}
	public String getInterior4() {
		return Interior4;
	}


	public void setInterior1(String interior1) {
		Interior1 = interior1;
	}

	public void setInterior2(String interior2) {
		Interior2 = interior2;
	}

	public void setInterior3(String interior3) {
		Interior3 = interior3;
	}

	public void setInterior4(String interior4) {
		Interior4 = interior4;
	}

	public String getSex() {
		return Sex;
	}
//
	public void setSex(String sex) {
		Sex = sex;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					dengLuXiTongWord frame = new dengLuXiTongWord();
//					frame.setVisible(true);
//
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		System.out.println("__________________________________");
		System.out.println("*          1.添加学生              *");
		System.out.println("*          2.删除学生              *");
		System.out.println("*          3.查询学生              *");
		System.out.println("-----------------------------------");

		dengLuXiTongWord frame = new dengLuXiTongWord();
		Scanner scannermain=new Scanner(System.in);
		int nummain=0;

		while (true){
			nummain=scannermain.nextInt();
			if(nummain>3||nummain<1){
				System.out.println("输入的命令不符合规范！！！请重新输入：");
			}else {
				break;
			}
		}
		switch (nummain){
			case 1:frame.setVisible(true);
				break;
			case 2:frame.shanchu();
				break;
			case 3:
				frame.chaxu();
				break;
		}

	}
	//输入数据库
	public void mysql_liagnjian(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/mydb?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
			String username="root";
			String password="15935728qaz";
			connection= DriverManager.getConnection(url, username, password);

			String sql="INSERT INTO student_kwj(ID,name,school,interest,dizi,sex)"
					+"VALUES(?,?,?,?,?,?)";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, Name);
			preparedStatement.setString(3, School);
			preparedStatement.setString(4, Interior1+Interior2+Interior3+Interior4);
			preparedStatement.setString(5, Dizi);
			preparedStatement.setString(6, Sex);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public dengLuXiTongWord() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/kwj.png"));
		setTitle("欢迎注册kwj");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 690);
		PlayGram = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO 自动生成的方法存根
				super.paintComponent(g);
				ImageIcon iimg = new ImageIcon("src/cat.png");
				g.drawImage(iimg.getImage(), 0, 0, 588,690,iimg.getImageObserver());
				
			}
		};
		PlayGram.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PlayGram);
		PlayGram.setLayout(null);
		
		JLabel tyiyt = new JLabel("个人信息登录系统");
		tyiyt.setHorizontalAlignment(SwingConstants.CENTER);
		tyiyt.setForeground(Color.RED);
		tyiyt.setFont(new Font("楷体", Font.PLAIN, 27));
		tyiyt.setBounds(156, 13, 260, 40);
		PlayGram.add(tyiyt);
		
		JLabel lblNewLabel = new JLabel("姓名：");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(55, 99, 72, 18);
		PlayGram.add(lblNewLabel);
		
		name = new JTextField();
		name.setBounds(176, 98, 201, 24);
		PlayGram.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("学号：");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(55, 154, 72, 18);
		PlayGram.add(lblNewLabel_1);
		
		ID = new JTextField();
		ID.setBounds(176, 151, 201, 24);
		PlayGram.add(ID);
		ID.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("学院：");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(55, 203, 72, 18);
		PlayGram.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("户籍所在地：");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(55, 254, 127, 18);
		PlayGram.add(lblNewLabel_3);
		
		dizi = new JTextField();
		dizi.setBounds(176, 253, 340, 24);
		PlayGram.add(dizi);
		dizi.setColumns(10);
		
//		JComboBox school = new JComboBox();
		String[] listData = new String[]{"数据信息学院", "城市管理学院", "机电汽车学院", "医药护理学院"};   
        final JComboBox<String> school = new JComboBox<String>(listData);
		school.setBounds(176, 202, 201, 24);
		
		PlayGram.add(school);
		
		JLabel lblNewLabel_4 = new JLabel("性别：");
		lblNewLabel_4.setForeground(Color.GRAY);
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(55, 305, 72, 18);
		PlayGram.add(lblNewLabel_4);
		
		JRadioButton Boy = new JRadioButton("男");
		Boy.setFont(new Font("宋体", Font.PLAIN, 17));
		buttonGroup.add(Boy);
		Boy.setBounds(118, 303, 50, 27);
		PlayGram.add(Boy);
		
		JRadioButton Girl = new JRadioButton("女");
		Girl.setFont(new Font("宋体", Font.PLAIN, 17));
		buttonGroup.add(Girl);
		Girl.setBounds(281, 303, 50, 27);
		PlayGram.add(Girl);
		
		JLabel lblNewLabel_5 = new JLabel("兴趣：");
		lblNewLabel_5.setForeground(Color.GRAY);
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(55, 353, 72, 18);
		PlayGram.add(lblNewLabel_5);
		
		JCheckBox Runs = new JCheckBox("跑步");
		Runs.setFont(new Font("宋体", Font.PLAIN, 17));
		Runs.setBackground(Color.WHITE);
		Runs.setBounds(130, 351, 77, 27);
		PlayGram.add(Runs);
		
		JCheckBox Basketball = new JCheckBox("打球");
		Basketball.setFont(new Font("宋体", Font.PLAIN, 17));
		Basketball.setBackground(Color.WHITE);
		Basketball.setBounds(213, 351, 85, 27);
		PlayGram.add(Basketball);
		
		JCheckBox LookBook = new JCheckBox("看书");
		LookBook.setFont(new Font("宋体", Font.PLAIN, 17));
		LookBook.setBackground(Color.WHITE);
		LookBook.setBounds(304, 351, 73, 27);
		PlayGram.add(LookBook);
		
		JCheckBox Playgame = new JCheckBox("玩电子游戏");
		Playgame.setFont(new Font("宋体", Font.PLAIN, 17));
		Playgame.setBackground(Color.WHITE);
		Playgame.setBounds(395, 351, 121, 27);
		PlayGram.add(Playgame);
		
		JButton btnNewButton = new JButton("注册");
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setBounds(156, 456, 221, 40);
		PlayGram.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				System.out.println(name.getText());
				System.out.println(ID.getText());
				System.out.println(school.getSelectedItem());
				System.out.println(dizi.getText());
				System.out.println(Boy.isSelected()?Boy.getText():Girl.getText());
				if (Runs.isSelected()) {System.out.println(Runs.getText());}
				if (Basketball.isSelected()) {System.out.println(Basketball.getText());}
				if (LookBook.isSelected()) {System.out.println(LookBook.getText());}
				if (Playgame.isSelected()) {System.out.println(Playgame.getText());}

				//赋值

				Name=name.getText();
				id=ID.getText();
				School= (String) school.getSelectedItem();
				Dizi=dizi.getText();
				Sex=Boy.isSelected()?Boy.getText():Girl.getText();
				if (Runs.isSelected()) {Interior1=Runs.getText();}
				if (Basketball.isSelected()) {Interior2=Basketball.getText();}
				if (LookBook.isSelected()) {Interior3=LookBook.getText();}
				if (Playgame.isSelected()) {Interior4=Playgame.getText();}

				mysql_liagnjian();

//				setName(Name);
//				setId(id);
//				setSchool(School);
//				setDizi(Dizi);
//				setSex(Sex);
//				setInterior1(Interior1);
//				setInterior2(Interior2);
//				setInterior3(Interior3);
//				setInterior4(Interior4);

			}
		});

		System.out.println(id+Name+School+Sex+Interior1+Dizi);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setBounds(156, 519, 221, 40);
		PlayGram.add(btnNewButton_1);
	}
	//查询登录系统
	public void chaxu()  {
		String idcx=null;
		String schoolcx=null;
		String dizicx=null;
		String sexcx=null;
		String namecx=null;

		ResultSet rs=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/mydb?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
			String username="root";
			String password="15935728qaz";
			connection= DriverManager.getConnection(url, username, password);
			stmt=connection.createStatement();

			Scanner scanner=new Scanner(System.in);
			System.out.println("请输入要查询的学生姓名：");
			namecx=scanner.nextLine();

			String sql="select id,dizi,sex,school from student_kwj where name='"+namecx+"'";
			rs=stmt.executeQuery(sql);



			while (rs.next()){
				idcx=rs.getString("id");
				schoolcx=rs.getString("school");
				sexcx=rs.getString("sex");
				dizicx=rs.getString("dizi");
				System.out.println("学号："+idcx+" |"+"学院："+schoolcx+" |"+"性别："+sexcx+" |"+"户籍所在地："+dizicx);

			}
		} catch (SQLException | ClassNotFoundException throwables) {

			System.out.println("查无此生！！！");
		}finally {
			try {
				stmt.close();
				rs.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//删除系统
	public void shanchu(){

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/mydb?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
			String username="root";
			String password="15935728qaz";
			connection= DriverManager.getConnection(url, username, password);
			Scanner scanner=new Scanner(System.in);
			System.out.println("输入要删除的学生的姓名：");
			String namesc=scanner.nextLine();

			String sql="delete from student_kwj where name='"+namesc+"'";
			stmt=connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}

		}
	}
}
