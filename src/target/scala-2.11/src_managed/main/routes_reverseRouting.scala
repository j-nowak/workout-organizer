// @SOURCE:/home/jakub/workspace/workout-organizer/src/conf/routes
// @HASH:27b53f23cf40c31efd4baf4a6db25ea61c1f63b0
// @DATE:Sat Jan 10 15:49:27 CET 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:6
package controllers {

// @LINE:15
// @LINE:14
// @LINE:13
class ReverseSignController {


// @LINE:13
def registerUser(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "register")
}
                        

// @LINE:14
def loginUser(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "signin")
}
                        

// @LINE:15
def logout(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "logout")
}
                        

}
                          

// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:12
class ReverseAssets {


// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:12
def at(path:String, file:String): Call = {
   (path: @unchecked, file: @unchecked) match {
// @LINE:12
case (path, file) if path == "/public/html" && file == "login.html" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/html"), ("file", "login.html")))
  Call("GET", _prefix + { _defaultPrefix } + "login")
                                         
// @LINE:18
case (path, file) if path == "/public" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
  Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
                                         
// @LINE:20
case (path, file) if path == "/public/javascripts" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/javascripts")))
  Call("GET", _prefix + { _defaultPrefix } + "javascripts/" + implicitly[PathBindable[String]].unbind("file", file))
                                         
// @LINE:21
case (path, file) if path == "/public/images" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/images")))
  Call("GET", _prefix + { _defaultPrefix } + "images/" + implicitly[PathBindable[String]].unbind("file", file))
                                         
// @LINE:22
case (path, file) if path == "/public/stylesheets" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/stylesheets")))
  Call("GET", _prefix + { _defaultPrefix } + "stylesheets/" + implicitly[PathBindable[String]].unbind("file", file))
                                         
   }
}
                                                

}
                          

// @LINE:9
// @LINE:6
class ReverseApplication {


// @LINE:9
def listAllExercises(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "exercises")
}
                        

// @LINE:6
def home(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "home")
}
                        

}
                          
}
                  


// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:15
// @LINE:14
// @LINE:13
class ReverseSignController {


// @LINE:13
def registerUser : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SignController.registerUser",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "register"})
      }
   """
)
                        

// @LINE:14
def loginUser : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SignController.loginUser",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "signin"})
      }
   """
)
                        

// @LINE:15
def logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SignController.logout",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
      }
   """
)
                        

}
              

// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:12
class ReverseAssets {


// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:12
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(path, file) {
      if (path == """ + implicitly[JavascriptLitteral[String]].to("/public/html") + """ && file == """ + implicitly[JavascriptLitteral[String]].to("login.html") + """) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
      if (path == """ + implicitly[JavascriptLitteral[String]].to("/public") + """) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
      if (path == """ + implicitly[JavascriptLitteral[String]].to("/public/javascripts") + """) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "javascripts/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
      if (path == """ + implicitly[JavascriptLitteral[String]].to("/public/images") + """) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "images/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
      if (path == """ + implicitly[JavascriptLitteral[String]].to("/public/stylesheets") + """) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "stylesheets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
      }
   """
)
                        

}
              

// @LINE:9
// @LINE:6
class ReverseApplication {


// @LINE:9
def listAllExercises : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.listAllExercises",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "exercises"})
      }
   """
)
                        

// @LINE:6
def home : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.home",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "home"})
      }
   """
)
                        

}
              
}
        


// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:15
// @LINE:14
// @LINE:13
class ReverseSignController {


// @LINE:13
def registerUser(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SignController.registerUser(), HandlerDef(this.getClass.getClassLoader, "", "controllers.SignController", "registerUser", Seq(), "POST", """""", _prefix + """register""")
)
                      

// @LINE:14
def loginUser(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SignController.loginUser(), HandlerDef(this.getClass.getClassLoader, "", "controllers.SignController", "loginUser", Seq(), "POST", """""", _prefix + """signin""")
)
                      

// @LINE:15
def logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SignController.logout(), HandlerDef(this.getClass.getClassLoader, "", "controllers.SignController", "logout", Seq(), "GET", """""", _prefix + """logout""")
)
                      

}
                          

// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:12
class ReverseAssets {


// @LINE:12
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Sign up and in""", _prefix + """login""")
)
                      

}
                          

// @LINE:9
// @LINE:6
class ReverseApplication {


// @LINE:9
def listAllExercises(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.listAllExercises(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "listAllExercises", Seq(), "GET", """ Exercises""", _prefix + """exercises""")
)
                      

// @LINE:6
def home(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.home(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "home", Seq(), "GET", """ Home page""", _prefix + """home""")
)
                      

}
                          
}
        
    