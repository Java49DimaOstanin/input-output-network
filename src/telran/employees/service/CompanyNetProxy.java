package telran.employees.service;

import java.util.List;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.*;
import telran.employees.dto.SalaryDistribution;
import telran.net.TcpHandler;

public class CompanyNetProxy implements Company {
	private TcpHandler tcpHandler;
	public CompanyNetProxy(TcpHandler tcpHandler) {
		this.tcpHandler = tcpHandler;
	}

	@Override
	public boolean addEmployee(Employee empl) {
		
		return tcpHandler.send("empployee/add", empl);
	}

	@Override
	public Employee removeEmployee(long id) {
		
		return tcpHandler.send("employee/remove", id);
	}

	@Override
	public Employee getEmployee(long id) {
		
		return tcpHandler.send("employee/get", id);
	}

	@Override
	public List<Employee> getEmployees() {
		
		return tcpHandler.send("employees", null);
	}

	@Override
	public List<DepartmentSalary> getDepartmentSalaryDistribution() {
		
		return tcpHandler.send("department/salary.destribution", null);
	}

	@Override
	public List<SalaryDistribution> getSalaryDistribution(int interval) {
		
		
		return tcpHandler.send("salary/", interval);
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		
		return tcpHandler.send("employees/deparment", 	department);
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		
		return tcpHandler.send("employees/salary", new FromTo(salaryFrom,salaryTo));
	}

	@Override
	public List<Employee> getEmployeesByAge(int ageFrom, int ageTo) {
		
		return tcpHandler.send("employees/age", new FromTo(ageFrom,ageTo));
	}

	@Override
	public Employee updateSalary(long id, int newSalary) {
		
		return tcpHandler.send("salary/update", new UpdateData<Integer>(id,newSalary));
	}

	@Override
	public Employee updateDepartment(long id, String newDepatment) {
		// TODO Auto-generated method stub
		return tcpHandler.send("salary/update", new UpdateData<String>(id,newDepatment));
	}

}