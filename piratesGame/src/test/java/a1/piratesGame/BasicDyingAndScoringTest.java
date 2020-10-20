package a1.piratesGame;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class BasicDyingAndScoringTest extends TestCase {
	private PiratesGame game;

	// Row 43
	@Given("The game has started")
	public void the_game_is_in_progress() {
		game = new PiratesGame();
	}
	
	@When("Player {string} plays the game")
	public void player_with_name_plays_the_game(String string) {
		game.setPlayer(new Player(string));
	}

	@When("Player draws captain FC")
	public void player_draws_captain_FC() {
		game.getPlayer().setFortune("captain");
	}

	@When("Player rolls die with two diamonds, two coins, one parrot, one sword, one monkey and one skull")
	public void player_rolls_die_with_two_diamonds_two_coins_one_parrot_one_sword_one_monkey_and_one_skull() {
		String[] roll = new String[]{"diamond", "diamond", "gold", "gold", "parrot", "sword", "monkey", "skull"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player sees menu and enters menu option {int}")
	public void player_sees_action_menu_and_enters_menu_option(Integer int1) {
		game.userPrompt();
	}

	@Then("Player scores {int}")
	public void player_scores_and_closes_connection(Integer int1) {
		game.scoreForRound(game.getPlayer());
		assertTrue(int1.equals(game.getPlayer().getScore()));
	}

	// Row 45
	@When("Player draws coin fortune card")
	public void player_draws_coin_FC() {
		game.getPlayer().setFortune("gold");
	}
	
	@When("Player rolls die with three monkeys, three swords, one parrot and one skull")
	public void player_rolls_die_with_three_monkeys_three_swords_one_parrot_and_one_skull() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "sword", "sword", "sword", "parrot", "skull"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	// Row 47
	@When("Player rolls die with three diamonds, two parrots, two monkeys and one skull")
	public void player_rolls_die_with_three_diamonds_two_parrots_two_monkeys_and_one_skull() {
		String[] roll = new String[]{"diamond", "diamond", "diamond", "parrot", "parrot", "monkey", "monkey", "skull"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	// Row 48
	@When("Player draws diamond FC")
	public void player_draws_diamond_FC() {
	    game.getPlayer().setFortune("diamond");
	}

	@When("Player rolls die with four coins, two parrots and two monkeys")
	public void player_rolls_die_with_four_coins_two_parrots_and_two_monkeys() {
		String[] roll = new String[]{"gold", "gold", "gold", "gold", "parrot", "parrot", "monkey", "monkey"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	// Row 49
	@When("Player rolls die with three swords, four parrots and one skull")
	public void player_rolls_die_with_three_swords_four_parrots_and_one_skull() {
		String[] roll = new String[]{"sword", "sword", "sword", "parrot", "parrot", "parrot", "parrot", "skull"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	// Row 53
	@When("Player rolls die with six monkeys and two skulls")
	public void player_rolls_die_with_six_monkeys_and_two_skulls() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "monkey", "monkey", "monkey", "skull", "skull"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	// Row 54
	@When("Player rolls die with seven parrots and one skull")
	public void player_rolls_die_with_seven_parrots_and_one_skull() {
		String[] roll = new String[]{"parrot", "parrot", "parrot", "parrot", "parrot", "parrot", "parrot", "skull"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	// Row 55
	@When("Player rolls die with eight coins")
	public void player_rolls_die_with_eight_coins() {
		String[] roll = new String[]{"gold", "gold", "gold", "gold", "gold", "gold", "gold", "gold"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	// Row 57
	@When("Player rolls die with eight swords")
	public void player_rolls_die_with_eight_swords() {
		String[] roll = new String[]{"sword", "sword", "sword", "sword", "sword", "sword", "sword", "sword"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	// Row 63
	@When("Player rolls die with four monkeys, two coins and two skulls")
	public void player_rolls_die_with_four_monkeys_two_coins_and_two_skulls() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "monkey", "gold", "gold", "skull", "skull"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}
}
