
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

object welcome extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String, style: String = "java"):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.43*/("""

"""),format.raw/*3.1*/("""<html lang="en">
    <head>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*5.54*/routes/*5.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*5.101*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*6.59*/routes/*6.65*/.Assets.versioned("images/favicon.png")),format.raw/*6.104*/("""">

    <script src=""""),_display_(/*8.19*/routes/*8.25*/.Assets.versioned("javascripts/jquery-3.2.1.min.js")),format.raw/*8.77*/("""" type="text/javascript"></script>
    <script src=""""),_display_(/*9.19*/routes/*9.25*/.Assets.versioned("javascripts/moment.min.js")),format.raw/*9.71*/("""" type="text/javascript"></script>
    <script src=""""),_display_(/*10.19*/routes/*10.25*/.Assets.versioned("javascripts/jpeg_camera/jpeg_camera_with_dependencies.min.js")),format.raw/*10.106*/("""" type="text/javascript"></script>
    <script src=""""),_display_(/*11.19*/routes/*11.25*/.Assets.versioned("javascripts/faceapp.js")),format.raw/*11.68*/("""" type="text/javascript"></script>
    </head>
  <body>
    <h1>Welcome to AWS Rekog!</h1>
    <div id='camera'>
      <div id='placeholder'>
        <p>Your browser does not support a camera!</p>
      </div>
    </div>
    <br>
    <p>
      <button id='compare_image'>Compare Image</button>
    </p>
    <br>
    <input id='photo_id' placeholder='Enter name for photo' type='text'>
    <button id='add_to_collection'>Add To Collection</button>
    <br>
    <div id='upload_status'></div>
    <div id='upload_result'></div>
    <br>
    <img src=""""),_display_(/*31.16*/routes/*31.22*/.Assets.versioned("images/loading.gif")),format.raw/*31.61*/("""" height="100" id="loading_img"  width="100"/>
    <audio id='audio_speech' src='#'></audio>
    <br>
    <form id = "form1" action="/create" method = "GET">
    	<input id='collection_id' name="collection_id" placeholder="Enter name for collection to create" type="text">
   	<button type ="submit">Create Collection</button>
    </form>
    <form id = "form2" action="/delete" method = "GET">
    	<input id='collection_id_delete' name="collection_id" placeholder="Enter name for collection to delete" type="text">
	<button type ="submit">Delete Collection</button>
    </form>
    <br>
  </body>

</html>
"""))
      }
    }
  }

  def render(message:String,style:String): play.twirl.api.HtmlFormat.Appendable = apply(message,style)

  def f:((String,String) => play.twirl.api.HtmlFormat.Appendable) = (message,style) => apply(message,style)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Jul 04 13:14:16 IST 2017
                  SOURCE: /home/bharat/code-combat/Rekognition/app/views/welcome.scala.html
                  HASH: f902038803962a64e0fb6c0224e410cfe402a05e
                  MATRIX: 957->1|1093->42|1121->44|1228->125|1242->131|1304->172|1391->233|1405->239|1465->278|1513->300|1527->306|1599->358|1678->411|1692->417|1758->463|1838->516|1853->522|1956->603|2036->656|2051->662|2115->705|2692->1255|2707->1261|2767->1300
                  LINES: 28->1|33->1|35->3|37->5|37->5|37->5|38->6|38->6|38->6|40->8|40->8|40->8|41->9|41->9|41->9|42->10|42->10|42->10|43->11|43->11|43->11|63->31|63->31|63->31
                  -- GENERATED --
              */
          