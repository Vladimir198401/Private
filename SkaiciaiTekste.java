import java.awt.*;        // Using AWT containers and components
import java.awt.event.*;  // Using AWT events classes and listener interfaces
import java.io.*;
 
// An AWT GUI program inherits the top-level container java.awt.Frame
public class SkaiciaiTekste extends Frame
	implements ActionListener, WindowListener {
	// This class acts as listener for ActionEvent and WindowEvent
	// A Java class can extend only one superclass, but it can implement multiple interfaces.
 
	private TextArea taDisplayTekstas;  // Declare a TextField component
	private Button btnNuskaityti;    // Declare a Button component
	private Button btnSkaitmenys;
	private Button btnSveiki;
	private Button btnRealus;
	private Button btnSuPrasme;
	
	private String s;
	private String [] ss;
	private String ABBA;
	private String NuskaitytasTekstas;
	private String Tekstas;
	private String [] sveiku_skaiciu_sar;
	private int n_sveiku_sk = 0;
	private String [] tekstas_po_simb;
	private int b = 0;
	private int t = 0;
	private int n = 0;
	private int kiekis_skaitmenu = 0;
		      
	// Constructor to setup the GUI components and event handlers
	public SkaiciaiTekste() {
			setLayout(new FlowLayout()); // "super" Frame sets to FlowLayout

			taDisplayTekstas = new TextArea(45, 70);  // Construct the TextField
			taDisplayTekstas.setEditable(false);       // read-only
			add(taDisplayTekstas);                     // "super" Frame adds TextField
		
			//add(new Label("Maks., sumos, kiekio, vid. skaiciavimas"));   // "super" Frame adds an anonymous Label
				btnNuskaityti = new Button("NuskaitytiTeksta");  // Construct the Button
				add(btnNuskaityti);     
				btnSkaitmenys = new Button("Skaitmenys");  // Construct the Button
				add(btnSkaitmenys);    
				btnSveiki = new Button("Sveiki skaiciai");  // Construct the Button
				add(btnSveiki);     
				btnRealus = new Button("Realus skaiciai");  // Construct the Button
				add(btnRealus);  
				btnSuPrasme = new Button("Skaiciai su ju prasme");  // Construct the Button
				add(btnSuPrasme);    
		
				btnNuskaityti.addActionListener(this);
				btnSkaitmenys.addActionListener(this);
				btnSveiki.addActionListener(this);
				btnRealus.addActionListener(this);
				btnSuPrasme.addActionListener(this);

		addWindowListener(this);
		// "super" Frame (source object) fires WindowEvent.
		// "super" Frame adds "this" object as a WindowEvent listener.

		setTitle("Teksto analize"); // "super" Frame sets title
		setSize(700, 750);            // "super" Frame sets initial size
		setVisible(true);             // "super" Frame shows
	}
	 
	// The entry main() method
	public static void main(String[] args) {
		
		new SkaiciaiTekste();  // Let the construct do the job
		
	}
	
	public static boolean yraSkaitmuo ( String simbolis ) {
		
		String[] skaitmenys = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		
		boolean yra_skaitmuo = false;
		
		for (int i = 0; i < 10; i++ ) {
		
			// System.out.println( "simbolis: " + simbolis + "\n" );
			
			if ( ( simbolis != null ) && simbolis.equals ( skaitmenys [ i ] ) ) {
			
				yra_skaitmuo = true;
			}
		}
		return yra_skaitmuo;
	}
	
	public void readFromFile () {
	   
		String s = null;
	
		try{
			
			FileReader fr=new FileReader( "tekstas.txt" );
			BufferedReader frx = new BufferedReader ( fr );
			
			String simb;
			NuskaitytasTekstas = "Pradedam darba \n\n";
			ss = new String [2000];
			t=0;
			n=0;
			tekstas_po_simb = new String [2000];
			while ( ( s = frx.readLine() ) != null ) {
				
				System.out.println( s );
				NuskaitytasTekstas += s + "\n";
				
				for ( int i = 0; i < s.length() - 1; i++ ) {
				
					simb =  s.substring( i, i+1 );
					tekstas_po_simb[n]=simb;
					n++;
					
					if ( yraSkaitmuo ( simb ) ) {
						
						kiekis_skaitmenu++;
						ss [ t ] = simb;
						t++;
					}
				}
			}
			fr.close();
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
	
	}
	
	public void showNumbersFromFile (){
		  
		Tekstas = "skaitmenis tekste: \n\n";
		for( int i = 0; i < t; i++){
			  
			Tekstas += ss[ i ] + " ";
		}
	}
	
	public void SveikiSkaiciai (){
		
		boolean radom_skaiciaus_pradzia = false;
		sveiku_skaiciu_sar = new String[ 2000 ];
		ABBA = " ";
		
		for ( int i = 0; i <= n; i++ ) {
			
			if ( yraSkaitmuo ( tekstas_po_simb [ i ] ) ) {
				
				if (  ! radom_skaiciaus_pradzia ) {
			 
					radom_skaiciaus_pradzia = true;	

					ABBA += tekstas_po_simb [ i ];
						
				} else {
			
					ABBA += tekstas_po_simb [ i ];				
				}
				
			} else {
				
				if ( radom_skaiciaus_pradzia ) {
					
					radom_skaiciaus_pradzia = false;
				    
					ABBA += " ";
				}
			}
		}
	}

	
	/* ActionEvent handler */
	@Override
	public void actionPerformed(ActionEvent evt) {
			readFromFile();
			
		if (evt.getActionCommand().equals("NuskaitytiTeksta")){
			
			String pradedam = "Pradedam darba\n\n";
			taDisplayTekstas.append("Tekstas:\n");
			//taDisplayTekstas.append( NuskaitytasTekstas );
			taDisplayTekstas.replaceRange(NuskaitytasTekstas , 0 , NuskaitytasTekstas.length());
			
			
		}	
		if (evt.getActionCommand().equals("Skaitmenys")){
			
			showNumbersFromFile();
			taDisplayTekstas.append("Skaiciai:\n");
			taDisplayTekstas.replaceRange(Tekstas, 0 , NuskaitytasTekstas.length());
			
		}
		if (evt.getActionCommand().equals("Sveiki skaiciai")){
			
			SveikiSkaiciai();
			taDisplayTekstas.append( "Surasti sveiki skaiciai:\n" );		
			taDisplayTekstas.replaceRange( ABBA + " " , 0 , NuskaitytasTekstas.length());
		}
		/*
		if (evt.getActionCommand().equals("Realus skaiciai")){
			taDisplayTekstas.append("Kas");
			taDisplayTekstas.append(showTekstasFromFile());
			
		}
		if (evt.getActionCommand().equals("Skaiciai su ju prasme")){
			taDisplayTekstas.append("Kas");			
			taDisplayTekstas.append(showTekstasFromFile());
			
		}
		*/
		
			/*sudarytiSkaiciuMasyva();
			taDisplayTekstas.append("Nuskaityta " + (nr_skaiciu) + " skaiciu. \n\n");
			taDisplayTekstas.append("Maksimali reiksme skaiciu sekoje: " + maksReiksmeSkaiciuMasyve() + "\n");
			taDisplayTekstas.append("Skaiciu suma: " + skaiciuMasyveSuma() + "\n");
			masyvoVidurkis();
			taDisplayTekstas.append("Vidurkis: " + vidurkis + "\n\n");
			masyvoSkaiciaiDidesniUzVidurki();
			taDisplayTekstas.append("Skaiciai didesni uz vidurki:\n\n " + didesniu_uz_vid_sarasas + "\n");
			taDisplayTekstas.append("likusiu skaiciu suma: " + maz_uz_vid_suma);*/
	}
 
   /* WindowEvent handlers */
   // Called back upon clicking close-window button
   @Override
   public void windowClosing(WindowEvent evt) {
      System.exit(0);  // Terminate the program
   }
 
   // Not Used, BUT need to provide an empty body to compile.
   @Override public void windowOpened(WindowEvent evt) { }
   @Override public void windowClosed(WindowEvent evt) { }
   // For Debugging
   @Override public void windowIconified(WindowEvent evt) { System.out.println("Window Iconified"); }
   @Override public void windowDeiconified(WindowEvent evt) { System.out.println("Window Deiconified"); }
   @Override public void windowActivated(WindowEvent evt) { System.out.println("Window Activated"); }
   @Override public void windowDeactivated(WindowEvent evt) { System.out.println("Window Deactivated"); }
}