package by.itacademy.database.dto;

import by.itacademy.database.entity.enam.Genre;
import lombok.Data;

@Data
public class CatalogFilterDto {

    private String name;
    private String genre;
    private String price;
    private String limit;
    private String page;

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre.isEmpty() ? null : Genre.valueOf(genre);
    }

    public Integer getPrice() {
        return price.isEmpty() ? null : Integer.valueOf(price);
    }

    public Integer getLimit() {
        return Integer.valueOf(limit);
    }

    public Integer getPage() {
        int number = Integer.valueOf(page);
        return number == 0 ? 0 : number - 1;
    }
}
