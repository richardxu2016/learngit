package com.soufun.optimization.buffer;

import java.awt.Color;
import java.awt.Graphics;

public class BufferMovingCircle extends NoBufferMovingCircle {

	
	private static final long serialVersionUID = 4738195826880965318L;

	Graphics doubleBuffer = null ;
	
	public void init(){
		super.init();
		doubleBuffer = screenImage.getGraphics();
	}
	
	public void paint(Graphics g){
		doubleBuffer.setColor(Color.white);
		doubleBuffer.fillRect(0, 0, 200, 100);
		drawCircle(doubleBuffer);
		g.drawImage(screenImage, 0, 0, this);
	}
}
