package nl.suriani.code.is.text.is.data.application;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class Authorisations {
    private static Set<Authorisation> authorisedFloors = new ConcurrentSkipListSet<>(Set.of(
            new Authorisation(0, LocalDateTime.MAX),
            new Authorisation(2, LocalDateTime.MAX)
    ));

    private static Duration durationAuthorisation = Duration.ofSeconds(15);

    public static void checkIn(Integer userId) {
        UserRegistry.user(userId).authorisedFloors().stream()
        .map(floor -> new Authorisation(floor, LocalDateTime.now().plus(durationAuthorisation)))
                .forEach(authorisedFloors::add);
    }

    public static void cleanUp() {
        LocalDateTime now = LocalDateTime.now();
        authorisedFloors.removeIf(authorisation -> authorisation.expiration.isBefore(now));
    }

    public record Authorisation(Integer floor, LocalDateTime expiration) {
        public Authorisation {
            Objects.requireNonNull(floor);
        }

        public boolean isAuthorised() {
            return authorisedFloors.contains(floor);
        }
    }
}
