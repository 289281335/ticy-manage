package com.tiantu.data.server.pushquery.entity;

import java.util.Date;

public class imagePackage {
    private String id;

    private String batchId;

    private String satelliteAbbr;

    private String sensorAbbr;

    private String fileName;

    private String originPath;

    private String storePath;

    private String outlinePath;

    private Double storeSpace;

    private Date receiveTime;

    private Double xTopLeft;

    private Double yTopLeft;

    private Double xTopRight;

    private Double yTopRight;

    private Double xBottomRight;

    private Double yBottomRight;

    private Double xBottomLeft;

    private Double yBottomLeft;

    private Object geom;

    private Float cloud;

    private Float resolutionMax;

    private Integer orbitNum;

    private Integer scenePath;

    private Integer sceneRow;

    private String productNum;

    private String checksum;

    private Integer status;

    private Date startTime;

    private Date endTime;

    private Integer progress;

    private String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public String getSatelliteAbbr() {
        return satelliteAbbr;
    }

    public void setSatelliteAbbr(String satelliteAbbr) {
        this.satelliteAbbr = satelliteAbbr == null ? null : satelliteAbbr.trim();
    }

    public String getSensorAbbr() {
        return sensorAbbr;
    }

    public void setSensorAbbr(String sensorAbbr) {
        this.sensorAbbr = sensorAbbr == null ? null : sensorAbbr.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getOriginPath() {
        return originPath;
    }

    public void setOriginPath(String originPath) {
        this.originPath = originPath == null ? null : originPath.trim();
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath == null ? null : storePath.trim();
    }

    public String getOutlinePath() {
        return outlinePath;
    }

    public void setOutlinePath(String outlinePath) {
        this.outlinePath = outlinePath == null ? null : outlinePath.trim();
    }

    public Double getStoreSpace() {
        return storeSpace;
    }

    public void setStoreSpace(Double storeSpace) {
        this.storeSpace = storeSpace;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Double getxTopLeft() {
        return xTopLeft;
    }

    public void setxTopLeft(Double xTopLeft) {
        this.xTopLeft = xTopLeft;
    }

    public Double getyTopLeft() {
        return yTopLeft;
    }

    public void setyTopLeft(Double yTopLeft) {
        this.yTopLeft = yTopLeft;
    }

    public Double getxTopRight() {
        return xTopRight;
    }

    public void setxTopRight(Double xTopRight) {
        this.xTopRight = xTopRight;
    }

    public Double getyTopRight() {
        return yTopRight;
    }

    public void setyTopRight(Double yTopRight) {
        this.yTopRight = yTopRight;
    }

    public Double getxBottomRight() {
        return xBottomRight;
    }

    public void setxBottomRight(Double xBottomRight) {
        this.xBottomRight = xBottomRight;
    }

    public Double getyBottomRight() {
        return yBottomRight;
    }

    public void setyBottomRight(Double yBottomRight) {
        this.yBottomRight = yBottomRight;
    }

    public Double getxBottomLeft() {
        return xBottomLeft;
    }

    public void setxBottomLeft(Double xBottomLeft) {
        this.xBottomLeft = xBottomLeft;
    }

    public Double getyBottomLeft() {
        return yBottomLeft;
    }

    public void setyBottomLeft(Double yBottomLeft) {
        this.yBottomLeft = yBottomLeft;
    }

    public Object getGeom() {
        return geom;
    }

    public void setGeom(Object geom) {
        this.geom = geom;
    }

    public Float getCloud() {
        return cloud;
    }

    public void setCloud(Float cloud) {
        this.cloud = cloud;
    }

    public Float getResolutionMax() {
        return resolutionMax;
    }

    public void setResolutionMax(Float resolutionMax) {
        this.resolutionMax = resolutionMax;
    }

    public Integer getOrbitNum() {
        return orbitNum;
    }

    public void setOrbitNum(Integer orbitNum) {
        this.orbitNum = orbitNum;
    }

    public Integer getScenePath() {
        return scenePath;
    }

    public void setScenePath(Integer scenePath) {
        this.scenePath = scenePath;
    }

    public Integer getSceneRow() {
        return sceneRow;
    }

    public void setSceneRow(Integer sceneRow) {
        this.sceneRow = sceneRow;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum == null ? null : productNum.trim();
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum == null ? null : checksum.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }
}