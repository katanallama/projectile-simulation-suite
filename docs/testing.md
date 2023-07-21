
# Table of Contents

1. [Testing Summary](#orgfca23cd1)
    1. [Variables Involved](#org02b51cf)
2. [Decision Table Testing](#org25dc064)
    1.  [First Iteration](#org4e922c5)
        1.  [Decisions Summary](#org43ccb3a)
        2.  [Decision Table](#org1ced758)
    2.  [Second Iteration](#org58e9b0f)
        1.  [Updated Decisions Summary](#orgcfd452d)
        2.  [Updated Decision Table](#orgce5c833)
3. [Structural Analysis](#org3ac4fb9)
    1. [Path Coverage](#orgf56ae2c)
    2. [Data Flow Coverage](#org42ca9d1)


<a id="orgfca23cd1"></a>

# Testing Summary

<a id="org02b51cf"></a>

## Variables Involved

-   `velocity` : The velocity of the projectile.

-   `rho` : The density of the fluid in which the projectile is moving.

-   `A` : The cross-sectional area of the projectile.

-   `Cd` : The drag coefficient.


<a id="org25dc064"></a>

# Decision Table Testing

Before we start, it&rsquo;s important to understand what decision table-based testing is. This method is a black-box test design technique where test cases are designed to execute the combinations of inputs and/or stimuli (causes) shown in a decision table. A decision table is a good way to deal with combinations of things (e.g., inputs).

To conduct Decision Table-Based testing on the `getProjectileDrag()` method, we begin by identifying the decisions the code is making and the variables those decisions are based on.

These decisions encapsulate the business logic of the function, providing a well-defined set of rules for how the function should behave under various conditions. The goal of decision table testing is to ensure that these rules are being applied correctly and consistently across a broad range of potential scenarios.


<a id="org4e922c5"></a>

## First Iteration


<a id="org43ccb3a"></a>

### Decisions Summary

-   If the projectile&rsquo;s velocity is `(0, 0, 0)`, then the function immediately returns `(0, 0, 0)`.

-   If not, the function calculates the drag force using the given parameters and returns the result.

Based on these decisions and variables, we construct a decision table. However, as these variables are continuous and not discrete, creating a full decision table isn&rsquo;t feasible. Instead, we focus on testing key scenarios or boundaries which could be representative of the whole range of possible scenarios.


<a id="org1ced758"></a>

### Decision Table

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />
</colgroup>
<thead>
<tr>
<th scope="col" class="org-left">&#xa0;</th>
<th scope="col" class="org-left">&#xa0;</th>
<th scope="col" class="org-left">1</th>
<th scope="col" class="org-left">2</th>
</tr>
</thead>

<tbody>
<tr>
<td class="org-left">C1:</td>
<td class="org-left">velocity = 0?</td>
<td class="org-left">T</td>
<td class="org-left">F</td>
</tr>
</tbody>

<tbody>
<tr>
<td class="org-left">A1:</td>
<td class="org-left">Return (0,0,0)</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-left">A2:</td>
<td class="org-left">Return Drag</td>
<td class="org-left">-</td>
<td class="org-left">X</td>
</tr>
</tbody>
</table>

And the outputs:

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col  class="org-right" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />
</colgroup>
<tbody>
<tr>
<td class="org-right">&#xa0;</td>
<td class="org-left">C1:</td>
<td class="org-left">A1:</td>
<td class="org-left">A2:</td>
</tr>


<tr>
<td class="org-right">&#xa0;</td>
<td class="org-left">velocity = 0?</td>
<td class="org-left">Return (0,0,0)</td>
<td class="org-left">Return Drag</td>
</tr>


<tr>
<td class="org-right">1</td>
<td class="org-left">(0, 0 ,0)</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-right">2</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">-</td>
<td class="org-left">X</td>
</tr>
</tbody>
</table>


<a id="org58e9b0f"></a>

## Second Iteration

Based on the second iteration of the function, we can see that some changes have been implemented, which require the decision table to be updated.


<a id="orgcfd452d"></a>

### Updated Decisions Summary

The second iteration of `getProjectileDrag()` incorporates additional decisions based on the findings from the first iteration of testing.

-   If the velocity of the projectile is `(0, 0, 0)`, then the function immediately returns a drag of `(0, 0, 0)`. This corresponds to the physical intuition that if an object is not moving, it will not experience any drag.

-   If any `x, y` component of the velocity vector is negative, the function also returns a drag of `(0, 0, 0)`. This corresponds to a physical model using relative positioning. We consider the origin as the howitzer position and the trajectory of a projectile as a positive displacement over time. This trajectory can then be overlaid onto any mapping of potential physical terrain.

-   Subsequently, the function proceeds to calculate the drag force using the parameters: fluid density `rho`, the cross-sectional area of the projectile `A`, the drag coefficient `Cd`, and the current velocity squared `vSquared`.

-   While calculating the drag, the function checks if the computed drag magnitude is greater than 0, which would mean the drag is in the same direction as the velocity. This is physically incorrect as the drag should always oppose the direction of velocity. If this condition is met, the function returns a drag of `(0, 0, 0)`.

If any of the above conditions are not met, the function calculates the drag force as per the equation and returns the resultant drag vector.


<a id="orgce5c833"></a>

### Updated Decision Table

The full decision table for the 4 conditions and $2^{4} = 16$ rules:

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />
</colgroup>
<thead>
<tr>
<th scope="col" class="org-left">&#xa0;</th>
<th scope="col" class="org-left">&#xa0;</th>
<th scope="col" class="org-left">1</th>
<th scope="col" class="org-left">2</th>
<th scope="col" class="org-left">3</th>
<th scope="col" class="org-left">4</th>
<th scope="col" class="org-left">5</th>
<th scope="col" class="org-left">6</th>
<th scope="col" class="org-left">7</th>
<th scope="col" class="org-left">8</th>
<th scope="col" class="org-left">9</th>
<th scope="col" class="org-left">10</th>
<th scope="col" class="org-left">11</th>
<th scope="col" class="org-left">12</th>
<th scope="col" class="org-left">13</th>
<th scope="col" class="org-left">14</th>
<th scope="col" class="org-left">15</th>
<th scope="col" class="org-left">16</th>
</tr>
</thead>

<tbody>
<tr>
<td class="org-left">C1:</td>
<td class="org-left"><code>velocity</code> = 0?</td>
<td class="org-left">T</td>
<td class="org-left">T</td>
<td class="org-left">T</td>
<td class="org-left">T</td>
<td class="org-left">T</td>
<td class="org-left">T</td>
<td class="org-left">T</td>
<td class="org-left">T</td>
<td class="org-left">F</td>
<td class="org-left">F</td>
<td class="org-left">F</td>
<td class="org-left">F</td>
<td class="org-left">F</td>
<td class="org-left">F</td>
<td class="org-left">F</td>
<td class="org-left">F</td>
</tr>


<tr>
<td class="org-left">C2:</td>
<td class="org-left"><code>velocity.x, y</code> &lt; 0?</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">T</td>
<td class="org-left">T</td>
<td class="org-left">T</td>
<td class="org-left">T</td>
<td class="org-left">F</td>
<td class="org-left">F</td>
<td class="org-left">F</td>
<td class="org-left">F</td>
</tr>


<tr>
<td class="org-left">C3:</td>
<td class="org-left">rho or A or Cd &lt;= 0?</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">T</td>
<td class="org-left">T</td>
<td class="org-left">F</td>
<td class="org-left">F</td>
</tr>


<tr>
<td class="org-left">C4:</td>
<td class="org-left">dragMagnitude &gt; 0?</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">F</td>
<td class="org-left">T</td>
<td class="org-left">F</td>
</tr>
</tbody>

<tbody>
<tr>
<td class="org-left">A1:</td>
<td class="org-left">Return (0,0,0)</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-left">A2:</td>
<td class="org-left">Return Drag</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">X</td>
</tr>
</tbody>
</table>

Simplifying the full decision table:

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />
</colgroup>
<thead>
<tr>
<th scope="col" class="org-left">&#xa0;</th>
<th scope="col" class="org-left">&#xa0;</th>
<th scope="col" class="org-left">1</th>
<th scope="col" class="org-left">2</th>
<th scope="col" class="org-left">3</th>
<th scope="col" class="org-left">4</th>
</tr>
</thead>

<tbody>
<tr>
<td class="org-left">C1:</td>
<td class="org-left">velocity = 0?</td>
<td class="org-left">T</td>
<td class="org-left">T</td>
<td class="org-left">F</td>
<td class="org-left">F</td>
</tr>


<tr>
<td class="org-left">C2:</td>
<td class="org-left">velocity.x, y &lt; 0?</td>
<td class="org-left">-</td>
<td class="org-left">T</td>
<td class="org-left">T</td>
<td class="org-left">F</td>
</tr>


<tr>
<td class="org-left">C3:</td>
<td class="org-left">rho or A or Cd &lt;= 0?</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">T</td>
<td class="org-left">F</td>
</tr>


<tr>
<td class="org-left">C4:</td>
<td class="org-left">dragMagnitude &gt; 0?</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">F</td>
</tr>
</tbody>

<tbody>
<tr>
<td class="org-left">A1:</td>
<td class="org-left">Return (0,0,0)</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-left">A2:</td>
<td class="org-left">Return Drag</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">X</td>
</tr>
</tbody>
</table>

The simplified table shows the dependency between some of the rules, but for robustness of testing the function will be tested with the extended set.

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col  class="org-right" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />
</colgroup>
<tbody>
<tr>
<td class="org-right">&#xa0;</td>
<td class="org-left">C1:</td>
<td class="org-left">C2:</td>
<td class="org-left">C3:</td>
<td class="org-left">C4:</td>
<td class="org-left">A1:</td>
<td class="org-left">A2:</td>
</tr>


<tr>
<td class="org-right">&#xa0;</td>
<td class="org-left"><code>velocity</code> = 0?</td>
<td class="org-left"><code>velocity.x, y</code> &lt; 0?</td>
<td class="org-left">rho or A or Cd &lt;= 0?</td>
<td class="org-left">dragMagnitude &gt; 0?</td>
<td class="org-left">Return (0,0,0)</td>
<td class="org-left">Return Drag</td>
</tr>


<tr>
<td class="org-right">1</td>
<td class="org-left">(0, 0, 0)</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-right">2</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(-1, 1, 1)</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-right">3</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(1, -1, 1)</td>
<td class="org-left">-</td>
<td class="org-left">-</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-right">4</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">rho = 0</td>
<td class="org-left">-</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-right">5</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">A = 0</td>
<td class="org-left">-</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-right">6</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">Cd = 0</td>
<td class="org-left">-</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-right">7</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">rho = 0</td>
<td class="org-left">F</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-right">8</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">A = 0</td>
<td class="org-left">F</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-right">9</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">Cd = 0</td>
<td class="org-left">F</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-right">10</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">rho = -1</td>
<td class="org-left">T</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-right">11</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">A = -1</td>
<td class="org-left">T</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-right">12</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">Cd = -1</td>
<td class="org-left">T</td>
<td class="org-left">X</td>
<td class="org-left">-</td>
</tr>


<tr>
<td class="org-right">13</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">rho = 1</td>
<td class="org-left">F</td>
<td class="org-left">-</td>
<td class="org-left">X</td>
</tr>


<tr>
<td class="org-right">14</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">A = 1</td>
<td class="org-left">F</td>
<td class="org-left">-</td>
<td class="org-left">X</td>
</tr>


<tr>
<td class="org-right">15</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">(1, 1, 1)</td>
<td class="org-left">Cd = 1</td>
<td class="org-left">F</td>
<td class="org-left">-</td>
<td class="org-left">X</td>
</tr>
</tbody>
</table>

As seen from the revised decision table, we now have additional test cases where the velocity components are negative, testing the updated conditions in the function. This will provide a more thorough test of the function&rsquo;s logic and help identify any potential issues with these new conditions.


<a id="org3ac4fb9"></a>

# Structural Analysis

This is a straightforward technique of analyzing a software artifact - in this case the function ```getProjectileDrag()``` - outside of runtime. The method we use here is to convert the code blocks into a directed, structural graph to visually describe all possible courses of actions the code can take. Then we can explicitly demonstrate what pieces of code are covered by our test cases.

![img](./res/graph-structural.png "Structural Graph")

<a id="orgf56ae2c"></a>

## Path Coverage

One type of static analysis is path testing, where we analyze the "paths" a software could take under certain conditions. 

Normally, we would aim to analyze for **Prime Path Coverage**, but  due to the simplicity of ```getProjectileDrag()```, the Prime Path Coverage in our case also happens to be the best (and most expensive) kind of graph coverage: **Complete Graph Coverage**. 

### Test Requirements

The Prime/Complete path set is as follows:
1. [1, 2, 4, 5, 6, 7]
2. [1, 2, 3]
3. [1, 2, 4, 3]

### Test Cases

1. Happy Path
- velocity = (1, 1, 1)
- Settings.FluidRho = 1
- Settings.ProjectileArea = 1
- Settings.DragCoefficient = 1

2. Negative or Zero Velocity
- velocity = (0, 0, 0)
- Settings.FluidRho = 1
- Settings.ProjectileArea = 1
- Settings.DragCoefficient = 1

3. Invalid Drag Magnitude
- velocity = (1, 1, 1)
- Settings.FluidRho = -1
- Settings.ProjectileArea = 1
- Settings.DragCoefficient = 1
**NOTE** this condition may be impossible to reach! For drag magnitude to be > 0, one or all 3 of the initial settings must be incorrectly set to a negative number, an un-intentional (and physically impossible) use case. However, we will also test this case for robustness since it is the only one (as compared to a set of illogical conditions in a more complex function).

<a id="org42ca9d1"></a>

## Data Flow Coverage

Another type of structural analysis is data flow, which focuses on the definition and use of variables. This allows us to verify that all variables are used correctly (e.g. not redefined before their intended use, or never used at all).

The coverage criteria we are analyzing for is **All DU-Path Coverage**, as it is the most comprehensive type of data flow analysis (subsuming both All Def-Coverage and All Use-Coverage). All DU-Path Coverage is defined as: ```For each set S = du(initial_node, final_node, variable), the Test Requirements contain every path d in S```
    --> cover all the paths between a variables def and its uses

![img](./res/graph-data-flow.png "Data Flow Graph")

### Test Requirements

The DU pairs of the graph are as follows:
1. du(1, 2, velocity)
2. du(1, (2, 3), velocity)
3. du(1, 4, velocity)
4. du(4, 5, unitVelocity)
5. du(4, (4, 3), dragMagnitude)
6. du(4, 6, dragMagnitude)
7. du(5, 6, unitVelocity)
8. du(6, 7, dragForce)

(Note: a few node have definitions followed by a single immediate use (rho, A, cD, vSquared, unitVelocity, and dragMagnitude) and are not considered for our du-paths)

If we define the "happy path" as avoiding both if statements (edges (2,3) and (3, 4)), we can plainly see that all du paths except for these "if" edges are covered. Thus, a simple test suite of the following paths will cover all du-paths:
1. [1, 2, 4, 5 ,6, 7]
2. [1, 2, 3]
3. [1, 2, 4, 3]

### Test Cases

We can see these Test Requirements are the same set of prime paths from the [Path Coverage](#orgf56ae2c) described above, as is expected with Prime Path and Complete Path Coverage. As such, we have proven we do not need to write any additional test cases, as the test requirements are already fulfilled by other tests.
