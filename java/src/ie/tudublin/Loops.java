package ie.tudublin;

import processing.core.PApplet;

public class Loops extends PApplet
{


	int mode = 0;

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		colorMode(HSB);
		
	}

	public void keyPressed()
	{
		if (key >= '0' && key <='9')
		{
			mode = key - '0';
		}
		println(mode);
	}

	
	public void draw()
	{
		
		switch(mode)	
		{
			case 0:
				background(0);
				int x = 0;
				int y = 0;

				if(mouseX > width/2){
					x = width/2;
				}

				if(mouseY > height/2){
					y = height/2;
				}

				fill(255);
				rect(x, y, width/2, height/2);
				break;
			case 1:
				background(0);
				int i;
				float dist = width/12;
				fill(255);
				for(i = 0; i<12; i++){
					line(0+width/12, 0+dist, width, 0+dist);
					dist = dist + dist;
				}

				dist = width/12;

				for(i = 0; i<12; i++){
					line(0+dist, 0+width/12, 0+dist, height);
					dist = dist + dist;
				}
				break;

			case 2:
				// float x = map(i, -5, 5, border, width - border);
				// stroke(0, 255, 0);
				// line(x, border, x, height - border);
				// line(border, x, width - border, x);
				// fill(255);
				// text(i, x, border * 0.5f);
				// text(i, border * 0.5f, x);
				// break;
		
			case 3:
			// background(0);
			// colorMode(RGB);
			// stroke(255);
			// stroke(255);
			// float cx = width / 2;
			// float cy = height / 2;
			// float radius = 200; 

			// int sides = (int)map(mouseX, 1, width, 0, 20);
			// for(int i = 1 ; i < sides ; i ++)
			// 	float theta1 map(i - 1, 0, sides, 0, TWO_PI);
			// 								* radius;
							
			// 			cx + sin(theta1)
			// 			= cy + cos(theta1) * radius;
			// 	float x1 =
			// 	float y1
			// 				map(i, 0, sides, 0, TWO_PI);
			// 			cx + sin(theta2)
			// 			cy + cos(theta2)
			// 	float theta2 
			// 	float x2 * radius; 
			// 	float y2 radius; I 
			// 	line(x1, y1, x2, y2);
			// 	//circle(x, y, 20);
		}
	}
}
