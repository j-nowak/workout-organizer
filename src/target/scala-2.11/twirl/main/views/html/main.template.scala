
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
object main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, activeNavBar: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.54*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(/*6.17*/title),format.raw/*6.22*/("""</title>
        <link rel="stylesheet" media="screen" href="stylesheets/main.css">
        <link rel="shortcut icon" type="image/png" href="images/favicon.png">
        
        <script type="text/javascript" src="javascripts/navigation.js"></script>
        <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", function(event) """),format.raw/*12.71*/("""{"""),format.raw/*12.72*/("""
        	"""),format.raw/*13.10*/("""activateNavbar(""""),_display_(/*13.27*/activeNavBar),format.raw/*13.39*/("""");
        """),format.raw/*14.9*/("""}"""),format.raw/*14.10*/(""");
        </script>        
    </head>
    <body>
    	 <header>
	    <div class="nav">
	      <ul>
	        <li ><a id="nav-home" href="/home">Home</a></li>
	        <li ><a id="nav-training" href="#">Training</a></li>
	        <li ><a id="nav-exercises" href="/exercises">Exercises</a></li>
	        <li ><a id="nav-gyms" href="#">Gyms</a></li>
	        <li ><a id="nav-account" href="#">Account</a></li>
	        <li ><a id="nav-logout" href="/logout">Logout</a></li>
	      </ul>
	    </div>
	  </header>
        """),_display_(/*30.10*/content),format.raw/*30.17*/("""
    """),format.raw/*31.5*/("""</body>
</html>"""))}
  }

  def render(title:String,activeNavBar:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title,activeNavBar)(content)

  def f:((String,String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title,activeNavBar) => (content) => apply(title,activeNavBar)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sat Jan 10 22:19:03 CET 2015
                  SOURCE: /home/jakub/workspace/workout-organizer/src/app/views/main.scala.html
                  HASH: b1536a34892fde4e63776702a08e41e03020bb5d
                  MATRIX: 734->1|874->53|901->54|978->105|1003->110|1393->472|1422->473|1460->483|1504->500|1537->512|1576->524|1605->525|2152->1045|2180->1052|2212->1057
                  LINES: 26->1|29->1|30->2|34->6|34->6|40->12|40->12|41->13|41->13|41->13|42->14|42->14|58->30|58->30|59->31
                  -- GENERATED --
              */
          