# Monkey Boy 🐒

A 2D adventure game built in Java featuring exploration, combat, magic, and quests in a pixel art 2D world.

## Game Overview

Monkey Boy is a classic 2D top down adventure game where you play as a young hero exploring villages, dungeons, and boss arenas. The game features real-time combat, magic spells, inventory management, and an engaging storyline with NPCs and quests.

## Features

### Core Gameplay

- **Real-time Combat** — Attack, guard, and use magic spells
- **Magic System** — Cast fireballs and other spells with mana management
- **Inventory & Equipment** — Collect weapons, armor, potions, and consumables
- **Quest System** — Interact with NPCs and complete story objectives
- **Save/Load System** — Progress is automatically saved

### World & Exploration

- **Multiple Areas** — Villages, dungeons, world maps, and boss rooms
- **Interactive Environment** — Doors, chests, torches, and other interactive objects
- **Day/Night Cycle** — Dynamic lighting with environmental effects
- **Mini-map** — Navigate with the built-in minimap system

### Combat & Enemies

- **Diverse Enemy Types**
  - Slimes (Red, Green, Blue)
  - Orcs and Dungeon Orcs
  - Bats
  - Boss Monsters (Slime Boss, Skeleton Lord)
- **Boss Battles** — Each boss has unique mechanics

### Technical Features

- **60 FPS Gameplay** — Smooth performance with optimized rendering
- **Fullscreen Support** — Toggle between windowed and fullscreen modes
- **Debug Mode** — Developer tools for testing and debugging
- **Audio System** — Background music and sound effects

## Game Areas

The game includes 14 playable maps:

| Area | Description |
|------|-------------|
| Bedroom | Starting area |
| Downstairs | Home interior |
| Village | Main hub with NPCs and shops |
| Store | Shop for equipment and items |
| Wizard Home | Magic-related content |
| World Maps (5) | Overworld exploration |
| Dungeons (2) | Dangerous underground areas |
| Boss Rooms | Slime Boss and Skeleton Lord arenas |

## Controls

### Movement

| Key | Action |
|-----|--------|
| Arrow Keys / WASD | Move character |
| Enter | Interact with objects and NPCs |

### Combat

| Key | Action |
|-----|--------|
| Enter | Attack |
| Space | Guard / Block |
| F | Cast magic spell (Fireball) |

### Game Systems

| Key | Action |
|-----|--------|
| C | Open inventory / character screen |
| V | Drop item |
| M | Toggle map |
| X | Toggle minimap |
| Esc | Open options menu |

### Debug (if enabled)

| Key | Action |
|-----|--------|
| F1 | Toggle debug mode |
| G | God mode |
| T | Freeze enemies |
| U | Toggle collision |
| I | Show collision |
| O | Toggle darkness filter |
| P | Game master panel |
| Y | Debug store |

## How to Run

### Prerequisites

- Java 8 or higher (Java 17 recommended)
- Game assets in the `res/` folder (sprites, audio, etc.)

### Option 1: Run from source (recommended for development)

**Windows**

```bat
run.bat
```

This script builds the project if needed, then launches the game with the correct classpath (`bin` + `res`).

To build without running:

```bat
build.bat
```

**Manual command**

```bash
javac -encoding UTF-8 -d bin -sourcepath src src/**/*.java
java -cp "bin;res" main.Main
```

On macOS/Linux, use `:` instead of `;` in the classpath:

```bash
java -cp "bin:res" main.Main
```

### Option 2: Run the JAR

If you have a `MonkeyBoy.jar` build with assets bundled:

```bash
java -jar MonkeyBoy.jar
```

Or double-click the JAR file.

### VS Code

Use the **Run Monkey Boy** launch configuration in `.vscode/launch.json`.

## Configuration

The game reads settings from `config.txt`:

```
Off    # Fullscreen (On/Off)
1      # Music volume
1      # Sound effects volume
```

## Items & Equipment

### Weapons

- **Normal Sword** — Basic melee weapon
- **Epic Sword** — Upgraded melee weapon
- **Axe** — Chops wood
- **Pickaxe** — Mining tool

### Shields

- **Wood Shield** — Basic defense
- **Blue Shield** — Enhanced defense
- **Boots** — Movement enhancement

### Consumables

- **Red Potion** — Health restoration
- **Mana Crystal** — Magic energy restoration
- **Heart** — Retore 1 health point
- **Sugar** — Quest item

### Magic & Tools

- **Fireball Book** — Learn fireball spell
- **Lantern** — Constant light source
- **Torch** — Portable light
- **Tent** — Rest and save point

## Bosses

### Slime Boss

- **Location:** Slime Boss Room
- **Abilities:** Spawns smaller slimes, area attacks
- **Strategy:** Use fireball magic and maintain distance

### Skeleton Lord

- **Location:** Skeleton Lord Room
- **Abilities:** Four phase battle, gets enraged after half health
- **Strategy:** Use guard to counter attacks

## Project Structure

```
Monkey Boy/
├── src/                        # Java source code
│   ├── main/                   # Core game systems
│   ├── entity/                 # Player, NPCs, and entity classes
│   ├── monsters/               # Enemy implementations
│   ├── objects/                # Item and object classes
│   ├── tile/                   # Tile and minimap management
│   ├── tile_interactive/       # Interactive tile objects
│   ├── environment/            # Lighting and environment effects
│   ├── data/                   # Save/load and progress tracking
│   ├── ai/                     # Pathfinding and AI
│   └── spellBook/              # Spell-related mini-games
├── res/                        # Game assets
│   └── Map/                    # Level data (.txt map files)
├── map editor/                 # Standalone map editor tool
├── build.bat                   # Compile all Java sources
├── run.bat                     # Build (if needed) and run the game
├── config.txt                  # Game configuration
└── README.md                   # This file
```

## Development Status

This is a personal demo project built over several months. The current source in this repository was recovered by decompiling a working JAR after original project files were lost, so some code may look unconventional (for example, CFR decompiler headers in source files).

The playable JAR build works fine. Restoring clean source and full asset folders in `res/` (sprites, audio, tiles) is ongoing work.

## Known Issues

- Some areas may have performance issues on older hardware
- Fullscreen mode may not work correctly on all systems
- Save files are stored locally and may be lost if the game directory is moved
- Building from source requires game assets to be present in `res/` beyond the included map data

Enjoy playing Monkey Boy! 🐒⚔️✨
