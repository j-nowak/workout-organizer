// @SOURCE:/home/jakub/workspace/workout-organizer/src/conf/routes
// @HASH:37741441f65dd5d8c3c5d7c3bd9268c557f0e23d
// @DATE:Mon Jan 05 19:58:41 CET 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:12
// @LINE:9
// @LINE:6
package controllers {

// @LINE:9
class ReverseSignController {


// @LINE:9
def registerUser(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "register")
}
                        

}
                          

// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:12
class ReverseAssets {


// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:12
def at(path:String, file:String): Call = {
   (path: @unchecked, file: @unchecked) match {
// @LINE:12
case (path, file) if path == "/public/html" && file == "login.html" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/html"), ("file", "login.html")))
  Call("GET", _prefix + { _defaultPrefix } + "login")
                                         
// @LINE:14
case (path, file) if path == "/public/stylesheets" && file == "login.css" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/stylesheets"), ("file", "login.css")))
  Call("GET", _prefix + { _defaultPrefix } + "assets/login.css")
                                         
// @LINE:15
case (path, file) if path == "/public/javascripts" && file == "login.js" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/javascripts"), ("file", "login.js")))
  Call("GET", _prefix + { _defaultPrefix } + "assets/login.js")
                                         
// @LINE:16
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
                  


// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:12
// @LINE:9
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:9
class ReverseSignController {


// @LINE:9
def registerUser : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SignController.registerUser",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "register"})
      }
   """
)
                        

}
              

// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:12
class ReverseAssets {


// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:12
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
        


// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:12
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:9
class ReverseSignController {


// @LINE:9
def registerUser(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SignController.registerUser(), HandlerDef(this.getClass.getClassLoader, "", "controllers.SignController", "registerUser", Seq(), "POST", """ Sign up and in""", _prefix + """register""")
)
                      

}
                          

// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:12
class ReverseAssets {


// @LINE:12
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
        
    