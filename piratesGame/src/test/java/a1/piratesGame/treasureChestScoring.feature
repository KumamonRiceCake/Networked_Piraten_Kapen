@ Level 1b
Feature: Advanced scoring
  Player should be able to roll die and score under complicated situations - treasure chest FC
  
  @ Row 80
  Scenario: roll 3 parrots, 2 swords, 2 diamonds, 1 coin     put 2 diamonds and 1 coin in chest
  then reroll 2 swords and get 2 parrots put 5 parrots in chest and take out 2 diamonds & coin
  then reroll the 3 dice and get 1 skull, 1 coin and a parrot
  score 6 parrots + 1 coin for 1100 points
    Given The game is in progress...
    When Player with name "Kim" plays the game...
    When Player draws treasure chest FC
    When Player rolls die with three parrots, two swords, two diamonds and one coin
    When Player sees action menu and enters menu option 4...
    When Player puts two diamonds and one coin in chest
    When Player holds three parrots and rerolls two parrots
    When Player sees action menu and enters menu option 4...
    When Player puts five parrots in chest
    When Player holds nothing and rerolls one skull, one coin and one parrot
    When Player sees action menu and enters menu option 3...
    Then Player scores 1100 and closes connection.
  
  @ Row 85
  Scenario: roll 2 skulls, 3 parrots, 3 coins   put 3 coins in chest
  then rerolls 3 parrots and get 2 diamonds 1 coin    put coin in chest (now 4)
  then reroll 2 diamonds and get 1 skull 1 coin     SC for chest only = 400 + 200 = 600
  also interface reports death & end of turn
    Given The game is in progress...
    When Player with name "Kim" plays the game...
    When Player draws treasure chest FC
    When Player rolls die with two skulls, three parrots and three coins
    When Player sees action menu and enters menu option 4...
    When Player puts three coins in chest
    When Player holds nothing and rerolls two diamonds and one coin
    When Player sees action menu and enters menu option 4...
    When Player puts four coins in chest
    When Player holds nothing and rerolls one skull and one coin
    Then Player die with three skulls
    Then Player scores 600 from treasure chest and closes connection
