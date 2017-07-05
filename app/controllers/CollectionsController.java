package controllers;

/**
 * Created by abhinav on 10/6/17.
 */

import play.data.DynamicForm;
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
import com.amazonaws.services.rekognition.model.CreateCollectionRequest;
import com.amazonaws.services.rekognition.model.CreateCollectionResult;
import com.amazonaws.services.rekognition.model.DeleteCollectionRequest;
import com.amazonaws.services.rekognition.model.DeleteCollectionResult;
import com.amazonaws.services.rekognition.model.ListCollectionsRequest;
import com.amazonaws.services.rekognition.model.ListCollectionsResult;

@Singleton
public class CollectionsController extends Controller {


   /* public Result index(String collectionId){
        System.out.println(collectionId);

        return ok("collection::"+collectionId);
    }*/
    public Result create(String collectionId) {
        //String collectionId =null;
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

        // 1. CreateCollection 1
        //String collectionId = "abhinav1";
        System.out.println("Creating collection: " +
                collectionId);
	CreateCollectionResult createCollectionResult=null;
	try{
        createCollectionResult = callCreateCollection(
                collectionId, amazonRekognition);
        System.out.println("CollectionArn : " +
                createCollectionResult.getCollectionArn());
        System.out.println("Status code : " +
                createCollectionResult.getStatusCode().toString());
	}

      catch(Exception e){
	System.out.println("Error in creating collection");
	return ok("Collection creation failed!"+e.getMessage().substring(0,e.getMessage().indexOf('(')));

        }

	return ok("Collection "+collectionId+" created successfully!!");
	
        // 4. Clean up collections with DeleteCollection

        //System.out.println("Deleting collections");
        //DeleteCollectionResult deleteCollectionResult = callDeleteCollection(
        //        collectionId, amazonRekognition);
        //System.out.println(collectionId + ": " + deleteCollectionResult.getStatusCode()
        //        .toString());
    }

    private static CreateCollectionResult callCreateCollection(String collectionId,
                                                               AmazonRekognition amazonRekognition) {
        CreateCollectionRequest request = new CreateCollectionRequest()
                .withCollectionId(collectionId);
        return amazonRekognition.createCollection(request);
    }

    private static DeleteCollectionResult callDeleteCollection(String collectionId,
                                                               AmazonRekognition amazonRekognition) {
        DeleteCollectionRequest request = new DeleteCollectionRequest()
                .withCollectionId(collectionId);
        return amazonRekognition.deleteCollection(request);
    }

    private static ListCollectionsResult callListCollections(String paginationToken,
                                                             int limit, AmazonRekognition amazonRekognition) {
        ListCollectionsRequest listCollectionsRequest = new ListCollectionsRequest()
                .withMaxResults(limit)
                .withNextToken(paginationToken);
        return amazonRekognition.listCollections(listCollectionsRequest);
    }

}
