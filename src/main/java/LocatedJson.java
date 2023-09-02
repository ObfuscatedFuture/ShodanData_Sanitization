public class LocatedJson
{
    String ipAddress;
    String response;
    String countryName;
    String stateName;
    String cityName;
    Double latitude;
    Double longitude;
    Integer accuracy;
    String timezone;
    String userType;

    public LocatedJson(String _ipAddress, String _countryName, String _stateName, String _cityName, Double _latitude, Double _longitude, Integer _accuracy, String _timezone, String _userType)
    {
        ipAddress = _ipAddress;
        countryName = _countryName;
        stateName = _stateName;
        cityName = _cityName;
        latitude = _latitude;
        longitude = _longitude;
        accuracy = _accuracy;
        timezone = _timezone;
        userType = _userType;
    }

    @Override
    public String toString() {
        return "LocatedJson{" +
                "ipAddress='" + ipAddress + '\'' +
                ", response='" + response + '\'' +
                ", countryName='" + countryName + '\'' +
                ", stateName='" + stateName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", accuracy=" + accuracy +
                ", timezone='" + timezone + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
