package a1.piratesGame;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;


public class NetworkedGameTest extends TestCase {
	private MockServer server;
	private MockClient players[];
	
	@Given("the game has been started")
	public void the_game_has_been_started() throws ClassNotFoundException {
		server = null;
		players = new MockClient[3];
		server = new MockServer();
		server.start();
		players[0] = new MockClient();
		players[0].game.setPlayer(new Player("Kim"));
		players[0].receiveId();
		players[0].sendPlayer();
		players[1] = new MockClient();
		players[1].game.setPlayer(new Player("Jeong"));
		players[1].receiveId();
		players[1].sendPlayer();
		players[2] = new MockClient();
		players[2].game.setPlayer(new Player("Won"));
		players[2].receiveId();
		players[2].sendPlayer();
	}

	@When("Round starts")
	public void round_starts() {
		players[0].receivePlayer();
		players[1].receivePlayer();
		players[2].receivePlayer();
	    assertEquals(1, players[0].receiveRound());
	}

	@When("Player {int} sees score board")
	public void player_sees_score_board(Integer int1) {
        players[int1-1].receiveScores();
        players[int1-1].game.printPlayerScores(players[0].players);
	}

	@When("Player {int} sees die roll and fortune card")
	public void player_sees_die_roll_and_fortune_card(Integer int1) {
		String[] roll = players[int1-1].mockRoll();
		players[int1-1].game.drawFortuneCard(players[int1-1].game.getPlayer());
		players[int1-1].game.printDieRoll(roll, players[int1-1].game.getPlayer().getFortune());
	}

	@When("Player {int} sees menu")
	public void player_sees_menu(Integer int1) {
		players[int1-1].game.userPrompt();
	}

	@When("Player {int} enters menu option {int}")
	public void player_enters_menu_option(Integer int1, Integer int2) {
		players[int1-1].sendData(int2.toString());
	}

	@When("Player {int} sees scoring message")
	public void player_sees_scoring_message(Integer int1) {
		players[int1-1].game.scoreForRound(players[int1-1].game.getPlayer());
	}

	@When("Player {int} sends score data to server and finishes turn")
	public void player_finishes_turn(Integer int1) {
		players[int1-1].sendScores(players[int1-1].game.getPlayer().getScore());
		players[int1-1].game.roundOnGoing = false;
	}

	@Then("Game goes to the next round and player {int} sees score updated board")
	public void game_goes_to_the_next_round(Integer int1) {
		assertEquals(2, server.round);
		players[int1-1].receiveScores();
        players[int1-1].game.printPlayerScores(players[0].players);
	}
}
