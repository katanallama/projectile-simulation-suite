
# Table of Contents

1.  [Decision Table Testing](#org21181cf)
    1.  [Variables Involved](#orgfdfc6e2)
    2.  [First Iteration](#org9fba096)
        1.  [Decisions Summary](#org2d9eb1e)
        2.  [Decision Table](#org6f1375c)
    3.  [Second Iteration](#org0c88afa)
        1.  [Updated Decisions Summary](#org2c20b17)
        2.  [Updated Decision Table](#orgaf37e8f)



<a id="org21181cf"></a>

# Decision Table Testing

Before we start, it&rsquo;s important to understand what decision table-based testing is. This method is a black-box test design technique where test cases are designed to execute the combinations of inputs and/or stimuli (causes) shown in a decision table. A decision table is a good way to deal with combinations of things (e.g., inputs).

To conduct Decision Table-Based testing on the `getProjectileDrag()` method, we begin by identifying the decisions the code is making and the variables those decisions are based on.

These decisions encapsulate the business logic of the function, providing a well-defined set of rules for how the function should behave under various conditions. The goal of decision table testing is to ensure that these rules are being applied correctly and consistently across a broad range of potential scenarios.


<a id="orgfdfc6e2"></a>

## Variables Involved

-   `velocity` : The velocity of the projectile.

-   `rho` : The density of the fluid in which the projectile is moving.

-   `A` : The cross-sectional area of the projectile.

-   `Cd` : The drag coefficient.


<a id="org9fba096"></a>

## First Iteration


<a id="org2d9eb1e"></a>

### Decisions Summary

-   If the projectile&rsquo;s velocity is `(0, 0, 0)`, then the function immediately returns `(0, 0, 0)`.

-   If not, the function calculates the drag force using the given parameters and returns the result.

Based on these decisions and variables, we construct a decision table. However, as these variables are continuous and not discrete, creating a full decision table isn&rsquo;t feasible. Instead, we focus on testing key scenarios or boundaries which could be representative of the whole range of possible scenarios.


<a id="org6f1375c"></a>

### Decision Table

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col  class="org-left" />

<col  class="org-right" />

<col  class="org-right" />

<col  class="org-right" />

<col  class="org-left" />
</colgroup>
<tbody>
<tr>
<td class="org-left">Velocity</td>
<td class="org-right">Rho</td>
<td class="org-right">A</td>
<td class="org-right">Cd</td>
<td class="org-left">Expected Output</td>
</tr>


<tr>
<td class="org-left">(0, 0, 0)</td>
<td class="org-right">-</td>
<td class="org-right">-</td>
<td class="org-right">-</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">0</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">0</td>
<td class="org-right">1</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">0</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(-1, -1, -1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">0.1</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">2.0</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">0.1</td>
<td class="org-right">1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">100.0</td>
<td class="org-right">1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">0.1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">1.0</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">-1</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">-1</td>
<td class="org-right">1</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">-1</td>
<td class="org-left">(0, 0, 0)</td>
</tr>
</tbody>
</table>


<a id="org0c88afa"></a>

## Second Iteration

Based on the second iteration of the function, we can see that some changes have been implemented, which require the decision table to be updated.


<a id="org2c20b17"></a>

### Updated Decisions Summary

The second iteration of `getProjectileDrag()` incorporates additional decisions based on the findings from the first iteration of testing.

-   If the velocity of the projectile is `(0, 0, 0)`, then the function immediately returns a drag of `(0, 0, 0)`. This corresponds to the physical intuition that if an object is not moving, it will not experience any drag.

-   If any `x, y` component of the velocity vector is negative, the function also returns a drag of `(0, 0, 0)`. This corresponds to a physical model using relative positioning. We consider the origin as the howitzer position and the trajectory of a projectile as a positive displacement over time. This trajectory can then be overlaid onto any mapping of potential physical terrain.

-   Subsequently, the function proceeds to calculate the drag force using the parameters: fluid density `rho`, the cross-sectional area of the projectile `A`, the drag coefficient `Cd`, and the current velocity squared `vSquared`.

-   While calculating the drag, the function checks if the computed drag magnitude is greater than 0, which would mean the drag is in the same direction as the velocity. This is physically incorrect as the drag should always oppose the direction of velocity. If this condition is met, the function returns a drag of `(0, 0, 0)`.

If any of the above conditions are not met, the function calculates the drag force as per the equation and returns the resultant drag vector.


<a id="orgaf37e8f"></a>

### Updated Decision Table

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col  class="org-left" />

<col  class="org-right" />

<col  class="org-right" />

<col  class="org-right" />

<col  class="org-left" />
</colgroup>
<tbody>
<tr>
<td class="org-left">Velocity</td>
<td class="org-right">Rho</td>
<td class="org-right">A</td>
<td class="org-right">Cd</td>
<td class="org-left">Expected Output</td>
</tr>


<tr>
<td class="org-left">(0, 0, 0)</td>
<td class="org-right">-</td>
<td class="org-right">-</td>
<td class="org-right">-</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">0</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">0</td>
<td class="org-right">1</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(-1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(1, -1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(1, 1, -1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">0</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(-1, -1, -1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">0.1</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">2.0</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">0.1</td>
<td class="org-right">1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">100.0</td>
<td class="org-right">1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">0.1</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">1.0</td>
<td class="org-left">(as per equation)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">-1</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">-1</td>
<td class="org-right">1</td>
<td class="org-left">(0, 0, 0)</td>
</tr>


<tr>
<td class="org-left">(1, 1, 1)</td>
<td class="org-right">1</td>
<td class="org-right">1</td>
<td class="org-right">-1</td>
<td class="org-left">(0, 0, 0)</td>
</tr>
</tbody>
</table>

As seen from the revised decision table, we now have additional test cases where the velocity components are negative, testing the updated conditions in the function. This will provide a more thorough test of the function&rsquo;s logic and help identify any potential issues with these new conditions.

