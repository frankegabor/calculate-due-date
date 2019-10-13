# Calculate due date

## Used technologies

| Goal                 | Technology |
| -------------------- | ---------- |
| Programming language | [Java SE  8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) |
| Testing framework    | [JUnit 5](https://junit.org/junit5/) |
| Testing toolkit      | [JMockit](https://jmockit.github.io) |
| Build tool           | [Maven](https://maven.apache.org/) |
| Code analyzer        | [PMD](https://pmd.github.io/) |
| Code coverage        | [JaCoCo](https://www.eclemma.org/jacoco/) |

## Usage

The module can be imported into other Maven module as dependency.

## Quality

Code quality is checked by PMD static code analyzer based on the self-defined rule set.
Code coverage is measured by JaCoCo. Gate is 95% branch coverage for all method.