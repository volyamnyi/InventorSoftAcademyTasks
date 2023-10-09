# Range Class

A Java class representing a range of elements of a generic type T. This class implements the `Set` interface and provides various operations for manipulating and querying the range using the Stream API approach.

## Table of Contents
- [Goals](#goals)
- [Introduction](#introduction)
- [Usage](#usage)
- [Methods](#methods)
- [Examples](#examples)
## Goals

You need to design collection which represents a range. It needs to support all number based classes. It should be based on collection framework’s Set interface. Float and double ranges should work with one digit prescission after comma. Custom types can be added via implementation of Comparable.

## Introduction

The `Range` class allows you to work with a range of elements of a generic type T, as long as T implements the `Comparable<T>` interface. You can perform various operations on the range, such as union, subtraction, checking if an element is within the range, and generating custom ranges.

## Usage

To use the `Range` class, you need to instantiate it with a start and end value. If the type of the start and end values is numeric (e.g., Integer, Double), you can generate a numeric range dynamically. Otherwise, you can create a custom range by adding `Comparable<T>` elements to it.

```Java
// Create a numeric range of integers from 1 to 10
Range<Integer> numericRange = new Range<>(1, 10);
numericRange.generateNumberRange();

// Create a custom range of strings
Range<String> customRange = new Range<>("A", "F");
customRange.generateCustomRange(Arrays.asList("B", "D", "E"));
```
## Methods
### subtract(Range<T> range): Subtract another range from this range and return a new Range object.
### unite(Range<T> range): Unite this range with another range and return a new Range object.
### generateCustomRange(Collection<? extends T> c): Generate a custom range based on the given collection of elements.
### generateNumberRange(): Generates a numeric range based on the type of the start value. It dynamically selects the appropriate range generation method for the numeric type.
### isBetween(T element): Checks if the range contains a specific element.
### isBefore(T element): Checks if the specific element is before the start value.
### isAfter(T element): Checks if the specific element is after the start value.
### add(T t): Adds an element to the range if it is within the start and end values.
### addAll(Collection<? extends T> c): Adds a collection of elements to the range if their values are within the start and end values.
### The rest methods have standard Set implementation.
## Examples
### The examples of usage of some methods:
``` Java
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
