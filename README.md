Hello, this is an explanation on the Pixie Evolution Simulator.
This is my third Version of an Evolution Simulator, improved with the experience  gained from the other ones.
I will first talk about the evolution simulator, then talk about the differences  to my other ones and give an outlook of what I am planning for the future.
Each of the colored squares represents an individual creature. I call them Pixies as they are just a bunch of coloured pixels.
The Green Squares are Plants, they serve as a food source for the Pixies. If a Pixies gets killed, it leaves Red Squares. These represent Meat that can be eaten as well.
Each of the creatures have an individual neural network. 
Now what makes this an evolution simulator ? 
The prerequisites needed for Evolution are Reproduction, Mutation and Competition.
By spending a high amount of food, these Pixies can split themselve to reproduce.
Each time there is a chance of a random mutation. 
Each mutation changes the color a bit, so similar looking creatures a closely related.
The mutations can change the size, the amount of spikes they have, the structure of the brain as well as what kind of Senses they own. 
Competition is quite self explanatory. The amount of food plants is limited and they can kill each other. 
This in theory should lead to an evolving set of creatures similar like animals evolve in the real world. 
To understand the simulation I will now go into more detail:
Each time there a no creatures left I will spawn in a set amount of them
If they manage to survive, the simulation continues and they reproduce and evolve.
To keep the environmental pressure high I decrease the amount of food gained by each plant proportionally to the amount of creatures that are currently alive, meaning that the better a species gets in surviving and reproducing the deadlier the environment  will become.
Plants spawn at a constant rate till they reach a specified threshold.
The Senses of Creatures can include a touch sense, so they get an input if they are next to a plant, meat or a creature.  Additionally they sense how different the creature's color is to discern if they are mates or enemies.
They can see in straight lines and can evolve clocks that give a signal each n ticks, to have a feeling of time.
Each of the creatures start with the input sense of how healthy they are, how much food they have and how far they are away from the border of the map.

The output of the neural network decides multiple choices. 
First of all, the movement. They can choose between standing still, moving in a direction or moving in circles of different radii.

The Second decision is what bonus action they want to take. They can choose between different options like reproducing, healing themself or doing nothing.

Third one is the interaction they choose if they move into another creature. They can chose to hurt the creature, just ignore it and stand still or even feed it and exchange information with it.

The amount of food that they consume is proportional to their size, the amount of spikes they have, the size of their brain and senses and what kind of actions they perform, for example moving takes more energy than standing still.

Now to the differences to my other evolution simulator.
My old simulations were written in C# and used the Unity Engine. This time I decided against the Unity engine as there is just too much overhead that I don't need for the simulation.
Instead I wrote it in Java using Swing for visualization.
I directly used a hashmap this time that uses the RGB Value as key to link to the Pixie Class Object. This made it easy to use a buffered Image as representation of the simulation. 
In my old Simulations I programmed the Neural Network Framework myself. As I am quite sure that other people can write way better optimized code I decided to use a machine learning library this time.
I chose the popular Encog Library for that as its easy to implement.
This meant that I couldn't use the NeuroEvolution of Augmenting Topology (NEAT) as it did not support it in a way that worked with my simulation. I instead opted for a a classic feedforward neural network with fixed layer size to increase the performance gain that they allow through being calculatable by matrix multiplication.

This directly leads to my future plans with the simulations. I still think that the Neat System leads to better and faster evolutionary behavior. 
Writing a fast NEAT Framework will be my next goal as I wasn't happy with the performance of my last NEAT Framework that I wrote. 

If you want to improve or play around with my simulation you will find the repository on my github creamsodacodes. 

I am always looking forward to improvements as it will increase the chances of interesting behaviors to evolve in reasonable time.



