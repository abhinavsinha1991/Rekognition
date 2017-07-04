
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/bharat/code-combat/Rekognition/conf/routes
// @DATE:Tue Jul 04 13:17:50 IST 2017


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
