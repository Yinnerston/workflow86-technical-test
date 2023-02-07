package workflow86.technical;

import java.util.ArrayList;
import java.util.HashMap;
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

    public void initModules()   {
        this.modules = new HashMap<String, List<String>>();
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
    throws CyclicDependencyException
    {
        // End condition: No dependencies
        // Do not add duplicates to dependencies
        List<String> currentDepList = getModules().get(ModuleID);
        boolean isDuplicateDep = deps.getDependencies().contains(ModuleID);
        if (currentDepList == null)
        {
            if (!isDuplicateDep)    {
                deps.addDependency(ModuleID);
            }
            return deps;
        }
        // Check for cycles + Add module ID to history
        if (deps.getHistory().contains(ModuleID))   {
            throw new CyclicDependencyException(String.format("Cyclic Dependency getting Module %s with history %s", ModuleID, deps.getHistory().toString()));
        }
        // Push ModuleID onto stack history
        deps.pushHistory(ModuleID);

        // Iterate through dependencies of current ModuleID
        for (String nextDependencyID: currentDepList)
        {
            // Recursive step
            getModuleDependencies(nextDependencyID, deps);
        }
        // Add to ModuleID to dependencies if not already in it?
        if (!isDuplicateDep && !(deps.getHistory().size() == 1))
        {
            deps.addDependency(ModuleID);
        }
        // Pop ModuleID off of stack history
        assert ModuleID == deps.popHistory();
        // Return deps
        return deps;
    }

    /**
     * 
     * @param ModuleID
     * @return
     */
    public List<String> getModuleDependencies(String ModuleID)
    throws CyclicDependencyException
    {
        List<String> deps = new ArrayList<String>();
        Stack<String> history = new Stack<String>();
        ModuleDependencyPair moduleDependences = new ModuleDependencyPair(deps, history);
        return getModuleDependencies(ModuleID, moduleDependences).getDependencies();
    }
}
