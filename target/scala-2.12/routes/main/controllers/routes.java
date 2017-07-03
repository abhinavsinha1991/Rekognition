
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/abhinav/Desktop/Rekognition/conf/routes
// @DATE:Mon Jul 03 14:24:26 IST 2017

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseIndexFaces IndexFaces = new controllers.ReverseIndexFaces(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseCollection Collection = new controllers.ReverseCollection(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseDeleteCollection DeleteCollection = new controllers.ReverseDeleteCollection(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseCountController CountController = new controllers.ReverseCountController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAsyncController AsyncController = new controllers.ReverseAsyncController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseIndexFaces IndexFaces = new controllers.javascript.ReverseIndexFaces(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseCollection Collection = new controllers.javascript.ReverseCollection(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseDeleteCollection DeleteCollection = new controllers.javascript.ReverseDeleteCollection(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseCountController CountController = new controllers.javascript.ReverseCountController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAsyncController AsyncController = new controllers.javascript.ReverseAsyncController(RoutesPrefix.byNamePrefix());
  }

}
