
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/wdd/semesterca/conf/routes
// @DATE:Tue Mar 13 12:25:57 GMT 2018


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
