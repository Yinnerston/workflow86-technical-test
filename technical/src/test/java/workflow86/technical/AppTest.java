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
    public void setUp()
    {
        test_instance = new App();
        test_instance.initModules();
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
        assertEquals(Arrays.asList("C", "D", "B"), test_instance.getModuleDependencies("A"));
    }

    // /**
    //  * Test adding modules functionality is valid.
    //  * Test overwriting of module dependencies is idempotent.
    //  */
    // public void testAddModules()
    // {
    //     // Add some modules
    //     test_instance.addModule("A", Arrays.asList("B", "C", "D"));
    //     test_instance.addModule("X", Arrays.asList("E", "F", "G"));
    //     // Check mapping is correct
    //     Map<String, List<String>> correctMap = new HashMap<String, List<String>>();
    //     correctMap.put("A", Arrays.asList("B", "C", "D"));
    //     correctMap.put("X", Arrays.asList("E", "F", "G"));
    //     assertEquals(test_instance, correctMap);
    // }

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
        assertEquals(Arrays.asList("H", "I"), test_instance.getModuleDependencies("G"));
        assertEquals(Arrays.asList("E", "F", "H", "I", "G"), test_instance.getModuleDependencies("C"));
        assertEquals(Arrays.asList("B", "E", "F", "H", "I", "G", "C", "D"), test_instance.getModuleDependencies("A"));

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
        test_instance.addModule("C", Arrays.asList("E", "F", "G", "E"));
        test_instance.addModule("G", Arrays.asList("H", "I"));
        test_instance.addModule("I", Arrays.asList("D"));
        // Test repeated dependencies not resolved        
        assertEquals(Arrays.asList("H", "D", "I"), test_instance.getModuleDependencies("G"));
        assertEquals(Arrays.asList("E", "F", "H", "D", "I", "G"), test_instance.getModuleDependencies("C"));
        assertEquals(Arrays.asList("B", "E", "F", "H", "D", "I", "G", "C"), test_instance.getModuleDependencies("A"));
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
        test_instance.addModule("C", Arrays.asList("E", "F", "G", "E"));
        test_instance.addModule("G", Arrays.asList("H", "I"));
        test_instance.addModule("I", Arrays.asList("C"));
        // Test cyclic dependency raises a CyclicDependencyException
        assertEquals(Arrays.asList("H", "D", "I"), test_instance.getModuleDependencies("G"));
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
        test_instance.addModule("C", Arrays.asList("E", "F", "G", "E"));
        test_instance.addModule("G", Arrays.asList("H", "I"));
        test_instance.addModule("I", Arrays.asList("C"));
        // Test cyclic dependency raises a CyclicDependencyException
        assertEquals(Arrays.asList("H", "D", "I"), test_instance.getModuleDependencies("G"));
        test_instance.getModuleDependencies("A");
    }
}
