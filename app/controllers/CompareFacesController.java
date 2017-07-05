package controllers;

/**
 * Created by abhinav on 10/6/17.
 */

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Singleton;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

@Singleton
public class CompareFacesController extends Controller {

    public Result compare(String collectionId) {

        ObjectNode result = Json.newObject();

        Float similarityThreshold = 90F;
        int maxFaces = 2;
    
        if(request().body() == null){
            return badRequest("Expecting Json data");
        }else {
            File photo = request().body().asRaw().asFile();
//            String photoId = json.findPath("photoId").textValue();
//            String collectionId = json.findPath("collectionId").textValue();
            
            AWSCredentials credentials;
            try {
                System.out.println(" CollectionID: " + collectionId);
                credentials = new ProfileCredentialsProvider().getCredentials();
            } catch (Exception e) {
                throw new AmazonClientException(
                        "Cannot load the credentials from the credential profiles file. " +
                                "Please make sure that your credentials file is at the correct " +
                                "location (/Users/userid/.aws/credentials), and is in valid format.",
                        e);
            }
    
            ByteBuffer imageBytes = null; // Capture image from webcam
            try {
                InputStream inputStream = new FileInputStream(photo);
                imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
            } catch (Exception e) {
                System.out.println("Failed to load source image " + photo);
                return badRequest("Failed to load source image");
            }

            try {
                AmazonRekognition amazonRekognition = AmazonRekognitionClientBuilder
                        .standard()
                        .withRegion(Regions.US_WEST_2)
                        .withCredentials(new AWSStaticCredentialsProvider(credentials))
                        .build();

                Image source = new Image()
                        .withBytes(imageBytes);
                SearchFacesByImageResult searchFacesByImageResult =
                        callSearchFacesByImage(collectionId, source, similarityThreshold, maxFaces,
                                amazonRekognition);
                List<FaceMatch> faceImageMatches = searchFacesByImageResult.getFaceMatches();
                if(!faceImageMatches.isEmpty()) {

                    System.out.println("Faces matching largest face in image");
                    FaceMatch face = faceImageMatches.get(0);

                    System.out.println("Person identified: " + face.getFace().getExternalImageId().toString());
                    System.out.println("Confidence: " + face.getFace().getConfidence().toString());
                    System.out.println();
                    result.put("status","OK");
                    result.put("confidence","Confidence: " + face.getFace().getConfidence().toString());
                    result.put("dataId",face.getFace().getExternalImageId().toString());
                    return ok(result);

                } else {
                    System.out.println("No matches found in image!!");
                    result.put("status","FAIL");
                    result.put("statusmessage","No matches found in image!!");
                    return ok(result);
                    }
                } catch (Exception e) {
                System.out.println("Error in comparing image."+e.getMessage().substring(0,60));
                result.put("status","FAIL");
                result.put("statusmessage","Image comparison failed!"+e.getMessage().substring(0,e.getMessage().indexOf("(")));
                return ok(result);

            }

        }
    }

    private static SearchFacesByImageResult callSearchFacesByImage(String collectionId,
                                                                   Image image, Float threshold, int maxFaces, AmazonRekognition amazonRekognition) {
        SearchFacesByImageRequest searchFacesByImageRequest = new SearchFacesByImageRequest()
                .withCollectionId(collectionId)
                .withImage(image)
                .withFaceMatchThreshold(threshold)
                .withMaxFaces(maxFaces);
        return amazonRekognition.searchFacesByImage(searchFacesByImageRequest);
    }
}
