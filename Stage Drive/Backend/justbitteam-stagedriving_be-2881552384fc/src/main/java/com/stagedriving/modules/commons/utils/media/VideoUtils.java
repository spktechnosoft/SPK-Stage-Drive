package com.stagedriving.modules.commons.utils.media;

import com.google.inject.Singleton;
import com.justbit.commons.TokenUtils;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by simone on 30/11/14.
 */
@Singleton
public class VideoUtils {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(VideoUtils.class);

    public String getVideoThumbnailFromByte(String videoUri, String tmpPath) {
        return null;
    }

    // Call ffmpegthumbnailer -i<inputfile> -o<outputpng> -s0 -f
    public String getVideoThumbnail(String videoPath, String tmpPath) throws InterruptedException, TimeoutException, IOException {
        String output = tmpPath + TokenUtils.generateUid() + ".png";

        int exit = new ProcessExecutor().command("ffmpegthumbnailer", "-i"+videoPath, "-o"+output, "-s0", "-t2%")
                .execute().getExitValue();

        if (exit == 0) {
            return output;
        }
        return null;
    }

    public File convertVideo(File file, String tmpPath) throws Exception {
        String output = tmpPath + TokenUtils.generateUid() + ".mpg";

        int exit = new ProcessExecutor().command("ffmpeg", "-i", file.getAbsolutePath(), "-c", "copy", "-an", "-bsf:v", "h264_mp4toannexb", "-f", "mpegts", output)
                .execute().getExitValue();

        if (exit != 0) {
            throw new Exception("Unable to convert video "+file.getAbsolutePath());
        }
        return new File(output);
    }

    /**
     * 1. ffmpeg -i 20170126_165027.mp4 -c copy -an -bsf:v h264_mp4toannexb -f mpegts camera.mpg
     * 2. ffmpeg -i "concat:/Users/simone/Downloads/intro.mp4|/Users/simone/Downloads/camera.mpg|/Users/simone/Downloads/ending_1.mp4" -c copy output.mpg
     *
     * @param videos
     * @param tmpPath
     * @return
     */
    public File concatenateVideos(List<File> videos, String tmpPath) throws Exception {
        String output = tmpPath + TokenUtils.generateUid() + ".mp4";

        String concatVideos = "concat:";
        for (File video : videos) {
            if (!video.exists()) {
                throw new Exception("Video "+video.getAbsolutePath()+" does not exist");
            }
            concatVideos += video.getAbsolutePath()+"|";
        }
        concatVideos = concatVideos.substring(0, concatVideos.length()-1);
        concatVideos += "";

        ProcessResult processResult = new ProcessExecutor().readOutput(true).command("ffmpeg", "-i", concatVideos, "-c", "copy", output)
                .execute();

        int exit = processResult.getExitValue();

        if (exit != 0) {
            throw new Exception("Unable to merge videos: "+processResult.getOutput().getString());
        }
        return new File(output);
    }

    /**
     * http://hamelot.io/visualization/using-ffmpeg-to-convert-a-set-of-images-into-a-video/
     *
     * 1. ffmpeg -r 25 -f image2 -s 1920x1080 -pattern_type glob -i 'Schermata*.png' -vcodec libx264 -crf 25  -pix_fmt yuv420p test.mp4
     *  @param bundleDir
     * @param tmpPath
     * @param frameSize
     * @param videoFrame @return
     * @param width
     * @param height
     */
    public File generateVideoFromImages(String bundleDir, List<String> images, String tmpPath, Integer frameSize, Integer videoFrame, Integer loop, Integer width, Integer height) throws Exception {
        String output = tmpPath + TokenUtils.generateUid() + ".mp4";

        if (loop != null && loop == 1) {
            // TODO
//            List<String> imgs = new ArrayList<>(images);
//            Collections.reverseOrder(imgs)
//            images.addAll()
        }

        // Duplicare i file
        int counter = 0;
        for (String image : images) {

            File file = new File(image);
            if (!file.exists()) {
                throw new Exception("Unable to duplicate file "+image);
            }

            for (int i=0; i<frameSize; i++) {
                String str = String.format("file://"+bundleDir+File.separator+"%08d", counter);
                Path newlink = Paths.get(URI.create(str));
                Path existing = Paths.get(URI.create("file://"+image));
                Path link = Files.createLink(newlink, existing);

                counter++;
            }
        }

        String dir = bundleDir+"/*";

        ProcessResult processResult = new ProcessExecutor().readOutput(true).command("ffmpeg", "-y", "-r", videoFrame.toString(), "-f", "image2", "-s", width+"x"+height, "-pattern_type", "glob", "-i", dir, "-vcodec", "libx264", "-crf", "25", "-pix_fmt", "yuv420p", output)
                .execute();

        int exit = processResult.getExitValue();

        if (exit != 0) {
            throw new Exception("Unable to generate video: "+processResult.getOutput().getString());
        }

        return new File(output);
    }

    /**
     * 1. ffmpeg -i Snapchat-18112799.mp4 -i logo_summit-1.png -filter_complex "overlay=0:0" Snapchat-18112799-W.mp4
     *
     * @param video
     * @param overlay
     * @param tmpPath
     * @return
     */
    public File applyOverlay(File video, File overlay, String tmpPath) throws Exception {
        String output = tmpPath + TokenUtils.generateUid() + ".mp4";

        ProcessResult processResult = new ProcessExecutor().readOutput(true).command("ffmpeg", "-i", video.getAbsolutePath(), "-i", overlay.getAbsolutePath(), "-filter_complex", "overlay=0:0", output)
                .execute();

        int exit = processResult.getExitValue();

        if (exit != 0) {
            throw new Exception("Unable to apply overlay: "+processResult.getOutput().getString());
        }
        return new File(output);
    }

}
