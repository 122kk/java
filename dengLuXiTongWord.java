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
		System.out.println("*          1.????????????              *");
		System.out.println("*          2.????????????              *");
		System.out.println("*          3.????????????              *");
		System.out.println("-----------------------------------");

		dengLuXiTongWord frame = new dengLuXiTongWord();
		Scanner scannermain=new Scanner(System.in);
		int nummain=0;

		while (true){
			nummain=scannermain.nextInt();
			if(nummain>3||nummain<1){
				System.out.println("?????????????????????????????????????????????????????????");
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
	//???????????????
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
		setTitle("????????????kwj");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 690);
		PlayGram = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO ???????????????????????????
				super.paintComponent(g);
				ImageIcon iimg = new ImageIcon("src/cat.png");
				g.drawImage(iimg.getImage(), 0, 0, 588,690,iimg.getImageObserver());
				
			}
		};
		PlayGram.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PlayGram);
		PlayGram.setLayout(null);
		
		JLabel tyiyt = new JLabel("????????????????????????");
		tyiyt.setHorizontalAlignment(SwingConstants.CENTER);
		tyiyt.setForeground(Color.RED);
		tyiyt.setFont(new Font("??????", Font.PLAIN, 27));
		tyiyt.setBounds(156, 13, 260, 40);
		PlayGram.add(tyiyt);
		
		JLabel lblNewLabel = new JLabel("?????????");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("??????", Font.PLAIN, 18));
		lblNewLabel.setBounds(55, 99, 72, 18);
		PlayGram.add(lblNewLabel);
		
		name = new JTextField();
		name.setBounds(176, 98, 201, 24);
		PlayGram.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("?????????");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("??????", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(55, 154, 72, 18);
		PlayGram.add(lblNewLabel_1);
		
		ID = new JTextField();
		ID.setBounds(176, 151, 201, 24);
		PlayGram.add(ID);
		ID.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("?????????");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("??????", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(55, 203, 72, 18);
		PlayGram.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("??????????????????");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("??????", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(55, 254, 127, 18);
		PlayGram.add(lblNewLabel_3);
		
		dizi = new JTextField();
		dizi.setBounds(176, 253, 340, 24);
		PlayGram.add(dizi);
		dizi.setColumns(10);
		
//		JComboBox school = new JComboBox();
		String[] listData = new String[]{"??????????????????", "??????????????????", "??????????????????", "??????????????????"};   
        final JComboBox<String> school = new JComboBox<String>(listData);
		school.setBounds(176, 202, 201, 24);
		
		PlayGram.add(school);
		
		JLabel lblNewLabel_4 = new JLabel("?????????");
		lblNewLabel_4.setForeground(Color.GRAY);
		lblNewLabel_4.setFont(new Font("??????", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(55, 305, 72, 18);
		PlayGram.add(lblNewLabel_4);
		
		JRadioButton Boy = new JRadioButton("???");
		Boy.setFont(new Font("??????", Font.PLAIN, 17));
		buttonGroup.add(Boy);
		Boy.setBounds(118, 303, 50, 27);
		PlayGram.add(Boy);
		
		JRadioButton Girl = new JRadioButton("???");
		Girl.setFont(new Font("??????", Font.PLAIN, 17));
		buttonGroup.add(Girl);
		Girl.setBounds(281, 303, 50, 27);
		PlayGram.add(Girl);
		
		JLabel lblNewLabel_5 = new JLabel("?????????");
		lblNewLabel_5.setForeground(Color.GRAY);
		lblNewLabel_5.setFont(new Font("??????", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(55, 353, 72, 18);
		PlayGram.add(lblNewLabel_5);
		
		JCheckBox Runs = new JCheckBox("??????");
		Runs.setFont(new Font("??????", Font.PLAIN, 17));
		Runs.setBackground(Color.WHITE);
		Runs.setBounds(130, 351, 77, 27);
		PlayGram.add(Runs);
		
		JCheckBox Basketball = new JCheckBox("??????");
		Basketball.setFont(new Font("??????", Font.PLAIN, 17));
		Basketball.setBackground(Color.WHITE);
		Basketball.setBounds(213, 351, 85, 27);
		PlayGram.add(Basketball);
		
		JCheckBox LookBook = new JCheckBox("??????");
		LookBook.setFont(new Font("??????", Font.PLAIN, 17));
		LookBook.setBackground(Color.WHITE);
		LookBook.setBounds(304, 351, 73, 27);
		PlayGram.add(LookBook);
		
		JCheckBox Playgame = new JCheckBox("???????????????");
		Playgame.setFont(new Font("??????", Font.PLAIN, 17));
		Playgame.setBackground(Color.WHITE);
		Playgame.setBounds(395, 351, 121, 27);
		PlayGram.add(Playgame);
		
		JButton btnNewButton = new JButton("??????");
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setBounds(156, 456, 221, 40);
		PlayGram.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO ???????????????????????????
				System.out.println(name.getText());
				System.out.println(ID.getText());
				System.out.println(school.getSelectedItem());
				System.out.println(dizi.getText());
				System.out.println(Boy.isSelected()?Boy.getText():Girl.getText());
				if (Runs.isSelected()) {System.out.println(Runs.getText());}
				if (Basketball.isSelected()) {System.out.println(Basketball.getText());}
				if (LookBook.isSelected()) {System.out.println(LookBook.getText());}
				if (Playgame.isSelected()) {System.out.println(Playgame.getText());}

				//??????

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
		
		JButton btnNewButton_1 = new JButton("??????");
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setBounds(156, 519, 221, 40);
		PlayGram.add(btnNewButton_1);
	}
	//??????????????????
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
			System.out.println("????????????????????????????????????");
			namecx=scanner.nextLine();

			String sql="select id,dizi,sex,school from student_kwj where name='"+namecx+"'";
			rs=stmt.executeQuery(sql);



			while (rs.next()){
				idcx=rs.getString("id");
				schoolcx=rs.getString("school");
				sexcx=rs.getString("sex");
				dizicx=rs.getString("dizi");
				System.out.println("?????????"+idcx+" |"+"?????????"+schoolcx+" |"+"?????????"+sexcx+" |"+"??????????????????"+dizicx);

			}
		} catch (SQLException | ClassNotFoundException throwables) {

			System.out.println("?????????????????????");
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
	//????????????
	public void shanchu(){

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/mydb?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
			String username="root";
			String password="15935728qaz";
			connection= DriverManager.getConnection(url, username, password);
			Scanner scanner=new Scanner(System.in);
			System.out.println("????????????????????????????????????");
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
