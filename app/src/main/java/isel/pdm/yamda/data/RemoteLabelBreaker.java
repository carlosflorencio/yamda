package isel.pdm.yamda.data;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class RemoteLabelBreaker {

    private HandlerThread handlerThread;
    private Handler handler;

    public static class MyHandler extends Handler {
        public static int GET_KEY = 1;

        public MyHandler(Looper l) {
                  super(l);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                     LabelCallback cb = (LabelCallback)msg.obj;
                    try {
                        String res = new RemoteLabelBreaker().getRemoteLabel(msg.arg1);
                        cb.onResult(res, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                        cb.onResult(null, e);
                    }
            }
        }


    }

    public RemoteLabelBreaker() {
        handlerThread = new HandlerThread("nome");
    }

    public interface LabelCallback {
        void onResult(String label, Exception e);
    }

    public void start() {
        handlerThread.start();

        handler = new MyHandler(handlerThread.getLooper());
    }

    public void getLabel(int key, LabelCallback cb) {
        Message m = new Message();
        m.what = 1;
        m.arg1 = key;
        m.obj = cb;
        handler.sendMessage(m);
    }






    public String getRemoteLabel(int key) throws Exception {
        return "";
    }


}
