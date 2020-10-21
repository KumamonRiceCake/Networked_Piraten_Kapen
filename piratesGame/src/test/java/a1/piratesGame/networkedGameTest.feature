# Level 2
Feature: Networked game test
  Game should be able to run with 3 players in network

  # Row 125
  Scenario: game starts, each player plays a turn with scores being updated correctly for each player
  Given the game has been started
  When Round starts
  When Player 1 sees score board
	When Player 1 sees die roll and fortune card
	When Player 1 sees menu
	When Player 1 enters menu option 3
	When Player 1 sees scoring message
	When Player 1 finishes turn
	When Player 2 sees score board
	When Player 2 sees die roll and fortune card
	When Player 2 sees menu
	When Player 2 enters menu option 3
	When Player 2 sees scoring message
	When Player 2 finishes turn
	When Player 3 sees score board
	When Player 3 sees die roll
	When Player 3 sees menu
	When Player 3 enters menu option 3
	When Player 3 sees scoring message
  When Player 3 finishes turn
  Then Game goes to the next round
	Then Player 1 sees score updated board
