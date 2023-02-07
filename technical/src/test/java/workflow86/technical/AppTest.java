package workflow86.technical;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Technical Challenge
 */
public class AppTest 
{
    private App test_instance;


    /**
     * Setup test_instance as new App.
     */
    @Before
    protected void setUp()
    {
        test_instance = new App();
    } 
    /**
     * Test module dependencies where the dependencies are not order alphanumerically.
     * Dependencies retain the ordering defined in the module when not ordered alphanumerically.
     * Modules:
     * A: C, D, B
     * Module Dependencies:
     * A -> {C, D, B}
     */
    public void testModuleDependenciesNonAlphanumericOrdering()
    {
        // Define modules
        test_instance.addModule("A", Arrays.asList("C", "D", "B"));
        // Check that modules retain the same ordering as when they were first defined
        assertEquals(test_instance.getModuleDependencies("A"), Arrays.asList("C", "D", "B"));
    }

    /**
     * Test correct ordering of dependencies.
     * Test example one from the technical challenge.
     * Modules:
     * A: B, C, D
     * C: E, F, G
     * G: H, I
     * Module Dependencies:
     * G -> {H, I}
     * C -> {E, F, H, I, G}
     * A -> {B, E, F, H, I, G, C, D}
     */
    @Test
    public void testModuleDependenciesExampleOne()
    {
        // Define modules
        test_instance.addModule("A", Arrays.asList("B", "C", "D"));
        test_instance.addModule("C", Arrays.asList("E", "F", "G"));
        test_instance.addModule("G", Arrays.asList("H", "I"));
        // Test correct ordering of dependencies
        assertEquals(test_instance.getModuleDependencies("G"), Arrays.asList("H", "I"));
        assertEquals(test_instance.getModuleDependencies("C"), Arrays.asList("E", "F", "H", "I", "G"));
        assertEquals(test_instance.getModuleDependencies("A"), Arrays.asList("B", "E", "F", "H", "I", "G", "C", "D"));

    }

    /**
     * Test repeated dependencies are not resolved.
     * Test example two from the technical challenge.
     * Modules:
     * A: B, C, D
     * C: E, F, G, E
     * G: H, I
     * I: D
     * Module Dependencies:
     * G -> {H, D, I}
     * C -> {E, F, H, D, I, G}
     * A -> {B, E, F, H, D, I, G}
     */
    @Test
    public void testModuleDependenciesExampleTwo()
    {
        // Define modules
        test_instance.addModule("A", Arrays.asList("B", "C", "D"));
        test_instance.addModule("C", Arrays.asList("E", "F", "G"));
        test_instance.addModule("G", Arrays.asList("H", "I"));
        test_instance.addModule("I", Arrays.asList("D"));
        // Test repeated dependencies not resolved
        assertEquals(test_instance.getModuleDependencies("G"), Arrays.asList("H", "D", "I"));
        assertEquals(test_instance.getModuleDependencies("C"), Arrays.asList("E", "F", "H", "D", "I", "G"));
        assertEquals(test_instance.getModuleDependencies("A"), Arrays.asList("B", "E", "F", "H", "D", "I", "G"));
    }


    /**
     * Test cyclic dependency resolves as error for cyclic dependency of Module C.
     * Test example three from the technical challenge
     * Modules:
     * A: B, C, D
     * C: E, F, G, E
     * G: H, I
     * I: C
     * Module Dependencies:
     * G -> {H, D, I}
     * C -> CyclicDependencyException
     * A -> CyclicDependencyException
     */
    @Test(expected = CyclicDependencyException.class)
    public void testModuleDependenciesExampleThreeModuleC()
    {
        // Define modules
        test_instance.addModule("A", Arrays.asList("B", "C", "D"));
        test_instance.addModule("C", Arrays.asList("E", "F", "G"));
        test_instance.addModule("G", Arrays.asList("H", "I"));
        test_instance.addModule("I", Arrays.asList("D"));
        // Test cyclic dependency raises a CyclicDependencyException
        assertEquals(test_instance.getModuleDependencies("G"), Arrays.asList("H", "D", "I"));
        test_instance.getModuleDependencies("C");
    }

    /**
     * Test cyclic dependency resolves as error for cyclic dependency of Module A.
     * Test example three from the technical challenge
     * Modules:
     * A: B, C, D
     * C: E, F, G, E
     * G: H, I
     * I: C
     * Module Dependencies:
     * G -> {H, D, I}
     * C -> CyclicDependencyException
     * A -> CyclicDependencyException
     */
    @Test(expected = CyclicDependencyException.class)
    public void testModuleDependenciesExampleThreeModuleA()
    {
        // Define modules
        test_instance.addModule("A", Arrays.asList("B", "C", "D"));
        test_instance.addModule("C", Arrays.asList("E", "F", "G"));
        test_instance.addModule("G", Arrays.asList("H", "I"));
        test_instance.addModule("I", Arrays.asList("D"));
        // Test cyclic dependency raises a CyclicDependencyException
        assertEquals(test_instance.getModuleDependencies("G"), Arrays.asList("H", "D", "I"));
        test_instance.getModuleDependencies("A");
    }
}
