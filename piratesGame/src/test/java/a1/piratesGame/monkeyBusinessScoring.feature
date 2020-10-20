@ Level 1b
Feature: Advanced scoring
  Player should be able to roll die and score under complicated situations

  @ Row 70
  Scenario: roll 2 skulls, reroll one of them due to sorceress, then go to next round of turn
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws sorceress FC
    When Player rolls die with two skulls, four swords and two coins
    When Player sees action menu and enters menu option 1
    When Player holds four swords and two coins and rerolls
    When Player sees one skull is rerolled in a new set
    When Player sees action menu and enters menu option 3
    Then Player scores some and goes to next round of turn

  @ Row 71
  Scenario: roll no skulls, then next round roll 1 skull and reroll for it, then score
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws sorceress FC
    When Player rolls die with two parrots, two swords, two monkeys and two coins
    When Player sees action menu and enters menu option 3
    Then Player scores 200 and goes to next round of turn
    When Player rolls die with one skull, two parrots, two swords, two monkeys and one coin
    When Player sees action menu and enters menu option 1
    When Player holds two parrots, two swords, two monkeys and one coin and rerolls 
    When Player sees one skull is rerolled in a new set
    When Player sees action menu and enters menu option 3
    Then Player scores some

  @ Row 72
  Scenario: roll no skulls, then next round roll 1 skull and reroll for it, then go to next round 
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws sorceress FC
    When Player rolls die with two parrots, two swords, two monkeys and two coins
    When Player sees action menu and enters menu option 3
    Then Player scores 200 and goes to next round of turn
    When Player rolls die with one skull, two parrots, two swords, two monkeys and one coin
    When Player sees action menu and enters menu option 1
    When Player holds two parrots, two swords, two monkeys and one coin and rerolls 
    When Player sees one skull is rerolled in a new set
    When Player sees action menu and enters menu option 3
    Then Player scores some and goes to next round of turn
  
  @ Row 75
  Scenario: first roll gets 3 monkeys 3 parrots  1 skull 1 coin  SC = 1100
  (i.e., sequence of of 6 + coin)
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws monkey business FC
    When Player rolls die with three monkeys, three parrots, one skull and one coin
    When Player sees action menu and enters menu option 3
    Then Player scores 1100 and closes connection

  @ Row 76
  Scenario: over several rolls: 2 monkeys, 1 parrot, 2 coins, 1 diamond, 2 swords    SC 400
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws monkey business FC
    When Player rolls die with two coins, two parrots, two monkeys and two swords
    When Player sees action menu and enters menu option 1
    When Player holds two coins and rerolls one diamond, three swords, one parrot and one monkey
    When Player sees action menu and enters menu option 1
    When Player holds two coins and one diamond and rerolls two monkeys, one parrot and two swords
    When Player sees action menu and enters menu option 3
    Then Player scores 400 and closes connection

  @ Row 77
  Scenario: over several rolls get 3 monkeys, 4 parrots, 1 sword    SC 2000 (ie seq of 7)
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws monkey business FC
    When Player rolls die with two coins, two parrots, two monkeys and two swords
    When Player sees action menu and enters menu option 1
    When Player holds two monkeys and two parrots and rerolls one diamond, one sword, one parrot and one monkey
    When Player sees action menu and enters menu option 1
    When Player holds three monkeys and three parrots and rerolls one parrot and one sword
    When Player sees action menu and enters menu option 3
    Then Player scores 2000 and closes connection
  
  @ Row 80
  Scenario: roll 3 parrots, 2 swords, 2 diamonds, 1 coin     put 2 diamonds and 1 coin in chest
  then reroll 2 swords and get 2 parrots put 5 parrots in chest and take out 2 diamonds & coin
  then reroll the 3 dice and get 1 skull, 1 coin and a parrot
  score 6 parrots + 1 coin for 1100 points
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws treasure chest FC
    When Player rolls die with three parrots, two swords, two diamonds and one coin
    When Player sees action menu and enters menu option 4
    When Player puts two diamonds and one coin in chest
    When Player holds three parrots and rerolls two parrots
    When Player sees action menu and enters menu option 4
    When Player puts five parrots in chest
    When Player holds nothing and rerolls one skull, one coin and one parrot
    When Player sees action menu and enters menu option 3
    Then Player scores 1100 and closes connection
  
  @ Row 85
  Scenario: roll 2 skulls, 3 parrots, 3 coins   put 3 coins in chest
  then rerolls 3 parrots and get 2 diamonds 1 coin    put coin in chest (now 4)
  then reroll 2 diamonds and get 1 skull 1 coin     SC for chest only = 400 + 200 = 600
  also interface reports death & end of turn
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws treasure chest FC
    When Player rolls die with two skulls, three parrots and three coins
    When Player sees action menu and enters menu option 4
    When Player puts three coins in chest
    When Player holds nothing and rerolls two diamonds and one coin
    When Player sees action menu and enters menu option 4
    When Player puts four coins in chest
    When Player holds nothing and rerolls one skull and one coin
    Then Player scores 600 and closes connection

  @ Row 91
  Scenario: 3 monkeys, 3 swords, 1 diamond, 1 parrot FC: coin   => SC 400  (ie no bonus)
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws coin FC
    When Player rolls die with three monkeys, three swords, one diamond and one parrot
    When Player sees action menu and enters menu option 3
    Then Player scores 400 and closes connection
  
  @ Row 92
  Scenario: 3 monkeys, 3 swords, 2 coins FC: captain   => SC (100+100+200+500)*2 =  1800
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws captain FC
    When Player rolls die with three monkeys, three swords and two coins
    When Player sees action menu and enters menu option 3
    Then Player scores 1800 and closes connection
  
  @ Row 93
  Scenario: 3 monkeys, 4 swords, 1 diamond, FC: coin   => SC 1000  (ie 100++200+100+100+bonus)
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws coin FC
    When Player rolls die with three monkeys, four swords and one diamond
    When Player sees action menu and enters menu option 3
    Then Player scores 1000 and closes connection
  
  @ Row 94
  Scenario: FC: 2 sword sea battle, first  roll:  4 monkeys, 1 sword, 2 parrots and a coin
  then reroll 2 parrots and get coin and 2nd sword
  score is: 200 (coins) + 200 (monkeys) + 300 (swords of battle) + 500 (full chest) = 1200
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws two sword sea battle FC
    When Player rolls die with four monkeys, one sword, two parrots and one coin
    When Player sees action menu and enters menu option 1
    When Player holds four monkeys, one sword and one coin and rerolls one coin and one sword
    When Player sees action menu and enters menu option 3
    When Player sees "Battle win, +300 pts" message
    Then Player scores 1200 and closes connection

  @ Row 97
  Scenario: FC: monkey business and RTS: 2 monkeys, 1 parrot, 2 coins, 3 diamonds   SC 1200 (bonus)
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws monkey business FC
    When Player rolls die with two monkeys, one parrot, two coins and three diamonds
    When Player sees action menu and enters menu option 3
    Then Player scores 1200 and closes connection
  
  @ Row 100
  Scenario: die by rolling one skull and having a FC with two skulls
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws two skull FC
    When Player rolls die with one skull and seven parrots
    Then Player dies with three skulls and sees "Dead with 3 skulls. End of the turn."
    Then Player scores 0 and closes connection
  
  @ Row 101
  Scenario: die by rolling 2 skulls and having a FC with 1 skull
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws one skull FC
    When Player rolls die with two skulls and six parrots
    Then Player dies with three skulls and sees "Dead with 3 skulls. End of the turn."
    Then Player scores 0 and closes connection
  
  @ Row 102
  Scenario: roll 5 skulls with FC: Captain => -1000 for all other players  (Captain doubles the penalty)
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player 2 with name "Jeong" plays the game
    When Player 2 has 1500 points
    When Player 3 with name "Won" plays the game
    When Player 3 has 2000 points
    When Player draws captain FC
    When Player rolls die with 5 skulls and 3 coins
    When Player sees "SKULL ISLAND!"
    When Player sees "Enter anything to throw dice!"
    When Player enters anything
    When Player rerolls 3 coins and gets 1 monkey and 2 swords
    When Player finishes turn
    Then Player 2 scores 500 points
    Then Player 3 scores 1000 points
  
  @ Row 103
  Scenario: roll 2 skulls AND have a FC with two skulls: roll 2 skulls next roll, then 1 skull => -700 
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player 2 with name "Jeong" plays the game
    When Player 2 has 1500 points
    When Player 3 with name "Won" plays the game
    When Player 3 has 2000 points
    When Player draws 2 skull FC
    When Player rolls die with 2 skulls, 6 coins
    When Player sees "SKULL ISLAND!"
    When Player sees "Enter anything to throw dice!"
    When Player enters anything
    When Player rerolls 6 coins and gets 2 skulls, 2 parrots and 2 monkeys
    When Player sees "SKULL ISLAND!"
    When Player sees "Enter anything to throw dice!"
    When Player rerolls 2 parrots and 2 monkeys and gets 1 skull and 3 diamonds
    When Player sees "SKULL ISLAND!"
    When Player sees "Enter anything to throw dice!"
    When Player enters anything
    When Player rerolls 3 dimonds and get 2 swords and 1 monkey
    When Player finishes turn
    Then Player 2 scores 800 points
    Then Player 3 scores 1300 points

  @ Row 104
  Scenario: roll 3 skulls AND have a FC with two skulls: roll no skulls next roll  => -500
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player 2 with name "Jeong" plays the game
    When Player 2 has 1500 points
    When Player 3 with name "Won" plays the game
    When Player 3 has 2000 points
    When Player draws 2 skull FC
    When Player rolls die with 3 skulls, 5 coins
    When Player sees "SKULL ISLAND!"
    When Player sees "Enter anything to throw dice!"
    When Player enters anything
    When Player rerolls 5 coins and gets 2 parrots and 3 monkeys
    When Player finishes turn
    Then Player 2 scores 1000 points
    Then Player 3 scores 1500 points
  
  @ Row 105
  Scenario: roll 3 skulls AND have a FC with 1 skull: roll 1 skull next roll then none => -500
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player 2 with name "Jeong" plays the game
    When Player 2 has 1500 points
    When Player 3 with name "Won" plays the game
    When Player 3 has 2000 points
    When Player draws 1 skull FC
    When Player rolls die with 3 skulls, 5 coins
    When Player sees "SKULL ISLAND!"
    When Player sees "Enter anything to throw dice!"
    When Player enters anything
    When Player rerolls 5 coins and gets 1 skull and 4 monkeys
    When Player sees "SKULL ISLAND!"
    When Player sees "Enter anything to throw dice!"
    When Player enters anything
    When Player rerolls 4 monkeys and gets 2 diamonds and 2 swords
    When Player finishes turn
    Then Player 2 scores 1000 points
    Then Player 3 scores 1500 points
  
  @ Row 106
  Scenario: show deduction received cannot make your score negative
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player 2 with name "Jeong" plays the game
    When Player 2 has 0 points
    When Player 3 with name "Won" plays the game
    When Player 3 has 400 points
    When Player draws coin FC
    When Player rolls die with 5 skulls and 3 coins
    When Player sees "SKULL ISLAND!"
    When Player sees "Enter anything to throw dice!"
    When Player enters anything
    When Player rerolls 3 coins and gets 1 monkey and 2 swords
    When Player finishes turn
    Then Player 2 scores 0 points
    Then Player 3 scores 0 points

  @ Row 109
  Scenario: FC 2 swords, die on first roll   => lose 300 points
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player has 1000 points
    When Player draws 2 swords sea battle FC
    When Player rolls die with 3 skulls and 5 golds

    Then Player dies with 3 skulls and sees "Dead with 3 skulls. End of the turn."
    Then Player sees "Battle lost, -300 pts"
    Then Player scores 700 and closes connection
  
  @ Row 110
  Scenario: FC 3 swords, die on first roll   => lose 500 points
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player has 1000 points
    When Player draws 3 swords sea battle FC
    When Player rolls die with 3 skulls and 5 golds

    Then Player dies with 3 skulls and sees "Dead with 3 skulls. End of the turn."
    Then Player sees "Battle lost, -500 pts"
    Then Player scores 500 and closes connection
  
  @ Row 111
  Scenario: FC 4 swords, die on first roll   => lose 1000 points
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player has 1500 points
    When Player draws 4 swords sea battle FC
    When Player rolls die with 3 skulls and 5 golds

    Then Player dies with 3 skulls and sees "Dead with 3 skulls. End of the turn."
    Then Player sees "Battle lost, -1000 pts"
    Then Player scores 500 and closes connection
  
  @ Row 112
  Scenario: show deduction received cannot make your score negative
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player has 500 points
    When Player draws 4 swords sea battle FC
    When Player rolls die with 3 skulls and 5 golds

    Then Player dies with 3 skulls and sees "Dead with 3 skulls. End of the turn."
    Then Player sees "Battle lost, -1000 pts"
    Then Player scores 0 and closes connection
  
  @ Row 113
  Scenario: FC 2 swords, roll 3 monkeys 2 swords, 1 coin, 2 parrots  SC = 100 + 100 + 300 = 500
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws 2 swords sea battle FC
    When Player rolls die with 3 monkeys, 2 swords, 1 coin and 2 parrots
    When Player sees action menu and enters menu option 3

    Then Player sees "Battle win, +300 pts"
    Then Player scores 500 and closes connection
  
  @ Row 114
  Scenario: FC 2 swords, roll 4 monkeys 1 sword, 1 skull  2 parrots
  then reroll 2 parrots and get 1 sword and 1 skull   SC = 200 +  300 = 500
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws 2 swords sea battle FC
    When Player rolls die with 4 monkeys, 1 swords, 1 skull and 2 parrots
    When Player sees action menu and enters menu option 1
    When Player holds 4 monkeys and 1 sword and rerolls 1 sword and 1 skull
    When Player sees action menu and enters menu option 3

    Then Player sees "Battle win, +300 pts"
    Then Player scores 500 and closes connection
  
  @ Row 116
  Scenario: FC 3 swords, roll 3 monkeys 4 swords  SC = 100 + 200 + 500 = 800
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws 3 swords sea battle FC
    When Player rolls die with 3 monkeys, 4 swords and 1 parrot
    When Player sees action menu and enters menu option 3

    Then Player sees "Battle win, +500 pts"
    Then Player scores 800 and closes connection
  
  @ Row 117
  Scenario: FC 3 swords, roll 4 monkeys 2 swords 2 skulls
  then reroll 4 monkeys and get  2 skulls and 2 swords   -> DIE
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player has 1000 points
    When Player draws 3 swords sea battle FC
    When Player rolls die with 4 monkeys, 2 swords and 2 skulls
    When Player sees action menu and enters menu option 1
    When Player holds 2 swords and rerolls 2 skulls and 2 swords

    Then Player sees "Battle lost, -500 pts"
    Then Player scores 500 and closes connection
  
  @ Row 119
  Scenario: FC 4 swords, roll 3 monkeys 4 swords 1 skull  SC = 100 +200 + 1000 = 1300
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws 4 swords sea battle FC
    When Player rolls die with 3 monkeys, 4 swords and 1 skull
    When Player sees action menu and enters menu option 3

    Then Player sees "Battle win, +1000 pts"
    Then Player scores 1300 and closes connection
  
  @ Row 120
  Scenario: FC 4 swords, roll 3 monkeys, 1 sword, 1 skull, 1 diamond, 2 parrots
  then reroll 2 parrots and get 2 swords thus you have 3 monkeys, 3 swords, 1 diamond, 1 skull
  then reroll 3 monkeys and get  1 sword and 2 parrots  SC = 200 + 100 + 1000 = 1300
    Given The game is in progress
    When Player with name "Kim" plays the game
    When Player draws 4 swords sea battle FC
    When Player rolls die with 3 monkeys, 1 sword, 1 skull, 1 diamond and 2 parrots
    When Player sees action menu and enters menu option 1
    When Player holds 3 monkeys, 1 sword and 1 diamond and rerolls 2 swords
    When Player sees action menu and enters menu option 1
    When Player holds 3 swords, 1 diamond and rerolls 1 sword and 2 parrots
    When Player sees action menu and enters menu option 3

    Then Player sees "Battle Win, +1000 pts"
    Then Player scores 1300 and closes connection
