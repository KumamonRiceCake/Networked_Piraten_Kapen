@ Level 1b
Feature: Advanced scoring
  Player should be able to roll die and score under complicated situations - sorceress FC

  @ Row 70
  Scenario: roll 2 skulls, reroll one of them due to sorceress, then go to next round of turn
    Given The game is in progress.
    When Player "Kim" plays the game.
    When Player draws sorceress FC
    When Player rolls die with two skulls, four swords and two coins
    When Player sees action menu and enters menu option 1.
    When Player holds four swords and two coins and rerolls
    When Player sees one skull is rerolled in a new set
    When Player sees action menu and enters menu option 3.
    Then Player scores some and goes to next round of turn

  @ Row 71
  Scenario: roll no skulls, then next round roll 1 skull and reroll for it, then score
    Given The game is in progress.
    When Player "Kim" plays the game.
    When Player draws sorceress FC
    When Player rolls die with two parrots, two swords, two monkeys and two coins
    When Player sees action menu and enters menu option 3.
    Then Player scores 200 and goes to next round of turn
    When Player rolls die with one skull, two parrots, two swords, two monkeys and one coin
    When Player sees action menu and enters menu option 1.
    When Player holds two parrots, two swords, two monkeys and one coin and rerolls 
    When Player sees one skull is rerolled in a new set
    When Player sees action menu and enters menu option 3.
    Then Player scores some

  @ Row 72
  Scenario: roll no skulls, then next round roll 1 skull and reroll for it, then go to next round 
    Given The game is in progress.
    When Player "Kim" plays the game.
    When Player draws sorceress FC
    When Player rolls die with two parrots, two swords, two monkeys and two coins
    When Player sees action menu and enters menu option 3.
    Then Player scores 200 and goes to next round of turn
    When Player rolls die with one skull, two parrots, two swords, two monkeys and one coin
    When Player sees action menu and enters menu option 1.
    When Player holds two parrots, two swords, two monkeys and one coin and rerolls 
    When Player sees one skull is rerolled in a new set
    When Player sees action menu and enters menu option 3.
    Then Player scores some and goes to next round of turn
