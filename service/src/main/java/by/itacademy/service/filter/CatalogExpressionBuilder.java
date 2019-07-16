package by.itacademy.service.filter;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.function.Function;

@Getter
@NoArgsConstructor
public class CatalogExpressionBuilder {

    private BooleanExpression expression;

    public <V> void add(V value, Function<V, BooleanExpression> function) {
        if (Objects.nonNull(value) && !isEmptyIfString(value)) {
            expression = Objects.isNull(expression) ? function.apply(value) : expression.and(function.apply(value));
        }
    }

    private <V> boolean isEmptyIfString(V value) {
        return value instanceof String && ((String) value).isEmpty();
    }
}
