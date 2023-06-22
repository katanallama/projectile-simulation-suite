
# Table of Contents

1.  [Schedule <code>[1/4]</code>](#org249871a)
    1.  [Development <code>[1/3]</code>](#orgd027a10)
        1.  [Development](#org2fbdcc5)
        2.  [Implement the input and output handling](#org56000d3)
        3.  [Physics simulation Itr 1](#org712b4ba)
        4.  [Physics simulation Itr 2](#org055e0d4)
    2.  [Testing <code>[0/3]</code>](#org7c5dbb7)
        1.  [Unit Testing](#org200a99c)
        2.  [Integration Testing](#orgb507af1)
        3.  [System Testing](#org7def42b)
    3.  [Docs & Submission <code>[0/3]</code>](#org0b7c9ce)
        1.  [Documentation](#orgcf92ffe)
        2.  [Review and Final Adjustments](#org22d0c18)
        3.  [Final Submission](#org7ac8627)
2.  [Completed](#org59972b3)
    1.  [Set Up Development Environment](#org21ebf59)
    2.  [Develop Initial Understanding](#org6b327fc)
        1.  [Problem Spec readme](#orgae4074f)
    3.  [Design <code>[0/0]</code>](#orgb250fcf)
        1.  [Initial Design](#org2270545)
        2.  [Define system specifications](#org1267fd6)
        3.  [Design the software architecture](#org4529c12)
    4.  [Development](#org4dca766)
        1.  [Misc](#org683b406)



<a id="org249871a"></a>

# PROJ Schedule <code>[1/4]</code>


<a id="orgd027a10"></a>

## TODO Development <code>[1/3]</code>


<a id="org2fbdcc5"></a>

### DONE Development

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-18 Sun&gt;</span></span></p>

-   [X] Set up the basic structure of the simulator
-   [X] Create the basic classes and functions as per the software architecture
-   [X] Set up a basic UI if necessary, depending on plan to represent the simulator


<a id="org56000d3"></a>

### TODO Implement the input and output handling

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-27 Tue&gt;</span></span></p>

-   [ ] Develop the logic for users to input the variables for the simulation
    -   [ ] json file parsing (gson)
    -   [ ] cli arguments (picocli)
-   [ ] Implement the logic for displaying the output of the simulation
    -   Currently prints a tuple(x, y, z) coordinates of the projectile in the vector space


<a id="org712b4ba"></a>

### TODO Physics simulation Itr 1

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-27 Tue&gt;</span></span></p>

First iteration will include external force and drag in a unidirectional, time invariant vector space

-   [ ] Translate the physics formulas into Java code, options:
    -   [ ] hard coded kinematics
    -   [ ] parse equations to function
-   [ ] Implement the logic for simulating projectile movement
    -   [ ] external force applied
    -   [ ] drag force applied

1.  TODO Review Itr 1

    <p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-27 Tue&gt;</span></span></p>
    
    -   [ ] Ensure that all key variables incorporated into the simulation correctly
    -   [ ] Ensure that errors in inputs handled gracefully
    -   [ ] Align on path forward for next iteration


<a id="org055e0d4"></a>

### TODO Physics simulation Itr 2

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-04 Tue&gt;</span></span></p>

1.  TODO Review Itr 2

    <p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-04 Tue&gt;</span></span></p>


<a id="org7c5dbb7"></a>

## TODO Testing <code>[0/3]</code>


<a id="org200a99c"></a>

### TODO Unit Testing

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-09 Sun&gt;</span></span></p>

-   [ ] Identify functions for testing
-   [ ] Develop boundary value tests
-   [ ] Develop equivalence class tests
-   [ ] Develop decision table-based tests
-   [ ] Develop path tests
-   [ ] Develop data flow tests
-   [ ] Develop slice tests


<a id="orgb507af1"></a>

### TODO Integration Testing

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-09 Sun&gt;</span></span></p>

-   [ ] Identify subset of units for integration testing
-   [ ] Develop and execute integration tests


<a id="org7def42b"></a>

### TODO System Testing

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-09 Sun&gt;</span></span></p>

-   [ ] Identify subset of use cases for system testing
-   [ ] Develop and execute system tests


<a id="org0b7c9ce"></a>

## TODO Docs & Submission <code>[0/3]</code>


<a id="orgcf92ffe"></a>

### TODO Documentation

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-20 Thu&gt;</span></span></p>

-   [ ] Create README.md file
-   [ ] Create TESTING.md file
-   [ ] Ensure code is commented


<a id="org22d0c18"></a>

### TODO Review and Final Adjustments

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-25 Tue&gt;</span></span></p>

-   [ ] Review entire simulator and tests
-   [ ] Make necessary adjustments


<a id="org7ac8627"></a>

### TODO Final Submission

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-08-02 Wed&gt;</span></span></p>


<a id="org59972b3"></a>

# Completed


<a id="org21ebf59"></a>

## DONE Set Up Development Environment

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-05-23 Tue&gt;</span></span></p>

-   [X] Install necessary software
-   [X] Setup GitHub repository


<a id="org6b327fc"></a>

## DONE Develop Initial Understanding

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-02 Fri&gt;</span></span></p>

-   [X] Understand the physics involved in the simulation
-   [X] Research on best practices for implementing physics in Java
-   [X] Research on best practices for java testing frameworks


<a id="orgae4074f"></a>

### DONE Problem Spec readme

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-02 Fri&gt;</span></span></p>

1.  DONE Objectives

2.  DONE Functions

3.  DONE Requirements


<a id="orgb250fcf"></a>

## DONE Design <code>[0/0]</code>


<a id="org2270545"></a>

### DONE Initial Design

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-01 Thu&gt;</span></span></p>

-   [X] Understand the problem domain
-   [X] Research on Howitzers, their operation and factors affecting their projectiles
-   [X] Understand the physics formulas provided and how they apply to the problem


<a id="org1267fd6"></a>

### DONE Define system specifications

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-06 Tue&gt;</span></span></p>

-   [X] Define the required inputs and expected outputs of the simulator
-   [X] Identify key variables of the system: barrel pose, projectile radius and mass, drag coefficient, initial speed, force, and gravity
-   [X] Discuss and agree on how these variables will be represented and manipulated in the simulator


<a id="org4529c12"></a>

### DONE Design the software architecture

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-11 Sun&gt;</span></span></p>

-   [X] Identify key components/modules of the simulator
-   [X] Establish interfaces and interactions between the components
-   [X] Choose suitable data structures and algorithms for implementing the simulation
-   [X] Sketch out a flowchart or UML diagram of the proposed system for visualization


<a id="org4dca766"></a>

## DONE Development


<a id="org683b406"></a>

### Misc

-   [X] Set up action to run tests on github
-   [X] Set up action to mirror repo to gitlab

