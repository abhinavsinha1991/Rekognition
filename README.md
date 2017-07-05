[<img src="https://img.shields.io/travis/playframework/play-java-starter-example.svg"/>](https://travis-ci.org/playframework/play-java-starter-example)

# Amazon Rekognition - Face detection using deep learning

This is an application that leverages AWS Rekognition for face detection and comparison.It also uses AWS Polly for audio confirmation if a match is found and greets the user.Please see the documentation at https://aws.amazon.com/rekognition/ for more details.

## Running

Run this using [sbt](http://www.scala-sbt.org/).  If you downloaded this project from http://www.playframework.com/download then you'll find a prepackaged version of sbt in the project directory:

```
sbt run
```

And then go to http://localhost:9000 to see the running web application.

Please use Chrome browser for best support.Firefox has known issues currently.


## Controllers

There are several important files available in this project.

- HomeController.java:

  Handle simple HTTP requests @ "/" for displaying the initial UI.

- CollectionsController.java:

  Handles request for creating coolection to store your images in.

- DeleteCollection.java:

  Handles request for deleting collections.

- IndexFacesController.java

  Handles request for indexing your image.

- CompareFacesController.java

  Handles request for comparing faces in existing collection.

- SpeechController.java

  Handles request for converting text to audio stream for audio confirmation of a match found.

## Components

- Module.java:

  Shows how to use Guice to bind all the components needed by your application.

