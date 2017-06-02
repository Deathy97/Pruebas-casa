import java.awt.*;
import java.applet.*;

public class Globo extends Rectangle{
	public static final int DIM = 100;
	private Image imagen;
	private boolean pinchado;
	private int valor;
	int vely;
    public Globo(int posx, int posy, Image img, int v) {
		super(posx, posy, DIM, DIM);
    	imagen = img;
    	valor = v;
    	pinchado = false;
    	vely = v/20;
    }

	public Image getImagen(){
		return imagen;
	}

	public int getValor(){
		return valor;
	}
	
	public boolean getPinchado(){
		return pinchado;
	}

	public void pinchar(){
		pinchado = true;
	}

    public void dibujar(Graphics g, Applet a){
    	g.setColor(Color.black);
		if(!pinchado)
			g.drawImage(imagen, x, y, width, height, a);
		else
			g.drawString("" + valor, (x+2), (y+20));
    }
    
    public void actualizar(){
    	y -= vely;
    }
}