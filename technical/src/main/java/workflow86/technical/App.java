package workflow86.technical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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
        App instance = new App();
        instance.initModules();
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Type \"next\" to query module dependencies");
        // Use scanner to add all dependencies
        while (true)
        {
            System.out.println("Add Module. Format %s:%s, %s, ... , %s");
            String moduleDeps = inputScanner.nextLine();
            if (moduleDeps.toLowerCase().contains("next"))
            {
                break;
            }
            String[] dependency = moduleDeps.split(":", 2);
            String moduleID = dependency[0];
            // Strip whitespace
            String[] dependentModules = dependency[1].split(",", 0);
            for (int i = 0; i < dependentModules.length; i++){
                dependentModules[i] = dependentModules[i].trim();
            }
            instance.addModule(moduleID, Arrays.asList(dependentModules));
        }
        // Query dependencies
        System.out.println("Type \"next\" to query module dependencies");
        Map<String, List<String>> modules = instance.getModules();
        System.out.println(modules.toString());
        while (true)
        {
            System.out.println("Get dependencies for %s");
            String moduleID = inputScanner.nextLine();
            if (moduleID == "next")
            {
                break;
            }
            if (modules.containsKey(moduleID))
            {
                System.out.println(instance.getModuleDependencies(moduleID));
            } else
            {
                System.out.println(moduleID + " has no dependencies");
            }
        }
        inputScanner.close();
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
