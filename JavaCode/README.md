# JavaCode

This directory contains a java program designed to model Turing Patterns using cellular automata.

In order to run, you need to have java installed on your machine!

## Running the Program

### Running without parameters

1. Compile Simulation.java

``` 
javac Simulation.java
```

2. Execute the Simulation program

```
java Simulation
```

### Running with parameters


1. Compile Simulation.java

``` 
javac Simulation.java
```

2. Execute the Simulation program 

BZ Reaction

```
java Simulation [alpha] [beta] [gamma]
```

Activator-Inhibitor Model

```
java Simulation [gamma]
```



# Models

At this moment, the program can only simulate the BZ reaction and the activator-inhibitor model. 

# Adding Models

The addition of new models can be done easily thanks to the state and template method design patterns. 

![uml](https://github.com/hpache/Turing-Patterns/blob/main/UML_Diagrams/UML_Turing_2-0.png)

Adding a new model requires you to add two new java programs. The first program needs to be added to the Cells package as a child of the Cell class.

For example, if you want to add a new model with the name of RD, then you would need to make an RD class extending the Cell class. Afterwards define a constructor(s), methods that handle the update of each cell (updateState), a draw method, and any auxiliary methods you may need to handle calculations. 

The next java program will need to be added to the Models package. All you need to do is define a new class that implements the Model interface. This package handles the generation of grids for a specific model.

Finally, update the Simulation.java file so that you can call on your new model through the command line.

# References


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 TRANSITIONAL//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="htmlExportStyleSheet.css" />

<body>
<div class="content">
<dl>
<dd class="Pub">
	<span class="Title">A Simple Model of the Belousov-Zhabotinsky Reaction from First Principles</span><br />
	<span class="Author">A. Turner</span><br />
	<span class="Journal"></span>&nbsp;
	<span class="Volume"></span>&nbsp;
	<span class="Pages"></span>&nbsp;
	(<span class="Date">2009</span>)<br />
    <span class="URL"><a href="https://discovery.ucl.ac.uk/id/eprint/17241">https://discovery.ucl.ac.uk/id/eprint/17241</a></span><br />
</dd>

</dl>
</div>


<div class="content">
<dl>
<dd class="Pub">
	<span class="Title">Cellular automata for reaction-diffusion systems</span><br />
	<span class="Author">J. R. Weimar</span><br />
	<span class="Journal">Parallel Computing</span>&nbsp;
	<span class="Volume">23</span>&nbsp;
	<span class="Pages">1699-1715</span>&nbsp;
	(<span class="Date">1997</span>)<br />
	<span class="URL"><a href="https://www.sciencedirect.com/science/article/pii/S0167819197000811">https://www.sciencedirect.com/science/article/pii/S0167819197000811</a></span><br />
	<span class="URL"><a href="https://doi.org/10.1016/S0167-8191(97)00081-1">https://doi.org/10.1016/S0167-8191(97)00081-1</a></span><br />
	
</dd>

</dl>
</div>
</body>
</html>
