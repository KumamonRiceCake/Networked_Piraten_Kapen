@ Level 1a
Feature: Basic dying and scoring
  Player should be able to roll die and score - reroll cases

  @ Row 44
  Scenario: get set of 2 monkeys on first roll, get 3rd monkey on 2nd roll (SC 200 since FC is coin)
    Given The game starts
    When "Kim" plays the game
    When Player draws coin from fortune card deck
    When Player rolls die with two monkeys, two parrots, two sword and two skull
    When Player sees action menu and enters option 1
    When Player holds two monkeys and rerolls one monkey, two swords and one parrot
    When Player sees action menu and enters option 3
    Then Player score is 200
    
  @ Row 46
  Scenario: score 2 sets of 3 (monkey, parrots) in RTS using 2 rolls   (SC 300)
    Given The game starts
    When "Kim" plays the game
    When Player draws coin from fortune card deck
    When Player rolls die with two monkeys, two parrots, two sword and two skull
    When Player sees action menu and enters option 1
    When Player holds two monkeys and two parrots and rerolls one monkey and one parrot
    When Player sees action menu and enters option 3
    Then Player score is 300

  @ Row 50
  Scenario: score set of 3 coins+ FC and set of 4 swords correctly over several rolls
  (SC = 200+400+200 = 800)
    Given The game starts
    When "Kim" plays the game
    When Player draws coin from fortune card deck
    When Player rolls die with two coins, two swords, two monkeys and two parrots
    When Player sees action menu and enters option 1
    When Player holds two coins and two swords and rerolls one coin, one sword and two monkeys
    When Player sees action menu and enters option 1
    When Player holds three coins and three swords and rerolls one sword and one skull
    When Player sees action menu and enters option 3
    Then Player score is 800
  
  @ Row 51
  Scenario: score set of 3 coins+ FC and set of 4 swords correctly over several rolls
  with captain fortune card (SC = (100 + + 300 + 200)*2 = 1200)
    Given The game starts
    When "Kim" plays the game
    When Player draws captain from fortune card deck
    When Player rolls die with two coins, two swords, two monkeys and two parrots
    When Player sees action menu and enters option 1
    When Player holds two coins and two swords and rerolls one coin, one sword and two monkeys
    When Player sees action menu and enters option 1
    When Player holds three coins and three swords and rerolls one sword and one skull
    When Player sees action menu and enters option 3
    Then Player score is 1200
  
  @ Row 52
  Scenario: score set of 5 swords over 3 rolls (SC 600)
    Given The game starts
    When "Kim" plays the game
    When Player draws coin from fortune card deck
    When Player rolls die with two coins, two swords, two monkeys and two parrots
    When Player sees action menu and enters option 1
    When Player holds two swords and rerolls two swords, two monkeys and two skulls
    When Player sees action menu and enters option 1
    When Player holds four swords and rerolls one sword and one parrot
    When Player sees action menu and enters option 3
    Then Player score is 600

  @ Row 58
  Scenario: score set of 8 monkeys over several rolls (SC 4600 because of FC is coin and full chest)
    Given The game starts
    When "Kim" plays the game
    When Player draws coin from fortune card deck
    When Player rolls die with two coins, two swords, two monkeys and two parrots
    When Player sees action menu and enters option 1
    When Player holds two monkeys and rerolls three monkeys, two swords and one parrot
    When Player sees action menu and enters option 1
    When Player holds five monkeys and rerolls three monkeys
    When Player sees action menu and enters option 3
    Then Player score is 4600

  @ Row 59
  Scenario: score a set of 2 diamonds over 2 rolls with FC is diamond (SC 400)
    Given The game starts
    When "Kim" plays the game
    When Player draws diamond from fortune card deck
    When Player rolls die with one diamond, two monkeys, two parrots, two swords and one skull
    When Player sees action menu and enters option 1
    When Player holds one diamond and rerolls one diamond, two monkeys, two parrots and one sword
    When Player sees action menu and enters option 3
    Then Player score is 400

  @ Row 60
  Scenario: score a set of 3 diamonds over 2 rolls (SC 500)
    Given The game starts
    When "Kim" plays the game
    When Player draws coin from fortune card deck
    When Player rolls die with one diamond, two monkeys, two parrots, two swords and one skull
    When Player sees action menu and enters option 1
    When Player holds one diamond and rerolls two diamonds, two monkeys, one parrot and one sword
    When Player sees action menu and enters option 3
    Then Player score is 500
  
  @ Row 61
  Scenario: score a set of 3 coins over 2 rolls  (SC 600)
    Given The game starts
    When "Kim" plays the game
    When Player draws coin from fortune card deck
    When Player rolls die with one coin, two monkeys, two parrots, two swords and one skull
    When Player sees action menu and enters option 1
    When Player holds one coin and rerolls two coins, two monkeys, one parrots and one sword
    When Player sees action menu and enters option 3
    Then Player score is 600
  
  @ Row 62
  Scenario: score a set of 3 coins over 2 rolls  with FC is diamond (SC 500)
    Given The game starts
    When "Kim" plays the game
    When Player draws coin from fortune card deck
    When Player rolls die with one coin, two monkeys, two parrots, two swords and one skull
    When Player sees action menu and enters option 1
    When Player holds one coin and rerolls two coins, two monkeys, one parrots and one sword
    When Player sees action menu and enters option 3
    Then Player score is 600

  @ Row 65
  Scenario: get 7 swords on first roll, try to roll the 8 die by itself -> interface reports not allowed
    Given The game starts
    When "Kim" plays the game
    When Player draws coin from fortune card deck
    When Player rolls die with seven swords and one monkey
    When Player sees action menu and enters option 2
    Then Player rerolls all eight die
