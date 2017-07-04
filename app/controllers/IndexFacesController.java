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

    public Result index(String photoId, String collectionId) {
    
        if(request().body() == null){
            return badRequest("Expecting Json data");
        }else {
            System.out.println(request().body().asText());
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
            try {
                Image source = new Image()
                        .withBytes(imageBytes);
                IndexFacesResult indexFacesResult = callIndexFaces(collectionId,
                        photoId, "ALL", source, amazonRekognition);
                System.out.println(photoId + " added");
                List<FaceRecord> faceRecords = indexFacesResult.getFaceRecords();
                for (FaceRecord faceRecord : faceRecords) {
                    System.out.println("Face detected: Faceid is " +
                            faceRecord.getFace().getFaceId());
                }
            } catch (Exception e) {
                System.out.println("Error in adding image.");
                return ok("Image addition failed!" + e.getMessage());
            }
    
            return ok("Image " + photoId + " added successfully!!");
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
