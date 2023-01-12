/**
 * Denna klass samlar allt till en GUI
 * Man kan göra alla saker som BankLogic kan göra
 * Man kan även spara alla kunder och deras konton/transaktioner till en fil
 * Man kan välja ett konto och spara dess transaktioner till en fil
 * Man kan läsa in tidigare kunder till Banken
 *
 * @author Per Granberg, epager-1
 */


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
	private JTextPane infoArea;
	private JScrollPane scrollInfo;
	private JScrollPane scrollCustomers;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField personNbrField;

	/**
	 * All the "Listener" below are used to activate events
	 * when the user does something, like click on "Add custoner"
	 */

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
				writeCustomersToFile();
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
				inputAllCustomers();
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


		nameField = new JTextField();
		nameField.setBorder(BorderFactory.createTitledBorder("Name"));
		surnameField = new JTextField();
		surnameField.setBorder(BorderFactory.createTitledBorder("Surname"));

		personNbrField = new JTextField();

		personNbrField.setBorder(BorderFactory.createTitledBorder("Personnumber"));
		personList = new JList<Account>();
		scrollCustomers = new JScrollPane(personList);
		personList.setBorder(BorderFactory.createTitledBorder("Bank Customers"));


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
		setLayout(new GridLayout(1,2));
		buildMenu();



		JPanel leftPanel = new JPanel(new GridLayout(5,2));


		leftPanel.add(nameField);
		leftPanel.add(surnameField);
		leftPanel.add(personNbrField);
		JButton addButton = new JButton("Create customer");
		addButton.addActionListener(new AddListener());
		leftPanel.add(addButton);
		JButton showButton = new JButton("Customer info");
		showButton.addActionListener(new ShowListener());
		leftPanel.add(showButton);


		JButton createSavingAccButton = new JButton("Create SavingAcc");
		createSavingAccButton.addActionListener(new SavingAccListner() ); //lägg till en createAccount
		leftPanel.add(createSavingAccButton);

		JButton createCreditAccButton = new JButton("Create CreditAcc");
		createCreditAccButton.addActionListener(new CreditAccListner());
		leftPanel.add(createCreditAccButton);

		JButton changeNameButton = new JButton("Change Name");
		changeNameButton.addActionListener(new changeNameListner());
		leftPanel.add(changeNameButton);


		JButton deleteCustomerButton = new JButton("Remove Customer");
		deleteCustomerButton.addActionListener(new DeleteCustomer());
		leftPanel.add(deleteCustomerButton);

		JButton depositButton = new JButton("Deposit");
		depositButton.addActionListener(new DepositListener());
		leftPanel.add(depositButton);

		JButton withdrawButton = new JButton("Withdraw");
		withdrawButton.addActionListener(new withdrawListener());
		leftPanel.add(withdrawButton);

		JButton closeAccButton = new JButton("Remove Account");
		closeAccButton.addActionListener(new closeAccListener());
		leftPanel.add(closeAccButton );

		JButton seeTransactions = new JButton("Info transactions");
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
		JMenu menu = new JMenu("Meny");
		menubar.add(menu);

		JMenuItem item = new JMenuItem("Show information on all customers");
		item.addActionListener(new showAllInfoListener());
		menu.add(item);

		item = new JMenuItem("Save all customers to file");
		item.addActionListener(new saveAllCustomersListener());
		menu.add(item);

		item = new JMenuItem("Read all customers");
		item.addActionListener(new readAllCustomersListner());
		menu.add(item);

		item = new JMenuItem("Save transactions to file");
		item.addActionListener(new writeTransactionsToFileListner());
		menu.add(item);

		item = new JMenuItem("Quit");
		item.addActionListener(new ExitListener());
		menu.add(item);

		setJMenuBar(menubar);
	}

	/**
	 * Adds a customer to the personList
	 * checks if the inputs for customer is correct, no numbers in name and 12 numbers in personNbr
	 */
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


	/**
	 * Creates the saving account for a customer
	 */
	private void createSavingAccount(){
		String pNbr = getPersonNumber();
		if (pNbr != null){
			bankLogic.createSavingsAccount(pNbr);
		}

	}

	/**
	 * Creates the credit account for a customer
	 */
	private void createCreditAccount(){
		String pNbr = getPersonNumber();
		if (pNbr != null){
			bankLogic.createCreditAccount(pNbr);
		}

	}


	/**
	 * Deletes a customer
	 */
	private void deleteCustomer(){
		String pNbr = getPersonNumber();
		if (pNbr != null) {
			infoArea.setText("Information about the deleted customer is:\n" + bankLogic.deleteCustomer(pNbr));
			personList.setListData(bankLogic.getAllCustomers().toArray());
		}

	}

	/**
	 * Lets the user deposit money for a selected customer
	 * If controls if a user is selected, control if the user have any account
	 *  Controls if the user write a letter when depositing
	 */

	private void deposit(){
		String pNbr = getPersonNumber();
		if( pNbr != null){
			ArrayList<String> accountsList = bankLogic.getAllAccountIds(pNbr);

			if (accountsList.size() > 0){
				JTextField depositField = new JTextField(5);

				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Deposit amount:"));
				myPanel.add(depositField);
				myPanel.add(Box.createHorizontalStrut(15)); // a spacer

				myPanel.add(new JLabel("Choose account:"));
				JComboBox jcb = new JComboBox(new Vector(accountsList));
				myPanel.add(jcb);

				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"Deposit money", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					int accountId =Integer.parseInt(jcb.getSelectedItem().toString());

					if (correctAmount(depositField.getText(), "Just write numbers when entering the amount")){
						boolean checkDeposit = bankLogic.deposit(pNbr,accountId,Integer.parseInt(depositField.getText()));

						if (checkDeposit == false){
							JOptionPane.showMessageDialog(null, "Could not deposit money to the account");
						}
					}

				}
			} else {
				JOptionPane.showMessageDialog(null, "The customer has no account, first create a account!");
			}



		}


	}


	/**
	 * Lets the user withdraw money from a customer
	 * Check if the customer have account, and no letters when entering the numbers
	 */
	private void withdraw(){
		String pNbr = getPersonNumber();
		if (pNbr != null){
			ArrayList<String> accountsList = bankLogic.getAllAccountIds(pNbr);

			if (accountsList.size() > 0){
				JTextField depositField = new JTextField(5);

				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Withdraw amount:"));
				myPanel.add(depositField);
				myPanel.add(Box.createHorizontalStrut(15)); // a spacer

				myPanel.add(new JLabel("Choose account:"));
				JComboBox jcb = new JComboBox(new Vector(accountsList));
				myPanel.add(jcb);


				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"Withdraw money", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
						int accountId =Integer.parseInt(jcb.getSelectedItem().toString());
						if (correctAmount(depositField.getText(), "Just write numbers when entering the amount")){
							boolean withdrawSuccess =  bankLogic.withdraw(pNbr,accountId,Integer.parseInt(depositField.getText()));
							if (withdrawSuccess == false) {
								JOptionPane.showMessageDialog(null, "Could not withdraw money from the account");
							}
						}


					}

				} else{
				JOptionPane.showMessageDialog(null, "The customer has no account, first create a account!");
			}



		}

	}

	/**
	 * Lets the user close a account for a customer
	 * Information of the deleted account is shown in "Information area"
	 */
	private void closeAccount(){
		String pNbr = getPersonNumber();
		if (pNbr != null){
			ArrayList<String> accountsList = bankLogic.getAllAccountIds(pNbr);

			// control if the person has a account
			if (accountsList.size() >0){
				JPanel myPanel = new JPanel();

				myPanel.add(new JLabel("Choose account to remove:"));
				JComboBox jcb = new JComboBox(new Vector(accountsList));
				myPanel.add(jcb);


				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"Remove account", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					int accountId =Integer.parseInt(jcb.getSelectedItem().toString());
					infoArea.setText("Information of the deleted account is:\n" + bankLogic.closeAccount(pNbr, accountId));
				}
			} else {
				JOptionPane.showMessageDialog(null, "The customer has no account, first create a account!");
			}

		}

	}


	/**
	 * Lets the user change name and surname of a selected customer
	 */
	private void changeName(){
		String pNbr = getPersonNumber();
		if (pNbr != null){
			JTextField nameField = new JTextField(5);
			JTextField surNameField = new JTextField(5);

			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("Name:"));
			myPanel.add(nameField );
			myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel("Surname:"));
			myPanel.add(surNameField);

			int result = JOptionPane.showConfirmDialog(null, myPanel,
					"Change Name and Surname", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				// lägg till så att man kan kontrollera namnet
				if (correctPersonInput(nameField.getText(), surNameField.getText(), pNbr) == true){
					bankLogic.changeCustomerName(nameField.getText(), surNameField.getText(), pNbr);
				}

			}

			personList.setListData(bankLogic.getAllCustomers().toArray());
		}

	}


	/**
	 * Shows information of a selected customer
	 */
	private void showSelected() throws IOException {
		int position = personList.getSelectedIndex();
		if(position > -1)
		{
			String testar = personList.getSelectedValue().toString();
			String answer = testar.split(" ")[0];
			infoArea.setText("Information of the selected customer is:\n" + bankLogic.getCustomer(answer).toString());
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You must first select a customer from the list");
		}
	}


	/**
	 * Shows information of all customers
	 */
	private void showAllInfo()
	{
		infoArea.setText(bankLogic.getAllCustomers().toString());

	}


	/**
	 * Shows all transactions for a selected account
	 */
	private void seeTransactions()
	{

		String pNbr = getPersonNumber();
		if (pNbr != null){
			ArrayList<String> accountsList = bankLogic.getAllAccountIds(pNbr);

			if (accountsList.size() > 0 ){
				JPanel myPanel = new JPanel();

				myPanel.add(new JLabel("Choose account to see all the transactions for:"));
				JComboBox jcb = new JComboBox(new Vector(accountsList));
				myPanel.add(jcb);


				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"See transactions", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					int accountId =Integer.parseInt(jcb.getSelectedItem().toString());
					infoArea.setText("All transactions for the account is:\n " +bankLogic.getTransactions(pNbr, accountId).toString());
				}
			} else {
				JOptionPane.showMessageDialog(null, "The customer has no account, first create a account!");
			}

		}

	}


	/**
	 * Clear the textinput
	 */
	private void clear()
	{
		nameField.setText("");
		surnameField.setText("");
		personNbrField.setText("");
	}



	/**
	 * Get the personNumber from a selected customer
	 */
	private String getPersonNumber(){
		int position = personList.getSelectedIndex();
		if(position > -1)
		{
			String customerInfo = personList.getSelectedValue().toString();
			String answer = customerInfo.split(" ")[0];
			String personNbr = answer;
			return personNbr;

		}
		else
		{
			JOptionPane.showMessageDialog(null, "You must first select a customer from the list");
			return null;
		}
	}


	/**
	 * Test if the input for name and personNbr are empty
	 */
	private boolean testEmptyInput(String name, String surName, String personNbr){

		if (name.isEmpty() == true || surName.isEmpty() == true || personNbr.isEmpty() == true){
			JOptionPane.showMessageDialog(null, "You must first write something in all three boxes " +
					"Name, Surname and Personnumber\n Could not create a customer, try again!");
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Controls if the name and personNbr have correct format
	 */
	private boolean correctPersonInput(String name, String surName, String personNbr){

		if (personNbr.matches("[0-9]+") && personNbr.length() == 10){


			if(name.matches(".*\\d+.*") == false && surName.matches(".*\\d+.*") == false ){
				return true;

			} else {
				JOptionPane.showMessageDialog(null, "The is a number in the Name or Surname, try again");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Personnumber have wrong format, you must write ÅÅMMDDXXXX");
		}
		return false;
	}


	/**
	 * Controls that the amount only contains numbers
	 */
	private boolean correctAmount(String amount, String outputMessage){
		if (amount.matches("[0-9]+")){
			return true;
		} else{
			JOptionPane.showMessageDialog(null, outputMessage);
			return false;
		}
	}



	/**
	 * Writes all the customers to a file, transaction and so on are also saved
	 * It also saves the last accountNbr used in a different file so that when loading the data it will pick up
	 */
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



	/**
	 * Reads all the customers from a file to the bank,also sets the new accountNbr
	 */
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



	/**
	 * The user will select a customer and save all transactions from one account into a txt file
	 */
	private void writeTransactionsToFile() throws IOException {
		List<Customer> allCustomers = BankLogic.customersList;
		String pNbr = getPersonNumber();

		if (pNbr != null){
			ArrayList<String> accountsList = bankLogic.getAllAccountIds(pNbr);

			if (accountsList.size() > 0 ){
				JPanel myPanel = new JPanel();

				myPanel.add(new JLabel("Select account to see transactions for:"));
				JComboBox jcb = new JComboBox(new Vector(accountsList));
				myPanel.add(jcb);


				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"Save transactions", JOptionPane.OK_CANCEL_OPTION);
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
				JOptionPane.showMessageDialog(null, "The customer has no account, first create a account!");
			}

		}

}

}