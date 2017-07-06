[<img src="https://img.shields.io/travis/playframework/play-java-starter-example.svg"/>](https://travis-ci.org/playframework/play-java-starter-example)

# Amazon Rekognition - Face detection using deep learning

This is an application that leverages AWS Rekognition for face detection and comparison.It also uses AWS Polly for audio confirmation;if a match is found,the app greets the user.

Please see the documentation at https://aws.amazon.com/rekognition/ for more details.

## Running

Run this using [sbt](http://www.scala-sbt.org/).  

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

  Handles request for creating collection to store your faces/images in.

- DeleteCollection.java:

  Handles request for deleting collections.

- IndexFacesController.java

  Handles request for indexing a new face to an existing collection.

- CompareFacesController.java

  Handles request for comparing faces in existing collection.

- SpeechController.java

  Handles request for converting matched person's name to audio stream for audio confirmation.

## Components

- Module.java:

  Uses Guice to bind all the components needed by your application.

