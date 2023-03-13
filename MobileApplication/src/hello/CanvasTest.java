package hello;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
public class CanvasTest extends MIDlet implements CommandListener{
    Command exitCommand=new Command("EXIT",Command.EXIT,1);
    
    public void startApp() {
        TestCanvas canvas=new TestCanvas();
        canvas.addCommand(exitCommand);
        canvas.setCommandListener(this);
        Display.getDisplay(this).setCurrent(canvas);
    }
    public void commandAction(Command c,Displayable d){
        if(c==exitCommand){
        destroyApp(true);
        notifyDestroyed();
             }
    }
        public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    public class TestCanvas extends Canvas{
        public void paint(Graphics g){
        g.drawRect(10,10,50,50);
        }
    }
}

