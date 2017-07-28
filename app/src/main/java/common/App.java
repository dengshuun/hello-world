package common;

/**
 * Created by ds on 2017/7/25.
 * app
 */

public class App {

    int mImageId;
    int mAppName;
    private String mPackageName;

    public App(int imageId, int appName){
        mImageId = imageId;
        mAppName = appName;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public void setPackageName(String mPackageName) {
        this.mPackageName = mPackageName;
    }
}
