/**
 * Denna klass använder metoder från Account och Customer för att kunna representera en bank.
 *
 * Här skapas en kund för att sedan läggas i en ArrayList som innehåller alla kunder.
 * Det finns sedan många metoder för att ändra eller skapa saker gällande kunder och konton.
 *
 * @author Per Granberg, epager-1
 */



package ObjectOrientedCourse.epager1;

import java.math.BigDecimal;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BankLogic {

    List<Customer> customersList = new ArrayList<Customer>();

    public BankLogic(){

    }


    /**
     * Skriver ut alla kunder i en fin array med string
     * @return ArrayList<String> av customers
     */
    public ArrayList<String> getAllCustomers(){
        ArrayList<String> allCustomers = new ArrayList<String>();
        for(Customer customer: customersList){
            allCustomers.add(customer.toString());
        }
        return allCustomers;
    }

    /**
     * Skapar en kund genom att använda datan från parametrarna
     * @param name, surname, pNo
     * @return Boolean
     */
    public boolean createCustomer(String name, String surname, String pNo){
        if (findCustomer(pNo) == null){
            Customer newCustomer = new Customer(name, surname, pNo);
            customersList.add(newCustomer);
            return true;
        }
        return false;
    }

    /**
     * Skriver ut information om en kund som identifieras via personnummer
     * @param String personnummer
     * @return ArrayList<String> eller null om kunden inte finns
     */
    public ArrayList<String> getCustomer(String pNo){
        Customer customer = findCustomer(pNo);
        if (customer != null){
            ArrayList<String> customerInfo = new ArrayList<String>();
            customerInfo.add(customer.toString());

            ArrayList<Account> accountList = customer.getAccountList();
            for (Account account: accountList){
                customerInfo.add(account.toString());
            }

            return customerInfo;
        }
        return null;
    }

    /**
     * En metod för att kunna ändra kundens namn och efternamn
     * @param String name, String surname, String pNo
     * @return Boolean
     */
    public boolean changeCustomerName(String name, String surname, String pNo){
        Customer customer = findCustomer(pNo);
        Boolean nameChange = name.isEmpty();
        Boolean surNameChange = surname.isEmpty();
        if( nameChange == true && surNameChange == true){
            return false;
        } else if (customer != null) {
            customer.changeNames(name, surname);
            return true;
        }
       return false;
    }

    /**
     * Skapar ett sparkonto, använder metoder från Customer klassen också
     * @param String pNo
     * @return Int, kontonumret eller -1 om kunden inte finns
     */
    public int createSavingsAccount(String pNo){
        Customer customer = findCustomer(pNo);
        if ( customer != null ){
            int accountNbr =  customer.createAccount();
            return accountNbr;

        }
        return -1;
    }

    /**
     * Få en utskrift om ett konto
     * @param String pNo, int accountId
     * @return String, utskrift om kontoinformationen
     */
    public String getAccount(String pNo, int accountId){
        Account foundAccount = findAccount(pNo, accountId);
        if (foundAccount != null){
            return foundAccount.toString();
        }
        return null;
    }

    /**
     * Gör att man kan ta ut pengar från kontot med matchande accountId
     * @param String pNo, int accountId, int amount
     * @return Boolean, true om man kunde ta ut pengarna
     */
    public boolean deposit(String pNo, int accountId, int amount){
        Account foundAccount = findAccount(pNo, accountId);
        if (foundAccount != null && amount > 0){
            foundAccount.deposit(amount);
            return true;
        }
        return false;
    }

    /**
     * Gör att man kan ta ut pengar från sitt konto
     * @param String pNo, int accountId, int amount
     * @return Boolean
     */
    public boolean withdraw(String pNo, int accountId, int amount){
        Account foundAccount = findAccount(pNo, accountId);
        if (foundAccount != null){
            return foundAccount.withdraw(amount);
        }
        return false;

    }

    /**
     * Gör att man kan stänga ett konto för en användare
     * @param String pNo, int accountId
     * @return Boolean
     */
    public String closeAccount(String pNo, int accountId){
        Customer foundCustomer = findCustomer(pNo);
        if (foundCustomer != null) {

            return foundCustomer.removeAccount(accountId);

        }

        return null;

    }

    /**
     * En metod för att hitta en användare och som kan återanvändas
     * @param String pNo
     * @return Customer som hittades eller null om kunden inte finns
     */
    private Customer findCustomer(String pNo){
        Customer foundCustomer = null;
        for(Customer customer: customersList){
            if(customer.getPersonalNumber() == pNo){
                foundCustomer = customer;
                break;
            }
        }
        return foundCustomer;
    }


    /**
     * En metod för att hitta ett konto för en användare. Skapdes för att man inte ska behöva upprepa samma sak
     * @param String pNo, int accountId
     * @return Account eller null om kontot inte finns
     */
    private Account findAccount(String pNo, int accountId){
        Customer customer = findCustomer(pNo);
        if (customer != null) {
            Account foundAccount = customer.findAccount(accountId);
            return foundAccount;
        }
        return null;
    }



    /**
     * Delete all data för en kund
     * @param String pNo
     * @return ArrayList<String>, innehåller information om kunden som togs bort
     */
    public ArrayList<String> deleteCustomer( String pNo){
        Customer customer = findCustomer(pNo);
        if (customer != null) {
            ArrayList<String> deleteCustomerStr = new ArrayList<String>();
            deleteCustomerStr.add(customer.toString());

            ArrayList<Account> accountList = customer.getAccountList();

            for (Account account: accountList){
                deleteCustomerStr.add(account.closeAccStr());
            }
            customer.removeAllAccounts();
            customersList.remove(customer);
            return deleteCustomerStr;
        }

        return null;
    }



}
