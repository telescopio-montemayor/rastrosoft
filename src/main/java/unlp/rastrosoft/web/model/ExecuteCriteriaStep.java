package unlp.rastrosoft.web.model;
public class ExecuteCriteriaStep {

        String id_sequence, number, ra, declination, exposureTime, hBinning, vBinning, frameType, x, y, width, height, focusPosition, quantity, delay;

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getId_sequence() {
        return id_sequence;
    }

    public void setId_sequence(String id_sequence) {
        this.id_sequence = id_sequence;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getDeclination() {
        return declination;
    }

    public void setDeclination(String declination) {
        this.declination = declination;
    }

    public String getExposureTime() {
        return exposureTime;
    }

    public void setExposureTime(String exposureTime) {
        this.exposureTime = exposureTime;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

        
        
    @Override
    public String toString() {
            return "SearchCriteria [id_sequence=" + id_sequence + ", number=" + number + ", ra=" + ra + ", declination=" + declination  + ", exposureTime=" + exposureTime + ", hBinning=" + hBinning + ", vBinning=" + vBinning + ", frameType=" + frameType + ", x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", focusPosition=" + focusPosition + ", quantity=" + quantity + ", delay=" + delay + "]";
    }

}
