
package generaattori;


public class Main {


    public static void main(String[] args) {
        Dungeon dungeon = new Dungeon(15, 60);
        
        dungeon.placeRooms();
        
        dungeon.print();
    }
    
}
