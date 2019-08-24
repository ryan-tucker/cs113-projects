package edu.miracosta.cs113;

import org.junit.Test;
import org.junit.Assert;

public class EnemyTest {
    Map map;

    @Test
    public void testMoveToPlayer() {
        map = new Map("TestMap.txt");
        Enemy enemy = map.getEnemies().get(0);
        enemy.move(0,-1,map);
        Assert.assertTrue(enemy.getX() == 1 && enemy.getY() == 0 && map.getTile(1,0) == Map.ENEMY_TILE);
    }

    @Test
    public void testMoveToBarrier() {
        map = new Map("TestMap.txt");
        Enemy enemy = map.getEnemies().get(0);
        enemy.move(-1,0,map);
        Assert.assertTrue(enemy.getX() == 1 && enemy.getY() == 1 && map.getTile(1,1) == Map.ENEMY_TILE);
    }

    @Test
    public void testMoveTowardsPlayer() {
        map = new Map("TestMap.txt");
        Enemy enemy = map.getEnemies().get(0);
        enemy.moveTowardsPlayer(map);
        Assert.assertTrue(enemy.getX() == 1 && enemy.getY() == 0 && map.getTile(1,0) == Map.ENEMY_TILE);
    }
}
