To run the program, make sure you run the AutoDrivingConsole.java file. It will display the input/output as per the requirements in the console.

The menu state transition is as follows (used state design pattern for this):
1. Start State : prints the welcome message
2. Rectangular Field Setup State : need to input the width and height of the field
3. Field Option State : to choose either 1. add a car or 2. run simulation
4. (If 1 is choosen in 3rd step) Add Car State: add the car by inputting its name, initial position and commands
5. Go back to step 3 after Add Car State request is executed
6. (If 2 is choosen in 3rd step) Run Simulation State: run simulation for all added cars in the field
7. Post Simulation State: to choose either 1. start over or 2. end
8. (If 1 is choosen in 7th step) Go back to step 1
9. (If 2 is choosen in 7th Step) Print a goodbye message and End the program.

   
