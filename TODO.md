# Next TODOs

## Model rules
Rules must cover
* whether the pet can enter (v)
* whether the pet can exit (v)
* always, never or time-based (for both enter and exit) (v)

## Model modes
Modes must cover
* id
* description
* rule

* a number of default modes must exist and cannot be overridden (which one?) (v)
* a forced mode can be set to apply to all pets at once (v)
* a forced mode has a cardinality of 0..1 (v)

## Modify AddMicrochip to include a default mode (always enter, never exit) as active mode

## Modify RemoveMicrochip to remove the active mode for the related microchip (not needed)

## Model active modes
* active modes are relationships between pets and modes (0..1 mode to 1..1 pet) (done by using mode Id rather than relationships)

## Set forced mode use case
## Remove forced mode use case
## Set active mode use case