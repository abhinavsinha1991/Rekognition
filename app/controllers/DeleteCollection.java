package controllers;

/**
 * Created by abhinav on 10/6/17.
 */

import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Singleton;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.DeleteCollectionRequest;
import com.amazonaws.services.rekognition.model.DeleteCollectionResult;

@Singleton
public class DeleteCollection extends Controller {

    public Result delete(String collectionId) {

        AWSCredentials credentials;
        try {
            //collectionId = request().body().asText();
            System.out.println(" CollectionID: "+collectionId);
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (/Users/userid/.aws/credentials), and is in valid format.",
                    e);
        }

        AmazonRekognition amazonRekognition = AmazonRekognitionClientBuilder
                .standard()
                .withRegion(Regions.US_WEST_2)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        // Clean up collections with DeleteCollection

        try{
        DeleteCollectionResult deleteCollectionResult = callDeleteCollection(
                collectionId, amazonRekognition);
        System.out.println(collectionId + ": " + deleteCollectionResult.getStatusCode()
                .toString());
        }
        catch(Exception e){
            System.out.println("Error in deleting collection"+e.getMessage());
            return ok("Collection deletion failed!"+e.getMessage().substring(0,e.getMessage().indexOf('(')));
        }

        return ok("Collection "+collectionId+" deleted successfully!!");
    }

    private static DeleteCollectionResult callDeleteCollection(String collectionId,
                                                               AmazonRekognition amazonRekognition) {
        DeleteCollectionRequest request = new DeleteCollectionRequest()
                .withCollectionId(collectionId);
        return amazonRekognition.deleteCollection(request);
    }

}
