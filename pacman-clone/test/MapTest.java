package edu.miracosta.cs113;
import org.junit.Assert;
import org.junit.Test;

public class MapTest {

    private Map map;

    @Test
    public void testMapFromFile() {
        map = new Map("TestMap.txt");


        String expected = "01\n" +
                "23\n";
        Assert.assertEquals(expected,map.toString());
    }

    @Test
    public void testGet1DIndex() {
        map = new Map("TestMap.txt");
        Assert.assertEquals(1, map.get1DIndex(0,1));
    }

    @Test
    public void testGet1DIndexSecondRow() {
        map = new Map("TestMap.txt");
        Assert.assertEquals(2, map.get1DIndex(1,0));
    }

    @Test
    public void get2DIndex() {
        map = new Map("TestMap.txt");
        int row;
        int column;
        int[] cords = map.get2DIndex(2);
        row = cords[0];
        column = cords[1];

        Assert.assertTrue(row == 1 && column == 0);
    }
}

