# Scenario-Based Tests
## Climate Change

BrushCommands (worldedit-core/src/main/java/com/sk89q/worldedit/command/BrushCommands.java): Created a new method called climateChangeBrush, responsible for create the climate change desired.
FreezeBrush (worldedit-core/src/main/java/com/sk89q/worldedit/command/tool/brush/FreezeBrush.java): Implementation of cold mode features.
DesertBrush (worldedit-core/src/main/java/com/sk89q/worldedit/command/tool/brush/DesertBrush.java): Implementation of dry mode features.

### //brush climatechange (radius) (climate_type) (intensity)

**radius:** The radius of the brush effect. Must be within server's configured maxBrushRadius.<br/>
**climate_type:** The type of climate change effect. Valid options are "hot", "cold", "dry", and "wet".<br/>
**intensity:** The intensity of the effect. Must be between 1 and 3 inclusive. Different intensities has different effects.<br/>

# Hot Mode - Basic Fire Effect
| Precondition                                                                 | Test Case ID   | Step | Description                          | ExpectedResult                                        |
|------------------------------------------------------------------------------|----------------|------|--------------------------------------|-------------------------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Hot Mode Lvl 1 | 1    | Type //brush climatechange 5 hot 1   | Command is accepted                                   |
|                                                                              |                | 2    | Right-click the mouse to apply brush | Fire blocks are placed in radius 5 area               |
|                                                                              |                | 3    | Check weather                        | Weather is clear                                      |
|                                                                              |                | 4    | Check affected area                  | Only fire blocks are placed, no lava or other effects |

# Hot Mode - Lava Effect
| Precondition                                                                 | Test Case ID   | Step | Description                        | ExpectedResult                                 |
|------------------------------------------------------------------------------|----------------|------|------------------------------------|------------------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Hot Mode Lvl 2 | 1    | Type //brush climatechange 5 hot 2 | Command is accepted                            |
|                                                                              |                | 2    | Right-click to apply brush         | Lava blocks are placed in radius 5 area        |
|                                                                              |                | 3    | Check weather                      | Weather is clear                               |
|                                                                              |                | 4    | Check affected area                | Only lava blocks are placed, no delta features |

# Hot Mode - Delta Feature
| Precondition                                                                 | Test Case ID   | Step | Description                        | ExpectedResult                                                 |
|------------------------------------------------------------------------------|----------------|------|------------------------------------|----------------------------------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Hot Mode Lvl 3 | 1    | Type //brush climatechange 6 hot 3 | Command is accepted                                            |
|                                                                              |                | 2    | Right-click to apply brush         | Delta features are generated in radius 6 area                  |
|                                                                              |                | 3    | Check weather                      | Weather is clear                                               |
|                                                                              |                | 4    | Check affected area                | Delta features are properly placed with appropriate generation |

# Cold Mode - Basic Snow
| Precondition                                                                 | Test Case ID    | Step | Description                           | ExpectedResult                                     |
|------------------------------------------------------------------------------|-----------------|------|---------------------------------------|----------------------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Cold Mode Lvl 1 | 1    | Type //brush climatechange 5 cold 1   | Command is accepted                                |
|                                                                              |                 | 2    | Right-click to apply brush            | Snow blocks and layers are placed in radius 5 area |
|                                                                              |                 | 3    | Check weather                         | Weather is clear                                   |
|                                                                              |                 | 4    | Check affected area                   | Only snow blocks and layers present, no ice spikes |

# Cold Mode - Ice Spikes Low
| Precondition                                                                 | Test Case ID    | Step | Description                          | ExpectedResult                                             |
|------------------------------------------------------------------------------|-----------------|------|--------------------------------------|------------------------------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Cold Mode Lvl 2 | 1    | Type //brush climatechange 6 cold 2  | Command is accepted                                        |
|                                                                              |                 | 2    | Right-click to apply brush           | Snow and ice spike features are generated in radius 6 area |
|                                                                              |                 | 3    | Check weather                        | Weather is clear                                           |
|                                                                              |                 | 4    | Check affected area                  | Snow blocks, layers and low density ice spikes             |

# Cold Mode - Ice Spikes High
| Precondition                                                                 | Test Case ID    | Step | Description                          | ExpectedResult                                                   |
|------------------------------------------------------------------------------|-----------------|------|--------------------------------------|------------------------------------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Cold Mode Lvl 3 | 1    | Type //brush climatechange 5 cold 3  | Command is accepted                                              |
|                                                                              |                 | 2    | Right-click to apply brush           | Snow and dense ice spike features are generated in radius 5 area |
|                                                                              |                 | 3    | Check weather                        | Weather is clear                                                 |
|                                                                              |                 | 4    | Check affected area                  | Snow blocks, layers and high density ice spikes                  |

