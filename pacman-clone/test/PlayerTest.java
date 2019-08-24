package edu.miracosta.cs113;
import org.junit.Test;
import org.junit.Assert;

public class PlayerTest {
    Map map;

    @Test
    public void testMove() {
        map = new Map("TestMap.txt");
        Player player = map.getPlayer();

        player.move(-1,0,map);
        Assert.assertTrue(player.getX() == 0 && player.getY() == 0 && map.getTile(0,0) == Map.PLAYER_TILE);
    }

    @Test
    public void testMoveBarrier() {
        map = new Map("TestMap.txt");
        Player player = map.getPlayer();

        player.move(-1,0,map);
        player.move(0,1,map);

        Assert.assertTrue(player.getX() == 0 && player.getY() == 0 && map.getTile(0,0) == Map.PLAYER_TILE);
    }

}
