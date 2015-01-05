// @SOURCE:/home/jakub/workspace/workout-organizer/src/conf/routes
// @HASH:45344d42a625475db42c822bf50b3667a459c446
// @DATE:Mon Jan 05 18:28:17 CET 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:6
package controllers {

// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
class ReverseAssets {


// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
def at(path:String, file:String): Call = {
   (path: @unchecked, file: @unchecked) match {
// @LINE:9
case (path, file) if path == "/public/html" && file == "login.html" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/html"), ("file", "login.html")))
  Call("GET", _prefix + { _defaultPrefix } + "login")
                                         
// @LINE:11
case (path, file) if path == "/public/stylesheets" && file == "login.css" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/stylesheets"), ("file", "login.css")))
  Call("GET", _prefix + { _defaultPrefix } + "assets/login.css")
                                         
// @LINE:12
case (path, file) if path == "/public/javascripts" && file == "login.js" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/javascripts"), ("file", "login.js")))
  Call("GET", _prefix + { _defaultPrefix } + "assets/login.js")
                                         
// @LINE:13
case (path, file) if path == "/public" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
  Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
                                         
   }
}
                                                

}
                          

// @LINE:6
class ReverseApplication {


// @LINE:6
def login(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

}
                          
}
                  


// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
class ReverseAssets {


// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(path, file) {
      if (path == """ + implicitly[JavascriptLitteral[String]].to("/public/html") + """ && file == """ + implicitly[JavascriptLitteral[String]].to("login.html") + """) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
      if (path == """ + implicitly[JavascriptLitteral[String]].to("/public/stylesheets") + """ && file == """ + implicitly[JavascriptLitteral[String]].to("login.css") + """) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/login.css"})
      }
      if (path == """ + implicitly[JavascriptLitteral[String]].to("/public/javascripts") + """ && file == """ + implicitly[JavascriptLitteral[String]].to("login.js") + """) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/login.js"})
      }
      if (path == """ + implicitly[JavascriptLitteral[String]].to("/public") + """) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
      }
   """
)
                        

}
              

// @LINE:6
class ReverseApplication {


// @LINE:6
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

}
              
}
        


// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
class ReverseAssets {


// @LINE:9
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """login""")
)
                      

}
                          

// @LINE:6
class ReverseApplication {


// @LINE:6
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.login(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "login", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

}
                          
}
        
    