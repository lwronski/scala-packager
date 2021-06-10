package packager

object Util {
  lazy val echoLauncher: os.Path = {
    val tmpDir = os.temp.dir(prefix = "scala-packager-tests")
    val dest = tmpDir / "echo"
    os.proc("cs", "bootstrap", "-o", dest.toString, "echo").call()
    dest
  }
}
