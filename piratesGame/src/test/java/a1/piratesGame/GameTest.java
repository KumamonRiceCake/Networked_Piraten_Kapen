package a1.piratesGame;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class GameTest extends TestCase
{
    PiratesGame PiratesGame = new PiratesGame();
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GameTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( GameTest.class );
    }

    /**
     * Test winner determination
     */
    public void testWinnerDetermination() {
        Player player1 = new Player("Kim");
        Player player2 = new Player("Jeong");
        Player player3 = new Player("Won");
        Player[] players = new Player[] {player1, player2, player3};
        
        PiratesGame.playEntry(player1);
        PiratesGame.playEntry(player2);
        PiratesGame.playEntry(player3);
        
        // Only 1 player reaches the score of 6000
        PiratesGame.setScore(0 ,6000);
        PiratesGame.setScore(1, 5000);
        PiratesGame.setScore(2, 4000);
        assertEquals(player1, PiratesGame.getWinner(players));
        
        // Only 2 players reach the score of 6000
        PiratesGame.setScore(0, 6000);
        PiratesGame.setScore(1, 6100);
        PiratesGame.setScore(2, 4000);
        assertEquals(player2, PiratesGame.getWinner(players));
        
        // No player reaches the score of 6000
        PiratesGame.setScore(0, 3000);
        PiratesGame.setScore(1, 5000);
        PiratesGame.setScore(2, 4000);
        assertNull(PiratesGame.getWinner(players));
    }
    
    /**
     * Test death with 3 skulls
     */
    public void testDeathWith3Skulls() {
        Player p = new Player("Kim");
        p.setFortune("gold");
        p.setScore(0);
        PiratesGame.setPlayer(p);
        
        // Die with 3 skulls
        String[] dieRoll1 = {"parrot", "skull", "monkey", "gold", "skull", "gold", "skull", "sword"};
        assertTrue(PiratesGame.isDead(dieRoll1));
        
        // Not die with less than 3 skulls
        String[] dieRoll2 = {"parrot", "skull", "monkey", "gold", "diamond", "gold", "skull", "sword"};
        assertFalse(PiratesGame.isDead(dieRoll2));
        
        // Not die with more than 3 skulls (skull island case)
        String[] dieRoll3 = {"parrot", "skull", "monkey", "skull", "skull", "gold", "skull", "sword"};
        assertFalse(PiratesGame.isDead(dieRoll3));
    }
 
    /**
     * Test skull island with more than 3 skulls
     */
    public void testSkullIsland() {
        // Skull island with more than 3 skulls
        Player p = new Player("Kim");
        PiratesGame.setPlayer(p);
        PiratesGame.getPlayer().setFortune("gold");
        String[] dieRoll1 = {"parrot", "skull", "monkey", "skull", "skull", "gold", "skull", "sword"};
        PiratesGame.getPlayer().setFinalRoll(dieRoll1);
        assertTrue(PiratesGame.isSkullIsland(dieRoll1));
    }
    
    /**
     * Test scoring points of sequencing
     */
    public void testPatternSequences() {
        // sequence of 3 parrots
        String[] parrotSequence = {"parrot", "diamond", "monkey", "skull", "skull", "gold", "parrot", "parrot"};
        assertEquals(100, PiratesGame.scoreSequence(parrotSequence));
        
        // sequence of 4 monkeys
        String[] monkeySequence = {"monkey", "monkey", "monkey", "gold", "diamond", "gold", "monkey", "sword"};
        assertEquals(200, PiratesGame.scoreSequence(monkeySequence));
        
        // sequence of 5 swords
        String[] swordSequence = {"sword", "sword", "monkey", "skull", "sword", "gold", "sword", "sword"};
        assertEquals(500, PiratesGame.scoreSequence(swordSequence));
        
        // sequence of 6 golds
        String[] goldSequence = {"gold", "skull", "gold", "skull", "gold", "gold", "gold", "gold"};
        assertEquals(1000, PiratesGame.scoreSequence(goldSequence));
        
        // sequence of 7 diamonds
        String[] diamondSequence1 = {"diamond", "diamond", "monkey", "diamond", "diamond", "diamond", "diamond", "diamond"};
        assertEquals(2000, PiratesGame.scoreSequence(diamondSequence1));
        
        // sequence of 8 diamonds
        String[] diamondSequence2 = {"diamond", "diamond", "diamond", "diamond", "diamond", "diamond", "diamond", "diamond"};
        assertEquals(4000, PiratesGame.scoreSequence(diamondSequence2));
        
        // no pattern
        String[] noSequence = {"diamond", "gold", "diamond", "skull", "sword", "skull", "parrot", "monkey"};
        assertEquals(0, PiratesGame.scoreSequence(noSequence));
    }
    
    /**
     * Test scoring points of diamonds and golds
     */
    public void testScoringDiamondsAndgolds() {
        // only golds
        String[] threegolds = {"parrot", "gold", "monkey", "skull", "skull", "gold", "parrot", "gold"};
        assertEquals(300, PiratesGame.scoreDiamondsAndGolds(threegolds));
        
        // only diamonds
        String[] fiveDiamonds = {"monkey", "diamond", "diamond", "diamond", "diamond", "diamond", "monkey", "sword"};
        assertEquals(500, PiratesGame.scoreDiamondsAndGolds(fiveDiamonds));
        
        // mix of golds and diamonds
        String[] mixOfTwo = {"sword", "diamond", "diamond", "skull", "parrot", "gold", "gold", "sword"};
        assertEquals(400, PiratesGame.scoreDiamondsAndGolds(mixOfTwo));
        
        // no golds and no diamonds
        String[] nogoldAndDiamonds = {"sword", "sword", "monkey", "skull", "sword", "skull", "sword", "sword"};
        assertEquals(0, PiratesGame.scoreDiamondsAndGolds(nogoldAndDiamonds));
    }
    
    /**
     * Test scoring full chest
     */
    public void testScoringFullChest() {
        // full chest case 1
        String[] fullChest1 = {"parrot", "parrot", "parrot", "parrot", "parrot", "monkey", "monkey", "monkey"};
        assertEquals(500, PiratesGame.scoreFullChest(fullChest1));
        
        // full chest case 2
        String[] fullChest2 = {"parrot", "parrot", "parrot", "parrot", "gold", "gold", "diamond", "diamond"};
        assertEquals(500, PiratesGame.scoreFullChest(fullChest2));
        
        // not full chest
        String[] noFullChest = {"monkey", "monkey", "monkey", "gold", "diamond", "gold", "monkey", "sword"};
        assertEquals(0, PiratesGame.scoreFullChest(noFullChest));
    }
    
    /**
     * Test die holding functionality
     */
    public void testDieHolding() {
        // check availability to hold selective die
        Player player1 = new Player("Kim");
        String[] roll1 = {"parrot", "diamond", "sword", "monkey", "parrot", "monkey", "sword", "skull"};
        String[] hold1 = {"0", "3", "5"};
        assertTrue(player1.holdDie(roll1, hold1));
        
        // failure: hold die with skull (skull is automatically put aside)
        Player player2 = new Player("Jeong");
        String[] roll2 = {"parrot", "diamond", "sword", "monkey", "parrot", "monkey", "sword", "skull"};
        String[] hold2 = {"0", "3", "5", "7"};
        assertFalse(player2.holdDie(roll2, hold2));
        
        // failure: trying to hold more than 7 dice
        Player player3 = new Player("Won");
        String[] roll3 = {"parrot", "diamond", "sword", "monkey", "parrot", "monkey", "sword", "gold"};
        String[] hold3 = {"0", "1", "2", "3", "4", "5", "7"};
        assertFalse(player3.holdDie(roll3, hold3));
    }
    
    /**
     * Test die re-rolling functionality
     */
    public void testReroll() {
        // reroll die if it is not held and not skull
        String[] roll = {"parrot", "diamond", "sword", "monkey", "parrot", "monkey", "sword", "skull"};
        String[] hold = {"1", "2", "3", "4", "6"};
        String[] newRoll = PiratesGame.reroll(roll, hold);
        assertTrue(roll[1].equals(newRoll[1]));
        assertTrue(roll[2].equals(newRoll[2]));
        assertTrue(roll[3].equals(newRoll[3]));
        assertTrue(roll[4].equals(newRoll[4]));
        assertTrue(roll[6].equals(newRoll[6]));
        assertTrue(roll[7].equals(newRoll[7])); // skull must be always kept
    }

    /**
     * Test skull island deduction
     */
    public void testSkullIslandDeduction() {
        Player player1 = new Player("Kim");
        Player player2 = new Player("Jeong");
        Player player3 = new Player("Won");
        player1.setScore(1000);
        player2.setScore(2000);
        player3.setScore(3000);
        PiratesGame.playEntry(player1);
        PiratesGame.playEntry(player2);
        PiratesGame.playEntry(player3);

        String[] skullIsland = {"skull", "skull", "skull", "monkey", "skull", "skull", "sword", "skull"};
        player1.setFinalRoll(skullIsland);

        PiratesGame.deductSkullIsland(player1);
        assertEquals(1000, PiratesGame.getPlayers("Kim").getScore());
        assertEquals(1400, PiratesGame.getPlayers("Jeong").getScore());
        assertEquals(2400, PiratesGame.getPlayers("Won").getScore());
    }
    
    /**
     * Test scoring with treasure chest fortune card
     */
    public void testTreasureChest() {
        Player player = new Player("Kim");
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("treasure chest");
        
        // roll equivalent to death
        String[] roll = {"skull", "diamond", "skull", "monkey", "parrot", "monkey", "sword", "skull"};
        
        // treasure equivalent to 300 points
        ArrayList<String> treasure = new ArrayList<String>(Arrays.asList("monkey", "monkey", "monkey", "diamond", "gold"));
        PiratesGame.getPlayer().setFinalRoll(roll);
        //PiratesGame.getPlayer().setTreasureChest(treasure);
        PiratesGame.scoreTreasureChest(PiratesGame.getPlayer());

        assertEquals(300, PiratesGame.getPlayer().getScore());
    }

    /**
     * Test scoring with captain fortune card
     */
    public void testCaptain() {
        // double scoring case
        Player player1 = new Player("Kim");
        player1.setScore(0);
        PiratesGame.playEntry(player1);
        PiratesGame.getPlayers("Kim").setFortune("captain");

        // roll equivalent to 300 points
        String[] roll = {"monkey", "parrot", "monkey", "diamond", "gold", "monkey", "sword", "skull"};
        PiratesGame.getPlayers("Kim").setFinalRoll(roll);
        PiratesGame.scoreCaptain(PiratesGame.getPlayers("Kim"));

        assertEquals(600, PiratesGame.getPlayers("Kim").getScore());

        // double deduction with skull island case
        Player player2 = new Player("Jeong");
        Player player3 = new Player("Won");
        player2.setScore(2000);
        player3.setScore(3000);
        PiratesGame.playEntry(player2);
        PiratesGame.playEntry(player3);

        PiratesGame.getPlayers("Kim").resetHeldDie();
        String[] skullIsland = {"skull", "skull", "skull", "monkey", "skull", "skull", "sword", "skull"};
        PiratesGame.getPlayers("Kim").setFinalRoll(skullIsland);

        PiratesGame.deductSkullIsland(PiratesGame.getPlayers("Kim"));
        assertEquals(600, PiratesGame.getPlayers("Kim").getScore());
        assertEquals(800, PiratesGame.getPlayers("Jeong").getScore());
        assertEquals(1800, PiratesGame.getPlayers("Won").getScore());
    }

    /**
     * Test scoring with sorceress fortune card
     */
    public void testSorceress() {
        // reroll one die with skull when including 3 skulls
        Player player = new Player("Kim");
        player.setScore(0);
        PiratesGame.playEntry(player);
        PiratesGame.getPlayers("Kim").setFortune("sorceress");
        
        String[] roll = {"monkey", "skull", "skull", "diamond", "gold", "monkey", "sword", "skull"};
        String[] hold = {"0", "3", "4"};
        // the first skull in the roll array is rerolled
        String[] newRoll = PiratesGame.sorceressReroll(roll, hold);
        assertEquals(roll[0], newRoll[0]);
        assertEquals(roll[2], newRoll[2]); // second skull must be always kept
        assertEquals(roll[3], newRoll[3]);
        assertEquals(roll[4], newRoll[4]);
        assertEquals(roll[7], newRoll[7]); // third skull must be always kept
    }

    /**
     * Test gold fortune card
     */
    public void testGoldCard() {
        Player player = new Player("Kim");
        String[] die = {"sword", "monkey", "parrot", "skull", "skull", "monkey", "diamond", "sword"};
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("gold");
        PiratesGame.getPlayer().setFinalRoll(die);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        
        assertEquals(200, PiratesGame.getPlayer().getScore());
    }

    /**
     * Test diamond fortune card
     */
    public void testDiamondCard() {
        Player player = new Player("Kim");
        String[] die = {"sword", "monkey", "parrot", "skull", "skull", "monkey", "diamond", "sword"};
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("gold");
        PiratesGame.getPlayer().setFinalRoll(die);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        
        assertEquals(200, PiratesGame.getPlayer().getScore());
    }

    /**
     * Test scoring with monkey business
     */
    public void testMonkeyBusiness() {
        Player player1 = new Player("Kim");
        player1.setScore(0);
        PiratesGame.playEntry(player1);
        PiratesGame.getPlayers("Kim").setFortune("monkey business");

        // roll equivalent to 5 of a kind = 500 points
        String[] roll = {"monkey", "parrot", "monkey", "parrot", "sword", "monkey", "sword", "skull"};
        PiratesGame.getPlayers("Kim").setFinalRoll(roll);
        PiratesGame.scoreMonkeyBusiness(PiratesGame.getPlayers("Kim"));

        assertEquals(500, PiratesGame.getPlayers("Kim").getScore());
    }

     /**
     * Test sea battle fortune card
     */
    public void testSeaBattle() {
        Player player = new Player("Kim");
        player.setScore(2000);
        PiratesGame.playEntry(player);

        // 2 sabre sea battle
        PiratesGame.getPlayers("Kim").setFortune("2 sabre sea battle");

        // Get 300 points bonus with 2 sabre + 100 points from 3 of a kind
        String[] roll1 = {"monkey", "parrot", "monkey", "parrot", "sword", "monkey", "sword", "skull"};
        PiratesGame.getPlayers("Kim").setFinalRoll(roll1);
        PiratesGame.scoreSeaBattle(PiratesGame.getPlayers("Kim"));
        assertEquals(2400, PiratesGame.getPlayers("Kim").getScore());

        // Lose 300 points by losing 2 sabre sea battle
        PiratesGame.getPlayers("Kim").resetHeldDie();
        String[] roll2 = {"monkey", "parrot", "monkey", "skull", "sword", "monkey", "skull", "skull"};
        PiratesGame.getPlayers("Kim").setFinalRoll(roll2);
        PiratesGame.scoreSeaBattle(PiratesGame.getPlayers("Kim"));
        assertEquals(2100, PiratesGame.getPlayers("Kim").getScore());

        // 3 sabre sea battle
        PiratesGame.getPlayers("Kim").setFortune("3 sabre sea battle");

        // Get 500 points bonus with 3 sabre + 200 points from 2 3 of a kind
        PiratesGame.getPlayers("Kim").resetHeldDie();
        String[] roll3 = {"monkey", "sword", "monkey", "parrot", "sword", "monkey", "sword", "skull"};
        PiratesGame.getPlayers("Kim").setFinalRoll(roll3);
        PiratesGame.scoreSeaBattle(PiratesGame.getPlayers("Kim"));
        assertEquals(2800, PiratesGame.getPlayers("Kim").getScore());

        // Lose 500 points by losing 3 sabre sea battle
        PiratesGame.getPlayers("Kim").resetHeldDie();
        String[] roll4 = {"monkey", "parrot", "monkey", "skull", "sword", "monkey", "skull", "skull"};
        PiratesGame.getPlayers("Kim").setFinalRoll(roll4);
        PiratesGame.scoreSeaBattle(PiratesGame.getPlayers("Kim"));
        assertEquals(2300, PiratesGame.getPlayers("Kim").getScore());

        // 4 sabre sea battle
        PiratesGame.getPlayers("Kim").setFortune("4 sabre sea battle");

        // Get 1000 points bonus with 4 sabre + 200 points from 4 of a kind
        PiratesGame.getPlayers("Kim").resetHeldDie();
        String[] roll5 = {"monkey", "sword", "monkey", "parrot", "sword", "sword", "sword", "skull"};
        PiratesGame.getPlayers("Kim").setFinalRoll(roll5);
        PiratesGame.scoreSeaBattle(PiratesGame.getPlayers("Kim"));
        assertEquals(3500, PiratesGame.getPlayers("Kim").getScore());

        // Lose 1000 points by losing 4 sabre sea battle
        PiratesGame.getPlayers("Kim").resetHeldDie();
        String[] roll6 = {"monkey", "parrot", "monkey", "skull", "sword", "monkey", "skull", "skull"};
        PiratesGame.getPlayers("Kim").setFinalRoll(roll6);
        PiratesGame.scoreSeaBattle(PiratesGame.getPlayers("Kim"));
        assertEquals(2500, PiratesGame.getPlayers("Kim").getScore());
    }

    /**
     * Test skull fortune card
     */
    public void testSkullCard() {
        // 1 skull fortune card
        Player player = new Player("Kim");
        String[] die = {"sword", "monkey", "parrot", "skull", "skull", "monkey", "diamond", "sword"};
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("1 skull");
        PiratesGame.getPlayer().setFinalRoll(die);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertTrue(PiratesGame.isDead(die));

        // 2 skull fortune card
        PiratesGame.setPlayer(player);
        PiratesGame.getPlayer().setFortune("2 skull");
        PiratesGame.getPlayer().setFinalRoll(die);
        PiratesGame.scoreForRound(PiratesGame.getPlayer());
        assertTrue(PiratesGame.isSkullIsland(die));
    }
    
    /**
     * Test score print on console
     */
    public void testPrintScore() {
        Player player1 = new Player("Kim");
        Player player2 = new Player("Jeong");
        Player player3 = new Player("Won");
        player1.setScore(100);
        player2.setScore(2000);
        player3.setScore(3500);
        Player[] players = new Player[] {player1, player2, player3};
        PiratesGame.printPlayerScores(players);
    }
    
    /**
     * Test die roll print on console
     */
    public void testPrintDieRoll() {
        String[] roll = {"parrot", "skull", "monkey", "gold", "skull", "gold", "skull", "sword"};
        String card = "treasure chest";
        PiratesGame.printDieRoll(roll, card);
    }
}