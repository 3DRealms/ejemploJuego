package juego1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Tablero extends JPanel implements Runnable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image background;
	 //private Image angrybird;
	 private BufferedImage img;
	 private Thread hilo;
	 private int x,y;
	 private int xMouse,yMouse;
	 private final int DELAY=15;


    
    /* CONSTRUCTOR DEL PANEL EN EL QUE SE ESTABLECEN ALGUNAS
    CONFIGURACION COMO EL COLOR DE FONDO, DOBLE BUFFER,
    INICIALIZACION DE IMAGENES, X,Y */
    public Tablero(){
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        background = new ImageIcon(this.getClass().getResource("/img/stage1.png")).getImage();
       // angrybird = new ImageIcon(this.getClass().getResource("/img/character1.png")).getImage();
        try {
	        img = ImageIO.read(new File("src/img/character1.png"));
	    } catch (IOException e) {
	    }
        xMouse = x=70;
        yMouse = y=200;

        addMouseListener(new MouseAdapter() {

        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		// TODO Auto-generated method stub
        		xMouse = arg0.getX();
        		yMouse = arg0.getY();
        	}
        });

    }
    
    /* ESTE METODO SE LLAMA AUTOMATICAMENTE AL AGREGAR EL PANEL
    A LA VENTANA */
    @Override
    public void addNotify(){
        super.addNotify();
        //CREAMOS UN NUEVO HILO Y LO INICIAMOS
        hilo = new Thread(this);
        hilo.start();
    }
    
    /* METODO QUE SE ENCARGA DE PINTAR EN EL PANEL */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        //CREAMOS UN LIENZO 2D CON EL LIENZO POR DEFECTO
        Graphics2D g2 = (Graphics2D)g;
        //DIBUJAMOS
        //LIBERAMOS LIENZO
        g2.drawImage(background, 0,0, null);
       // g2.drawImage(angrybird,x,y, null);
        g.drawImage(img.getSubimage(0, 0, img.getWidth() / 3, img.getHeight()/4),x, y, null);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    /* METODO DONDE SE ACTUALIZAN LAS COORDENADAS PARA LAS
    POSICIONES DE LAS IMAGNES */
    public void ciclo(){

        if ( x > xMouse )
            x--;
        
        if ( x < xMouse )
            x++;
        if ( y > yMouse )
            y--;
        
        if ( y < yMouse )
            y++;

    }
 
    /* METODO DEFINIDO DE LA INTERFAZ RUNNABLE, AQUI IRA EL CICLO
    PRINCIPAL DEL JUEGO, SE CALCULAN LAS POSICIONES, SE MANDA REPINTAR,
    SE ESTABLECE UN TIEMPO DE ESPERA RAZONABLE ENTRE (FRAMERATE) */
    @Override
    public void run() {
        while(true){
            ciclo();
            repaint();
            try{
				Thread.sleep(DELAY );
            }catch(InterruptedException err){
                System.out.println(err);
            }
        }
    }

}

