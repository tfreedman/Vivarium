Vivarium
========

Note: This project was an assignment for Ryerson’s second Computer Science course. The assignment was to create a vivarium, with various creatures that would interact with one another in the event of a collision. As the purpose of the assignment was to partially show that you understood object oriented programming, many of the classes were required, and do not necessarily reflect the way I would have built it if I had full control.

![Example Run](/screenshot.png)

Usage:
------

~/Vivarium $ javac VivariumViewer.java   
~/Vivarium $ java VivariumViewer  

Details:
--------

Below is the readme file that was submitted at the time of submission, in the spring of 2011:

——-

For my project, rather than creating a vivarium with generic shapes, I opted to recreate a small portion of Kirby's Dream Land, a Game Boy game from 1992. The creatures are all based on those in the game, as is the music and background. While we aren't being marked for things like including music, some of the sound effects are triggered during collisionResponse events, so you'd be missing out if you couldn't hear it.

Since we couldn't use images to create the creatures, I used a technique that allows me to pack sprite data into arrays of rectangles, and then re-compile the data by assembling small portions of the array at a time. Since smaller portions of the array are accessed for each image, I can pack multiple frames of each creature into an array, and, using a variety of methods, generate the other images. For example, Kirby has two images packed into the array, but flipH() in creature allows me to generate the other two images that would be called when Kirby turns around. By procedurally generating each sprite, I also gain the ability to set the color manually. However, procedural rendering for small images like these is incredibly inefficient compared to just packing GIFs or PNGs, so if I could do this with images, I would. Using images would allow me to replace a few hundred lines of code in all the assemble() methods with under 10 lines, so please don't be judgemental when you mark it, I know it sucks.

Here's a video of someone playing the level I re-made. My portion is the very beginning, which you can see from the background.

http://www.youtube.com/watch?v=1aZVdvSSu9k

For each creature, I tried to recreate it faithfully, using the colors present in the game (playing it on a SNES will display it in color, but most YouTube videos are played on a Game Boy, which is black and white). You can manually set each creature's color, but I've set them all by default to the actual colors used in the game, except the squids. The squids will cycle through an array of colors if they collide with anything and don't die, but they'd otherwise be cyan. And yes, they are flying squids, that's intentional. In Kirby, squids can totally fly.

In order to make the background function, I've created a class Block, which creates invisible platforms. The platforms in the image are overlayed with Block objects, and 1x1 block objects are used at the edges of platforms to prevent characters like Waddle Dee from falling off.

Below is a table of the collisionResponse events that are generated when each thing interacts with another. If Kirby hits an enemy, a sound effect will play, and if Kirby hits Gordo, game over music will play, and trigger System.exit(0);. Interactions between enemies do not generate sound effects.

--

Block interacts with everything.
Creature (Default Character) is suicidal. Everything will kill it. Since it's boring and is just a square, I've left it out of the default submission.

Kirby -> Kirby == Bounces off each other  
Kirby -> Gordo == Death  
Kirby -> Squishy == Death to Squishy, flicker Kirby's sprite  
Kirby -> WaddleDee == Death to WaddleDee, flicker Kirby's sprite  

WaddleDee -> WaddleDee == Bounces off each other  
WaddleDee -> Kirby == Death to WaddleDee  
WaddleDee -> Gordo == Death to WaddleDee  
WaddleDee -> Squishy == Flail wildly until Squishy leaves.  

Gordo -> Gordo == Bounces off each other  
Gordo -> Kirby == Death to Kirby  
Gordo -> Squishy == Death to Squishy  
Gordo -> WaddleDee == Death to WaddleDee  

Squishy -> Gordo == Death to Squishy  
Squishy -> Kirby == Death to Squishy  
Squishy -> Squishy == Bounces off each other  
Squishy -> WaddleDee/Block/Walls == Changes Color