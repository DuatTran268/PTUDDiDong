package Model;

public class TrendingModel {
    private String Ttitle;
    private String Turl;
    private String Tvid;


    public TrendingModel() {
    }


    public TrendingModel(String ttitle, String turl, String tvid) {
        Ttitle = ttitle;
        Turl = turl;
        Tvid = tvid;
    }

    public String getTtitle() {
        return Ttitle;
    }

    public void setTtitle(String ttitle) {
        Ttitle = ttitle;
    }

    public String getTurl() {
        return Turl;
    }

    public void setTurl(String turl) {
        Turl = turl;
    }

    public String getTvid() {
        return Tvid;
    }

    public void setTvid(String tvid) {
        Tvid = tvid;
    }
}
