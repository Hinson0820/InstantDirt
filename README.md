# InstantDirt Mod

[![Minecraft Version: 1.21.1](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)](https://www.minecraft.net)
[![Mod Loader: NeoForge](https://img.shields.io/badge/Mod%20Loader-NeoForge-blue.svg)](https://neoforged.net/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A simple, lightweight, server-side Minecraft mod that brings a small touch of realism and convenience to your building and landscaping projects.

## Features

The core functionality is simple and focused: when an opaque block is placed on top of grass or mycelium, they instantly convert to dirt.

-   **Grass Blocks** placed under a solid block will turn into a `Dirt` block.
-   **Mycelium Blocks** placed under a solid block will turn into a `Dirt` block.

This mimics the effect of cutting off sunlight, providing a quick and intuitive way to manage grassy areas without needing to use a shovel.

## Key Characteristics

### ✅ Lightweight and Performant
This mod contains minimal, highly-optimized code. It has virtually no impact on server performance.

### ✅ Server-Side Only
This is a huge advantage for server administrators. **Players do not need to install this mod to join a server that has it.** It works entirely on the server, and vanilla clients will see the changes seamlessly.

### ✅ Vanilla Feel
InstantDirt adds no new blocks, items, or textures. It simply alters a vanilla behavior in a way that feels natural and intuitive to the core game.

### ✅ Simplifies Building
Quickly clear grassy patches for foundations or create dirt paths without having to break and replace blocks manually.

## Installation (For Players and Server Admins)

1.  Ensure you have **NeoForge** for Minecraft 1.21.1 installed.
2.  Download the latest `.jar` file from the [GitHub Releases page](https://github.com/hinson820/InstantDirt/releases).
3.  Place the downloaded `.jar` file into your `mods` folder.
4.  Start the game or server. That's it!

## Building From Source (For Developers)

If you want to compile the mod yourself, follow these steps:

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