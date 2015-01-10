// @SOURCE:/home/jakub/workspace/workout-organizer/src/conf/routes
// @HASH:44d5803e1b3a2f0853e39c01e1cf8243491812b1
// @DATE:Sat Jan 10 13:50:03 CET 2015

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
// @LINE:17
// @LINE:16
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:11
// @LINE:10
// @LINE:9
class ReverseSignController {


// @LINE:9
def registerUser(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "register")
}
                        

// @LINE:10
def loginUser(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "signin")
}
                        

// @LINE:11
def logout(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "logout")
}
                        

}
                          

// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:14
class ReverseAssets {


// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:14
def at(path:String, file:String): Call = {
   (path: @unchecked, file: @unchecked) match {
// @LINE:14
case (path, file) if path == "/public/html" && file == "login.html" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/html"), ("file", "login.html")))
  Call("GET", _prefix + { _defaultPrefix } + "login")
                                         
// @LINE:16
case (path, file) if path == "/public/stylesheets" && file == "login.css" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/stylesheets"), ("file", "login.css")))
  Call("GET", _prefix + { _defaultPrefix } + "assets/login.css")
                                         
// @LINE:17
case (path, file) if path == "/public/javascripts" && file == "login.js" =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/javascripts"), ("file", "login.js")))
  Call("GET", _prefix + { _defaultPrefix } + "assets/login.js")
                                         
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
                          

// @LINE:6
class ReverseApplication {


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
// @LINE:17
// @LINE:16
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:11
// @LINE:10
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
                        

// @LINE:10
def loginUser : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SignController.loginUser",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "signin"})
      }
   """
)
                        

// @LINE:11
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
// @LINE:17
// @LINE:16
// @LINE:14
class ReverseAssets {


// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:14
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
              

// @LINE:6
class ReverseApplication {


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
// @LINE:17
// @LINE:16
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:11
// @LINE:10
// @LINE:9
class ReverseSignController {


// @LINE:9
def registerUser(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SignController.registerUser(), HandlerDef(this.getClass.getClassLoader, "", "controllers.SignController", "registerUser", Seq(), "POST", """ Sign up and in""", _prefix + """register""")
)
                      

// @LINE:10
def loginUser(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SignController.loginUser(), HandlerDef(this.getClass.getClassLoader, "", "controllers.SignController", "loginUser", Seq(), "POST", """""", _prefix + """signin""")
)
                      

// @LINE:11
def logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SignController.logout(), HandlerDef(this.getClass.getClassLoader, "", "controllers.SignController", "logout", Seq(), "GET", """""", _prefix + """logout""")
)
                      

}
                          

// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:14
class ReverseAssets {


// @LINE:14
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """login""")
)
                      

}
                          

// @LINE:6
class ReverseApplication {


// @LINE:6
def home(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.home(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "home", Seq(), "GET", """ Home page""", _prefix + """home""")
)
                      

}
                          
}
        
    