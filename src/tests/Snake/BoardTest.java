package tests.Snake;

import main.Snake.Board;
import org.junit.Assert;
import org.junit.Test;


public class BoardTest{
    Board board = new Board();
    @Test
    public void sumTest(){
        Assert.assertEquals(board.sum(1,2),3);
    }
}