$(document).ready(function() {

  if (window.JpegCamera) {

    var camera; // placeholder

    // Add the photo taken to the current Rekognition collection for later comparison
    var add_to_collection = function(ref) {
      var photo_id = $("#photo_id").val();
      var collection_id = $("#collection_id").val();
      if (!photo_id.length) {
        $("#upload_status").html("Please provide name for the upload");
        ref.data('requestNotRunning', false);
        return;
      }
      if (!collection_id.length) {
              $("#upload_status").html("Please provide name for the collection!");
              ref.data('requestNotRunning', false);
              return;
            }

      var snapshot = camera.capture();
      $("#loading_img").show();
      $("#upload_status").html("");
      $("#upload_result").html("");


      var api_url = "/upload/" + photo_id + "/" + collection_id;
      $("#loading_img").show();
      snapshot.upload({api_url: api_url}).done(function(response) {
        $("#upload_result").html(response);
        $("#loading_img").hide();
        this.discard();
        ref.data('requestNotRunning', false);
      }).fail(function(status_code, error_message, response) {
        $("#upload_status").html("Upload failed with status " + status_code + " (" + error_message + ")");
        $("#upload_result").html(response);
        $("#loading_img").hide();
        ref.data('requestNotRunning', false);
      });
//      $.ajax({url: r.url, type: r.type, contentType: "application/json",
//      data: JSON.stringify({
//             photoId: photo_id,
//             collectionId: collection_id,
//             photo: JSON.stringify(snapshot)
//            }),
//        success: function (msg) {
//            alert(request.responseText);
//            },
//        error: function (msg) {alert("Something went wrong !!")},
//        cache: false,
//        processData: false
//        });
    };


    // Compare the photographed image to the current Rekognition collection
    var compare_image = function(ref) {
      $("#upload_result").html("");
      var collection_id = $("#collection_id").val();
      if (!collection_id.length) {
                    $("#upload_status").html("Please provide name for the collection!");
                    ref.data('requestNotRunning', false);
                    return;
                  }

      var snapshot = camera.capture();
      var api_url = "/compare/" + collection_id;
      $("#loading_img").show();
      snapshot.upload({api_url: api_url}).done(function(response) {
        console.log(JSON.stringify(response));
        var results = JSON.parse(response);
        if(!results.error) {
        var confidence = results.confidence;
        var dataId = results.dataId;
          $("#upload_result").html(confidence);
          // create speech response
          var toSay = "Good " + greetingTime(moment()) + " " + dataId;
          console.log(toSay);
          $.get(jsRoutes.controllers.SpeechController.speak(toSay), function(response) {
            $("#audio_speech").attr("src", "data:audio/mpeg;base64," + response);
            $("#audio_speech")[0].play();
          });

        $("#loading_img").hide();
        this.discard();
        }
        ref.data('requestNotRunning', false);
      }).fail(function(status_code, error_message, response) {
        $("#upload_status").html("Upload failed with status " + status_code + " (" + error_message + ")");
        $("#upload_result").html(response);
        $("#loading_img").hide();
        ref.data('requestNotRunning', false);
      });
    };

    var greetingTime = function(moment) {
      var greet = null;
      
      if(!moment || !moment.isValid()) { return; } //if we can't find a valid or filled moment, we return.
            var split_afternoon = 12 //24hr time to split the afternoon
      var split_evening = 17 //24hr time to split the evening
      var currentHour = parseFloat(moment.format("HH"));
      
      if(currentHour >= split_afternoon && currentHour <= split_evening) {
        greet = "afternoon";
      } else if(currentHour >= split_evening) {
        greet = "evening";
      } else {
        greet = "morning";
      }      
      return greet;
    }

    // Define what the button clicks do.
//    $("#add_to_collection").click(add_to_collection);

    $('#add_to_collection').click(function(e) {
        var me = $(this);
        e.preventDefault();
            if (! me.data('requestNotRunning') ) {
                me.data('requestNotRunning', true);
                add_to_collection(me);
            }
    });
//    $("#compare_image").click(compare_image);
    $("#compare_image").click(function(e) {
        var me = $(this);
        e.preventDefault();
            if (! me.data('requestNotRunning') ) {
                me.data('requestNotRunning', true);
                compare_image(me);
            }
     });

    // Initiate the camera widget on screen
    var options = {
      shutter_ogg_url: "js/jpeg_camera/shutter.ogg",
      shutter_mp3_url: "js/jpeg_camera/shutter.mp3",
      swf_url: "js/jpeg_camera/jpeg_camera.swf"
    }


    camera = new JpegCamera("#camera", options).ready(function(info) {
      $("#loading_img").hide();
    });

  }

});