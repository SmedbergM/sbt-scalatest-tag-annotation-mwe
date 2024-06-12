# sbt-scalatest-tag-annotation-mwe
MWE for a bug in SBT/Scalatest running tests by tag annotation

## Summary

Scalatest is supposed to support test inclusion/exclusion using annotations.
One paradigmatic example of this is "slow" tests, which Scalatest provides a built-in annotation for skipping, `org.scalatest.tags.Slow`.
However, for mixed Java/Scala projects, this mechanism does not appear to be working as intended.
When using a custom `@E2ETest` annotation, Java suites are incorrectly included.

## Setup

The submodule `e2e-common` provides a `@TagAnnotation` interface `E2ETest`.
This interface is similar to the builtin `org.scalatest.tags.Slow`.

Two suites in the `service1` submodule are untagged.
These are meant to mimic unit tests.
They should pass when run.

The two remaining suites are tagged at the class level with `@E2ETest`.
These suites are intended to mimic end-to-end tests, and are currently written to fail.

In `build.sbt`, we have added the commandline args `-l smedbergm.mwe.e2e.E2ETest` to the Scalatest invocation.
This is supposed to exclude all suites and individual tests tagged with the indicated annotation.

## Expected vs Observed Behavior

1. `sbt service1/test`

### Expected result

* `Service1UnitSpec` and `Service1UnitTest` each run one (successful) test.
* All tests in `Service1E2ESpec` and `Service1E2ETest` are omitted.

### Actual result

* `Service1UnitSpec` and `Service1UnitTest` each run one (successful) test.
* `Service1E2ESpec` is reported, but no tests are run.
* `Service1E2ETest` is reported, and the test method `testService1E2E()` is run and fails.

2. `sbt service1/runE2e`

### Expected result

The expected result is the inverse of the expected result for the previous invocation:

* All tests in `Service1UnitSpec` and `Service1UnitTest` are omitted.
* `Service1E2ESpec` and `Service1E2ETest` each run one (failing) test.

### Observed result

The observed result is the inverse of the observed result for the previous invocation:

* `Service1UnitSpec` and `Service1UnitTest` are reported, but no tests are run.
* `Service1E2ESpec` runs one (failed) test.
* `Service1E2ETest` is reported, but no tests are run.
