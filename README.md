# InstantDirt Mod

[![Minecraft Version: 1.21.1](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)](https://www.minecraft.net)
[![Mod Loader: NeoForge](https://img.shields.io/badge/Mod%20Loader-NeoForge-blue.svg)](https://neoforged.net/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

InstantDirt is a lightweight, server-side Minecraft mod that introduces a more intuitive and realistic "sunlight" mechanic for grass and mycelium blocks.

## Features

This mod enhances a single vanilla mechanic: Grass and Mycelium will instantly turn to Dirt when they are deprived of light. The mod is smart about what "deprived of light" means, checking for both the block's shape and its material properties.

This logic applies in two scenarios:
-   When you **cover** an existing grass/mycelium block.
-   When you **place** a grass/mycelium block under an existing cover.

**Examples:**
-   ✅ Placing **solid blocks** (Cobblestone, Planks) on grass turns it to dirt.
-   ✅ Placing **right-side-up Stairs or bottom Slabs** on grass turns it to dirt.
-   ❌ Placing **upside-down Stairs or top Slabs** on grass does **not** affect it.
-   ❌ Placing **transparent blocks** (Glass) on grass does **not** affect it.
-   ❌ Placing **partial blocks with gaps** (Chests, Anvils, Fences) on grass does **not** affect it.

## Key Characteristics

### ✅ Lightweight and Performant
The mod's logic is highly efficient and only runs during block placement events, resulting in a negligible impact on server performance.

### ✅ Server-Side Only
This is a key feature for server administrators. **Players do not need to install this mod to join a server that has it.** All logic is handled by the server, and vanilla clients will see the results seamlessly.

### ✅ Vanilla Feel
InstantDirt adds no new blocks, items, or textures. Its change is designed to feel like a logical extension of the base game—the way grass and sunlight *should* have worked all along.

## Installation

1.  Ensure you have **NeoForge** for Minecraft 1.21.1 installed.
2.  Download the latest `.jar` file from the [GitHub Releases page](https://github.com/hinson820/InstantDirt/releases).
3.  Place the downloaded `.jar` file into your `mods` folder.
4.  Start the game or server. That's it!

## Building From Source (For Developers)

1.  **Prerequisites:**
    -   JDK (Java Development Kit) 21 is required.

2.  **Clone the repository:**
    ```bash
    git clone https://github.com/hinson820/InstantDirt.git
    cd InstantDirt
    ```

3.  **Build the project using the Gradle wrapper:**
    -   On Windows:
        ```bash
        gradlew build
        ```
    -   On macOS/Linux:
        ```bash
        ./gradlew build
        ```

4.  The compiled mod `.jar` file will be located in the `build/libs/` directory.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
