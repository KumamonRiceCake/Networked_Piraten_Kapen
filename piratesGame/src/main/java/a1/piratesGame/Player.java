package a1.piratesGame;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
    private String name;
    private int score;
    private int round;
    private String fortuneCard;
    private String[] heldDie = new String[8];
    private String[] treasureChest = new String[8];
    public boolean lastRound = false;
    
    /**
     * Player constructor
     * @param name - player name
     */
    public Player(String name) {
        this.name = name;
        score = 0;
        round = 1;
        fortuneCard = "";
    }

    /**
     * Getter for player name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for player's current score
     * @param score
     */
    public void setScore(int score) {
    	this.score = score;
    	if (score < 0)
    		this.score = 0;
    }
    
    /**
     * Getter for player's current score
     * @return score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Increment the user's current round
     * @param score
     */
    public void nextRound() {
        round++;
    }

    /**
     * Getter for player's current round
     * @return score
     */
    public int getRound() {
        return round;
    }

    /**
     * save dices user selected
     * @param dieRoll - the last thrown dices
     * @param holdingSelection - array of indices of selected dices
     * @return true if the selection is successfully on hold
     */
    public boolean holdDie(String[] dieRoll, String[] holdingSelection) {
        String[] held = new String[8];
        // cannot hold more than 6 dices
        if (holdingSelection.length > 6) {
            return false;
        }
        // skull is automatically held
        for (int i=0; i<8; i++) {
            if (dieRoll[i].equals("skull"))
                held[i] = "skull";
        }
        for (String holdIndex : holdingSelection) {
            if (dieRoll[Integer.parseInt(holdIndex)].equals("skull"))
                return false;
            held[Integer.parseInt(holdIndex)] = dieRoll[Integer.parseInt(holdIndex)];
        }
        heldDie = held;
        return true;
    }

    /**
     * getter function for heldDie
     * @return array of held dices from the previous roll
     */
    public String[] getHeldDie() {
        return heldDie;
    }

    /**
     * Finalize the roll
     * @param roll
     * @return true if successful
     */
    public void setFinalRoll(String[] roll) {
        if (!isRollingOver()) {
            heldDie = roll;
        }
    }
    
    /**
     * Determine if re-rolling is over
     * @param roll
     * @return true if the user has the final set of dices
     */
    public boolean isRollingOver() {
        for (String die : heldDie) {
            if (die == null)
                return false;
        }
        return true;
    }
    
    /**
     * setter function for fortune card
     * @param card
     */
    public void setFortune(String card) {
        fortuneCard = card;
    }
    
    /**
     * getter function for fortune card
     * @return user's fortune card of the current turn
     */
    public String getFortune() {
        return fortuneCard;
    }
    
    /**
     * setter function for treasure chest
     */
    public void setTreasureChest(String[] treasure) {
        treasureChest = treasure;
    }
    
    /**
     * getter function for treasure chest
     * @return treasure chest
     */
    public String[] getTreasureChest() {
        return treasureChest;
    }
    
    /**
     * reset the currently held die
     */
    public void resetHeldDie() {
        heldDie = new String[8];
    }
}
