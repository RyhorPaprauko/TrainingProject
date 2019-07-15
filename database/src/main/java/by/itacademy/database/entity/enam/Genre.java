package by.itacademy.database.entity.enam;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genre {
    CLASSIC("Classic"),
    COMEDY("Comedy"),
    FANTASY("Fantasy");

    private String name;
}
