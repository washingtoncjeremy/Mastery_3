package com.amazon.ata.types;

import java.math.BigDecimal;
import java.util.Objects;

public class Box extends Packaging{

    private BigDecimal length;

    /**
     * This packaging's smallest dimension.
     */
    private BigDecimal width;

    /**
     * This packaging's largest dimension.
     */
    private BigDecimal height;

    public Box(Material material, BigDecimal length, BigDecimal width, BigDecimal height) {
        super(material, length, width, height);
        this.length = length;
        this.height = height;
        this.width = width;

    }

    public BigDecimal getMass() {

        BigDecimal two = BigDecimal.valueOf(2);

        // For simplicity, we ignore overlapping flaps
        BigDecimal endsArea = length.multiply(width).multiply(two);
        BigDecimal shortSidesArea = length.multiply(height).multiply(two);
        BigDecimal longSidesArea = width.multiply(height).multiply(two);

        return endsArea.add(shortSidesArea).add(longSidesArea);

    }

    public boolean canFitItem(Item item) {
        return this.length.compareTo(item.getLength()) > 0 &&
                this.width.compareTo(item.getWidth()) > 0 &&
                this.height.compareTo(item.getHeight()) > 0;
    }


    public BigDecimal getLength() {
        return length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Box)) return false;
        if (!super.equals(o)) return false;
        Box box = (Box) o;
        return Objects.equals(getLength(), box.getLength()) && Objects.equals(getWidth(), box.getWidth()) && Objects.equals(getHeight(), box.getHeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLength(), getWidth(), getHeight());
    }
}
