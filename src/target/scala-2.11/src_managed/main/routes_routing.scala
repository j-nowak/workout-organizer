// @SOURCE:/home/jakub/workspace/workout-organizer/src/conf/routes
// @HASH:cc599bc2a072f2201f138af3426f4933ad323d10
// @DATE:Sun Jan 11 13:24:25 CET 2015


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_home0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("home"))))
private[this] lazy val controllers_Application_home0_invoker = createInvoker(
controllers.Application.home(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "home", Nil,"GET", """ Home page""", Routes.prefix + """home"""))
        

// @LINE:9
private[this] lazy val controllers_Application_listAllExercises1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("exercises"))))
private[this] lazy val controllers_Application_listAllExercises1_invoker = createInvoker(
controllers.Application.listAllExercises(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "listAllExercises", Nil,"GET", """ Exercises""", Routes.prefix + """exercises"""))
        

// @LINE:12
private[this] lazy val controllers_Application_listAllGyms2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("gyms"))))
private[this] lazy val controllers_Application_listAllGyms2_invoker = createInvoker(
controllers.Application.listAllGyms(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "listAllGyms", Nil,"GET", """ Gyms""", Routes.prefix + """gyms"""))
        

// @LINE:15
private[this] lazy val controllers_Assets_at3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Assets_at3_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Sign up and in""", Routes.prefix + """"""))
        

// @LINE:16
private[this] lazy val controllers_Assets_at4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
private[this] lazy val controllers_Assets_at4_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """login"""))
        

// @LINE:17
private[this] lazy val controllers_SignController_registerUser5_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("register"))))
private[this] lazy val controllers_SignController_registerUser5_invoker = createInvoker(
controllers.SignController.registerUser(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.SignController", "registerUser", Nil,"POST", """""", Routes.prefix + """register"""))
        

// @LINE:18
private[this] lazy val controllers_SignController_loginUser6_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("signin"))))
private[this] lazy val controllers_SignController_loginUser6_invoker = createInvoker(
controllers.SignController.loginUser(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.SignController", "loginUser", Nil,"POST", """""", Routes.prefix + """signin"""))
        

// @LINE:19
private[this] lazy val controllers_SignController_logout7_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
private[this] lazy val controllers_SignController_logout7_invoker = createInvoker(
controllers.SignController.logout(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.SignController", "logout", Nil,"GET", """""", Routes.prefix + """logout"""))
        

// @LINE:22
private[this] lazy val controllers_Assets_at8_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at8_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:24
private[this] lazy val controllers_Assets_at9_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("javascripts/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at9_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """javascripts/$file<.+>"""))
        

// @LINE:25
private[this] lazy val controllers_Assets_at10_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("images/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at10_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """images/$file<.+>"""))
        

// @LINE:26
private[this] lazy val controllers_Assets_at11_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("stylesheets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at11_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """stylesheets/$file<.+>"""))
        
def documentation = List(("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """home""","""controllers.Application.home()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """exercises""","""controllers.Application.listAllExercises()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """gyms""","""controllers.Application.listAllGyms()"""),("""GET""", prefix,"""controllers.Assets.at(path:String = "/public/html", file:String = "login.html")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Assets.at(path:String = "/public/html", file:String = "login.html")"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """register""","""controllers.SignController.registerUser()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """signin""","""controllers.SignController.loginUser()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.SignController.logout()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """javascripts/$file<.+>""","""controllers.Assets.at(path:String = "/public/javascripts", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """images/$file<.+>""","""controllers.Assets.at(path:String = "/public/images", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """stylesheets/$file<.+>""","""controllers.Assets.at(path:String = "/public/stylesheets", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_home0_route(params) => {
   call { 
        controllers_Application_home0_invoker.call(controllers.Application.home())
   }
}
        

// @LINE:9
case controllers_Application_listAllExercises1_route(params) => {
   call { 
        controllers_Application_listAllExercises1_invoker.call(controllers.Application.listAllExercises())
   }
}
        

// @LINE:12
case controllers_Application_listAllGyms2_route(params) => {
   call { 
        controllers_Application_listAllGyms2_invoker.call(controllers.Application.listAllGyms())
   }
}
        

// @LINE:15
case controllers_Assets_at3_route(params) => {
   call(Param[String]("path", Right("/public/html")), Param[String]("file", Right("login.html"))) { (path, file) =>
        controllers_Assets_at3_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:16
case controllers_Assets_at4_route(params) => {
   call(Param[String]("path", Right("/public/html")), Param[String]("file", Right("login.html"))) { (path, file) =>
        controllers_Assets_at4_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:17
case controllers_SignController_registerUser5_route(params) => {
   call { 
        controllers_SignController_registerUser5_invoker.call(controllers.SignController.registerUser())
   }
}
        

// @LINE:18
case controllers_SignController_loginUser6_route(params) => {
   call { 
        controllers_SignController_loginUser6_invoker.call(controllers.SignController.loginUser())
   }
}
        

// @LINE:19
case controllers_SignController_logout7_route(params) => {
   call { 
        controllers_SignController_logout7_invoker.call(controllers.SignController.logout())
   }
}
        

// @LINE:22
case controllers_Assets_at8_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at8_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:24
case controllers_Assets_at9_route(params) => {
   call(Param[String]("path", Right("/public/javascripts")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at9_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:25
case controllers_Assets_at10_route(params) => {
   call(Param[String]("path", Right("/public/images")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at10_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:26
case controllers_Assets_at11_route(params) => {
   call(Param[String]("path", Right("/public/stylesheets")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at11_invoker.call(controllers.Assets.at(path, file))
   }
}
        
}

}
     