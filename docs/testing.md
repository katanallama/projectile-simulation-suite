
# Table of Contents

1.  [Decision Table Testing](#org25dc064)
    1.  [Variables Involved](#org02b51cf)
    2.  [First Iteration](#org4e922c5)
        1.  [Decisions Summary](#org43ccb3a)
        2.  [Decision Table](#org1ced758)
    3.  [Second Iteration](#org58e9b0f)
        1.  [Updated Decisions Summary](#orgcfd452d)
        2.  [Updated Decision Table](#orgce5c833)



<a id="org25dc064"></a>

# Decision Table Testing

Before we start, it&rsquo;s important to understand what decision table-based testing is. This method is a black-box test design technique where test cases are designed to execute the combinations of inputs and/or stimuli (causes) shown in a decision table. A decision table is a good way to deal with combinations of things (e.g., inputs).

To conduct Decision Table-Based testing on the `getProjectileDrag()` method, we begin by identifying the decisions the code is making and the variables those decisions are based on.

These decisions encapsulate the business logic of the function, providing a well-defined set of rules for how the function should behave under various conditions. The goal of decision table testing is to ensure that these rules are being applied correctly and consistently across a broad range of potential scenarios.


<a id="org02b51cf"></a>

## Variables Involved

-   `velocity` : The velocity of the projectile.

-   `rho` : The density of the fluid in which the projectile is moving.

-   `A` : The cross-sectional area of the projectile.

-   `Cd` : The drag coefficient.


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

