public class Main {
    public static void main(String[] args) {
        // Create two ranges
        Range<Float> range1 = new Range<>(1.1f, 5.9f);
        range1.generateNumberRange();

        Range<Float> range2 = new Range<>(3.1f, 7.9f);
        range2.generateNumberRange();

        System.out.println("Start range1:");
        System.out.println(range1);
        System.out.println("End range1");

        System.out.println("Start range2:");
        System.out.println(range2);
        System.out.println("End range2");

        // Subtract range2 from range1
        Range<Float> subtractedRange = range1.subtract(range2);
        System.out.println("\nStart Subtracted range:");
        System.out.println(subtractedRange);
        System.out.println("End Subtracted range");

        // Unite range1 and range2
        Range<Float> unitedRange = range2.unite(range1);
        System.out.println("\nStart United range:");
        System.out.println(unitedRange);
        System.out.println("End United range");

        // Check if an element is in the range
        float f = 4.1f;
        System.out.println("\n" + f + " is in range:");
        boolean isInRange = range1.isBetween(f);
        System.out.println(isInRange);

        // Add elements to the range
        System.out.println(f + " is added: ");
        System.out.println(range2.add(f));

    }
}
