package workflow86.technical;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for Technical Challenge
 */
public class AppTest 
{
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
        //
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
        //
    }


    /**
     * Test cyclic dependency resolves as error.
     * Test example three from the technical challenge
     * Modules:
     * A: B, C, D
     * C: E, F, G, E
     * G: H, I
     * I: C
     * Module Dependencies:
     * G -> {H, D, I}
     * C -> CyclicDependencyError
     * A -> CyclicDependencyError
     */
    @Test
    public void testModuleDependenciesExampleThree()
    {
        //
    }



}
