public class CSVobj
{
    public int ip;
    public String ip_str;
    public int port;
    public String timestamp;
    public CSVobj()
    {

    }
    public CSVobj(int ip, String ipStr, int port, String timestamp)
    {
        this.ip = ip;
        this.ip_str = ipStr;
        this.port = port;
        this.timestamp = timestamp;
    }
    public int getIP()
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



}
