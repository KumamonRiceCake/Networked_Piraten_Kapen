@ Level 1b
Feature: Advanced scoring
  Player should be able to roll die and score under complicated situations - skull island and skull fortune cards

  @ Row 100
  Scenario: die by rolling one skull and having a FC with two skulls
    Given The game is in progress.....
    When Player with name "Kim" plays the game.....
    When Player draws two skull FC
    When Player rolls die with one skull and seven parrots
    Then Player dies with three skulls and sees "Dead with 3 skulls. End of the turn.".
    Then Player scores 0 and closes connection...
  
  @ Row 101
  Scenario: die by rolling 2 skulls and having a FC with 1 skull
    Given The game is in progress.....
    When Player with name "Kim" plays the game.....
    When Player draws one skull FC
    When Player rolls die with two skulls and six parrots
    Then Player dies with three skulls and sees "Dead with 3 skulls. End of the turn.".
    Then Player scores 0 and closes connection...
  
  @ Row 102
  Scenario: roll 5 skulls with FC: Captain => -1000 for all other players  (Captain doubles the penalty)
    Given The game is in progress.....
    When Player with name "Kim" plays the game.....
    When Player with name "Jeong" joins the game
    When Player "Jeong" has 1500 points
    When Player with name "Won" joins the game
    When Player "Won" has 2000 points
    When Player draws captain FC..
    When Player rolls die with five skulls and three coins
    When Player sees "SKULL ISLAND!" and "Enter anything to throw dice!" and enters anything
    When Player rerolls three coins and gets one monkey and two swords
    When Player finishes turn
    Then Player "Jeong" scores 500 points
    Then Player "Won" scores 1000 points
  
  @ Row 103
  Scenario: roll 2 skulls AND have a FC with two skulls: roll 2 skulls next roll, then 1 skull => -700 
    Given The game is in progress.....
    When Player with name "Kim" plays the game.....
    When Player with name "Jeong" joins the game
    When Player "Jeong" has 1500 points
    When Player with name "Won" joins the game
    When Player "Won" has 2000 points
    When Player draws two skull FC
    When Player rolls die with two skulls, six coins
    When Player sees "SKULL ISLAND!" and "Enter anything to throw dice!" and enters anything
    When Player rerolls six coins and gets two skulls, two parrots and two monkeys
    When Player sees "SKULL ISLAND!" and "Enter anything to throw dice!" and enters anything
    When Player rerolls two parrots and two monkeys and gets one skull and three diamonds
    When Player sees "SKULL ISLAND!" and "Enter anything to throw dice!" and enters anything
    When Player rerolls three dimonds and get two swords and one monkey
    When Player finishes turn
    Then Player "Jeong" scores 800 points
    Then Player "Won" scores 1300 points

  @ Row 104
  Scenario: roll 3 skulls AND have a FC with two skulls: roll no skulls next roll  => -500
    Given The game is in progress.....
    When Player with name "Kim" plays the game.....
    When Player with name "Jeong" joins the game
    When Player "Jeong" has 1500 points
    When Player with name "Won" joins the game
    When Player "Won" has 2000 points
    When Player draws two skull FC
    When Player rolls die with three skulls and five coins
    When Player sees "SKULL ISLAND!" and "Enter anything to throw dice!" and enters anything
    When Player rerolls five coins and gets two parrots and three monkeys
    When Player finishes turn
    Then Player "Jeong" scores 1000 points
    Then Player "Won" scores 1500 points
  
  @ Row 105
  Scenario: roll 3 skulls AND have a FC with 1 skull: roll 1 skull next roll then none => -500
    Given The game is in progress.....
    When Player with name "Kim" plays the game.....
    When Player with name "Jeong" joins the game
    When Player "Jeong" has 1500 points
    When Player with name "Won" joins the game
    When Player "Won" has 2000 points
    When Player draws one skull FC
    When Player rolls die with three skulls and five coins
    When Player sees "SKULL ISLAND!" and "Enter anything to throw dice!" and enters anything
    When Player rerolls five coins and gets one skull and four monkeys
    When Player sees "SKULL ISLAND!" and "Enter anything to throw dice!" and enters anything
    When Player rerolls four monkeys and gets two diamonds and two swords
    When Player finishes turn
    Then Player "Jeong" scores 1000 points
    Then Player "Won" scores 1500 points
  
  @ Row 106
  Scenario: show deduction received cannot make your score negative
    Given The game is in progress.....
    When Player with name "Kim" plays the game.....
    When Player with name "Jeong" joins the game
    When Player "Jeong" has 0 points
    When Player with name "Won" joins the game
    When Player "Won" has 400 points
    When Player draws coin FC..
    When Player rolls die with five skulls and three coins
    When Player sees "SKULL ISLAND!" and "Enter anything to throw dice!" and enters anything
    When Player rerolls three coins and gets one monkey and two swords
    When Player finishes turn
    Then Player "Jeong" scores 0 points
    Then Player "Won" scores 0 points
