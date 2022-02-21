package ie.tudublin;

import processing.core.PApplet;

public class Arrays extends PApplet{

    float[] rainfall = {45,37,55,27,38,50,79,48,104,31,100,58};
    String[] months = {"JAN", "FEB", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUG", "SEP", "OCT", "NOV", "DEC"};
    int lowest_rainfall = (int) rainfall[0];
    int max_rainfall = (int) rainfall[0];

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

    public void draw(){
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

       


    }


}
