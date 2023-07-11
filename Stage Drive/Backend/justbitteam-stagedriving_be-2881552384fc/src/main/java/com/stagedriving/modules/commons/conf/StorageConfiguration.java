/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.conf;

public class StorageConfiguration {

//    @NotEmpty
//    private String accessKey;
//
//    @NotEmpty
//    private String secretKey;
//
    private String endpoint = "s3.amazonaws.com";
//
//    private boolean pathStyle = false;
//
//    private boolean useSsl = false;
//
//    private int port;

    private String tempFilePath = "/tmp/scx/";

    private String documentBucket = "scx-api.documents";

    private String photoBucket = "scx-api.photos";

    private String videoBucket = "scx-api.videos";

    private String eventBucket = "scx-api.events";

    private String qrcodeBucket = "scx-api.qrcodes";

    private String templateBucket = "scx-api.template";

    private String reportBucket = "scx-api.reports";

    private String staticImgBucket = "scx.static.images";

    public String getVideoBucket() {
        return videoBucket;
    }

    public void setVideoBucket(String videoBucket) {
        this.videoBucket = videoBucket;
    }

    public String getReportBucket() {
        return reportBucket;
    }

    public void setReportBucket(String reportBucket) {
        this.reportBucket = reportBucket;
    }

    public String getTemplateBucket() {
        return templateBucket;
    }

    public void setTemplateBucket(String templateBucket) {
        this.templateBucket = templateBucket;
    }

    public String getQrcodeBucket() {
        return qrcodeBucket;
    }

    public void setQrcodeBucket(String qrcodeBucket) {
        this.qrcodeBucket = qrcodeBucket;
    }

    public String getPhotoBucket() {
        return photoBucket;
    }

    public void setPhotoBucket(String photoBucket) {
        this.photoBucket = photoBucket;
    }

    public String getEventBucket() {
        return eventBucket;
    }

    public void setEventBucket(String eventBucket) {
        this.eventBucket = eventBucket;
    }

    public String getTempFilePath() {
        return tempFilePath;
    }

    public void setTempFilePath(String tempFilePath) {
        this.tempFilePath = tempFilePath;
    }

    public String getStaticImgBucket() {
        return staticImgBucket;
    }

    public void setStaticImgBucket(String staticImgBucket) {
        this.staticImgBucket = staticImgBucket;
    }

    public String getDocumentBucket() {
        return documentBucket;
    }

    public void setDocumentBucket(String documentBucket) {
        this.documentBucket = documentBucket;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}