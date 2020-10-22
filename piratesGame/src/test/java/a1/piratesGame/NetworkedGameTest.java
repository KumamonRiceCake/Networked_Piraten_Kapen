package a1.piratesGame;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;


public class NetworkedGameTest extends TestCase {
	private MockServer server;
	private MockClient players[];
	
	// Row 125
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
	}

	@When("Player {int} sees score board")
	public void player_sees_score_board(Integer int1) {
		assertEquals(1, players[int1-1].receiveRound());
        players[int1-1].receiveScores();
        players[int1-1].game.printPlayerScores(players[int1-1].players);
	}

	@When("Player {int} sees die roll and fortune card")
	public void player_sees_die_roll_and_fortune_card(Integer int1) {
		String[] roll = players[int1-1].mockRoll();
		players[int1-1].game.getPlayer().setFinalRoll(roll);
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
		assertEquals(2, players[int1-1].receiveRound());
		players[int1-1].receiveScores();
        players[int1-1].game.printPlayerScores(players[0].players);
	}
	
	// Row 127
	@When("Player {int} rolls |parrot|parrot|gold|diamond|skull|gold|sword|sword| and gets {string} fortune card")
	public void player_rolls_parrot_parrot_gold_diamond_skull_gold_sword_sword_and_gets_fortune_card(Integer int1, String string) {
		String[] roll = new String[] {"parrot", "parrot", "gold", "diamond", "skull", "gold", "sword", "sword"};
		players[int1-1].game.getPlayer().setFinalRoll(roll);
		players[int1-1].game.getPlayer().setFortune(string);
	}

	@When("Player {int} enters {string} to hold")
	public void player_enters_to_hold(Integer int1, String string) {
		players[int1-1].sendData(string);
		hold = scanner.next().replaceAll("\\s", "").split(",");
    	roll = holdAndReroll(roll, hold);
	}

	@When("Player {int} rerolls |skull|parrot|gold|diamond|skull|gold|sword|skull| and die with three skulls")
	public void player_rerolls_skull_parrot_gold_diamond_skull_gold_sword_skull_and_die_with_three_skulls(Integer int1) {
		String[] newRoll = players[int1-1].game.reroll(players[int1-1].game.getPlayer().getHeldDie(), hold)
	}

	@When("Player {int} rolls |gold|parrot|parrot|diamond|monkey|gold|sword|sword| and gets {string} fortune card")
	public void player_rolls_gold_parrot_parrot_diamond_monkey_gold_sword_sword_and_gets_fortune_card(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Player {int} rerolls |gold|gold|gold|gold|skull|gold|gold|gold|")
	public void player_rerolls_gold_gold_gold_gold_skull_gold_gold_gold(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Player {int} sees scoring message and scores {int} points")
	public void player_sees_scoring_message_and_scores_points(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Player {int} rolls |skull|skull|parrot|diamond|monkey|skull|sword|sword| and gets {string} fortune card and die with three skulls")
	public void player_rolls_skull_skull_parrot_diamond_monkey_skull_sword_sword_and_gets_fortune_card_and_die_with_three_skulls(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Player {int} rolls |sword|parrot|parrot|diamond|monkey|gold|skull|sword| and gets {string} fortune card")
	public void player_rolls_sword_parrot_parrot_diamond_monkey_gold_skull_sword_and_gets_fortune_card(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Player {int} rolls |diamond|parrot|parrot|diamond|monkey|gold|diamond|monkey| and gets {string} fortune card")
	public void player_rolls_diamond_parrot_parrot_diamond_monkey_gold_diamond_monkey_and_gets_fortune_card(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Player {int} rerolls |skull|parrot|skull|sword|skull|sword|skull|diamond| and die with three skulls")
	public void player_rerolls_skull_parrot_skull_sword_skull_sword_skull_diamond_and_die_with_three_skulls(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Player {int} sees {string} message and scores {int} points")
	public void player_sees_message_and_scores_points(Integer int1, String string, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Player {int} rolls |monkey|parrot|parrot|monkey|sword|gold|sword|sword| and gets {string} fortune card")
	public void player_rolls_monkey_parrot_parrot_monkey_sword_gold_sword_sword_and_gets_fortune_card(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Player {int} rerolls |sword|sword|sword|sword|sword|sword|sword|sword|")
	public void player_rerolls_sword_sword_sword_sword_sword_sword_sword_sword(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Player {int} sees winner message")
	public void player_sees_winner_message(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Game closes")
	public void game_closes() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
}
