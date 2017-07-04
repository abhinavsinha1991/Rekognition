$(document).ready(function() {

  if (window.JpegCamera) {

    var camera; // placeholder

    // Add the photo taken to the current Rekognition collection for later comparison
    var add_to_collection = function() {
      var photo_id = $("#photo_id").val();
      var collection_id = $("#collection_id").val();
      if (!photo_id.length) {
        $("#upload_status").html("Please provide name for the upload");
        return;
      }
      if (!collection_id.length) {
              $("#upload_status").html("Please provide name for the collection!");
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
      }).fail(function(status_code, error_message, response) {
        $("#upload_status").html("Upload failed with status " + status_code + " (" + error_message + ")");
        $("#upload_result").html(response);
        $("#loading_img").hide();
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
    var compare_image = function() {
      var snapshot = camera.capture();
      var api_url = "/compare";
      $("#loading_img").show();
      snapshot.upload({api_url: api_url}).done(function(response) {
        var data = JSON.parse(response);
        if (data.id !== undefined) {
          $("#upload_result").html(data.message + ": " + data.id + ", Confidence: " + data.confidence);
          // create speech response
          $.post("/speech", {tosay: "Good " + greetingTime(moment()) + " " + data.id}, function(response) {
            $("#audio_speech").attr("src", "data:audio/mpeg;base64," + response);
            $("#audio_speech")[0].play();
          });
        } else {
          $("#upload_result").html(data.message);
        }
        $("#loading_img").hide();
        this.discard();
      }).fail(function(status_code, error_message, response) {
        $("#upload_status").html("Upload failed with status " + status_code + " (" + error_message + ")");
        $("#upload_result").html(response);
        $("#loading_img").hide();
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
    $("#add_to_collection").click(add_to_collection);
    $("#compare_image").click(compare_image);

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