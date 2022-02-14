package ie.tudublin;

//import com.jogamp.nativewindow.util.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;

public class BugZap extends PApplet
{

    float playerX, playerY, playerWidth, bugX, bugY, bugWidth, bulletX,bulletY,bulletR, teleportTime,r,g,b, velX, velY, newLocX, newLocY, bulletSpeed, playerSpeed, bugSpeed, bugGrowSize;
	double bugGrowSpeed;
	int shoot, score, newLocation, bugscore, start, end;
	PImage img;

	public void settings()
	{
		size(1000, 750);
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
		bugGrowSize = 1;
		bugGrowSpeed = 0.5;
		bugscore = 0;
		start = 0;
		end = 0;
		img = loadImage("C:/Users/omcdo/OneDrive - Technological University Dublin/OOP2/OOP-2021-2022/java/src/ie/tudublin/cake.png");
		smooth();
		
	}

	void startGame(){
		textSize(30);
		text("Get 10 points before the bug does!", 300, height/2);
		text("Press 'SPACE' to start", 380, height/2 + 50);
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
		y2 = (float) (y + w*1.65);
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
		if(bulletX> bugX-bugWidth-bulletR/2 && bulletX<bugX+bugWidth+bulletR/2 && bulletY<bugY+(bugWidth*1.65) && bulletY>bugY){
			System.out.println("hit");
			shoot = 0;
			newLocation = 1;
			g = 0;
			b = 0;
			score = score + 1;
			downgradeBug();
		}
	}

	void displayScore(){
		fill(255,255,255);
		textSize(20);
		String displayText = "Score : " + score;
		text(displayText, 50, 50);
	}

	void displayBugScore(){
		fill(255,255,255);
		textSize(20);
		String displayText = "Bugs Score : " + bugscore;
		text(displayText, 800, 50);
	}

	void moveBug(float x, float y){
		float distX, distY, dist, steps;

		if(newLocation == 1){
			dist = 0;
			distX = 0;
			distY = 0;
			
			while(dist<180){
				newLocX = random(0+bugWidth, width-bugWidth);
				newLocY = random(0+bugWidth, height-150-bugWidth);

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
			float i = bugWidth/2;

			if(bugX > newLocX){
				if(bugY > newLocY){
					if((bugX - newLocX)<i && (bugY - newLocY)<i){
						upgradeBug();
						newLocation = 1;
					}
				}

				else{
					if((bugX - newLocX)<i && (newLocY - bugY)<i){
						upgradeBug();
						newLocation = 1;
					}
				}
			}

			else{
				if(bugY > newLocY){
					if((newLocX - bugX)<i && (bugY - newLocY)<i){
						upgradeBug();
						newLocation = 1;
					}
				}

				else{
					if((newLocX) - bugX<5 && (newLocY - bugY)<5){
						upgradeBug();
						newLocation = 1;
					}
				}
			}
		}

		fill(255,255,255);
		//circle(newLocX, newLocY+bugWidth, 10);
		image(img, newLocX, newLocY, 30, 30);


	}

	void upgradeBug(){
		bugscore = bugscore + 1;
		bugWidth = bugWidth + bugGrowSize;
		bugSpeed = (float) (bugSpeed + bugGrowSpeed);

		if(bugWidth == 40){
			gameOver();
		}
	}

	void downgradeBug(){
		bugscore = bugscore - 1;
		if(bugscore < 0){

			bugscore = 0;
		}
		bugWidth = bugWidth - bugGrowSize;
		bugSpeed = (float) (bugSpeed - bugGrowSpeed);
		if(bugWidth < 30){
			bugWidth = 30;
		}

		if(bugSpeed < 3){
			bugSpeed = 3;
		}
	}

	void checkGameState(){
		if(score == 10 || bugscore == 10){
			end = 1;
		}
	}

	void gameOver(){
		textSize(30);
		text("Game Over!",400, height/2);
	}

    public void keyPressed()
	{
		if(start == 1){
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

		if(start == 0){
			if(key == ' '){
				start = 1;
			}
		}
	}



	public void draw()
	{
		background(18, 23, 41);

		if(start == 0){
			startGame();
		}

		if(start == 1 && end == 0){
			drawPlayer(playerX, playerY, playerWidth);
			drawBug(bugX, bugY, bugWidth);
			moveBug(bugX, bugY);
			checkShoot();
			collisionCheck();
			displayScore();
			displayBugScore();
			checkGameState();
		}

		if(end == 1){
			gameOver();
		}
	}
}
