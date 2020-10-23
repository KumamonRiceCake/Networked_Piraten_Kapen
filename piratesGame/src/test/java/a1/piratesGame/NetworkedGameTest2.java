package a1.piratesGame;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;


public class NetworkedGameTest2 extends TestCase {
	private MockServer server;
	private MockClient players[];

	// Row 127
	@Given("the game has been started on port {int}.")
	public void the_game_has_been_started_on_port(Integer int1) {
		server = null;
		players = new MockClient[3];
		server = new MockServer(int1);
		server.start();
		players[0] = new MockClient(int1);
		players[0].game.setPlayer(new Player("Kim"));
		players[0].receiveId();
		players[0].sendPlayer();
		players[1] = new MockClient(int1);
		players[1].game.setPlayer(new Player("Jeong"));
		players[1].receiveId();
		players[1].sendPlayer();
		players[2] = new MockClient(int1);
		players[2].game.setPlayer(new Player("Won"));
		players[2].receiveId();
		players[2].sendPlayer();
	}
	
	@When("Round starts.")
	public void round_starts() {
		players[0].receivePlayer();
		players[1].receivePlayer();
		players[2].receivePlayer();
	}
	
	@When("Player {int} sees score board of round {int}")
	public void player_sees_score_board_of_round(Integer int1, Integer int2) {
		assertEquals(int2.intValue(), players[int1-1].receiveRound());
        players[int1-1].receiveScores();
        players[int1-1].game.printPlayerScores(players[int1-1].players);
	}
	
	@When("Player {int} rolls |parrot|parrot|gold|diamond|skull|gold|sword|sword| and gets {string} fortune card")
	public void player_rolls_parrot_parrot_gold_diamond_skull_gold_sword_sword_and_gets_fortune_card(Integer int1, String string) {
		String[] roll = new String[] {"parrot", "parrot", "gold", "diamond", "skull", "gold", "sword", "sword"};
		players[int1-1].game.getPlayer().setFinalRoll(roll);
		players[int1-1].game.getPlayer().setFortune(string);
		players[int1-1].game.printDieRoll(players[int1-1].game.getPlayer().getHeldDie(), players[int1-1].game.getPlayer().getFortune());
	}
	
	@When("Player {int} sees menu.")
	public void player_sees_menu(Integer int1) {
		players[int1-1].game.userPrompt();
	}
	
	@When("Player {int} enters menu option {int}.")
	public void player_enters_menu_option(Integer int1, Integer int2) {
		players[int1-1].sendData(int2.toString());
	}

	@When("Player {int} enters {string} to hold and rerolls |skull|parrot|gold|diamond|skull|gold|sword|skull| and die with three skulls")
	public void player_enters_to_hold_and_rerolls_skull_parrot_gold_diamond_skull_gold_sword_skull_and_die_with_three_skulls(Integer int1, String string) {
		players[int1-1].sendData(string);
		String[] hold = string.replaceAll("\\s", "").split(",");
		String[] newRoll = players[int1-1].game.holdAndReroll(players[int1-1].game.getPlayer().getHeldDie(), hold);
		newRoll[0] = "skull";
		newRoll[1] = "parrot";
		newRoll[4] = "skull";
		newRoll[6] = "sword";
		newRoll[7] = "skull";
		players[int1-1].game.getPlayer().setFinalRoll(newRoll);
		players[int1-1].game.printDieRoll(players[int1-1].game.getPlayer().getHeldDie(), players[int1-1].game.getPlayer().getFortune());
		assertTrue(players[int1-1].game.isDead(players[int1-1].game.getPlayer().getHeldDie()));
		assertFalse(players[int1-1].game.roundOnGoing);
	}
	
	@When("Player {int} sends score data to server and finishes turn.")
	public void player_sends_score_data_to_server_and_finishes_turn(Integer int1) {
		players[int1-1].sendScores(players[int1-1].game.getPlayer().getScore());
		players[int1-1].game.roundOnGoing = false;
	}

