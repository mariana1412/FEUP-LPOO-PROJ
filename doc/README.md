
# lpoo_T7G76 - SKY JUMP

The game consists of an adaptation of SKY JUMP from Pou. The goal is to reach as high as possible, jumping from platform to platform. 

Along the way, the player can collect rewards (coins) to increase the score. There are also enemies that go from side to side, whose contact results in the reduction of one in the player's health.

The game ends with the player's arrival at the highest reachable level.

> This project was developed by: Mariana Truta (up201806543@fe.up.pt) and Rita Peixoto (up201806257@fe.up.pt) for the LPOO course in 2019/2020.

![Game Demo](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/SkyJump.gif)
 

## Implemented features
* **Jumping** - the player will jump up if W key is pressed, it will jump left if the combination of W key is followed by A key and it will jump right if the combination of W key followed D key is pressed.
* **Catching coins** - if the player goes to the position of a coin, this one is going to be collected, adding 5 points to the score.
* As the time goes by, the **screen** is scrolling down, obligating the player to always go up before hitting the ground, which will cause game over.
* The first jump movement of the player is free. This means before actually reaching the first platform, the player never dies even if it misses the platform jumping or hits any limits of the game.
* **Score** - the score is added with time (1 point per second, plus 5 points for each coin collected).	
* Whenever the player hits the ground, a **game over** message is shown with its final score.
* In the beggining of the game, the player is given 3 lives ( displayed in **health** on the screen).
* Everytime the player collides with a enemy one life is lost, which means there is a reduction of one in the player's health. 
* When the player's health reaches zero, the player loses and a game over message is shown with its final score.
* As time goes by, the screen starts to scroll down faster, in order to increase the difficulty of the game and this speed will increase from time to time reaching the end at it's highest speed.
* A **menu** is also implemented where the user can either start the game, check the game instructions or leave the program.
* After the game over message the game goes back to the menu after the user presses enter,  giving the user the possibility to play again.
* At this point, the player wins when he has jumped 600 platforms and it is at the highest possible level. When this happens a win message is shown with the final score.


[Menu](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/menu.png)

[Instructions](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/instructions.png)

[GameOver](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/gameOver.png)

[Win](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/win.PNG)

## Planned Features
The plan was to create enemies on the platforms and if the player touched them, it would die. However, since the platforms are so narrow, it would limit the player's movement.
It was also planned to change the scenario from time to time and perharps, from a certain level, the platform would fall once the player jumped off of them, but this wasn't implemented since Lanterna doesn't really provide many options in case of scenario ideas.

## Design
####  Problem in context
Right from the start of the design, it was decided to use this architectural pattern. It was implemented  in order to keep the view, model and controller independent of each others, separating the responsibilities in different modules, working towards a good code design and organization.

####  Pattern
The pattern used was **Model-view-controller** (also known as MVC). This pattern divides the code in three parts :

* Model - only represents the data;

* View - displays the model data, and sends user actions to the controller;

* Controller - provides model data to the view, and interprets user actions.

 
####  Implementation
The following diagram shows how the pattern was implemented, showing the role of each class:
![MVC](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/MVC.png)

