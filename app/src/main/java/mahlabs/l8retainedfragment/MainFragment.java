package mahlabs.l8retainedfragment;



import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ag6505.network2.R;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MainFragment extends Fragment {
    private Controller controller;
    private EditText edNumber1;
    private EditText edNumber2;
    private EditText edOperation;
    private TextView tvResult;
    private Button btnConnect;
    private Button btnDisconnect;
    private Button btnSend;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initializeComponents(view);
        registerListeners();
        return view;
    }

    private void initializeComponents(View view) {
        edNumber1 = (EditText)view.findViewById(R.id.edNumber1);
        edNumber2 = (EditText)view.findViewById(R.id.edNumber2);
        edOperation = (EditText)view.findViewById(R.id.edOperation);
        tvResult = (TextView)view.findViewById(R.id.tvResult);
        btnConnect = (Button)view.findViewById(R.id.btnConnect);
        btnDisconnect = (Button)view.findViewById(R.id.btnDisconnect);
        btnSend = (Button)view.findViewById(R.id.btnSend);
    }

    private void registerListeners() {
        btnConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                controller.connectClicked();
            }
        });
        btnDisconnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                controller.disconnectClicked();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                controller.sendClicked();
            }
        });
    }


    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setResult(String message) {
        tvResult.setText(message);
    }

    public void setSendEnabled(boolean b) {
        btnSend.setEnabled(b);
    }

    public void setDisconnectEnabled(boolean b) {
        btnDisconnect.setEnabled(b);
    }

    public void setConnectEnabled(boolean b) {
        btnConnect.setEnabled(b);
    }

    public String getNbr1() {
        return edNumber1.getText().toString();
    }

    public String getNnr2() {
        return edNumber2.getText().toString();
    }

    public String getOperation() {
        return edOperation.getText().toString();
    }
}
