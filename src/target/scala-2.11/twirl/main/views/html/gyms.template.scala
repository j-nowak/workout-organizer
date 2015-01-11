
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
object gyms extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[List[Gym],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(gyms: List[Gym]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(/*3.2*/main("Workout organizer GYMS", "nav-gyms")/*3.44*/ {_display_(Seq[Any](format.raw/*3.46*/("""

	"""),format.raw/*5.2*/("""<div>
		 GYMS
	</div>
	
	<div class="gyms-container">
		<ul id="gyms-list">
			"""),_display_(/*11.5*/for(g <- gyms) yield /*11.19*/ {_display_(Seq[Any](format.raw/*11.21*/("""
				"""),format.raw/*12.5*/("""<li>
					<div class="g-name">
						"""),_display_(/*14.8*/g/*14.9*/.getName()),format.raw/*14.19*/("""
					"""),format.raw/*15.6*/("""</div>
					<div class="g-content">
						City: """),_display_(/*17.14*/g/*17.15*/.getAddress().getCity()),format.raw/*17.38*/(""" """),format.raw/*17.39*/("""<br>
						Street: """),_display_(/*18.16*/g/*18.17*/.getAddress().getStreet()),format.raw/*18.42*/(""" """),format.raw/*18.43*/("""<br>
						Rating: """),_display_(/*19.16*/g/*19.17*/.getRating()),format.raw/*19.29*/(""" """),format.raw/*19.30*/("""<br>
					</div>
				</li>
			""")))}),format.raw/*22.5*/("""
		"""),format.raw/*23.3*/("""</ul>
	</div>

""")))}))}
  }

  def render(gyms:List[Gym]): play.twirl.api.HtmlFormat.Appendable = apply(gyms)

  def f:((List[Gym]) => play.twirl.api.HtmlFormat.Appendable) = (gyms) => apply(gyms)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sun Jan 11 13:36:59 CET 2015
                  SOURCE: /home/jakub/workspace/workout-organizer/src/app/views/gyms.scala.html
                  HASH: ebebf1a81f7c055d88e42e6373d02254139fb559
                  MATRIX: 725->1|830->18|858->21|908->63|947->65|976->68|1082->148|1112->162|1152->164|1184->169|1248->207|1257->208|1288->218|1321->224|1397->273|1407->274|1451->297|1480->298|1527->318|1537->319|1583->344|1612->345|1659->365|1669->366|1702->378|1731->379|1792->410|1822->413
                  LINES: 26->1|29->1|31->3|31->3|31->3|33->5|39->11|39->11|39->11|40->12|42->14|42->14|42->14|43->15|45->17|45->17|45->17|45->17|46->18|46->18|46->18|46->18|47->19|47->19|47->19|47->19|50->22|51->23
                  -- GENERATED --
              */
          