package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.Client;
import Service.ClientService;

class ClientTest {

	@Test
	void testInsert() {
		ClientService client = new ClientService();
		Client C =new Client();
		
		client.insertClient("Marcel", 123, "1150291245158", "Crangului");
		
		C=client.findClientByName("Marcel");
		
		
		assertEquals("Marcel",C.getNume());
		assertEquals(123,C.getIDcard());
		assertEquals("1150291245158",C.getPersonalCode());
		assertEquals("Crangului",C.getAddress());
		
		
	}

}
