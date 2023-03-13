package hello;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;
import java.io.*;
public class RMSTest extends MIDlet implements CommandListener{
    Command exitCommand=new Command("Cikis",Command.EXIT,2);
    Command writeCommand=new Command("Yaz",Command.OK,1);
    TextField nameField;
    TextField ageField;
    public void startApp() {
        Form form=new Form("Liste");
        nameField=new TextField("isim","",30,TextField.ANY);
        ageField=new TextField("yas","",30,TextField.NUMERIC);
        form.append(nameField);
        form.append(ageField);
        form.addCommand(writeCommand);
        form.addCommand(exitCommand);
        form.setCommandListener(this);
        readNameAndAge();
        Display.getDisplay(this).setCurrent(form);
          }
    public void pauseApp() {
    }
    public void destroyApp(boolean unconditional) {
    }
    public void commandAction(Command c,Displayable d){
        if(c==exitCommand){
        destroyApp(false);
        notifyDestroyed();
        }else if(c==writeCommand){
        String name=nameField.getString();
        String age=ageField.getString();
        try{
                writeNameAndAge(name,Integer.parseInt(age));
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
        }
      }
    public void writeNameAndAge(String name,int age){
    try {
        RecordStore store=RecordStore.openRecordStore("profile",true);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(baos);
        dos.writeUTF(name);
        dos.writeInt(age);
        byte[] data=baos.toByteArray();
        if(store.getNumRecords()>0){
            store.setRecord(1,data,0,data.length);
        }else{
            store.addRecord(data,0,data.length);
            }
    }catch (Exception e){
        e.printStackTrace();
    }
    }
    private void readNameAndAge(){
        try{
        RecordStore store=RecordStore.openRecordStore("profile",false);
        byte[] record=store.getRecord(1);
        ByteArrayInputStream bais=new ByteArrayInputStream(record);
        DataInputStream dis=new DataInputStream(bais)        ;
        String name=dis.readUTF();
        int age=dis.readInt();
        nameField.setString(name);
        ageField.setString(Integer.toString(age));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
