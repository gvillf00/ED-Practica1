package ule.edi.event;

//import static org.junit.Assert.assertFalse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.*;

import ule.edi.model.*;

public class EventArrayImplTests {

	private DateFormat dformat = null;
	private EventArrayImpl e;
	
	private Date parseLocalDate(String spec) throws ParseException {
        return dformat.parse(spec);
	}

	public EventArrayImplTests() {
		
		dformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	@Before
	public void testBefore() throws Exception{
	    e = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 110);
	    
	}
	
	@Test
	public void testEventoVacio() throws Exception {
		Person cliente=new Person("gemma", "1111T", 30);
		e.sellSeat(1, cliente, false);
	    Assert.assertTrue(e.getNumberOfAvailableSeats()==109);
	    Assert.assertEquals(e.getNumberOfAvailableSeats(), 109);
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 1);
	}
	
	@Test
	public void testSellSeat1Adult() throws Exception{
		
		Person cliente=new Person("10203040A","Alice", 34);
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
		Assert.assertTrue(e.sellSeat(1, cliente,false));	//venta normal
		//butaca ya ocupada qeu devuelve false
		Assert.assertFalse(e.sellSeat(1, cliente,false));
		//venta anticipada
		Assert.assertTrue(e.sellSeat(2, cliente,true));
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 2);  
	    Assert.assertEquals(e.getNumberOfNormalSaleSeats(), 1);
	  
	}
	

	
	@Test
	public void testgetCollection() throws Exception{
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 4);
		Assert.assertEquals(ep.sellSeat(1, new Person("1010", "AA", 10), true),true);
		Assert.assertTrue(ep.getCollectionEvent()==75);					
	}
	
	// TODO EL RESTO DE MÃ‰TODOS DE TESTS NECESARIOS PARA LA COMPLETA COMPROBACIÃ“N DEL BUEN FUNCIONAMIENTO DE TODO EL CÃ“DIGO IMPLEMENTADO
	
	@Test
	public void TestCrearArrayconprecioydescuento() throws Exception{
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 4,10.0,(byte)20);
		Assert.assertEquals(ep.sellSeat(1, new Person("1010", "AA", 10), false),true);
		Assert.assertTrue(ep.getCollectionEvent()==10.00);					
	}
	@Test
	public void TestConseguirNombre() throws Exception{
		//Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 4,10.0,(byte)20);
		//Assert.assertEquals("The Fabulous Five", e.getName());
		Assert.assertTrue(""!=e.getName());					
	}
	@Test
	public void TestConseguirFechaEvento() throws Exception{
		//Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 4,10.0,(byte)20);
		//Assert.assertEquals("The Fabulous Five", e.getName());
		Assert.assertTrue(null!=e.getDateEvent());					
	}

	

	
	  @Test public void TestConseguirNumeroAsientosVendidosCero() throws Exception{
	  //Assert.assertTrue(0!=e.getNumberOfSoldSeats()); 
		  Assert.assertEquals(0,e.getNumberOfSoldSeats()); 
	  }
	 
  
	
	
	  @Test public void TestAsientosVenidos() throws Exception{ 
		  Person persona1= new Person("gemma","999",20); 
		  Person persona2=new Person("jose","888",50); 
		  e.sellSeat(1, persona1, false); 
		  e.sellSeat(2,persona2,false);
		  
		  Assert.assertEquals(2, e.getNumberOfSoldSeats());
	  
	  }
	  
	  @Test public void TestAsientosVentaAnticipada() throws Exception{ 
		  Person persona1= new Person("gemmaAnticipada","111",20); 
		  e.sellSeat(5, persona1, true); 
		  e.sellSeat(6, persona1, false);
		  Assert.assertEquals(1, e.getNumberOfAdvanceSaleSeats());
		  
		  }
	  @Test
		public void TestNúmerodeAsientos() throws Exception{
			//Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 4,10.0,(byte)20);
			//Assert.assertEquals("The Fabulous Five", e.getName());
			//Assert.assertTrue(null!=e.getNumberOfSeats());	
			//Assert.assertTrue(110==e.getNumberOfSeats());
			Assert.assertEquals(110,e.getNumberOfSeats());
		}
	  @Test
		public void testgetSeat() {
			Person holder1 = new Person("cliente1","1111",50);
			Person holder2 = new Person("cliente2","2222",60);
			Person holder3 = new Person("cliente3","3333",45);
			e.sellSeat(1, holder1, false);
			e.sellSeat(2, holder2, false);
			e.sellSeat(3, holder3, false);
			
			Seat seat; 
			seat = e.getSeat(3);
			
			Assert.assertTrue(seat == e.getSeat(3));
			Assert.assertTrue(null == e.getSeat(0));
			Assert.assertTrue(null == e.getSeat(111));
		}
	  @Test
	  
		public void testPersonaOcupaButaca() {
		  Person holder1 = new Person("cliente1","1111",50);
		  Person holder2 = new Person("cliente2","2222",60);
		  Person holder3 = new Person("cliente3","3333",45);
		  e.sellSeat(1, holder1, false);
		  e.sellSeat(2, holder2, false);
		  e.sellSeat(3, holder3, false);
			
			Person QuienOcupa;
			QuienOcupa=e.refundSeat(2);
			
			Assert.assertTrue(QuienOcupa == e.refundSeat(2));
			Assert.assertTrue(null == e.refundSeat(5));
			Assert.assertTrue(null == e.refundSeat(0));
			//Assert.assertTrue(null == e.refundSeat(110));
			//Assert.assertTrue(null == e.refundSeat(110));
		}
	  @Test
	  
		public void testMenoresEdad() {
		  Person holder1 = new Person("cliente1","1111",12);
		  Person holder2 = new Person("cliente2","2222",60);
		  Person holder3 = new Person("cliente3","3333",10);
		  e.sellSeat(1, holder1, false);
		  e.sellSeat(2, holder2, false);
		  e.sellSeat(3, holder3, false);
			
			
			Assert.assertTrue(2 == e.getNumberOfAttendingChildren());
		
		}
	  @Test
	  
		public void testMayoresEdad() {
		  Person holder1 = new Person("cliente1","1111",12);
		  Person holder2 = new Person("cliente2","2222",66);
		  Person holder3 = new Person("cliente3","3333",10);
		  e.sellSeat(1, holder1, false);
		  e.sellSeat(2, holder2, false);
		  e.sellSeat(3, holder3, false);
			
			
			Assert.assertTrue(1 == e.getNumberOfAttendingAdults());
		
		}
	  @Test
	  
		public void TestAncianosAsistentes() {
		  Person holder1 = new Person("cliente1","1111",12);
		  Person holder2 = new Person("cliente2","2222",66);
		  Person holder3 = new Person("cliente3","3333",65);
		  e.sellSeat(1, holder1, false);
		  e.sellSeat(2, holder2, false);
		  e.sellSeat(3, holder3, false);
			
			
			Assert.assertTrue(2 == e.getNumberOfAttendingElderlyPeople());
		
		}
	  
	  @Test
	  
		public void TestListaAsientosDisponibles() {
		  List<Integer> AsientosDisp = new ArrayList<Integer>();
			
			Person holder1 = new Person("cliente1","111",40);			
			e.sellSeat(1, holder1, false);
			//vendo una y añado a la lista el resto hasta completar las 110 butacas
			//como vendo solo la butaca 1 relleno la lista en adelante
			for (int i = 1; i < 110; i++) {
				AsientosDisp.add(i+1);
			}
			
			Assert.assertEquals(AsientosDisp, e.getAvailableSeatsList());	

		
		}
	  @Test
	  
		public void TestListaAsientosVentaAnticipada() {
		  List<Integer> AsientosVentaAnticipada = new ArrayList<Integer>();
			Person holder1 = new Person("cliente1","111",40);			
			e.sellSeat(1, holder1, true);
			e.sellSeat(2, holder1, false);
			//vendo una y añado a la lista el resto hasta completar las 110 butacas
			//como vendo solo la butaca 1 relleno la lista en adelante
			//for (int i = 1; i < 109; i++) {
				AsientosVentaAnticipada.add(1);
			//}
			Assert.assertEquals(AsientosVentaAnticipada, e.getAdvanceSaleSeatsList());
				
		
		}
	  @Test
	  
		public void TestAsientosConsecutivos() {
		  Person holder1 = new Person("cliente1","1111",12);
		  Person holder2 = new Person("cliente2","2222",66);
		  Person holder3 = new Person("cliente3","3333",65);
		  e.sellSeat(1, holder1, false);
		  e.sellSeat(50, holder2, false);
		  e.sellSeat(110, holder3, false);
			
			
			Assert.assertEquals(59, e.getMaxNumberConsecutiveSeats());
		
		}
	  
	  @Test
		public void TestConseguirPrecio() throws Exception {
		  //evento con precio
		  e=new EventArrayImpl("Concierto", parseLocalDate("24/02/2020 18:00:00"), 10);
		   
			Person holder1 = new Person("cliente1","1111",50);
			Person holder2 = new Person("cliente2","2222",60);
			Person holder3 = new Person("cliente3","3333",45);
			e.sellSeat(1, holder1, false);
			e.sellSeat(2, holder2, false);
			e.sellSeat(3, holder3, true);
			Seat asiento;
			Seat asiento2;
			
			asiento = e.getSeat(3);
			Assert.assertTrue(75.0 == e.getPrice(asiento));
			asiento=e.getSeat(2);
			Assert.assertTrue(100.0 == e.getPrice(asiento));
			//Assert.assertTrue(100.0 == e.getPrice(2));
			
			EventArrayImpl e1;
			e1=new EventArrayImpl("ConciertoNuevo", parseLocalDate("24/03/2020 18:00:00"), 20);
			e1.sellSeat(1, holder3, false);
			asiento2=e1.getSeat(1);
			Assert.assertEquals(e.getPrice(asiento2), 0.0, 0.0);
			
		}
	  @Test
	  
		public void TestButacaPersona() {
		  Person holder1 = new Person("cliente1","1111",12);
		  Person holder2 = new Person("cliente2","2222",66);
		  Person holder3 = new Person("cliente3","3333",65);
		  //cliente que no vendo
		  Person holder4 = new Person("cliente4","4444",20);
		  e.sellSeat(1, holder1, false);
		  e.sellSeat(2, holder2, false);
		  e.sellSeat(5, holder3, false);
			
			
			Assert.assertTrue(5 == e.getPosPerson(holder3));
			Assert.assertTrue(-1 == e.getPosPerson(holder4));
		
		}
	  
	  @Test
	  
		public void TestPersonaDuplicada() {
		  Person holder1 = new Person("cliente1","1111",12);
		  Person holder2 = new Person("cliente1","1111",12);
		  Person holder3 = new Person("cliente3","3333",65);
		  //cliente que no vendo
		  Person holder4 = new Person("cliente4","4444",20);
		  e.sellSeat(5, holder1, false);
		  e.sellSeat(7, holder2, false);
		  e.sellSeat(10, holder3, true);
			
		  //persona en asiento 5 sin venta anticipada
			Assert.assertFalse(e.isAdvanceSale(holder1));
			//persona duplicada en asiento 5 el primero y venta anticipada
			Assert.assertTrue(e.isAdvanceSale(holder3));
			//persona no encontrada no tiene asiento
			Assert.assertFalse(e.isAdvanceSale(holder4));
		
		}

	  
}
