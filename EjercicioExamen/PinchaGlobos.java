import java.awt.*;
import java.applet.*;

public class PinchaGlobos extends Applet implements Runnable{
	public static final int NUM_IMGS = 6;
	public static final int NUM_GLOBOS_INICIO = 10;
	Image imagen;
	Graphics noseve;
	Thread animacion;
	Image globos[];
	
	int contador = 0;
	int puntos = 0;
	int segundos = 0;
	
	java.util.List<Globo> globosObj;

    public void init() {
    	imagen = createImage(500, 500);
    	noseve = imagen.getGraphics();
    	globos = new Image[NUM_IMGS];
    	for(int i=0; i<NUM_IMGS; i++)
			 globos[i] = getImage(getCodeBase(), "globos/globo" + (i+1) + ".jpg");

    	globosObj = new java.util.ArrayList<Globo>();

    	for(int i=0; i<10; i++){
    		int pos = (int)(Math.random()*6);
    		globosObj.add(new Globo((int)(Math.random()*400), (int)(Math.random()*500) + 100 , globos[pos], (pos+1)*100));
    	}
    }
    
    public void start(){
    	animacion = new Thread(this);
    	animacion.start(); //llama al método run
    }
    
    public void paint(Graphics g){
    	noseve.setColor(Color.white);
    	noseve.fillRect(0, 0, 500, 500);
		for(int i=0; i<globosObj.size(); i++)
			globosObj.get(i).dibujar(noseve, this);
		noseve.setColor(Color.red);
		noseve.drawString("Puntuación : " + puntos, 15, 15);
    	g.drawImage(imagen, 0, 0, this);
    }
    
    public void update(Graphics g){
    	paint(g);
    }
    
    public boolean mouseDown(Event ev, int x, int y){
		for(int i=0; i<globosObj.size(); i++)
			if(globosObj.get(i).contains(x, y)){
				globosObj.get(i).pinchar();
				puntos += globosObj.get(i).getValor();
			}
    	return true;
    }
    
    public void run(){
    	while(true){
    		for(int i=0; i<globosObj.size(); i++){
				globosObj.get(i).actualizar();
				if(globosObj.get(i).y < -100)
					globosObj.remove(i);
    		}
   			contador ++;
   			segundos = (int)(contador / 10);
   			if(contador % 10 == 0){
   				int pos = (int)(Math.random()*6);
   				globosObj.add(new Globo((int)(Math.random()*400), (int)(Math.random()*100) + 500 , globos[pos], (pos+1)*100));
   			}
			repaint();
			if(segundos == 60)
				animacion.stop();
    		try{
    			Thread.sleep(100);
    		}catch(InterruptedException e){};
    	}
    }
}