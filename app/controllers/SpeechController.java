package controllers;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.*;
import com.amazonaws.util.IOUtils;
import play.mvc.Controller;
import play.mvc.Result;
import play.routing.JavaScriptReverseRouter;

import javax.inject.Singleton;
import java.nio.ByteBuffer;
import java.util.Base64;

/**
 * Created by abhinav on 4/7/17.
 */
@Singleton
public class SpeechController extends Controller{

    public Result speak(String toSay) {

            AWSCredentials credentials;
            try {
                credentials = new ProfileCredentialsProvider().getCredentials();
            } catch (Exception e) {
                throw new AmazonClientException(
                        "Cannot load the credentials from the credential profiles file. " +
                                "Please make sure that your credentials file is at the correct " +
                                "location (/Users/userid/.aws/credentials), and is in valid format.",
                        e);
            }

            try {
                AmazonPolly amazonPolly = AmazonPollyClientBuilder
                        .standard()
                        .withRegion(Regions.US_WEST_2)
                        .withCredentials(new AWSStaticCredentialsProvider(credentials))
                        .build();

                DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();

                // Synchronously ask Amazon Polly to describe available TTS voices.
                DescribeVoicesResult describeVoicesResult = amazonPolly.describeVoices(describeVoicesRequest);
                Voice voice = describeVoicesResult.getVoices().get(0);

                SynthesizeSpeechRequest synthReq =
                        new SynthesizeSpeechRequest().withText(toSay).withVoiceId(voice.getId())
                                .withOutputFormat(OutputFormat.Mp3);

                SynthesizeSpeechResult synthRes = amazonPolly.synthesizeSpeech(synthReq);

                return ok(synthRes.getAudioStream());
            } catch (Exception e) {
                System.out.println("Error in synthesizing speech."+e.getMessage());
                return ok(e.getMessage().substring(0,e.getMessage().indexOf('(')));
            }

        }


    public Result javascriptRoutes() {
        return ok(
                JavaScriptReverseRouter.create("jsRoutes",
                        routes.javascript.SpeechController.speak()
                )
        ).as("text/javascript");
    }
}
