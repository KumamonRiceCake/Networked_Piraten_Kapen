# Level 2
Feature: Networked game test - whole game
  Game should be able to run with 3 players in network

  # Row 127
  Scenario: game starts, players play turns until a winner is declared (first to 6000 points wins immediately)
  Given the game has been started on port 49100.
  When Round starts.
  When Player 1 sees score board of round 1
	When Player 1 rolls |parrot|parrot|gold|diamond|skull|gold|sword|sword| and gets "gold" fortune card
	When Player 1 sees menu.
	When Player 1 enters menu option 1.
	When Player 1 enters "2,3,5" to hold and rerolls |skull|parrot|gold|diamond|skull|gold|sword|skull| and die with three skulls
	When Player 1 sends score data to server and finishes turn.
	When Player 2 sees score board of round 1
	When Player 2 rolls |gold|parrot|parrot|diamond|monkey|gold|sword|sword| and gets "captain" fortune card
	When Player 2 sees menu.
	When Player 2 enters menu option 1.
  When Player 2 enters "0,5" to hold and rerolls |gold|gold|gold|gold|skull|gold|gold|gold|
  When Player 2 sees menu.
	When Player 2 enters menu option 3.
	When Player 2 sees scoring message and scores 5400 points
	When Player 2 sends score data to server and finishes turn.
	When Player 3 sees score board of round 1
	When Player 3 rolls |skull|skull|parrot|diamond|monkey|skull|sword|sword| and gets "monkey business" fortune card and die with three skulls
	When Player 3 sends score data to server and finishes turn.
	When Player 1 sees score board of round 2
	When Player 1 rolls |sword|parrot|parrot|diamond|monkey|gold|skull|sword| and gets "2 sabre sea battle" fortune card
	When Player 1 sees menu.
	When Player 1 enters menu option 3.
	When Player 1 sees scoring message and scores 500 points
	When Player 1 sends score data to server and finishes turn.
	When Player 2 sees score board of round 2
	When Player 2 rolls |diamond|parrot|parrot|diamond|monkey|gold|diamond|monkey| and gets "4 sabre sea battle" fortune card
	When Player 2 sees menu.
	When Player 2 enters menu option 2.
  When Player 2 rerolls |skull|parrot|skull|sword|skull|sword|monkey|diamond| and die with three skulls
	When Player 2 sees "Battle lost, -1000 pts" message and scores 4400 points
	When Player 2 sends score data to server and finishes turn.
	When Player 3 sees score board of round 2
	When Player 3 rolls |monkey|parrot|parrot|monkey|sword|gold|sword|sword| and gets "captain" fortune card
	When Player 3 sees menu.
	When Player 3 enters menu option 1.
  When Player 3 enters "4,6,7" to hold and rerolls |sword|sword|sword|sword|sword|sword|sword|sword|
  When Player 3 sees menu.
	When Player 3 enters menu option 3.
	When Player 3 sees scoring message and scores 9000 points
	When Player 3 sends score data to server and finishes turn.
	When Game is over with a winner
	Then Player 1 sees winner message
	Then Player 2 sees winner message
	Then Player 3 sees winner message
