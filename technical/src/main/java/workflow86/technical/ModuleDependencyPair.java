package workflow86.technical;

import java.util.List;
import java.util.Stack;

public class ModuleDependencyPair {
    private List<String> dependencies;
    // Immutable history
    private Stack<String> history;

    public List<String> getDependencies() {
        return dependencies;
    }
    public Stack<String> getHistory() {
        return history;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    public void setHistory(Stack<String> history) {
        this.history = history;
    }

    public ModuleDependencyPair(List<String> dependencies, Stack<String> history)
    {
        setDependencies(dependencies);
        setHistory(history);
    }
}
