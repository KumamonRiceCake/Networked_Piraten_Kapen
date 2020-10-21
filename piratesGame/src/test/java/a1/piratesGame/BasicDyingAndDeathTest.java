package a1.piratesGame;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class BasicDyingAndDeathTest extends TestCase {
	private PiratesGame game;

	// Row 38
	@Given("The game is in progress")
	public void the_game_is_in_progress() {
		game = new PiratesGame();
	}

	@When("Player with name {string} plays the game")
	public void player_with_name_plays_the_game(String string) {
		game.setPlayer(new Player(string));
	}

	@When("Player draws coin FC")
	public void player_draws_coin_FC() {
		game.getPlayer().setFortune("gold");
	}

	@When("Player rolls die with three skulls and five diamonds")
	public void player_rolls_die_with_three_skulls_and_five_diamonds() {
		String[] roll = new String[]{"skull", "skull", "skull", "diamond", "diamond", "diamond", "diamond", "diamond"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@Then("Player dies with three skulls and sees {string}")
	public void player_dies_with_three_skulls_and_sees(String string) {
		assertTrue(game.isDead(game.getPlayer().getHeldDie()));
	}
	
	@Then("Player finishes the round")
	public void player_finishes_the_round() {
		assertFalse(game.roundOnGoing);
	}

	@Then("Player scores none and closes connection")
	public void player_scores_none_and_closes_connection() {
		assertEquals(0, game.getPlayer().getScore());
	}

	// Row 39
	@When("Player rolls die with one skull, four parrots, three swords")
	public void player_rolls_die_with_one_skull_four_parrots_three_swords() {
		String[] roll = new String[]{"skull", "parrot", "parrot", "parrot", "parrot", "sword", "sword", "sword"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player sees action menu and enters menu option {int}")
	public void player_sees_action_menu_and_enters_menu_option(Integer int1) {
		game.userPrompt();
	}

	@When("Player holds four parrots and rerolls three swords and gets two skulls and one sword")
	public void player_holds_four_parrots_and_rerolls_three_swords_and_gets_two_skulls_and_one_sword() {
		String[] hold = new String[] {"1", "2", "3", "4"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[5] = "skull";
		newRoll[6] = "skull";
		newRoll[7] = "sword";
		game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 40
	@When("Player rolls die with two skulls, four parrots, two swords")
	public void player_rolls_die_with_two_skulls_four_parrots_two_swords() {
		String[] roll = new String[]{"skull", "skull", "parrot", "parrot", "parrot", "parrot", "sword", "sword"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player holds four parrots and rerolls two swords and gets one skull and one sword")
	public void player_holds_four_parrots_and_rerolls_two_swords_and_gets_one_skull_and_one_sword() {
		String[] hold = new String[] {"2", "3", "4", "5"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[6] = "skull";
		newRoll[7] = "sword";
		game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 41
	@When("Player holds four parrots and rerolls three swords and gets one skull and two monkeys")
	public void player_holds_four_parrots_and_rerolls_three_swords_and_gets_one_skull_and_two_monkeys() {
		String[] hold = new String[] {"1", "2", "3", "4"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[5] = "skull";
		newRoll[6] = "monkey";
		newRoll[7] = "monkey";
		game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player holds four parrots and rerolls two monkeys and gets one skull and one monkey")
	public void player_holds_four_parrots_and_rerolls_two_monkeys_and_gets_one_skull_and_one_monkey() {
		String[] hold = new String[] {"1", "2", "3", "4"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[6] = "skull";
		newRoll[7] = "monkey";
		game.getPlayer().setFinalRoll(newRoll);
	}
}
