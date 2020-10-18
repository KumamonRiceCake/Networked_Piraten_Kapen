package a1.piratesGame;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class GridTest extends TestCase
{
    PiratesGame PiratesGame = new PiratesGame();
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GridTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( GridTest.class );
    }

    /**
     * Test die with 3 skulls on first roll  -> interface reports death & end of turn
     */
    public void testRow48() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        String[] dieRoll = {"parrot", "skull", "monkey", "gold", "skull", "gold", "skull", "sword"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        assertTrue(PiratesGame.isDead(dieRoll));
    }
    
    /**
     * Test roll 1 skull, 4 parrots, 3 swords, hold parrots, reroll swords, get 2 skulls 1 sword  die
     */
    public void testRow49() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        String[] dieRoll = {"skull", "parrot", "parrot", "parrot", "parrot", "sword", "sword", "sword"};
        String[] holdingSelection = {"1", "2", "3", "4"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection);
        String[] reRoll = PiratesGame.reroll(dieRoll, holdingSelection);
        reRoll[5] = "skull";
        reRoll[6] = "skull";
        reRoll[7] = "sword";
        PiratesGame.getPlayer().setFinalRoll(reRoll);
        assertTrue(PiratesGame.isDead(reRoll));
    }
    
    /**
     * Test roll 2 skulls, 4 parrots, 2 swords, hold parrots, reroll swords, get 1 skull 1 sword  die
     */
    public void testRow50() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        String[] dieRoll = {"skull", "skull", "parrot", "parrot", "parrot", "parrot", "sword", "sword"};
        String[] holdingSelection = {"2", "3", "4", "5"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection);
        String[] reRoll = PiratesGame.reroll(dieRoll, holdingSelection);
        reRoll[6] = "skull";
        reRoll[7] = "sword";
        PiratesGame.getPlayer().setFinalRoll(reRoll);
        assertTrue(PiratesGame.isDead(reRoll));
    }
    
    /**
     * Test roll 1 skull, 4 parrots, 3 swords, hold parrots, reroll swords, get 1 skull 2 monkeys
     */
    public void testRow51() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        String[] dieRoll = {"skull", "parrot", "parrot", "parrot", "parrot", "sword", "sword", "sword"};
        String[] holdingSelection = {"1", "2", "3", "4"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection);
        String[] reRoll = PiratesGame.reroll(dieRoll, holdingSelection);
        reRoll[5] = "skull";
        reRoll[6] = "monkey";
        reRoll[7] = "monkey";
        PiratesGame.getPlayer().setFinalRoll(reRoll);
        assertFalse(PiratesGame.isDead(reRoll));
    }
    
    /**
     * Test reroll 2 monkeys, get 1 skull 1 monkey and die
     */
    public void testRow52() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        String[] dieRoll = {"skull", "skull", "parrot", "parrot", "parrot", "sword", "monkey", "monkey"};
        String[] holdingSelection = {"2", "3", "4", "5"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection);
        String[] reRoll = PiratesGame.reroll(dieRoll, holdingSelection);
        reRoll[6] = "skull";
        reRoll[7] = "monkey";
        PiratesGame.getPlayer().setFinalRoll(reRoll);
        assertTrue(PiratesGame.isDead(reRoll));
    }
    
    /**
     * Test score first roll with nothing but 2 diamonds and 2 coins and FC is captain (SC 800)
     */
    public void testRow53() {
        // double scoring case
        Player player1 = new Player("Kim");
        player1.setScore(0);
        PiratesGame.playEntry(player1);
        PiratesGame.getPlayers("Kim").setFortune("captain");

        // roll equivalent to 400 points
        String[] roll = {"diamond", "parrot", "gold", "diamond", "gold", "monkey", "sword", "skull"};
        PiratesGame.getPlayers("Kim").setFinalRoll(roll);
        PiratesGame.scoreCaptain(PiratesGame.getPlayers("Kim"));

        assertEquals(800, PiratesGame.getPlayers("Kim").getScore());
    }
    
    /**
     * Test get set of 2 monkeys on first roll, get 3rd monkey on 2nd roll (SC 200 since FC is coin)
     */
    public void testRow54() {
        Player player1 = new Player("Kim");
        player1.setScore(0);
        PiratesGame.setPlayer(player1);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = new String[8];
        dieRoll[0] = "skull";
        dieRoll[1] = "monkey";
        dieRoll[2] = "monkey";
        dieRoll[3] = "sword";
        dieRoll[4] = "sword";
        dieRoll[5] = "parrot";
        dieRoll[6] = "skull";
        dieRoll[7] = "parrot";
        String[] holdingSelection = {"1", "2"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection);
        String[] reRoll = PiratesGame.reroll(dieRoll, holdingSelection);
        reRoll[3] = "sword";
        reRoll[4] = "parrot";
        reRoll[5] = "parrot";
        reRoll[6] = "skull";
        reRoll[7] = "monkey";
        PiratesGame.getPlayer().setFinalRoll(reRoll);
        PiratesGame.scoreForRound(player1);
        assertEquals(200, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score 2 sets of 3 (monkey, swords) in RTS on first roll   (SC 300)
     */
    public void testRow55() {
        Player player1 = new Player("Kim");
        player1.setScore(0);
        PiratesGame.setPlayer(player1);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = new String[8];
        dieRoll[0] = "skull";
        dieRoll[1] = "monkey";
        dieRoll[2] = "monkey";
        dieRoll[3] = "monkey";
        dieRoll[4] = "sword";
        dieRoll[5] = "sword";
        dieRoll[6] = "sword";
        dieRoll[7] = "parrot";
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(player1);
        assertEquals(300, PiratesGame.getPlayer().getScore());
    }

    /**
     * Test score 2 sets of 3 (monkey, parrots) in RTS using 2 rolls   (SC 300)
     */
    public void testRow56() {
        Player player1 = new Player("Kim");
        player1.setScore(0);
        PiratesGame.setPlayer(player1);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = new String[8];
        dieRoll[0] = "skull";
        dieRoll[1] = "monkey";
        dieRoll[2] = "monkey";
        dieRoll[3] = "sword";
        dieRoll[4] = "sword";
        dieRoll[5] = "parrot";
        dieRoll[6] = "skull";
        dieRoll[7] = "parrot";
        String[] holdingSelection = {"1", "2", "5", "7"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection);
        String[] reRoll = PiratesGame.reroll(dieRoll, holdingSelection);
        reRoll[3] = "parrot";
        reRoll[4] = "monkey";
        PiratesGame.getPlayer().setFinalRoll(reRoll);
        PiratesGame.scoreForRound(player1);
        assertEquals(300, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score a set of 3 diamonds correctly (i.e., 400 points)   (SC 500)
     */
    public void testRow57() {
        Player player1 = new Player("Kim");
        player1.setScore(0);
        PiratesGame.setPlayer(player1);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = new String[8];
        dieRoll[0] = "skull";
        dieRoll[1] = "diamond";
        dieRoll[2] = "diamond";
        dieRoll[3] = "diamond";
        dieRoll[4] = "sword";
        dieRoll[5] = "parrot";
        dieRoll[6] = "skull";
        dieRoll[7] = "parrot";
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(player1);
        assertEquals(500, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score a set of 4 coins correctly (i.e., 200 + 400 points) with FC is a diamond (SC 700)
     */
    public void testRow58() {
        Player player1 = new Player("Kim");
        player1.setScore(0);
        PiratesGame.setPlayer(player1);
        PiratesGame.getPlayer().setFortune("diamond");
        String[] dieRoll = new String[8];
        dieRoll[0] = "monkey";
        dieRoll[1] = "gold";
        dieRoll[2] = "gold";
        dieRoll[3] = "gold";
        dieRoll[4] = "gold";
        dieRoll[5] = "parrot";
        dieRoll[6] = "skull";
        dieRoll[7] = "parrot";
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(player1);
        assertEquals(700, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score set of 3 swords and set of 4 parrots correctly on first roll (SC 400 because of FC)
     */
    public void testRow59() {
        Player player1 = new Player("Kim");
        player1.setScore(0);
        PiratesGame.setPlayer(player1);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = new String[8];
        dieRoll[0] = "skull";
        dieRoll[1] = "sword";
        dieRoll[2] = "sword";
        dieRoll[3] = "sword";
        dieRoll[4] = "parrot";
        dieRoll[5] = "parrot";
        dieRoll[6] = "parrot";
        dieRoll[7] = "parrot";
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(player1);
        assertEquals(400, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score set of 3 coins+ FC and set of 4 swords correctly over several rolls (SC = 200+400+200 = 800)
     */
    public void testRow60() {
        Player player1 = new Player("Kim");
        player1.setScore(0);
        PiratesGame.setPlayer(player1);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = new String[8];
        dieRoll[0] = "parrot";
        dieRoll[1] = "gold";
        dieRoll[2] = "gold";
        dieRoll[3] = "gold";
        dieRoll[4] = "sword";
        dieRoll[5] = "sword";
        dieRoll[6] = "sword";
        dieRoll[7] = "sword";
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(player1);
        assertEquals(800, PiratesGame.getPlayer().getScore());

        dieRoll[0] = "sword";
        dieRoll[1] = "gold";
        dieRoll[2] = "parrot";
        dieRoll[3] = "gold";
        dieRoll[4] = "sword";
        dieRoll[5] = "sword";
        dieRoll[6] = "sword";
        dieRoll[7] = "gold";
        PiratesGame.getPlayer().setScore(0);
        PiratesGame.getPlayer().resetHeldDie();
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(player1);
        assertEquals(800, PiratesGame.getPlayer().getScore());
        
        dieRoll[0] = "sword";
        dieRoll[1] = "gold";
        dieRoll[2] = "monkey";
        dieRoll[3] = "sword";
        dieRoll[4] = "gold";
        dieRoll[5] = "sword";
        dieRoll[6] = "gold";
        dieRoll[7] = "sword";
        PiratesGame.getPlayer().setScore(0);
        PiratesGame.getPlayer().resetHeldDie();
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(player1);
        assertEquals(800, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test same as previous row but with captain fortune card  (SC = (100 + + 300 + 200)*2 = 1200)
     */
    public void testRow61() {
        Player player1 = new Player("Kim");
        player1.setScore(0);
        PiratesGame.setPlayer(player1);
        PiratesGame.getPlayer().setFortune("captain");
        String[] dieRoll = new String[8];
        dieRoll[0] = "parrot";
        dieRoll[1] = "gold";
        dieRoll[2] = "gold";
        dieRoll[3] = "gold";
        dieRoll[4] = "sword";
        dieRoll[5] = "sword";
        dieRoll[6] = "sword";
        dieRoll[7] = "sword";
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(player1);
        assertEquals(1200, PiratesGame.getPlayer().getScore());
    }

    /**
     * Test score set of 5 swords over 3 rolls (SC 600)
     */
    public void testRow62() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = {"diamond", "parrot", "parrot", "monkey", "parrot", "sword", "sword", "sword"};
        String[] holdingSelection1 = {"6", "7"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);

        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        reRoll1[0] = "gold";
        reRoll1[1] = "sword";
        reRoll1[2] = "parrot";
        reRoll1[3] = "sword";
        reRoll1[4] = "diamond";
        reRoll1[5] = "monkey";
        String[] holdingSelection2 = {"1", "3", "6", "7"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection2);

        String[] reRoll2 = PiratesGame.reroll(reRoll1, holdingSelection2);
        reRoll2[0] = "skull";
        reRoll2[2] = "sword";
        reRoll2[4] = "monkey";
        reRoll2[5] = "parrot";
        PiratesGame.getPlayer().setFinalRoll(reRoll2);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(600, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score set of 6 monkeys on first roll (SC 1100)
     */
    public void testRow63() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = {"monkey", "monkey", "parrot", "monkey", "sword", "monkey", "monkey", "monkey"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(1100, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score set of 7 parrots on first roll (SC 2100)
     */
    public void testRow64() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = {"parrot", "parrot", "parrot", "monkey", "parrot", "parrot", "parrot", "parrot"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(2100, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score set of 8 coins on first roll (SC 5400)  seq of 8 + 9 coins +  full chest (if you have it)
     */
    public void testRow65() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = {"gold", "gold", "gold", "gold", "gold", "gold", "gold", "gold"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(5400, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score set of 8 coins on first roll and FC is diamond (SC 5400)
     */
    public void testRow66() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("diamond");
        String[] dieRoll = {"gold", "gold", "gold", "gold", "gold", "gold", "gold", "gold"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(5400, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score set of 8 swords on first roll and FC is captain (SC 4500x2 = 9000) if you have full chest
     */
    public void testRow67() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("captain");
        String[] dieRoll = {"sword", "sword", "sword", "sword", "sword", "sword", "sword", "sword"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(9000, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score set of 8 monkeys over several rolls (SC 4600 because of FC is coin and full chest)
     */
    public void testRow68() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = {"diamond", "parrot", "parrot", "monkey", "parrot", "sword", "sword", "sword"};
        String[] holdingSelection1 = {"3"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);

        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        reRoll1[0] = "gold";
        reRoll1[1] = "monkey";
        reRoll1[2] = "monkey";
        reRoll1[4] = "diamond";
        reRoll1[5] = "monkey";
        reRoll1[6] = "diamond";
        reRoll1[7] = "monkey";
        String[] holdingSelection2 = {"1", "2", "3", "5", "7"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection2);

        String[] reRoll2 = PiratesGame.reroll(reRoll1, holdingSelection2);
        reRoll2[0] = "monkey";
        reRoll2[4] = "monkey";
        reRoll2[6] = "monkey";
        PiratesGame.getPlayer().setFinalRoll(reRoll2);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(4600, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score a set of 2 diamonds over 2 rolls with FC is diamond (SC 400)
     */
    public void testRow69() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("diamond");
        String[] dieRoll = {"gold", "parrot", "parrot", "monkey", "diamond", "sword", "sword", "sword"};
        String[] holdingSelection1 = {"4"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);

        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        reRoll1[0] = "diamond";
        reRoll1[1] = "sword";
        reRoll1[2] = "monkey";
        reRoll1[3] = "sword";
        reRoll1[5] = "parrot";
        reRoll1[6] = "parrot";
        reRoll1[7] = "monkey";

        PiratesGame.getPlayer().setFinalRoll(reRoll1);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(400, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score a set of 3 diamonds over 2 rolls (SC 500)
     */
    public void testRow70() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = {"gold", "parrot", "parrot", "monkey", "diamond", "sword", "sword", "sword"};
        String[] holdingSelection1 = {"4"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);

        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        reRoll1[0] = "diamond";
        reRoll1[1] = "sword";
        reRoll1[2] = "monkey";
        reRoll1[3] = "sword";
        reRoll1[5] = "diamond";
        reRoll1[6] = "parrot";
        reRoll1[7] = "monkey";

        PiratesGame.getPlayer().setFinalRoll(reRoll1);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(500, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score a set of 3 coins over 2 rolls  (SC 600)
     */
    public void testRow71() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = {"gold", "parrot", "parrot", "monkey", "diamond", "sword", "gold", "sword"};
        String[] holdingSelection1 = {"0", "6"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);

        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        
        reRoll1[1] = "sword";
        reRoll1[2] = "monkey";
        reRoll1[3] = "sword";
        reRoll1[4] = "gold";
        reRoll1[5] = "parrot";
        reRoll1[7] = "monkey";

        PiratesGame.getPlayer().setFinalRoll(reRoll1);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(600, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score a set of 3 coins over 2 rolls  with FC is diamond (SC 500)
     */
    public void testRow72() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("diamond");
        String[] dieRoll = {"gold", "parrot", "parrot", "monkey", "diamond", "sword", "gold", "sword"};
        String[] holdingSelection1 = {"0", "6"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);

        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        
        reRoll1[1] = "sword";
        reRoll1[2] = "monkey";
        reRoll1[3] = "sword";
        reRoll1[4] = "gold";
        reRoll1[5] = "parrot";
        reRoll1[7] = "monkey";

        PiratesGame.getPlayer().setFinalRoll(reRoll1);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(500, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test score a set of 4 monkeys and a set of 3 coins (including the COIN fortune card) (SC 600)
     */
    public void testRow73() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = {"monkey", "parrot", "monkey", "sword", "monkey", "gold", "monkey", "gold"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(600, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test get 7 swords on first roll, try to roll the 8 die by itself -> interface reports not allowed
     */
    public void testRow75() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = {"sword", "sword", "sword", "monkey", "sword", "sword", "sword", "sword"};
        for (int i=0; i<8; i++)
            dieRoll[i] = PiratesGame.rollDie();
    }
    
    /**
     * Test roll 2 skulls, reroll one of them due to sorceress, then go to next round of turn
     */
    public void testRow80() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("sorceress");
        String[] dieRoll = {"skull", "parrot", "skull", "monkey", "diamond", "sword", "gold", "sword"};
        String[] holdingSelection1 = new String[8];
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);

        String[] reRoll1 = PiratesGame.sorceressReroll(dieRoll, holdingSelection1);
        PiratesGame.getPlayer().setFinalRoll(reRoll1);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        PiratesGame.getPlayer().nextRound();
    }
    
    /**
     * Test roll no skulls, then next round roll 1 skull and reroll for it, then score 
     */
    public void testRow81() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("sorceress");
        String[] dieRoll = {"parrot", "parrot", "diamond", "monkey", "diamond", "sword", "gold", "sword"};
        String[] holdingSelection1 = new String[8];
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        PiratesGame.getPlayer().nextRound();

        String[] dieRoll2 = {"diamond", "gold", "skull", "gold", "diamond", "parrot", "monkey", "gold"};
        String[] holdingSelection2 = new String[8];
        PiratesGame.getPlayer().holdDie(dieRoll2, holdingSelection2);
        String[] reRoll1 = PiratesGame.sorceressReroll(dieRoll2, holdingSelection2);
        PiratesGame.getPlayer().setFinalRoll(reRoll1);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
    }
    
    /**
     * Test roll no skulls, then next round roll 1 skull and reroll for it, then go to next round 
     */
    public void testRow82() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("sorceress");
        String[] dieRoll = {"parrot", "parrot", "diamond", "monkey", "diamond", "sword", "gold", "sword"};
        String[] holdingSelection1 = new String[8];
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        PiratesGame.getPlayer().nextRound();

        String[] dieRoll2 = {"diamond", "gold", "skull", "gold", "diamond", "parrot", "monkey", "gold"};
        String[] holdingSelection2 = new String[8];
        PiratesGame.getPlayer().holdDie(dieRoll2, holdingSelection2);
        String[] reRoll1 = PiratesGame.sorceressReroll(dieRoll2, holdingSelection2);
        PiratesGame.getPlayer().setFinalRoll(reRoll1);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        PiratesGame.getPlayer().nextRound();
    }
    
    /**
     * Test first roll gets 3 monkeys 3 parrots  1 skull 1 coin  SC = 1100  (i.e., sequence of of 6 + coin)
     */
    public void testRow85() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("monkey business");
        String[] dieRoll = {"parrot", "monkey", "parrot", "monkey", "skull", "monkey", "gold", "parrot"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(1100, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * Test over several rolls: 2 monkeys, 1 parrot, 2 coins, 1 diamond, 2 swords  SC 400
     */
    public void testRow86() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("monkey business");
        String[] dieRoll = {"diamond", "parrot", "parrot", "monkey", "parrot", "sword", "sword", "sword"};
        String[] holdingSelection1 = {"0", "1", "3", "5", "6"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);

        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        reRoll1[2] = "monkey";
        reRoll1[4] = "monkey";
        reRoll1[7] = "diamond";
        String[] holdingSelection2 = {"0", "1", "2", "3", "5", "6"};
        PiratesGame.getPlayer().holdDie(reRoll1, holdingSelection2);

        String[] reRoll2 = PiratesGame.reroll(reRoll1, holdingSelection2);
        reRoll2[4] = "gold";
        reRoll2[7] = "gold";
        PiratesGame.getPlayer().setFinalRoll(reRoll2);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(400, PiratesGame.getPlayer().getScore());
    }

    /**
     * Test over several rolls get 3 monkeys, 4 parrots, 1 sword    SC 2000 (ie seq of 7)
     */
    public void testRow87() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("monkey business");
        String[] dieRoll = {"diamond", "parrot", "diamond", "monkey", "gold", "sword", "gold", "parrot"};
        String[] holdingSelection1 = {"1", "3", "5", "7"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);

        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        reRoll1[0] = "monkey";
        reRoll1[2] = "parrot";
        reRoll1[4] = "gold";
        reRoll1[6] = "diamond";
        String[] holdingSelection2 = {"0", "1", "2", "3", "5", "7"};
        PiratesGame.getPlayer().holdDie(reRoll1, holdingSelection2);

        String[] reRoll2 = PiratesGame.reroll(reRoll1, holdingSelection2);
        reRoll2[4] = "parrot";
        reRoll2[6] = "monkey";
        PiratesGame.getPlayer().setFinalRoll(reRoll2);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(2000, PiratesGame.getPlayer().getScore());
    }

    /**
     * roll 3 parrots, 2 swords, 2 diamonds, 1 coin
     * put 2 diamonds and 1 coin in chest
     * then reroll 2 swords and get 2 parrots put 5 parrots in chest and take out 2 diamonds & coin
     * then reroll the 3 dice and get 1 skull, 1 coin and a parrot
     * score 6 parrots + 1 coin for 1100 points
     */
    public void testRow90() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("treasure chest");
        String[] dieRoll = {"diamond", "parrot", "diamond", "parrot", "gold", "sword", "sword", "parrot"};
        ArrayList<String> tChest = new ArrayList<String>();
        tChest.add("diamond");
        tChest.add("diamond");
        tChest.add("gold");
        //PiratesGame.getPlayer().setTreasureChest(tChest);
        String[] holdingSelection1 = {"0", "1", "2", "3", "4", "7"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);

        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        reRoll1[5] = "parrot";
        reRoll1[6] = "parrot";
        tChest.removeAll(tChest);
        tChest.add("parrot");
        tChest.add("parrot");
        tChest.add("parrot");
        tChest.add("parrot");
        tChest.add("parrot");
        //PiratesGame.getPlayer().setTreasureChest(tChest);
        String[] holdingSelection2 = {"1", "3", "5", "6", "7"};
        PiratesGame.getPlayer().holdDie(reRoll1, holdingSelection2);

        String[] reRoll2 = PiratesGame.reroll(reRoll1, holdingSelection2);
        reRoll2[0] = "skull";
        reRoll2[2] = "gold";
        reRoll2[4] = "parrot";
        PiratesGame.getPlayer().setFinalRoll(reRoll2);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(1100, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * roll 2 skulls, 3 parrots, 3 coins   put 3 coins in chest
     * then rerolls 3 parrots and get 2 diamonds 1 coin    put coin in chest (now 4)
     * then reroll 2 diamonds and get 1 skull 1 coin     SC for chest only = 400 + 200 = 600
     * also interface reports death & end of turn
     */
    public void testRow95() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("treasure chest");
        String[] dieRoll = {"skull", "parrot", "skull", "parrot", "gold", "gold", "gold", "parrot"};
        ArrayList<String> tChest = new ArrayList<String>();
        tChest.add("gold");
        tChest.add("gold");
        tChest.add("gold");
        //PiratesGame.getPlayer().setTreasureChest(tChest);
        String[] holdingSelection1 = {"4", "5", "6"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);

        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        reRoll1[1] = "diamond";
        reRoll1[3] = "gold";
        reRoll1[7] = "diamond";
        tChest.add("gold");
        //PiratesGame.getPlayer().setTreasureChest(tChest);
        String[] holdingSelection2 = {"3", "4", "5", "6"};
        PiratesGame.getPlayer().holdDie(reRoll1, holdingSelection2);

        String[] reRoll2 = PiratesGame.reroll(reRoll1, holdingSelection2);
        reRoll2[1] = "skull";
        reRoll2[7] = "gold";
        PiratesGame.getPlayer().setFinalRoll(reRoll2);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(600, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * 3 monkeys, 3 swords, 1 diamond, 1 parrot FC: coin   => SC 400  (ie no bonus)
     */
    public void testRow101() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = {"monkey", "monkey", "monkey", "sword", "sword", "sword", "diamond", "parrot"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(400, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * 3 monkeys, 3 swords, 2 coins FC: captain   => SC (100+100+200+500)*2 =  1800
     */
    public void testRow102() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("captain");
        String[] dieRoll = {"monkey", "monkey", "monkey", "sword", "sword", "sword", "gold", "gold"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(1800, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * 3 monkeys, 4 swords, 1 diamond, FC: coin   => SC 1000  (ie 100++200+100+100+bonus)
     */
    public void testRow103() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll = {"monkey", "monkey", "monkey", "sword", "sword", "sword", "sword", "diamond"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(1000, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * FC: 2 sword sea battle, first  roll:  4 monkeys, 1 sword, 2 parrots and a coin
     * then reroll 2 parrots and get coin and 2nd sword
     * score is: 200 (coins) + 200 (monkeys) + 300 (swords of battle) + 500 (full chest) = 1200
     */
    public void testRow104() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("2 sabre sea battle");
        
        String[] dieRoll = {"monkey", "parrot", "monkey", "parrot", "gold", "monkey", "monkey", "sword"};
        String[] holdingSelection1 = {"0", "2", "4", "5", "6", "7"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);

        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        reRoll1[1] = "sword";
        reRoll1[3] = "gold";
        PiratesGame.getPlayer().setFinalRoll(reRoll1);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(1200, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * FC: monkey business and RTS: 2 monkeys, 1 parrot, 2 coins, 3 diamonds   SC 1200 (bonus)
     */
    public void testRow107() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("monkey business");
        
        String[] dieRoll = {"monkey", "parrot", "monkey", "gold", "gold", "diamond", "diamond", "diamond"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(1200, PiratesGame.getPlayer().getScore());
    }

    /**
     * die by rolling one skull and having a FC with two skulls
     */
    public void testRow110() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("2 skull");
        
        String[] dieRoll = {"skull", "parrot", "monkey", "gold", "gold", "diamond", "diamond", "diamond"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertTrue(PiratesGame.isDead(dieRoll));
    }

    /**
     * die by rolling 2 skulls and having a FC with 1 skull
     */
    public void testRow111() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("1 skull");
        
        String[] dieRoll = {"skull", "parrot", "monkey", "gold", "skull", "diamond", "diamond", "diamond"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertTrue(PiratesGame.isDead(dieRoll));
    }
    
    /**
     * roll 2 skulls AND have a FC with two skulls: roll 2 skulls next roll, then 1 skull => -700 
     */
    public void testRow112() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("2 skull");
        
        String[] dieRoll = {"skull", "parrot", "monkey", "gold", "skull", "diamond", "diamond", "diamond"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        assertTrue(PiratesGame.isSkullIsland(dieRoll));
        
        String[] holdingSelection = new String[8];
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection);
        String[] reRoll = PiratesGame.reroll(dieRoll, holdingSelection);
        reRoll[1] = "skull";
        reRoll[3] = "skull";
        reRoll[2] = "diamond";
        reRoll[5] = "gold";
        reRoll[6] = "monkey";
        reRoll[7] = "monkey";
        PiratesGame.getPlayer().holdDie(reRoll, holdingSelection);
        String[] reRoll2 = PiratesGame.reroll(reRoll, holdingSelection);
        reRoll2[2] = "diamond";
        reRoll2[5] = "gold";
        reRoll2[6] = "skull";
        reRoll2[7] = "monkey";
        
        //assertEquals(-700, PiratesGame.deductSkullIsland(player, reRoll2));
    }
    
    /**
     * roll 3 skulls AND have a FC with two skulls: roll no skulls next roll  => -500
     */
    public void testRow113() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("2 skull");
        
        String[] dieRoll = {"skull", "parrot", "monkey", "gold", "skull", "diamond", "skull", "diamond"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        assertTrue(PiratesGame.isSkullIsland(dieRoll));
        
        String[] holdingSelection = new String[8];
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection);
        String[] reRoll = PiratesGame.reroll(dieRoll, holdingSelection);
        reRoll[1] = "diamond";
        reRoll[2] = "parrot";
        reRoll[3] = "diamond";
        reRoll[5] = "gold";
        reRoll[7] = "monkey";
        //assertEquals(-500, PiratesGame.deductSkullIsland(player, reRoll));
    }
    
    /**
     * roll 3 skulls AND have a FC with 1 skull: roll 1 skull next roll then none => -500
     */
    public void testRow114() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("1 skull");
        
        String[] dieRoll = {"skull", "parrot", "monkey", "gold", "skull", "diamond", "skull", "diamond"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);
        assertTrue(PiratesGame.isSkullIsland(dieRoll));
        
        String[] holdingSelection = new String[8];
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection);
        String[] reRoll = PiratesGame.reroll(dieRoll, holdingSelection);
        reRoll[1] = "skull";
        reRoll[2] = "diamond";
        reRoll[3] = "monkey";
        reRoll[5] = "parrot";
        reRoll[7] = "monkey";
        PiratesGame.getPlayer().holdDie(reRoll, holdingSelection);
        String[] reRoll2 = PiratesGame.reroll(reRoll, holdingSelection);
        reRoll2[2] = "diamond";
        reRoll2[3] = "monkey";
        reRoll2[5] = "parrot";
        reRoll2[7] = "monkey";
        PiratesGame.getPlayer().setFinalRoll(reRoll2);
        //assertEquals(-500, PiratesGame.deductSkullIsland(player, reRoll2));
    }
    
    /**
     * show deduction received cannot make your score negative
     */
    public void testRow115() {        
        Player player1 = new Player("Kim");
        player1.setScore(0);
        player1.setFortune("2 skull");
        PiratesGame.playEntry(player1);
        
        Player player2 = new Player("Jeong");
        player2.setScore(1000);
        player2.setFortune("2 skull");
        PiratesGame.playEntry(player2);
        
        Player player3 = new Player("Won");
        player3.setScore(0);
        player3.setFortune("2 skull");
        PiratesGame.playEntry(player3);
        
        
        String[] dieRoll = {"skull", "parrot", "monkey", "gold", "skull", "diamond", "skull", "diamond"};
        PiratesGame.getPlayers("Kim").setFinalRoll(dieRoll);
        
        PiratesGame.deductSkullIsland(PiratesGame.getPlayers("Kim"));  // -500
        assertEquals(500, PiratesGame.getPlayers("Jeong").getScore());
        assertEquals(0, PiratesGame.getPlayers("Won").getScore());
    }

    /**
     * FC 2 swords, die on first roll   => lose 300 points
     */
    public void testRow118() {  
        Player player = new Player("Kim");
        player.setScore(1000);
        player.setFortune("2 sabre sea battle");
        PiratesGame.setPlayer(player);;
        
        String[] dieRoll = {"skull", "parrot", "monkey", "gold", "skull", "diamond", "skull", "diamond"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);

        assertTrue(PiratesGame.isDead(dieRoll));
        PiratesGame.scoreSeaBattle(player);
        assertEquals(700, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * FC 3 swords, die on first roll   => lose 500 points
     */
    public void testRow119() {  
        Player player = new Player("Kim");
        player.setScore(1000);
        player.setFortune("3 sabre sea battle");
        PiratesGame.setPlayer(player);;
        
        String[] dieRoll = {"skull", "parrot", "monkey", "gold", "skull", "diamond", "skull", "diamond"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);

        assertTrue(PiratesGame.isDead(dieRoll));
        PiratesGame.scoreForRound(player);
        assertEquals(500, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * FC 4 swords, die on first roll   => lose 1000 points
     */
    public void testRow120() {  
        Player player = new Player("Kim");
        player.setScore(2000);
        player.setFortune("4 sabre sea battle");
        PiratesGame.setPlayer(player);;
        
        String[] dieRoll = {"skull", "parrot", "monkey", "gold", "skull", "diamond", "skull", "diamond"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);

        assertTrue(PiratesGame.isDead(dieRoll));
        PiratesGame.scoreForRound(player);
        assertEquals(1000, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * show deduction received cannot make your score negative
     */
    public void testRow121() {  
        Player player = new Player("Kim");
        player.setScore(100);
        player.setFortune("3 sabre sea battle");
        PiratesGame.setPlayer(player);;
        
        String[] dieRoll = {"skull", "parrot", "monkey", "gold", "skull", "diamond", "skull", "diamond"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);

        assertTrue(PiratesGame.isDead(dieRoll));
        PiratesGame.scoreForRound(player);
        assertEquals(0, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * FC 2 swords, roll 3 monkeys 2 swords, 1 coin, 2 parrots  SC = 100 + 100 + 300 = 500
     */
    public void testRow122() {  
        Player player = new Player("Kim");
        player.setScore(0);
        player.setFortune("2 sabre sea battle");
        PiratesGame.setPlayer(player);;
        
        String[] dieRoll = {"monkey", "monkey", "monkey", "sword", "sword", "gold", "parrot", "parrot"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);

        PiratesGame.scoreForRound(player);
        assertEquals(500, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * FC 2 swords, roll 4 monkeys 1 sword, 1 skull  2 parrots
     * then reroll 2 parrots and get 1 sword and 1 skull   SC = 200 +  300 = 500
     */
    public void testRow123() {  
        Player player = new Player("Kim");
        player.setScore(0);
        player.setFortune("2 sabre sea battle");
        PiratesGame.setPlayer(player);
        
        String[] dieRoll = {"monkey", "monkey", "monkey", "monkey", "sword", "skull", "parrot", "parrot"};
        String[] holdingSelection1 = {"0", "1", "2", "3", "4", "5"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);

        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        reRoll1[6] = "sword";
        reRoll1[7] = "skull";
        PiratesGame.getPlayer().setFinalRoll(reRoll1);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(500, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * FC 3 swords, roll 3 monkeys 4 swords  SC = 100 + 200 + 500 = 800
     */
    public void testRow125() {  
        Player player = new Player("Kim");
        player.setScore(0);
        player.setFortune("3 sabre sea battle");
        PiratesGame.setPlayer(player);;
        
        String[] dieRoll = {"monkey", "monkey", "monkey", "sword", "sword", "sword", "sword", "parrot"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);

        PiratesGame.scoreForRound(player);
        assertEquals(800, PiratesGame.getPlayer().getScore());
    }
    
    /**
     * FC 3 swords, roll 4 monkeys 2 swords 2 skulls
     * then reroll 4 monkeys and get  2 skulls and 2 swords   -> DIE
     */
    public void testRow126() {  
        Player player = new Player("Kim");
        player.setScore(2000);
        player.setFortune("3 sabre sea battle");
        PiratesGame.setPlayer(player);;
        
        String[] dieRoll = {"monkey", "monkey", "monkey", "monkey", "sword", "sword", "skull", "skull"};
        String[] holdingSelection1 = {"4", "5", "6", "7"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);
        
        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        reRoll1[0] = "skull";
        reRoll1[1] = "skull";
        reRoll1[2] = "sword";
        reRoll1[3] = "sword";
        PiratesGame.getPlayer().setFinalRoll(reRoll1);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(1500, PiratesGame.getPlayer().getScore());
    }
            
    /**
     * FC 4 swords, roll 3 monkeys 4 swords 1 skull  SC = 100 +200 + 1000 = 1300
     */
    public void testRow128() {  
        Player player = new Player("Kim");
        player.setScore(0);
        player.setFortune("4 sabre sea battle");
        PiratesGame.setPlayer(player);;
        
        String[] dieRoll = {"monkey", "monkey", "monkey", "sword", "sword", "sword", "sword", "skull"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll);

        PiratesGame.scoreForRound(player);
        assertEquals(1300, PiratesGame.getPlayer().getScore());
    }        
            
    /**
     * FC 4 swords, roll 3 monkeys, 1 sword, 1 skull, 1 diamond, 2 parrots  
     * then reroll 2 parrots and get 2 swords thus you have 3 monkeys, 3 swords, 1 diamond, 1 skull
     * then reroll 3 monkeys and get  1 sword and 2 parrots  SC = 200 + 100 + 1000 = 1300
     */
    public void testRow129() {  
        Player player = new Player("Kim");
        player.setScore(0);
        player.setFortune("4 sabre sea battle");
        PiratesGame.setPlayer(player);;
        
        String[] dieRoll = {"monkey", "monkey", "monkey", "sword", "skull", "diamond", "parrot", "parrot"};
        String[] holdingSelection1 = {"0", "1", "2", "3", "4", "5"};
        PiratesGame.getPlayer().holdDie(dieRoll, holdingSelection1);
        
        String[] reRoll1 = PiratesGame.reroll(dieRoll, holdingSelection1);
        reRoll1[6] = "sword";
        reRoll1[7] = "sword";
        
        String[] holdingSelection2 = {"3", "4", "5", "6", "7"};
        PiratesGame.getPlayer().holdDie(reRoll1, holdingSelection2);
        
        String[] reRoll2 = PiratesGame.reroll(reRoll1, holdingSelection2);
        reRoll2[0] = "sword";
        reRoll2[1] = "parrot";
        reRoll2[2] = "parrot";
        
        PiratesGame.getPlayer().setFinalRoll(reRoll2);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertEquals(1300, PiratesGame.getPlayer().getScore());
    }
}
    

    