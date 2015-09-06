import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*; 

class ImagePanel extends JPanel
	 {
	JFrame frame1;
	private Image img;
	
	JLabel jl1= new JLabel("WELCOME TO THE CHAT SERVER");
	JButton jb1= new JButton(" START ");	  	 		 		 
	JLabel jl2 = new JLabel(new ImageIcon("pisa.jpg"));
	public ImagePanel(JFrame frame1,String img) 
	{
    	this(frame1,new ImageIcon(img).getImage());
        }
  	public ImagePanel(JFrame frame1,Image img) {
   	this.img = img;
	this.frame1=frame1;
    	Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    	setPreferredSize(size);
    	setMinimumSize(size);
    	setMaximumSize(size);
    	setSize(size);
    	setLayout(null);
	setLayout(new FlowLayout());
	Font font = new Font("Verdana", Font.BOLD,14);
        jl1.setFont(font);
	jl1.setForeground(Color.yellow);
	add(jl1);
	add(jl2);
	add(jb1);
	jb1.addActionListener(new Start());
     	}//end constructor
	  public void paintComponent(Graphics g) {
    	  g.drawImage(img, 0, 0, null);
  	 }}
    	 public class NewClass3 {
  	 public static void main(String[] args) {
  	 JFrame frame1 = new JFrame("CHAT SERVER");
       	 ImagePanel panel = new ImagePanel(frame1,new ImageIcon("background-blur-1.jpg").getImage());
   	 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 frame1.getContentPane().add(panel);
    	 frame1.pack();
    	 frame1.setVisible(true);
	 }}
	class Start implements ActionListener
		{	
			public void actionPerformed(ActionEvent ae){
			try{
			ServerSocket so=new ServerSocket(13297);
			JFrame jf=new JFrame();
			JTabbedPane jtb=new JTabbedPane();
			jf.add(jtb);
			jf.setVisible(true);
			jf.setSize(351,360);
			new ServerThread(so,jtb);
			}catch(Exception a){}
			}
		class ServerThread extends Thread
		{
		ServerSocket ss;
		JTabbedPane jtb;
		int cnt;
		ServerThread(ServerSocket ss,JTabbedPane jtb)
		{
			this.ss=ss;
			this.jtb=jtb;
			start();
		}
		public void run(){
		while(true){ try{
		Socket so=ss.accept();
		System.out.println("accepted connection");
        	InputStream    in  =     so.getInputStream();
         	OutputStream out =     so.getOutputStream();
        	InputStreamReader inr = new  InputStreamReader(in);
        	BufferedReader   br = new BufferedReader(inr);
      		OutputStreamWriter osw = new OutputStreamWriter(out);
      		PrintWriter pw = new PrintWriter (osw,true);
		MyTab mk=new MyTab(br, pw);
		cnt++;
		jtb.addTab("Client"+cnt,mk);
		}
		catch(Exception ae){}
		}
		}	
		}
		class MyTab extends JPanel
		{
		BufferedReader br;
		PrintWriter pw;
		JButton jb=new JButton();
		JLabel jl4=new JLabel("SEVER");
		JTextField jft= new JTextField(15);
		JButton jb3= new JButton("Send");
		JLabel jl5=new JLabel(new ImageIcon("toptitle.jpg"));
		JLabel cl= new JLabel("CLIENT");
		JTextArea ja=new JTextArea(10,20);
		JScrollPane sp = new JScrollPane(ja);
		MyTab(BufferedReader br, PrintWriter pw)
			{	
				this.br=br;
				this.pw=pw;		 
   				Font font1 = new Font("Verdana", Font.BOLD,12);
				jl4.setFont(font1);
				jl4.setForeground(Color.blue);
				add(jl4);
				add(jft);
				add(jb3);
				jb3.addActionListener(new Send());
				add(jl5);
				Font font2 = new Font("Verdana", Font.BOLD,12);
				cl.setFont(font2);
				cl.setForeground(Color.blue);
				add(cl);
				add(sp);
				new ClientRead(br,ja);
			} 
			class Send implements ActionListener{
			public void actionPerformed(ActionEvent ae){
			String s=jft.getText();
			pw.println(s);
			jft.setText(" ");
			ja.append("\n Server :"+s);
			} }
	class ClientRead extends Thread
	{
		BufferedReader br;
		JTextArea jta;
		ClientRead(BufferedReader br, JTextArea jta)
		{
		this.jta=jta;
		this.br=br;
		 start();
		}
		public void run()
		{
			try{
				while(true){
				String s1=br.readLine();
				jta.append("\n Client :"+s1);}
			}catch(Exception e){System.out.println(e);}
		}
	}}
	

		}
	
