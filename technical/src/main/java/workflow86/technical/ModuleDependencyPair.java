package workflow86.technical;

import java.util.List;
import java.util.Stack;

public class ModuleDependencyPair {
    private List<String> dependencies;
    private Stack<String> history;

    public List<String> getDependencies() {
        return dependencies;
    }

    public Stack<String> getHistory() {
        return history;
    }

    public void addDependency(String dep)
    {
        getDependencies().add(dep);
    }

    public String popHistory()
    {
        return getHistory().pop();
    }

    public void pushHistory(String ModuleID)
    {
        getHistory().push(ModuleID);
    }

    private void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    private void setHistory(Stack<String> history) {
        this.history = history;
    }

    public ModuleDependencyPair(List<String> dependencies, Stack<String> history)
    {
        setDependencies(dependencies);
        setHistory(history);
    }
}
