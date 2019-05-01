package principal;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import bbdd.*;
import modelos.*;


public class Main {
	static Scanner sc = new Scanner(System.in);
	static BD_Credenciales bdCre = new BD_Credenciales();
	static BD_CliEmpJef bdCliEmpJef = new BD_CliEmpJef();
	static BD_PistaSala bdPisSal = new BD_PistaSala();
	static BD_ReservaVisita bdResVis = new BD_ReservaVisita();
	static BD_Conector conector = new BD_Conector();
	public static void main(String[] args) {
		BD_Conector.BD_Ini("escapedaw");

		int opc = 0;
		String rol;
		do {
			try {
				System.out.println("\nMENU CONECTARSE");
				System.out.println("1. Conectarse");
				System.out.println("2. Salir de la aplicación");
				opc = sc.nextInt();
				sc.nextLine();
				switch (opc) {
				case 1:
					System.out.println("Introduzca usuario: ");
					String usuario = sc.nextLine();
					System.out.println("Introduzca clave: ");
					String clave = sc.nextLine();
					rol = bdCre.conectarse(usuario.toUpperCase(), clave);

					switch (rol) {
					case "J":
						menuJefe(usuario);
						break;
					case "E":
						System.out.println("MENU EMPLEADO");
						break;
					case "C":
						System.out.println("MENU CLIENTE");
						break;
					default:
						System.out.println("¡Datos incorrectos!");
						break;
					}
					break;
				case 2:
					System.out.println("Adios!");
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
				}
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("\nHas introducido un caracter no válido");
			}
		} while (opc != 2);
	}

	public static void menuJefe(String usuario) {
		int opc = 0;
		do {
			try {
				System.out.println("\nMENU JEFE");
				System.out.println("1. Gestionar credenciales");
				System.out.println("2. Gestionar empleados");
				System.out.println("3. Gestionar salas");
				System.out.println("4. Ver datos clientes");
				System.out.println("5. Ver datos reservas");
				System.out.println("6. Ver datos visitas");
				System.out.println("7. Ver datos facturación");
				System.out.println("8. Desconectarse");

				opc = sc.nextInt();
				sc.nextLine();
				switch (opc) {
				case 1:
					menuJefeCredenciales(usuario);
					break;
				case 2:
					menuJefeEmpleados(usuario);
					break;
				case 3:
					menuJefeSala(usuario);
					break;
				case 4:
					menuJefeCliente();
					break;
				case 5:
					menuJefeReserva();
					break;
				case 6:
					menuJefeVisita();
					break;
				case 7:
					menuJefeFacturacion();
					break;
				case 8:
					System.out.println("Desconectado");
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
				}
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("\nHas introducido un caracter no válido");
			}
		} while (opc != 8);

	}

	public static void menuJefeCredenciales(String usuario) {
		int opc = 0;
		String id;
		do {
			try {
				System.out.println("\nMENU CREDENCIALES");
				System.out.println("1. Crear credenciales");
				System.out.println("2. Modificar contraseña");
				System.out.println("3. Eliminar credenciales");
				System.out.println("4. Salir");

				opc = sc.nextInt();
				sc.nextLine();
				switch (opc) {
				case 1:
					System.out.println("Introduce dni:");
					String dni = sc.nextLine();
					id = bdCre.buscarId(dni, "empleados");
					if (id == null)
						System.out.println("\nFallo técnico, póngase en contacto con el administrador");
					else if (id.equalsIgnoreCase(""))
						System.out.println("\nNo encontrado");
					else {
						System.out.println("Introduce contraseña (15 caracteres):");
						String pass = sc.nextLine();
						int c = bdCre.anadir_Usuario(new Credencial(id, pass, 'E'));
						switch (c) {
						case -1:
							System.out.println("\nFallo técnico, póngase en contacto con el administrador");
							break;
						case 0:
							System.out.println("\nEn estos momentos no podemos atender su solicitud");
							break;
						default:
							System.out.println("\nAñadido correctamente");
							break;
						}
					}

					break;
				case 2:
					System.out.println("Introduce nueva contraseña:");
					String contraseña = sc.nextLine();

					int contra = bdCre.cambiar_clave(usuario, contraseña);
					switch (contra) {
					case -1:
						System.out.println("\nFallo técnico, póngase en contacto con el administrador");
						break;
					case 0:
						System.out.println("\nEn estos momentos no podemos atender su solicitud");
						break;
					default:
						System.out.println("\nCambiada correctamente");
						break;
					}

					break;
				case 3:
					System.out.println("Introduce usuario:");
					id = sc.nextLine();

					int eliminar = bdCre.eliminar_Usuario(id);
					switch (eliminar) {
					case -1:
						System.out.println("\nFallo técnico, póngase en contacto con el administrador");
						break;
					case 0:
						System.out.println("\nEn estos momentos no podemos atender su solicitud");
						break;
					default:
						System.out.println("\nEliminado correctamente");
						break;
					}
					break;
				case 4:
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
				}
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("\nHas introducido un caracter no válido");
			}
		} while (opc != 4);
	}

