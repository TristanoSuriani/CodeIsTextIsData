package nl.suriani.code.is.text.is.data.adt;

public record Nothing() implements Expression<Nothing> {

    @Override
    public Nothing eval(Environment environment) {
        return this;
    }
}
