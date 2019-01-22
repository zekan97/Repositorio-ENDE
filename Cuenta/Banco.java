package cuentas;

import java.util.Scanner;

public class Banco {

	/**
	 *  Atributos que necesitamos 
	 *  para crear la aplicación 
	 *  de las cuentas bancarias
	 */
		private int numCuentas;
		private CCuenta array[];
		
	/**
	 *  Constructor
	 */
	/////////////////////////////////////////////////////
		
		public Banco() {
			numCuentas = 0;
			array = new CCuenta[numCuentas];
		}
		
	/**
	 * El metodo unElementoMas es un metodo que pide
	 * un espacio más en memoria para que el array pueda
	 * almacenar otro elemento.
	 */
		/////////////////////////////////////////////////////
		public void unElementoMas(CCuenta aux[]) {
			//Pedimos memoria para una cuenta nueva
			array = new CCuenta[numCuentas +1];
			//Copiamos todo
			for(int i = 0; i < numCuentas; i++)	{
				array[i] = aux[i];
			} 
			//Actualizamos el número de contactos
			numCuentas++;
		}
		
		/**
		 * El metodo unElementoMenos es un metodo que quita
		 * un espacio en memoria para eliminar
		 * un elemento
		 */
		/////////////////////////////////////////////////////
		
		public void unElementoMenos(CCuenta aux[])	{
			

			array = new CCuenta[numCuentas -1];
			/**
			 * J se usa para posicionarte en el array destino
			 */
			int j = 0;
			for(int i = 0; i < numCuentas; i++)	{
				if(aux[i] != null)	{
					array[j] = aux[i];
					j++;
				}
			}
			numCuentas --;
		}
		
	/////////////////////////////////////////////////////
		
		public boolean arrayVacio() {
			return (numCuentas == 0);
		}
		
		/////////////////////////////////////////////////////////////////////////////////
		
		public void operacionesMenu() {
			boolean salir = false;
;
			
			if (!salir)
			do {
				int opcion = verMenu();
				salir = (opcion == 7);
				switch(opcion)	{ 
				case 1:
					consulta();
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					altas();
					break;
				case 5:
					bajas();
					break;					
				case 6:
					
					break;
				}
			}while (!salir);	
			System.out.println("Programa finalizado");
		}


		/////////////////////////////////////////////////////////////////////////////////////////
		
		public int verMenu()
		{
			Scanner entrada = new Scanner (System.in);
			boolean opcionValida = true;
			int opcion;
			System.out.println("¿Que quieres hacer?");
			System.out.println("-------------------");
			System.out.println("1.Consultar saldo");
			System.out.println("2.Ingreso");
			System.out.println("3.Reintegro");
			System.out.println("4.Altas");
			System.out.println("5.Bajas");
			System.out.println("6.Mantemiento");
			System.out.println("7.Salir");
			do 
			{ 	
				System.out.println("Elige una opción: ");
				opcion = entrada.nextInt();	
				opcionValida = (opcion >=1 && opcion <=7);
				if (!opcionValida) 
				{
					System.err.println("La opcion no es valida");	
				}
			}while (!opcionValida);
			return opcion;	
		}
		
		///////////////////////////////////////////////////////////////////////////////////////
		
