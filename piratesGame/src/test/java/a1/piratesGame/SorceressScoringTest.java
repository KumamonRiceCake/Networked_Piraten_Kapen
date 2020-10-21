package a1.piratesGame;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class SorceressScoringTest extends TestCase {
	private PiratesGame game;
	
	// Row 70
	@Given("The game is in progress.")
	public void the_game_is_in_progress() {
		game = new PiratesGame();
	}

	@When("Player {string} plays the game.")
	public void player_with_name_plays_the_game(String string) {
		game.setPlayer(new Player(string));
	}

	@When("Player draws sorceress FC")
	public void player_draws_sorceress_FC() {
		game.getPlayer().setFortune("sorceress");
	}

	@When("Player rolls die with two skulls, four swords and two coins")
	public void player_rolls_die_with_two_skulls_four_swords_and_two_coins() {
		String[] roll = new String[]{"skull", "skull", "sword", "sword", "sword", "sword", "gold", "gold"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player sees action menu and enters menu option {int}.")
	public void player_sees_action_menu_and_enters_menu_option(Integer int1) {
		game.userPrompt();
	}
	
	@When("Player holds four swords and two coins and rerolls")
	public void player_holds_four_swords_and_two_coins_and_rerolls() {
		String[] hold = new String[] {"2", "3", "4", "5", "6", "7"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player sees one skull is rerolled in a new set")
	public void player_sees_one_skull_is_rerolled_in_a_new_set() {
		game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@Then("Player scores some and goes to next round of turn")
	public void player_scores_some_and_goes_to_next_round_of_turn() {
		game.scoreForRound(game.getPlayer());
		assertTrue(game.getPlayer().getScore() > 0);
		game.getPlayer().resetHeldDie();
		game.getPlayer().nextRound();
	}

	// Row 71
	@When("Player rolls die with two parrots, two swords, two monkeys and two coins")
	public void player_rolls_die_with_two_parrots_two_swords_two_monkeys_and_two_coins() {
		String[] roll = new String[]{"parrot", "parrot", "sword", "sword", "monkey", "monkey", "gold", "gold"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@Then("Player scores {int} and goes to next round of turn")
	public void player_scores_and_goes_to_next_round_of_turn(Integer int1) {
		game.scoreForRound(game.getPlayer());
		assertTrue(int1.equals(game.getPlayer().getScore()));
		game.getPlayer().resetHeldDie();
		game.getPlayer().nextRound();
	}

	@When("Player rolls die with one skull, two parrots, two swords, two monkeys and one coin")
	public void player_rolls_die_with_one_skull_two_parrots_two_swords_two_monkeys_and_one_coin() {
		String[] roll = new String[]{"skull", "parrot", "parrot", "sword", "sword", "monkey", "monkey", "gold"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player holds two parrots, two swords, two monkeys and one coin and rerolls")
	public void player_holds_two_parrots_two_swords_two_monkeys_and_one_coin_and_rerolls() {
		String[] hold = new String[] {"1", "2", "3", "4", "5", "6", "7"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		game.getPlayer().setFinalRoll(newRoll);
	}

	@Then("Player scores some")
	public void player_scores_some() {
		game.scoreForRound(game.getPlayer());
		assertTrue(game.getPlayer().getScore() > 0);
		game.getPlayer().resetHeldDie();
		game.getPlayer().nextRound();
	}
}
