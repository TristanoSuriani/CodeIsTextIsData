package nl.suriani.code.is.text.is.data.application.command;

public sealed interface Command permits AuthoriseFloor, ReachFloor, SelectNextFloor, UserCheckIn {
}
