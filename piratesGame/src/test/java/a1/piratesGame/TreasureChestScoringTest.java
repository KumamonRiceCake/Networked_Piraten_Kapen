package a1.piratesGame;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class TreasureChestScoringTest extends TestCase {
	private PiratesGame game;
	
	// Row 80
	@Given("The game is in progress...")
	public void the_game_is_in_progress() {
		game = new PiratesGame();
	}

	@When("Player with name {string} plays the game...")
	public void player_with_name_plays_the_game(String string) {
		game.setPlayer(new Player(string));
	}

	@When("Player draws treasure chest FC")
	public void player_draws_treasure_chest_FC() {
		game.getPlayer().setFortune("treasure chest");
	}

	@When("Player rolls die with three parrots, two swords, two diamonds and one coin")
	public void player_rolls_die_with_three_parrots_two_swords_two_diamonds_and_one_coin() {
		String[] roll = new String[]{"parrot", "parrot", "parrot", "sword", "sword", "diamond", "diamond", "gold"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player sees action menu and enters menu option {int}...")
	public void player_sees_action_menu_and_enters_menu_option(Integer int1) {
		game.userPrompt();
	}

	@When("Player puts two diamonds and one coin in chest")
	public void player_puts_two_diamonds_and_one_coin_in_chest() {
		String[] treasure = new String[]{"5", "6", "7"};
	    game.getPlayer().setTreasureChest(treasure);
	}

	@When("Player holds three parrots and rerolls two parrots")
	public void player_holds_three_parrots_and_rerolls_two_parrots() {
		String[] hold = new String[] {"0", "1", "2"};
		String[] newRoll = game.chestReroll(game.getPlayer().getHeldDie(), hold, game.getPlayer().getTreasureChest());
		newRoll[3] = "parrot";
		newRoll[4] = "parrot";
		game.getPlayer().resetHeldDie();
		game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player puts five parrots in chest")
	public void player_puts_five_parrots_in_chest() {
		String[] treasure = new String[]{"0", "1", "2", "3", "4"};
	    game.getPlayer().setTreasureChest(treasure);
	}

	@When("Player holds nothing and rerolls one skull, one coin and one parrot")
	public void player_holds_nothing_and_rerolls_one_skull_one_coin_and_one_parrot() {
		String[] hold = new String[] {};
		String[] newRoll = game.chestReroll(game.getPlayer().getHeldDie(), hold, game.getPlayer().getTreasureChest());
		newRoll[5] = "skull";
		newRoll[6] = "gold";
		newRoll[7] = "parrot";
		game.getPlayer().resetHeldDie();
		game.getPlayer().setFinalRoll(newRoll);
		game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}
	
	@Then("Player scores {int} and closes connection.")
	public void player_scores_and_closes_connection(Integer int1) {
		game.scoreForRound(game.getPlayer());
		assertTrue(int1.equals(game.getPlayer().getScore()));
		game = null;
	}

	// Row 85
	@When("Player rolls die with two skulls, three parrots and three coins")
	public void player_rolls_die_with_two_skulls_three_parrots_and_three_coins() {
		String[] roll = new String[]{"skull", "skull", "parrot", "parrot", "parrot", "gold", "gold", "gold"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player puts three coins in chest")
	public void player_puts_three_coins_in_chest() {
		String[] treasure = new String[]{"5", "6", "7"};
	    game.getPlayer().setTreasureChest(treasure);
	}

	@When("Player holds nothing and rerolls two diamonds and one coin")
	public void player_holds_nothing_and_rerolls_two_diamonds_and_one_coin() {
		String[] hold = new String[] {};
		String[] newRoll = game.chestReroll(game.getPlayer().getHeldDie(), hold, game.getPlayer().getTreasureChest());
		newRoll[2] = "diamond";
		newRoll[3] = "diamond";
		newRoll[4] = "gold";
		game.getPlayer().resetHeldDie();
		game.getPlayer().setFinalRoll(newRoll);
		game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player puts four coins in chest")
	public void player_puts_four_coins_in_chest() {
		String[] treasure = new String[]{"4", "5", "6", "7"};
	    game.getPlayer().setTreasureChest(treasure);
	}

	@When("Player holds nothing and rerolls one skull and one coin")
	public void player_holds_nothing_and_rerolls_one_skull_and_one_coin() {
		String[] hold = new String[] {};
		String[] newRoll = game.chestReroll(game.getPlayer().getHeldDie(), hold, game.getPlayer().getTreasureChest());
		newRoll[2] = "skull";
		newRoll[3] = "gold";
		game.getPlayer().resetHeldDie();
		game.getPlayer().setFinalRoll(newRoll);
		game.printDieRoll(newRoll, game.getPlayer().getFortune());
	}
	
	@Then("Player die with three skulls")
	public void player_die_with_three_skulls() {
		assertTrue(game.isDead(game.getPlayer().getHeldDie()));
	}
	
	@Then("Player scores {int} from treasure chest and closes connection")
	public void player_scores_from_treasure_chest_and_closes_connection(Integer int1) {
		game.scoreTreasureChest(game.getPlayer());
		assertTrue(int1.equals(game.getPlayer().getScore()));
		game = null;
	}
}
