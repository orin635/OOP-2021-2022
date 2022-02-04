package ie.tudublin;

import com.jogamp.nativewindow.util.Rectangle;

import processing.core.PApplet;

public class BugZap extends PApplet
{

    float playerX, playerY, playerWidth, bugX, bugY, bugWidth, bulletX,bulletY,bulletR, teleportTime,r,g,b, velX, velY, newLocX, newLocY, bulletSpeed, playerSpeed, bugSpeed;
	int shoot, score, newLocation;

	public void settings()
	{
		size(750, 750);
	}

	public void setup() {
		colorMode(RGB);
		background(0);
        playerX = width/2;
        playerY = height - 50;
        playerWidth = 100;
		bugX = width/2;
		bugY = 100;
		bugWidth = 30;
		shoot = 0;
		bulletX = playerX;
		bulletY = playerY-40;
		newLocation = 1;
		bulletR = 12;
		teleportTime = 60;
		score = 0;
		bulletSpeed = 15;
		playerSpeed = 12;
		bugSpeed = 3;
		r = 255;
		g = 255;
		b = 255;

		smooth();
		
	}

    void drawPlayer(float x, float y, float w){
		fill(255,255,255);
        strokeWeight(10);
		stroke(255,255,255);
        line(x,y,x,y-40);
		strokeWeight(0);
        rectMode(CENTER);
        rect(x, y, w, w/2, 4);
    }   

	void drawBullet(float x, float y, float r){
		fill(117, 117, 117);
		strokeWeight(0);
		circle(x, y, r);
		rect(x,y+r/2,r,r);
	}

	void drawBug(float x, float y, float w){
        strokeWeight(0);
		if(g<255 || b<255){
			g = g + 15;
			b = b + 15;
			if(g>255){
				g = 255;
			}
			if(b>255){
				b = 255;
			}
		}
		fill(r,g,b);
		float x2,y2,x3,y3;
		x2 = x - w;
		x3 = x + w;
		y2 = y + 50;
		y3 = y2;
		triangle(x, y, x2, y2, x3, y3);
    }   

	void checkShoot(){
		if(shoot == 0){
			bulletX = playerX;
			bulletY = playerY-40;
		}

		if(shoot == 1){
			drawBullet(bulletX, bulletY, bulletR);
			bulletY = bulletY - bulletSpeed;
		}

		if(bulletY<0){
			shoot = 0;
		}
	}

	void teleportBug(){
		if ((frameCount % teleportTime) == 0)
		{
			bugX = random(0+bugWidth,width-bugWidth);
			bugY = random(0+bugWidth,height-200);
			teleportTime = random(60, 240);
			if(teleportTime<=120){
				teleportTime = 120;
			}
			else if(teleportTime<=180){
				teleportTime = 180;
			}
			else if(teleportTime<=240){
				teleportTime = 240;
			}
		}
	}

	void collisionCheck(){
		if(bulletX> bugX-30-bulletR/2 && bulletX<bugX+30+bulletR/2 && bulletY<bugY+50 && bulletY>bugY){
			System.out.println("hit");
			shoot = 0;
			newLocation = 1;
			g = 0;
			b = 0;
			score = score + 1;
		}
	}

	void displayScore(){
		fill(255,255,255);
		textSize(20);
		String displayText = "Score : " + score;
		text(displayText, 50, 50);
	}

	void moveBug(float x, float y){
		float distX, distY, dist, steps;

		if(newLocation == 1){
			dist = 0;
			distX = 0;
			distY = 0;
			
			while(dist<180){
				newLocX = random(0+bugWidth, width-bugWidth);
				newLocY = random(0+bugWidth, height-200);

				distX = newLocX - x;
				distY = newLocY - y;
			
				dist = (distX * distX) + (distY * distY);
				dist = (float) Math.sqrt(dist);
			}

			steps = dist / bugSpeed;

			velX = distX / steps;
			velY = distY / steps;

			newLocation = 0;

			String print = "NewLocX :" + newLocX + " NewLocY :" + newLocY + " DistX :" + distX + " DistY :" + distY + " dist :" + dist + " velX :" + velX + " velY :" + velY;

			System.out.println(print);
		}

		else if(newLocation == 0){
			bugX = bugX + velX;
			bugY = bugY + velY;

			if(bugX > newLocX){
				if(bugY > newLocY){
					if((bugX - newLocX)<2 && (bugY - newLocY)<2){
						newLocation = 1;
					}
				}

				else{
					if((bugX - newLocX)<2 && (newLocY - bugY)<2){
						newLocation = 1;
					}
				}
			}

			else{
				if(bugY > newLocY){
					if((newLocX - bugX)<2 && (bugY - newLocY)<2){
						newLocation = 1;
					}
				}

				else{
					if((newLocX) - bugX<2 && (newLocY - bugY)<2){
						newLocation = 1;
					}
				}
			}

			//if((bugX - newLocX)<2 && (bugY - newLocY)<2){
			//	newLocation = 1;
			//}
		}

		fill(255,255,255);
		circle(newLocX, newLocY+30, 10);


	}

    public void keyPressed()
	{
		if (key == 'a')
		{
            if(playerX > 0+(playerWidth/2)){
                playerX = playerX - playerSpeed;
            }
		}
		if (key == 'd')
		{
            if(playerX < width-(playerWidth/2)){
                playerX = playerX + playerSpeed;
            }
		}
		if (key == ' ')
		{
			shoot = 1;
		}
	}



	public void draw()
	{
        background(18, 23, 41);
		drawPlayer(playerX, playerY, playerWidth);
		drawBug(bugX, bugY, bugWidth);
		moveBug(bugX, bugY);
		//teleportBug();
		checkShoot();
		collisionCheck();
		displayScore();

	}
}
