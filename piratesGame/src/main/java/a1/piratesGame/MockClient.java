package a1.piratesGame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;

public class MockClient implements Serializable {
    private static final long serialVersionUID = 1L;
    public PiratesGame game = new PiratesGame();
    
    public Player[] players = new Player[3];

    Socket socket;
    private ObjectInputStream dIn;
    private ObjectOutputStream dOut;

    public MockClient() {
        try {
            socket = new Socket("localhost", 49200);
            dOut = new ObjectOutputStream(socket.getOutputStream());
            dIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Failed to connect to client");
        }
    }
    
	public void receiveId() {
		try {
			int playerId = dIn.readInt();
			System.out.println("Connected as id " + playerId);
		} catch (IOException ex) {
			System.out.println("Failed to connect to client");
		}
	}
    
    /**
     * send player object to server
     */
    public void sendPlayer() {
        try {
            dOut.writeObject(game.getPlayer());
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
    public void receiveScores() {
        try {
            for (int i = 0; i < 3; i++) {
            	int score = dIn.readInt();
                players[i].setScore(score);
            }
        } catch (Exception e) {
            System.out.println("Failed to receive score");
            e.printStackTrace();
        }
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
    
    public void sendData(String data) {
	    try{
	    	System.out.println(data);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
}

    public String[] mockRoll() {
        String[] roll = new String[8];
        for (int i=0; i<8; i++)
            roll[i] = game.rollDie();
        return roll;
    }
}
