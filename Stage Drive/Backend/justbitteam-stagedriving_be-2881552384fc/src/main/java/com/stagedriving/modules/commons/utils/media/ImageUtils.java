/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.utils.media;

import com.google.inject.Inject;
import com.justbit.aws.S3Utils;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import javaxt.io.Image;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.imgscalr.Scalr;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author simone
 */
public class ImageUtils {

    public static int[] widths = new int[]{
            200, 400, 800
    };
    public static String[] exts = new String[]{
            "small", "medium", "large"
    };

    private static Color defaultBackgroundColor = new Color(245, 245, 245);

    private AppConfiguration configuration;
    @Inject
    private S3Utils s3Utils;

    @Inject
    public ImageUtils(AppConfiguration configuration) {
        this.configuration = configuration;
    }


    public Image uploadImage(Image image, String type, String id) throws IOException, InterruptedException {
        fixExifRotation(image);
        ByteArrayInputStream is = new ByteArrayInputStream(image.getByteArray());

        BufferedImage sourceImage = ImageIO.read(is);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(sourceImage, type, os);
        byte[] photoByte = os.toByteArray();
        is = new ByteArrayInputStream(photoByte);

        s3Utils.uploadData(is, configuration.getStorage().getPhotoBucket(), id + "." + type, photoByte.length);

        // Saving thumbnails
        is = new ByteArrayInputStream(image.getByteArray());
        saveThumbnails(is, id);

        return image;
    }


    private BufferedImage scaleImage(BufferedImage sourceImage, Integer width, Integer height, boolean keepTransparency) {
        BufferedImage result = sourceImage;

        Color backgroundColor = null;
        if (!keepTransparency) {
            backgroundColor = defaultBackgroundColor;
        }
        if (sourceImage.getWidth() > sourceImage.getHeight() && width >= height) {
//            if (sourceImage.getWidth() > width) {
            result = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, width, height, backgroundColor);
//            } else {
//                result = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, width, height);
//            }
        } else if (sourceImage.getWidth() > sourceImage.getHeight() && width < height) {
            result = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, width, height, backgroundColor);
        } else if (sourceImage.getWidth() < sourceImage.getHeight() && width < height) {
//            if (sourceImage.getHeight() > height) {
            result = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, width, height, backgroundColor);
//            } else {
//                result = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, width, height);
//            }
        } else if (sourceImage.getWidth() < sourceImage.getHeight() && width >= height) {
            result = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, width, height, backgroundColor);
        } else if (sourceImage.getWidth() == sourceImage.getHeight() && width >= height) {
//            if (sourceImage.getWidth() > width) {
            result = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, width, height, backgroundColor);
//            } else {
//                result = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, width, height);
//            }
        } else if (sourceImage.getWidth() == sourceImage.getHeight() && width < height) {
            result = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, width, height, backgroundColor);
        } else {
            result = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, height, backgroundColor);
        }

