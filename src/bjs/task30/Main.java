package bjs.task30;

public class Main {

    public static void main(String[] args) {
        ComputersStore computersStore = new ComputersStore();
        ComputersStoreLoader storeLoader = new ComputersStoreLoader(computersStore);
        storeLoader.parse("computerStore.xml");

        System.out.print(computersStore);
    }
}

/*
Catalogs in the computer store:
Catalog id = 1
	Type Id: 1
	Title: Computer1
	Type: Desktop
	Amount: 10

	Type Id: 2
	Title: Computer2
	Type: Laptop
	Amount: 20

	Type Id: 3
	Title: Computer3
	Type: Tablet
	Amount: 30
 */
