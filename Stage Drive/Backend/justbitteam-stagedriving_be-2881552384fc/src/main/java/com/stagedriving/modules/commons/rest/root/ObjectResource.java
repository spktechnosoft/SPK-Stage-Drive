package com.stagedriving.modules.commons.rest.root;

import com.codahale.metrics.annotation.Metered;
import com.google.inject.Inject;
import com.justbit.aws.S3Utils;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.ObjectDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.utils.media.ImageUtils;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;
import javaxt.io.Image;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


@Path("/v1/objects")
@Api(value = "objects", description = "Objects resources")
public class ObjectResource {

    @Inject
    TokenUtils tokenUtils;
    @Inject
    S3Utils s3Utils;
    @Inject
    ImageUtils imageUtils;

    private AppConfiguration configuration;

    @Inject
    public ObjectResource(AppConfiguration configuration) {

        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @UnitOfWork
    public Response uploadImageForm(@HeaderParam("Authorization") String authorization,
                                    @FormDataParam("authorization") String auth,
                                    @FormDataParam("file") InputStream file,
                                    @FormDataParam("bucket") String bucket,
                                    @FormDataParam("size") int size,
                                    @FormDataParam("type") String type) throws InterruptedException, IOException {

        String resultingUrl = null;
        String fileUid = "";
        fileUid = TokenUtils.generateUid();

        bucket = configuration.getStorage().getEventBucket();
//        if (bucket.equals(ScxData.ObjectBuckets.EVENT)) {
//        } else if (bucket.equals(ScxData.ObjectBuckets.PHOTO)) {
//            bucket = configuration.getStorage().getPhotoBucket();
//        } else if (bucket.equals(ScxData.ObjectBuckets.VIDEO)) {
//            bucket = configuration.getStorage().getVideoBucket();
//        } else if (bucket.equals(ScxData.ObjectBuckets.DOC)) {
//            bucket = configuration.getStorage().getDocumentBucket();
//        }

        byte[] dataByte = null;
        ByteArrayInputStream is = null;

        if (type == null) {
            type = "png";
        }

        if (type.equals("png")) {
            Image image = new Image(file);
            imageUtils.fixExifRotation(image);
            is = new ByteArrayInputStream(image.getByteArray("png"));

            BufferedImage sourceImage = ImageIO.read(is);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(sourceImage, "png", os);
            dataByte = os.toByteArray();
            is = new ByteArrayInputStream(dataByte);

            resultingUrl = s3Utils.uploadData(is, bucket, fileUid + "." + type, dataByte.length);
        } else {
            resultingUrl = s3Utils.uploadData(file, bucket, fileUid + "." + type, size);
        }

        StgdrvResponseDTO responseDTO = new StgdrvResponseDTO();
        responseDTO.setId(fileUid);
        responseDTO.setUri(resultingUrl);
        responseDTO.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDTO.setMessage("Object successfully upload");
        return Response.ok(responseDTO).build();

    }

    @POST
    @Path("/images")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Add new image",
            notes = "Add new image",
            response = StgdrvResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response uploadImage(ObjectDTO objectDTO) throws InterruptedException, IOException {

        String resultingUrl = null;
        String fileUid = "";
        fileUid = TokenUtils.generateUid();
        String bucket = null;
        bucket = configuration.getStorage().getEventBucket();
//        if (objectDTO.getBucket().equals(ScxData.ObjectBuckets.EVENT)) {
//        } else if (objectDTO.getBucket().equals(ScxData.ObjectBuckets.PHOTO)) {
//            bucket = configuration.getStorage().getPhotoBucket();
//        } else if (objectDTO.getBucket().equals(ScxData.ObjectBuckets.VIDEO)) {
//            bucket = configuration.getStorage().getPhotoBucket();
//        }

        // TODO Change this shit!!!
        if (objectDTO.getMimeType() == null) {
            objectDTO.setMimeType("png");
        }

        if (objectDTO.getContent().startsWith("data:image")) {
            if (objectDTO.getMimeType().equals("png")) {
                objectDTO.setContent(objectDTO.getContent().substring("data:image/png;base64".length()));
            } else if (objectDTO.getMimeType().equals("jpeg")) {
                objectDTO.setContent(objectDTO.getContent().substring("data:image/jpeg;base64".length()));
            }
        }
        byte[] content = org.apache.commons.codec.binary.Base64.decodeBase64(objectDTO.getContent().getBytes());
        //BufferedImage image = imageUtils.decodeToImage(objectDTO.getContent());
//        Image image = new Image(content);
        //content = image.getByteArray("png");
        ByteArrayInputStream is = new ByteArrayInputStream(content);

        resultingUrl = s3Utils.uploadData(is, bucket, fileUid + ".png", content.length);

        // Saving thumbnails
        //is = new ByteArrayInputStream(image.getByteArray());
        //imageUtils.saveThumbnails(is, fileUid+".png");

        StgdrvResponseDTO responseDTO = new StgdrvResponseDTO();
        responseDTO.setId(fileUid);
        responseDTO.setUri(resultingUrl);
        responseDTO.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDTO.setMessage("Object successfully upload");
        return Response.ok(responseDTO).build();

    }


}