# Dry Mode - Basic Sand
| Precondition                                                                 | Test Case ID   | Step | Description                         | ExpectedResult                          |
|------------------------------------------------------------------------------|----------------|------|-------------------------------------|-----------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Dry Mode Lvl 1 | 1    | Type //brush climatechange 5 dry 1  | Command is accepted                     |
|                                                                              |                | 2    | Right-click to apply brush          | Sand blocks are placed in radius 5 area |
|                                                                              |                | 3    | Check weather                       | Weather is clear                        |
|                                                                              |                | 4    | Check affected area                 | Only sand blocks present, no vegetation |

# Dry Mode - With Vegetation (cacti and bushes)
| Precondition                                                                 | Test Case ID   | Step | Description                        | ExpectedResult                                                |
|------------------------------------------------------------------------------|----------------|------|------------------------------------|---------------------------------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Dry Mode Lvl 2 | 1    | Type //brush climatechange 6 dry 2 | Command is accepted                                           |
|                                                                              |                | 2    | Right-click to apply brush         | Sand blocks and desert vegetation are placed in radius 6 area |
|                                                                              |                | 3    | Check vegetation                   | Dead bushes and cacti are properly placed in sand area        |

# Dry Mode - With Dunes
| Precondition                                                                 | Test Case ID   | Step | Description                        | ExpectedResult                                                    |
|------------------------------------------------------------------------------|----------------|------|------------------------------------|-------------------------------------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Dry Mode Lvl 3 | 1    | Type //brush climatechange 4 dry 3 | Command is accepted                                               |
|                                                                              |                | 2    | Right-click to apply brush         | Sand dunes, blocks, and vegetation are generated in radius 4 area |
|                                                                              |                | 3    | Check dune formation               | Dunes have proper height and distribution                         |
|                                                                              |                | 4    | Check vegetation                   | Desert vegetation is placed after dune formation                  |

# Wet Mode - Light Rain
| Precondition                                                                 | Test Case ID   | Step | Description                        | ExpectedResult                                 |
|------------------------------------------------------------------------------|----------------|------|------------------------------------|------------------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Wet Mode Lvl 1 | 1    | Type //brush climatechange 5 wet 1 | Command is accepted                            |
|                                                                              |                | 2    | Check weather                      | Weather is set to rain; starts to rain         |
|                                                                              |                | 3    | Right-click to apply brush         | Small water blocks are placed in radius 5 area |
|                                                                              |                | 4    | Verify rain duration               | Rain lasts for 600 ticks (30 seconds)          |

# Wet Mode - Heavy Rain
| Precondition                                                                 | Test Case ID   | Step | Description                        | ExpectedResult                                     |
|------------------------------------------------------------------------------|----------------|------|------------------------------------|----------------------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Wet Mode Lvl 2 | 1    | Type //brush climatechange 4 wet 2 | Command is accepted                                |
|                                                                              |                | 2    | Check weather                      | Weather is set to rain; starts to rain             |
|                                                                              |                | 3    | Right-click to apply brush         | Water blocks are placed in radius 4 area           |
|                                                                              |                | 4    | Verify water coverage              | Higher water block density than with intensity '1' |
# Wet Mode - Storm
| Precondition                                                                 | Test Case ID   | Step | Description                        | ExpectedResult                                 |
|------------------------------------------------------------------------------|----------------|------|------------------------------------|------------------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Wet Mode Lvl 3 | 1    | Type //brush climatechange 6 wet 3 | Command is accepted                            |
|                                                                              |                | 2    | Check weather                      | Weather is set to thunderstorm; starts to rain |
|                                                                              |                | 3    | Right-click to apply brush         | Water blocks are placed in radius 6 area       |
|                                                                              |                | 4    | Verify storm effects               | Thunder and lightning effects are present      |

# Invalid Intensity
| Precondition                                                                 | Test Case ID      | Step | Description                         | ExpectedResult                                   |
|------------------------------------------------------------------------------|-------------------|------|-------------------------------------|--------------------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Invalid Intensity | 1    | Type //brush climatechange 5 hot 4  | Error message about invalid intensity is shown   |
|                                                                              |                   | 2    | Type //brush climatechange 5 cold 0 | Error message about invalid intensity is shown   |
|                                                                              |                   | 3    | Type //brush climatechange 5 dry -1 | Error message about invalid intensity is shown   |

# Invalid Climate Type
| Precondition                                                                 | Test Case ID    | Step | Description                            | ExpectedResult                     |
|------------------------------------------------------------------------------|-----------------|------|----------------------------------------|------------------------------------|
| Having the game installed<br/>Logged in<br/> Player has a brush tool in hand | Invalid Climate | 1    | Type //brush climatechange 5 invalid 1 | No effect is applied               |
|                                                                              |                 | 2    | Type //brush climatechange 5 rain 1    | No effect is applied               |
|                                                                              |                 | 3    | Check area                             | No changes are made to the terrain |