		public int buscar(String termino) {
				for(int i = 0; i < numCuentas; i++) {
				if(termino.equals(array[i].getNombre()) || termino.equals(array[i].getCuenta())) {
					return i;
				}	
			}
			return -1;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		public boolean eliminar(String cuenta) {
			if (arrayVacio()) {
				System.out.println("No hay ninguna cuenta guardada");
				return false;
			} else {	

			int pos = buscar(cuenta);
				if (pos == -1) {
					return false;
				} else {
					array[pos] = null;
					unElementoMenos(array);
					System.out.println("La cuenta ha sido eliminida.");
					return true;
				}
		}
	}	
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		public void insertarEn(CCuenta cuenta, int pos ) {
			boolean posValida; 
			posValida = (pos >= numCuentas);
				if (posValida) {
					System.err.println("La posicion donde deseas insertar esta cuenta no es valida");
				} else {
					array[pos] = cuenta;
					System.out.println("Se ha insertado correctamente");
				}
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		public String retornarValor(int pos) {
			boolean posValida;
			posValida = (pos >= numCuentas);
			if (posValida) {
				return null;
			}
			return array[pos].cuenta;
			
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		public void anadir(CCuenta cuenta)
		{
			unElementoMas(array);
			insertarEn(cuenta, numCuentas-1);
		}
		
		//////////////////////////////////////////////////////////////////////
		
		public void altas() {
				Scanner entrada = new Scanner (System.in);
				boolean opcionValida = true;
				int opcion;
				System.out.println("¿Que quieres tipo de cuentas es?");
				System.out.println("-------------------");
				System.out.println("1.Cuenta ahorro");
				System.out.println("2.Cuenta corriente");
				System.out.println("3.Cuenta corriente con intereses");
				do 	{ 	
					System.out.println("Elige el tipo de cuenta: ");
					opcion = entrada.nextInt();	
					opcionValida = (opcion >=1 && opcion <=3);
					if (!opcionValida) 
					{
						System.err.println("La opcion no es valida");	
					}
				}while (!opcionValida);
				leerDatos(opcion);
			}	
		
		//////////////////////////////////////////////////////////////////////////
		
		public void leerDatos(int opcion) {
			//Introducimos los datos
			Scanner entrada = new Scanner(System.in);
			System.out.println("Introduce el nombre");
			String nombre = entrada.nextLine();
			System.out.println("Introduce el numero de cuenta");
			String cuenta = entrada.nextLine();
			System.out.println("Introduce los intereses de la cuenta");
			double intereses = entrada.nextDouble();
			System.out.println("¿Cuanto quieres ingresar en esta cuenta?");
			double saldo = entrada.nextDouble();
				if (opcion == 1) {
					System.out.println("¿Que cuota de mantenimiento tiene esta cuenta?");
					double cuotaMantenimiento = entrada.nextDouble();
				anadir( new CCuentaAhorro (nombre, cuenta, intereses, saldo, cuotaMantenimiento));
				} else {
					if(opcion == 2) {
						System.out.println("¿Cuantas transacciones tiene exentas de comision?");
						int transExentas = entrada.nextInt();
						System.out.println("¿Que comision tiene por transaccion?");
						double importePorTrans = entrada.nextDouble();
						anadir( new CCuentaCorriente (nombre, cuenta, intereses, saldo, importePorTrans, transExentas ));
					} else {
						System.out.println("¿Cuantas transacciones tiene exentas de comision?");
						int transExentas = entrada.nextInt();
						System.out.println("¿Que comision tiene por transaccion?");
						double importePorTrans = entrada.nextDouble();
						anadir( new CCuentaCorrienteConIn (nombre, cuenta, intereses, saldo, importePorTrans, transExentas ));
					}
				}
			
		}	
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		
		public String leerCuenta() {
			
			Scanner entrada = new Scanner(System.in);
			System.out.println("Introduce el numero de cuenta que deseas buscar");
			String cuenta = entrada.nextLine();
			return cuenta;
			
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		public String leerNombre() {
			
			Scanner entrada = new Scanner(System.in);
			System.out.println("Introduce el nombre del titular");
			String nombre = entrada.nextLine();
			return nombre;			
		}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
		
		public void consulta() {
			Scanner entrada = new Scanner(System.in);
			boolean opcionValida;
			String opcion;
			String cuentaonombre = null;
			if (arrayVacio()) {
					System.out.println("No hay cuentas");
			} else {
			System.out.println("¿Quieres buscar por nombre de titular o numero de cuenta?");
			System.out.println("Introduce una 'n' para el nombre o una 'c' para el numero de cuenta");
			System.out.println("-------------------------------------------------------------------");
			do {
			opcion = entrada.nextLine();
			opcionValida = (opcion.equals("n") || opcion.equals("c"));
				if(opcion.equals("n")) {
					cuentaonombre = leerNombre();
				} else {
					if(opcion.equals("c")) {
						cuentaonombre = leerCuenta();
					} else { 
						System.err.println("La opcion no es valida");
					}
				}
			} while (!opcionValida);
			int pos = buscar(cuentaonombre);
			System.out.println("El nombre es " + array[pos].getNombre() + " el numero de cuenta es " + array[pos].getCuenta() + " y el saldo que tiene es " + array[pos].getSaldo());
			}
		}	
		
		////////////////////////////////////////////////////////////////////////////////////
		
		public void bajas() {
			if (arrayVacio()) {
				System.out.println("No hay cuentas");
			} else {
				String cuenta = leerCuenta();
				eliminar(cuenta);
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////////
		
		public void ingresar() {
			Scanner entrada = new Scanner(System.in);
			System.out.println("¿Cuanto deseas ingresar?");
			double cantidad = entrada.nextDouble();
			
		
		}
	}
