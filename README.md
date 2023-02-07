# Running the technical
```
docker build -t nathan-technical .
docker run --mount type=bind,source="$(pwd)"/technical,target=/app nathan-technical
```

# Running tests
```
docker build -t nathan-technical .
docker run --mount type=bind,source="$(pwd)"/technical,target=/app nathan-technical
```

# Steps
- Setup maven dependencies https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

# Assumptions
- Repeated depenencies are not imported after the first import
- Modules can have duplicate dependencies but dependencies are only imported once at runtime
    - E.G. A: B*, C, D, B*
    - The first B* is imported and the rest
- ModuleID(s) can be any string and are unique

# Errors in the technical challenge
- Example #2 had two errors
    - Data:
        - Module A: B, C, D 
        - Module C: E, F, G, E 
        - Module G: H, I 
        - Module I: D
    - It was written as getModuleDependencies(A) ==> {B,E,F,H,D,I,G,D}
    - D should not have been duplicated
    - Missing C in the output
    - THe correct output is {B,E,F,H,I,G,C,D}