These classes can be found in the following packages:
* [Model](https://github.com/FEUP-LPOO/lpoo-2020-g76/tree/master/code/src/main/java/g76/Model)
* [View](https://github.com/FEUP-LPOO/lpoo-2020-g76/tree/master/code/src/main/java/g76/View)
* [Controller](https://github.com/FEUP-LPOO/lpoo-2020-g76/tree/master/code/src/main/java/g76/Controller)

####  Consequences

* Easy to apply modifications, because of the separation of responsibilities.

* With the clearer separation of concerns, each part can be better testeed independently.

* High cohesion of the code, enables logical grouping of related actions.

* The navigability of the code becomes more complex, because of the separation of responsibilities.
---
#### Problem in Context
At some point, analysing the code lead to realize that the game loop was too confusing and that the switch case that was being used to implement it was very complex. Also, the game itself behaved differently depending on its current state that changes in runtime.  
#### Pattern
So the pattern used to solve this issue was ***State***. This pattern allows the representation of different states with different subclasses, letting the object alter its behavior when its internal state changes, which means the game can switch to a different state of the application by switching to another implementation. This pattern allowed to address the identified problems because, in the function *[getNextCommand()](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/View/Gui/Gui.java#L38)*, a new action type was created accordingly to what was entered by the user and now a new command is created accordingly to the current state of the game and the user's input. 

#### Implementation
The State pattern sugests the creation of new classes for all possible states, extracting all its specific behavour into to the corresponding class.
The following diagram shows how the pattern was implemented, showing the role of each class:

![State Pattern](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/StatePattern.png)

These classes can be found in the following files:
* [Game](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Game.java)
* [Gui](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/View/Gui/Gui.java)
* [GameState](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/States/GameState.java)
* [StartState](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/States/StartState.java)
* [MenuState](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/States/MenuState.java)
* [InstructionsState](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/States/InstructionsState.java)
* [PlayState](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/States/PlayState.java)
* [WinState](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/States/WinState.java)
* [GameOverState](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/States/GameOverState.java)
* [QuitState](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/States/QuitState.java)

#### Consequences
* Single Responsibility Principle is no longer violated. The code regarding to different states is organized into separate classes.
* Open/Closed Principle - 	new states are introduced without changing existing state classes or the context.
* The code is simplified, more organized and readable. There is no longer a long set of conditional if or switch statements associated with the various states.
---
#### Problem in Context
If only a generic command function was made to deal with all the possibilities of user's actions in the menu as well as in the game, a lot of mixed conditional statements would exist and SRP would be violated. There needed to be a way to clean all this implementation of different commands in one function and make it more organized, clean and independent. 

#### Pattern
So the pattern used to solve this problem was **Command**. This pattern turns a request into a stand-alone object that contains all information about the request. Each class [Command](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/Command.java) will do what's necessary to implement the action in their own [execute()](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/Command.java#L10) function. This pattern allowed to address the identified problems because now the [_Game_](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Game.java) class asks [_GUI_](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/View/Gui/Gui.java) for the command to execute and it executes the answer from [_GUI_](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/View/Gui/Gui.java) without needing  to check a bunch of things before choosing what action to perform, the command will do it itself.

#### Implementation
This pattern suggests that the GUI object just triggers the command, which will handle the details. Therefore, there should be a interface that all the commands implement with a single execute method.
The following diagram shows how the pattern was implemented, showing the role of each class:

![Command Pattern](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/CommandPattern.png)

These classes can be found in the following files:
*  [Game](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Game.java)
* [Gui](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/View/Gui/Gui.java)
* [Command](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/Command.java)
* [DoNothingCommand](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/DoNothingCommand.java)
* [NextOptionCommand](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/NextOptionCommand.java)
* [PreviousOptionCommand](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/PreviousOptionCommand.java)
* [GameOverCommand](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/ChangeStateCommands/GameOverCommand.java)
* [InstructionsCommand](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/ChangeStateCommands/InstructionsCommand.java)
* [MenuCommand](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/ChangeStateCommands/MenuCommand.java)
* [PlayCommand](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/ChangeStateCommands/PlayCommand.java)
* [QuitCommand](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/ChangeStateCommands/QuitCommand.java)
* [WinCommand](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/ChangeStateCommands/WinCommand.java)
* [Action](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/PlayerAction/Action.java)
* [JumpPlayer](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/PlayerAction/JumpPlayer.java)
* [JumpLeftPlayer](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/PlayerAction/JumpLeftPlayer.java)
* [JumpRightPlayer](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/PlayerAction/JumpRightPlayer.java)
* [MoveLeftPlayer](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/PlayerAction/MoveLeftPlayer.java)
* [MoveRightPlayer](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/PlayerAction/MoveRightPlayer.java)

#### Consequences 
* The details of the command execution implementation can be isolated from the code that uses it.
* _Single Responsibility Principle_ -  classes that invoke operations can be decoupled  from classes that perform these operations.
* The implementation of the command execution used inside the object can be swapped in runtime.
* Undo/redo can be implemented.
* _Open/Closed Principle_- new commands can be introduced without having to change what is already done.
* Open/Closed Principal -  new strategies can be introduced without having to change the context.
---
#### Problem in Context 
Everytime there was a change in the program, there needed to be a way so all objects could be notified and updated accordingly to this change, since a lot of this objects are related. This means that, if an object is modified, then all it's dependent objects need to be notified automatically.

#### Pattern
To solve this problem was used the **Observer Pattern**, which allowed all the objects to be notified about the event that happened, at the same time. The change of the player state (position) requires changing other objects as well as the change from one button to the other in the menu implies a few changes itself.

#### Implementation
To implement this pattern there should be an interface with the bare minimum of a update method.
The following diagram shows how the pattern was implemented, showing the role of each class:

![Observer Pattern](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/ObserverPattern.png)

These classes can be found in the following files:
* [Game](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Game.java)
* [GameState](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/States/GameState.java)
* [MenuState](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/States/MenuState.java)
* [PlayState](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/States/PlayState.java)
* [Menu](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Model/Menu.java)
* [Arena](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Model/Arena.java)

#### Consequences 
* Open/Closed Principle - there is the possibility to add more objects to the scene without having to change the code of observers or the interface.
* The objects are notified in a random order.


---
## Known Code Smells and Refactoring Suggestions

Throughout the development of the project, some code smells were found and most of them solved. In order to do so, some refactoring was done, for instance:

### Long Parameter List
* In the private method of *[checkCollisionElement()](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Model/Arena.java#L288)*, that was before _checkCollisionCoin()_ of the *[Arena ](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Model/Arena.java)* class, there used to be five parameters. Analyzing it, four of these parameters could be calculated inside the method if the full *[Coin](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Model/Coin.java)* object was sent as a parameter. At this moment, it makes more sense to have these calculations made inside the method that is actually using them and also making the *[catchedCoin()](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Model/Arena.java#L315)* code more meaningful. After this, the checkCollision function was generalized for elements, since the collision with coins and enemies was done in a similar way.

>[Long Parameter List I ](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/longParameterList_1.PNG)

>[Long Parameter List II ](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/longParameterList_2.PNG)


### Extract Variable
* In the methods *[checkCollison[…]](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Model/Arena.java#L244)* of the *[Arena](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Model/Arena.java)* class, in the conditional statement, there were a lot of expressions that were hard to understand. The solution found was to create separate variables that are self-explanatory to represent the calculations made in this complicated expression, allowing it to be more readable and understandable.

>[Extract Variable](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/extractVariable1.PNG)
	

### Parameterized Method
*  Due to the fact that there was a need to update the platforms, the coins and the enemy as the screen is scrolling, separate functions were made for each one of them. After analyzing the code, they both seem to do the exact same operations just using different objects. Therefore, since both these classes extend the *[Element](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Model/Element.java)* class, a more generic method was created to do the common part [updateElements(...)](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Model/Arena.java#L358).

### Replace Parameter with Explicit Methods
* In regards to the same case, inside this more generic method, the removal of the element is made according to what object type it is calling the corresponding method. Resolving a code smell led to creating a new one. Nevertheless, the other one represented code duplication, which is a more unfavorable code smell than an if statement to apply the matching remove method

> [Parameterized Method and Replace Parameter with Explicit Method](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/parameterizedMethod_replaceParameter.PNG)

### Lazy Class
* When the user doesn't press any key or the key pressed isn't one used in the state the program is at the moment, the *[getNextCommand()](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/View/Gui/Gui.java#L38)* method returns a *[DoNothingCommand](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/src/main/java/g76/Controller/Commands/DoNothingCommand.java)* that has no responsibility. At this time, no solution for this code smell was found that made our code more organized or readable.
>[Lazy Class](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/lazyClass.PNG)


A big part of the refactoring that was done was in the implementation of the design pattern refered in the previous section. Some more refactoring was done, however, mainly were cases similar to the ones presented. 

## Testing Results
All the non-trivial code was tested. The tests implemented cover a high number of lines of code and a reasonable number of mutators. All that was left not tested was because either it wasn't discovered how to or told by the regent not do so, for example functions using random or time, getters, setters, etc.
![Tests results](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/testCoverage.PNG) 
![Pitest coverage](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/doc/screenshots/pitestCoverage.png)
> **Complete test coverage and mutation results**  can be found  [here](https://github.com/FEUP-LPOO/lpoo-2020-g76/blob/master/code/build/reports/pitest/202005291043/index.html)

## Self-Evaluation

This project was entirely developed as a pair. A small amount of work was done separately, being mainly made together. Even though the situation we are in, calls sharing the screen were made most of the time, allowing us to develop it together.
The amount of time dedicated to this project was around 70 hours. 
 * Mariana Truta : 50%

 * Rita Peixoto  : 50%
