package a1.piratesGame;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class MonkeyBusinessScoringTest extends TestCase {
	PiratesGame game;

	// Row 75
	@When("Player draws monkey business FC")
	public void player_draws_monkey_business_FC() {
		game.getPlayer().setFortune("monkey business");
	}

	@When("Player rolls die with three monkeys, three parrots, one skull and one coin")
	public void player_rolls_die_with_three_monkeys_three_parrots_one_skull_and_one_coin() {
		String[] roll = new String[]{"monkey", "monkey", "monkey", "parrot", "parrot", "parrot", "skull", "gold"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	// Row 76
	@When("Player rolls die with two coins, two parrots, two monkeys and two swords")
	public void player_rolls_die_with_two_coins_two_parrots_two_monkeys_and_two_swords() {
		String[] roll = new String[]{"gold", "gold", "parrot", "parrot", "monkey", "monkey", "sword", "sword"};
	    game.getPlayer().setFinalRoll(roll);
	    game.printDieRoll(game.getPlayer().getHeldDie(), game.getPlayer().getFortune());
	}

	@When("Player holds two coins and rerolls one diamond, three swords, one parrot and one monkey")
	public void player_holds_two_coins_and_rerolls_one_diamond_three_swords_one_parrot_and_one_monkey() {
		String[] hold = new String[] {"0", "1"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[2] = "diamond";
		newRoll[3] = "sword";
		newRoll[4] = "sword";
		newRoll[5] = "sword";
		newRoll[6] = "parrot";
		newRoll[7] = "monkey";
		game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player holds two coins and one diamond and rerolls two monkeys, one parrot and two swords")
	public void player_holds_two_coins_and_one_diamond_and_rerolls_two_monkeys_one_parrot_and_two_swords() {
		String[] hold = new String[] {"0", "1", "2"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[3] = "monkey";
		newRoll[4] = "monkey";
		newRoll[5] = "parrot";
		newRoll[6] = "sword";
		newRoll[7] = "sword";
		game.getPlayer().setFinalRoll(newRoll);
	}

	// Row 77
	@When("Player holds two monkeys and two parrots and rerolls one diamond, one sword, one parrot and one monkey")
	public void player_holds_two_monkeys_and_two_parrots_and_rerolls_one_diamond_one_sword_one_parrot_and_one_monkey() {
		String[] hold = new String[] {"2", "3", "4", "5"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[0] = "diamond";
		newRoll[1] = "sword";
		newRoll[6] = "parrot";
		newRoll[7] = "monkey";
		game.getPlayer().setFinalRoll(newRoll);
	}

	@When("Player holds three monkeys and three parrots and rerolls one parrot and one sword")
	public void player_holds_three_monkeys_and_three_parrots_and_rerolls_one_parrot_and_one_sword() {
		String[] hold = new String[] {"2", "3", "4", "5", "6", "7"};
		String[] newRoll = game.holdAndReroll(game.getPlayer().getHeldDie(), hold);
		newRoll[0] = "parrot";
		newRoll[1] = "sword";
		game.getPlayer().setFinalRoll(newRoll);
	}
}
