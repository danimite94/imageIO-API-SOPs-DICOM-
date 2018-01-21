import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player extends Thread implements WindowListener{
	
	private static BufferedImage[] imagens;
	private static double delay;
	private static boolean playing = false;;
	private static JFrame jf = new JFrame();
	private static int indice = 0;
	private static JLabel label = new JLabel();
	
	public Player(final BufferedImage[] imagens,double delay,String Title){
		Player.jf.setTitle(Title);
		Player.imagens = imagens;
		Player.delay = delay;			
		initComponents();
        this.start();
	}
	
	public void initComponents(){
		
		JPanel panel = new JPanel(new BorderLayout());
		JButton playbutton = new JButton("Play");
		ImageIcon iconLogo = new ImageIcon("Images/play.png");
		playbutton.setIcon(iconLogo);
		JPanel flowPanel1 = new JPanel(new FlowLayout());
        flowPanel1.add(playbutton);
        panel.add(flowPanel1,BorderLayout.LINE_START);		

        JButton pausebutton = new JButton("Pause");
        ImageIcon iconLogo1 = new ImageIcon("Images/pause.png");
		pausebutton.setIcon(iconLogo1);
        JPanel flowPanel = new JPanel(new FlowLayout());
        flowPanel.add(pausebutton); 
        panel.add(flowPanel,BorderLayout.CENTER);
        
        JButton stopbutton = new JButton("Stop");
        ImageIcon iconLogo2 = new ImageIcon("Images/stop.png");
		stopbutton.setIcon(iconLogo2);
        JPanel flowPanel2 = new JPanel(new FlowLayout());
        flowPanel2.add(stopbutton);
        panel.add(flowPanel2,BorderLayout.LINE_END);
        
		label.setSize(512, 512);
		ImageIcon image = new ImageIcon(imagens[indice]);
		label.setIcon(image);
        panel.add(label,BorderLayout.PAGE_END);
        
        jf.add(panel);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        jf.addWindowListener(this);
        
        playbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playing = true; 
            }
        });
        
        pausebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playing = false;
            }
        });
        
        stopbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playing = false;
                indice = 0;
            }
        });
	}
			
	public void run(){
		 while (true){
			 if (playing){	
				ImageIcon image = new ImageIcon(imagens[indice]);
				label.setIcon(image);
	         	indice += 1;
	         	jf.pack();
	         	
	         	if (indice == imagens.length){ indice = 0; }
	         	
	         	try{Thread.sleep((long) delay);}
	         	catch(Exception e){e.printStackTrace();}
			 }
         } 
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		playing = false;
		indice = 0;
		this.stop();
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
