# Machine Field Manual

Every value below is part of the machine's public contract and is repeated on its in-game head.
The first release registers the machines and their recipes; powered processing GUIs land chapter by
chapter without renaming IDs or silently changing failure behavior.

| Machine | Tier | Draw | Buffer | Time | Input → Output | Hazard / containment | Safe shutdown |
|---|---:|---:|---:|---:|---|---|---|
| Carbyne Pulverizer | 1 | 256 J/t | 2 MJ | 16s | Carbon + alloy → precursor | Carbon dust / Industrial I | Stops on full output |
| Molecular Synthesizer | 2 | 1,024 J/t | 25 MJ | 30s | Precursor + graphene → nanocarbon | Nanites / Cleanroom II | Seals and purges batch |
| Nanocell Programmer | 2 | 2,048 J/t | 50 MJ | 45s | Nanocarbon + circuit → 2 cells | Logic instability / Cleanroom II | Quarantines firmware |
| Nanoforge | 3 | 4,096 J/t | 100 MJ | 60s | Cells + exotic alloy → suit part | Heat and nanites / Cleanroom III | SCRAM without consuming inputs |
| Armor Assembly Bench | 3 | 2,048 J/t | 80 MJ | 40s | Frame + ARC + modules → suit core | Stored charge / ownership lock | Rejects mixed generations |
| Palladium Coil Winder | 1 | 512 J/t | 5 MJ | 20s | Palladium + copper → coil | Fumes / Ventilation I | Safe idle |
| New Element Accelerator | 4 | 8,192 J/t | 250 MJ | 120s | Exotic lattice + singularity → ingot | Extreme heat / IV | ARC SCRAM + visual EMP |
| Gamma Cyclotron | 3 | 4,096 J/t | 120 MJ | 75s | Boss isotope + shield → charged isotope | Radiation / Gamma III | Seals isotope |
| Banner Stabilization Chamber | 4 | 6,144 J/t | 180 MJ | 90s | Isotope + genetics → controlled serum | Mutation / Gamma IV | Produces inert waste |
| Techno-Arcane Forge | 4 | 8,192 J/t | 300 MJ | 100s | ARC part + essence → Latverian part | Runic feedback / Ward IV | Grounds resonance |
| Cosmic Fragment Spectrometer | 5 | 16,384 J/t | 500 MJ | 180s | Fragment → domain signature | Exposure / V | Ejects sealed capsule |
| Singularity Growth Chamber | 6 | 32,768 J/t | 2 GJ | 300s | Signature + boss core → replica | Reality collapse / VI | Stasis, zero block damage |

## Required operating menu

Powered implementations expose the same seven controls: `Status`, `Power`, `Thermals`,
`Containment`, `Recipe`, `Modules`, and `Emergency Shutdown`. No machine may hide a required
coolant, catalyst, orientation, maintenance threshold or ownership rule outside the game.
