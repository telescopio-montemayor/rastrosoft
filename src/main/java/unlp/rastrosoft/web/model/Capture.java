/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;

/**
 *
 * @author ip300
 */
public class Capture {
    private String id, datetime, ra, dec, hBinning, vBinning, temperature, frameType, x, y, 
       width, height, focusPosition, exposureTime, filePath;

    public Capture(String id, String datetime, String ra, String dec, String hBinning, String vBinning, String temperature, String frameType, String x, String y, String width, String height, String focusPosition, String exposureTime, String filePath) {
        this.id = id;
        this.datetime = datetime;
        this.ra = ra;
        this.dec = dec;
        this.hBinning = hBinning;
        this.vBinning = vBinning;
        this.temperature = temperature;
        this.frameType = frameType;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.focusPosition = focusPosition;
        this.exposureTime = exposureTime;
        this.filePath = filePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String gethBinning() {
        return hBinning;
    }

    public void sethBinning(String hBinning) {
        this.hBinning = hBinning;
    }

    public String getvBinning() {
        return vBinning;
    }

    public void setvBinning(String vBinning) {
        this.vBinning = vBinning;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getFrameType() {
        return frameType;
    }

    public void setFrameType(String frameType) {
        this.frameType = frameType;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getFocusPosition() {
        return focusPosition;
    }

    public void setFocusPosition(String focusPosition) {
        this.focusPosition = focusPosition;
    }

    public String getExposureTime() {
        return exposureTime;
    }

    public void setExposureTime(String exposureTime) {
        this.exposureTime = exposureTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
}
