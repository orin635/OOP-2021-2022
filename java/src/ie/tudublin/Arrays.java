package ie.tudublin;

import processing.core.PApplet;

public class Arrays extends PApplet{

    float[] rainfall = {45,37,55,27,38,50,79,48,104,31,100,58};
    String[] months = {"JAN", "FEB", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUG", "SEP", "OCT", "NOV", "DEC"};
    int lowest_rainfall = (int) rainfall[0];
    int max_rainfall = (int) rainfall[0];
    int mode = 0;

    public void settings(){
        size(725, 500);
        
    }

    public void setup(){
        for(int i = 0; i<rainfall.length; i++){
            println(rainfall[i] + "\t" + months[i]);
        }

        int i = 0;
        for(float r:rainfall){
            println(r + "\t" + months[i]);
            i++;
        }

        for(int ii = rainfall.length - 1; ii >=0; ii--){
            println(rainfall[ii] + "\t" + months[ii]);
        }

        
        int total = 0;

        for(int j = 0; j<rainfall.length; j++){
            if(rainfall[j] < lowest_rainfall){
                lowest_rainfall = (int) rainfall[j];
            }

            if(rainfall[j] >  max_rainfall){
                max_rainfall = (int) rainfall[j];
            }

            total = (int) (total + rainfall[j]);

        }
        println(lowest_rainfall + "\t" + max_rainfall + "\t" + total + "\t" + total/rainfall.length);


    }


    public void keyPressed()
	{
		if (key >= '0' && key <='9')
		{
			mode = key - '0';
		}
		println(mode);
	}


    public void draw(){
        switch(mode)	
		{
            case 0:
            {
                colorMode(HSB);
                stroke(0);
                background(255);
                textSize(17);
                textAlign(CENTER, CENTER);
                text("Average Rainfall per Month", width/2, 20);
                fill(0);
                strokeWeight(5);
                line(50, 50, 50, height-50);
                line(50, height-50, width-50, height-50);
                int h = 0;
                int x = 70;

                for(int k = 0; k<rainfall.length; k++){

                    h = (int) map(rainfall[k], 0, max_rainfall, 0, 400);
                    rect(x, height-50, 30, -h);
                    textSize(12);
                    text(rainfall[k] + "ml", x+15, (450-h-10));
                    text(months[k], x+15, 460);
                    x = x + 50;

                }
                break;
            }


            case 1:
            {
                colorMode(HSB);
                background(0);
                float border = width * 0.1f;
                stroke(255);
                line(border, border, border, height - border);
                line(border, height - border, width - border, height - border);

                for(int i = 0; i <= 120; i +=10){
                    float y = map(i, 0, 120, height - border, border);
                    line(border - 5, y, border,y);
                    fill(255);
                    textAlign(CENTER, CENTER);
                    text(i, border /2, y);
                }

                float w = (width - (border * 2.0f))/ (float)rainfall.length;

                for(int i = 0; i < rainfall.length; i++ ){
                    float x = map(i, 0, rainfall.length, border, width-border); 
                    float c = map(i, 0, rainfall.length, 0, 255); 
                    stroke(255);
                    fill(c,255,255);
                    float h = map(rainfall[i], 0, 120, 0, -height + (border * 2.0f));
                    rect(x, height - border,w,h );
                    
                }

                break;
            }

            case 2:
            {
                colorMode(HSB);
                background(0);
                float r = width * 0.3f;
                float cx = width/2;
                float cy = height /2;
                stroke(255);

                circle(cx, cy, r * 2.0f);
                
                float sum = 0;
                for(int k = 0; k<rainfall.length; k++){
                    sum = sum + rainfall[k];
                }

                float start_angle = 0; 

                strokeWeight(2);
                int red = 0;
                float total_angle = 0;

                for(int k = 0; k<rainfall.length; k++){
                    fill(red,255,255);
                    red = red + 255/rainfall.length;
                    float move_angle = map(rainfall[k], 0, sum, 0, TWO_PI);
                    total_angle = total_angle + move_angle;
                    float angle = move_angle + start_angle;
                    arc(cx, cy, r*2.0f, r*2.0f, start_angle, angle);

                    start_angle = start_angle + move_angle;


                    float textX = cx+ (r+20)*cos((float) (total_angle-(move_angle/2)));
                    float textY = cy+ (r+20)*sin((float) (total_angle-(move_angle/2)));

                    fill(255);
                    text(months[k], textX, textY);

                    textX = cx+ (r-30)*cos((float) (total_angle-(move_angle/2)));
                    textY = cy+ (r-30)*sin((float) (total_angle-(move_angle/2)));

                    fill(0);
                    text(rainfall[k], textX, textY);



                }

                


                break;
            }

            case 3:
            {
                colorMode(HSB);
                stroke(0);
                background(255);
                textSize(17);
                textAlign(CENTER, CENTER);
                text("Average Rainfall per Month", width/2, 20);
                fill(0);
                strokeWeight(5);
                line(50, 50, 50, height-50);
                line(50, height-50, width-50, height-50);
                int h = 0;
                int x = 70;
                float last_x = x;
                float last_y = h;

                for(int k = 0; k<=months.length; k++){
                    float text_y = 50;
                    text_y = text_y + (400/12)*k;
                    int measure = 120 - 10*k;

                    line(35,text_y,50,text_y);
                    text(measure, 10, text_y);
                }

                for(int k = 0; k<rainfall.length; k++){
                    h = (int) map(rainfall[k], 0, 120, 0, 400);
                    if(k == 0){
                        last_y = h;
                    }
                    line(x, 450-h, last_x, 450-last_y);
                    last_x = x;
                    last_y = h;
                    textSize(12);
                    text(months[k], x+15, 460);
                    x = x + 50;
                }
            }
        }
       


    }


}
