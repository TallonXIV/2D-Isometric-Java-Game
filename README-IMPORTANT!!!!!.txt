----------------------------------------INSTRUCTIONS:-------------------------------------------
This game can be started 2 ways. You can either:

a.) Run the GameLauncher class, where you will be immediately put into the game...

----OR-----

b.) Run the OpenLauncherMenu class where there will be a launcher menu for
the game that I made as a way to add some polish and provide some information for
the game and its intention. (In the OpenLauncherMenu window, there is a Start Button that calls the main
method of the GameLauncher class.)

tl;dr:  Either class will put you into the game when run, but OpenLauncherMenu will show
you the menu and some info on the game (*pst* run that one).

-Wes

------------------------------------OBJECTIVE CHECKLIST:--------------------------------------------
[X] A 2D field of play
(Used JPanel and JPane as well as 2D Graphics)

[X] A movable sprite
(Player Character AKA my face)

[X] A circular projectile increasing in radius from point fired
(The enemy projectile, which is circular, not only expands but contracts. The
reason behind the design is because it would eventually take up the whole
screen, given the objective of "survive")

[X] Allow the sprite to wrap around the field of play
(the player character wraps to the opposite site were they to go too far left, right, up, or down)