	@When("Player {int} rolls |gold|parrot|parrot|diamond|monkey|gold|sword|sword| and gets {string} fortune card")
	public void player_rolls_gold_parrot_parrot_diamond_monkey_gold_sword_sword_and_gets_fortune_card(Integer int1, String string) {
		String[] roll = new String[] {"gold", "parrot", "parrot", "diamond", "monkey", "gold", "sword", "sword"};
		players[int1-1].game.getPlayer().setFinalRoll(roll);
		players[int1-1].game.getPlayer().setFortune(string);
		players[int1-1].game.printDieRoll(players[int1-1].game.getPlayer().getHeldDie(), players[int1-1].game.getPlayer().getFortune());
	}

	@When("Player {int} enters {string} to hold and rerolls |gold|gold|gold|gold|skull|gold|gold|gold|")
	public void player_enters_to_hold_and_rerolls_gold_gold_gold_gold_skull_gold_gold_gold(Integer int1, String string) {
		players[int1-1].sendData(string);
		String[] hold = string.replaceAll("\\s", "").split(",");
		String[] newRoll = players[int1-1].game.holdAndReroll(players[int1-1].game.getPlayer().getHeldDie(), hold);
		newRoll[1] = "gold";
		newRoll[2] = "gold";
		newRoll[3] = "gold";
		newRoll[4] = "skull";
		newRoll[6] = "gold";
		newRoll[7] = "gold";
		players[int1-1].game.getPlayer().setFinalRoll(newRoll);
		players[int1-1].game.printDieRoll(players[int1-1].game.getPlayer().getHeldDie(), players[int1-1].game.getPlayer().getFortune());
	}

	@When("Player {int} sees scoring message and scores {int} points")
	public void player_sees_scoring_message_and_scores_points(Integer int1, Integer int2) {
		players[int1-1].game.scoreForRound(players[int1-1].game.getPlayer());
		assertTrue(int2.equals(players[int1-1].game.getPlayer().getScore()));
	}

	@When("Player {int} rolls |skull|skull|parrot|diamond|monkey|skull|sword|sword| and gets {string} fortune card and die with three skulls")
	public void player_rolls_skull_skull_parrot_diamond_monkey_skull_sword_sword_and_gets_fortune_card_and_die_with_three_skulls(Integer int1, String string) {
		String[] roll = new String[] {"skull", "skull", "parrot", "diamond", "monkey", "skull", "sword", "sword"};
		players[int1-1].game.getPlayer().setFinalRoll(roll);
		players[int1-1].game.getPlayer().setFortune(string);
		players[int1-1].game.printDieRoll(players[int1-1].game.getPlayer().getHeldDie(), players[int1-1].game.getPlayer().getFortune());
	}

	@When("Player {int} rolls |sword|parrot|parrot|diamond|monkey|gold|skull|sword| and gets {string} fortune card")
	public void player_rolls_sword_parrot_parrot_diamond_monkey_gold_skull_sword_and_gets_fortune_card(Integer int1, String string) {
		String[] roll = new String[] {"sword", "parrot", "parrot", "diamond", "monkey", "gold", "skull", "sword"};
		players[int1-1].game.getPlayer().resetHeldDie();
		players[int1-1].game.getPlayer().setFinalRoll(roll);
		players[int1-1].game.getPlayer().setFortune(string);
		players[int1-1].game.printDieRoll(players[int1-1].game.getPlayer().getHeldDie(), players[int1-1].game.getPlayer().getFortune());
	}

