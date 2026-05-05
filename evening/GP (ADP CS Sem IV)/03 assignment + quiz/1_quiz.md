# CS2713 Game Programming – Practical Research-Based Quiz (Weeks 1–3)

**Time required:** ~2 hours  
**Open-book (lecture notes, course materials) – No Unity installation or game tool allowed**  
**Objective:** Assess deep synthesis of Week 1–3 concepts through a single, scenario‑driven technical design task.

---

## Scenario

You are the **Lead Developer** in a two‑person indie studio. Your task is to design and write a complete technical implementation plan for a 2D arcade game called **“Orbit Guardian”**, directly inspired by the Solar System Simulation project and the foundational Unity workflow covered in Weeks 1–3.

In **Orbit Guardian** the player controls a spaceship that **orbits a central planet** (just as the Earth orbits the Sun in your earlier assignment). Instead of only viewing celestial bodies, the spaceship must **defend the planet by shooting down incoming asteroids**. The game features **three progressively harder levels**, a **start menu**, and a **game‑over screen**.

You do **not** have access to Unity during this quiz. All answers must be derived from your lecture notes, understanding of the Unity editor, and the principles taught during Weeks 1–3 (Introduction, Game Engine, Solar System project, Level Design, Game System).

Your submission must be a single, coherent document that addresses **all seven sections** below with deep technical specificity. Generic or non–course‑referenced answers will not meet the professional standard required.

---

## Submission Requirements

### 1. Game Design Summary *(≈300 words)*
Outline the core gameplay loop, the verbs (actions) available to the player, and how the game escalates across three levels.  
**Explicitly connect** your design to:
- The role of a **game designer**
- The **development team structure** discussed in Week 1
- **Costs and limitation considerations** from Week 1

### 2. Unity Project & Scene Setup
Describe the **initial project creation steps** as you would follow them from the Week 2–3 solar system workflow:
- Project naming, folder organisation, importing assets
- Configuring the default scene  
Explain how you would **organise the Hierarchy** for Orbit Guardian, including lights, cameras, and empty parent GameObjects (as seen in the Solar System Simulation). Justify your choices in terms of reusability and clarity.

### 3. Component‑Based Architecture
For the following three core GameObjects – **PlayerSpaceship**, **Asteroid**, and **Planet** – list **all necessary Unity components** (Transform, Sprite Renderer, Collider, Rigidbody, custom scripts, etc.) and justify each.

Explain:
- How you will use **Prefabs** for asteroids and projectiles
- How the **Inspector** will expose tuning parameters (speed, health, spawn rate)
- Why this approach builds on the Prefab knowledge from the Solar System wrap‑up

### 4. Scripting & Game Loop (Pseudo‑Code)
Provide detailed, commented pseudo‑code for **two C# scripts** that are central to the game. Place each method in the correct Unity execution order (`Awake`, `Start`, `Update`, etc.) and justify.

#### `OrbitalMover.cs` (attached to PlayerSpaceship)
Moves the ship in a perfect circle around the planet **using Transform manipulation only** (no physics). Must include:
- Adjustable orbit **radius** and **speed**
- Input to toggle orbit **direction** (clockwise / counter‑clockwise)

#### `GameManager.cs`
Manages game states (Menu, Playing, GameOver), score, and spawns waves of asteroids using a repeating call (conceptually `InvokeRepeating` or a coroutine pattern – you may explain the logic without Unity’s exact API).

For each script, mention which **behaviours** (Week 3 term) are being implemented and how they contribute to the overall **game system**.

### 5. Level Design & Game System
Design **three distinct levels**. For each level define:
- Asteroid spawn pattern and quantity (random directions? timed waves?)
- Player’s starting orbit radius and fire rate
- Win condition (survive X seconds / destroy Y asteroids)
- Environmental variation (planet material colour, background Sprite, or lighting changes)

Explain how the **Game System** (finite state machine, score, lives) transitions between levels and to Game Over.  
Additionally, describe how you would integrate **Audio** (background music, sound effects) and **UI** (score text, health bar, menu buttons) using Unity’s Audio Source, Canvas, and Text components – as shown in the final Solar System steps (Week 3).

### 6. Build & Screenshot Plan
Detail the **Build Settings** process to produce a standalone PC build. Which Player Settings would you modify (resolution, company name, etc.)?

Then, give a **step‑by‑step method** for capturing a high‑quality promotional screenshot from the **Game View**, just as the “taking screenshot” topic in Week 3 instructed.

Finally, explain where you would source **Additional Resources** (free sprites, sounds) to polish Orbit Guardian, referencing the lecture’s mention of Additional Resources and how they accelerate development.

### 7. Reflection on Development Process
Using the Week 1 topics **“Costs and limitation”** and **“The game Development team”**, evaluate the real‑world challenges of building Orbit Guardian with only two people. What are the key limitations, and how would you **mitigate risks** to keep the project feasible while still meeting the three‑level scope?

---

## Evaluation Criterion

Answers that lack **precise references** to:
- The CS2713 Solar System Simulation
- Specific Unity workflow terms (Materials, Lights, Behaviours, Cameras, Audio, Prefabs, Build, taking screenshot, Additional Resources)
- The theoretical Week 1–3 content

…will be deemed insufficient. This quiz demands a **professional synthesis of the course’s practical and conceptual layers** – the kind of deep, contextualised thinking that cannot be convincingly outsourced to a generic AI prompt.

*Good luck – think like the Lead Developer who has just completed the Solar System Simulation and is now using that exact workflow to craft Orbit Guardian.*