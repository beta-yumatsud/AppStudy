package example.android.bindservicetestsample;

import example.android.bindservicetestsample.ActivityAIDL;

interface ServiceAIDL{
    void startService(ActivityAIDL callback);
    void stopService();
    void resetService(ActivityAIDL callback);
}