package telran.view.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.function.BinaryOperator;

import telran.view.*;

public class SimpleCalculatorAppl {

	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		Menu menu = new Menu("Main",getListItems());
		
		Menu menuData = new Menu("Date Operations",getDataItems());
		menu.perform(io);

	}
	static void calculate(InputOutput io,BinaryOperator<Double> operator) {
		double first = io.readDouble("Enter first number", "Must be any number");
		double second = io.readDouble("Enter second number", "Must be any number");
		io.writeLine(operator.apply(first,second));
	}
	
	static Menu getOperationsMenu() {
		return new Menu("Number Operations",getOperationsItems());
	}
	static Menu getDataMenu() {
		return new Menu("Date Operations",getDataItems());
	}
	static Item[] getListItems() {
		Item[] ListItems = {
				Item.of("Number Operations", io -> getOperationsMenu().perform(io))	,
				Item.of("Date Operations",  io -> getDataMenu().perform(io)),
				Item.ofExit()	
		};
		return ListItems;
	}
	static Item[] getOperationsItems() {
		Item[] OperationsItems = {
			Item.of("Add numbers", io -> calculate(io,(a,b)-> a + b))	,
			Item.of("Subtract number", io -> calculate(io,(a,b)-> a - b))	,
			Item.of("Multiply number",  io -> calculate(io,(a,b)-> a * b))	,
			Item.of("Divide number",  io -> calculate(io,(a,b)-> a / b))	,
			Item.ofExit()
		};
		return OperationsItems;
	}
	static Item[] getDataItems() {
		Item[] dataItems = {
				Item.of("Date after a given number of days", io -> dateCalc(io,false))	,
				Item.of("Date before a given number of days",  io -> dateCalc(io,true))	,
				Item.of("Days between two dates",  io -> dateCalcBetween(io))	,
				Item.ofExit()	
		};
		return dataItems;
	}
	static void dateCalc(InputOutput io,boolean subtractDays) {
		
		long days = io.readLong("Enter amount of days", "Must be any number");
		if(subtractDays) {
			days = (~(days -1));
		}
		LocalDateTime todayDate = LocalDateTime.now();
		LocalDateTime newDate = todayDate.plusDays(days);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");
		io.writeLine("Today date: " + todayDate.format(dtf)+ "   Shifted date: " + newDate.format(dtf)  );
	}
	static void dateCalcBetween(InputOutput io) {
        LocalDate firstDate = io.readDate("Enter first date (yyyy-MM-dd)", "Incorrect date format");
        LocalDate secondDate = io.readDate("Enter second date (yyyy-MM-dd)", "Incorrect date format");

        long daysBetween = ChronoUnit.DAYS.between(firstDate, secondDate);
        io.writeLine("Days between two dates: " + daysBetween);
    }
	
	
}
