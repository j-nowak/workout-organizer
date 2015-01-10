
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
				"""),format.raw/*19.5*/("""<li>
					<div class="ex-name">
						"""),_display_(/*21.8*/ex/*21.10*/.getName()),format.raw/*21.20*/("""
					"""),format.raw/*22.6*/("""</div>
					<div class="ex-content">
						Description: """),_display_(/*24.21*/ex/*24.23*/.getDescription()),format.raw/*24.40*/(""" """),format.raw/*24.41*/("""<br>
						Movie: """),_display_(/*25.15*/ex/*25.17*/.getMovieUri()),format.raw/*25.31*/(""" """),format.raw/*25.32*/("""<br>
						Rating: """),_display_(/*26.16*/ex/*26.18*/.getRating()),format.raw/*26.30*/(""" """),format.raw/*26.31*/("""<br>
					</div>
				</li>
			""")))}),format.raw/*29.5*/("""
		"""),format.raw/*30.3*/("""</ul>
	</div>
""")))}))}
  }

  def render(exercises:List[Exercise]): play.twirl.api.HtmlFormat.Appendable = apply(exercises)

  def f:((List[Exercise]) => play.twirl.api.HtmlFormat.Appendable) = (exercises) => apply(exercises)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sat Jan 10 21:58:47 CET 2015
                  SOURCE: /home/jakub/workspace/workout-organizer/src/app/views/exercises.scala.html
                  HASH: b1d9d9ff0b9249fd8d072506aa0ff92c4eba659e
                  MATRIX: 735->1|850->28|878->31|938->83|977->85|1006->88|1359->415|1395->435|1435->437|1467->442|1532->481|1543->483|1574->493|1607->499|1691->556|1702->558|1740->575|1769->576|1815->595|1826->597|1861->611|1890->612|1937->632|1948->634|1981->646|2010->647|2071->678|2101->681
                  LINES: 26->1|29->1|31->3|31->3|31->3|33->5|46->18|46->18|46->18|47->19|49->21|49->21|49->21|50->22|52->24|52->24|52->24|52->24|53->25|53->25|53->25|53->25|54->26|54->26|54->26|54->26|57->29|58->30
                  -- GENERATED --
              */
          