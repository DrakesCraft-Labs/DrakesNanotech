const machines=[
['Carbyne Pulverizer','Salvaged Servo','Nanocarbon Matrix','256 J/t · 16s'],
['Palladium Coil Winder','Salvaged Servo','Palladium Coil','512 J/t · 20s'],
['Molecular Synthesizer','Nanocarbon Matrix','Programmable Nanocell','1,024 J/t · 30s'],
['Nanocell Programmer','Nanocarbon Matrix','2× Programmable Nanocell','2,048 J/t · 45s'],
['Nanoforge','Programmable Nanocell','Nano-Servo Cluster','4,096 J/t · 60s'],
['Armor Assembly Bench','Nano-Servo Cluster','Mark L Nanocore','2,048 J/t · 40s'],
['New Element Accelerator','ARC Reactor Core','New Element Ingot','8,192 J/t · 120s'],
['Gamma Cyclotron','Gamma Isotope','Ross Energy Absorber','4,096 J/t · 75s'],
['Banner Stabilization Chamber','Gamma Isotope','Banner Stabilizer','6,144 J/t · 90s'],
['Techno-Arcane Forge','ARC Reactor Core','Latverian Core','8,192 J/t · 100s'],
['Cosmic Spectrometer','Cosmic Fragment','Cosmic Circuit','16,384 J/t · 180s'],
['Singularity Growth Chamber','Cosmic Circuit','Power Stone','32,768 J/t · 300s'],
['Stellar Swarm Fabricator','Programmable Nanocell','Dyson Swarm Segment','262,144 J/t · 240s'],
['Graviton Field Regulator','Graviton Coil','God-Prison Field Core','131,072 J/t · 180s']];
document.querySelector('#machineRows').innerHTML=machines.map(row=>`<tr>${row.map(cell=>`<td>${cell}</td>`).join('')}</tr>`).join('');
