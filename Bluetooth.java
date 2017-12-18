import jssc.SerialPort;
import jssc.SerialPortException;
import java.util.*;
import java.util.concurrent.*;

interface BluetoothDataReceivedListener
{
    void dataReceived(byte[] data);
}
// https://stackoverflow.com/questions/6270132/create-a-custom-event-in-java
public class Bluetooth
{
    private final SerialPort serialPort;
    private List<BluetoothDataReceivedListener> listeners = new ArrayList<BluetoothDataReceivedListener>();
    private ScheduledExecutorService executor;
    private Runnable readTask;
    
    public Bluetooth(String port)
    {
        serialPort = new SerialPort(port);
        readTask = () -> // new runnable { void run {}}
        {
            byte[] data = read();
            if (data != null && data.length > 0) onDataReceived(data);
        };
    }
    
    public boolean open()
    {
        if (serialPort.isOpened()) return true;
        
        try
        {
            serialPort.openPort();
            serialPort.setParams(   SerialPort.BAUDRATE_115200,
                                    SerialPort.DATABITS_8,
                                    SerialPort.STOPBITS_1,
                                    SerialPort.PARITY_NONE);
            return true;
        }
        catch(SerialPortException ex) {}
        catch (Exception ex) {}
        return false;
    }
    
    public void close()
    {
        try 
        {
            serialPort.closePort();
        }
        catch (SerialPortException ex) {}
        catch (Exception ex) {}
    }
    
    public boolean sendData(byte[] data)
    {
        boolean sent = false;
        try { sent = serialPort.writeBytes(data); }
        catch (SerialPortException ex) {}
        catch (Exception ex) {}
        return sent;
    }
    
    public void startListening()
    {
        if (executor != null)
        {
            executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(readTask, 0, 1, TimeUnit.SECONDS);
        }
    }
    
    public void stopListening()
    {
        if (!executor.isShutdown())
        {
            executor.shutdown();
        }
    }
    
    public void addDataReceivedListener(BluetoothDataReceivedListener listener)
    {
        listeners.add(listener);
    }
    
    public byte[] read()
    {
        try
        {
            return serialPort.readBytes();
        }
        catch (SerialPortException ex) {}
        catch (Exception ex) {}
        return null;
    }
    
    private void onDataReceived(byte[] data)
    {
        for (BluetoothDataReceivedListener l : listeners)
            l.dataReceived(data);
    }
}