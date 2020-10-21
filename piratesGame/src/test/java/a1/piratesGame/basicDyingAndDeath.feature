@ Level 1a
Feature: Basic dying and scoring
  Player should be able to roll die and score - 3 skulls death cases

  @ Row 38
  Scenario: Die with 3 skulls on first roll -> interface reports death & end of turn
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws coin FC
    When Player rolls die with three skulls and five diamonds
    Then Player dies with three skulls and sees "Dead with 3 skulls. End of the turn."
    Then Player finishes the round
    Then Player scores none and closes connection

  @ Row 39
  Scenario: Roll 1 skull, 4 parrots, 3 swords, hold parrots, reroll swords, get 2 skulls 1 sword die
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws coin FC
    When Player rolls die with one skull, four parrots, three swords
    When Player sees action menu and enters menu option 1
    When Player holds four parrots and rerolls three swords and gets two skulls and one sword
    Then Player dies with three skulls and sees "Dead with 3 skulls. End of the turn."
    Then Player finishes the round
    Then Player scores none and closes connection

  @ Row 40
  Scenario: Roll 2 skulls, 4 parrots, 2 swords, hold parrots, reroll swords, get 1 skull 1 sword die
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws coin FC
    When Player rolls die with two skulls, four parrots, two swords
    When Player sees action menu and enters menu option 1
    When Player holds four parrots and rerolls two swords and gets one skull and one sword
    Then Player dies with three skulls and sees "Dead with 3 skulls. End of the turn."
    Then Player finishes the round
    Then Player scores none and closes connection
    
  @ Row 41
  Scenario: Roll 1 skull, 4 parrots, 3 swords, hold parrots, reroll swords, get 1 skull 2 monkeys
  reroll 2 monkeys, get 1 skull 1 monkey and die
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws coin FC
    When Player rolls die with one skull, four parrots, three swords
    When Player sees action menu and enters menu option 1
    When Player holds four parrots and rerolls three swords and gets one skull and two monkeys
    When Player sees action menu and enters menu option 1
    When Player holds four parrots and rerolls two monkeys and gets one skull and one monkey
    Then Player dies with three skulls and sees "Dead with 3 skulls. End of the turn."
    Then Player finishes the round
    Then Player scores none and closes connection
