package nl.suriani.code.is.text.is.data.application;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserRegistry {
    private final static Map<Integer, User> users = Map.of(
            -1, new User(-1, "Unknown", List.of()),
            0, new User(0, "Tristano", List.of(1, 8)),
            1, new User(1, "Barry", List.of(1, 7)),
            2, new User(2, "Laurens", List.of(1, 6)),
            3, new User(3, "Douwe", List.of(1, 4)),
            4, new User(4, "Giacomino", List.of(5))
    );

    public static User user(Integer id) {
        Objects.requireNonNull(id);
        return users.getOrDefault(id, users.get(-1));
    }

    public record User(Integer id, String name, List<Integer> authorisedFloors) {
        public User {
            Objects.requireNonNull(id);
            Objects.requireNonNull(name);
            Objects.requireNonNull(authorisedFloors);
        }

        public boolean isAuthorised(Integer floor) {
            Objects.requireNonNull(floor);
            return authorisedFloors.contains(floor);
        }
    }
}
