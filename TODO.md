# Next TODOs

## Model rules
Rules must cover
* whether the pet can enter
* whether the pet can exit
* always, never or time-based (for both enter and exit)

## Model modes
Modes must cover
* name
* description
* rule

* a number of default modes must exist and cannot be overridden (which one?)
* a forced mode can be set to apply to all pets at once 
* a forced mode has a cardinality of 0..1

## Modify AddMicrochip to include a default mode (always enter, never exit) as active mode

## Modify RemoveMicrochip to remove the active mode for the related microchip

## Model active modes
* active modes are relationships between pets and modes (0..1 mode to 1..1 pet)

## Set forced mode use case
## Remove forced mode use case
## Set active mode use case