package mahlabs.l8retainedfragment;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import mahlabs.l8retainedfragment.expressions.Expression;

/**
 * Created by tsroax on 2014-09-30.
 */
public class Controller {
    private MainActivity activity;
    private MainFragment mainFragment;
    private TCPConnection connection;
    private boolean connected = false;
    private ReceiveListener listener;


    public Controller(MainActivity activity, MainFragment mainFragment, TCPConnection connection) {
        this.activity = activity;
        this.mainFragment = mainFragment;
        this.connection=connection;
        this.connection.setListener(new RL());
        if(connection.isRunning()){
            connected = true;
            mainFragment.setConnectEnabled(false);
            mainFragment.setDisconnectEnabled(true);
            mainFragment.setSendEnabled(true);
        }else{
            connected = false;
            mainFragment.setConnectEnabled(true);
            mainFragment.setDisconnectEnabled(false);
            mainFragment.setSendEnabled(false);
        }

        mainFragment.setController(this);

    }

    public void connectClicked() {
        connection.connect();
    }

    public void disconnectClicked() {
        if(connected) {
            connection.disconnect();
        }
    }

    public void sendClicked() {
        char operation;
        try {
            int number1 = Integer.parseInt(mainFragment.getNbr1());
            int number2 = Integer.parseInt(mainFragment.getNnr2());
            String operStr = mainFragment.getOperation();
            if(operStr.length()>0)
                operation = operStr.charAt(0);
            else
                operation = '?';
            Expression exp = new Expression(number1,operation,number2);
            connection.send(exp);
        }catch(NumberFormatException e) {
            listener.newMessage("Bad arguments: " +mainFragment.getNbr1() +
                    ", " + mainFragment.getNnr2());
        }
    }

    private class RL implements ReceiveListener {
        public void newMessage(final String answer) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    String message = answer;
                    Exception e = connection.getException();
                    if ("CONNECTED".equals(answer)) {
                        mainFragment.setConnectEnabled(false);
                        mainFragment.setDisconnectEnabled(true);
                        mainFragment.setSendEnabled(true);
                        connected = true;
                    } else if ("CLOSED".equals(answer)) {
                        mainFragment.setConnectEnabled(true);
                        mainFragment.setDisconnectEnabled(false);
                        mainFragment.setSendEnabled(false);
                        connected = false;
                    } else if ("EXCEPTION".equals(answer) && e != null) {
                        message = e.toString();
                    }
                    mainFragment.setResult(message);
                }
            });
        }
    }

}
