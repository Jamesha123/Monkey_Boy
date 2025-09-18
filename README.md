
# Monkey Boy 🐒

A 2D Adventure Game built in Java featuring exploration, combat, magic, and quests in a pixel-art world.

## 🎮 Game Overview

Monkey Boy is a classic 2D top-down adventure game where you play as a young hero exploring various environments including villages, dungeons, and boss arenas. The game features real-time combat, magic spells, inventory management, and an engaging storyline with NPCs and quests.

## ✨ Features

### Core Gameplay
- **Real-time Combat System** - Attack, guard, and use magic spells
- **Magic System** - Cast fireballs and other spells with mana management
- **Inventory & Equipment** - Collect weapons, armor, potions, and consumables
- **Quest System** - Interact with NPCs and complete story objectives
- **Save/Load System** - Progress is automatically saved

### World & Exploration
- **Multiple Areas** - Explore villages, dungeons, world maps, and boss rooms
- **Interactive Environment** - Doors, chests, torches, and other interactive objects
- **Day/Night Cycle** - Dynamic lighting system with environmental effects
- **Mini-map** - Navigate with the built-in minimap system

### Combat & Enemies
- **Diverse Enemy Types**:
  - Slimes (Red, Green, Blue)
  - Orcs and Dungeon Orcs
  - Bats
  - Boss Monsters (Slime Boss, Skeleton Lord)
- **Boss Battles** - Epic encounters with unique mechanics
- **Projectile Combat** - Ranged attacks and magic spells

### Technical Features
- **60 FPS Gameplay** - Smooth performance with optimized rendering
- **Fullscreen Support** - Toggle between windowed and fullscreen modes
- **Debug Mode** - Developer tools for testing and debugging
- **Audio System** - Background music and sound effects
- **Particle Effects** - Visual feedback for combat and magic

## 🎯 Game Areas

The game includes 15 different maps:
- **Bedroom** - Starting area
- **Downstairs** - Home interior
- **Village** - Main hub with NPCs and shops
- **Store** - Shop for equipment and items
- **Wizard Home** - Magic-related content
- **World Maps** (5 different areas) - Overworld exploration
- **Dungeons** (2 levels) - Dangerous underground areas
- **Boss Rooms** - Slime Boss and Skeleton Lord arenas

## 🕹️ Controls

### Movement
- **Arrow Keys** or **WASD** - Move character
- **Enter** - Interact with objects/NPCs

### Combat
- **Enter** - Attack
- **Space** - Guard/Block
- **F** - Cast magic spell (Fireball)

### Game Systems
- **C** - Open inventory/character screen
- **V** - Drop Item
- **M** - Toggle Map
- **X** - Toggle minimap
- **Esc** - Open options menu

### Debug (if enabled)
- **F1** - Toggle debug mode
- **G** - God mode
- **T** - Freeze enemies
- **U** - Toggle collision 
- **I** - Show collision
- **O** - Toggle darkness filter
- **P** - Game master panel 
- **Y** - Debug Store

## 🚀 How to Run

### Prerequisites
- Java 8 or higher installed on your system

### Running the Game
1. Download the `MonkeyBoy.jar` file
2. Double-click the JAR file to run, or use command line:
   ```bash
   java -jar MonkeyBoy.jar
   ```

### Configuration
The game uses a `config.txt` file for settings:
- Fullscreen mode toggle
- Volume settings
- Other game preferences

## 🎒 Items & Equipment

### Weapons
- **Normal Sword** - Basic melee weapon
- **Epic Sword** - Upgraded melee weapon
- **Axe** - Alternative melee weapon
- **Pickaxe** - Mining tool

### Armor & Shields
- **Wood Shield** - Basic defense
- **Blue Shield** - Enhanced defense
- **Boots** - Movement enhancement

### Consumables
- **Red Potion** - Health restoration
- **Mana Crystal** - Magic energy restoration
- **Heart** - Permanent health increase
- **Sugar** - Quest item

### Magic & Tools
- **Fireball Book** - Learn fireball spell
- **Lantern** - Light source
- **Torch** - Portable light
- **Tent** - Rest and save point

## 🏆 Bosses

### Slime Boss
- **Location**: Slime Boss Room
- **Abilities**: Spawns smaller slimes, area attacks
- **Strategy**: Use fireball magic and maintain distance

### Skeleton Lord
- **Location**: Skeleton Lord Room  
- **Abilities**: Two-phase battle, powerful melee attacks
- **Strategy**: Learn attack patterns, use guard effectively

## 🛠️ Technical Details

- **Engine**: Custom Java 2D game engine
- **Graphics**: Pixel art sprites with 16x16 base tiles scaled to 48x48
- **Resolution**: 960x576 pixels (20x12 tiles)
- **Audio**: WAV/FLAC format sound effects and music
- **Performance**: 60 FPS target with optimized rendering pipeline

## 📁 Project Structure

```
Monkey Boy/
├── src/                    # Java source code
│   ├── main/              # Core game systems
│   ├── entity/            # Player and entity classes
│   ├── monsters/          # Enemy implementations
│   ├── objects/           # Item and object classes
│   ├── tile/              # Tile management
│   ├── data/              # Save/load and progress
│   └── ai/                # Pathfinding and AI
├── res/                   # Game assets
│   ├── Player/            # Player sprites
│   ├── Monster/           # Enemy sprites
│   ├── NPC/               # Character sprites
│   ├── Tiles/             # Environment tiles
│   ├── Sound/             # Audio files
│   └── Map/               # Level data
├── MonkeyBoy.jar          # Executable game file
├── config.txt             # Game configuration
└── README.md              # This file
```

## 🎨 Art & Audio Credits

The game features custom pixel art sprites and original audio. All assets are designed specifically for this project.

## 🐛 Known Issues

- Some areas may have performance issues on older hardware
- Fullscreen mode may not work correctly on all systems
- Save files are stored locally and may be lost if the game directory is moved
- Files got deleted. That's what I get for trying to use AI to create a jar executable quick. 
Tried to decompile from the jar file and get source files back. That is why it looks funky. 
It is a demo game but it took me months of work to do though. 
Maybe I will try to fix when I have time. Jar executable of the game works fine.

## 📝 License

This is a personal project. All rights reserved.

---

**Enjoy playing Monkey Boy!** 🐒⚔️✨
