
# Table of Contents

1.  [Design Choices](#org4540916)
    1.  [Benefits](#org69827a2)
    2.  [Basic Classes](#orgc799de0)
1.  [Overall Class Diagram](#orga050aab)



<a id="org4540916"></a>

## Design Choices

Taking into account the modularity, extensibility, and testability required for this simulation program, the strategy design pattern is best suited.

The strategy design pattern allows us to select an algorithm&rsquo;s behavior at runtime. It defines a family of algorithms, encapsulates each one, and makes them interchangeable. In this case, there are multiple methods (strategies) for simulating the physics of a projectile, accounting for different factors such as wind resistance, gravity, barrel pose, etc. These strategies could then be hot swapped at runtime.

This allows for adding new - or removing, variables without affecting the overall structure of the simulation, and without having to change the main simulation code. This separation of concerns makes it easier to write tests for each strategy.


<a id="org69827a2"></a>

### Benefits

-   *Flexibility*: It allows us to dynamically choose between different behaviors.

-   *Extensibility*: We can add new behaviors without affecting existing code.

-   *Testability*: We can test behaviors individually, isolated from the rest of the system.


<a id="orgc799de0"></a>

### Basic Classes

The individual Classes and basic relationships are as follows:

1.  Simulation Suite

    ![img](./res/pss.png)

2.  Simulator

    ![img](./res/simulator.png)

3.  Projectile

    ![img](./res/projectile.png)

4.  Handlers

    ![img](./res/handlers.png)

5.  Interfaces

    ![img](./res/interfaces.png)

6.  Factories

    ![img](./res/factories.png)


<a id="orga050aab"></a>

# Overall Class Diagram

![img](./res/suite.png)
