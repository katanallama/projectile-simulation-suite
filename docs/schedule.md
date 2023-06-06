
# Table of Contents

1.  [Schedule <code>[2/6]</code>](#org328f707)
    1.  [Set Up Development Environment](#orgeb02497)
    2.  [Develop Initial Understanding](#orge772aa0)
        1.  [Problem Spec readme](#org58d5fee)
    3.  [Design <code>[0/3]</code>](#orgd4db505)
        1.  [Initial Design](#org942c326)
        2.  [Define system specifications](#org767d44b)
        3.  [Design the software architecture](#org6714092)
    4.  [Development <code>[0/3]</code>](#org06cf307)
        1.  [Misc](#orgb1d1170)
        2.  [Implement the input and output handling](#org3ed7803)
        3.  [Development](#orgf8860b8)
        4.  [Implement the physics simulation](#orge71a122)
    5.  [Testing <code>[0/3]</code>](#orgadaeecf)
        1.  [Unit Testing](#org35ad9d0)
        2.  [Integration Testing](#orgcda002d)
        3.  [System Testing](#org08b4b53)
    6.  [Docs & Submission <code>[0/3]</code>](#orge3ee796)
        1.  [Documentation](#org8bf45af)
        2.  [Review and Final Adjustments](#orgd9772f1)
        3.  [Final Submission](#orgc70ba3b)
2.  [Update 1](#org9e5d75d)



<a id="org328f707"></a>

# PROJ Schedule <code>[2/6]</code>


<a id="orgeb02497"></a>

## DONE Set Up Development Environment

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-05-23 Tue&gt;</span></span></p>

-   [X] Install necessary software
-   [X] Setup GitHub repository


<a id="orge772aa0"></a>

## DONE Develop Initial Understanding

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-02 Fri&gt;</span></span></p>

-   [X] Understand the physics involved in the simulation
-   [X] Research on best practices for implementing physics in Java
-   [X] Research on best practices for java testing frameworks


<a id="org58d5fee"></a>

### DONE Problem Spec readme

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-02 Fri&gt;</span></span></p>

1.  DONE Objectives

2.  DONE Functions

3.  DONE Requirements


<a id="orgd4db505"></a>

## TODO Design <code>[0/3]</code>


<a id="org942c326"></a>

### TODO Initial Design

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-01 Thu&gt;</span></span></p>

-   [X] Understand the problem domain
-   [X] Research on Howitzers, their operation and factors affecting their projectiles
-   [X] Understand the physics formulas provided and how they apply to the problem


<a id="org767d44b"></a>

### TODO Define system specifications

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-06 Tue&gt;</span></span></p>

-   [X] Define the required inputs and expected outputs of the simulator
-   [X] Identify key variables of the system: barrel pose, projectile radius and mass, drag coefficient, initial speed, force, and gravity
-   [ ] Discuss and agree on how these variables will be represented and manipulated in the simulator


<a id="org6714092"></a>

### TODO Design the software architecture

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-11 Sun&gt;</span></span></p>

-   [ ] Identify key components/modules of the simulator
-   [ ] Establish interfaces and interactions between the components
-   [ ] Choose suitable data structures and algorithms for implementing the simulation
-   [ ] Sketch out a flowchart or UML diagram of the proposed system for visualization


<a id="org06cf307"></a>

## TODO Development <code>[0/3]</code>


<a id="orgb1d1170"></a>

### Misc

-   [X] Set up action to run tests on github
-   [X] Set up action to mirror repo to gitlab


<a id="org3ed7803"></a>

### TODO Implement the input and output handling

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-11 Sun&gt;</span></span></p>

-   [-] Develop the logic for users to input the variables for the simulation (picocli)
-   [ ] Implement the logic for displaying the output of the simulation
-   [ ] Ensure that errors in inputs handled gracefully


<a id="orgf8860b8"></a>

### TODO Development

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-18 Sun&gt;</span></span></p>

-   [ ] Set up the basic structure of the simulator
-   [ ] Create the basic classes and functions as per the software architecture
-   [ ] Set up a basic UI if necessary, depending on plan to represent the simulator


<a id="orge71a122"></a>

### TODO Implement the physics simulation

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-18 Sun&gt;</span></span></p>

-   [ ] Translate the physics formulas into Java code
-   [ ] Implement the logic for simulating the Howitzer firing and projectile movement
-   [ ] Ensure that all key variables incorporated into the simulation correctly


<a id="orgadaeecf"></a>

## TODO Testing <code>[0/3]</code>


<a id="org35ad9d0"></a>

### TODO Unit Testing

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-06-25 Sun&gt;</span></span></p>

-   [ ] Identify functions for testing
-   [ ] Develop boundary value tests
-   [ ] Develop equivalence class tests
-   [ ] Develop decision table-based tests
-   [ ] Develop path tests
-   [ ] Develop data flow tests
-   [ ] Develop slice tests


<a id="orgcda002d"></a>

### TODO Integration Testing

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-02 Sun&gt;</span></span></p>

-   [ ] Identify subset of units for integration testing
-   [ ] Develop and execute integration tests


<a id="org08b4b53"></a>

### TODO System Testing

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-09 Sun&gt;</span></span></p>

-   [ ] Identify subset of use cases for system testing
-   [ ] Develop and execute system tests


<a id="orge3ee796"></a>

## TODO Docs & Submission <code>[0/3]</code>


<a id="org8bf45af"></a>

### TODO Documentation

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-20 Thu&gt;</span></span></p>

-   [ ] Create README.md file
-   [ ] Create TESTING.md file
-   [ ] Ensure code is commented


<a id="orgd9772f1"></a>

### TODO Review and Final Adjustments

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-07-25 Tue&gt;</span></span></p>

-   [ ] Review entire simulator and tests
-   [ ] Make necessary adjustments


<a id="orgc70ba3b"></a>

### TODO Final Submission

<p><span class="timestamp-wrapper"><span class="timestamp-kwd">DEADLINE:</span> <span class="timestamp">&lt;2023-08-02 Wed&gt;</span></span></p>


<a id="org9e5d75d"></a>

# Update 1

> Please update your GitLab .md files with &rsquo;Problem Specification and Design Requirements&rsquo;. Create a separate section for each.
> 
> In &rsquo;Design Requirements&rsquo; section, you need to specify &rsquo;Objectives, Functions and Constraints&rsquo; each in a separate sub-section. You should consider at least four constraints overarching your design. Following is the list of some constraints (you can also come up your with your own constraints).
> 
> Economic Factors
> Regulatory Compliance (Security and Access)
> Reliability
> Sustainability and Environmental Factors
> Ethics
> Societal Impacts
> The deadline to finish the work is Friday June 02, 23:59:59.

