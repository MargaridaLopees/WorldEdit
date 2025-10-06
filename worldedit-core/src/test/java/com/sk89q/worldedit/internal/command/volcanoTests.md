
# Junit test

The method makeVolcano is in the class EditSession, to call the method we need to create EditSession object. 
Due to the excessive object parameters expected by the EditSessionClass we could not have the test done, 
although we have an example of test for the makeVolcano method as comment for the future needs.

# Eruptive Case

| Step | Description                    | ExpectedResult                                                                                                                                        |
|------|--------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1    | Type 't'                       | The console opens.                                                                                                                                    |
| 2    | Type /volcano radius height On | The volcano is created with overflowing Lava. <br/> The last layer should have not only lava but also some random blocks so it can overflow smoothly. |



# Non-eruptive Case
| Step | Description                      | ExpectedResult                                                                                           |
|------|----------------------------------|----------------------------------------------------------------------------------------------------------|
| 1    | type /t                          | the console opens                                                                                        |
| 2    | type /volcano radiusX height off | the volcano is created with lava inside and smoke,<br/>also the pattern outside of the volcano is random |


