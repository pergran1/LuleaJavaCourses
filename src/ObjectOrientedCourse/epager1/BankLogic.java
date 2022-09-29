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

    public ArrayList<String> getAllCustomers(){
        ArrayList<String> allCustomers = new ArrayList<String>();
        for(Customer customer: customersList){
            allCustomers.add(customer.toString());
        }
        return allCustomers;
    }

    public boolean createCustomer(String name, String surname, String pNo){
       // Customer account = customersList.stream().filter(a -> a.getPersonalNumber() == pNo).collect(Collectors.toList()).get(0);
        if (findCustomer(pNo) == null){
            Customer testar = new Customer(name, surname, pNo);
            customersList.add(testar);
            return true;
        }
        return false;
    }


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



    public boolean changeCustomerName(String name, String surname, String pNo){
        Customer customer = findCustomer(pNo);
        Boolean nameChange = name.isEmpty();
        Boolean surNameChange = surname.isEmpty();
        if (customer == null){
            return false;
        } else if (nameChange == false && surNameChange == false){
            customer.setName(name);
            customer.setSurName(surname);
            return true;
        } else if (nameChange == false){
            customer.setName(name);
            return true;
        } else if (surNameChange == false){
            customer.setSurName(surname);
            return true;
        } else{
            return false;
        }
    }

    public int createSavingsAccount(String pNo){
        Customer customer = findCustomer(pNo);
        if ( customer != null){
            int accountNbr =  customer.createAccount();
            return accountNbr;

        }
        return -1;
    }

    public String getAccount(String pNo, int accountId){
        Account foundAccount = findAccount(pNo, accountId);
        if (foundAccount != null){
            return foundAccount.toString();
        }
        return null;
    }

    public boolean deposit(String pNo, int accountId, int amount){
        Account foundAccount = findAccount(pNo, accountId);
        if (foundAccount != null && amount > 0){
            foundAccount.deposit(amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(String pNo, int accountId, int amount){
        Account foundAccount = findAccount(pNo, accountId);
        if (foundAccount != null){
            return foundAccount.withdraw(amount);
        }
        return false;

    }

    public String closeAccount(String pNo, int accountId){
        Customer foundCustomer = findCustomer(pNo);
        if (foundCustomer != null) {

            return foundCustomer.removeAccount(accountId);

        }

        return null;

    }




    // method to search the customerList and return the customer with the pNo or Null if it does not exist in the list
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

    private Account findAccount(String pNo, int accountId){
        Customer customer = findCustomer(pNo);
        if (customer != null) {
            Account foundAccount = customer.findAccount(accountId);
            return foundAccount;
        }
        return null;
    }


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







    public static void main(String[] args) {
        BankLogic hoho = new BankLogic();
        hoho.createCustomer("Per", "Granberg", "423");
        hoho.createCustomer("Erik", "Granberg", "523");
        hoho.createCustomer("Lars", "Granberg", "923");
        //System.out.println(hoho.customersList.toString());
        System.out.println(hoho.getAllCustomers());
        System.out.println(hoho.getCustomer("923"));
        hoho.changeCustomerName("Lars New", "hoho", "923");
        hoho.changeCustomerName("", "", "523");
        hoho.createSavingsAccount("523");
        hoho.createSavingsAccount("523");
        System.out.println(hoho.getAllCustomers());
        System.out.println(hoho.getCustomer("523"));
        System.out.println(hoho.getAccount("523", 10902));
        hoho.deposit("523", 1001, 1050);
        System.out.println(hoho.getCustomer("523"));
        hoho.withdraw("523", 1001, 50);
        System.out.println(hoho.getCustomer("523"));
        System.out.println(hoho.closeAccount("523", 1001));
        System.out.println(hoho.getCustomer("523"));
        System.out.println(hoho.deleteCustomer("523"));
        System.out.println(hoho.getAllCustomers());
        //hoho.findCustomer("523");


    }


}
