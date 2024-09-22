package glue;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import account.Account;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;

public class AccountSteps {

    Account account = null;

    @Given("^Account exists for Acc No\\. \"([^\"]*)\" with Name \"([^\"]*)\"$")
    public void accountExistsForAccNoWithName(String number, String name) {
        this.account = new Account(name, number);
        Assert.assertTrue("Account number has been set", this.account.getAccNo() == number);
        Assert.assertTrue("Account name has been set", this.account.getAccName() == name);
    }
    
    @And("deposits are made")
    public void depositsAreMade(DataTable deposits) {
    	float balance = this.account.getBalance();
		List<List<String>> rows = deposits.asLists(String.class);
		for (List<String> columns : rows) {
			String ref = columns.get(0);
			float value = Float.parseFloat(columns.get(1));
			account.deposit(ref, value);
			Assert.assertTrue(String.format("%.2f added to account balance",value),
							   this.account.getBalance() == balance + value);
			balance += value;
		}
    }
    
    @And("withdrawals are made")
    public void withdrawalsAreMade(DataTable withdrawals) {
    	float balance = this.account.getBalance();
    	List<List<String>> rows = withdrawals.asLists(String.class);
    	for (List<String> columns : rows) {
    		String ref = columns.get(0);
    		float value = Float.parseFloat(columns.get(1));
    		account.withdraw(ref, value);
    		Assert.assertTrue(String.format("%.2f subtracted from account balance",value),
    						  this.account.getBalance() == balance - value);
    		balance -= value;
    	}
    }

    @When("statement is produced")
    public void statementIsProduced() {
    	String statement = account.produceAccountStatement();
    	Assert.assertTrue("Statement is non-empty", (statement != "" & statement != null));
    }
    
    @Then("^statement includes \"([^\"]*)\"$")
    public void statementIncludes(String info) {
    	String statement = account.produceAccountStatement();
    	// Possibly runs the risk of incorrectly identifying some other part of
    	// the statement as a particular deposit/withdrawal (e.g. if a deposit has ID
    	// "Acco" then the assertion will find that substring in the line "Account Name",
    	// but I think this has more to do with ID checks not being separate test cases
    	// to other parts of the statement than anything
    	Assert.assertTrue(String.format("Statement includes \"%s\"",info),statement.contains(info));
    }

}
