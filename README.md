# Baccarat

Baccarat is a card game played in casino. Search for the rule of the game if interested.

This program's main purpose is to simulate a game of Baccarat (8 decks of cards, around 75-85 rounds), display result for each round, 
and tallies result into statistics at the end for further analysis. 

Here is an example of how the output looks like:

Beginning of output | [...] | End of output
------------------- | ----- | -------------
<img src="img/output1.PNG" width="398"> | [...] | <img src="img/output2.PNG" width="398">

Each round of game displays intial score of player's and banker's hand.

If draw/hit condition is met, player/banker draws a card.

Then the program compares the score, annouces the result of that round (PLAYER/BANKER/TIE/PANDA/DRAGON).

<br/>

At the end of the program, statistics on number of rounds, number of banker/player wins, special conditions (TIE/DRAGON/PANDA/PAIRS) 
and their round of occurence are shown for further analysis.

## Additional Features

This is the basic version of program. Additional features can be implemented very fast. Here are some examples:

#### Make another card game simulation!

Since this program is object oriented, the objects for cards are already implemented. I only need to implement rules for another game and 
statistics that the user wants to see. 

#### Simulate 100 more times and show me the stats!

This program simulates 1 game of Baccarat (8 decks of cards) then displays statistics. I can simulate 100 games then display result if the 
user requests.


#### Make it an actual game where I can play each round of game, have chips to lose and win, and display scores!

Instead of letting program deal cards by itself, I can implement user input in between to simulate a real game of Baccarat.

#### GUI when?

I would use Android Studio to make an app. After implementing an actual game for user to play (as mentioned above), the user input would switch to touch 
events and text based display would switch to UI display elements. It will take longer to implement than other features, but it's 
doable. 

## Takeaways from this project

- Learning Github documentation
- Showcase object oriented programming
- User friendly display
- Implementing additional client requests for features (and do it fast due to good modularity)
- Modularize code for better readability and scalability
