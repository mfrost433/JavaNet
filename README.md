# Neural-net-deep-learning-API

This small API provides functionality for producing a basic neural network.
Backpropogation is implemented via Gradient Descent algorithm, and only the sigmoid
/ logistic function is available as an activation function.

This has been tested with a basic function approximation problem, and has achieved
good results with strong convergence. Example function is producing a line
x = y, and asking the Neural Network to categorize points on either side of the line.

## API features
The building blocks for a basic  Artificial Neural Network are implemented
in this Java project. These allow you some degree of freedom in customizing your
Network

### Layer Class
The "Layer" class is the building block of a Neural Network with this API. Each 
layer object is initialized with a set number of nodes and outgoing weights.

#### Output and Input Layer
Class heirarchy describes two children of the "Layer" class, input and output layers.
These layers act slightly differently to its parent class.

### Network Class
The "Network" class encapsulates Layer objects, and allows training of the Layer 
weights, and predictions to be made after training. Propagates inputs through the 
input layer, through intermediate layer, to the output layer. Backpropagates using
gradient descent if in training mode.

### Graphical Classes
Classes such as Point exist to allow testing of a neural network. You can code an
equation to represent on a graph, and use the Neural Network to categorize points
on either side of the line.

## Setup
Easy! Just clone the repo and open the project in your favorite IDE.