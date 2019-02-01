//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (Monthly Account Book)
// Files:           (AccountBook.java, RecordNode.java)
// Course:          (CS300 Fall 2017)
//
// Author:          (Dustin Li)
// Email:           (dli284@wisc.edu)
// Lecturer's Name: (Gary Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (NONE)
// Online Sources:  (NONE)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;

public class AccountBook 
{
	private RecordNode head;
	private RecordNode tail;
	private double balance;
	
	public AccountBook()
	{
		this.head = null;
		this.tail = null;
		this.balance = 0;
	}
	
	/**
	 * Insert a record node into the account book. The money amount can be either
	 * negative, meaning the user spent money, or positive, meaning the user
	 * received money. If in the account book there are records on the same day, you
	 * need to insert the record after the last of them; Otherwise, you need to
	 * insert the record between records on earlier days and those on later days.
	 * 
	 * @param day
	 *            The day of the record to be inserted.
	 * @param amount
	 *            The money amount of the record.
	 */
	public void insertRecord(int day, double amount) 
	{
		if (day < 1 || day > 31)
		{
			System.out.println("WARNING: Invalid day number.");
		}
		else
		{
			RecordNode currNode = head;
			RecordNode prevNode = currNode;
			if (head == null)
			{
				head = new RecordNode(day, amount);
				tail = head;
			}
			else if (head.getDay() > day)
			{
				RecordNode input = new RecordNode(day, amount);
				input.setNext(head);
				head = input;
			}
			else
			{
				RecordNode newNode = new RecordNode(day, amount);
				while (currNode.getDay() <= day)
				{
					if (currNode.getNext() == null)
					{
						currNode.setNext(newNode);
						tail = newNode;
						newNode.setNext(null);
						return;
					}
					else
					{
						prevNode = currNode;
						currNode = currNode.getNext();
					}
				}
				prevNode.setNext(newNode);
				newNode.setNext(currNode);
			}	
		}
		
	}
	 
	/**
	 * Prepend a record into the account book. The day of the record should be the
	 * same as the EARLIEST record in the book. If there haven't been any records in
	 * the book yet, you should show user the warning message "WARNING: Unable to
	 * prepend a record, for no records in the account book yet." by printing it to
	 * the console.
	 * 
	 * @param amount
	 *            The money amount of the record to be prepended.
	 */
	public void prependRecord(double amount)
	{ 
		
		if (head == null)
		{
			System.out.println("WARNING: Unable to" + 
					" prepend a record, for no records in the account book yet.");
		}
		else 
		{
			int first = head.getDay();
			RecordNode firstRec = new RecordNode(first, amount);
			firstRec.setNext(head);
			head = firstRec;
			
		}
	}
	 
	/**
	 * Append a record into the account book. Similar as above, the day of the
	 * record should be the same as the LATEST record. If there haven't no records
	 * in the book yet, you should show user the warning message "WARNING: Unable to
	 * append a record, for no records in the account book yet.".
	 * 
	 * @param amount
	 *            The money amount of the record to be appended.
	 */
	public void appendRecord(double amount) 
	{
		if (tail == null)
		{
			System.out.println("WARNING: Unable to" + 
					" append a record, for no records in the account book yet.");
		}
		else
		{
			int last = tail.getDay();
			tail.setNext(new RecordNode(last, amount));
			tail = new RecordNode(last, amount);
		}
	}
	 
