import java.awt.*;
import java.awt.event.*;
 
// An AWT GUI program inherits from the top-level container java.awt.Frame
public class AwtDemo1 extends Frame implements KeyListener, WindowListener{
      // This class acts as KeyEvent Listener
 
   private TextField tfInput;  // Single-line TextField to receive tfInput key
   private TextArea taDisplay; // Multi-line TextArea to taDisplay result
	
   //private Button btnClosed;
   // Constructor to setup the GUI components and event handlers
   public AwtDemo1() {
      setLayout(new FlowLayout()); // "super" frame sets to FlowLayout
 
      add(new Label("Lauzas: "));
      tfInput = new TextField(5);
      add(tfInput);
      taDisplay = new TextArea(5, 40); // 5 rows, 40 columns
      add(taDisplay);
 
      tfInput.addKeyListener(this);
         // tfInput TextField (source) fires KeyEvent.
         // tfInput adds "this" object as a KeyEvent listener.
	   
	      addWindowListener(this);
        // "super" Frame (source object) fires WindowEvent.
        // "super" Frame adds "this" object as a WindowEvent listener.
 
      setTitle("KeyEvent Demo"); // "super" Frame sets title
      setSize(400, 300);         // "super" Frame sets initial size
      setVisible(true);          // "super" Frame shows
   }
 
   // The entry main() method
   public static void main(String[] args) {
      new AwtDemo1();  // Let the constructor do the job
   }
   /** KeyEvent handlers */
   // Called back when a key has been typed (pressed and released)
   @Override
   public void keyTyped(KeyEvent evt) {
      taDisplay.append("You have typed " + evt.getKeyChar() + "\n");
   }
 @Override
   public void windowClosing(WindowEvent evt) {
      System.exit(0);  // Terminate the program
   }
 
   // Not Used, BUT need to provide an empty body to compile.
   @Override public void windowOpened(WindowEvent evt) { }
   @Override public void windowClosed(WindowEvent evt) { }
   // Not Used, but need to provide an empty body for compilation
   @Override public void keyPressed(KeyEvent evt) { }
  @Override public void keyReleased(KeyEvent evt) { }
   // For Debugging
   @Override public void windowIconified(WindowEvent evt) { System.out.println("Window Iconified"); }
   @Override public void windowDeiconified(WindowEvent evt) { System.out.println("Window Deiconified"); }
   @Override public void windowActivated(WindowEvent evt) { System.out.println("Window Activated"); }
   @Override public void windowDeactivated(WindowEvent evt) { System.out.println("Window Deactivated"); }
}