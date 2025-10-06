
# Junit test
## Rift

RegionCommands (worldedit-core/src/main/java/com/sk89q/worldedit/command/RegionCommands.java): Created a new method called rift responsible for creating the rift.
EditSession (worldedit-core/src/main/java/com/sk89q/worldedit/EditSession.java): Created a new method called drawValley that draws the valley between two points.

### //rift (thickness) (height) (wave_amp) (wave_freq) (block_mat) (-ud)

**thickness:** The thickness of the rift. The default value is “2”. The thickness must be greater than or equal to “0”. <br/>
**height:** The height of the rift. The default value is “25”. The height must be greater than or equal to “0”.<br/>
**wave_amp:** The wave amplitude of the rift. Creates a wave with the amplitude (maximum height of the wave) defined in the rift drawing. The default value is “5”. <br/>
**wave_freq:** The wave frequency of the rift. Creates a wave with the frequency (number of oscillations) defined in the rift drawing. The default value is “1”. <br/>
**block_mat:** The pattern of blocks to place. The default pattern is “air”. <br/>
**-ud:** Create upward rift (u) and a downward rift (d). Both can be selected simultaneously or just one. The default is “ud”. <br/>



# Default Rift
| Precondition                              | Test Case id     | Step | Description           | ExpectedResult                                                                          |
|-------------------------------------------|------------------|------|-----------------------|-----------------------------------------------------------------------------------------|
| Having the game installed <br/> Logged in | Default rift ok  | 1    | Type 't'              | The console opens.                                                                      |
|                                           |                  | 2    | Type //wand           | Selects a default wand that is used to select an imaginary region.                      |
|                                           |                  | 3    | Right-click the mouse | Selects the first position, which corresponds to the beginning of the rift to be drawn. |
|                                           |                  | 4    | Left-click the mouse  | Selects the second position, which corresponds to the end of the rift to be drawn.      |
|                                           |                  | 5    | Type //rift           | The rift is created with the default values.                                            |



# Rift with custom values
| Precondition                              | Test Case id         | Step | Description           | ExpectedResult                                                                                     |
|-------------------------------------------|----------------------|------|-----------------------|----------------------------------------------------------------------------------------------------|
| Having the game installed <br/> Logged in | Rift custom value ok | 1    | Type 't'              | The console opens.                                                                                 |
|                                           |                      | 2    | Type //wand           | Selects a default wand that is used to select an imaginary region.                                 |
|                                           |                      | 3    | Right-click the mouse | Selects the first position, which corresponds to the beginning of the rift to be drawn.            |
|                                           |                      | 4    | Left-click the mouse  | Selects the second position, which corresponds to the end of the rift to be drawn.                 |
|                                           |                      | 5    | Type //rift 4 50 2 3  | The rift is created with the given values of thickness, height, wave amplitude and wave frequency. |


# Downward rift with custom values and custom block
| Precondition                              | Test Case ID         | Step | Description                   | ExpectedResult                                                                                                                      |
|-------------------------------------------|----------------------|------|-------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| Having the game installed <br/> Logged in | Down rift custom ok | 1    | Type 't'                      | The console opens.                                                                                                                  |
|                                           |                      | 2    | Type //wand                   | Selects a default wand that is used to select an imaginary region.                                                                  |
|                                           |                      | 3    | Right-click the mouse         | Selects the first position, which corresponds to the beginning of the rift to be drawn.                                             |
|                                           |                      | 4    | Left-click the mouse          | Selects the second position, which corresponds to the end of the rift to be drawn.                                                  |
|                                           |                      | 5    | Type //rift 4 50 2 3 water -d | The rift is created with the given values of thickness, height, wave amplitude and wave frequency, and is filled with water blocks. |


# Upward rift with custom values and block

| Precondition                              | Test Case ID      | Step | Description                 | ExpectedResult                                                                                                                                    |
|-------------------------------------------|-------------------|------|-----------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| Having the game installed <br/> Logged in | Up rift custom ok | 1    | Type 't'                    | The console opens.                                                                                                                                |
|                                           |                   | 2    | Type //wand                 | Selects a default wand that is used to select an imaginary region.                                                                                |
|                                           |                   | 3    | Right-click the mouse       | Selects the first position, which corresponds to the beginning of the rift to be drawn.                                                           |
|                                           |                   | 4    | Left-click the mouse        | Selects the second position, which corresponds to the end of the rift to be drawn.                                                                |
|                                           |                   | 5    | Type //rift 4 50 2 3 ice -u | The upward rift is created with the given values of thickness, height, wave amplitude and wave frequency and in ice blocks, creating an ice wall. |


# Upward and downward rift with custom values

| Precondition                              | Test Case ID           | Step | Description                 | ExpectedResult                                                                                                                                                                                                                    |
|-------------------------------------------|------------------------|------|-----------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Having the game installed <br/> Logged in | Up Down rift custom ok | 1    | Type 't'                    | The console opens.                                                                                                                                                                                                                |
|                                           |                        | 2    | Type //wand                 | Selects a default wand that is used to select an imaginary region.                                                                                                                                                                |
|                                           |                        | 3    | Right-click the mouse       | Selects the first position, which corresponds to the beginning of the rift to be drawn.                                                                                                                                           |
|                                           |                        | 4    | Left-click the mouse        | Selects the second position, which corresponds to the end of the rift to be drawn.                                                                                                                                                |
|                                           |                        | 5    | Type //rift 4 50 2 3 ice    | The upward rift is created with the given values of thickness, height, wave amplitude and wave frequency and in ice blocks, creating an ice structure that forms a wall of ice above the ground and an ice rift below the ground. |
