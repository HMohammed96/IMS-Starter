Coverage: 62.4%
Inventory Management System (IMS) Fundamental Project

This is the README file of my IMS starter project which is a Inventory Management System that provides an application which allows a user to Create, Read, Update and Delete a Customer, Item or Order from an SQL database. Along with the functions mentioned already the user is able to Add an new item to an existing order or delete an item from an existing order. To complete this application MySQL was used as the database management system to create the database and tables, then Java was used as the back-end programming language in Eclipse to write the codebase.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The software that you will need to install are Git, MySQL, Java, Maven, JUnit.

Version Control System: Git 
Database Management System: MySQL Server 5.7+ (local or GCP instance) 
Back-End Programming Language: Java 
Build Tool: Maven 
Unit Testing: JUnit 

### Installing

A step by step series of examples that tell you how to get a development env running

1. Firstly you would use Git to clone down the repository before creating your own development branch to work on.
2. Open the folder of the project in your Eclipse IDE.
3. Once the project has loaded you can then navigate through the multiple files in the project where you will find the methods, database and tables also.


## Running the tests

To run the tests you would use JUnit.

### Unit Tests 

This tests the methods of creating a customer. It can be ran by running JUnit on the file.

	@Test
	public void testCreate() {
		final String F_NAME = "barry", L_NAME = "scott";
		final Customer created = new Customer(F_NAME, L_NAME);

		Mockito.when(utils.getString()).thenReturn(F_NAME, L_NAME);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}
  
  	@Test
	public void testCreate() {
		final Customer created = new Customer(2L, "chris", "perrins");
		assertEquals(created, DAO.create(created));
	}
  
  This tests the methods of creating a customer. It can be ran by running JUnit on the file.
  
  	@Test
	public void testUpdate() {
		Item updated = new Item(1l, "Air Jordan 3", 109d);
		
		Mockito.when(this.utils.getLong()).thenReturn(1l);
		Mockito.when(this.utils.getString()).thenReturn(updated.getItemName());
		Mockito.when(this.utils.getDouble()).thenReturn(updated.getPrice());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);
		
		assertEquals(updated, this.controller.update());
		
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);	
	}
  
  	@Test
	public void testUpdate() {
		final Item updated =  new Item(1l, "Air Jordan 3", 109d);
		assertEquals(updated, DAO.update(updated));
	}


## Deployment

To deploy run the fat.jar file.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Hamid Mohammed** - *Initial work* - [HMohammed96](https://github.com/HMohammed96)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 
