package tietorakenteet;

/**
 *
 * @param <E> Type of the objects you want to save in the list
 */
public class OwnArrayList<E> {

    private E[] list;
    private int size;

    /**
     * Creates an ArrayList with initial size of 10
     */
    public OwnArrayList() {
        this.list = (E[]) new Object[10];
        this.size = 0;
    }

    /**
     * Adds an element to the list
     *
     * @param e Element you want to add to the list
     */
    public void add(E e) {
        if (this.size == list.length) {
            createBiggerList();
        }

        this.list[this.size] = e;
        this.size++;

    }

    /**
     * Makes the list bigger in size
     */
    public void createBiggerList() {
        double newSizeAsDouble = this.list.length * 1.5;
        int newSize = (int) newSizeAsDouble;

        E[] newList = (E[]) new Object[newSize];

        for (int i = 0; i < this.list.length; i++) {
            newList[i] = this.list[i];
        }

        this.list = newList;

    }

    /**
     *
     * @return The amount of elements in the list
     */
    public int size() {
        return this.size;
    }

    /**
     *
     * @return true, if there are no elements in the list. Otherwise true
     */
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Get the element in the given index
     *
     * @param i Index of the wanted element
     * @return Returns an element from the desired index
     */
    public E get(int i) {
        return this.list[i];
    }

    /**
     * Check if the list contains a certain element
     *
     * @param e The element you want to see if the list contains
     * @return True if the list contains the element. Otherwise false
     */
    public boolean contains(E e) {
        for (int i = 0; i < this.size; i++) {
            if (this.list[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes an element from a certain index and moves all the elements from
     * its right side one step to the left
     *
     * @param index Index of the element you want to remove
     */
    public void remove(int index) {
        for (int i = index; i < this.size - 1; i++) {
            this.list[i] = this.list[i + 1];
        }

        this.size--;
    }

}
