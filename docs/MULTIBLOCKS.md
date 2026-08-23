# Multiblock Field Manual

DrakesNanotech uses large infrastructure to turn tiny precision parts into endgame systems. A
controller must validate every required block, an empty working volume and the complete power
buffer before consuming ingredients. A failed validation never consumes a batch.

| Structure | Tier | Footprint | Controller | Required structure | Power | Output purpose | Safe shutdown |
|---|---:|---:|---|---|---|---|---|
| Sterile Nanite Cleanroom | 3 | 7x7x5 | Cleanroom Controller | 48 Cleanroom Walls, 4 filters, sealed door | 8,192 J/t; 250 MJ | Contamination-free nanocells | Seals the batch if any wall opens |
| New Element Accelerator Array | 5 | 11x5x5 | New Element Accelerator | 8 ARC Pylons, 24 Vacuum Rings, 2 beam dumps | 65,536 J/t; 4 GJ | One New Element lattice | Four-point SCRAM; no explosion |
| Gamma Containment Vault | 5 | 9x9x7 | Gamma Cyclotron | 96 Gamma Shielding Blocks, airlock, absorber crown | 32,768 J/t; 2 GJ | Stabilized gamma isotopes | Converts a failed batch into inert waste |
| Hulkbuster Assembly Gantry | 5 | 9x9x12 | Armor Assembly Bench | 4 cranes, 8 anchors, 16 servo columns | 16,384 J/t; 1 GJ | Heavy modular frames | Owner lock, volume scan and emergency brakes |
| Orbital Fabrication Silo | 6 | 13x13x15 | Nanoforge | 4 cleanroom decks, 12 buses, launch aperture | 131,072 J/t; 12 GJ | Mark LXXXV and War Machine systems | Aborts if an entity enters the assembly volume |
| Universal Infinity Forge | 6 | 17x17x9 | Infinity Forge Controller | 6 Focusing Rings, 12 pylons, Snap Matrix, dais | 524,288 J/t; 60 GJ | Filled universal gauntlets | Region scan, six-domain SCRAM, zero block damage |

## Construction contract

- Shapes are orientation-aware and must be validated from the controller face.
- Protection checks include the complete footprint and safety buffer, even in the builder's region.
- Processing never loads chunks and pauses if any required chunk unloads.
- Every dangerous chamber fails closed. No multiblock failure changes blocks or creates explosions.
- The Snap Calibration Matrix is a physical safety interlock: its target contract is Bukkit `Mob`
  only and explicitly excludes players, NPC markers, graves and protected-pet tags.
