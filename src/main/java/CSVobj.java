public class CSVobj
{
    public long ip;
    public String ip_str;
    public int port;
    public String timestamp;
    public CSVobj()
    {

    }
    public CSVobj(long ip, String ipStr, int port, String timestamp)
    {
        this.ip = ip;
        this.ip_str = ipStr;
        this.port = port;
        this.timestamp = timestamp;
    }
    public long getIP()
    {
        return ip;
    }
    public String getIPStr()
    {
        return ip_str;
    }
    public int getPort()
    {
        return port;
    }
    public String getTimestamp()
    {
        return timestamp;
    }



}
