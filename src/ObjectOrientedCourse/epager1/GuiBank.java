package ObjectOrientedCourse.epager1;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;


public class GuiBank extends JFrame
{


	private BankLogic bankLogic;
	private JList personList;
	private JList infoList; //shows information about customers
	private JTextPane infoArea;
	private JScrollPane scrollInfo;
	private JScrollPane scrollCustomers;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField personNbrField;

	private class AddListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			add();
		}
	}


	private class SavingAccListner implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			createSavingAccount();
		}
	}

	private class changeNameListner implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			changeName();
		}
	}


	private class CreditAccListner implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			createCreditAccount();
		}
	}

	private class DeleteCustomer implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			deleteCustomer();
		}
	}


	private class DepositListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			deposit();
		}
	}


	private class withdrawListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			withdraw();
		}
	}

	private class closeAccListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			closeAccount();
		}
	}


	private class showAllInfoListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			showAllInfo();
		}
	}

	private class saveAllCustomersListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try {
				saveAllCustomers();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class readAllCustomersListner implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try {
				readAllCustomers();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}


	private class writeTransactionsToFileListner implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try {
				writeTransactionsToFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	private class transactionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			seeTransactions();
		}
	}



	/* Inre klass som sköter det som ska hända om man väljer alternativet "Visa" */
	private class ShowListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try {
				showSelected();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	/* Inre klass som sköter menyval "Exit" */
	private class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			System.exit(0);
		}
	}

	public static void main(String[] args)
	{
		GuiBank frame = new GuiBank();
		frame.setVisible(true);
	}

	public GuiBank()
	{
		initiateInstanceVariables();
		buildFrame();
	}

	private void initiateInstanceVariables()
	{
		bankLogic = new BankLogic();


		nameField = new JTextField();  //nameField.getText() för att få texten som finns i namnet
		nameField.setBorder(BorderFactory.createTitledBorder("Namn"));
		surnameField = new JTextField();  //nameField.getText() för att få texten som finns i namnet
		surnameField.setBorder(BorderFactory.createTitledBorder("Efternamn"));

		personNbrField = new JTextField();

		personNbrField.setBorder(BorderFactory.createTitledBorder("Personnummer"));
		personList = new JList<Account>();
		scrollCustomers = new JScrollPane(personList);
		personList.setBorder(BorderFactory.createTitledBorder("Registrerade personer"));

		infoList = new JList<Customer>();
		infoList.setBorder(BorderFactory.createTitledBorder("Information om kunder"));
		infoList.setBorder(BorderFactory.createTitledBorder("Information om kunder"));

		infoArea = new JTextPane();
		scrollInfo = new JScrollPane(infoArea);
		infoArea.setBorder(BorderFactory.createTitledBorder("Information area"));
		infoArea.setEditable(false);
		infoArea.setContentType("text");

	}

	private void buildFrame()
	{
		setTitle("Java Bank");
		setSize(1300,650);

		setLocation(100,100);

		setLayout(new GridLayout(1,2));  // vet inte vad denna gö

		buildMenu(); // Skapa upp menyn



		JPanel leftPanel = new JPanel(new GridLayout(5,2));


		leftPanel.add(nameField);
		leftPanel.add(surnameField);
		leftPanel.add(personNbrField);
		JButton addButton = new JButton("Lägg till Person och konto");
		addButton.addActionListener(new AddListener());
		leftPanel.add(addButton);
		JButton showButton = new JButton("Visa vald kund och konto");
		showButton.addActionListener(new ShowListener());
		leftPanel.add(showButton);


		// add buttons for creating saving och credit account
		JButton createSavingAccButton = new JButton("Skapa ett saving account");
		createSavingAccButton.addActionListener(new SavingAccListner() ); //lägg till en createAccount
		leftPanel.add(createSavingAccButton);

		JButton createCreditAccButton = new JButton("Skapa credit account");
		createCreditAccButton.addActionListener(new CreditAccListner());
		leftPanel.add(createCreditAccButton);

		// Button for changing name
		JButton changeNameButton = new JButton("Ändra Namn");
		changeNameButton.addActionListener(new changeNameListner());
		leftPanel.add(changeNameButton);


		// Button for deleting customer funkar
		JButton deleteCustomerButton = new JButton("Ta bort kund");
		deleteCustomerButton.addActionListener(new DeleteCustomer());
		leftPanel.add(deleteCustomerButton);

		// lägg till knapp för deposit och withdraw
		JButton depositButton = new JButton("Sätt in pengar");
		depositButton.addActionListener(new DepositListener());
		leftPanel.add(depositButton);

		// lägg till knapp för deposit och withdraw
		JButton withdrawButton = new JButton("Ta ut pengar");
		withdrawButton.addActionListener(new withdrawListener());
		leftPanel.add(withdrawButton);

		// lägg till close account
		JButton closeAccButton = new JButton("Ta bort konto");
		closeAccButton.addActionListener(new closeAccListener());
		leftPanel.add(closeAccButton );

		// lägg till se transactions
		JButton seeTransactions = new JButton("Titta på transaktioner");
		seeTransactions.addActionListener(new transactionListener());
		leftPanel.add(seeTransactions);



		add(leftPanel);

		add(scrollCustomers);

		add(scrollInfo);


		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	private void buildMenu()
	{
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Arkiv");
		menubar.add(menu);

		JMenuItem item = new JMenuItem("Visa alla kunders information");
		item.addActionListener(new showAllInfoListener());
		menu.add(item);

		item = new JMenuItem("Spara alla kunder till fil");
		item.addActionListener(new saveAllCustomersListener());
		menu.add(item);

		item = new JMenuItem("Läs in alla kunder");
		item.addActionListener(new readAllCustomersListner());
		menu.add(item);

		item = new JMenuItem("Skriv transaktioner till fil");
		item.addActionListener(new writeTransactionsToFileListner());
		menu.add(item);

		item = new JMenuItem("Avsluta");
		item.addActionListener(new ExitListener());
		menu.add(item);

		setJMenuBar(menubar);
	}


	private void add()
	{
		// control that the inputs are valid
		String name = nameField.getText();
		String surName = surnameField.getText();
		String personNbr = personNbrField.getText();
		 if (testEmptyInput(name, surName, personNbr ) == true && correctPersonInput(name, surName, personNbr) == true) {
			 if (bankLogic.createCustomer(nameField.getText(), surnameField.getText(), personNbrField.getText()) == true){
				 personList.setListData(bankLogic.getAllCustomers().toArray());
				 clear();
			 } else{
				 JOptionPane.showMessageDialog(null, "Kunde inte skapa en ny kund, personnumret används redan");
			 }

		 }


	}

	private void createSavingAccount(){
		String pNbr = getPersonNumber();
		if (pNbr != null){
			bankLogic.createSavingsAccount(pNbr);
		}

	}


	private void createCreditAccount(){
		String pNbr = getPersonNumber();
		if (pNbr != null){
			bankLogic.createCreditAccount(pNbr);
		}

	}


	private void deleteCustomer(){
		String pNbr = getPersonNumber();
		if (pNbr != null) {
			infoArea.setText("Informationen om den raderade kunden är:\n" + bankLogic.deleteCustomer(pNbr));
			personList.setListData(bankLogic.getAllCustomers().toArray());
		}

	}

	private void deposit(){
		String pNbr = getPersonNumber();
		if( pNbr != null){
			ArrayList<String> accountsList = bankLogic.getAllAccountIds(pNbr);

			if (accountsList.size() > 0){
				JTextField depositField = new JTextField(5);

				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Sätt in:"));
				myPanel.add(depositField);
				myPanel.add(Box.createHorizontalStrut(15)); // a spacer

				myPanel.add(new JLabel("Välj account:"));
				JComboBox jcb = new JComboBox(new Vector(accountsList));
				myPanel.add(jcb);

				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"Sätt in pengar", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					int accountId =Integer.parseInt(jcb.getSelectedItem().toString());

					if (correctAmount(depositField.getText(), "Skriv bara siffror när du sätter in pengar")){
						boolean checkDeposit = bankLogic.deposit(pNbr,accountId,Integer.parseInt(depositField.getText()));

						if (checkDeposit == false){
							JOptionPane.showMessageDialog(null, "Kunde inte sätta in pengar");
						}
					}

				}
			} else {
				JOptionPane.showMessageDialog(null, "Kunden har inget konto, skapa först ett konto!");
			}



		}


	}


	private void withdraw(){
		// lägg till en if sats som kontrollerar att kunden har något account
		String pNbr = getPersonNumber();
		if (pNbr != null){
			ArrayList<String> accountsList = bankLogic.getAllAccountIds(pNbr);

			if (accountsList.size() > 0){
				JTextField depositField = new JTextField(5);

				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Ta ut pengar:"));
				myPanel.add(depositField);
				myPanel.add(Box.createHorizontalStrut(15)); // a spacer

				myPanel.add(new JLabel("Välj account:"));
				JComboBox jcb = new JComboBox(new Vector(accountsList));
				myPanel.add(jcb);


				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"Ta ut pengar", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
						int accountId =Integer.parseInt(jcb.getSelectedItem().toString());
						if (correctAmount(depositField.getText(), "Skriv bara siffror när du tar ut pengar")){
							boolean withdrawSuccess =  bankLogic.withdraw(pNbr,accountId,Integer.parseInt(depositField.getText()));
							if (withdrawSuccess == false) {
								JOptionPane.showMessageDialog(null, "Kunde inte ta ut pengar från kontot!");
							}
						}


					}

				} else{
				JOptionPane.showMessageDialog(null, "Kunden har inget konto, skapa först ett konto!");
			}



		}

	}


	private void closeAccount(){
		// lägg till en if sats som kontrollerar att kunden har något account

		String pNbr = getPersonNumber();
		if (pNbr != null){
			ArrayList<String> accountsList = bankLogic.getAllAccountIds(pNbr);

			// control if the person has a account
			if (accountsList.size() >0){
				JPanel myPanel = new JPanel();

				myPanel.add(new JLabel("Välj konto att ta bort:"));
				JComboBox jcb = new JComboBox(new Vector(accountsList));
				myPanel.add(jcb);


				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"Ta bort konto", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					int accountId =Integer.parseInt(jcb.getSelectedItem().toString());
					infoArea.setText("Informationen om det raderade kontot är:\n" + bankLogic.closeAccount(pNbr, accountId));
				}
			} else {
				JOptionPane.showMessageDialog(null, "Kunden har inget konto, skapa först ett konto!");
			}

		}

	}


	private void changeName(){


		String pNbr = getPersonNumber();
		if (pNbr != null){
			JTextField nameField = new JTextField(5);
			JTextField surNameField = new JTextField(5);

			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("Namn:"));
			myPanel.add(nameField );
			myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel("Efternamn:"));
			myPanel.add(surNameField);

			int result = JOptionPane.showConfirmDialog(null, myPanel,
					"Ändra namn och efternamn", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				// lägg till så att man kan kontrollera namnet
				if (correctPersonInput(nameField.getText(), surNameField.getText(), pNbr) == true){
					bankLogic.changeCustomerName(nameField.getText(), surNameField.getText(), pNbr);
				}

			}

			personList.setListData(bankLogic.getAllCustomers().toArray());
		}



	}

	private void showSelected() throws IOException {
		int position = personList.getSelectedIndex();
		if(position > -1)
		{
			String testar = personList.getSelectedValue().toString();
			String answer = testar.split(" ")[0];
			infoArea.setText("Informationen om valda personen är:\n" + bankLogic.getCustomer(answer).toString());

		}
		else
		{
			JOptionPane.showMessageDialog(null, "Du måste markera en person i listan!");
		}
	}


	private void showAllInfo()
	{
		infoArea.setText(bankLogic.getAllCustomers().toString());

	}

	private void saveAllCustomers() throws IOException {
		writeCustomersToFile();
	}

	private void readAllCustomers() throws FileNotFoundException {
		inputAllCustomers();
	}



	private void seeTransactions()
	{

		String pNbr = getPersonNumber();
		if (pNbr != null){
			ArrayList<String> accountsList = bankLogic.getAllAccountIds(pNbr);

			if (accountsList.size() > 0 ){
				JPanel myPanel = new JPanel();

				myPanel.add(new JLabel("Välj konto att se transaktionerna för:"));
				JComboBox jcb = new JComboBox(new Vector(accountsList));
				myPanel.add(jcb);


				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"Se transaktioner", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					int accountId =Integer.parseInt(jcb.getSelectedItem().toString());
					infoArea.setText("Kundens alla transaktioner för kontot är:\n " +bankLogic.getTransactions(pNbr, accountId).toString());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Kunden har inget konto, skapa först ett konto!");
			}

		}

	}

	private void clear()
	{
		// makes the inputbox empty again
		nameField.setText("");
		surnameField.setText("");
		personNbrField.setText("");
	}


	private String getPersonNumber(){
		int position = personList.getSelectedIndex();
		if(position > -1)
		{
			String testar = personList.getSelectedValue().toString();
			String answer = testar.split(" ")[0];
			String personNbr = answer;
			return personNbr;

		}
		else
		{
			JOptionPane.showMessageDialog(null, "Du måste markera en person i listan!");
			return null;
		}
	}

	private boolean testEmptyInput(String name, String surName, String personNbr){

		if (name.isEmpty() == true || surName.isEmpty() == true || personNbr.isEmpty() == true){
			JOptionPane.showMessageDialog(null, "Du måste fylla i något i alla tre rutorna för" +
					"namn, efternamn och personnummer\n Kunde inte skapa en kund, försök igen!");
			return false;
		} else {
			return true;
		}

	}

	// Control input for name and so
	private boolean correctPersonInput(String name, String surName, String personNbr){

		if (personNbr.matches("[0-9]+") && personNbr.length() == 10){


			if(name.matches(".*\\d+.*") == false && surName.matches(".*\\d+.*") == false ){
				return true;

			} else {
				JOptionPane.showMessageDialog(null, "Det finns en siffra i namnet eller efternamnet, skriv in igen");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Personnumret har fel format, du måste ange ÅÅMMDDXXXX");
		}
		return false;
	}

	private boolean correctAmount(String amount, String outputMessage){
		if (amount.matches("[0-9]+")){
			return true;
		} else{
			JOptionPane.showMessageDialog(null, outputMessage);
			return false;
		}
	}




	private void writeCustomersToFile() throws IOException {
		List<Customer> allCustomers = BankLogic.customersList;
		try
		{
			FileOutputStream fos = new FileOutputStream("customerData.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(allCustomers);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		//  save the last accountNUmber to a file
		Writer wr = new FileWriter("save_last_accountNumber.txt");
		wr.write (bankLogic.getLastestAccountNbr() + "");
		wr.close();
	}

	private void inputAllCustomers() throws FileNotFoundException {
		List<Customer> namesList = new ArrayList<Customer>();

		try
		{
			FileInputStream fis = new FileInputStream("customerData.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);

			namesList = (ArrayList) ois.readObject();

			ois.close();
			fis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		bankLogic.setCustomerList(namesList);
		personList.setListData(bankLogic.getAllCustomers().toArray());

		// Change the latest accountnumber
		Scanner scanner = new Scanner(new File("save_last_accountNumber.txt"));
		int updateAccountNbr = scanner.nextInt();
		Account.setNbrOfAccounts(updateAccountNbr);

	}

	private void writeTransactionsToFile() throws IOException {
		List<Customer> allCustomers = BankLogic.customersList;
		String pNbr = getPersonNumber();

		if (pNbr != null){
			ArrayList<String> accountsList = bankLogic.getAllAccountIds(pNbr);

			if (accountsList.size() > 0 ){
				JPanel myPanel = new JPanel();

				myPanel.add(new JLabel("Välj konto att spara transaktioner för:"));
				JComboBox jcb = new JComboBox(new Vector(accountsList));
				myPanel.add(jcb);


				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"Spara transaktioner", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					int accountId =Integer.parseInt(jcb.getSelectedItem().toString());


					// save the transacions to a file
					FileWriter writer = new FileWriter("transactions_for_"+ accountId + ".txt");
					ArrayList<String> transactionList = bankLogic.getTransactions(pNbr, accountId);
					for(String str: transactionList) {
						writer.write(str + System.lineSeparator());
					}
					writer.close();

				}
			} else {
				JOptionPane.showMessageDialog(null, "Kunden har inget konto, skapa först ett konto!");
			}

		}

}

}