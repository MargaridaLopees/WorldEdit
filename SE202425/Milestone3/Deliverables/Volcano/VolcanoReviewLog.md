# Volcano review

## by Afonso Sousa 65263 & Margarida Lopes 64557

### Code review

**randomBlock** in `WorldEditPrivate\worldedit-core\src\main\java\com\sk89q\worldedit\EditSession.java` <br>
The method is simple and functional, with good reuse to avoid duplication of block randomization logic.<br>
We would only suggest that the frequency be checked before performing the operation, as there is a risk of resulting in a division by zero.

**outsideConeBlock** in `WorldEditPrivate\worldedit-core\src\main\java\com\sk89q\worldedit\EditSession.java` <br>
It is clear and simple. Nothing to point out.

**volcanoNonEruptionBlock** in `WorldEditPrivate\worldedit-core\src\main\java\com\sk89q\worldedit\EditSession.java` <br>
It is well-structured and well-divided, making it quite easy to understand the purpose of this method. <br>
We suggest that, to avoid unnecessary code readings, instead of using just if, use else if.

**insideVolcanoConduit** in `WorldEditPrivate\worldedit-core\src\main\java\com\sk89q\worldedit\EditSession.java` <br>
The method implements clear and efficient logic. Nothing to add.

**getAffected** in `WorldEditPrivate\worldedit-core\src\main\java\com\sk89q\worldedit\EditSession.java` <br>
The method is well-organized and easy to understand. We have nothing to add.

**makeVolcano** in `WorldEditPrivate\worldedit-core\src\main\java\com\sk89q\worldedit\EditSession.java` <br>
Although the method is a bit long, it is well-structured, clear, and organized.

**volcano** in `WorldEditPrivate\worldedit-core\src\main\java\com\sk89q\worldedit\command\GenerationCommands.java` <br>
This method is well-organized and concise. We have nothing to add.

**checkvolcanoMaxRadius** in `Worldedit-core\src\main\java\com\sk89q\worldedit\WorldEdit.java` <br>
It is a clean method that performs its function quite well.
<br>
<br>
Overall, the code is well-structured, well-divided, and the purpose of each method is clear. 
All methods are also well-commented and the names of each method were well-chosen.


### Use Case and use case diagram Review
The use case is clear and well-written. It is easy to understand the purpose of the command, how to use it, and the expected outcome.
The diagram is simple and well-structured.

### Class Diagram Review
The class diagram appears correct and consistent with the code. Nothing to add.

### Sequence Diagram Review
The diagram is well-organized and it is quite easy to understand the flow of the code.

### Final Review
The final result is very good! The volcano looks quite realistic and matches what we expected. We really liked having the option to choose the version of the volcano we want, whether it's explosive with lava flowing or a calmer volcano.

Overall, the final result was very well done. We have nothing to point out!
