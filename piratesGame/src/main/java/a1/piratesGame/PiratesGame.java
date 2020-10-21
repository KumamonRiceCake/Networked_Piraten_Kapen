package a1.piratesGame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PiratesGame implements Serializable {
    private static final long serialVersionUID = 1L;
    static Client clientConnection;
    
    private Player[] players = new Player[3];
    private Player player;
    private int entryNumber = 0;
    public boolean roundOnGoing = false;
    private static final String[] diePattern = {"skull", "parrot", "monkey", "sword", "gold", "diamond"};

    /**
     * roll a single die
     * @return randomly generated die
     */
    public String rollDie() {
        int index = (int) (Math.random() * 5);
        return diePattern[index];
    }

    /**
     * Add a player to the player entry
     * @param player
     * @return true if there is a room in the entry (maximum 3)
     */
    public boolean playEntry(Player player) {
        if (entryNumber < 3) {
            // prevent an entry of players with same names
            for (int i=0; i<entryNumber; i++) {
                if (entryNumber > 0 && player.getName().equals(players[i].getName()))
                    return false;
            }
            players[entryNumber] = player;
            entryNumber++;
            return true;
        }
        return false;
    }
    
    /**
     * Setter function for Player object
     * @param p
     */
    public void setPlayer(Player p) {
        player = p;
    }
    
    /**
     * Getter function for Player object
     * @param p
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Set the score for the player with the provided player number
     * @param playerNumber
     * @param score
     */
    public void setScore(int playerNumber, int score) {
        players[playerNumber].setScore(score);
    }
    
    /**
     * Return the winner of the game who earns over 6000 points and the most
     * @return winner
     */
    public Player getWinner(Player[] players) {
        Player winner = null;
        for (Player p : players) {
            if (p.getScore() >= 6000) {
                if (winner == null || p.getScore() > winner.getScore())
                    winner = p;
            }
        }
        return winner;
    }
    
    /**
     * Function to determine if the player's set of rolls means death
     * @param dieRoll - a set of values of die roll
     * @return true if the player's last roll have 3 skulls
     */
    public boolean isDead(String[] dieRoll) {
        int skullCount = 0;
        int lastSkull = -1;
        int arrSize = 8;
        if (player.getFortune().equals("1 skull"))
            arrSize = 9;
        else if (player.getFortune().equals("2 skull"))
            arrSize = 10;
        String[] arr = new String[arrSize];
        for (int i=0; i<8; i++)
            arr[i] = dieRoll[i];
        for (int i=8; i<arrSize; i++)
            arr[i] = "skull";
        
        for (int i=0; i<arrSize; i++) {
            if (arr[i] != null && arr[i].equals("skull")) {
                skullCount++;
                lastSkull = i;
            }
        }
        if (skullCount == 3) {
            // when died with 3 skulls, sorceress fortune card gives a chance to reroll one die
            if (player != null && player.getFortune().equals("sorceress")) {
                dieRoll[lastSkull] = rollDie();
                if (dieRoll[lastSkull].equals("skull")) {
                    System.out.println("Dead with 3 skulls. End of the turn.");
                    return true;
                }
            }
            System.out.println("Dead with 3 skulls. End of the turn.");
            roundOnGoing = false;
            return true;
        }
        return false;
    }
    
    /**
     * Function to determine if the player's set of rolls means the skull island
     * @param dieRoll - a set of values of die roll
     * @return true if the player's last roll have more than 3 skulls
     */
    public boolean isSkullIsland(String[] dieRoll) {
        int skullCount = 0;
        int arrSize = 8;
        if (player.getFortune().equals("1 skull"))
            arrSize = 9;
        else if (player.getFortune().equals("2 skull"))
            arrSize = 10;
        String[] arr = new String[arrSize];
        for (int i=0; i<8; i++)
            arr[i] = dieRoll[i];
        for (int i=8; i<arrSize; i++)
            arr[i] = "skull";
        
        for (int i=0; i<arrSize; i++) {
            if (arr[i] != null && arr[i].equals("skull")) {
                skullCount++;
            }
        }
        if (skullCount > 3)
            return true;
        return false;
    }
    
    /**
     * Function to score sequence of patterns
     * @param dieRoll
     * @return score earned by sequences of patterns
     */
    public int scoreSequence(String[] dieRoll) {
        HashMap<String, Integer> patternDict = new HashMap<String, Integer>();
        int score = 0;
        for (String pattern : dieRoll) {
            if (pattern != null) {
                if (patternDict.containsKey(pattern))
                    patternDict.put(pattern, patternDict.get(pattern)+1);
                else
                    patternDict.put(pattern, 1);
            }
        }
        
        for (int numPattern : patternDict.values()) {
            switch(numPattern){
            case 3: 
                score += 100;
                break;
            case 4:
                score += 200;
                break;
            case 5:
                score += 500;
                break;
            case 6:
                score += 1000;
                break;
            case 7:
                score += 2000;
                break;
            case 8:
                score += 4000;
                break;
            case 9:
                score += 4000;
                break;
            }
        }
        System.out.println("Score for this sequence: " + score);
        return score;
    }
    
    /**
     * Function to score diamonds and golds
     * @param dieRoll
     * @return score earned by diamonds and golds
     */
    public int scoreDiamondsAndGolds(String[] dieRoll) {
        int score = 0;
        for (String pattern : dieRoll) {
            if (pattern != null && (pattern.equals("gold") || pattern.equals("diamond")))
                score += 100;
        }
        System.out.println("Score for gold and diamonds: " + score);
        return score;
    }
    
    /**
     * Function to score full chest
     * @param dieRoll
     * @return 500 if the roll is full chest
     */
    public int scoreFullChest(String[] dieRoll) {
        HashMap<String, Integer> patternDict = new HashMap<String, Integer>();
        for (String pattern : dieRoll) {
            if (pattern == null || pattern.equals("skull"))
                return 0;
            else if (patternDict.containsKey(pattern))
                patternDict.put(pattern, patternDict.get(pattern)+1);
            else
                patternDict.put(pattern, 1);
        }
        
        for (String pattern : patternDict.keySet()) {
            if (patternDict.get(pattern) < 3 
                    && !pattern.equals("gold") 
                    && !pattern.equals("diamond")) {
                 
                if (pattern.equals("sword")) {
                    if (patternDict.get("sword")==2 && player.getFortune().equals("2 sabre sea battle"));
                    else
                        return 0;
                }
                else
                    return 0;
            }
        }
        System.out.println("Score for full chest: " + 500);
        return 500;
    }
    
    /**
     * Reroll unheld dices
     * @param prevRoll - previously rolled dices
     * @param hold - indices of dices to be held
     * @return newly rolled set of dices
     */
    public String[] reroll(String[] prevRoll, String[] hold) {
        String[] result = new String[8];
        for (int k=0; k<hold.length; k++) {
            if (hold[k] != null)
                result[Integer.parseInt(hold[k])] = prevRoll[Integer.parseInt(hold[k])];
        }
        for (int i=0; i<8; i++) {
            if (prevRoll[i] != null && prevRoll[i].equals("skull"))
                result[i] = "skull";
        }
        for (int j=0; j<8; j++) {
            if (result[j] == null)
                result[j] = rollDie();
        }
        return result;
    }
    
    /**
     * Reroll unheld dices when treasure chest
     * @param prevRoll - previously rolled dices
     * @param hold - indices of dices to be held
     * @return newly rolled set of dices
     */
    public String[] chestReroll(String[] prevRoll, String[] hold, String[] chest) {
        String[] result = new String[8];
        for (int l=0; l<chest.length; l++) {
            if (chest[l] != null)
                result[Integer.parseInt(chest[l])] = prevRoll[Integer.parseInt(chest[l])];
        }
        
        for (int k=0; k<hold.length; k++) {
            if (hold[k] != null)
                result[Integer.parseInt(hold[k])] = prevRoll[Integer.parseInt(hold[k])];
        }
        for (int i=0; i<8; i++) {
            if (prevRoll[i] != null && prevRoll[i].equals("skull"))
                result[i] = "skull";
        }
        for (int j=0; j<8; j++) {
            if (result[j] == null)
                result[j] = rollDie();
        }
        return result;
    }
    
    /**
     * Get player with the specified name
     * @param name
     * @return Player object with the name
     */
    public Player getPlayers(String name) {
        for (Player p : players) {
            if (p.getName().equals(name))
                return p;
        }
        return null;
    }

    /**
     * Deduct points from opponents in case of skull island
     * @param player, numSkull
     */
    public int deductSkullIsland(Player player, int numSkull) {
        if (player.getFortune().equals("1 skull"))
            numSkull += 1;
        else if (player.getFortune().equals("2 skull"))
            numSkull += 2;
        if (player.getFortune() != null && player.getFortune().equals("captain"))
            return numSkull * -2;
        else
            return numSkull * -1;
    }
    
    /**
     * score with treasure chest fortune card
     * @param name - player name with the card
     */
    public void scoreTreasureChest(Player p) {
        String[] result = new String[8];
        String[] chest = new String[8];
        int score = 0;
    	if (p.getTreasureChest() != null)
            chest = p.getTreasureChest();

        for (String s : chest)
        	result[Integer.parseInt(s)] = p.getHeldDie()[Integer.parseInt(s)];
        
        
        score = scoreSequence(result) + scoreDiamondsAndGolds(result) + scoreFullChest(result);
        p.setScore(p.getScore() + score);
    }
    
    /**
     * score with captain fortune card
     * @param name - player name with the card
     */
    public void scoreCaptain(Player p) {
        int score = 0;
        if (p.getFortune().equals("captain")) {
            score = scoreSequence(p.getHeldDie()) + scoreDiamondsAndGolds(p.getHeldDie()) + scoreFullChest(p.getHeldDie());
            p.setScore(p.getScore() + score * 2);
        }
    }

    /**
     * re-roll a skull when having sorceress fortune card
     * @param roll
     * @param hold
     * @return re-roll of a die with skull
     */
    public String[] sorceressReroll(String[] roll, String[] hold) {
        String[] newRoll = reroll(roll, hold);
        for (int i=0; i<8; i++) {
            if (newRoll[i].equals("skull")) {
                int index = (int) (Math.random() * 5);
                newRoll[i] = diePattern[index];
                break;
            }
        }
        return newRoll;
    }

    /**
     * draw a fortune card 
     * @param p - a player drawing a fortune card
     */
    public void drawFortuneCard(Player p) {
        int index = (int) (Math.random() * 34);
        if (index < 4)
            p.setFortune("gold");
        else if (index < 8)
            p.setFortune("diamond");
        else if (index < 12)
            p.setFortune("sorceress");
        else if (index < 16)
            p.setFortune("captain");
        else if (index < 20)
            p.setFortune("treasure chest");
        else if (index < 24)
            p.setFortune("monkey business");
        else if (index < 27)
            p.setFortune("1 skull");
        else if (index < 29)
            p.setFortune("2 skull");
        else if (index < 31)
            p.setFortune("2 sabre sea battle");
        else if (index < 33)
            p.setFortune("3 sabre sea battle");
        else
            p.setFortune("4 sabre sea battle");
    }

    /**
     * for gold, diamond and skull fortune cards, apply their effect to the player's die
     * @param name - player name with the card
     */
    public int applyFortuneCard(String[] roll) {
        String card = player.getFortune();
        String[] newRoll = new String[10];
        for (int i=0; i<8; i++)
            newRoll[i] = roll[i];
        if (card.equals("gold"))
            newRoll[8] = "gold";
        else if (card.equals("diamond"))
            newRoll[8] = "diamond";
        else if (card.equals("1 skull"))
            newRoll[8] = "skull";
        else if (card.equals("2 skull")) {
            newRoll[8] = "skull";
            newRoll[9] = "skull";
        }
        int score = scoreSequence(newRoll) + scoreDiamondsAndGolds(newRoll) + scoreFullChest(roll);
        return score;
    }

    /**
     * Score roll with monkey business fortune card
     * @param p
     */
    public void scoreMonkeyBusiness(Player p) {
        int score = 0;
        String[] roll = new String[8];
        for (int i=0; i<8; i++) {
            if (p.getHeldDie()[i].equals("parrot"))
                roll[i] = "monkey";
            else
                roll[i] = p.getHeldDie()[i];
        }
        score = scoreSequence(roll) + scoreDiamondsAndGolds(roll) + scoreFullChest(roll);
        p.setScore(p.getScore() + score);
    }

    /**
     * Score see battle
     * @param p
     */
    public void scoreSeaBattle(Player p) {
        int score = 0;
        int numSabre = 0;
        int numSkull = 0;
        for (String d : p.getHeldDie()) {
            if (d != null && d.equals("sword"))
                numSabre++;
            if (d != null && d.equals("skull"))
                numSkull++;
        }
        score = scoreSequence(p.getHeldDie()) + scoreDiamondsAndGolds(p.getHeldDie()) + scoreFullChest(p.getHeldDie());
        if (p.getFortune().equals("2 sabre sea battle")) {
            if (numSabre >= 2 && numSkull<3) {
                score += 300;
                p.setScore(p.getScore() + score);
                System.out.println("Battle win, +300 pts");
            }
            else {
                p.setScore(p.getScore() - 300);
                System.out.println("Battle lost, -300 pts");
            }
        }
        else if (p.getFortune().equals("3 sabre sea battle")) {
            if (numSabre >= 3 && numSkull<3) {
                score += 500;
                p.setScore(p.getScore() + score);
                System.out.println("Battle win, +500 pts");
            }
            else {
                p.setScore(p.getScore() - 500);
                System.out.println("Battle lost, -500 pts");
            }
        }
        else if (p.getFortune().equals("4 sabre sea battle")) {
            if (numSabre >= 4 && numSkull<3) {
                score += 1000;
                p.setScore(p.getScore() + score);
                System.out.println("Battle win, +1000 pts");
            }
            else {
                p.setScore(p.getScore() - 1000);
                System.out.println("Battle lost, -1000 pts");
            }
        }
        if (p.getScore() < 0)
            p.setScore(0);
    }
    
    /**
     * Calculate the score of a single round of a player
     * @param p
     */
    public void scoreForRound(Player p) {
        if (p.getFortune().equals("captain"))
            scoreCaptain(p);
        else if (p.getFortune().equals("monkey business"))
            scoreMonkeyBusiness(p);
        else if (p.getFortune().equals("2 sabre sea battle")
                || p.getFortune().equals("3 sabre sea battle") 
                || p.getFortune().equals("4 sabre sea battle"))
            scoreSeaBattle(p);
        else if (!p.getFortune().equals("sorceress"))
            p.setScore(p.getScore() + applyFortuneCard(p.getHeldDie()));
        else {
            int score = 0;
            score += scoreSequence(p.getHeldDie())
                    + scoreDiamondsAndGolds(p.getHeldDie())
                    + scoreFullChest(p.getHeldDie());
            System.out.println("Score for this round: " + score);
            p.setScore(p.getScore() + score);
        }
    }

    /**
     * Show a set of die roll formatted on console
     * @param roll - die roll
     * @param card - fortune card
     */
    public void printDieRoll(String[] roll, String card) {
        System.out.println("--Fortune Card: " + card);
        for (int i=0; i<8; i++) {
            System.out.print("     " + i + "     ");
        }
        System.out.println();
        for (String d : roll) {
            System.out.print(" [ " + d + " ] ");
        }
        System.out.println();
    }

    /**
     * Print status of players in the entry
     * @param Array of players
     */
    public void printPlayerScores(Player[] players) {
        for (Player pl : players) {
            System.out.println("|----------------------------------------------------------------|");
            System.out.println("| Player : " + pl.getName());
            System.out.println("| Total Score : " + pl.getScore());
            System.out.println("|----------------------------------------------------------------|");
        }
    }
    
    public void userPrompt() {
        System.out.println("Select an action: ");
        System.out.println("(1) Hold dice and reroll");
        System.out.println("(2) Roll all except skull");
        System.out.println("(3) Score this round");
        if (player.getFortune().equals("treasure chest"))
            System.out.println("(4) Put dice in treasure chest");
    }
    
    public void skullIslandPrompt() {
        System.out.println();
        System.out.println("\nSKULL ISLAND!");
        System.out.println("\nEnter anything to throw dice!");
    }
    
    public String[] holdAndReroll(String[] roll, String[] hold) {
        System.out.print("hold: ");
        for (int i=0; i<hold.length; i++)
            System.out.print(hold[i] + " ");
        System.out.println();
        player.holdDie(roll, hold);
        return reroll(roll, hold);
    }
    
    /**
     * Function to run a single round
     * @param player
     * @return player's score
     */
    public int playSingleRound(Player player) {
        Scanner scanner = new Scanner(System.in);

        String[] roll = new String[8];
        String[] hold = new String[8];
        roundOnGoing = true;
        for (int i=0; i<8; i++)
            roll[i] = rollDie();
        player.nextRound();
        player.resetHeldDie();
        printDieRoll(roll, player.getFortune());
        isDead(roll);
        boolean isSorceress = false;
        if (player.getFortune().equals("sorceress"))
        	isSorceress = true;

        // loop until death or skull island
        while (roundOnGoing) {
            if (isSkullIsland(roll)) {
                if (player.getFortune().equals("2 sabre sea battle")
                        || player.getFortune().equals("3 sabre sea battle") 
                        || player.getFortune().equals("4 sabre sea battle")) {
                	scoreSeaBattle(player);
                    roundOnGoing = false;
                    continue;
                }

                // rolling for skull island
                roundOnGoing = false;
                skullIslandPrompt();
                String skullThrow = scanner.nextLine();
                boolean loop = true;
                int numSkull = 0;
                for (int i=0; i<8; i++) {
                    if (roll[i].equals("skull"))
                        numSkull++;
                }
                while (loop) {
                    loop = false;
                    for (int i=0; i<8; i++) {
                        if (!roll[i].equals("skull")) {
                            roll[i] = rollDie();
                            if (roll[i].equals("skull")) {
                                loop = true;
                                numSkull++;
                            }
                        }
                    }
                    printDieRoll(roll, player.getFortune());
                    if (numSkull == 8)
                        loop = false;
                    if (loop) {
                        System.out.println("Enter anything to throw dice!");
                        String throwAgain = scanner.nextLine();
                    }
                }
                return deductSkullIsland(player, numSkull);
            }

            userPrompt();
            int selection = scanner.nextInt();
            switch (selection) {
            case 1:
            	if (isSorceress) {
	            	for (int i=0; i<8; i++) {
	            		if (roll[i].equals("skull")) {
	            			System.out.println("Sorceress throws a skull!");
	            			roll[i] = null;
	            			isSorceress = false;
	            		}
	            	}
            	}
            	System.out.println("Select the dices to hold: (0 ~ 7) ");
                hold = scanner.next().replaceAll("\\s", "").split(",");
            	roll = holdAndReroll(roll, hold);
                System.out.println("New Roll: ");
            	printDieRoll(roll, player.getFortune());
                if (isDead(roll)) {
                    System.out.println("3 Skull death!");
                    roundOnGoing = false;
                    scoreSeaBattle(player);
                }
                break;
            case 2:
            	if (isSorceress) {
	            	for (int i=0; i<8; i++) {
	            		if (roll[i].equals("skull")) {
	            			System.out.println("Sorceress throws a skull!");
	            			roll[i] = null;
	            			isSorceress = false;
	            		}
	            	}
            	}
                roll = reroll(roll, hold);
                System.out.println("New Roll: ");
                printDieRoll(roll, player.getFortune());
                if (isDead(roll)) {
                    System.out.println("3 Skull death!");
                    roundOnGoing = false;
                    scoreSeaBattle(player);
                }
                break;
            case 3:
                player.setFinalRoll(roll);
                scoreForRound(player);
                roundOnGoing = false;
                break;
            case 4:
                System.out.println("Select the dices to keep in your treasure chest: (0 ~ 7) ");
                String[] chest = scanner.next().replaceAll("\\s", "").split(",");
                player.setTreasureChest(chest);

                System.out.println("Select the dices to hold: (0 ~ 7) ");
                hold = scanner.next().replaceAll("\\s", "").split(",");
                System.out.print("hold: ");
                for (int i=0; i<hold.length; i++)
                    System.out.print(hold[i] + " ");
                System.out.println();
                player.holdDie(roll, hold);
                roll = chestReroll(roll, hold, chest);
                System.out.println("New Roll: ");
                printDieRoll(roll, player.getFortune());
                if (isDead(roll) || isSkullIsland(roll)) {
                    System.out.println("3 Skull death!");
                    roundOnGoing = false;
                    scoreTreasureChest(player);
                }
                break;
            case 5:
            	player.setScore(5900);
            	break;
            }
        }
        return player.getScore();
    }
    
    /**
     * This function runs the game in a loop until the end condition is observed
     */
    public void runGame() {
        players = clientConnection.receivePlayer();
        while (true) {
            int round = clientConnection.receiveRound();
            // flag for breaking the loop
            if (round == -1) {
                int[] pl = clientConnection.receiveScores();
                for (int i = 0; i < 3; i++) {
                    players[i].setScore(pl[i]);
                }
            	for (Player p : players) {
                    if (p.getScore() >= 6000)
                        System.out.println("Game over!!!\n" + p.getName() + " wins the game!");
                }
                break;
            }
            System.out.println("\n \n =======Round Number " + round + "=======");
            int[] pl = clientConnection.receiveScores();
            for (int i = 0; i < 3; i++) {
                players[i].setScore(pl[i]);
            }
            printPlayerScores(players);
            drawFortuneCard(player);
            clientConnection.sendScores(playSingleRound(player));
            player.nextRound();
        }
    }
    
    /**
     * Initialize client connection
     */
    public void connectToClient() {
        clientConnection = new Client();
    }

    private class Client {
        Socket socket;
        private ObjectInputStream dIn;
        private ObjectOutputStream dOut;

        public Client() {
            try {
                socket = new Socket("localhost", 49152);
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());

                int playerId = dIn.readInt();

                System.out.println("Connected as id " + playerId);
                sendPlayer();

            } catch (IOException ex) {
                System.out.println("Failed to connect to client");
            }
        }
        
        /**
         * send player object to server
         */
        public void sendPlayer() {
            try {
                dOut.writeObject(player);
                dOut.flush();
            } catch (IOException ex) {
                System.out.println("Failed to send player");
                ex.printStackTrace();
            }
        }

        /**
         * send score data to server
         */
        public void sendScores(int score) {
            try {
                dOut.writeInt(score);
                dOut.flush();

            } catch (IOException e) {
                System.out.println("Failed to send score");
                e.printStackTrace();
            }
        }

        /**
         * receive players information
         */
        public Player[] receivePlayer() {
            Player[] players = new Player[3];
            try {
                Player p = (Player) dIn.readObject();
                players[0] = p;
                p = (Player) dIn.readObject();
                players[1] = p;
                p = (Player) dIn.readObject();
                players[2] = p;
                return players;
            } catch (IOException e) {
                System.out.println("Failed to send players list");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                e.printStackTrace();
            }
            return players;
        }

        /**
         * receive scores of players
         */
        public int[] receiveScores() {
            try {
                int[] score = new int[3];
                for (int i = 0; i < 3; i++) {
                    score[i] = dIn.readInt();
                    System.out.println();
                }

                return score;
            } catch (Exception e) {
                System.out.println("Failed to receive score");
                e.printStackTrace();
            }
            return null;
        }

        /**
         * receive round data
         */
        public int receiveRound() {
            try {
                return dIn.readInt();

            } catch (IOException e) {
                System.out.println("Failed to receive round data");
                e.printStackTrace();
            }
            return 0;
        }
    }
    
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the player name");
        String name = scanner.next();
        PiratesGame piratesGame = new PiratesGame();
        piratesGame.setPlayer(new Player(name));
        piratesGame.connectToClient();
        piratesGame.runGame();
        scanner.close();
    }
}
