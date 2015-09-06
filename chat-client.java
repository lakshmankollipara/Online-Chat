import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
 
class ImagePanel extends JPanel
	 {
	JFrame frame1;
	private Image img;
	
	JLabel jl1= new JLabel("CLIENT WINDOW");
	JButton jb1=new JButton("  Connect  ");
	JButton jb2= new JButton("PROJECT MADE BY");	 		 
	JLabel jl2 = new JLabel(new ImageIcon("intro_logo.jpg"));
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
	jb1.addActionListener(new Connect());
	jb2.addActionListener(new KKR());
     	}//end constructor
	  public void paintComponent(Graphics g) {
    	  g.drawImage(img, 0, 0, null);
	 }}
	public class NewClass5 {
 
  	public static void main(String[] args) {

   	 JFrame frame1 = new JFrame("CLIENT WINDOW");
     	ImagePanel panel = new ImagePanel(frame1,new ImageIcon("www.jpg").getImage());
  	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame1.getContentPane().add(panel);
    	frame1.pack();
	frame1.setVisible(true);
 	}
	}
	 class Connect implements ActionListener
 		{	Socket so;
			InputStream in=null;
			OutputStream out=null;
			OutputStreamWriter osw=null;
			InputStreamReader inr=null;
			BufferedReader   br=null;
			PrintWriter pw=null;
			
		public  void actionPerformed(ActionEvent c)
		{
		try{
			 so=new Socket("127.0.0.1",13297);
			 in = so.getInputStream();
        	 	 out = so.getOutputStream();
        		 inr = new  InputStreamReader(in);
        		 br = new BufferedReader(inr);
      			 osw = new OutputStreamWriter(out);
      			 pw = new PrintWriter (osw,true);
			new MyTab(br,pw);
		}catch(Exception ae){System.out.println(ae);}
		}

		class MyTab extends JPanel
		{
		PrintWriter pw;
		JFrame frame2=new JFrame();
		JPanel pan=new JPanel();
		JButton jb=new JButton();
		JLabel jl4=new JLabel("CLIENT");
		JTextField jft2= new JTextField(15);
		JButton jb3= new JButton("Send");
		JLabel jl5=new JLabel(new ImageIcon("toptitle.jpg"));
		JLabel cl= new JLabel("SERVER");
		JTextArea ja=new JTextArea(10,20);
		JScrollPane sp = new JScrollPane(ja);
		
		MyTab(BufferedReader br, PrintWriter pw)
			{					
				this.pw=pw; 				
				frame2.add(pan);
				Font font1 = new Font("Verdana", Font.BOLD,12);
				jl4.setFont(font1);
				jl4.setForeground(Color.blue);
				pan.add(jl4);
				pan.add(jft2);
				pan.add(jb3);
				jb3.addActionListener(new Send());
				pan.add(jl5);
				Font font2 = new Font("Verdana", Font.BOLD,12);
				cl.setFont(font2);
				cl.setForeground(Color.blue);
				pan.add(cl);
				pan.add(sp);
				new ServerRead(br,ja);
				frame2.setSize(351,360);
				frame2.setVisible(true);
				}	
		class Send implements ActionListener
				{	
					
				public void actionPerformed(ActionEvent e) {
					try{
					String s3=jft2.getText();
					pw.println(s3);
					jft2.setText("");
					ja.append("\n Client  : "+s3);
					}catch(Exception t){System.out.println(e);}}
					}}			
		class ServerRead extends Thread
			{
		BufferedReader br;
		JTextArea ja;
		ServerRead(BufferedReader br, JTextArea ja)
		{
		this.ja=ja;
		this.br=br;
		start();
		}
		public void run()
		{
			try{
				while(true){
				String s3=br.readLine();
				ja.append("\n Server :"+s3);
						}
			}catch(Exception e){System.out.println(e);}
		}}
	class KKR implements ActionListener
			{		
		JFrame frame1= new JFrame("PROJECT MADE BY");
		JPanel pan=new JPanel();
		JLabel jl4=new JLabel("PROJECT MADE BY");
		JLabel jl5=new JLabel(new ImageIcon("btn_ff_dl120.jpg"));
		
		JLabel cl3=new JLabel(new ImageIcon("tit.jpg"));
		public void actionPerformed(ActionEvent e) {
		     	frame1.setLayout(new FlowLayout());	
			frame1.add(pan);
				Font font1 = new Font("Verdana", Font.BOLD,12);
				jl4.setFont(font1);
				jl4.setForeground(Color.blue);
			frame1.add(jl4);
			frame1.add(jl5);
				Font font2 = new Font("Verdana", Font.BOLD,12);
			frame1.add(cl3);
			frame1.setVisible(true);
		       frame1.setSize(351,360);
			}
			}			
}
