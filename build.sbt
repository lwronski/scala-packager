import Settings.project

inThisBuild(
  List(
    organization := "org.virtuslab",
    homepage := Some(url("https://github.com/VirtuslabRnD/scala-packager")),
    licenses := List(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers := List(
      Developer(
        "lwronski",
        "Łukasz Wroński",
        "",
        url("https://github.com/lwronski")
      )
    )
  )
)

lazy val coreDependencies = Seq(
  libraryDependencies ++= Seq(
    Deps.commonsIo,
    Deps.image4j,
    Deps.jib,
    Deps.osLib,
    Deps.thumbnailator
  )
)

lazy val testFramework = Seq(
  testFrameworks += new TestFramework("munit.Framework")
)

lazy val cliMainClass = Seq(
  Compile / mainClass := Some("packager.cli.PackagerCli")
)

lazy val compileOptions: Seq[Setting[_]] = Seq(
  scalacOptions ++= Seq("-Xfatal-warnings", "-deprecation")
)

lazy val packagerProjectSettings = Seq(
  name := "scala-packager",
  scalaVersion := ScalaVersions.scala213,
  crossScalaVersions := ScalaVersions.all
)

lazy val cliProjectSettings = Seq(
  name := "scala-packager-cli",
  scalaVersion := ScalaVersions.scala213,
  crossScalaVersions := ScalaVersions.all,
  libraryDependencies ++= Seq(Deps.caseApp)
)

lazy val utest: Seq[Setting[_]] = Seq(
  libraryDependencies ++= Seq(Deps.munit % Test, Deps.expecty % Test),
  testFrameworks += new TestFramework("munit.Framework")
)

lazy val cli = project("cli")
  .dependsOn(packager)
  .settings(
    cliProjectSettings,
    cliMainClass,
    utest,
    compileOptions
  )

lazy val packager = project("packager")
  .settings(
    packagerProjectSettings,
    coreDependencies,
    utest,
    compileOptions
  )
