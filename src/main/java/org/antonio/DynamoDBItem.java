package org.antonio;

public class DynamoDBItem {
    private String cultivationName;
    private String deviceIds;
    private String measureDate;
    private String temperature;
    private String humidity;

    public DynamoDBItem(String cultivationName, String deviceIds, String measureDate, String temperature, String humidity) {
        this.cultivationName = cultivationName;
        this.deviceIds = deviceIds;
        this.measureDate = measureDate;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getCultivationName() {
        return cultivationName;
    }

    public void setCultivationName(String cultivationName) {
        this.cultivationName = cultivationName;
    }

    public String getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(String deviceIds) {
        this.deviceIds = deviceIds;
    }

    public String getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(String measureDate) {
        this.measureDate = measureDate;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
