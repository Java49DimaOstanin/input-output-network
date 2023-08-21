package telran.employees.controller;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.SalaryDistribution;
import telran.employees.service.Company;
import telran.view.InputOutput;
import telran.view.Item;


public class CompanyController {
   private static final long MIN_ID = 100000;
private static final long MAX_ID = 999999;
private static final int MIN_SALARY = 6000;
private static final int MAX_SALARY = 50000;
private static final int MAX_AGE = 75;
private static final int MIN_AGE = 20;
static Company company;
static Set<Integer> idList;
	public static ArrayList<Item> getCompanyItems(Company company) {
		CompanyController.company = company;
		
		ArrayList<Item> res = new ArrayList<>(Arrays.asList(
				getItems()));
		return res;
	}
	private static Item[] getItems() {
		
		return new Item[] {
				Item.of(" Add new Employee", CompanyController::addEmployeeItem),
				Item.of(" Remove Employee", CompanyController::removeEmployeeItem),
				Item.of(" All Employees", CompanyController::getEmployeesItem),
				Item.of(" Data about Employee", CompanyController::getEmployeeItem),
				Item.of(" Employees by Salary", CompanyController::getEmployeesBySalaryItem),
				Item.of(" Employees by Department", CompanyController::getEmployeesByDepartmentItem),
				Item.of(" Update salary", CompanyController::updateSalaryItem),
				Item.of(" Departments and Salary", CompanyController::getDepartmentSalaryDistributionItem),
				Item.of(" Distribution by Salary", CompanyController::getSalaryDistributionItem),
				Item.of("Employees by Age", CompanyController::getEmployeesByAgeItem),
				Item.of("Update Department", CompanyController::updateDepartmentItem)
		};
	}
	static private Set<String> departments = new HashSet<>(Arrays.asList(new String[] {
			"QA", "Development", "Audit", "Management", "Accounting"
	}));
	
	static void addEmployeeItem(InputOutput io) {
		long id = io.readLong("Enter Employee identity", "Wrong identity value", MIN_ID, MAX_ID);
		if(checkId(id)) {
			io.writeLine("Employee with this id already exist");
			addEmployeeItem(io);
			
		}
		String name = io.readString("Enter name", "Wrong name",
				str -> str.matches("[A-Z][a-z]+"));
		String department = io.readString("Enter department", "Wrong department", departments );
		int salary = io.readInt("Enter salary", "Wrong salary", MIN_SALARY, MAX_SALARY);
		LocalDate birthDate = io.readDate("Enter birth data", "Wrong birth date entered",
				getBirthdate(MAX_AGE), getBirthdate(MIN_AGE));
		boolean res = company.addEmployee(new Employee(id, name, department, salary, birthDate));
		io.writeLine(res ? String.format("Employee with id %d has been added", id) : 
			String.format("Employee with id %d already exists", id));
	}
	private static LocalDate getBirthdate(int age) {
		
		return LocalDate.now().minusYears(age);
	}
	private static boolean checkId(long id) {
		
		boolean res = company.getEmployee(id) != null;
		return res;
	}
	static void removeEmployeeItem(InputOutput io) {
		long id = io.readLong("Enter Employee ID", "Wrong ID");
		Employee employee = company.removeEmployee(id);
		io.writeLine(employee != null? String.format("Employee with id %d has been deleted", id) : 
			String.format("Employee with id %d not exists", id) );
	}
	static void getEmployeeItem(InputOutput io) {
		long id = io.readLong("Enter Employee ID", "Wrong ID");
		Employee employee = company.getEmployee(id);
		io.writeLine(employee != null?  employee.toString()  : 
			String.format("Employee with id %d not exists", id) );
	}
	static void getEmployeesItem(InputOutput io) {
		company.getEmployees().forEach(io::writeLine);
	}
	static void getDepartmentSalaryDistributionItem(InputOutput io) {
		company.getDepartmentSalaryDistribution().forEach(io::writeLine);
		
	}
	static void getSalaryDistributionItem(InputOutput io) {
		company.getSalaryDistribution(io.readInt("Enter salary distribution interval", "Wrong Interval(Must be number)")).forEach(io::writeLine);
	}
	static void getEmployeesByDepartmentItem(InputOutput io) {
		company.getEmployeesByDepartment(io.readString("Enter department", "Wrong department", departments )).forEach(io::writeLine);
	}
	static void getEmployeesBySalaryItem(InputOutput io) {
		int salaryFrom =  io.readInt("Enter Salary from ", "Wrong salary from", MIN_SALARY, MAX_SALARY);
		int salaryTo =  io.readInt("Enter Salary to", "Wrong salary to", salaryFrom, MAX_SALARY);
		company.getEmployeesBySalary(salaryFrom, salaryTo).forEach(io::writeLine);
	}
	static void getEmployeesByAgeItem(InputOutput io) {
		int ageFrom = io.readInt("Enter Age from", "Wrong Age from", MIN_AGE, MAX_AGE);
		int ageTo = io.readInt("Enter Age to", "Wrong Age to", ageFrom, MAX_AGE);
		company.getEmployeesByAge(ageFrom, ageTo).forEach(io::writeLine);
	}
	static void updateSalaryItem(InputOutput io) {
		long id = io.readLong("Enter ID of Employee", "Wrong ID number");
		if(checkId(id)) {
			int newSalary = io.readInt("Enter new salary", "Wrong salary", MIN_SALARY, MAX_SALARY);
			company.updateSalary(id, newSalary);
			io.writeLine("Salary updated");
			
		}else {
			io.writeLine(String.format("Employee with id %d not exists", id));
			updateSalaryItem(io);
		}
	}
	static void updateDepartmentItem(InputOutput io) {
		long id = io.readLong("Enter ID of Employee", "Wrong ID number");
		if(checkId(id)) {
			String newdepartment = io.readString("Enter new department", "Wrong department", departments );
			company.updateDepartment(id, newdepartment);
			io.writeLine("Deparment updated");
			
		}else {
			io.writeLine(String.format("Employee with id %d not exists", id));
			updateSalaryItem(io);
		}
	}
	
	

}