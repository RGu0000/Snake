"# Snake" 

Description:
Simple Snake game. User can choose the speed of the snake before starting a new game.

Basic functionalities of Java were used in this project (including Swing)



The most important improvement compared to other implementations of Snake game avaiable online, is the additional check while changing the
snake direction. If the delay between next steps is quite big or if the player is fast enough, he can bypass the standard check:


if ((key == KeyEvent.VK_LEFT) && (!rightDirection)){ ... }


If the snake is going right, you can press up/down arrow and then immediately left arrow. If user is fast enough to achieve that before snake makes a move, above condition will not detect any abnormalities, as the Direction has already been changed to up/down, and this will cause the snake turning back in place and eating itself. 
My approach is to have additional condition which "remembers" from what direction snake was coming in previous step.

Screenshots:

<br>
<p align="center">
<img align="center" src=https://user-images.githubusercontent.com/35892799/35527250-6a9c464e-052a-11e8-8c7d-f0993c80f573.JPG height=400>
 </p>
<br>
<p align="center">
<img src=https://user-images.githubusercontent.com/35892799/35527309-a6a3e6ec-052a-11e8-982a-2301bf5c23c3.JPG height=400>
 </p>
<br>
<p align="center">
<img src=https://user-images.githubusercontent.com/35892799/35527326-b48b2e50-052a-11e8-814f-c55dc2fb0479.JPG height=400>
 </p>
