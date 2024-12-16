To run the program, make sure you run the AutoDrivingConsole.java file. It will display the input/output as per the requirements in the console.

The menu state transition is as follows (used state design pattern for this):
1. Start State : prints the welcome message
2. Rectangular Field Setup State : need to input the width and height of the field
3. Field Option State : to choose either 1. add a car or 2. run simulation
4. (If 1 is selected in 3rd step) Add Car State: add the car by inputting its name, initial position and commands
5. Go back to step 3 after Add Car State request is executed
6. (If 2 is selected in 3rd step) Run Simulation State: run simulation for all added cars in the field
7. Post Simulation State: to choose either 1. start over or 2. end
8. (If 1 is selected in 7th step) Go back to step 1
9. (If 2 is selected in 7th Step) Print a goodbye message and End the program.

Validations:
When invalid it will prompt the same question to user again

When user selects to add a car
1. First a check will be done to see if there is enough spaces on the field based on its area.
   E.g width is 2 and height is 2. Means area is 4, and the field can occupy a max of 4 cars
2. Then user can input their name and it will be validated
   if the name starts or ends with spaces those will be trimed, empty values after that considered invalid
3. Then user inputs the car's initial position
   if the positions x and y are not numbers, negative or outside the field boundary it will be invalid
   if the direction is not N/S/E/W it will be invalid. direction is case insensitive and only the first char of the input will be taken for direction.
   E.g user inputs East, it will be treated as E.
5. User at last enters the commands
   commands are case-insensitive too. if the user inputs any other char beside F, L or R, it will be filtered out. after filtering if no chars then invalid.

when user runs simulation
1. All cars will execute their first command, but in the order of when the car was added. E.g Car A executes their first command, then Car B will execute their first command.
2. The iteration of executing commands will go on till the car with max commands has all their commands executed or all cars ended up colliding.
3. A collision can occur multiple times at the same position plots. E.g Car A and B first collided at step 3, and then Car C collided at the same position plot at step 7.
This is how the console will print the result
   After simulation, the result is:
- A, collides with B at (5,4) at step 3
- B, collides with A at (5,4) at step 3
- C, collides with A,B at (5,4) at step 7
4. When 3 cars collides at the same step, e.g A and B collides first since its executed based on the order it was added then C collides this will how the console will print the result.
     After simulation, the result is:
- A, collides with B at (5,4) at step 3
- B, collides with A at (5,4) at step 3
- C, collides with A,B at (5,4) at step 3