	public static void menuJefeEmpleados(String usuario) {
		int opc = 0;
		int sueldo=0;
		String idemp;
		do {
			try {
				System.out.println("\nGESTIONAR EMPLEADOS");
				System.out.println("1. Mostrar empleados");
				System.out.println("2. Añadir empleados");
				System.out.println("3. Modificar sueldo");
				System.out.println("4. Eliminar empleado");
				System.out.println("5. Salir");

				opc = sc.nextInt();
				sc.nextLine();
				switch (opc) {
				case 1:
					Vector<Empleado> ve = bdCliEmpJef.mostrarEmpleados();
					for (int i = 0; i < ve.size(); i++)
						System.out.println(ve.get(i).toString());
					if (ve.size() == 0)
						System.out.println("No hay empleados actualmente");
					break;
				case 2:
					System.out.println("Introduce NIF:");
					String nif = sc.nextLine();
					int id = conector.consultaNumeroSecuencial("ID", "empleados", "EM");
					String ident="EM"+(id+1);
					System.out.println("Introduce nombre:");
					String nombre = sc.nextLine();
					System.out.println("Introduce apellido:");
					String apellido = sc.nextLine();
					System.out.println("Introduce telefono:");
					String telefono = sc.nextLine();
					System.out.println("Introduce direccion:");
					String direccion = sc.nextLine();
					System.out.println("Introduce sueldo:");
					sueldo = sc.nextInt();
					sc.nextLine();
					Empleado emp = new Empleado(nif, ident, nombre, apellido, telefono, direccion, sueldo, usuario);
					if (bdCliEmpJef.añadirEmpleado(emp) == 1)
						System.out.println("Añadido");
					else
						System.out.println("No se ha podido añadir");
					break;
				case 3:
					System.out.println("Introduce id empleado:");
					idemp = sc.nextLine();
					try {
					System.out.println("Introduce nuevo sueldo:");
					sueldo = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nHas introducido un caracter no válido");
					}
					sc.nextLine();
					int cambioSueldo = bdCliEmpJef.actualizar_Empleado(idemp, sueldo);
					switch (cambioSueldo) {
					case -1:
						System.out.println("\nFallo técnico, póngase en contacto con el administrador");
						break;
					case 0:
						System.out.println("\nEn estos momentos no podemos atender su solicitud");
						break;
					default:
						System.out.println("\nActualizado correctamente");
						break;
					}

					break;
				case 4:
					System.out.println("Introduce id empleado:");
					idemp = sc.nextLine();

					int eliminar = bdCliEmpJef.eliminarEmpleado(idemp);
					switch (eliminar) {
					case -1:
						System.out.println("\nFallo técnico, póngase en contacto con el administrador");
						break;
					case 0:
						System.out.println("\nEn estos momentos no podemos atender su solicitud");
						break;
					default:
						System.out.println("\nEliminado correctamente");
						break;
					}
					break;
				case 5:
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
				}
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("\nHas introducido un caracter no válido");
			}
		} while (opc != 5);
	}
	
