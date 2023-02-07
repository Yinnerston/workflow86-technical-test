# Running the technical
```
docker build -t nathan-technical .
docker run --mount type=bind,source="$(pwd)"/technical,target=/app nathan-technical
```

# Running tests
```
```

# Steps
- Setup maven dependencies https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

# Assumptions
- Repeated depenencies are not imported after the first import
- Modules can have duplicate dependencies but dependencies are only imported once at runtime
    - E.G. A: B*, C, D, B*
    - The first B* is imported and the rest
- ModuleID(s) can be any string and are unique