
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/bharat/code-combat/Rekognition/conf/routes
// @DATE:Tue Jul 04 13:17:50 IST 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_2: controllers.HomeController,
  // @LINE:8
  CountController_0: controllers.CountController,
  // @LINE:10
  AsyncController_3: controllers.AsyncController,
  // @LINE:12
  CollectionsController_1: controllers.CollectionsController,
  // @LINE:13
  DeleteCollection_6: controllers.DeleteCollection,
  // @LINE:14
  IndexFacesController_4: controllers.IndexFacesController,
  // @LINE:18
  Assets_5: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_2: controllers.HomeController,
    // @LINE:8
    CountController_0: controllers.CountController,
    // @LINE:10
    AsyncController_3: controllers.AsyncController,
    // @LINE:12
    CollectionsController_1: controllers.CollectionsController,
    // @LINE:13
    DeleteCollection_6: controllers.DeleteCollection,
    // @LINE:14
    IndexFacesController_4: controllers.IndexFacesController,
    // @LINE:18
    Assets_5: controllers.Assets
  ) = this(errorHandler, HomeController_2, CountController_0, AsyncController_3, CollectionsController_1, DeleteCollection_6, IndexFacesController_4, Assets_5, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_2, CountController_0, AsyncController_3, CollectionsController_1, DeleteCollection_6, IndexFacesController_4, Assets_5, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """count""", """controllers.CountController.count"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """message""", """controllers.AsyncController.message"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """create""", """controllers.CollectionsController.index(collection_id:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """delete""", """controllers.DeleteCollection.index(collection_id:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """upload/""" + "$" + """photoId<[^/]+>/""" + "$" + """collectionId<[^/]+>""", """controllers.IndexFacesController.index(photoId:String, collectionId:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_2.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_CountController_count1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("count")))
  )
  private[this] lazy val controllers_CountController_count1_invoker = createInvoker(
    CountController_0.count,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CountController",
      "count",
      Nil,
      "GET",
      this.prefix + """count""",
      """ An example controller showing how to use dependency injection""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_AsyncController_message2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("message")))
  )
  private[this] lazy val controllers_AsyncController_message2_invoker = createInvoker(
    AsyncController_3.message,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AsyncController",
      "message",
      Nil,
      "GET",
      this.prefix + """message""",
      """ An example controller showing how to write asynchronous code""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_CollectionsController_index3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("create")))
  )
  private[this] lazy val controllers_CollectionsController_index3_invoker = createInvoker(
    CollectionsController_1.index(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CollectionsController",
      "index",
      Seq(classOf[String]),
      "GET",
      this.prefix + """create""",
      """ Controller for Rekog API calls""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_DeleteCollection_index4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("delete")))
  )
  private[this] lazy val controllers_DeleteCollection_index4_invoker = createInvoker(
    DeleteCollection_6.index(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.DeleteCollection",
      "index",
      Seq(classOf[String]),
      "GET",
      this.prefix + """delete""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_IndexFacesController_index5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("upload/"), DynamicPart("photoId", """[^/]+""",true), StaticPart("/"), DynamicPart("collectionId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_IndexFacesController_index5_invoker = createInvoker(
    IndexFacesController_4.index(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.IndexFacesController",
      "index",
      Seq(classOf[String], classOf[String]),
      "POST",
      this.prefix + """upload/""" + "$" + """photoId<[^/]+>/""" + "$" + """collectionId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_Assets_versioned6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned6_invoker = createInvoker(
    Assets_5.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_2.index)
      }
  
    // @LINE:8
    case controllers_CountController_count1_route(params) =>
      call { 
        controllers_CountController_count1_invoker.call(CountController_0.count)
      }
  
    // @LINE:10
    case controllers_AsyncController_message2_route(params) =>
      call { 
        controllers_AsyncController_message2_invoker.call(AsyncController_3.message)
      }
  
    // @LINE:12
    case controllers_CollectionsController_index3_route(params) =>
      call(params.fromQuery[String]("collection_id", None)) { (collection_id) =>
        controllers_CollectionsController_index3_invoker.call(CollectionsController_1.index(collection_id))
      }
  
    // @LINE:13
    case controllers_DeleteCollection_index4_route(params) =>
      call(params.fromQuery[String]("collection_id", None)) { (collection_id) =>
        controllers_DeleteCollection_index4_invoker.call(DeleteCollection_6.index(collection_id))
      }
  
    // @LINE:14
    case controllers_IndexFacesController_index5_route(params) =>
      call(params.fromPath[String]("photoId", None), params.fromPath[String]("collectionId", None)) { (photoId, collectionId) =>
        controllers_IndexFacesController_index5_invoker.call(IndexFacesController_4.index(photoId, collectionId))
      }
  
    // @LINE:18
    case controllers_Assets_versioned6_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned6_invoker.call(Assets_5.versioned(path, file))
      }
  }
}
