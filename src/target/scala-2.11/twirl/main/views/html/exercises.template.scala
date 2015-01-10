
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
object exercises extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[List[Exercise],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(exercises: List[Exercise]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.29*/("""

"""),_display_(/*3.2*/main("Workout organizer EXERCISES", "nav-exercises")/*3.54*/ {_display_(Seq[Any](format.raw/*3.56*/("""

	"""),format.raw/*5.2*/("""<div>
		 <ul id="muslce-groups">
			<li><a href="#">Chest</a></li>
			<li><a href="#">Back</a></li>
			<li><a href="#">Shoulders</a></li>
			<li><a href="#">Bajceps</a></li>
			<li><a href="#">Triceps</a></li>
			<li><a href="#">ALL</a></li>
		 </ul>
	</div>
	
	<div class="exercises-container">
		<ul id="exercises-list">
			"""),_display_(/*18.5*/for(ex <- exercises) yield /*18.25*/ {_display_(Seq[Any](format.raw/*18.27*/("""
				"""),format.raw/*19.5*/("""<li>"""),_display_(/*19.10*/ex/*19.12*/.getName()),format.raw/*19.22*/("""</li>
			""")))}),format.raw/*20.5*/("""
		"""),format.raw/*21.3*/("""</ul>
	</div>
""")))}))}
  }

  def render(exercises:List[Exercise]): play.twirl.api.HtmlFormat.Appendable = apply(exercises)

  def f:((List[Exercise]) => play.twirl.api.HtmlFormat.Appendable) = (exercises) => apply(exercises)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sat Jan 10 17:52:38 CET 2015
                  SOURCE: /home/jakub/workspace/workout-organizer/src/app/views/exercises.scala.html
                  HASH: d3dcd1dd3425a7bf6d78481f73cd2233525abcde
                  MATRIX: 735->1|850->28|878->31|938->83|977->85|1006->88|1359->415|1395->435|1435->437|1467->442|1499->447|1510->449|1541->459|1581->469|1611->472
                  LINES: 26->1|29->1|31->3|31->3|31->3|33->5|46->18|46->18|46->18|47->19|47->19|47->19|47->19|48->20|49->21
                  -- GENERATED --
              */
          