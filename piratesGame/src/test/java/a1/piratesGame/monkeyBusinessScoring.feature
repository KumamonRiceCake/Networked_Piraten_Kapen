@ Level 1b
Feature: Advanced scoring
  Player should be able to roll die and score under complicated situations - monkey business FC
  
  @ Row 75
  Scenario: first roll gets 3 monkeys 3 parrots  1 skull 1 coin  SC = 1100
  (i.e., sequence of of 6 + coin)
    Given The game is in progress..
    When Player with name "Kim" plays the game..
    When Player draws monkey business fortune card
    When Player rolls die with three monkeys, three parrots, one skull and one coin
    When Player sees action menu and enters menu option 3..
    Then Player scores 1100 and closes connection

  @ Row 76
  Scenario: over several rolls: 2 monkeys, 1 parrot, 2 coins, 1 diamond, 2 swords    SC 400
    Given The game is in progress..
    When Player with name "Kim" plays the game..
    When Player draws monkey business fortune card
    When Player rolls die with two coins, two parrots, two monkeys and two swords
    When Player sees action menu and enters menu option 1..
    When Player holds two coins and rerolls one diamond, three swords, one parrot and one monkey
    When Player sees action menu and enters menu option 1..
    When Player holds two coins and one diamond and rerolls two monkeys, one parrot and two swords
    When Player sees action menu and enters menu option 3..
    Then Player scores 400 and closes connection

  @ Row 77
  Scenario: over several rolls get 3 monkeys, 4 parrots, 1 sword    SC 2000 (ie seq of 7)
    Given The game is in progress..
    When Player with name "Kim" plays the game..
    When Player draws monkey business fortune card
    When Player rolls die with two coins, two parrots, two monkeys and two swords
    When Player sees action menu and enters menu option 1..
    When Player holds two monkeys and two parrots and rerolls one diamond, one sword, one parrot and one monkey
    When Player sees action menu and enters menu option 1..
    When Player holds three monkeys and three parrots and rerolls one parrot and one sword
    When Player sees action menu and enters menu option 3..
    Then Player scores 2000 and closes connection
