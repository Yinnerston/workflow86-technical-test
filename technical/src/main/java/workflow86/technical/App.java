package workflow86.technical;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Nathan Yin technical challenge.
 *
 */
public class App 
{
    protected Map<String, List<String>> modules;
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    /**
     * Add a module and its direct dependencies.
     * @param ModuleID
     * @param dependencies
     */
    public void addModule(String ModuleID, List<String> dependencies)
    {
        this.modules.put(ModuleID, dependencies);
    }

    /**
     * Return Map of moduleIDs: String -> direct dependencies: List<String>
     * @return
     */
    public Map<String, List<String>> getModules()
    {
        return this.modules;
    }

    private ModuleDependencyPair getModuleDependencies(String ModuleID, ModuleDependencyPair deps)
    {
        // End condition: No dependencies

        // Check for cycles + Add module ID to history
            // Throw CyclicDependencyException on cycle
            // Push ModuleID onto stack history

        // Iterate through dependencies
            // Recursive step
            // Remove most-recently added duplicate dependencies
        // Add to ModuleID to dependencies if not already in it?
        // Pop ModuleID off of stack history
        // Return deps
        return deps;
    }

    /**
     * 
     * @param ModuleID
     * @return
     */
    public List<String> getModuleDependencies(String ModuleID)
    {
        List<String> deps = new ArrayList<String>();
        Stack<String> history = new Stack<String>();
        ModuleDependencyPair moduleDependences = new ModuleDependencyPair(deps, history);
        return getModuleDependencies(ModuleID, moduleDependences).getDependencies();
    }
}
