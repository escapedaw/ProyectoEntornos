package principal;

import java.util.Scanner;
import java.util.Vector;

import bbdd.*;
import modelos.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BD_Conector.BD_Ini("escapedaw");
		BD_CliEmpJef bdCliEmpJeg = new BD_CliEmpJef();
		Cliente cli;
		Empleado emp;
		int opc;
		String dni;
		
		System.out.println("1. Listar clientes");
		System.out.println("2. Añadir cliente");
		System.out.println("3. Eliminar cliente");
		System.out.println("4. Listar empleados");
		System.out.println("5. Contratar empleado");
		System.out.println("6. Modificar empleado");
		opc = sc.nextInt();
		sc.nextLine();
		switch (opc) {
		case 1:
			Vector<Cliente> vc = bdCliEmpJeg.mostrarClientes();
			for (int i = 0; i < vc.size(); i++)
				System.out.println(vc.get(i).toString());
			if(vc.size()==0)
				System.out.println("No hay clientes actualmente");
			break;
		case 2:
			System.out.println("Introduce NIF:");
			String nif = sc.nextLine();
			System.out.println("Introduce identificador empleado:");
			String id_emple = sc.nextLine();
			System.out.println("Introduce nombre:");
			String nombre = sc.nextLine();
			System.out.println("Introduce apellido:");
			String apellido = sc.nextLine();
			System.out.println("Introduce direccion:");
			String direccion = sc.nextLine();
			System.out.println("Introduce telefono:");
			String telefono = sc.nextLine();
			cli = new Cliente(nif,id_emple,nombre,apellido, direccion, telefono);
			if (bdCliEmpJeg.añadirCliente(cli) == 1)
				System.out.println("Añadido");
			else
				System.out.println("No se ha podido añadir");
			break;
		case 3:
			System.out.println("Introduce dni cliente");
			dni = sc.nextLine();
			if (bdCliEmpJeg.eliminarCliente(dni) == 1)
				System.out.println("Eliminado");
			else
				System.out.println("No se ha podido eliminar");
			break;
		case 4:
			Vector<Empleado> ve = bdCliEmpJeg.mostrarEmpleados();
			for (int i = 0; i < ve.size(); i++)
				System.out.println(ve.get(i).toString());
			if(ve.size()==0)
				System.out.println("No hay empleados actualmente");
			break;
		case 5:
			System.out.println("Introduce NIF:");
			nif = sc.nextLine();
			System.out.println("Introduce ID:");
			String id = sc.nextLine();
			System.out.println("Introduce nombre:");
			nombre = sc.nextLine();
			System.out.println("Introduce apellido:");
			apellido = sc.nextLine();
			System.out.println("Introduce telefono:");
			telefono = sc.nextLine();
			System.out.println("Introduce direccion:");
			direccion = sc.nextLine();
			System.out.println("Introduce sueldo:");
			int sueldo = sc.nextInt();
			sc.nextLine();
			System.out.println("Introduce id_jefe:");
			String id_jefe = sc.nextLine();
			emp = new Empleado(nif,id,nombre,apellido, telefono, direccion,sueldo,id_jefe);
			if (bdCliEmpJeg.añadirEmpleado(emp) == 1)
				System.out.println("Añadido");
			else
				System.out.println("No se ha podido añadir");
			break;
		default:
			System.out.println("Opción incorrecta");
			break;
		}

	}

}
