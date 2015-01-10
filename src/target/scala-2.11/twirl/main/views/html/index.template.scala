
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
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(username: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.20*/("""

"""),_display_(/*3.2*/main("Workout organizer HOME")/*3.32*/ {_display_(Seq[Any](format.raw/*3.34*/("""

  """),format.raw/*5.3*/("""<h1>Welcome """),_display_(/*5.16*/username),format.raw/*5.24*/("""!</h1>

""")))}))}
  }

  def render(username:String): play.twirl.api.HtmlFormat.Appendable = apply(username)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (username) => apply(username)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sat Jan 10 14:01:10 CET 2015
                  SOURCE: /home/jakub/workspace/workout-organizer/src/app/views/index.scala.html
                  HASH: 45ed363240ee8bc9e1c6a366b5700228920438d1
                  MATRIX: 723->1|829->19|857->22|895->52|934->54|964->58|1003->71|1031->79
                  LINES: 26->1|29->1|31->3|31->3|31->3|33->5|33->5|33->5
                  -- GENERATED --
              */
          