package principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.time.temporal.ChronoUnit;

import javax.swing.Timer;

import bbdd.BD_CliEmpJef;
import bbdd.BD_Conector;
import bbdd.BD_Credenciales;
import bbdd.BD_PistaSala;
import bbdd.BD_ReservaVisita;
import modelos.Cliente;
import modelos.Credencial;
import modelos.Empleado;
import modelos.Pista;
import modelos.Reserva;
import modelos.Sala;
import modelos.Visita;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static BD_Credenciales bdCre = new BD_Credenciales();
	static BD_CliEmpJef bdCliEmpJef = new BD_CliEmpJef();
	static BD_PistaSala bdPisSal = new BD_PistaSala();
	static BD_ReservaVisita bdResVis = new BD_ReservaVisita();
	static BD_Conector conector = new BD_Conector();
	static boolean checkJuego=false;
	static Timer timer;

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
						menuEmpleado(usuario);
						break;
					case "C":
						menuCliente(usuario);
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
				System.out.println("1. Crear credenciales para empleados");
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
		int sueldo = 0;
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
					String ident = "EM" + (id + 1);
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
		int precio = 0;
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
					String nSala = Sala.calcularNumeroSala("nSala", "salas", "SA");
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
					Sala sa = new Sala(nSala, precio);
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
					for (int i = 0; i < v.size(); i++) {
						System.out.println(v.get(i));
					}
					if (v.size() == 0)
						System.out.println("No hay empleados actualmente");
					else {
						System.out.println("Empleado:");
						String empleado = sc.nextLine();
						Vector<Sala> vs = new Vector<Sala>();
						vs = bdPisSal.listarSalas();
						for (int i = 0; i < vs.size(); i++) {
							System.out.println(vs.get(i).toString());
						}
						System.out.println("Sala:");
						nSala = sc.nextLine();
						int actuEmple = bdPisSal.modificarIdEmple(empleado, nSala);
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
					for (int i = 0; i < vs.size(); i++) {
						System.out.println(vs.get(i).toString());
					}
					System.out.println("Sala:");
					nSala = sc.nextLine();

					int eliminar = bdPisSal.borrarSala(nSala);
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
		Vector<Visita> vVis = bdResVis.mostrarVisitas();
		if (vVis == null)
			System.out
					.println("\nHa surgido un problema técnico. Póngase en contacto con el administrador");
		else if (vVis.size() == 0)
			System.out.println("\nNo hay visitas actualmente");
		else {
			for (int i = 0; i < vVis.size(); i++)
				System.out.println(vVis.get(i).toString());
		}
	}

	public static void menuJefeFacturacion() {
		DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd/LL/yyyy");
		String fechaInicio, fechaFin;
		System.out.println("Introduce fecha inicio (dd/mm/aaaa):");
		fechaInicio = sc.nextLine();
		System.out.println("Introduce fecha fin (dd/mm/aaaa):");
		fechaFin = sc.nextLine();
		LocalDate fechaI = LocalDate.parse(fechaInicio, fechaFormateada);
		LocalDate fechaF = LocalDate.parse(fechaFin, fechaFormateada);
		System.out.println("\nImporte: "+ bdResVis.facturacion(fechaI, fechaF)+" euros.");
	}

	public static void menuEmpleado(String usuario) {
		int opc = 0;
		do {
			try {
				System.out.println("\nMENU EMPLEADO");
				System.out.println("1. Gestionar credenciales");
				System.out.println("2. Gestionar clientes");
				System.out.println("3. Gestionar reservas");
				System.out.println("4. Gestionar pistas");
				System.out.println("5. Ver datos visitas");
				System.out.println("6. Desconectarse");

				opc = sc.nextInt();
				sc.nextLine();
				switch (opc) {
				case 1:
					menuEmpleadoCredenciales(usuario);
					break;
				case 2:
					menuEmpleadoClientes(usuario);
					break;
				case 3:
					menuEmpleadoReservas(usuario);
					break;
				case 4:
					menuEmpleadoPistas(usuario);
					break;
				case 5:
					Vector<Visita> vVis = bdResVis.mostrarVisitas();
					if (vVis == null)
						System.out
								.println("\nHa surgido un problema técnico. Póngase en contacto con el administrador");
					else if (vVis.size() == 0)
						System.out.println("\nNo hay visitas actualmente");
					else {
						for (int i = 0; i < vVis.size(); i++)
							System.out.println(vVis.get(i).toString());
					}
					break;
				case 6:
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
		} while (opc != 6);
	}

	public static void menuEmpleadoCredenciales(String usuario) {
		int opc = 0;
		String id;
		do {
			try {
				System.out.println("\nMENU CREDENCIALES");
				System.out.println("1. Crear credenciales para cliente");
				System.out.println("2. Modificar mi contraseña");
				System.out.println("3. Salir");

				opc = sc.nextInt();
				sc.nextLine();
				switch (opc) {
				case 1:
					System.out.println("Introduce dni:");
					String dni = sc.nextLine();
					id = bdCre.buscarId(dni, "clientes");
					if (id == null)
						System.out.println("\nFallo técnico, póngase en contacto con el administrador");
					else if (id.equalsIgnoreCase(""))
						System.out.println("\nNo encontrado");
					else {
						System.out.println("Introduce contraseña (15 caracteres):");
						String pass = sc.nextLine();
						int c = bdCre.anadir_Usuario(new Credencial(id, pass, 'C'));
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
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
				}
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("\nHas introducido un caracter no válido");
			}
		} while (opc != 3);
	}

	public static void menuEmpleadoClientes(String usuario) {
		int opc = 0;
		String idcli, telefono;
		do {
			try {
				System.out.println("\nGESTIONAR CLIENTES");
				System.out.println("1. Mostrar clientes");
				System.out.println("2. Añadir clientes");
				System.out.println("3. Modificar telefono");
				System.out.println("4. Eliminar cliente");
				System.out.println("5. Salir");

				opc = sc.nextInt();
				sc.nextLine();
				switch (opc) {
				case 1:
					Vector<Cliente> ve = bdCliEmpJef.mostrarClientes();
					for (int i = 0; i < ve.size(); i++)
						System.out.println(ve.get(i).toString());
					if (ve.size() == 0)
						System.out.println("No hay clientes actualmente");
					break;
				case 2:
					System.out.println("Introduce NIF:");
					String nif = sc.nextLine();
					int id = conector.consultaNumeroSecuencial("ID", "clientes", "CL");
					String ident = "CL" + (id + 1);
					System.out.println("Introduce nombre:");
					String nombre = sc.nextLine();
					System.out.println("Introduce apellido:");
					String apellido = sc.nextLine();
					System.out.println("Introduce telefono:");
					telefono = sc.nextLine();
					System.out.println("Introduce direccion:");
					String direccion = sc.nextLine();
					Cliente cli = new Cliente(nif, ident, usuario, nombre, apellido, direccion, telefono);
					if (bdCliEmpJef.añadirCliente(cli) == 1)
						System.out.println("Añadido");
					else
						System.out.println("No se ha podido añadir");
					break;
				case 3:
					System.out.println("Introduce id cliente:");
					idcli = sc.nextLine();
					System.out.println("Introduce nuevo telefono:");
					telefono = sc.nextLine();
					int cambioTelefono = bdCliEmpJef.actualizar_Cliente(idcli, telefono);
					switch (cambioTelefono) {
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
					System.out.println("Introduce id cliente:");
					idcli = sc.nextLine();

					int eliminar = bdCliEmpJef.eliminarCliente(idcli);
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

	public static void menuEmpleadoReservas(String usuario) {
		int opc = 0;
		DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd/LL/yyyy HH:mm");
		do {
			try {
				System.out.println("\nGESTIONAR RESERVAS");
				System.out.println("1. Mostrar reservas");
				System.out.println("2. Añadir reserva");
				System.out.println("3. Modificar personas en reserva");
				System.out.println("4. Modificar fecha en reserva");
				System.out.println("5. Eliminar reserva");
				System.out.println("6. Salir");

				opc = sc.nextInt();
				sc.nextLine();
				switch (opc) {
				case 1:
					Vector<Reserva> ve = bdResVis.mostrarReservas();
					for (int i = 0; i < ve.size(); i++)
						System.out.println(ve.get(i).toString());
					if (ve.size() == 0)
						System.out.println("No hay reservas actualmente");
					break;
				case 2:
					System.out.println("Introduce fecha (dd/mm/aaaa):");
					String fechaLeida = sc.nextLine();
					System.out.println("Introduce hora (hh:mm):");
					String horaLeida = sc.nextLine();
					String fechaHoraLeida = fechaLeida + " " + horaLeida;
					LocalDateTime fecha = LocalDateTime.parse(fechaHoraLeida, fechaFormateada);
					int id = conector.consultaNumeroSecuencial("COD_RESERVA", "reservas", "RE");
					String ident = "RE" + (id + 1);
					System.out.println("Introduce nSala:");
					String nSala = sc.nextLine();
					System.out.println("Introduce nif cliente:");
					String nifCliente = sc.nextLine();
					System.out.println("Introduce numero personas:");
					int numPersonas = sc.nextInt();
					sc.nextLine();
					Reserva res = new Reserva(ident, fecha, nSala, usuario, nifCliente, numPersonas);
					if (bdResVis.añadirReserva(res) == 1)
						System.out.println("Añadido");
					else
						System.out.println("No se ha podido añadir");
					break;
				case 3:
					System.out.println("Introduzca código reserva que desea modificar:");
					String codR = sc.nextLine();
					System.out.println("Introduzca número de personas:");
					int numP = sc.nextInt();
					sc.nextLine();
					if (bdResVis.modificarPersonasReserva(codR, numP) == 1)
						System.out.println("Modificado");
					else
						System.out.println("No se ha podido modificar");
					break;
				case 4:
					System.out.println("Introduzca código reserva que desea modificar:");
					codR = sc.nextLine();
					System.out.println("Introduce fecha (dd/mm/aaaa):");
					fechaLeida = sc.nextLine();
					System.out.println("Introduce hora (hh:mm):");
					horaLeida = sc.nextLine();
					fechaHoraLeida = fechaLeida + " " + horaLeida;
					fecha = LocalDateTime.parse(fechaHoraLeida, fechaFormateada);
					if (bdResVis.modificarFechaReserva(fecha, codR) == 1)
						System.out.println("Modificado");
					else
						System.out.println("No se ha podido modificar");
					break;
				case 5:
					System.out.println("Introduzca código reserva que desea eliminar:");
					codR = sc.nextLine();
					if (bdResVis.eliminarReserva(codR) == 1)
						System.out.println("Eliminada");
					else
						System.out.println("No se ha podido eliminar");
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
	
	public static void menuEmpleadoPistas(String usuario) {
		int opc = 0;
		do {
			try {
				System.out.println("\nGESTIONAR PISTAS");
				System.out.println("1. Mostrar pistas");
				System.out.println("2. Añadir pista");
				System.out.println("3. Modificar descripción pista");
				System.out.println("4. Eliminar pista");
				System.out.println("5. Ver pista solicitada");
				System.out.println("6. Confirmar pista solicitada");
				System.out.println("7. Resetear pistas");
				System.out.println("8. Salir");

				opc = sc.nextInt();
				sc.nextLine();
				switch (opc) {
				case 1:
					Vector<Pista> ve = bdPisSal.listarPistas();
					for (int i = 0; i < ve.size(); i++)
						System.out.println(ve.get(i).toString());
					if (ve.size() == 0)
						System.out.println("No hay pistas actualmente");
					break;
				case 2:
					int id = conector.consultaNumeroSecuencial("COD_PISTA", "pistas", "PI");
					String ident = "PI" + (id + 1);
					System.out.println("Introduce a qué sala pertenece:");
					String nsala = sc.nextLine();
					System.out.println("Introduce descripción pista:");
					String descripcion = sc.nextLine();
					
					Pista pis = new Pista (ident,nsala,descripcion);
					if (bdPisSal.crearPista(pis) == 1)
						System.out.println("Añadida la pista");
					else
						System.out.println("No se ha podido añadir");
					break;
				case 3:
					System.out.println("Introduzca código de la pista que desea modificar:");
					String codP = sc.nextLine();
					System.out.println("Introduzca la nueva descripción:");
					descripcion = sc.nextLine();
					if (bdPisSal.modificarDescripcion(descripcion,codP) == 1)
						System.out.println("Modificado");
					else
						System.out.println("No se ha podido modificar");
					break;
				case 4:
					System.out.println("Introduzca código de la pista que desea eliminar:");
					codP = sc.nextLine();
					if (bdPisSal.borrarPista(codP) == 1)
						System.out.println("Eliminada");
					else
						System.out.println("No se ha podido eliminar");
					break;
				case 5:
					ve = bdPisSal.pistasSolicitadas();
					for (int i = 0; i < ve.size(); i++)
						System.out.println(ve.get(i).toStringClienteConfirmadas());
					if (ve.size() == 0)
						System.out.println("\nNo hay pistas solicitadas");
					break;
				case 6:
					ve = bdPisSal.pistasSolicitadas();
					for (int i = 0; i < ve.size(); i++)
						System.out.println(i+" - "+ve.get(i).toStringClienteConfirmadas());
					if (ve.size() == 0)
						System.out.println("\nNo hay pistas solicitadas");
					else { //Comprobar el numero introducido
						System.out.println("¿Qué pista desea confirmar?");
						int numP=sc.nextInt();
						sc.nextLine();
						if(bdPisSal.confirmarPista(ve.get(numP).getCod_pista()))
							System.out.println("\nPista confirmada");
						else
							System.out.println("\nError confirmando la pista");
					}
					break;
				case 7:
					System.out.println("Introduce sala:");
					nsala = sc.nextLine();
					if(bdPisSal.reiniciarJuego(nsala)>=0)
						System.out.println("Pistas reseteadas");
					else
						System.out.println("No ha sido posible resetear las pistas");
					break;
				case 8:
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

	public static void menuCliente(String usuario) {
		int opc = 0;
		do {
			try {
				System.out.println("\nMENU CLIENTE");
				System.out.println("1. Modificar contraseña");
				System.out.println("2. Ver mis visitas");
				System.out.println("3. Ver mis reservas");
				System.out.println("4. Empezar juego");
				System.out.println("5. Desconectarse");

				opc = sc.nextInt();
				sc.nextLine();
				switch (opc) {
				case 1:
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
				case 2:
					Vector<Visita> vVis = bdResVis.mostrarVisitasID(usuario);
					if (vVis == null)
						System.out
								.println("\nHa surgido un problema técnico. Póngase en contacto con el administrador");
					else if (vVis.size() == 0)
						System.out.println("\nNo hay visitas actualmente");
					else {
						for (int i = 0; i < vVis.size(); i++)
							System.out.println(vVis.get(i).toString());
					}
					break;
				case 3:
					Vector<Reserva> vRes = bdResVis.mostrarReservasID(usuario);
					if (vRes == null)
						System.out
								.println("\nHa surgido un problema técnico. Póngase en contacto con el administrador");
					else if (vRes.size() == 0)
						System.out.println("\nNo hay reservas actualmente");
					else {
						for (int i = 0; i < vRes.size(); i++)
							System.out.println(vRes.get(i).toString());
					}
					break;
				case 4:
					String nsala=bdResVis.poderJugar(usuario);
					if(!nsala.equals("0")) {
						bdPisSal.reiniciarJuego(nsala);
						menuClienteJuego(usuario,nsala);
					}
					else
						System.out.println("No tienes una reserva hoy");
					break;
				case 5:
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
		} while (opc != 5);
	}
	
	public static void menuClienteJuego(String usuario, String nsala) {
		int opc = 0;
		long min=0, seg=0;
		timer = new Timer(3600000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n¡SE ACABO EL TIEMPO!");
				checkJuego=juegoAcabado(60,0,usuario,nsala);
				System.out.println("\nPulse una tecla para terminar");
			}
		});
	
		timer.start();
		LocalTime horaInicio = LocalTime.now();
		
		do {
			try {
				System.out.println("\nMENU JUEGO");
				System.out.println("1. Ver tiempo");
				System.out.println("2. Solicitar pista");
				System.out.println("3. Ver pistas solicitadas");
				System.out.println("4. Ver pistas disponibles");
				System.out.println("5. Finalizar juego");

				opc = sc.nextInt();
				sc.nextLine();
				if(checkJuego) {
					return;
				}
				switch (opc) {
				case 1:
					System.out.println("Tiempo transcurrido: "+ChronoUnit.MINUTES.between(horaInicio, LocalTime.now())+" minutos, "+(ChronoUnit.SECONDS.between(horaInicio, LocalTime.now()))%60+" segundos");
					break;
				case 2:
					int numP;
					Vector<Pista> ve = bdPisSal.listarPistasSala(nsala);
					for (int i = 0; i < ve.size(); i++)
						System.out.println(i+" - "+ve.get(i).toStringCliente());
					if (ve.size() == 0)
						System.out.println("\nNo hay pistas disponibles actualmente");
					else {
						System.out.println("\n¿Qué pista desea solicitar?");
						numP=sc.nextInt();
						if(bdPisSal.pedirPista(ve.get(numP).getCod_pista()))
							System.out.println("\nPista solicitada");
						else
							System.out.println("\nNo ha sido posible solicitar la pista");
					}
					break;
				case 3:
					ve = bdPisSal.listarPistasSalaCliente(nsala);
					for (int i = 0; i < ve.size(); i++)
						System.out.println(ve.get(i).toStringCliente());
					if (ve.size() == 0)
						System.out.println("\nNo hay pistas solicitadas");
					break;
				case 4:
					ve = bdPisSal.pistasConfirmadas(nsala);
					for (int i = 0; i < ve.size(); i++)
						System.out.println(ve.get(i).toStringClienteConfirmadas());
					if (ve.size() == 0)
						System.out.println("\nNo hay pistas disponibles");
					break;
				case 5:
					min=ChronoUnit.MINUTES.between(horaInicio, LocalTime.now());
					seg=(ChronoUnit.SECONDS.between(horaInicio, LocalTime.now()))%60;
					checkJuego=juegoAcabado(min,seg,usuario,nsala);
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
				}
			} catch (InputMismatchException e) {
				sc.nextLine();
				if(checkJuego)
					return;
				System.out.println("\nHas introducido un caracter no válido");
			}
		} while (opc != 5);
		
	}
	
	public static boolean juegoAcabado(long min, long seg, String usuario,String nsala) {
		System.out.println("\nHabéis tardado: "+min+" minutos, "+seg+" segundos.");
		int id = conector.consultaNumeroSecuencial("COD_VISITA", "visitas", "VI");
		String ident = "VI" + (id + 1);
		if(bdResVis.añadirVisitaTrasJugar(usuario, min,ident)) {
			System.out.println("\nVisita añadida en tu historial de visitas, puede verla en el menú principal");
			if(bdResVis.eliminarReservaTrasJugar(usuario)>0)
				System.out.println("Se ha procedido a eliminar su reserva.\n\n¡Gracias por vuestra visita!");
			else
				System.out.println("\nSe ha producido un error eliminando su reserva, por favor, póngase en contacto con el empleado de la sala");
		}
		else
			System.out.println("\nSe ha producido un añadiendo su visita, por favor, póngase en contacto con el empleado de la sala");
		
		bdPisSal.reiniciarJuego(nsala);
		timer.stop();
		return true;
	}
}