	@When("Player {int} rolls |diamond|parrot|parrot|diamond|monkey|gold|diamond|monkey| and gets {string} fortune card")
	public void player_rolls_diamond_parrot_parrot_diamond_monkey_gold_diamond_monkey_and_gets_fortune_card(Integer int1, String string) {
		String[] roll = new String[] {"diamond", "parrot", "parrot", "diamond", "monkey", "gold", "diamond", "monkey"};
		players[int1-1].game.getPlayer().resetHeldDie();
		players[int1-1].game.getPlayer().setFinalRoll(roll);
		players[int1-1].game.getPlayer().setFortune(string);
		players[int1-1].game.printDieRoll(players[int1-1].game.getPlayer().getHeldDie(), players[int1-1].game.getPlayer().getFortune());
	}

	@When("Player {int} rerolls |skull|parrot|skull|sword|skull|sword|monkey|diamond| and die with three skulls")
	public void player_rerolls_skull_parrot_skull_sword_skull_sword_skull_diamond_and_die_with_three_skulls(Integer int1) {
		String[] newRoll = {"skull", "parrot", "skull", "sword", "skull", "sword", "monkey", "diamond"};
		players[int1-1].game.getPlayer().resetHeldDie();
		players[int1-1].game.getPlayer().setFinalRoll(newRoll);
		players[int1-1].game.printDieRoll(players[int1-1].game.getPlayer().getHeldDie(), players[int1-1].game.getPlayer().getFortune());
		assertTrue(players[int1-1].game.isDead(players[int1-1].game.getPlayer().getHeldDie()));
		assertFalse(players[int1-1].game.roundOnGoing);
	}

	@When("Player {int} sees {string} message and scores {int} points")
	public void player_sees_message_and_scores_points(Integer int1, String string, Integer int2) {
		players[int1-1].game.scoreForRound(players[int1-1].game.getPlayer());
		assertTrue(int2.equals(players[int1-1].game.getPlayer().getScore()));
	}

	@When("Player {int} rolls |monkey|parrot|parrot|monkey|sword|gold|sword|sword| and gets {string} fortune card")
	public void player_rolls_monkey_parrot_parrot_monkey_sword_gold_sword_sword_and_gets_fortune_card(Integer int1, String string) {
		String[] roll = new String[] {"monkey", "parrot", "parrot", "monkey", "sword", "gold", "sword", "sword"};
		players[int1-1].game.getPlayer().resetHeldDie();
		players[int1-1].game.getPlayer().setFinalRoll(roll);
		players[int1-1].game.getPlayer().setFortune(string);
		players[int1-1].game.printDieRoll(players[int1-1].game.getPlayer().getHeldDie(), players[int1-1].game.getPlayer().getFortune());
	}

	@When("Player {int} enters {string} to hold and rerolls |sword|sword|sword|sword|sword|sword|sword|sword|")
	public void player_enters_to_hold_and_rerolls_sword_sword_sword_sword_sword_sword_sword_sword(Integer int1, String string) {
		players[int1-1].sendData(string);
		String[] hold = string.replaceAll("\\s", "").split(",");
		String[] newRoll = players[int1-1].game.holdAndReroll(players[int1-1].game.getPlayer().getHeldDie(), hold);
		newRoll[0] = "sword";
		newRoll[1] = "sword";
		newRoll[2] = "sword";
		newRoll[3] = "sword";
		newRoll[5] = "sword";
		players[int1-1].game.getPlayer().setFinalRoll(newRoll);
		players[int1-1].game.printDieRoll(players[int1-1].game.getPlayer().getHeldDie(), players[int1-1].game.getPlayer().getFortune());
	}
	
	@When("Game is over with a winner")
	public void game_is_over_with_a_winner() {
		assertEquals(-1, players[0].receiveRound());
		assertEquals(-1, players[1].receiveRound());
		assertEquals(-1, players[2].receiveRound());
	}
	
	@Then("Player {int} sees winner message")
	public void player_sees_winner_message(Integer int1) {
		players[int1-1].receiveScores();
		assertTrue(("Game over!!!\n" + "Won wins the game!").equals(players[int1-1].mockWinnerDeclare()));
		System.out.println(players[int1-1].mockWinnerDeclare());
	}
}
