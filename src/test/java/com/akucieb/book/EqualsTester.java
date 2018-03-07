package com.akucieb.book;

import org.junit.Assert;

public class EqualsTester {
    private static final int ITERATIONS = 10;
    private final Object reference, equalReference, secondEqualReference, notEqualReference;

    public EqualsTester(Object reference, Object equalReference, Object secondEqualReference, Object notEqualReference) {
        this.reference = reference;
        this.equalReference = equalReference;
        this.notEqualReference = notEqualReference;
        this.secondEqualReference = secondEqualReference;
    }

    public void verify() {
        testConstancyHashCode();
        testEqualityOfHashCode();
        testIsReflexive();
        testIsSymmetric();
        testIsTransitive();
        testConstancyEquals();
        testNullIsNotEquals();
    }

    private void testNullIsNotEquals() {
        Assert.assertFalse(reference.equals(null));
        Assert.assertFalse(equalReference.equals(null));
        Assert.assertFalse(secondEqualReference.equals(null));
        Assert.assertFalse(notEqualReference.equals(null));
    }

    private void testConstancyEquals() {
        for (int i = 0; i < ITERATIONS; i++) {
            Assert.assertTrue(reference.equals(equalReference));
            Assert.assertTrue(reference.equals(secondEqualReference));
            Assert.assertFalse(reference.equals(notEqualReference));
        }
    }

    private void testIsTransitive() {
        Assert.assertTrue(reference.equals(equalReference));
        Assert.assertTrue(equalReference.equals(secondEqualReference));
        Assert.assertTrue(reference.equals(secondEqualReference));
    }

    private void testIsSymmetric() {
        Assert.assertTrue(reference.equals(equalReference));
        Assert.assertTrue(equalReference.equals(reference));

        Assert.assertFalse(reference.equals(notEqualReference));
        Assert.assertFalse(notEqualReference.equals(reference));
    }

    private void testIsReflexive() {
        Assert.assertTrue(reference.equals(reference));
        Assert.assertTrue(equalReference.equals(equalReference));
        Assert.assertTrue(notEqualReference.equals(notEqualReference));
    }

    private void testEqualityOfHashCode() {
        Assert.assertEquals(reference.hashCode(), equalReference.hashCode());
    }

    private void testConstancyHashCode() {
        int hashCode = reference.hashCode();
        for (int i = 0; i < ITERATIONS; i++) {
            Assert.assertEquals(hashCode, reference.hashCode());
        }
    }
}