	/**
	 * Remove a record from the account book. The two arguments identify which
	 * record to remove. E.g., with day being 4 and seq_num being 2, the user
	 * would like to delete the second record on the 4th day. If the number of
	 * records on day is smaller than seq_num, you show user the warning message
	 * "WARNING: Unable to remove a record, for not enough records on the day
	 * specified.".
	 * 
	 * @param day
	 *            The day of the record to be removed.
	 * @param seq_num
	 *            The sequence number of the record within the day of it.
	 */
	public void removeRecord(int day, int seq_num)
	{
		if (day < 1 || day > 31)
		{
			System.out.println("WARNING: Invalid day number.");
			return;
		}
		else
		{
			if (seq_num <= 0)
			{
				System.out.println("WARNING: Invalid sequence number.");
				return;
			}
			else
			{
				if (head == null)
				{
					System.out.println("WARNING: Unable to remove a record, for not enough records on "
							+ "the day specified.");
					return;
				}
				else 
				{
					RecordNode currNode = head;
					RecordNode prevNode = currNode;
					while (currNode.getDay() < day)
					{
						if (currNode.getNext() != null)
						{
							prevNode = currNode;
							currNode = currNode.getNext();			
						}
						else
						{
							System.out.println("WARNING: Unable to remove a record, for not enough records "
									+ "on the day specified.");
							return;
						}
					}
					if (currNode.getDay() > day)
					{
						System.out.println("WARNING: Unable to remove a record, for not enough records "
								+ "on the day specified.");
						return;
					}
					else if (currNode.getDay() == day)
					{
						for (int i = 1; i < seq_num; i++)
						{
							if (currNode.getNext() == null)
							{
								i = seq_num;
								System.out.println("WARNING: Unable to remove a record, for not enough "
									+ "records on the day specified.");
								return;
							}
							else
							{
								prevNode = currNode;
								currNode = currNode.getNext();
							}
						}
					}
					if (currNode != head)
					{
						if (currNode.getNext() != null)
						{
							prevNode.setNext(currNode.getNext());
						}
						else 
						{
							tail = prevNode;
							prevNode.setNext(null);
						}
					}
					else if (currNode == head)
					{
						head = currNode.getNext();
					}
				}
			}
		}
	}
	 
	/**
	 * Modify a record in the account book. Similar as above, day and seq_num
	 * identify which record to modify, while amount indicates the excepted money
	 * amount of the record after modification E.g., with the three arguments being
	 * 4 2 100 respectively, the user would like to modify the second record on the
	 * 4th day, and change the amount to 100. If the number of records on day is
	 * smaller than seq_num , you should show user the warning message "WARNING:
	 * Unable to modify a record, for not enough records on the day specified.".
	 * 
	 * @param day
	 *            The day of the record to be modified.
	 * @param seq_num
	 *            The sequence number of the record within the day of it.
	 * @param amount
	 *            The amount of the record after modified.
	 */
	public void modifyRecord(int day, int seq_num, double amount) 
	{
		if (day < 1 || day > 31)
		{
			System.out.println("WARNING: Invalid day number.");
		}
		else
		{
			if (seq_num <= 0)
			{
				System.out.println("WARNING: Invalid sequence number.");
			}
			else
			{

				if (head == null)
				{
					System.out.println("WARNING: Unable to remove a record, for not enough records on "
							+ "the day specified.");
				}
				else 
				{
					RecordNode currNode = head;
					//RecordNode prevNode = currNode;
					while (currNode.getDay() < day)
					{
						if (currNode.getNext() != null)
						{
							currNode = currNode.getNext();			
						}
						else
						{
							System.out.println("WARNING: Unable to modify a record, for not enough records "
									+ "on the day specified.");
							return;
						}
					}
					if (currNode.getDay() > day)
					{
						System.out.println("WARNING: Unable to modify a record, for not enough records "
								+ "on the day specified.");
						return;
					}
					else if (currNode.getDay() == day)
					{
						for (int i = 0; i < seq_num - 1; i++)
						{
							if (currNode == null)
							{
								i = seq_num - 1;
								System.out.println("WARNING: Unable to modify a record, for not enough "
									+ "records on the day specified.");
							}
							else
							{
								//prevNode = currNode;
								currNode = currNode.getNext();
							}
						}
					}
					if (currNode != null)
					{
						currNode.setAmount(amount);
					}
					else 
					{
						System.out.println("WARNING: Unable to modify a record, for not enough "
								+ "records on the day specified.");
					}
				}
			}
		}
	}
	 
	/**
	 * Show user the overall balance by printing some leading textual prompt
	 * followed by the balance to the console, e.g., "Balance: -90.95". The balance
	 * should be initialized as 0 at first, and accumulates as the user
	 * insert/prepend/append/remove/modify records.
	 */
	public void showBalance() 
	{
		RecordNode currNode = head;
		balance = 0;
		while (currNode.getNext() != null)
		{
			balance += currNode.getAmount();
			currNode = currNode.getNext();
		}
		balance += currNode.getAmount();
		System.out.printf("Balance: $%.2f\n", balance);
	}
	 
