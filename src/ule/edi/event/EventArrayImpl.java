package ule.edi.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.Random;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;


public class EventArrayImpl implements Event {
	
	private String name;
	private Date eventDate;
	private int nSeats;
	
	private Double price;    // precio de entradas 
	private Byte discountAdvanceSale;   // descuento en venta anticipada (0..100)
   	
	private Seat[] seats;
		
	
   public EventArrayImpl(String name, Date date, int nSeats){
	 //TODO 
	 // utiliza los precios por defecto: DEFAULT_PRICE y DEFAULT_DISCOUNT definidos en Configuration.java   
	 // Debe crear el array de butacas
	   //el array está declarado con private seat [] de tipo seats y aquí lo inicio
	   //con el número de asientos que me pasan en la función.
	this.seats=new Seat[nSeats];
	//asigno también el nombre del evento.
	this.name=name;
	//asigno la fecha del evento.
	this.eventDate=date;
	//asigno los precios y descuentos por defecto que están en la clase configuración
	this.price =Configuration.DEFAULT_PRICE;
	this.discountAdvanceSale =Configuration.DEFAULT_DISCOUNT;
	this.nSeats =nSeats;
	
	   
   }
   
   
   public EventArrayImpl(String name, Date date, int nSeats, Double price, Byte discount){
	   //TODO 
	   // Debe crear el array de butacas
	   this.seats=new Seat[nSeats];
		//asigno también el nombre del evento.
		this.name=name;
		//asigno la fecha del evento.
		this.eventDate=date;
		//asigno precios, y coje los precios y los descuentos por defecto.
		this.price=price;
		this.discountAdvanceSale=discount;
		this.nSeats =nSeats;
   }


@Override
public String getName() {
	// TODO Auto-generated method stub
	return this.name;
}


@Override
public Date getDateEvent() {
	// TODO Auto-generated method stub
	return this.eventDate;
}


@Override
public Double getPrice() {
	// TODO Auto-generated method stub
	return this.price;
}


@Override
public Byte getDiscountAdvanceSale() {
	// TODO Auto-generated method stub
	return this.discountAdvanceSale;
}

//número de butacas vendidas.
@Override
public int getNumberOfSoldSeats() {
	// TODO Auto-generated method stub
	//consulto la longitud del array
	int vendidas=0;
	//recorro el array
	for (int i = 0; i < this.seats.length; i++) 
	{// si el asiento es nulo no hago nada, si no es nulo lo sumo a la vble.
		if (this.seats[i]!=null) 
		{
		    vendidas=vendidas+1;
		}  
	}
	
	return vendidas;
}

//número de butacas en venta normal
@Override
public int getNumberOfNormalSaleSeats() {
	//calcula el n�mero de butacas vendidas en forma normal del evento.
	// TODO Auto-generated method stub
	int vendidas=0;
	for (int i = 0; i < this.seats.length; i++) {
		//comprobar qeu no sea nulo el asiento
		if (this.seats[i]!=null) {
			//el tipo está en la clase de configuration.
			if (this.seats[i].getType()==Configuration.Type.NORMAL) {
			    vendidas=vendidas + 1;
			} 	
		}
	}
	return vendidas;
}


@Override
public int getNumberOfAdvanceSaleSeats() {
	// TODO Auto-generated method stub
	int vendidasadelantadas=0;
	for (int i = 0; i < this.seats.length; i++) {
		//comprobar que el asiento no sea nulo que si lo es falla
		if (this.seats[i]!=null) {
			//el tipo está en la clase de configuration.
			if (this.seats[i].getType()==Configuration.Type.ADVANCE_SALE) {
				vendidasadelantadas=vendidasadelantadas + 1;
			} 
		}
	}
	return vendidasadelantadas;
}


@Override
public int getNumberOfSeats() {
	// TODO Auto-generated method stub
	return this.nSeats;
}


@Override
public int getNumberOfAvailableSeats() {
	// TODO Auto-generated method stub
	//n�mero de asientos dispobibles no vendidas, los recorro y cuento los null
	int disponibles=0;
	for (int i = 0; i < this.seats.length; i++) {
		if (this.seats[i]==null) {
			disponibles=disponibles + 1;
		} 
	}
	return disponibles;
	
}


@Override
public Seat getSeat(int pos) {
	//comprobamos que el asiento no es null porque si no da error
	//if (this.seats[pos]!=null) {
		// TODO Auto-generated method stub
		//si est� todo bien devuelve la butaca en la posici�n solicitada para trabajar con ella.
		//si la posici�n no es correcta devuelve null
		if (pos<1 || pos>seats.length ) {
			return null;
		}
		return seats[pos-1];
	//}
	//else {
	//	return null;
	//}
}


@Override
public Person refundSeat(int pos) {
	// TODO Auto-generated method stub
	//si la posici�n que pasa no es correcta devuelve null, si es correcta 
	//y esta ocupada devuelve la persona que la ocupa.
	if (seats[pos]!=null && pos>=1) {
		
			return seats[pos-1].getHolder();
		
	}	
	return null;
}


@Override
public boolean sellSeat(int pos, Person p, boolean advanceSale) {
	// TODO Auto-generated method stub
	//si la butaca est� ya ocupada no hace nada, 
	//si no est� ocupada la vende.
	//true indica si se pudo realizar la venta de la butaca, 
	//false en caso contrario
	if (this.seats[pos-1]==null) {
		//si es de tipo venta anticipada guardo un valor, si no el normal
		if(advanceSale==true) {
			seats[pos-1] = new Seat(this,pos-1, Type.ADVANCE_SALE, p );
		}
		else 
		{
			this.seats[pos-1]=new Seat (this, pos-1, Type.NORMAL ,p);
		}
		return true;
	}
	else 
	{
		return false;
	}
}


@Override
public int getNumberOfAttendingChildren() {
	//número de niños asistentes al evento.
	// TODO Auto-generated method stub
	//recorro el array consultando que tenga contenido
	int menoredad=0;
	for (int i = 0; i < this.seats.length; i++) {
		if (this.seats[i]!=null) {
			//tiene contenido, busco la edad de la persona que ocupa el asiento
			//y la comparo con la edad m�nima en configuraci�n
			if (this.seats[i].getHolder().getAge()<= Configuration.CHILDREN_EXMAX_AGE) {
				menoredad=menoredad +1;
			}
		}
	}
	
	return menoredad;
}


@Override
public int getNumberOfAttendingAdults() {
	//número de adultos asistentes al evento.
	// TODO Auto-generated method stub
	//recorro el array consultando que tenga contenido
	int mayoredad=0;
	for (int i = 0; i < this.seats.length; i++) {
		if (this.seats[i]!=null) {
			//tiene contenido, busco la edad de la persona que ocupa el asiento
			//y la comparo con la edad superior a la de menor de edad
			if (this.seats[i].getHolder().getAge()> Configuration.CHILDREN_EXMAX_AGE) {
				mayoredad=mayoredad +1;
			}
		}
	}
	
	return mayoredad;
	
}


@Override
public int getNumberOfAttendingElderlyPeople() {
	//calcula el número de ancianos asistentes al evento
	// TODO Auto-generated method stub
	//recorro el array consultando que tenga contenido
	int ancianos=0;
	for (int i = 0; i < this.seats.length; i++) {
		if (this.seats[i]!=null) {
			//tiene contenido, busco la edad de la persona que ocupa el asiento
			//y la comparo con la edad superior a la de menor de edad
			if (this.seats[i].getHolder().getAge()>= Configuration.ELDERLY_PERSON_INMIN_AGE) {
				ancianos=ancianos +1;
			}
		}
	}
	
	return ancianos;
}


@Override
public List<Integer> getAvailableSeatsList() {
	//calcula la lista de números de butacas disponibles.
	// TODO Auto-generated method stub
	//declaro variable lista con un nuevo arraylist
	List<Integer> asientosdisp = new ArrayList<Integer>();
	//recorro el array de asientos.
	for (int i = 0; i < this.seats.length; i++) {
		if (this.seats[i]==null) {
			//si el asiento está vacío añado a la lista el número de butaca
			asientosdisp.add(i+1);
		}
	}
	return asientosdisp;
}


@Override
public List<Integer> getAdvanceSaleSeatsList() {
	//calcula la lista de asientos vendidos en venta anticipada
	// TODO Auto-generated method stub
	//declaro variable lista con un nuevo arraylist
	List <Integer> asientosVA = new ArrayList<Integer>();
	
	for (int i = 0; i < this.seats.length; i++) {
		if (this.seats[i]!=null) {
			//consulto si es de venta anticipada
			if (seats[i].getType()==Configuration.Type.ADVANCE_SALE) {	
				asientosVA.add(i+1);
			}
		}
	}
	return asientosVA;
}


@Override
public int getMaxNumberConsecutiveSeats() {
	//calcula el número máximo de posiciones dispobibles consecutivas.
	
	//array de enteros, pero que al final no he usado.
	//List <Integer> listaconsecutivos=new ArrayList<Integer>();
	// TODO Auto-generated method stub
	
	int i=0;
	int contador=0;//cuento los que están nulos
	int acumulado=0;//meto el mayor valor que va encontrando.
	for (i = 0; i < this.seats.length; i++) {
		if (this.seats[i]==null) {
			contador=contador+1;
		}
		else {
			if (contador>acumulado) {
				acumulado=contador;
				contador=0;
			}
		}
	}
	
	return acumulado;
}


@Override
public Double getPrice(Seat seat) {
	// TODO Auto-generated method stub
	//calcula el precio de la butaca en función del tipo de venta
	//y del descuento.
	//si el evento que tiene asignado la butaca no es el que hace
	//la llamada (this) devolverá cero.
	double descuento=0.0;
	double preciobutaca=0.0;
	if (this!=seat.getEvent()) {
		preciobutaca=0.0;
		return preciobutaca;
	}
	
	//cojo el precio de la butaca.
	preciobutaca=seat.getEvent().getPrice();
	
	if (seat.getType()==Configuration.Type.ADVANCE_SALE) {
		descuento=seat.getEvent().getDiscountAdvanceSale();
		preciobutaca=preciobutaca-((preciobutaca*descuento)/100);
	}
	return preciobutaca;
}


@Override
public Double getCollectionEvent() {
	//calcula el importe total recaudado por las butacas ocupadas.
	// TODO Auto-generated method stub
	double totalimporte=0.0;
	
	for(int i=0;i<this.seats.length;i++) {
		if (this.seats[i]!=null) {
			totalimporte=totalimporte+this.getPrice(seats[i]);
		}
		
	}
	return totalimporte;
}


@Override
public int getPosPerson(Person p) {
	// TODO Auto-generated method stub
	//calcula el número de butaca qeu ocupa la persona qeu nos pasan.
	//tengo qeu buscar a la persona en el array de asientos.
	//recorro el array
	
	for (int i=0;i<this.seats.length ;i++) {
		//compruebo que no sea nulo primero porque si no falla
		if (this.seats[i]!=null) {
			//prueba=this.seats[i].getHolder().getNif() == p.getNif();
			if (this.seats[i].getHolder().equals(p)==true) {
				//ha encontrado la persona.
				return i+1;
			}
		}
	}
	return -1;
}


@Override
public boolean isAdvanceSale(Person p) {
	// TODO Auto-generated method stub
	//Si la persona ocupase más de una butaca, se considerará la butaca 
	//de menor nº. Si no ocupa ninguna butaca, se devuelve false
	//true si la persona ocupa una butaca vendida en venta anticipada, 
	//false en caso contrario
	int menorasiento=0;
	for (int i=0;i<this.seats.length ;i++) {
		//prueba=this.seats[i].getHolder().getNif() == p.getNif();
		//compruebo que no sea nulo primero porque si no falla
		if (this.seats[i]!=null) {
			if (this.seats[i].getHolder().equals(p)==true) {
				//ha encontrado la persona.
				menorasiento=i;
			}
		}

	}
	if (menorasiento==0) {
		//no está la persona
		return false;
	}
	else 
	{
		//si está, y hay qeu saber si es de venta anticipada o no
		if(this.seats[menorasiento].getType()==Type.ADVANCE_SALE) {
			return true;
		}
		else 
		{
			return false;
		}
		
	}	
}
}