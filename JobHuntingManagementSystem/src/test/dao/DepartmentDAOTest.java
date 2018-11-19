package test.dao;

import java.util.ArrayList;

import dao.DepartmentDAO;
import dto.Department;

public class DepartmentDAOTest {

	public static void main(String[] args) {
//		String department_id = "s";
//		int number = DepartmentDAO.getNumber(department_id);
//		System.out.println("数字:" + number);

		ArrayList<Department> departmentList = DepartmentDAO.getDepartmentList();
		for(Department department : departmentList){
			System.out.println("学科ID:" + department.getDepartment_id());
			System.out.println("学科名:" + department.getDepartment_name());
			System.out.println();
		}
	}
}