@ Level 1a
Feature: Basic dying and scoring
  Player should be able to roll die and score - no reroll cases

  @ Row 43
  Scenario: Score first roll with nothing but 2 diamonds and 2 coins and FC is captain (SC 800)
    Given The game has started
    When Player "Kim" plays the game
    When Player draws captain FC
    When Player rolls die with two diamonds, two coins, one parrot, one sword, one monkey and one skull
    When Player sees menu and enters menu option 3
    Then Player scores 800

  @ Row 45
  Scenario: score 2 sets of 3 (monkey, swords) in RTS on first roll   (SC 300)
    Given The game has started
    When Player "Kim" plays the game
    When Player draws coin fortune card
    When Player rolls die with three monkeys, three swords, one parrot and one skull
    When Player sees menu and enters menu option 3
    Then Player scores 300

  @ Row 47
  Scenario: score a set of 3 diamonds correctly (i.e., 400 points)   (SC 500)
    Given The game has started
    When Player "Kim" plays the game
    When Player draws coin fortune card
    When Player rolls die with three diamonds, two parrots, two monkeys and one skull
    When Player sees menu and enters menu option 3
    Then Player scores 500

  @ Row 48
  Scenario: score a set of 4 coins correctly (i.e., 200 + 400 points) with FC is a diamond (SC 700)
    Given The game has started
    When Player "Kim" plays the game
    When Player draws diamond FC
    When Player rolls die with four coins, two parrots and two monkeys
    When Player sees menu and enters menu option 3
    Then Player scores 700
  
  @ Row 49
  Scenario: score set of 3 swords and set of 4 parrots correctly on first roll (SC 400 because of FC)
    Given The game has started
    When Player "Kim" plays the game
    When Player draws coin fortune card
    When Player rolls die with three swords, four parrots and one skull
    When Player sees menu and enters menu option 3
    Then Player scores 400

  @ Row 53
  Scenario: score set of 6 monkeys on first roll (SC 1100)
    Given The game has started
    When Player "Kim" plays the game
    When Player draws coin fortune card
    When Player rolls die with six monkeys and two skulls
    When Player sees menu and enters menu option 3
    Then Player scores 1100
  
  @ Row 54
  Scenario: score set of 7 parrots on first roll (SC 2100)
    Given The game has started
    When Player "Kim" plays the game
    When Player draws coin fortune card
    When Player rolls die with seven parrots and one skull
    When Player sees menu and enters menu option 3
    Then Player scores 2100
  
  @ Row 55
  Scenario: score set of 8 coins on first roll (SC 5400)  seq of 8 + 9 coins +  full chest (if you have it)
    Given The game has started
    When Player "Kim" plays the game
    When Player draws coin fortune card
    When Player rolls die with eight coins
    When Player sees menu and enters menu option 3
    Then Player scores 5400

  @ Row 56
  Scenario: score set of 8 coins on first roll and FC is diamond (SC 5400)  
    Given The game has started
    When Player "Kim" plays the game
    When Player draws diamond FC
    When Player rolls die with eight coins
    When Player sees menu and enters menu option 3
    Then Player scores 5400
  
  @ Row 57
  Scenario: score set of 8 swords on first roll and FC is captain (SC 4500x2 = 9000) if you have full chest
    Given The game has started
    When Player "Kim" plays the game
    When Player draws captain FC
    When Player rolls die with eight swords
    When Player sees menu and enters menu option 3
    Then Player scores 9000

  @ Row 63
  Scenario: score a set of 4 monkeys and a set of 3 coins (including the COIN fortune card) (SC 600)
    Given The game has started
    When Player "Kim" plays the game
    When Player draws coin fortune card
    When Player rolls die with four monkeys, two coins and two skulls 
    When Player sees menu and enters menu option 3
    Then Player scores 600
