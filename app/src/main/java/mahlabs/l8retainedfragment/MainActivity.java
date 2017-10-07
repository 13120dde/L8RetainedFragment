package mahlabs.l8retainedfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import com.example.ag6505.network2.R;

public class MainActivity extends Activity {
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        TCPConnection connection = (TCPConnection) fm.findFragmentByTag(TCPConnection.TAG_TCP_Fragment);
        MainFragment mainFragment = (MainFragment)fm.findFragmentById(R.id.fragment);

        if(connection==null){
            connection = new TCPConnection();
            connection.setIP("195.178.227.53");
            connection.setPort(9384);
            fm.beginTransaction().add(connection,TCPConnection.TAG_TCP_Fragment).commit();
        }

        controller = new Controller(this,mainFragment,connection);

    }

}