//        BufferedImage newResult = new BufferedImage(result.getWidth(),result.getHeight(), BufferedImage.TYPE_INT_ARGB);
//        Graphics g = newResult.getGraphics();
//
//        // "Clear" the background of the new image with our padding color first.
//        g.setColor(Color.lightGray);
//        g.fillRect(0, 0, result.getWidth(), result.getHeight());
//
//        // Draw the image into the center of the new padded image.
//        g.drawImage(result, 0, 0, null);
//        g.dispose();

        return result;
    }

    private BufferedImage centerImage(BufferedImage sourceImage, int w, int h) {
        return centerImage(sourceImage, w, h, false);
    }

    private BufferedImage centerImage(BufferedImage sourceImage, int w, int h, boolean keepTransparency) {
        BufferedImage result = sourceImage;

        Color backgroundColor = null;
        if (!keepTransparency) {
            backgroundColor = defaultBackgroundColor;
        }

        if (sourceImage.getWidth() < w) {
            int leftPadding = (w - sourceImage.getWidth()) / 2;

            BufferedImage padded = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics g = padded.getGraphics();

            g.drawImage(sourceImage, leftPadding, 0, backgroundColor, null);
            if (leftPadding > 0 && !keepTransparency) {

                g.setColor(backgroundColor);
                g.fillRect(0, 0, leftPadding, sourceImage.getHeight());

                g.fillRect(leftPadding + sourceImage.getWidth(), 0, w - (leftPadding + sourceImage.getWidth()), h);
            }
            g.dispose();

            result = padded;
        } else if (sourceImage.getHeight() < h) {
            int topPadding = (h - sourceImage.getHeight()) / 2;

            BufferedImage padded = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics g = padded.getGraphics();

            g.drawImage(sourceImage, 0, topPadding, backgroundColor, null);
            if (topPadding > 0 && !keepTransparency) {

                g.setColor(backgroundColor);
                g.fillRect(0, 0, sourceImage.getWidth(), topPadding);

                g.fillRect(0, topPadding + sourceImage.getHeight(), w, h - (sourceImage.getHeight() + topPadding));
            }
            g.dispose();

            result = padded;
        }

        return result;
    }

    public BufferedImage mergeImages(int w, int h, BufferedImage... images) {
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

// paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        g.setColor(defaultBackgroundColor);
        g.fillRect(0, 0, w, h);
        for (BufferedImage img : images) {
            g.drawImage(img, 0, 0, null);
        }
        g.dispose();

        return combined;
    }

    public void saveImageContent(String imageContent, String title, String format, String bucket) throws IOException, InterruptedException {

        byte[] content = Base64.decodeBase64(imageContent.getBytes());
//        Image image = new Image(content);
//        byte[] imageByte = image.getByteArray();

        ByteArrayInputStream is = new ByteArrayInputStream(content);

        s3Utils.uploadData(is, bucket, title + "." + format, content.length);

        // Saving thumbnails
        is = new ByteArrayInputStream(content);
        saveThumbnails(is, bucket, title);
    }

    public void saveImageContent(String imageContent, String title, String format) throws IOException, InterruptedException {

        saveImageContent(imageContent, title, format, configuration.getStorage().getPhotoBucket());
    }

    public String saveImageUri(String imageUri, String title, String format, String bucket) throws IOException, InterruptedException {

        try {
            Content returnContent = Request.Get(imageUri).execute().returnContent();
            byte[] content = returnContent.asBytes();

            ByteArrayInputStream is = new ByteArrayInputStream(content);

            String uri = s3Utils.uploadData(is, bucket, title + "." + format, content.length);

            // Saving thumbnails
            is = new ByteArrayInputStream(content);
            saveThumbnails(is, bucket, title);
            return uri;
        } catch (Exception ex) {
            throw new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INTERNAL_ERROR, "Unable to upload image");
        }
    }

    public void saveImageUri(String imageUri, String title, String format) throws IOException, InterruptedException {
        saveImageUri(imageUri, title, format, configuration.getStorage().getPhotoBucket());
    }

    public static void saveThumbnails(String filename, String inputPath, String outputPath, String format) {
        FileInputStream in = null;

        try {
            // Set content size
            File file = new File(inputPath + File.separator + filename + "." + format);
            in = new FileInputStream(file);
            BufferedImage sourceImage = ImageIO.read(in);
            for (int i = 0; i < widths.length; i++) {
                int width = widths[i];
                String ext = exts[i];
                BufferedImage resizedImage = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC,
                        width, width);

                ImageIO.write(resizedImage, format, new File(outputPath + File.separator + filename + "-" + ext + "." + format));
            }


        } catch (IOException ex) {
            Logger.getLogger(ImageUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ImageUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void saveThumbnails(String filename, String inputPath, String outputPath) {
        FileInputStream in = null;

        try {
            // Set content size
            File file = new File(inputPath + File.separator + filename + ".png");
            in = new FileInputStream(file);
            BufferedImage sourceImage = ImageIO.read(in);
            for (int i = 0; i < widths.length; i++) {
                int width = widths[i];
                String ext = exts[i];
                BufferedImage resizedImage = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC,
                        width, width);

                ImageIO.write(resizedImage, "png", new File(outputPath + File.separator + filename + "-" + ext + ".png"));
            }


        } catch (IOException ex) {
            Logger.getLogger(ImageUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ImageUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void saveThumbnails(String data, String title) throws IOException, InterruptedException {
        ByteArrayInputStream is = new ByteArrayInputStream(data.getBytes());
        saveThumbnails(is, title);

    }

    public void saveThumbnails(byte[] data, String title) throws IOException, InterruptedException {
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        saveThumbnails(is, title);

    }

    public void saveThumbnails(InputStream fileInputStream, String eventBucket, String objectName) throws IOException, InterruptedException {
        BufferedImage sourceImage = null;

        sourceImage = ImageIO.read(fileInputStream);

        for (int i = 0; i < widths.length; i++) {
            int width = widths[i];
            String ext = exts[i];

            BufferedImage resizedImage = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC,
                    width, width);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "png", os);
            InputStream bis = new ByteArrayInputStream(os.toByteArray());

            objectName = objectName.replaceAll(".png", "");
            s3Utils.uploadData(bis, eventBucket, objectName + "-" + ext + ".png", os.toByteArray().length);
        }
    }

    public void saveThumbnails(InputStream is, String title) throws IOException, InterruptedException {
        saveThumbnails(is, configuration.getStorage().getPhotoBucket(), title);
    }

//    public void saveThumbnailsBucket(InputStream is, String title, String bucket) throws IOException, InterruptedException {
//
//        BufferedImage sourceImage = null;
//
//        sourceImage = ImageIO.read(is);
//
//        for (int i = 0; i < widths.length; i++) {
//            int width = widths[i];
//            String ext = exts[i];
//
//            BufferedImage resizedImage = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC,
//                    width, width);
//
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            ImageIO.write(resizedImage, "png", os);
//            InputStream bis = new ByteArrayInputStream(os.toByteArray());
//            s3Utils.uploadData(bis, bucket, title + "-" + ext + ".png", os.toByteArray().length);
//        }
//    }

    public void fixExifRotation(Image image) {
        HashMap<Integer, Object> exif = image.getExifTags();

        //Print Image Orientation
        try {
            int orientation = (Integer) exif.get(0x0112);
            String desc = "";
            switch (orientation) {
                case 1:
                    desc = "Top, left side (Horizontal / normal)";
                    break;
                case 2:
                    desc = "Top, right side (Mirror horizontal)";
                    image.flip();
                    image.rotate(180.0);
                    break;
                case 3:
                    desc = "Bottom, right side (Rotate 180)";
                    image.rotate(180.0);
                    break;
                case 4:
                    desc = "Bottom, left side (Mirror vertical)";
                    image.flip();
                    break;
                case 5:
                    desc = "Left side, top (Mirror horizontal and rotate 270 CW)";
                    image.flip();
                    image.rotate(180.0);
                    image.rotate(270.0);
                    break;
                case 6:
                    desc = "Right side, top (Rotate 90 CW)";
                    image.rotate(90.0);
                    break;
                case 7:
                    desc = "Right side, bottom (Mirror horizontal and rotate 90 CW)";
                    image.flip();
                    image.rotate(180.0);
                    image.rotate(90.0);
                    break;
                case 8:
                    desc = "Left side, bottom (Rotate 270 CW)";
                    image.rotate(270.0);
                    break;
            }
        } catch (Exception e) {
        }
    }

    public void printExifMetadata(Image image) {
        HashMap<Integer, Object> exif = image.getExifTags();

//Print Camera Info
        LoggerFactory.getLogger(this.getClass()).info("EXIF ScxFieldDTO: " + exif.size());
        LoggerFactory.getLogger(this.getClass()).info("-----------------------------");
        LoggerFactory.getLogger(this.getClass()).info("Date: " + exif.get(0x0132)); //0x9003
        LoggerFactory.getLogger(this.getClass()).info("Camera: " + exif.get(0x0110));
        LoggerFactory.getLogger(this.getClass()).info("Manufacturer: " + exif.get(0x010F));
        LoggerFactory.getLogger(this.getClass()).info("Focal Length: " + exif.get(0x920A));
        LoggerFactory.getLogger(this.getClass()).info("F-Stop: " + exif.get(0x829D));
        LoggerFactory.getLogger(this.getClass()).info("Exposure Time (1 / Shutter Speed): " + exif.get(0x829A));
        LoggerFactory.getLogger(this.getClass()).info("ISO Speed Ratings: " + exif.get(0x8827));
        LoggerFactory.getLogger(this.getClass()).info("Shutter Speed Value (APEX): " + exif.get(0x9201));
        LoggerFactory.getLogger(this.getClass()).info("Shutter Speed (Exposure Time): " + exif.get(0x9201));
        LoggerFactory.getLogger(this.getClass()).info("Aperture Value (APEX): " + exif.get(0x9202));

//Print Image Orientation
        try {
            int orientation = (Integer) exif.get(0x0112);
            String desc = "";
            switch (orientation) {
                case 1:
                    desc = "Top, left side (Horizontal / normal)";
                    break;
                case 2:
                    desc = "Top, right side (Mirror horizontal)";
                    break;
                case 3:
                    desc = "Bottom, right side (Rotate 180)";
                    break;
                case 4:
                    desc = "Bottom, left side (Mirror vertical)";
                    break;
                case 5:
                    desc = "Left side, top (Mirror horizontal and rotate 270 CW)";
                    break;
                case 6:
                    desc = "Right side, top (Rotate 90 CW)";
                    break;
                case 7:
                    desc = "Right side, bottom (Mirror horizontal and rotate 90 CW)";
                    break;
                case 8:
                    desc = "Left side, bottom (Rotate 270 CW)";
                    break;
            }
            LoggerFactory.getLogger(this.getClass()).info("Orientation: " + orientation + " -- " + desc);
        } catch (Exception e) {
        }


//Print GPS Information
        double[] coord = image.getGPSCoordinate();
        if (coord != null) {
            LoggerFactory.getLogger(this.getClass()).info("GPS Coordinate: " + coord[0] + ", " + coord[1]);
            LoggerFactory.getLogger(this.getClass()).info("GPS Datum: " + image.getGPSDatum());
        }
    }


    public ImageProperties getJpegProperties(File file) throws FileNotFoundException, IOException {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));

            // check for "magic" header
            byte[] buf = new byte[2];
            int count = in.read(buf, 0, 2);
            if (count < 2) {
                throw new RuntimeException("Not a valid Jpeg file!");
            }
            if ((buf[0]) != (byte) 0xFF || (buf[1]) != (byte) 0xD8) {
                throw new RuntimeException("Not a valid Jpeg file!");
            }

            int width = 0;
            int height = 0;
            char[] comment = null;

            boolean hasDims = false;
            boolean hasComment = false;
            int ch = 0;

            while (ch != 0xDA && !(hasDims && hasComment)) {
                /* Find next marker (JPEG markers begin with 0xFF) */
                while (ch != 0xFF) {
                    ch = in.read();
                }
                /* JPEG markers can be padded with unlimited 0xFF's */
                while (ch == 0xFF) {
                    ch = in.read();
                }
                /* Now, ch contains the value of the marker. */

                int length = 256 * in.read();
                length += in.read();
                if (length < 2) {
                    throw new RuntimeException("Not a valid Jpeg file!");
                }
                /* Now, length contains the length of the marker. */

                if (ch >= 0xC0 && ch <= 0xC3) {
                    in.read();
                    height = 256 * in.read();
                    height += in.read();
                    width = 256 * in.read();
                    width += in.read();
                    for (int foo = 0; foo < length - 2 - 5; foo++) {
                        in.read();
                    }
                    hasDims = true;
                } else if (ch == 0xFE) {
                    // that's the comment marker
                    comment = new char[length - 2];
                    for (int foo = 0; foo < length - 2; foo++)
                        comment[foo] = (char) in.read();
                    hasComment = true;
                } else {
                    // just skip marker
                    for (int foo = 0; foo < length - 2; foo++) {
                        in.read();
                    }
                }
            }
            return (new ImageProperties(width, height, comment, "jpeg"));

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * Decode string to image
     **/
    public BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Encode image to string
     **/
    public String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

//    public ImageProperties getGifProperties(File file) throws FileNotFoundException, IOException {
//        BufferedInputStream in = null;
//        try {
//            in = new BufferedInputStream(new FileInputStream(file));
//            byte[] buf = new byte[10];
//            int count = in.read(buf, 0, 10);
//            if (count < 10) {
//                throw new RuntimeException("Not a valid Gif file!");
//            }
//            if ((buf[0]) != (byte) 'G' || (buf[1]) != (byte) 'I' || (buf[2]) != (byte) 'F') {
//                throw new RuntimeException("Not a valid Gif file!");
//            }
//
//            int w1 = (buf[6] & 0xff) | (buf[6] & 0x80);
//            int w2 = (buf[7] & 0xff) | (buf[7] & 0x80);
//            int h1 = (buf[8] & 0xff) | (buf[8] & 0x80);
//            int h2 = (buf[9] & 0xff) | (buf[9] & 0x80);
//
//            int width = w1 + (w2 << 8);
//            int height = h1 + (h2 << 8);
//
//            return (new ImageProperties(width, height, null, "gif"));
//
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                }
//            }
//        }
//    }

    public ImageProperties getGifProperties(InputStream is) throws FileNotFoundException, IOException {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(is);
            byte[] buf = new byte[10];
            int count = in.read(buf, 0, 10);
            if (count < 10) {
                throw new RuntimeException("Not a valid Gif file!");
            }
            if ((buf[0]) != (byte) 'G' || (buf[1]) != (byte) 'I' || (buf[2]) != (byte) 'F') {
                throw new RuntimeException("Not a valid Gif file!");
            }

            int w1 = (buf[6] & 0xff) | (buf[6] & 0x80);
            int w2 = (buf[7] & 0xff) | (buf[7] & 0x80);
            int h1 = (buf[8] & 0xff) | (buf[8] & 0x80);
            int h2 = (buf[9] & 0xff) | (buf[9] & 0x80);

            int width = w1 + (w2 << 8);
            int height = h1 + (h2 << 8);

            return (new ImageProperties(width, height, null, "gif"));

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * @version $Id: ImageProperties.java 587751 2007-10-24 02:41:36Z vgritsenko $
     */
    public class ImageProperties {
        final public int width;
        final public int height;
        final public char[] comment;
        final public String type;

        public ImageProperties(int width, int height, char[] comment, String type) {
            this.width = width;
            this.height = height;
            this.comment = comment;
            this.type = type;
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append(type).append(" ").append(width).append("x").append(height);
            if (comment != null) {
                sb.append(" (").append(comment).append(")");
            }
            return (sb.toString());
        }
    }

}

