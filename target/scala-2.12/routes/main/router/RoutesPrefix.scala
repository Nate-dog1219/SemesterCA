
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/brandon/Documents/SemesterCA/conf/routes
// @DATE:Tue Mar 20 01:00:15 GMT 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
