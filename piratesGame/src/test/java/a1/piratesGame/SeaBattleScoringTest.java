package a1.piratesGame;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class SeaBattleScoringTest extends TestCase {
	private PiratesGame game;
	
	// Row 109
	@Given("The game is in progress......")
	public void the_game_is_in_progress() {
		game = new PiratesGame();
	}

	@When("Player with name {string} plays the game......")
	public void player_with_name_plays_the_game(String string) {
		game.setPlayer(new Player(string));
	}

	@When("Player has {int} points")
	public void player_has_points(Integer int1) {
	    game.getPlayer().setScore(int1);
	}

	@When("Player draws two swords sea battle FC")
	public void player_draws_two_swords_sea_battle_FC() {
		game.getPlayer().setFortune("2 sabre sea battle");
	}

	@When("Player rolls die with three skulls and five golds")
	public void player_rolls_die_with_three_skulls_and_five_golds() {
		String[] roll = new String[]{"skull", "skull", "skull", "gold", "gold", "gold", "gold", "gold"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@Then("Player dies with three skulls and sees {string}..")
	public void player_dies_with_three_skulls_and_sees(String string) {
		assertTrue(game.isDead(game.getPlayer().getHeldDie()));
	}

	@Then("Player sees {string} and scores {int} and closes connection...")
	public void player_sees_and_scores_and_closes_connection(String string, Integer int1) {
		game.scoreForRound(game.getPlayer());
		assertTrue(int1.equals(game.getPlayer().getScore()));
		game = null;
	}

	// Row 110
	@When("Player draws three swords sea battle FC")
	public void player_draws_three_swords_sea_battle_FC() {
		game.getPlayer().setFortune("3 sabre sea battle");
	}

	// Row 111
	@When("Player draws four swords sea battle FC")
	public void player_draws_four_swords_sea_battle_FC() {
		game.getPlayer().setFortune("4 sabre sea battle");
	}

	@When("Player rolls die with three monkeys, two swords, one coin and two parrots")
	public void player_rolls_die_with_three_monkeys_two_swords_one_coin_and_two_parrots() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "sword", "sword", "gold", "parrot", "parrot"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player sees action menu and enters menu option {int}.....")
	public void player_sees_action_menu_and_enters_menu_option(Integer int1) {
		game.userPrompt();
	}

	// Row 114
	@When("Player rolls die with four monkeys, one swords, one skull and two parrots")
	public void player_rolls_die_with_four_monkeys_one_swords_one_skull_and_two_parrots() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "monkey", "sword", "skull", "parrot", "parrot"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player holds four monkeys and one sword and rerolls one sword and one skull")
	public void player_holds_four_monkeys_and_one_sword_and_rerolls_one_sword_and_one_skull() {
		String[] hold = new String[] {"0", "1", "2", "3", "4"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[6] = "sword";
		newRoll[7] = "skull";
		game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 116
	@When("Player rolls die with three monkeys, four swords and one parrot")
	public void player_rolls_die_with_three_monkeys_four_swords_and_one_parrot() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "sword", "sword", "sword", "sword", "parrot"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	// Row 117
	@When("Player rolls die with four monkeys, two swords and two skulls")
	public void player_rolls_die_with_four_monkeys_two_swords_and_two_skulls() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "monkey", "sword", "sword", "skull", "skull"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player holds two swords and rerolls two skulls and two swords")
	public void player_holds_two_swords_and_rerolls_two_skulls_and_two_swords() {
		String[] hold = new String[] {"4", "5"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[0] = "skull";
		newRoll[1] = "skull";
		newRoll[2] = "sword";
		newRoll[3] = "sword";
		game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 119
	@When("Player rolls die with three monkeys, four swords and one skull")
	public void player_rolls_die_with_three_monkeys_four_swords_and_one_skull() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "sword", "sword", "sword", "sword", "skull"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	// Row 120
	@When("Player rolls die with three monkeys, one sword, one skull, one diamond and two parrots")
	public void player_rolls_die_with_three_monkeys_one_sword_one_skull_one_diamond_and_two_parrots() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "sword", "skull", "diamond", "parrot", "parrot"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player holds three monkeys, one sword and one diamond and rerolls two swords")
	public void player_holds_three_monkeys_one_sword_and_one_diamond_and_rerolls_two_swords() {
		String[] hold = new String[] {"0", "1", "2", "3", "5"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[6] = "sword";
		newRoll[7] = "sword";
		game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player holds three swords, one diamond and rerolls one sword and two parrots")
	public void player_holds_three_swords_one_diamond_and_rerolls_one_sword_and_two_parrots() {
		String[] hold = new String[] {"3", "5", "6", "7"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[0] = "sword";
		newRoll[1] = "parrot";
		newRoll[2] = "parrot";
		game.getPlayer().setFinalRoll(newRoll);
	}
}
