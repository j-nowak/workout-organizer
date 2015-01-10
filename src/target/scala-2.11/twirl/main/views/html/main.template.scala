
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.32*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(/*6.17*/title),format.raw/*6.22*/("""</title>
        <link rel="stylesheet" media="screen" href="stylesheets/main.css">
        <link rel="shortcut icon" type="image/png" href="images/favicon.png">
    </head>
    <body>
    	 <header>
	    <div class="nav">
	      <ul>
	        <li class="home"><a class="active" href="#">Home</a></li>
	        <li class="trainings"><a href="#">Training</a></li>
	        <li class="exercises"><a href="#">Exercises</a></li>
	        <li class="gyms"><a href="#">Gyms</a></li>
	        <li class="account"><a href="#">Account</a></li>
	      </ul>
	    </div>
	  </header>
        """),_display_(/*22.10*/content),format.raw/*22.17*/("""
    """),format.raw/*23.5*/("""</body>
</html>"""))}
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sat Jan 10 14:04:16 CET 2015
                  SOURCE: /home/jakub/workspace/workout-organizer/src/app/views/main.scala.html
                  HASH: ffca505759a05702d165755ca993bf067468047a
                  MATRIX: 727->1|845->31|872->32|949->83|974->88|1583->670|1611->677|1643->682
                  LINES: 26->1|29->1|30->2|34->6|34->6|50->22|50->22|51->23
                  -- GENERATED --
              */
          