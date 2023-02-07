package workflow86.technical;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * 
     * @param ModuleID
     * @return
     */
    public List<String> getModuleDependencies(String ModuleID)
    {
        return new ArrayList<String>();
    }
}
