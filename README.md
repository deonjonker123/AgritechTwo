![AT](https://raw.githubusercontent.com/deonjonker123/AgritechTwo/refs/heads/26.1.2/at_ban.png)

# Agritech (AT2)

Farming automation mod. Planters and raised beds grow your crops and trees for you.

## Automated Planters

Plant a seed or sapling, it grows by itself, drops go into whatever's underneath it. All 11 vanilla wood types.

## Cloche

Glass dome that attaches to a planter. Boosts growth speed and yield, stacks with fertilizer. Right-click to attach, shift-right-click empty-handed to take it back off. Break a cloched planter and you get both items back separately.

## Fertilizer

Bone meal works. Also supports Mystical Agriculture, Immersive Engineering, and Forbidden & Arcanus fertilizers. Pipe or hopper it into any side of the planter.

## Data-Driven

Nothing's hardcoded. Add/remove/edit seeds, soils, saplings, fertilizers through:

- **Datapacks** — regular JSON
- **KubeJS** — for scripted/server-side control

Means you can add support for unsupported mods, change growth modifiers, mess with fertilizer values, or override defaults you don't like.

## Mod Support

**Crops:** Mystical Agriculture & Mystical Agradditions, Farmer's Delight, Ars Nouveau, Silent Gear, Immersive Engineering, Occultism, Cobblemon, Pam's HarvestCraft 2, Actually Additions, Croptopia, The Aether II

**Trees:** Ars Nouveau & Ars Elemental, Forbidden & Arcanus, Integrated Dynamics, Silent Gear, Occultism, Cobblemon, Pam's HarvestCraft 2, Croptopia, EvilCraft, The Aether II

**Soils:** Mystical Agriculture farmland, Farmer's Delight soils, Just Dire Things goosoils, The Aether II

## Interactive Placement

- Right-click with seeds/saplings/soil to insert directly
- Right-click with a hoe to till compatible blocks
- Right-click vanilla farmland with mystical essence to convert it

## Raised Beds

Manual early-game version. All 12 wood types. Grows stuff automatically but drops harvest on the ground instead of storing it. Outdoor + sky access + daytime = growth bonus. No cloche or automation, fertilize by hand. JEI/Jade supported.

## Crates

54-slot storage block, 12 wood types. Auto-collects drops in a 5x5 area, toggleable in the GUI, configurable interval. Pipes can use any side. Also just works as normal storage if you don't care about the auto-collect.

## JEI

Shows valid crop/soil combos for planters and raised beds, with drop counts and chance %.

## Jade

Shows crop/sapling, growth stage and progress, soil type and its bonus, sunlight boost status, and cloche speed/yield.

## Note

Agritech is NOT related to Agritech: Evolved (ATE). Not an addon, not a successor, doesn't build on it. Two separate mods, similar name, same general idea, zero shared code.

Running both is fine but pointless — datapacks/KubeJS for one won't do anything for the other, different namespaces entirely.