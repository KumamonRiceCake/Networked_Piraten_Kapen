package a1.piratesGame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;


public class MockServer extends Thread implements Serializable {
    private static final long serialVersionUID = 1L;
    
    Server[] playerServer = new Server[3];
    Player[] players = new Player[3];
    ServerSocket ss;
    PiratesGame game = new PiratesGame();
    int networkPort;
    
    int numPlayers;
    public int round;

    public MockServer(int port) {
        numPlayers = 0;
        networkPort = port;
        for (int i = 0; i < 3; i++) {
            players[i] = new Player("");
        }
    }

    
	@Override
	public void run() {
        try {
            ss = new ServerSocket(networkPort);
            System.out.println("Connecting to game server");
            while (numPlayers < 3) {
                Socket s = ss.accept();
                numPlayers++;
                Server server = new Server(s, numPlayers);
                server.dOut.writeInt(server.playerId);
                server.dOut.flush();
                Player p = (Player) server.dIn.readObject();
                System.out.println("Player " + server.playerId + " - " + p.getName() + " has joined");
                
                players[server.playerId - 1] = p;
                playerServer[numPlayers - 1] = server;
            }
            System.out.println("Three players have joined the game");
            System.out.println("Starting the game");
            // start the server threads
            for (int i = 0; i < playerServer.length; i++) {
                Thread t = new Thread(playerServer[i]);
                t.start();
            }
            gameLoop();
            ss.close();
        } catch (IOException ex) {
            System.out.println("Failed to open server");
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Closing");
	}
	
	public void stop_connection() {
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public int deductionPt(int numSkull) {
    	return numSkull * 100;
    }

    /**
     * Game loop from server side
     */
    public void gameLoop() {
        try {
            // send players data
            playerServer[0].sendPlayers(players);
            playerServer[1].sendPlayers(players);
            playerServer[2].sendPlayers(players);

            int p0Deduct = 0;
            int p1Deduct = 0;
            int p2Deduct = 0;
            /**
             * Loop for a single round
             */
            while (true) {
                round++;

                // Play for Player 0
                System.out.println("*****************************************");
                System.out.println("============Round " + round + " ============");
                playerServer[0].sendRound(round);
                playerServer[0].sendScores(players);
                int p0Receive = playerServer[0].receiveScores();
                if (p0Receive < 0 && p0Receive > -10) {
                    System.out.println("Skull Island: Player 1 deducts " + (p0Receive * 100) + " from other players");
                    p1Deduct = deductionPt(p0Receive);
                    p2Deduct = deductionPt(p0Receive);
                    if (players[1].getScore() < p1Deduct)
                    	p1Deduct = players[1].getScore();
                    if (players[2].getScore() < p2Deduct)
                    	p2Deduct = players[2].getScore();
                }
                else {
                    players[0].setScore(p0Receive + p0Deduct);
                    p0Deduct = 0;
                }
                System.out.println("Player 1 completed turn and the score is " + players[0].getScore());
                
                // When a player reaches over 6000 points
                if (players[0].getScore() >= 6000)
                	break;

                // Play for Player 1
                playerServer[1].sendRound(round);
                playerServer[1].sendScores(players);
                int p1Receive = playerServer[1].receiveScores();
                if (p1Receive < 0 && p1Receive > -10) {
                    System.out.println("Skull Island: Player 2 deducts " + (p1Receive * 100) + " from other players");
                    p0Deduct = deductionPt(p1Receive);
                    p2Deduct = deductionPt(p1Receive);
                    if (players[0].getScore() < p0Deduct)
                    	p0Deduct = players[0].getScore();
                    if (players[2].getScore() < p2Deduct)
                    	p2Deduct = players[2].getScore();
                }
                else {
                    players[1].setScore(p1Receive + p1Deduct);
                    p1Deduct = 0;
                }
                System.out.println("Player 2 completed turn and the score is " + players[1].getScore());
                
                // When a player reaches over 6000 points
                if (players[1].getScore() >= 6000)
                	break;

                // Play for Player 2
                playerServer[2].sendRound(round);
                playerServer[2].sendScores(players);
                int p2Receive = playerServer[2].receiveScores();
                if (p2Receive < 0 && p2Receive > -20) {
                    System.out.println("Skull Island: Player 3 deducts " + (p2Receive * 100) + " from other players");
                    p0Deduct = deductionPt(p2Receive);
                    p1Deduct = deductionPt(p2Receive);
                    if (players[0].getScore() < p0Deduct)
                    	p0Deduct = players[0].getScore();
                    if (players[1].getScore() < p1Deduct)
                    	p1Deduct = players[1].getScore();
                }
                else {
                    players[2].setScore(p2Receive + p2Deduct);
                    p2Deduct = 0;
                }
                System.out.println("Player 3 completed turn and the score is " + players[2].getScore());
                
                // When a player reaches over 6000 points
                if (players[2].getScore() >= 6000)
                	break;
            }
            
            // set round to -1 as a flag
            playerServer[0].sendRound(-1);
            playerServer[1].sendRound(-1);
            playerServer[2].sendRound(-1);
            
            playerServer[0].sendScores(players);
            playerServer[1].sendScores(players);
            playerServer[2].sendScores(players);
            
            // determines the winner
            Player p = game.getWinner(players);
            System.out.println("The winner is " + p.getName());
            for (int i = 0; i < playerServer.length; i++) {
                playerServer[i].dOut.writeObject(p);
                playerServer[i].dOut.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public class Server implements Runnable {
        private Socket socket;
        private ObjectInputStream dIn;
        private ObjectOutputStream dOut;
        private int playerId;

        public Server(Socket s, int playerid) {
            socket = s;
            playerId = playerid;
            try {
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                System.out.println("Server Connection failed");
            }
        }

        /**
         * Run server
         */
        public void run() {
            try {
                while (true) {
                }

            } catch (Exception ex) {
                {
                    System.out.println("Failed to run");
                    ex.printStackTrace();
                }
            }
        }

        /**
         * Send players list to clients
         * @param pl
         */
        public void sendPlayers(Player[] pl) {
            try {
                for (Player p : pl) {
                    dOut.writeObject(p);
                    dOut.flush();
                }

            } catch (IOException ex) {
                System.out.println("Failed to send players list");
                ex.printStackTrace();
            }
        }
        
        /**
         * Send round info to clients
         * @param r
         */
        public void sendRound(int r) {
            try {
                dOut.writeInt(r);
                dOut.flush();
            } catch (Exception e) {
                System.out.println("Failed to send round info");
                e.printStackTrace();
            }
        }
        
        /**
         * Send scores of 3 players to the clients
         * @param pl
         */
        public void sendScores(Player[] pl) {
            try {
                for (int i = 0; i < 3; i++) {
                    dOut.writeInt(pl[i].getScore());
                }
                dOut.flush();
            } catch (Exception e) {
                System.out.println("Failed to send score info of players");
                e.printStackTrace();
            }
        }

        /**
         * Receive scores from client
         * @return score
         */
        public int receiveScores() {
            try {
                int sc = dIn.readInt();
                return sc;
            } catch (Exception e) {
                System.out.println("Score sheet not received");
                e.printStackTrace();
            }
            return 0;
        }
    }
}