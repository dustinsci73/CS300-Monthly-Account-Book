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

public class RecordNode 
{
	private int day;
	private double amount;
	private RecordNode next;
                     
	public RecordNode(int day, double amount)
	{
		this.day = day;
		this.amount = amount;
		this.next = null;
	}
	public void setDay(int day)
	{ 
		this.day = day;
	}
	public int getDay()
	{
		return day;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	public double getAmount()
	{
		return amount;
	}
	public void setNext(RecordNode node)
	{
		this.next = node;
	}
	public RecordNode getNext()
	{
		return next;
	}
	
}
