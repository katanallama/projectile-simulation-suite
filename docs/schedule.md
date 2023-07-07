
# Table of Contents

1.  [Schedule <code>[1/4]</code>](#orgafef1a8)
    1.  [Development <code>[2/6]</code>](#org34f6171)
        1.  [Implement the input and output handling](#org454e1d1)
        2.  [Review Itr 1](#org2bdf4c7)
        3.  [Physics simulation Itr 2](#org9350c7e)
        4.  [Review Itr 2](#org0311b6f)
    2.  [Testing <code>[0/3]</code>](#orgecacf7d)
        1.  [Unit Testing](#org07d39b9)
        2.  [Integration Testing](#org82d1ff8)
        3.  [System Testing](#orgdc38708)
    3.  [Docs & Submission <code>[0/3]</code>](#org5ad90dd)
        1.  [Documentation](#org8635d54)
        2.  [Review and Final Adjustments](#orgfaec38f)
        3.  [Final Submission](#org3b41e67)
2.  [Completed](#orgc8cff4f)
    1.  [Set Up Development Environment](#org46b1d4e)
    2.  [Develop Initial Understanding](#org3d00a4f)
        1.  [Problem Spec readme](#org9879e54)
    3.  [Design <code>[0/0]</code>](#org6bfeee5)
        1.  [Initial Design](#org8349596)
        2.  [Define system specifications](#org895f738)
        3.  [Design the software architecture](#org6b66cf6)
    4.  [Development](#org0a6b79f)
        1.  [Misc](#org9fa91b4)
        2.  [Physics simulation Itr 1](#org3d38bef)



<a id="orgafef1a8"></a>

# PROJ Schedule <code>[1/4]</code>


<a id="org34f6171"></a>

## TODO Development <code>[2/6]</code>


<a id="org454e1d1"></a>

### TODO Implement the input and output handling

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-27 Tue&gt;</span></span></p>

-   [X] Develop the logic for users to input the variables for the simulation
    -   [X] json file parsing (gson)
-   [ ] cli arguments (picocli)
-   [ ] Implement the logic for displaying the output of the simulation
    -   Currently prints a tuple(x, y, z) coordinates of the projectile in the vector space


<a id="org2bdf4c7"></a>

### TODO Review Itr 1

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-27 Tue&gt;</span></span></p>

-   [ ] Ensure that all key variables incorporated into the simulation correctly
-   [ ] Ensure that errors in inputs handled gracefully
-   [ ] Align on path forward for next iteration


<a id="org9350c7e"></a>

### TODO Physics simulation Itr 2

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-11 Tue&gt;</span></span></p>


<a id="org0311b6f"></a>

### TODO Review Itr 2

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-13 Thu&gt;</span></span></p>


<a id="orgecacf7d"></a>

## TODO Testing <code>[0/3]</code>


<a id="org07d39b9"></a>

### TODO Unit Testing

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-09 Sun&gt;</span></span></p>

-   [ ] Identify functions for testing
-   [ ] Develop boundary value tests
-   [ ] Develop equivalence class tests
-   [ ] Develop decision table-based tests
-   [ ] Develop path tests
-   [ ] Develop data flow tests
-   [ ] Develop slice tests


<a id="org82d1ff8"></a>

### TODO Integration Testing

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-09 Sun&gt;</span></span></p>

-   [ ] Identify subset of units for integration testing
-   [ ] Develop and execute integration tests


<a id="orgdc38708"></a>

### TODO System Testing

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-09 Sun&gt;</span></span></p>

-   [ ] Identify subset of use cases for system testing
-   [ ] Develop and execute system tests


<a id="org5ad90dd"></a>

## TODO Docs & Submission <code>[0/3]</code>


<a id="org8635d54"></a>

### TODO Documentation

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-20 Thu&gt;</span></span></p>

-   [ ] Create README.md file
-   [ ] Create TESTING.md file
-   [ ] Ensure code is commented


<a id="orgfaec38f"></a>

### TODO Review and Final Adjustments

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-25 Tue&gt;</span></span></p>

-   [ ] Review entire simulator and tests
-   [ ] Make necessary adjustments


<a id="org3b41e67"></a>

### TODO Final Submission

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-08-02 Wed&gt;</span></span></p>


<a id="orgc8cff4f"></a>

# Completed


<a id="org46b1d4e"></a>

## DONE Set Up Development Environment

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-05-23 Tue&gt;</span></span></p>

-   [X] Install necessary software
-   [X] Setup GitHub repository


<a id="org3d00a4f"></a>

## DONE Develop Initial Understanding

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-02 Fri&gt;</span></span></p>

-   [X] Understand the physics involved in the simulation
-   [X] Research on best practices for implementing physics in Java
-   [X] Research on best practices for java testing frameworks


<a id="org9879e54"></a>

### DONE Problem Spec readme

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-02 Fri&gt;</span></span></p>

1.  DONE Objectives

2.  DONE Functions

3.  DONE Requirements


<a id="org6bfeee5"></a>

## DONE Design <code>[0/0]</code>


<a id="org8349596"></a>

### DONE Initial Design

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-01 Thu&gt;</span></span></p>

-   [X] Understand the problem domain
-   [X] Research on Howitzers, their operation and factors affecting their projectiles
-   [X] Understand the physics formulas provided and how they apply to the problem


<a id="org895f738"></a>

### DONE Define system specifications

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-06 Tue&gt;</span></span></p>

-   [X] Define the required inputs and expected outputs of the simulator
-   [X] Identify key variables of the system: barrel pose, projectile radius and mass, drag coefficient, initial speed, force, and gravity
-   [X] Discuss and agree on how these variables will be represented and manipulated in the simulator


<a id="org6b66cf6"></a>

### DONE Design the software architecture

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-11 Sun&gt;</span></span></p>

-   [X] Identify key components/modules of the simulator
-   [X] Establish interfaces and interactions between the components
-   [X] Choose suitable data structures and algorithms for implementing the simulation
-   [X] Sketch out a flowchart or UML diagram of the proposed system for visualization


<a id="org0a6b79f"></a>

## DONE Development

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-18 Sun&gt;</span></span></p>


<a id="org9fa91b4"></a>

### Misc

-   [X] Set up action to run tests on github
-   [X] Set up action to mirror repo to gitlab

-   [X] Set up the basic structure of the simulator
-   [X] Create the basic classes and functions as per the software architecture
-   [X] Set up a basic UI if necessary, depending on plan to represent the simulator


<a id="org3d38bef"></a>

### DONE Physics simulation Itr 1

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-27 Tue&gt;</span></span></p>

First iteration will include external force and drag in a unidirectional, time invariant vector space

-   [X] Translate the physics formulas into Java code, options:
    -   [X] hard coded kinematics
    -   [X] parse equations to function
-   [X] Implement the logic for simulating projectile movement
    -   [X] external force applied
    -   [X] drag force applied