	public static void menuJefeSala(String usuario) {
		int opc = 0;
		String idemp;
		int precio=0;
		do {
			try {
				System.out.println("\nGESTIONAR SALAS");
				System.out.println("1. Mostrar salas");
				System.out.println("2. Añadir salas");
				System.out.println("3. Modificar precio sala");
				System.out.println("4. Añadir/Modificar empleado responsable de la sala");
				System.out.println("5. Eliminar sala");
				System.out.println("6. Salir");

				opc = sc.nextInt();
				sc.nextLine();
				switch (opc) {
				case 1:
					Vector<Sala> ve = bdPisSal.listarSalas();
					for (int i = 0; i < ve.size(); i++)
						System.out.println(ve.get(i).toStringMio());
					if (ve.size() == 0)
						System.out.println("No hay salas creadas actualmente");
					break;
				case 2:
					String nSala=Sala.calcularNumeroSala("nSala", "salas", "SA");
					System.out.println(nSala);
					System.out.println("Tipo sala:");
					String tipo = sc.nextLine();
					System.out.println("Dificultad:");
					String dificultad = sc.nextLine();
					System.out.println("Número personas:");
					int numPersonas = sc.nextInt();
					System.out.println("Precio:");
					precio = sc.nextInt();
					sc.nextLine();
					Sala sala = new Sala(nSala, null, usuario, tipo, dificultad, numPersonas, precio);
					if (bdPisSal.crearSala(sala) == 1)
						System.out.println("Añadido");
					else
						System.out.println("No se ha podido añadir");
					break;
				case 3:
					System.out.println("Introduce número sala:");
					nSala = sc.nextLine();
					try {
					System.out.println("Introduce nuevo precio sala:");
					precio = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nHas introducido un caracter no válido");
					}
					sc.nextLine();
					Sala sa=new Sala(nSala,precio);
					int actuPrecio = bdPisSal.modificarPrecio(sa);
					switch (actuPrecio) {
					case -1:
						System.out.println("\nFallo técnico, póngase en contacto con el administrador");
						break;
					case 0:
						System.out.println("\nEn estos momentos no podemos atender su solicitud");
						break;
					default:
						System.out.println("\nActualizado correctamente");
						break;
					}

					break;
				case 4:
					Vector<String> v = new Vector<String>();
					v = bdCliEmpJef.mostrarIdEmpleados();
					for(int i=0;i<v.size();i++) {
						System.out.println(v.get(i));
					}
					if(v.size()==0)
						System.out.println("No hay empleados actualmente");
					else {
						System.out.println("Empleado:");
						String empleado=sc.nextLine();
						Vector<Sala> vs = new Vector<Sala>();
						vs = bdPisSal.listarSalas();
						for(int i=0;i<vs.size();i++) {
							System.out.println(vs.get(i).toString());
						}
						System.out.println("Sala:");
						nSala=sc.nextLine();
						int actuEmple = bdPisSal.modificarIdEmple(empleado,nSala);
						switch (actuEmple) {
						case -1:
							System.out.println("\nFallo técnico, póngase en contacto con el administrador");
							break;
						case 0:
							System.out.println("\nEn estos momentos no podemos atender su solicitud");
							break;
						default:
							System.out.println("\nActualizado correctamente");
							break;
						}
					}
					
					break;
				case 5:
					Vector<Sala> vs = new Vector<Sala>();
					vs = bdPisSal.listarSalas();
					for(int i=0;i<vs.size();i++) {
						System.out.println(vs.get(i).toString());
					}
					System.out.println("Sala:");
					nSala=sc.nextLine();

					int eliminar = bdPisSal.borrarSala(nSala) ;
					switch (eliminar) {
					case -1:
						System.out.println("\nFallo técnico, póngase en contacto con el administrador");
						break;
					case 0:
						System.out.println("\nEn estos momentos no podemos atender su solicitud");
						break;
					default:
						System.out.println("\nEliminado correctamente");
						break;
					}
					break;
				case 6:
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
				}
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("\nHas introducido un caracter no válido");
			}
		} while (opc != 6);
	}
	
	public static void menuJefeCliente() {
		Vector<Cliente> ve = bdCliEmpJef.mostrarClientes();
		for (int i = 0; i < ve.size(); i++)
			System.out.println(ve.get(i).toString());
		if (ve.size() == 0)
			System.out.println("No hay clientes actualmente");
	}
	
	public static void menuJefeReserva() {
		Vector<Reserva> ve = bdResVis.mostrarReservas();
		for (int i = 0; i < ve.size(); i++)
			System.out.println(ve.get(i).toString());
		if (ve.size() == 0)
			System.out.println("No hay reservas actualmente");
	}
	
	public static void menuJefeVisita() {
		/*Vector<Reserva> ve = bdResVis;
		for (int i = 0; i < ve.size(); i++)
			System.out.println(ve.get(i).toString());
		if (ve.size() == 0)
			System.out.println("No hay reservas actualmente");*/
	}
	
	public static void menuJefeFacturacion() {
		/**/
	}
	
	
}