	/**
	 * Display all the records so far as well as the overall balance. If there
	 * haven't been no records in the book yet, you should display "No records in
	 * the book yet." before displaying the account balance.
	 */
	public void display() 
	{
		balance = 0;
		if (head != null)
			{
				RecordNode currNode = head;
				System.out.println("Day       Amount");
				System.out.println("=================");
				while (currNode.getNext() != null)
				{
					System.out.print(currNode.getDay());
					System.out.printf("         $%.2f\n", currNode.getAmount());
					balance += currNode.getAmount();
					currNode = currNode.getNext();
				}
				if (currNode.getNext() == null)
				{
					System.out.print(currNode.getDay());
					System.out.printf("         $%.2f\n", currNode.getAmount());
					balance += currNode.getAmount();
				}
			}
		else
		{
			System.out.println("No records in the book yet.");
		}
		System.out.println();
		System.out.printf("Balance: $%.2f\n", balance);
		
	}
	 
	/**
	 * Show the records and accumulated balance on the day specified. If in the
	 * account book there haven't been any records on the day specified yet, you
	 * should display "No records on the day yet." before displaying the accumulated
	 * balance.
	 * 
	 * @param day
	 *            The day of the summary to be shown.
	 */
	public void showDaySummary(int day) 
	{
		if (day < 1 || day > 31)
		{
			System.out.println("WARNING: Invalid day number.");
		}
		else
		{
			RecordNode currNode = head;
			balance = 0;
			while (currNode.getDay() != day)
			{
				if (currNode.getNext() == null)
				{
					System.out.println("No records on the day yet.");
					return;
				}
				else
				{
					currNode = currNode.getNext();
				}
			}
			if (currNode.getDay() == day)
			{
				System.out.println("Day       Amount");
				System.out.println("=================");
				while (currNode.getDay() == day)
				{
					System.out.print(day);
					System.out.printf("         $%.2f\n", currNode.getAmount());
					balance += currNode.getAmount();
					if (currNode.getNext() != null)
					{
						currNode = currNode.getNext();
					}
					else
					{
						System.out.println();
						System.out.printf("Accumulated Balance: $%.2f\n", balance);
						return;
					}
				}
				System.out.println();
				System.out.printf("Accumulated Balance: $%.2f\n", balance);
			}
		}
	}
	public static void main(String[] args) 
	{
		AccountBook book = new AccountBook();
		Scanner scnr = new Scanner(System.in);
		String inputOne = "";
		do {
			System.out.println("=====Account Book Program=====");
			System.out.println("1. Enter 'i' to insert a record into the account book"); 
			System.out.println("2. Enter 'p' to prepend a record into the account book"); 
			System.out.println("3. Enter 'a' to append a record into the account book"); 
			System.out.println("4. Enter 'r' to remove a record from the account book"); 
			System.out.println("5. Enter 'm' to modify a record in the account book");
			System.out.println("6. Enter 'b' to show the overall balance"); 
			System.out.println("7. Enter 'd' to display all the records and the overall balance"); 
			System.out.println("8. Enter 's' to show the records and accumulated balance on a day"); 
			System.out.println("0. Enter 'q' to quit the program"); 
			System.out.println("==============================");
			System.out.println("Please enter your command: ");
			String input = scnr.nextLine();
			String[] inputs = input.split(" ");
			inputOne = inputs[0].toLowerCase();
			switch (inputOne)
			{
				case "i" : book.insertRecord(Integer.parseInt(inputs[1]),Double.parseDouble(inputs[2]));
					break;
				case "p" : book.prependRecord(Double.parseDouble(inputs[1]));
					break;
				case "a" : book.appendRecord(Double.parseDouble(inputs[1]));
					break;
				case "r" : book.removeRecord(Integer.parseInt(inputs[1]),Integer.parseInt(inputs[2]));
					break;
				case "m" : book.modifyRecord(Integer.parseInt(inputs[1]),Integer.parseInt(inputs[2]),
											Double.parseDouble(inputs[3]));
					break;
				case "b" : book.showBalance();
					break;
				case "d" : book.display();
					break;
				case "s" : book.showDaySummary(Integer.parseInt(inputs[1]));
					break;
				case "q" : return;
				default :
					System.out.println("WARNING: Unrecognized command.");
					//break;
				}
			} while (true);
	}
}
