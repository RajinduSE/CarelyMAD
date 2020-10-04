package com.example.caring;

import com.example.caring.models.education.Mark;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private Mark mark;

    @Before
    public void setup(){
       mark = new Mark();
    }

    /*
     *@Author Rajindu
     * IT19111834
     */
    @Test
    public void calculateAverage() {

      double avg = mark.calculateAverage(100,10);
        assertEquals(10, avg,0.001);

    }

    /*
     *@Author Dilmi
     * IT19120980
     */
    @Test
    public void calcAverage(){

        double avg1 = mark.calculateAverage(200,3);
        assertEquals(66.666, avg1,0.001);
    }

    /*
     *@Author Dilumi
     * IT19213286
     */
    @Test
    public void calcAvg(){

        double avg1 = mark.calculateAverage(300,3);
        assertEquals(100, avg1,0.001);
    }
    /*
     *@Author Ayodya
     * IT19146898
     */
    @Test
    public void calculateAvg(){

        double avg1 = mark.calculateAverage(150,5);
        assertEquals(30, avg1,0.001);
    }
}