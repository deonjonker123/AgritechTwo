# Changelog

All notable changes to this project will be documented here.

## [1.1.5.2+neoforge-mc-26.1.2] - 2026-05-24
### Added
- Japanese translation (ja_jp) - thanks hamu6251ren0725-hue!

## [1.1.5.3+neoforge-mc-26.1.2] - 2026-05-24
### Bug fix
- Fixed shift-clicking items into a crate deleting them instead of merging with an existing stack of the same item.

## [1.1.5.4+neoforge-mc-26.1.2] - 2026-05-26
### Fixed
- Fallback recipe for planters, raised beds and crates

## [1.1.5.5+neoforge-mc-26.1.2] - 2026-06-04
### Maint
- Version bump

## [1.2.0+neoforge-mc26.1.2] - 2026-06-12
### Changed
- Compatibility layer is now fully data-driven. Seeds, saplings, soils, fertilizers and their values are defined via recipes and NeoForge datamaps — fully packdev-accessible via datapacks or KubeJS.
### Added
- Tooltip injection on all valid soil blocks and fertilizers showing their planter growth modifiers

## [1.2.1+neoforge-mc26.1.2] - 2026-06-14
### Performance
- Planters and raised beds no longer scan the full recipe list every tick. Recipe lookups are now cached per seed item and invalidated on datapack reload, reducing server tick time significantly at scale
- Valid soil items are now cached per datapack revision, eliminating repeated recipe scans on inventory interaction
- Block update packets now only fire on growth stage change instead of every second per planter
- Output item transfer now runs only on harvest instead of every tick

## [1.2.2+neoforge-mc26.1.2] - 2026-06-16
### Added
- New compat API for planters and raised beds: `PlanterProcessingTimeEvent`, `PlanterPreHarvestEvent`, `PlanterPostHarvestEvent` (in `com.misterd.agritechtwo.integration`). Allows other mods to read/modify seed stats, override growth time, and adjust harvest output via NeoForge events. Fired by the planter and raised beds
- `PlanterPreHarvestEvent` writes the returned seed back into the planter's seed slot, allowing persistent stat changes on the planted seed itself without needing to cycle harvested seeds back through the output
### Removed
- Removed the `/agritechtwo reload` command as it no longer makes sense nor works with the new data-driven approach

## [1.2.2.1+neoforge-mc26.1.2] - 2026-06-16
### Fixed
- Fixed data components (NBT) being stripped from items when extracted through menus or scaled through yield modifiers

## [2.2.2.2+neoforge-mc26.1.2] - 2026-06-24
### Fixed
-  Made it so MA farmlands can't be downgraded in the planter

## [2.2.2.3+neoforge-mc26.1.2] - 2026-07-07
### Added
-  Silent's Gems, Biomes O' Plenty, Oh The Biomes We've Gone, and Regions Unexplored compatibility