package a1.piratesGame;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class SkullIslandTest extends TestCase {
	private PiratesGame game;
	
	// Row 100
	@Given("The game is in progress.....")
	public void the_game_is_in_progress() {
		game = new PiratesGame();
	}

	@When("Player with name {string} plays the game.....")
	public void player_with_name_plays_the_game(String string) {
		game.setPlayer(new Player(string));
	}

	@When("Player draws two skull FC")
	public void player_draws_two_skull_FC() {
		game.getPlayer().setFortune("2 skull");
	}

	@When("Player rolls die with one skull and seven parrots")
	public void player_rolls_die_with_one_skull_and_seven_parrots() {
		String[] roll = new String[]{"skull", "parrot", "parrot", "parrot", "parrot", "parrot", "parrot", "parrot"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@Then("Player dies with three skulls and sees {string}.")
	public void player_dies_with_three_skulls_and_sees(String string) {
		assertTrue(game.isDead(game.getPlayer().getHeldDie()));
	}
	
	@Then("Player scores {int} and closes connection...")
	public void player_scores_and_closes_connection(Integer int1) {
		assertTrue(int1.equals(game.getPlayer().getScore()));
		game = null;
	}

	// Row 101
	@When("Player draws one skull FC")
	public void player_draws_one_skull_FC() {
		game.getPlayer().setFortune("1 skull");
	}

	@When("Player rolls die with two skulls and six parrots")
	public void player_rolls_die_with_two_skulls_and_six_parrots() {
		String[] roll = new String[]{"skull", "skull", "parrot", "parrot", "parrot", "parrot", "parrot", "parrot"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

    // Row 102
	@When("Player with name {string} joins the game")
	public void player_with_name_joins_the_game(String string) {
	    assertTrue(game.playEntry(new Player(string)));
	}
	
	@When("Player {string} has {int} points")
	public void player_has_points(String string, Integer int1) {
	    game.getPlayers(string).setScore(int1);
	}

	@When("Player draws captain FC..")
	public void player_draws_captain_FC() {
		game.getPlayer().setFortune("captain");
	}
	
	@When("Player rolls die with five skulls and three coins")
	public void player_rolls_die_with_five_skulls_and_three_coins() {
		String[] roll = new String[]{"skull", "skull", "skull", "skull", "skull", "gold", "gold", "gold"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player sees {string} and {string} and enters anything")
	public void player_sees_and(String string, String string2) {
	    assertTrue(game.isSkullIsland(game.getPlayer().getHeldDie()));
	    game.skullIslandPrompt();
	}

	@When("Player rerolls three coins and gets one monkey and two swords")
	public void player_rerolls_three_coins_and_gets_one_monkey_and_two_swords() {
		String[] hold = new String[] {};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[5] = "monkey";
		newRoll[6] = "sword";
		newRoll[7] = "sword";
		game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player finishes turn")
	public void player_finishes_turn() {
	    game.getPlayer().nextRound();
	}

	@Then("Player {string} scores {int} points")
	public void player_scores_points(String string, Integer int1) {
	    GameServer server = new GameServer();
	    int numSkull = 0;
	    for (String s : game.getPlayer().getHeldDie()) {
	    	if (s.equals("skull"))
	    		numSkull++;
	    }
	    int deduction = game.deductSkullIsland(game.getPlayer(), numSkull);
	    game.getPlayers(string).setScore(game.getPlayers(string).getScore() + server.deductionPt(deduction));
	    assertTrue(int1.equals(game.getPlayers(string).getScore()));
	}

	// Row 103
	@When("Player rolls die with two skulls, six coins")
	public void player_rolls_die_with_two_skulls_six_coins() {
		String[] roll = new String[]{"skull", "skull", "gold", "gold", "gold", "gold", "gold", "gold"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player rerolls six coins and gets two skulls, two parrots and two monkeys")
	public void player_rerolls_six_coins_and_gets_two_skulls_two_parrots_and_two_monkeys() {
		String[] hold = new String[] {};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[2] = "skull";
		newRoll[3] = "skull";
		newRoll[4] = "parrot";
		newRoll[5] = "parrot";
		newRoll[6] = "monkey";
		newRoll[7] = "monkey";
		game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player rerolls two parrots and two monkeys and gets one skull and three diamonds")
	public void player_rerolls_two_parrots_and_two_monkeys_and_gets_one_skull_and_three_diamonds() {
		String[] hold = new String[] {};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[4] = "skull";
		newRoll[5] = "diamond";
		newRoll[6] = "diamond";
		newRoll[7] = "diamond";
		game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player rerolls three dimonds and get two swords and one monkey")
	public void player_rerolls_three_dimonds_and_get_two_swords_and_one_monkey() {
		String[] hold = new String[] {};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[5] = "sword";
		newRoll[6] = "sword";
		newRoll[7] = "monkey";
		game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 104
	@When("Player rolls die with three skulls and five coins")
	public void player_rolls_die_with_three_skulls_and_five_coins() {
		String[] roll = new String[]{"skull", "skull", "skull", "gold", "gold", "gold", "gold", "gold"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player rerolls five coins and gets two parrots and three monkeys")
	public void player_rerolls_five_coins_and_gets_two_parrots_and_three_monkeys() {
		String[] hold = new String[] {};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[3] = "parrot";
		newRoll[4] = "parrot";
		newRoll[5] = "monkey";
		newRoll[6] = "monkey";
		newRoll[7] = "monkey";
		game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 105
	@When("Player rerolls five coins and gets one skull and four monkeys")
	public void player_rerolls_five_coins_and_gets_one_skull_and_four_monkeys() {
		String[] hold = new String[] {};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[3] = "skull";
		newRoll[4] = "monkey";
		newRoll[5] = "monkey";
		newRoll[6] = "monkey";
		newRoll[7] = "monkey";
		game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player rerolls four monkeys and gets two diamonds and two swords")
	public void player_rerolls_four_monkeys_and_gets_two_diamonds_and_two_swords() {
		String[] hold = new String[] {};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[4] = "diamond";
		newRoll[5] = "diamond";
		newRoll[6] = "sword";
		newRoll[7] = "sword";
		game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 106
	@When("Player draws coin FC..")
	public void player_draws_coin_FC() {
		game.getPlayer().setFortune("gold");
	}
}
