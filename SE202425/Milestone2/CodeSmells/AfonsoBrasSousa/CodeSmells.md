# 1. Primitive obsession

## Code snippet

In `java/com/sk89q/worldedit/math/transform/AffineTransform.java`:

```java
public record AffineTransform(
    // coefficients for x coordinate.
    double m00, double m01, double m02, double m03,
    // coefficients for y coordinate.
    double m10, double m11, double m12, double m13,
    // coefficients for z coordinate.
    double m20, double m21, double m22, double m23
) implements Transform {

// ...

    public AffineTransform() {
        // init to identity matrix
        this(
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0
        );
    }

// ...

@Override
    public AffineTransform inverse() {
        if (isIdentity()) {
            return this;
        }
        double det = this.determinant();
        return new AffineTransform(
                (m11 * m22 - m21 * m12) / det,
                (m21 * m02 - m01 * m22) / det,
                (m01 * m12 - m11 * m02) / det,
                (m01 * (m22 * m13 - m12 * m23) + m02 * (m11 * m23 - m21 * m13)
                        - m03 * (m11 * m22 - m21 * m12)) / det,
                (m20 * m12 - m10 * m22) / det,
                (m00 * m22 - m20 * m02) / det,
                (m10 * m02 - m00 * m12) / det,
                (m00 * (m12 * m23 - m22 * m13) - m02 * (m10 * m23 - m20 * m13)
                        + m03 * (m10 * m22 - m20 * m12)) / det,
                (m10 * m21 - m20 * m11) / det,
                (m20 * m01 - m00 * m21) / det,
                (m00 * m11 - m10 * m01) / det,
                (m00 * (m21 * m13 - m11 * m23) + m01 * (m10 * m23 - m20 * m13)
                        - m03 * (m10 * m21 - m20 * m11)) / det);
    }
```

## Code location

- **Package:** java/com/sk89q/worldedit/math/transform
- **Class:** AffineTransform

## Rationale

- The `AffineTransform` class uses a total of 12 primitive similarly named `double` variables to represent elements of a 3x4 matrix. It then uses each variable for a number of methods that are just generic matrix operations. This makes the code very unreadable and prone to mistakes such as swapped values or variables passed in the wrong order.

## Proposed refactoring

I would suggest refactoring out the parameters into a new `Matrix` class that can encapsulate these variables as well as apply general matrix operations that would work on matrices of any size and kind:

```java
public class Matrix {
    private final double[][] values;

    public Matrix(double[][] values) {
    // ...check if values is a properly formatted matrix...
    this.values = values;
    }

    public double get(int row, int col) {
        return values[row][col];
    }

    public isSquare() {//...}

    public inverse() {
        if (!isSquare()) throw new Exception;
        // ...calculate and return inverted matrix...
    }
```

This can then be used by `AffineTransform` and other classes that rely on matrix input and output:

```java
public record AffineTransform(Matrix matrix) implements Transform {
    
    public AffineTransform() {
        this(new Matrix(new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0}
        }));
    }

    public AffineTransform(Matrix matrix) {
        this.matrix = matrix;
    }

    public boolean isIdentity() {
        //...
    }

    public AffineTransform inverse() {
        return this.matrix.inverse()
    }

    // ...
}
```

# 2. Long parameter list

## Code snippet

In `java/com/sk89q/worldedit/math/transform/AffineTransform.java`:

```java
    /**
     * Returns the affine transform created by applying first the affine
     * transform given by the parameters, then this affine transform.
     *
     * @return the composition this * that
     */    public AffineTransform concatenate(double o00, double o01, double o02, double o03,
                                       double o10, double o11, double o12, double o13,
                                       double o20, double o21, double o22, double o23) {
        double n00 = m00 * o00 + m01 * o10 + m02 * o20;
        double n01 = m00 * o01 + m01 * o11 + m02 * o21;
        double n02 = m00 * o02 + m01 * o12 + m02 * o22;
        double n03 = m00 * o03 + m01 * o13 + m02 * o23 + m03;
        double n10 = m10 * o00 + m11 * o10 + m12 * o20;
        double n11 = m10 * o01 + m11 * o11 + m12 * o21;
        double n12 = m10 * o02 + m11 * o12 + m12 * o22;
        double n13 = m10 * o03 + m11 * o13 + m12 * o23 + m13;
        double n20 = m20 * o00 + m21 * o10 + m22 * o20;
        double n21 = m20 * o01 + m21 * o11 + m22 * o21;
        double n22 = m20 * o02 + m21 * o12 + m22 * o22;
        double n23 = m20 * o03 + m21 * o13 + m22 * o23 + m23;
        return new AffineTransform(
            n00, n01, n02, n03,
            n10, n11, n12, n13,
            n20, n21, n22, n23);
    }
// ...
```

## Code location

- **Package:** java/com/sk89q/worldedit/math/transform
- **Class:** AffineTransform

## Rationale

- I checked the Martin metrics for the math package as it had extremely high efferent coupling but zero abstraction and afferent coupling. This indicated that the package relies on specific, concrete implementations rather than abstract interfaces, which made it a prime candidate to look into for more specific code smells.

## Proposed refactoring

We can replace the passed parameters with a parameter object, for instance using the `Matrix` class we defined in [[# 1. Primivite obsession]]:

```java
public AffineTransform concatenate(AffineTransform other) {
        return new AffineTransform(this.matrix.multiply(other.matrix));
    }
```

# 3. Long method

## Code snippet

In `java/com/sk89q/worldedit/bukkit/adapter/impl/v1_20_R2/PaperweightDataConverters.java`:

```java
private void registerInspectors() {
        // dozens of lines of code similar to this:
        // ...
        registerEntityItemListEquipment("EntityArmorStand");
        registerEntityItemListEquipment("EntityBat");
        registerEntityItemListEquipment("EntityBlaze");
        registerEntityItemListEquipment("EntityCaveSpider");
        // ...
    }
```

## Code location

- **Package:** `com.sk89q.worldedit.bukkit.adapter.impl.v1_20_R2`
- **Class:** `PaperweightDataConverters`

## Rationale

- Sitting at 2803 lines of code, `PaperweightDataConverters.java` is a behemoth of a file that makes it a prime candidate to look into for code smells. A quick glance reveals numerous methods resembling the example above, filled with dozens of repetitive, nearly identical method calls. This excessive repetition significantly hampers maintainability and complicates the process of adding new features.

## Proposed refactoring

Refactor these into a list such as:

```java
private void registerInspectors() {
    List<String> equipment = Arrays.asList("EntityArmorStand", "EntityBat", "EntityBlaze", "EntityCaveSpider", //...
    );
    
    for(String e : equipment) {
         registerEntityItemListEquipment(e);
    }
    // ...
}
```

