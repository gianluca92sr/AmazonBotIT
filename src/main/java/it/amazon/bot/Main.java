package it.amazon.bot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import it.amazon.bot.util.AzioniDOM;
import it.amazon.bot.util.ReadData;
import it.amazon.bot.validator.MyInputVerifier;

public class Main {
	private static JTextField textField;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JPasswordField passwordField;
	private static JLabel status = new JLabel("", JLabel.CENTER);
	
	public static void main(String[] args) {
		
        try {
			UIManager.setLookAndFeel(
			        UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (InstantiationException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			e2.printStackTrace();
		} catch (UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}
        
		JFrame frame = new JFrame("AmazonITBot");
		frame.setResizable(false);
		frame.setMaximumSize(new Dimension(800, 400));
		frame.setMinimumSize(new Dimension(800, 400));
		frame.setPreferredSize(new Dimension(800, 600));
		frame.getContentPane().setSize(new Dimension(800, 600));
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setSize(947, 666);
		frame.setBounds(100,100,400,300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(status, BorderLayout.SOUTH);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_3.setColumns(10);
		
		((AbstractDocument)textField_3.getDocument()).setDocumentFilter(new DocumentFilter(){
	        Pattern regEx = Pattern.compile("\\d*");

	        @Override
	        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {          
	            Matcher matcher = regEx.matcher(text);
	            if(!matcher.matches()){
	                return;
	            }
	            super.replace(fb, offset, length, text, attrs);
	        }
	    });
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Email Amazon");
		lblNewJgoodiesLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("Password Amazon");
		lblNewJgoodiesLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblNewJgoodiesLabel_2 = DefaultComponentFactory.getInstance().createLabel("Link Prodotto Amazon");
		lblNewJgoodiesLabel_2.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblNewJgoodiesLabel_3 = DefaultComponentFactory.getInstance().createLabel("Prezzo");
		lblNewJgoodiesLabel_3.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("<html>Inserisci i dati richiesti per iniziare a tracciare il prodotto</html>");
		lblNewJgoodiesTitle.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JButton btnAvviaTracking = new JButton("Avvia Tracking");
		btnAvviaTracking.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAvviaTracking.setBackground(new Color(0, 204, 0));
		btnAvviaTracking.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		btnAvviaTracking.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
								.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
								.addComponent(lblNewJgoodiesLabel, Alignment.LEADING)
								.addComponent(lblNewJgoodiesLabel_1, Alignment.LEADING)
								.addComponent(lblNewJgoodiesLabel_2, Alignment.LEADING)
								.addComponent(lblNewJgoodiesLabel_3, Alignment.LEADING)
								.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewJgoodiesTitle, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)
							.addGap(185)
							.addComponent(btnAvviaTracking, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewJgoodiesLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewJgoodiesLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(lblNewJgoodiesLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewJgoodiesLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewJgoodiesTitle, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAvviaTracking))
					.addGap(33))
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNewJgoodiesTitle, btnAvviaTracking, textField, passwordField, textField_2, lblNewJgoodiesLabel, lblNewJgoodiesLabel_1, lblNewJgoodiesLabel_2, lblNewJgoodiesLabel_3, textField_3}));
		

		btnAvviaTracking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String [] dati = new String[4];
				dati[0] = textField.getText();
				dati[1] = Arrays.toString(passwordField.getPassword());
				dati[2] = textField_2.getText();
				dati[3] = textField_3.getText();
				
				ReadData dataConverter = new ReadData();
				
				//Prendo i dati inseriti dall'utente
				Map<String, Object> datiAmazon = new HashMap<String, Object>();
				try {
					datiAmazon = dataConverter.convertiDati(dati);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				boolean isNotReady = false;
//TODO non funziona il regex
//		        for (Map.Entry<String, Object> datoAmazon : datiAmazon.entrySet()) {
//		           boolean isErrato =  new MyInputVerifier().verify(datoAmazon);
//		           if(isErrato) {
//		        	   status = new JLabel("Errato", JLabel.CENTER);
//		        	   isNotReady = true;
//		           }
//		        }
		        
	         if(!isNotReady) {
				try {
					avviaBot(datiAmazon);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	         }
		           
			}
		});
	}
	
	
	static void avviaBot(Map<String, Object> datiAmazon) throws Exception {
		
		Logger myLog = Logger.getLogger(Main.class.getName());
		
		//Carico il driver per Chrome
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		//Carico il mio profilo per evitare email / sms di sicurezza
		ChromeOptions options = new ChromeOptions();
		if (datiAmazon.get("GOOGLE") != null && datiAmazon.get("GOOGLE").toString().equals("SI")) {
			options.addArguments("user-data-dir=" + datiAmazon.get("PATH_GOOGLE").toString());
		}
		
		//Incognito per limitare fenomeni di caching
		options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		options.setCapability("chrome.switches", Arrays.asList("--incognito"));
		
		//Apro un'istanza del driver con le opzioni desiderate
		WebDriver driver = new ChromeDriver(options);
		
		//Apro link dell'oggetto che si vuole monitorare
		try {
			driver.get(datiAmazon.get("OGGETTO_URL").toString());
		}catch (Exception e) {
			myLog.info("Errore nell'apertura del browser " + e);
		}

		//Classe che contiene tutte le azioni da effettuare sul DOM
		AzioniDOM dom = new AzioniDOM();
		try {
			boolean isAmazonPrezzoOK = dom.isPrezzoAmazonOK(Integer.parseInt((String) datiAmazon.get("PREZZO_MASSIMO")), driver);
			
			//Se il prezzo è giusto e il venditore/spedizioniere è Amazon allora lo aggiunge al carrello
			if(isAmazonPrezzoOK)
				dom.aggiungiAlCarrello(driver);
			
			
			//Skippa offerta Prime se presente
			dom.skipPrime(driver);
			
			//Arriva alla fase di pagamento dell'ordine
			dom.procediOrdine(driver, datiAmazon);
			myLog.info("Ordine effettuato");
		}catch (Exception e) {
			myLog.info("Errore azioni DOM" + e);
		}
		
		driver.close();
	}
}
