ThisBuild / scalaVersion := "3.4.1"

val scalatestDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.2.18" % Test,
  "org.scalatestplus" %% "testng-7-5" % "3.2.17.0" % Test
)

lazy val runE2e = taskKey[Unit]("Run end-to-end tests")
lazy val e2eSettings = Seq(
  (Test / test) := {
    (Test / testOnly).toTask(" -- -l smedbergm.mwe.e2e.E2ETest").value
  },
  (Test / runE2e) := {
    (Test / testOnly).toTask(" -- -n smedbergm.mwe.e2e.E2ETest").value
  }
)

lazy val `e2e-common` = (project in file("e2e-common"))
  .settings(
    libraryDependencies ++= scalatestDependencies
  )

lazy val service1 = (project in file("service1"))
  .dependsOn(`e2e-common` % "compile->compile;test->test")
  .settings(e2eSettings)

lazy val root = (project in file("."))
  .aggregate(
    `e2e-common`,
    service1,
  )
