/**
 *
 */
package rw;

import java.util.Scanner; // import scanner

public class Account
{ // create class

	// define variables

	private static double annualIntrestRate;

	public static double getMonthlyIntrestRate()
	{

		return annualIntrestRate / 12 / 100;
	}

	public static void main(String[] args)
	{ // create main

		final Account[] accounts = new Account[11];
		for (int i = 0; i < accounts.length; i++)
		{
			accounts[i] = new Account(i);
		}

		final Scanner input = new Scanner(System.in); // create scanner

		while (true)
		{ // loop and input validation

			System.out.print("== What is your account number? ");
			int     enteredID  = input.nextInt();
			boolean shouldExit = false;

			if (enteredID > 10 || enteredID < 1)
			{

				System.out.println("== Invalid Account Number. Please Try Again. ");
				System.out.print("== What is your account number? ");
				enteredID = input.nextInt();

			}

			if (enteredID == accounts[enteredID].getID())
			{

				// atm menu options
				System.out.println("");
				System.out.println("== Welcome Account Holder " + enteredID);
				System.out.println("");

				System.out.print("ATM Options: ");
				System.out.print("(1) Balance ");
				System.out.print("(2) Withdraw ");
				System.out.print("(3) Deposit ");
				System.out.println("(4) Exit");

				System.out.println("Please Select Transaction: ");
				final int choice = input.nextInt();

				// if else statements for input validation
				if (choice == 1)
				{
					System.out.println(
						"== Your current balance is $" + String.format("%.2f", accounts[enteredID].getBalance()));
					continue;
				}
				else if (choice == 2)
				{
					System.out.println("== Enter amount to withdraw: ");
					final int withdrawAmount = input.nextInt();
					accounts[enteredID].withDraw(withdrawAmount);
					System.out.println(
						"== Success! Your new balance is $" + String.format("%.2f", accounts[enteredID].getBalance()));
					continue;
				}
				else if (choice == 3)
				{
					System.out.println("Enter amount to deposit: ");
					final int depositAmount = input.nextInt();
					accounts[enteredID].deposit(depositAmount);
					System.out.println(
						"== Success! Your new balance is $" + String.format("%.2f", accounts[enteredID].getBalance()));
					continue;
				}
				else if (choice > 4 || choice < 1)
				{
					System.out.println("Invalid option. Please try again. ");
					continue;
				}

				// menu exit and loop
				shouldExit = false;
				if (choice == 4)
				{
					System.out.println("== Sign off.");
					System.out.println("== Thank you for using our ATM. Come back soon!");
					System.out.println("");
					enteredID  = input.nextInt();

					shouldExit = false;

				}
			}

		}
	}

	private int            id;

	private double         balance;

	private java.util.Date dateCreated;

	public Account()
	{

	}

	public Account(int id)
	{
		this.id     = id;
		balance     = 100;
		dateCreated = new java.util.Date();

	}

	public double deposit(double depositeAmount)
	{
		return balance = balance + depositeAmount;
	}

	public double getBalance()
	{
		return balance;
	}

	public java.util.Date getDate()
	{
		return dateCreated;
	}

	public int getID()
	{
		return id;
	}

	public double getMonthlyIntrest()
	{
		return balance * getMonthlyIntrestRate();
	}

	public void setAnnualIntrest(double intrest)
	{
		annualIntrestRate = intrest;

	}

	public void setBalance(double newBalance)
	{
		balance = newBalance;
	}

	public void setID(int newID)
	{
		id = newID;
	}

	public double withDraw(double withDrawAmount)
	{
		return balance = balance - withDrawAmount;
	}
}