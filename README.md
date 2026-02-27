# Utilities for SDK Plugins

## Development

## Update dependencies

1. Update the `pom.xml` (or use the `autoupdate.py` script from the `core` repo)

2. Create JAR with included dependencies for testing before deploying:

```bash
mvn clean package
cp target/utilities-*.jar /some/other/location
```

### Deploy to maven

1. (First time only) Set up `m2/settings.xml` and GPG keys.
2. Increment `revision` in `pom.xml`.
3. Run: `mvn clean deploy -P deploy`.

