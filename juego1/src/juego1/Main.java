package juego1;

import javax.swing.JFrame;



public class Main extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Main(){
        //TITULO
        setTitle("Como Crear un Juego 2D con Java - La base 01");
        //CERRAR AL CLICKEAR EN LA CRUZ
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //TAMAÑO DE VENTANA
        setSize(500,500);
        //LOCACION DE LA VENTANA ( CENTRO )
        setLocationRelativeTo(null);
        //NO SE REDIMENSIONE LA VENTANA
        setResizable(false);
        
        //AGREGAMOS A LA VENTANA UN NUEVO OBJETO QUE ES UNA CLASE LLAMADA TABLERO
        //QUE HEREDA DE LA CLASE JPANEL DE SWING Y QUE ES DONDE SE DIBUJARA
        //NUESTRO JUEGO
        add(new Tablero());
        
        //HACEMOS VISIBLE NUESTRA VENTANA
        setVisible(true);
    }
    
    //METODO PRINCIPAL - SERA BUSCADO AL EJECUTAR UN PROGRAMA EN JAVA
    public static void main(String args[]){
        new Main();
    }
}
