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
import com.amazonaws.services.rekognition.model.FaceRecord;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.IndexFacesRequest;
import com.amazonaws.services.rekognition.model.IndexFacesResult;
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
public class IndexFacesController extends Controller {

    public Result upload(String photoId, String collectionId) {
    
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

            AmazonRekognition amazonRekognition = AmazonRekognitionClientBuilder
                    .standard()
                    .withRegion(Regions.US_WEST_2)
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .build();

            List<FaceRecord> faceRecords;
            ObjectNode result = Json.newObject();
            try {
                Image source = new Image()
                        .withBytes(imageBytes);
                IndexFacesResult indexFacesResult = callIndexFaces(collectionId,
                        photoId, "ALL", source, amazonRekognition);
                 faceRecords = indexFacesResult.getFaceRecords();
                for (FaceRecord faceRecord : faceRecords) {
                    System.out.println("Face detected: Faceid is " +
                            faceRecord.getFace().getFaceId());
                }
            } catch (Exception e) {
                System.out.println("Error in adding image."+ e.getMessage());
                result.put("status","FAIL");
                result.put("statusMessage", "Image addition failed."+e.getMessage().substring(0,e.getMessage().indexOf('(')));
                return ok(result);
            }
            if(!faceRecords.isEmpty()) {
                result.put("status", "OK");
                result.put("statusMessage", "Image " + photoId + " added successfully!!");
                result.put("agerange", faceRecords.get(0).getFaceDetail().getAgeRange().toString());
                result.put("hasbeard", faceRecords.get(0).getFaceDetail().getBeard().toString());
                result.put("gender", faceRecords.get(0).getFaceDetail().getGender().toString());
                result.put("specs", faceRecords.get(0).getFaceDetail().getEyeglasses().toString());
                return ok(result);
            } else {
                result.put("status","FAIL");
                result.put("statusMessage", "No face found in image to add.");
                return ok(result);
            }

        }
    }

    private static IndexFacesResult callIndexFaces(String collectionId, String externalImageId,
                                                   String attributes, Image image, AmazonRekognition amazonRekognition) {
        IndexFacesRequest indexFacesRequest = new IndexFacesRequest()
                .withImage(image)
                .withCollectionId(collectionId)
                .withExternalImageId(externalImageId)
                .withDetectionAttributes(attributes);
        return amazonRekognition.indexFaces(indexFacesRequest);

    }

}
