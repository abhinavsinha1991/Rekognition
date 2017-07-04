
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
  def apply/*7.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.32*/("""

"""),format.raw/*9.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        """),format.raw/*12.62*/("""
        """),format.raw/*13.9*/("""<title>"""),_display_(/*13.17*/title),format.raw/*13.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*14.54*/routes/*14.60*/.Assets.versioned("stylesheets/camera.css")),format.raw/*14.103*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*15.59*/routes/*15.65*/.Assets.versioned("images/favicon.png")),format.raw/*15.104*/("""">
        <script src='https://code.jquery.com/jquery-3.1.1.min.js'></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment.min.js"></script>
        <script src=""""),_display_(/*18.23*/routes/*18.29*/.Assets.versioned("javascripts/faceapp.js")),format.raw/*18.72*/("""" type="text/javascript"></script>
        <script src=""""),_display_(/*19.23*/routes/*19.29*/.Assets.versioned("javascripts/jpeg_camera/jpeg_camera_with_dependencies.min.js")),format.raw/*19.110*/("""" type="text/javascript">
</script>
    </head>
    <body>
        """),format.raw/*24.32*/("""
        """),_display_(/*25.10*/content),format.raw/*25.17*/("""
    """),format.raw/*26.5*/("""</body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Jul 03 16:23:39 IST 2017
                  SOURCE: /home/bharat/code-combat/Rekognition/app/views/main.scala.html
                  HASH: 8da1b37553bbfb722106381f61899ed35e6a1dac
                  MATRIX: 1206->260|1331->290|1359->292|1439->397|1475->406|1510->414|1536->419|1625->481|1640->487|1705->530|1793->591|1808->597|1869->636|2099->839|2114->845|2178->888|2262->945|2277->951|2380->1032|2475->1189|2512->1199|2540->1206|2572->1211
                  LINES: 33->7|38->7|40->9|43->12|44->13|44->13|44->13|45->14|45->14|45->14|46->15|46->15|46->15|49->18|49->18|49->18|50->19|50->19|50->19|54->24|55->25|55->25|56->26
                  -- GENERATED --
              */
          