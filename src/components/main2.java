
// JAVA CLASS DEFINITIONS

class Customer{
	String _name;
	Address _address;
	String _phoneNumber;

	public Customer(String name, Address address, String phoneNumber)
	{
		_name = name;
		_address = address;
		_phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return _address;
	}
}

class Address{
	String _street;
	String _city;
	public Address(String street, String city)
	{
		_street = street;
		_city = city;
	}
}

// Exemple de com guardar un objecte en Java

// Instanciem 2 objectes en JAVA. El client (Customer) té una adreça (Address)
Address addr = new Address("1 First Street", "San Jose");
Customer cust = new Customer("Michael",addr,"4089999999");
try{
// Obrim el fitxer que conté la BD representada per un ObjectContainer
	File file = new File("customers.yap");
	String fullPath = file.getAbsolutePath();
	ObjectContainer db = Db4oEmbedded.openFile(fullPath);
	// Guardem l’objecte client, que a la vegada conté un objecte adreça a una de les seues propietats
	db.store(cust); //cust és un objecte de la classe Customer
}
finally{
	// tanquem la base de dades
	db.close();
}

// Exemple de com recuperar un objecte de la BD en Java

try{
	Customer cust = null;
	// Obrim la base de dades
	File file = new File("customers.yap");
	String fullPath = file.getAbsolutePath();
	ObjectContainer db = Db4oEmbedded.openFile(fullPath);
	// Query by example - Creem un objecte que usarem per consultar la BD
	Customer example = new Customer("Carl"); //Busquem clients que es diguen Carl
	// Recuperem l’objecte(s) que concorden en l’example
	ObjectSet set = db.get(example);
	cust = (Customer)set.next();
	// Si l’hem trobat tractem el client recuperat, per exemple llegim la seua adreça
	if (cust != null) Address address = cust.getAddress();
}
finally{
	db.close(); //Tanquem la BD
}


// JAVA CLASS DEFINITION

class Account{
	double _balance;
	public double getBalance()
	{
	return _balance;
	}
}

// Exemple de consulta nativa usant Java

// Passem al mètode query de la BD un nou objecte Predicate que retornarà
// els comptes bancaris amb un balanç superior a 1000, sobreescrivint el
// mètode match
List <Account> accounts = db.query <Account> (
	new Predicate <Account> () {
		public boolean match(Account account){
			return account.getBalance() > 1000.0;
		}
	}
);


//Exemple de consulta SODA usant Java:

// Passem restriccions a l’objecte Query que en executar-lo retornarà
// els comptes bancaris amb un balanç superior a 1000
Query q = db.query();
q.constrain(Account.class);
q.descend("_balance").constrain(1000.0).greater();
ObjectSet result = q.execute();