package dev.codenmore.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.naming.InitialContext;

import dev.codenmore.tilegame.display.Display;

public class Game implements Runnable {

	private Display display;
	public int width, height;
	public String title;
	
	private Thread thread;
	private boolean running = false;
	
	
	private BufferStrategy bs;
	private Graphics g;
	
	
	public Game(String title, int width,int height ) {
		this.width = width;
		this.height = height;
		this.title = title;
		
	}
	
	private void Init() {
		display = new Display(title, width, height);
		
		
	}
	
	private void tick() {
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;			
		}
		
		g= bs.getDrawGraphics();
		//�b�o�e�e!
		
		g.fillRect(0, 0, width/2, height/2);
		
		//�����e�e!
		bs.show();
		g.dispose();
	}
	;
	public void run() {
		
		Init();
		
		while (running) {
			tick();
			render();
		}
		stop();
	}


	public synchronized void start() {
		if(running) return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	public synchronized void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
	}
}
