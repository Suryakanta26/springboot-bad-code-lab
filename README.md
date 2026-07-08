# Spring Boot Bad Code Lab

This is a dummy Spring Boot application. Code is written in such a way that errors should be found by static code analyzers such as SonarQube, Snyk, OWASP Dependency-Check, Checkmarx, Fortify, Semgrep, and similar tools.

Do not deploy this application. It intentionally contains vulnerable code and vulnerable dependencies for POC scanning.

## Intentionally bad examples

- Hardcoded secrets in Java classes.
- Vulnerable dependencies in `pom.xml`, including old Log4j, Jackson, Shiro, XStream, SnakeYAML, Commons Collections, Commons BeanUtils, Commons FileUpload, and H2 versions.
- SQL injection string concatenation.
- OS command injection through `Runtime.exec`.
- Spring Expression Language injection.
- Path traversal and arbitrary file read.
- Server-side request forgery.
- Open redirect.
- Unsafe Java deserialization.
- XML external entity parsing.
- Predictable random token generation.
- Weak MD5 hashing.
- Insecure cookies and permissive CORS.
