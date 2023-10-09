import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * A class representing a range of elements of a generic type T.
 * It implements the Set interface and provides various operations for manipulating and querying the range
 * using Stream API approach.
 *
 * @param <T> The type of elements in the range, which must implement Comparable<T>.
 */
public class Range<T extends Comparable<T>> implements Set<T> {
    private final TreeSet<T> range;
    private final T start;
    private final T end;

    public TreeSet<T> getRange() {
        return range;
    }

    public T getStart() {
        return start;
    }

    public T getEnd() {
        return end;
    }

    boolean isBetween(T element) {
        return element.compareTo(start) >= 0 && element.compareTo(end) <= 0;
    }

    boolean isBefore(T element) {
        return element.compareTo(start) < 0;
    }

    boolean isAfter(T element) {
        return element.compareTo(end) > 0;
    }

    /**
     * Check if the range is filled (contains more than 'start' and 'end' elements).
     */
    boolean isFilled() {
        return size() > 2;
    }

    /**
     * Subtract another range from this range and returns a new Range object, otherwise returns current range.
     */
    public Range<T> subtract(Range<T> range) {
        TreeSet<T> subtractedRange = getRange()
                .stream()
                .filter(r -> !range.contains(r))
                .collect(Collectors.toCollection(TreeSet::new));
        if (!subtractedRange.isEmpty())
            return new Range<>(subtractedRange.first(), subtractedRange.last(), subtractedRange);
        return this;
    }

    /**
     * Unite this range with another range and returns a new Range object.
     */
    public Range<T> unite(Range<T> range) {
        TreeSet<T> unitedRange = new TreeSet<>(getRange());
        unitedRange.addAll(range.getRange());
        return new Range<T>(unitedRange.first(), unitedRange.last(), unitedRange);
    }

    public Range(T start, T end) {
        this.start = start;
        this.end = end;
        range = new TreeSet<>();
        if (!(start instanceof Number)) {
            range.add(start);
            range.add(end);
        }

    }

    /**
     * Private constructor for creating a Range object with an existing range.
     */
    private Range(T start, T end, TreeSet<T> range) {
        this.start = start;
        this.end = end;
        this.range = range;
    }

    /**
     * Generate a custom range based on the given collection of elements.
     */
    public void generateCustomRange(Collection<? extends T> c) {
        addAll(c);
    }

    /**
     * Generates a numeric range based on the type of the 'start' value.
     * It dynamically selects the appropriate range generation method for the numeric type.
     * If the 'start' type is not numeric, it throws a runtime exception.
     *
     * @throws RuntimeException if the 'start' type is not numeric and the range cannot be automatically generated.
     */
    public void generateNumberRange() {
        if (start instanceof Double) {
            generateDoubleRange();
        } else if (start instanceof Float) {
            generateFloatRange();
        } else if (start instanceof Long) {
            generateLongRange();
        } else if (start instanceof Integer) {
            generateIntRange();
        } else if (start instanceof Short) {
            generateShortRange();
        } else if (start instanceof Byte) {
            generateByteRange();
        } else {
            throw new RuntimeException("The range is not numeric and cannot be automatically generated.");
        }

    }

    /**
     * Generates a range of double values starting from the truncated start value and increasing by 0.1 increments.
     */
    @SuppressWarnings("unchecked")
    private void generateDoubleRange() {
        double truncatedStart = BigDecimal.valueOf((Double) start)
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
        final double[] increment = {truncatedStart * 10};
        TreeSet<Double> range = Stream
                .generate(() -> {
                    double doubleValue = increment[0] / 10.0d;
                    increment[0]++;
                    return doubleValue;
                })
                .takeWhile(e -> e.compareTo((Double) end) <= 0)
                .collect(Collectors.toCollection(TreeSet::new));

        this.range.addAll((Collection<? extends T>) range);
    }

    /**
     * Generates a range of float values starting from the truncated start value and increasing by 0.1 increments.
     */
    @SuppressWarnings("unchecked")
    private void generateFloatRange() {
        float truncatedStart = BigDecimal.valueOf((Float) start)
                .setScale(1, RoundingMode.HALF_UP)
                .floatValue();
        final float[] increment = {truncatedStart * 10};
        TreeSet<Float> range = Stream
                .generate(() -> {
                    float floatValue = increment[0] / 10.0f;
                    increment[0]++;
                    return floatValue;
                })
                .takeWhile(e -> e.compareTo((Float) end) <= 0)
                .collect(Collectors.toCollection(TreeSet::new));

        this.range.addAll((Collection<? extends T>) range);
    }

    @SuppressWarnings("unchecked")
    private void generateLongRange() {
        TreeSet<Long> range = new TreeSet<>(LongStream
                .range((Long) start, (Long) end + 1)
                .boxed()
                .collect(Collectors.toSet()));

        this.range.addAll((Collection<? extends T>) range);
    }

    @SuppressWarnings("unchecked")
    private void generateIntRange() {
        TreeSet<Integer> range = new TreeSet<>(IntStream
                .range((Integer) start, (Integer) end + 1)
                .boxed()
                .collect(Collectors.toSet()));

        this.range.addAll((Collection<? extends T>) range);
    }

    @SuppressWarnings("unchecked")
    private void generateShortRange() {
        TreeSet<Short> range = new TreeSet<>();
        for (short i = (Short) start; i <= (Short) end; i++) {
            range.add(i);
        }

        this.range.addAll((Collection<? extends T>) range);
    }

    @SuppressWarnings("unchecked")
    private void generateByteRange() {
        TreeSet<Byte> range = new TreeSet<>();
        for (byte i = (Byte) start; i <= (Byte) end; i++) {
            range.add(i);
        }

        this.range.addAll((Collection<? extends T>) range);
    }


    @Override
    public int size() {
        return range.size();
    }

    @Override
    public boolean isEmpty() {
        return range.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return range.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return range.iterator();
    }

    @Override
    public Object[] toArray() {
        return range.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return range.toArray(a);
    }

    /**
     * Add an element to the range if it is within the start and end values
     */
    @Override
    public boolean add(T t) {
        if (isBetween(t))
            return range.add(t);
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return range.remove(o);

    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return range.containsAll(c);
    }

    /**
     * Add a collection of elements to the range if its values are within the start and end values
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.stream().allMatch(this::isBetween))
            return range.addAll(c);
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return range.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return range.removeAll(c);
    }

    @Override
    public void clear() {
        range.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Range<?> range1)) return false;

        if (!getRange().equals(range1.getRange())) return false;
        if (!getStart().equals(range1.getStart())) return false;
        return getEnd().equals(range1.getEnd());
    }

    @Override
    public int hashCode() {
        int result = getRange().hashCode();
        result = 31 * result + getStart().hashCode();
        result = 31 * result + getEnd().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Range{" +
                "range=" + range +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}