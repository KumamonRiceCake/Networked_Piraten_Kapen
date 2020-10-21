package a1.piratesGame;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class FullChestScoringTest extends TestCase {
	private PiratesGame game;
	
	// Row 91
	@Given("The game is in progress....")
	public void the_game_is_in_progress() {
		game = new PiratesGame();
	}

	@When("Player with name {string} plays the game....")
	public void player_with_name_plays_the_game(String string) {
		game.setPlayer(new Player(string));
	}
	
	@When("Player draws coin FC.")
	public void player_draws_coin_FC() {
		game.getPlayer().setFortune("gold");
	}

	@When("Player rolls die with three monkeys, three swords, one diamond and one parrot")
	public void player_rolls_die_with_three_monkeys_three_swords_one_diamond_and_one_parrot() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "sword", "sword", "sword", "diamond", "parrot"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player sees action menu and enters menu option {int}....")
	public void player_sees_action_menu_and_enters_menu_option(Integer int1) {
		game.userPrompt();
	}

	@Then("Player scores {int} and closes connection..")
	public void player_scores_and_closes_connection(Integer int1) {
		game.scoreForRound(game.getPlayer());
		assertTrue(int1.equals(game.getPlayer().getScore()));
		game = null;
	}

	// Row 92
	@When("Player draws captain FC.")
	public void player_draws_captain_FC() {
		game.getPlayer().setFortune("captain");
	}
	
	@When("Player rolls die with three monkeys, three swords and two coins")
	public void player_rolls_die_with_three_monkeys_three_swords_and_two_coins() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "sword", "sword", "sword", "gold", "gold"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	// Row 93
	@When("Player rolls die with three monkeys, four swords and one diamond")
	public void player_rolls_die_with_three_monkeys_four_swords_and_one_diamond() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "sword", "sword", "sword", "sword", "diamond"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	// Row 94
	@When("Player draws two sword sea battle FC.")
	public void player_draws_two_sword_sea_battle_FC() {
		game.getPlayer().setFortune("2 sabre sea battle");
	}

	@When("Player rolls die with four monkeys, one sword, two parrots and one coin")
	public void player_rolls_die_with_four_monkeys_one_sword_two_parrots_and_one_coin() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "monkey", "sword", "parrot", "parrot", "gold"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player holds four monkeys, one sword and one coin and rerolls one coin and one sword")
	public void player_holds_four_monkeys_one_sword_and_one_coin_and_rerolls_one_coin_and_one_sword() {
		String[] hold = new String[] {"0", "1", "2", "3", "4", "7"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[5] = "gold";
		newRoll[6] = "sword";
		game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player sees {string} message")
	public void player_sees_message(String string) {
	    // Nothing to be done
	}

	// Row 97
	@When("Player draws monkey business FC.")
	public void player_draws_monkey_business_FC() {
		game.getPlayer().setFortune("monkey business");
	}

	@When("Player rolls die with two monkeys, one parrot, two coins and three diamonds")
	public void player_rolls_die_with_two_monkeys_one_parrot_two_coins_and_three_diamonds() {
		String[] roll = new String[]{"monkey", "monkey", "parrot", "gold", "gold", "diamond", "diamond", "diamond"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}
}
