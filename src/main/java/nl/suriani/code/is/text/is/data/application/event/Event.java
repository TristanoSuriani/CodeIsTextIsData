package nl.suriani.code.is.text.is.data.application.event;

public sealed interface Event permits FloorAuthorised, FloorReached, NextFloorSelected, NoFloorSelected, UserCheckedIn {
}
