# Tool Commands

## **Use Cases Diagram**:

![img_1.png](img_1.png)


## **Use Cases**:


| Use Case: SelectBlocks                                                                                                                                                                                                                                                                    | 
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **ID:** TC1                                                                                                                                                                                                                                                                               |
| **Brief description:** <br/> Select blocks with the tool and use them.                                                                                                                                                                                                                    |
| **Primary Actors:** <br/> Player: Executes the commands.                                                                                                                                                                                                                                  |
| **Secondary Actors:** <br/> None.                                                                                                                                                                                                                                                         |
| **Preconditions:** <br/> 1. The player must have WorldEdit installed. <br/> 2. The player has permission level to use WorldEdit. <br/> 3. The player either has no tool or has to unbind the old one. <br/> 4. The player must be holding an item.                                        |
| **Main flow:** <br/> 1.The use case starts when the player types the `/tool selwand` command in the console. <br/> 2. The player's current item's functionality changes into a selection tool. <br/> 3. The player has now the power to select a set of blocks and use them as he wishes. |
| **Alternative flows:** <br/> None.                                                                                                                                                                                                                                                        |
| **Postconditions:** <br/> None.                                                                                                                                                                                                                                                           |

## **Illustration**:
It can be selected and then copied, for example. It should show something like this:
![img_3.png](img_3.png)



| Use Case: FillBlocks                                                                                                                                                                                                                                                                                   | 
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **ID:** NC2                                                                                                                                                                                                                                                                                            |
| **Brief description:** <br/> Fill a defined space with blocks of a certain type.                                                                                                                                                                                                                       |
| **Primary Actors:** <br/> Player: Executes the commands.                                                                                                                                                                                                                                               |
| **Secondary Actors:** <br/> None.                                                                                                                                                                                                                                                                      |
| **Preconditions:** <br/> 1. The player must have WorldEdit installed. <br/> 2. The player has permission level to use WorldEdit. <br/> 3. The player either has no tool or has to unbind the old one. <br/> 4. The player must be holding an item.                                                     |
| **Main flow:** <br/> 1.The use case starts when the player types the `/floodfill` command in the console. <br/> 2. The player's current item's functionality changes into a flood fill tool. <br/> 3. The player has now the power to create a set of blocks within a range established by the player. |
| **Alternative flows:** <br/> Instead of stopping creating blocks when the limit is reached, it could keep creating in a different direction until it's told to stop.                                                                                                                                   |
| **Postconditions:** <br/> None.                                                                                                                                                                                                                                                                        |

## **Illustration**: 
It was created a whole amount of blocks, created in every direction.

![img.png](img.png)



| Use Case: StackOfBlocks                                                                                                                                                                                                                                                                         | 
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **ID:** NC3                                                                                                                                                                                                                                                                                     |
| **Brief description:** <br/> Add a whole stack of blocks in a selected region.                                                                                                                                                                                                                  |
| **Primary Actors:** <br/> Player: Executes the commands.                                                                                                                                                                                                                                        |
| **Secondary Actors:** <br/> None.                                                                                                                                                                                                                                                               |
| **Preconditions:** <br/> 1. The player must have WorldEdit installed. <br/> 2. The player has permission level to use WorldEdit. <br/> 3. The player either has no tool or has to unbind the old one. <br/> 4. The player must be holding an item.                                              |
| **Main flow:** <br/> 1.The use case starts when the player types the `/stacker` command in the console. <br/> 2. The player's current item's functionality changes into a stacker tool. <br/> 3. The player has now the power to create a line of blocks with a size established by the player. |                                              |
| **Alternative flows:** <br/> None.                                                                                                                                                                                                                                                              |
| **Postconditions:** <br/> None.                                                                                                                                                                                                                                                                 |

## **Illustration**:
It can be useful when building a house, for example.

![img_2.png](img_2.png)



# Chunk Commands


| Use Case: ChunkData                                                                                                                                                        | 
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **ID:** CC1                                                                                                                                                                |
| **Brief description:** <br/> Inform the player about chunk data.                                                                                                           |
| **Primary Actors:** <br/> Player: Executes the commands.                                                                                                                   |
| **Secondary Actors:** <br/> None.                                                                                                                                          |
| **Preconditions:** <br/> 1. The player must have WorldEdit installed. <br/> 2. The player has permission level to use WorldEdit.                                           |
| **Main flow:** <br/> 1.The use case starts when the player types the `/chunkinfo` command in the console. <br/> 2. The chunk's information's are calculated and displayed. |                                              
| **Alternative flows:** <br/> None.                                                                                                                                         |
| **Postconditions:** <br/> None.                                                                                                                                            |

## **Illustration**:
It should show something like this:

![img_4.png](img_4.png)



| Use Case: ListOfChunks                                                                                                                                                          | 
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **ID:** CC2                                                                                                                                                                     |
| **Brief description:** <br/> List selected chunks.                                                                                                                              |
| **Primary Actors:** <br/> Player: Executes the commands.                                                                                                                        |
| **Secondary Actors:** <br/> None.                                                                                                                                               |
| **Preconditions:** <br/> 1. The player must have WorldEdit installed. <br/> 2. The player must have selected chunks. <br/> 3. The player has permission level to use WorldEdit. |
| **Main flow:** <br/> 1.The use case starts when the player types the `/listchunks` command in the console. <br/> 2. The chunks are listed.                                      |                                              
| **Alternative flows:** <br/> None.                                                                                                                                              |
| **Postconditions:** <br/> None.                                                                                                                                                 |

## **Illustration**:
It should show something like this:

![img_8.png](img_8.png)


# Errors/success examples of the first use case: 

## When the arguments are less than the expected:

![img_5.png](img_5.png)

## When the player tries to bind a tool and does not have an item:

![img_6.png](img_6.png)

## In case of success:

![img_7.png](img_7.png)