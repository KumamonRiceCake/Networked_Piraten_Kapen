package a1.piratesGame;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class BasicDyingAndScoringTest2 extends TestCase {
	private PiratesGame game;
	
	// Row 44
	@Given("The game starts")
	public void the_game_is_in_progress() {
		game = new PiratesGame();
	}

	@When("{string} plays the game")
	public void player_with_name_plays_the_game(String string) {
		game.setPlayer(new Player(string));
	}
	
	@When("Player draws coin from fortune card deck")
	public void player_draws_coin_from_fortune_card_deck() {
		game.getPlayer().setFortune("gold");
	}

	@When("Player rolls die with two monkeys, two parrots, two sword and two skull")
	public void player_rolls_die_with_two_monkeys_two_parrots_two_sword_and_two_skull() {
		String[] roll = new String[]{"monkey", "monkey", "parrot", "parrot", "sword", "sword", "skull", "skull"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}
	
	@When("Player sees action menu and enters option {int}")
	public void player_sees_action_menu_and_enters_option(Integer int1) {
		game.userPrompt();
	}

	@When("Player holds two monkeys and rerolls one monkey, two swords and one parrot")
	public void player_holds_two_monkeys_and_rerolls_one_monkey_two_swords_two_parrots_and_one_skull() {
		String[] hold = new String[] {"0", "1"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[2] = "monkey";
		newRoll[3] = "sword";
		newRoll[4] = "sword";
		newRoll[5] = "parrot";
		game.getPlayer().setFinalRoll(newRoll);
	}
	
	@Then("Player score is {int}")
	public void player_score_is(Integer int1) {
		game.scoreForRound(game.getPlayer());
		assertTrue(int1.equals(game.getPlayer().getScore()));
	}

	// Row 46
	@When("Player holds two monkeys and two parrots and rerolls one monkey and one parrot")
	public void player_holds_two_monkeys_and_two_parrots_and_rerolls_one_monkey_and_one_parrot() {
		String[] hold = new String[] {"0", "1", "2", "3"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[4] = "monkey";
		newRoll[5] = "parrot";
		game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 50
	@When("Player rolls die with two coins, two swords, two monkeys and two parrots")
	public void player_rolls_die_with_two_coins_two_swords_two_monkeys_and_two_parrots() {
		String[] roll = new String[]{"gold", "gold", "sword", "sword", "monkey", "monkey", "parrot", "parrot"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player holds two coins and two swords and rerolls one coin, one sword and two monkeys")
	public void player_holds_two_coins_and_two_swords_and_rerolls_one_coin_one_sword_and_two_monkeys() {
		String[] hold = new String[] {"0", "1", "2", "3"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[4] = "gold";
		newRoll[5] = "sword";
		newRoll[6] = "monkey";
		newRoll[7] = "monkey";
		game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player holds three coins and three swords and rerolls one sword and one skull")
	public void player_holds_three_coins_and_three_swords_and_rerolls_one_sword_and_one_skull() {
		String[] hold = new String[] {"0", "1", "2", "3", "4", "5"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[6] = "sword";
		newRoll[7] = "skull";
		game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 51
	@When("Player draws captain from fortune card deck")
	public void player_draws_captain_from_fortune_card_deck() {
		game.getPlayer().setFortune("captain");
	}

	// Row 52
	@When("Player holds two swords and rerolls two swords, two monkeys and two skulls")
	public void player_holds_two_swords_and_rerolls_two_swords_two_monkeys_and_two_skulls() {
        String[] hold = new String[] {"2", "3"};
        String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
        newRoll[0] = "sword";
        newRoll[1] = "sword";
		newRoll[4] = "monkey";
		newRoll[5] = "monkey";
		newRoll[6] = "skull";
		newRoll[7] = "skull";
        game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player holds four swords and rerolls one sword and one parrot")
	public void player_holds_four_swords_and_rerolls_one_sword_and_one_parrot() {
        String[] hold = new String[] {"0", "1", "2", "3"};
        String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[4] = "sword";
		newRoll[5] = "parrot";
        game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 58
	@When("Player holds two monkeys and rerolls three monkeys, two swords and one parrot")
	public void player_holds_two_monkeys_and_rerolls_three_monkeys_two_swords_and_one_parrot() {
        String[] hold = new String[] {"4", "5"};
        String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
        newRoll[0] = "monkey";
        newRoll[1] = "monkey";
		newRoll[2] = "monkey";
		newRoll[3] = "sword";
		newRoll[6] = "sword";
		newRoll[7] = "parrot";
        game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player holds five monkeys and rerolls three monkeys")
	public void player_holds_five_monkeys_and_rerolls_three_monkeys() {
        String[] hold = new String[] {"0", "1", "2", "4", "5"};
        String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[3] = "monkey";
		newRoll[6] = "monkey";
		newRoll[7] = "monkey";
        game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 59
	@When("Player draws diamond from fortune card deck")
	public void player_draws_diamond_from_fortune_card_deck() {
		game.getPlayer().setFortune("diamond");
	}

	@When("Player rolls die with one diamond, two monkeys, two parrots, two swords and one skull")
	public void player_rolls_die_with_one_diamond_two_monkeys_two_parrots_two_swords_and_one_skull() {
		String[] roll = new String[]{"diamond", "monkey", "monkey", "parrot", "parrot", "sword", "sword", "skull"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player holds one diamond and rerolls one diamond, two monkeys, two parrots and one sword")
	public void player_holds_one_diamond_and_rerolls_one_diamond_two_monkeys_two_parrots_and_one_sword() {
        String[] hold = new String[] {"0"};
        String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[1] = "diamond";
		newRoll[2] = "monkey";
		newRoll[3] = "monkey";
		newRoll[4] = "parrot";
		newRoll[5] = "parrot";
		newRoll[6] = "sword";
        game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 60
	@When("Player holds one diamond and rerolls two diamonds, two monkeys, one parrot and one sword")
	public void player_holds_one_diamond_and_rerolls_two_diamonds_two_monkeys_one_parrot_and_one_sword() {
        String[] hold = new String[] {"0"};
        String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[1] = "diamond";
		newRoll[2] = "diamond";
		newRoll[3] = "monkey";
		newRoll[4] = "monkey";
		newRoll[5] = "parrot";
		newRoll[6] = "sword";
        game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 61
	@When("Player rolls die with one coin, two monkeys, two parrots, two swords and one skull")
	public void player_rolls_die_with_one_coin_two_monkeys_two_parrots_two_swords_and_one_skull() {
		String[] roll = new String[]{"gold", "monkey", "monkey", "parrot", "parrot", "sword", "sword", "skull"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player holds one coin and rerolls two coins, two monkeys, one parrots and one sword")
	public void player_holds_one_coin_and_rerolls_two_coins_two_monkeys_one_parrots_and_one_sword() {
        String[] hold = new String[] {"0"};
        String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[1] = "gold";
		newRoll[2] = "gold";
		newRoll[3] = "monkey";
		newRoll[4] = "monkey";
		newRoll[5] = "parrot";
		newRoll[6] = "sword";
        game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 65
	@When("Player rolls die with seven swords and one monkey")
	public void player_rolls_die_with_seven_swords_and_one_monkey() {
		String[] roll = new String[]{"sword", "sword", "sword", "sword", "sword", "sword", "sword", "monkey"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@Then("Player rerolls all eight die")
	public void player_rerolls_all_eight_die() {
        String[] hold = new String[] {};
        String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
        game.getPlayer().setFinalRoll(newRoll);
        assertEquals(8, game.getPlayer().getHeldDie().length);
	}
}
