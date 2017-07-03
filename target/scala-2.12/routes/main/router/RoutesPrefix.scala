
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/abhinav/Desktop/Rekognition/conf/routes
// @DATE:Mon Jul 03 16:09:34 IST 2017


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
