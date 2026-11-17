# actual-minecraft-mod

Mod for real Minecraft. A [Fabric](https://fabricmc.net/) mod for **Minecraft 26.3**.

## What it does

**Removes Sniffers and Allays.**

- Neither mob can exist in a world. That covers natural and structure spawns (pillager outpost cages, woodland mansions), spawn eggs, Sniffer eggs hatching, and `/summon`.
- Sniffers and Allays already in an existing world are deleted when their chunk loads.
- The Sniffer spawn egg, Allay spawn egg and Sniffer egg are hidden from the creative inventory.
- Sniffer eggs no longer come out of suspicious sand at warm ocean ruins.
- The advancements that need these mobs are removed, and the Sniffer is dropped from "Two by Two", so 100% completion is still possible.

> Note: Torchflower seeds and pitcher pods can only be found by Sniffers, so in survival you can no longer get them.

## Building

You need **JDK 25**.

```bash
./gradlew build
```

The mod jar ends up at `build/libs/actualmod-<version>.jar`. GitHub Actions also builds it on every push. Download it from the run's **Artifacts** section.

## Installing

1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 26.3.
2. Put [Fabric API](https://modrinth.com/mod/fabric-api) and `actualmod-<version>.jar` in your `mods` folder.

It has to be installed on the server (or in singleplayer). Clients connecting to a server that has it don't strictly need it.

## Developing

- `./gradlew runClient`: launch Minecraft with the mod
- `./gradlew runServer`: launch a dedicated server with the mod
- Versions live in `gradle.properties`. See https://fabricmc.net/develop for the latest ones.

Minecraft 26.x ships unobfuscated, so the code uses Mojang's own class names and no mappings are needed.

| Path | Contents |
| --- | --- |
| `src/main/java/net/actualmod/ActualMod.java` | Mod entrypoint. Blocks the entities and hides the items |
| `src/main/resources/fabric.mod.json` | Mod metadata |
| `src/main/resources/data/minecraft/` | Overrides for vanilla loot tables and advancements |
