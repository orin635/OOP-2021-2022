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

	float magicMap(float a, float b, float c, float d, float e)
	{
		float output;
		a -= b;
		c -= b;
		e-= d;

		output = ((a/c)*e) + d;

		return output;
	}

	float magicMap1(float a, float b, float c, float d, float e)
	{
		float r1 = c -b;
		float r2 = e-d;
		float howFar = a - b;

		return d + ((howFar / r1) * r2);
	}

	
	public void draw()
	{
		
		switch(mode)	
		{
			case 0:
				background(0);
				int bars = (int) (mouseX / 10.0f);
				float w = width / (float)bars;	
				for(int i = 0 ; i < bars ; i ++)
				{
					noStroke();
					fill(map(i, 0, bars, 0, 255), 255, 255);
					rect(map(i, 0, bars, 0, 500), 0, w, height);
				}
				break;
			case 1:
				background(0);
				int squares = (int) (mouseX / 2.0f);
				float h = width / (float)squares;	
				for(int i = 0 ; i < squares ; i ++)
				{
					noStroke();
					fill(map(i, 0, squares, 0, 255), 255, 255);
					rect(map(i, 0, squares, 0, 500),map(i, 0, squares, 0, 500) , h, h);
				}

				break;
				//map(a,b,c,d,e);
				//a = inputvalue
				// b - c - start and end of the first range
				// d, e 0 - start and and of the end range

				// map(-2, 10, 90, 200, 233);


				case 2:
					background(0);
					// int circle = (int) (mouseX / 2.0f);
					// int start = height / circle;
					// int y = (int) map(y,0,start,0,height);
					// float r = width / (float)circle;	
					// for(int i = 0 ; i < circle ; i ++)
					// {
					// 	noStroke();
					// 	fill(map(i, 0, circle, 0, 255), 255, 255);
					// 	circle(map(i, 0, circle, 0+r/2, 500),y,r);
					// 	//circle(0+r/2,map(i, 0, circle, 0+r/2, 500),r);
					// }
					int circle = (int)(mouseX/10.0f);
					
					float r = width / (float)circle;
					for(int j = (int) (0+(r/2)); j < height; j = (int) (j + r)){
						for(int i = 0 ; i < circle ; i ++)
						{
							noStroke();
							int colour = (int) map(i, circle/j, circle, j, 255); 
							while(colour > 255){
								colour = colour - 255;
							}
							fill(colour, 255, 255);
							circle(map(i, 0, circle, 0+r/2, 500),j,r);
						}
					}

				


				break;
		}
	}
}
